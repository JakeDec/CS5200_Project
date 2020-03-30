SELECT * FROM
(SELECT platforms.PlatformName as Platform, games.ReleaseYear as CalendarYear, sum(GlobalSales) as GlobalSales FROM gamesales
INNER JOIN gameonplatform
on gamesales.GameIdFk=gameonplatform.GameIdFk
INNER JOIN platforms
on platforms.PlatformId=gameonplatform.PlatformIdFk
INNER JOIN games
on games.GameId=gamesales.GameIdFk
group by games.ReleaseYear, platforms.PlatformName) blah
where CalendarYear is not null and Platform in ('PC','XB','XONE','WII','3DS','PS3','PS4')