-- What games were perchased but have little to no playtime.
-- Less than 2 hours of playtime
-- Limited to 20 entries
-- Sorted by number of users who own but havn't played the game

SELECT 
    games.GameName,
    AVG(filtered.PlayTime) AS AveragePlayTime,
    COUNT(filtered.PlayTime) AS Users
FROM
    (SELECT 
        *
    FROM
        userhasgame
    WHERE
        PlayTime <= 2) AS filtered
        INNER JOIN
    games ON filtered.GameIdFk = games.GameId
GROUP BY GameIdFk
ORDER BY Users DESC
LIMIT 20;