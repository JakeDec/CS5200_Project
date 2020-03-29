SELECT blah.Year as Year, avg(UserReviews.Score) as AverageScore, blah.GDP as GDPChange 
FROM UserReviews
INNER JOIN Reviews
ON UserReviews.ReviewIdFk = Reviews.ReviewId
INNER JOIN Games
ON Reviews.GameIdFk = Games.GameId
INNER JOIN (SELECT * FROM RegionGDP
WHERE Region = "World") blah
ON blah.Year = Games.ReleaseYear
Group By Games.ReleaseYear
Order by Year desc;