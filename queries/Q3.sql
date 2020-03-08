-- What are the most prolific genre types?

SELECT 
    genres.Genre, COUNT(genres.Genre) AS NumberGames
FROM
    gameisgenre
        INNER JOIN
    genres ON genres.GenreId = gameisgenre.GenreIdFk
GROUP BY genres.Genre
ORDER BY NumberGames DESC
LIMIT 20;