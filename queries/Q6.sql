-- What game from the games table has the most entries in the gameonplatform table?
-- Group by GameName
-- Sorted by platform count
-- Limited to 10 entries

SELECT
	GameName, COUNT(gameonplatform.PlatformIdFk) as PlatformCount
FROM
	games
    	    INNER JOIN
	gameonplatform ON gameonplatform.GameIdFk = games.GameId
GROUP BY GameName
ORDER BY PlatformCount DESC
LIMIT 10;
