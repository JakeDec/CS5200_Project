-- What games have the longest average playtime?
-- Filtered to games that are owned by at least 1% of our sampled user base
-- Limited to 20 entries
-- Sorted by playtime

SELECT 
    games.GameName,
    AVG(filtered.PlayTime) AS AveragePlayTime,
    COUNT(filtered.PlayTime) AS Users,
    COUNT(filtered.PlayTime) / MAX(filtered.UserIdFk) * 100 AS PercentageOwnerShip
FROM
    (SELECT * FROM userhasgame
    WHERE PlayTime > 0) AS filtered
        INNER JOIN
    games ON filtered.GameIdFk = games.GameId
GROUP BY GameIdFk
HAVING COUNT(filtered.playtime) > MAX(filtered.UserIdFk) *.01
ORDER BY AveragePlayTime DESC
LIMIT 20;