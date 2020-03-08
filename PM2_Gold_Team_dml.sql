USE GameRater;

DROP TABLE IF EXISTS rawSteamUserReviews;
DROP TABLE IF EXISTS rawidToUsername;
DROP TABLE IF EXISTS rawMetaCriticGame;
DROP TABLE IF EXISTS rawMetaCriticGameGenre;
DROP TABLE IF EXISTS rawVgchartzGame;
DROP TABLE IF EXISTS rawMetaUserComments;
DROP TABLE IF EXISTS rawMetaUserCommentsFixed;
DROP TABLE IF EXISTS rawMetaCriticGameReviews;
DROP TABLE IF EXISTS rawMetaCriticGameReviewsFixed;

CREATE TABLE rawSteamUserReviews (
  UserId INT,
  GameName TEXT,
  PurchasedOrPlayed TEXT,
  PlayTime FLOAT,
  Zero INT
) ENGINE = InnoDB;

CREATE TABLE rawidToUsername (
  UserId INT,
  UserName varchar(255)
) ENGINE = InnoDB;

CREATE TABLE rawMetaCriticGame (
  Title varchar(255),
  TheYear INT NULL,
  Publisher varchar(255),
  Genre varchar(255), 
  Platform varchar(255), 
  Metascore INT NULL,
  AvgUserscore FLOAT NULL,
  Players varchar(255)
) ENGINE = InnoDB;

CREATE TABLE rawMetaCriticGameGenre (
  Title varchar(255),
  Genre varchar(255)
) ENGINE = InnoDB;

CREATE TABLE rawVgchartzGame (
  TheRank INT,
  TheName varchar(255),
  basename varchar(255),
  Genre varchar(255), 
  ESRB_Rating varchar(255), 
  Platform varchar(255),
  Publisher varchar(255),
  Developer varchar(255),
  VGChartz_Score FLOAT NULL,
  Critic_Score FLOAT NULL,
  User_Score FLOAT NULL,
  Total_Shipped FLOAT NULL,
  TheYear FLOAT NULL
) ENGINE = InnoDB;

CREATE TABLE rawMetaCriticGameReviews (
  CriticName varchar(255),
  Review TEXT,
  Game varchar(255),
  Platform varchar(255), 
  Score FLOAT NULL, 
  TheDate DATE
) ENGINE = InnoDB;

CREATE TABLE rawMetaCriticGameReviewsFixed (
  CriticName varchar(255),
  Review TEXT,
  Game INT,
  Score FLOAT NULL
) ENGINE = InnoDB;

DELIMITER $$
 
CREATE TRIGGER CriticReviewTrigger
AFTER INSERT
ON rawMetaCriticGameReviewsFixed FOR EACH ROW
BEGIN
    DECLARE review_id INT DEFAULT 0;

    INSERT INTO reviews(GameIdFK, Review)
    VALUES(NEW.Game, NEW.Review);
    
    -- get review_id
    SET review_id= LAST_INSERT_ID();

    INSERT INTO criticreviews(ReviewIdFk, CriticName, Score)
        VALUES(review_id, NEW.CriticName, NEW.Score);
END$$
DELIMITER ;


CREATE TABLE rawMetaUserComments (
CommentID INT,
GameName TEXT,
Platform VARCHAR(255),
UserScore FLOAT,
MetaComment TEXT,
UserName TEXT
) ENGINE = InnoDB;

CREATE TABLE rawMetaUserCommentsFixed (
GameId TEXT,
UserScore FLOAT,
Review TEXT,
UserId INT
) ENGINE = InnoDB;

DELIMITER $$
CREATE TRIGGER UserReviewTrigger
AFTER INSERT
ON rawMetaUserCommentsFixed FOR EACH ROW
BEGIN
    DECLARE review_id INT DEFAULT 0;
    INSERT INTO reviews(GameIdFK, Review)
    VALUES(NEW.GameId, NEW.Review);
    
    -- get review_id
    SET review_id= LAST_INSERT_ID();
    
    -- insert review into critic account
    INSERT INTO userreviews(ReviewIdFk, UserIdFk, Score)
        VALUES(review_id,NEW.UserId,NEW.UserScore);
