#What are the highest rated genres?
#Joined lookup tables for genres onto games, and from critic reviews onto reviews
#Reviews and games joined to cross-reference critic reviews and game genres
#Top 10 genres listed; grouped by their average critic score
#Descending order, with the top ranked genre displayed first.
USE GameRater;
SELECT Genre, Avg(Score) 'Average Critic Score' from games
LEFT JOIN GameIsGenre on GameIsGenre.GameIdFK = games.GameId
LEFT JOIN Genres on Genres.GenreId = GameIsGenre.GenreIdFk
LEFT JOIN Reviews on games.GameId = Reviews.GameIdFk
LEFT JOIN CriticReviews on Reviews.ReviewId = CriticReviews.ReviewIdFk
GROUP BY Genre
ORDER BY 2 Desc
LIMIT 10;
