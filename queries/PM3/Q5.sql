-- What game from a genre has highest average critic + user score?
-- We want the average of both the critic or user score, even if a game doesn’t have both types of scores.
-- Left join from the review table to get critic reviews, user reviews, and game genre.
-- Group by GameName
-- Limited to 3 entries
-- Sorted by combined score

SELECT
	GameName, genres.Genre,
	(AVG(IFNULL
(criticreviews.Score, IFNULL(userreviews.Score * 10, 0))) 
+
    	(AVG(IFNULL
(userreviews.Score * 10, IFNULL(criticreviews.Score, 0)))))
	/ 2 AS ComboScore
FROM
	reviews
    	LEFT JOIN criticreviews 
ON reviews.ReviewId = criticreviews.ReviewIdFk
    	LEFT JOIN games 
ON games.GameId = reviews.GameIdFk
    	LEFT JOIN userreviews 
ON reviews.ReviewId = userreviews.ReviewIdFk
    	LEFT JOIN gameisgenre 
ON games.GameId = gameisgenre.GameIdFk
    	LEFT JOIN genres 
ON genres.GenreId = gameisgenre.GenreIdFk
GROUP BY GameName
HAVING genres.Genre = "Role-Playing"
ORDER BY ComboScore DESC
LIMIT 10;
