CREATE SCHEMA IF NOT EXISTS GameRater;
USE GameRater;

DROP TABLE IF EXISTS GameIsGenre;
DROP TABLE IF EXISTS UserHasGame;
DROP TABLE IF EXISTS UserReviews;
DROP TABLE IF EXISTS Games;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Platforms;
DROP TABLE IF EXISTS Publishers;
DROP TABLE IF EXISTS Genres;

DROP TABLE IF EXISTS rawSteamUserReviews;
DROP TABLE IF EXISTS rawidToUsername;
DROP TABLE IF EXISTS rawMetaCriticGame;
DROP TABLE IF EXISTS rawVgchartzGame;

CREATE TABLE Platforms (
  PlatformId INT NOT NULL UNIQUE AUTO_INCREMENT,
  PlatformName VARCHAR(50) NOT NULL UNIQUE,
  CONSTRAINT PlatformPk
    PRIMARY KEY (PlatformId)
) ENGINE = InnoDB;

CREATE TABLE Publishers (
  PublisherId INT NOT NULL UNIQUE AUTO_INCREMENT,
  PublisherName VARCHAR(255) NOT NULL UNIQUE,
  CONSTRAINT PublisherPk
    PRIMARY KEY (PublisherId)
) ENGINE = InnoDB;

CREATE TABLE Genres (
  GenreId INT NOT NULL UNIQUE AUTO_INCREMENT,
  Genre VARCHAR(255) UNIQUE,
  CONSTRAINT GenresPk
    PRIMARY KEY (GenreId)
) ENGINE = InnoDB;

CREATE TABLE Users (
  UserId INT NOT NULL UNIQUE AUTO_INCREMENT,
  SteamId INT NOT NULL UNIQUE,
  UserName TEXT DEFAULT NULL,
  CONSTRAINT UserPk
    PRIMARY KEY (UserId)
) ENGINE = InnoDB;

CREATE TABLE Games (
  GameId INT NOT NULL UNIQUE AUTO_INCREMENT,
  GameName VARCHAR(255) NOT NULL UNIQUE,
  PlatformIdFk INT,
  PublisherIdFk INT,
  ReleaseYear INT,
  Genre VARCHAR(50),
  CONSTRAINT GamesPk
    PRIMARY KEY (GameId),
  CONSTRAINT GamesPlatformsFk
    FOREIGN KEY (PlatformIdFk)
    REFERENCES Platforms (PlatformId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT GamesPublishersFk
    FOREIGN KEY (PublisherIdFk)
    REFERENCES Publishers (PublisherId)
    ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE = InnoDB;

CREATE TABLE UserReviews (
  UserIdFk INT NOT NULL,
  GameIdFK INT NOT NULL,
  Review TEXT,
  CONSTRAINT UserReviewsUsersFK
    FOREIGN KEY (UserIdFk)
    REFERENCES Users (UserId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT UserReviewsGamesrFK
    FOREIGN KEY (GameIdFk)
    REFERENCES Games (GameId)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE UserHasGame (
  UserIdFk INT NOT NULL,
  GameIdFk INT NOT NULL,
  PlayTime FLOAT,
--   CONSTRAINT UserHasGamePk
--     UNIQUE (UserIdFk, GameIdFk),
  CONSTRAINT UserHasGameUsersFk
    FOREIGN KEY (UserIdFk)
    REFERENCES Users (UserId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT UserHasGameGamesFk
    FOREIGN KEY (GameIdFk)
    REFERENCES Games (GameId)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE GameIsGenre (
  GameIdFk INT NOT NULL UNIQUE,
  GenreIdFk INT,
  CONSTRAINT GameIsGenreGamesFk
    FOREIGN KEY (GameIdFk)
    REFERENCES Games (GameId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT GameIsGenreGenresFk
    FOREIGN KEY (GenreIdFk)
    REFERENCES Genres (GenreId)
    ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE rawSteamUserReviews (
  UserId INT,
  GameName TEXT,
  PurchasedOrPlayed TEXT,
  PlayTime DECIMAL,
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

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/steamUserReviews.csv' INTO TABLE rawSteamUserReviews
 FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
 LINES TERMINATED BY '\n';
#INSERT INTO Users (SteamId) SELECT DISTINCT UserId FROM rawSteamUserReviews;
INSERT INTO Games (GameName) SELECT DISTINCT GameName FROM rawSteamUserReviews;
INSERT INTO UserHasGame (UserIdFk, GameIdFk, PlayTime) SELECT (SELECT UserId FROM Users WHERE SteamId = raw.UserId), (SELECT GameId FROM Games WHERE GameName = raw.GameName), PlayTime FROM rawSteamUserReviews raw WHERE PurchasedOrPlayed = 'played';

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/idToUsername.csv' INTO TABLE rawidToUsername
 FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 (UserName, UserId); 
INSERT INTO Users (SteamId, Username) SELECT DISTINCT UserId,UserName FROM rawidToUsername;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/metacriticGameInfo.csv' INTO TABLE rawMetaCriticGame
 FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 IGNORE 1 LINES
 (@var,Title,@TheYear,Publisher,Genre,Platform,@Metascore,@score,Players)
 SET
   AvgUserscore = if(@score in ('tbd', 'not specified'), NULL, @score),
   TheYear = if(@TheYear in ('tbd', 'not specified'), NULL, @TheYear),
   MetaScore = if(@Metascore in ('tbd', 'not specified'), NULL, @Metascore);
   

INSERT IGNORE INTO Publishers (PublisherName) SELECT DISTINCT Publisher FROM rawMetaCriticGame;
INSERT IGNORE INTO Platforms (PlatformName) SELECT DISTINCT Platform FROM rawMetaCriticGame;
   
   
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
    
INSERT IGNORE INTO Publishers (PublisherName) SELECT DISTINCT Publisher FROM rawVgchartzGame;
INSERT IGNORE INTO Platforms (PlatformName) SELECT DISTINCT Platform FROM rawVgchartzGame;
INSERT IGNORE INTO Genres (Genre) SELECT DISTINCT Genre FROM rawVgchartzGame;

INSERT IGNORE INTO gameisgenre (GameIdFk, GenreIdFk)  (SELECT games.GameId, genres.GenreId
FROM games
INNER JOIN rawvgchartzgame 
  ON games.GameName = rawvgchartzgame.TheName
INNER JOIN genres 
  ON rawvgchartzgame.Genre = genres.Genre)
