SELECT blah.Year as Year, avg(CriticReviews.Score) as AverageScore, blah.GDP as GDPChange FROM CriticReviews
INNER JOIN Reviews
ON CriticReviews.ReviewIdFk = Reviews.ReviewId
INNER JOIN Games
ON Reviews.GameIdFk = Games.GameId
INNER JOIN (SELECT * FROM RegionGDP
WHERE Region = "World") blah
ON blah.Year = Games.ReleaseYear
Group By Games.ReleaseYear
Order by Year desc;