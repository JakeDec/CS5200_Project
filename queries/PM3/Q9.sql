#What is the average playtime for the most popular genres?
#Join genre lookup tables onto games, and whether a user owns a game onto the game table
#Group entries by the genre, and calculate their average score
#Show the top 20 genres by average playtime
Use GameRater;
SELECT Genre, Avg(Playtime)as 'Average Playtime' from games
LEFT JOIN GameIsGenre on GameIsGenre.GameIdFK = games.GameId
LEFT JOIN Genres on Genres.GenreId = GameIsGenre.GenreIdFk
INNER JOIN UserHasGame on Games.GameId = UserHasGame.GameIdFk
GROUP BY Genre
ORDER BY 2 DESC
LIMIT 20;
