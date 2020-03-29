-- What publisher owns the game with the highest users owning that game?
-- Group by GameIdFk
-- Sorted by count of games
-- Limited to the top entry

SELECT PublisherName, COUNT(*) AS GameCount
FROM userhasgame
    INNER JOIN
	games ON games.GameId = userhasgame.GameIdFk
	INNER JOIN
	publishers ON publishers.PublisherId = games.PublisherIdFk
GROUP BY GameIdFk
ORDER BY GameCount DESC
LIMIT 1;
