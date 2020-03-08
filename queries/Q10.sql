#What Games Are Rated Most Highly By Steam Users?
#join user reviews onto the user, then cross-reference whether that particular owns a game
#group the game names by average user ratings
#only show the top 20 results and where the steamid is not null
#if in the data set the steam id was null, this would indicate the user was not a steam user.

Use GameRater;
SELECT GameName, Avg(Score) as 'Average User Rating' from games
INNER JOIN UserHasGame on games.GameId = UserHasGame.GameIdFk
INNER JOIN Users on UserHasGame.UserIdFK = Users.UserId
INNER JOIN Reviews on games.GameId = Reviews.GameIdFk
INNER JOIN UserReviews on Users.UserID = UserReviews.UserIdFk
WHERE SteamID IS NOT NULL
GROUP BY GameName
ORDER BY 2 DESC
LIMIT 20;
