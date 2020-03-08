-- Most highly rated games of all time by critics
-- Limit 20;

SELECT 
    GameName, AVG(criticreviews.Score) AS AverageScore
FROM
    reviews
        INNER JOIN
    criticreviews ON reviews.ReviewId = criticreviews.ReviewIdFk
        INNER JOIN
    games ON games.GameId = reviews.GameIdFk
GROUP BY GameName
ORDER BY AverageScore DESC
LIMIT 20;