END$$
DELIMITER ;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Steam.csv' IGNORE INTO TABLE rawSteamUserReviews
 FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
 LINES TERMINATED BY '\n';
 
 -- Load our steam games into the games table.
INSERT INTO Games (GameName) SELECT DISTINCT GameName FROM rawSteamUserReviews;

-- Load SteamID to UserName mapping pulled from API
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/idToUsername.csv' INTO TABLE rawidToUsername
 FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 (UserName, UserId); 
 
 -- Load our steam users from the Steam into a user table
INSERT INTO Users (SteamId, Username) SELECT DISTINCT UserId,UserName FROM rawidToUsername;

-- Map users to games played
INSERT IGNORE INTO userhasgame (SELECT users.UserId, games.GameId, playedGame.PlayTime
FROM users
INNER JOIN (SELECT * FROM rawsteamuserreviews WHERE PurchasedOrPlayed='play') AS playedGame
  ON users.SteamId = playedGame.UserId
INNER JOIN games
  ON games.GameName = playedGame.GameName);

-- If the user has purchased a game, but has not played it, insert as gametime 0
INSERT IGNORE INTO userhasgame (Select users.UserId, games.GameId, 0
FROM users
INNER JOIN (SELECT * FROM rawsteamuserreviews WHERE PurchasedOrPlayed='purchase') as purchasedGame
  ON users.SteamId = purchasedGame.UserId
INNER JOIN games
  ON games.GameName = purchasedGame.GameName);


LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/metacriticGameInfo.csv' INTO TABLE rawMetaCriticGame
 FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 IGNORE 1 LINES
 (@var,Title,@TheYear,Publisher,Genre,Platform,@Metascore,@score,Players)
 SET
   AvgUserscore = if(@score in ('tbd', 'not specified'), NULL, @score),
   TheYear = if(@TheYear in ('tbd', 'not specified'), NULL, @TheYear),
   MetaScore = if(@Metascore in ('tbd', 'not specified'), NULL, @Metascore);

-- Insert publisher and Platform information for games   
INSERT IGNORE INTO Publishers (PublisherName) SELECT DISTINCT Publisher FROM rawMetaCriticGame;
INSERT IGNORE INTO Platforms (PlatformName) SELECT DISTINCT Platform FROM rawMetaCriticGame;

-- Update and insert games that do not have steam reviews into games table.
INSERT INTO games (GameName, PublisherIdFk, ReleaseYear)
SELECT rawmetacriticgame.Title, publishers.PublisherId, rawmetacriticgame.TheYear
FROM rawmetacriticgame
INNER JOIN publishers
  ON publishers.PublisherName = rawmetacriticgame.Publisher
ON DUPLICATE KEY UPDATE
PublisherIdFk = publishers.PublisherId,
ReleaseYear = rawmetacriticgame.TheYear;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/mcGameInfoGenre.csv' INTO TABLE rawMetaCriticGameGenre
 FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 IGNORE 1 LINES ; 

INSERT IGNORE INTO Genres (Genre) SELECT DISTINCT Genre FROM rawMetaCriticGameGenre;
-- Map games to genres (games can have more than one genre)
INSERT IGNORE INTO gameisgenre (GameIdFk, GenreIdFk)  (SELECT games.GameId, genres.GenreId
FROM games
INNER JOIN rawmetacriticgamegenre 
  ON games.GameName = rawmetacriticgamegenre.Title
INNER JOIN genres 
  ON rawmetacriticgamegenre.Genre = genres.Genre);
   
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/vgchartz12042019.csv' INTO TABLE rawVgchartzGame
 FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 IGNORE 1 LINES
 (TheRank,TheName,basename,Genre,ESRB_Rating,Platform,Publisher,Developer,@VGChartz_Score,@Critic_Score,@User_Score,@Total_Shipped,@Global_Sales,@NA_Sales,@PAL_Sales,@JP_Sales,@Other_Sales,@TheYear,@Last_Update,@url,@thestatus,@Vgchartzscore,@img_url)
 SET
	VGChartz_Score = nullif(@VGChartz_Score,''),
	Critic_Score = nullif(@Critic_Score,''),
	User_Score = nullif(@User_Score,''),
	Total_Shipped = nullif(@Total_Shipped,''),
	TheYear = nullif(@TheYear,''); 

-- Add a more exastive list of games, including older console games to the platforms and publishers tables    
INSERT IGNORE INTO Publishers (PublisherName) SELECT DISTINCT Publisher FROM rawVgchartzGame;
INSERT IGNORE INTO Platforms (PlatformName) SELECT DISTINCT Platform FROM rawVgchartzGame;
INSERT IGNORE INTO Genres (Genre) SELECT DISTINCT Genre FROM rawVgchartzGame;

-- Update our games table with new entries
INSERT INTO games (GameName, PublisherIdFk, ReleaseYear)
SELECT rawvgchartzgame.TheName, publishers.PublisherId, rawvgchartzgame.TheYear
FROM rawvgchartzgame
INNER JOIN publishers
  ON publishers.PublisherName = rawvgchartzgame.Publisher
ON DUPLICATE KEY UPDATE
PublisherIdFk = publishers.PublisherId,
ReleaseYear = rawvgchartzgame.TheYear;

-- Fill in the platform information for games (games can be on multiple platforms
INSERT INTO GameOnPlatform SELECT games.GameId, platforms.PlatformId 
FROM games
  INNER JOIN rawvgchartzgame
    ON games.GameName = rawvgchartzgame.TheName
  INNER JOIN platforms
    ON rawvgchartzgame.Platform = platforms.PlatformName;

-- Map games to genres (games can have more than one genre)
INSERT IGNORE INTO gameisgenre (GameIdFk, GenreIdFk)  (SELECT games.GameId, genres.GenreId
FROM games
INNER JOIN rawvgchartzgame 
  ON games.GameName = rawvgchartzgame.TheName
INNER JOIN genres 
  ON rawvgchartzgame.Genre = genres.Genre);
  
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/metacriticUserComments_1.csv' IGNORE INTO TABLE rawMetaUserComments
 CHARACTER SET 'utf8'
 FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' ESCAPED BY "\\"
 LINES TERMINATED BY '\n'
 IGNORE 1 LINES;
 
INSERT IGNORE INTO Users (UserName) SELECT UserName FROM rawMetaUserComments;

INSERT INTO rawMetaUserCommentsFixed (GameId,UserScore,Review,UserId)
  SELECT games.GameId, rawMetaUserComments.UserScore, rawMetaUserComments.MetaComment, users.UserId
  FROM rawMetaUserComments
INNER JOIN games
  ON games.GameName = rawMetaUserComments.GameName
INNER JOIN users
  ON users.UserName = rawMetaUserComments.UserName;
  
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/metacriticCriticReviews.csv' IGNORE INTO TABLE rawMetaCriticGameReviews
 FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 IGNORE 1 LINES
 (CriticName,Review,Game,Platform,Score,@TheDate)
 SET
  TheDate = STR_TO_DATE(@TheDate, "%M %d, %Y"); 

INSERT IGNORE INTO Games (GameName) SELECT DISTINCT Game FROM rawMetaCriticGameReviews;
  
INSERT INTO rawMetaCriticGameReviewsFixed (CriticName,Review,Game,Score)
  SELECT rawMetaCriticGameReviews.CriticName, rawMetaCriticGameReviews.Review, games.GameId, rawMetaCriticGameReviews.Score
  FROM rawMetaCriticGameReviews
INNER JOIN games
  ON games.GameName = rawMetaCriticGameReviews.Game;
  
DROP TABLE IF EXISTS rawSteamUserReviews;
DROP TABLE IF EXISTS rawidToUsername;
DROP TABLE IF EXISTS rawMetaCriticGame;
DROP TABLE IF EXISTS rawVgchartzGame;
DROP TABLE IF EXISTS rawMetaUserComments;
DROP TABLE IF EXISTS rawMetaUserCommentsFixed;
DROP TABLE IF EXISTS rawMetaCriticGameReviews;
DROP TABLE IF EXISTS rawMetaCriticGameReviewsFixed;