\COPY ym_ratings FROM ../data/ydata-ymusic-user-artist-ratings-v1_0_2002.txt ;
\COPY ym_artist (aid,name) FROM ../data/ydata-ymusic-artist-names-v2_0.txt ;

CREATE INDEX ym_ratings_uid_idx ON ym_ratings (uid) ;
CREATE INDEX ym_ratings_aid_idx ON ym_ratings (aid) ;

INSERT INTO ym_user (uid,num_ratings,avg_rating) 
	SELECT uid,
		   count(rating) AS num_ratings,
		   round(avg(rating),4) AS avg_rating
		FROM ym_ratings
		GROUP BY uid ;

UPDATE ym_artist 
	SET num_ratings = sq.nr,
	avg_rating = sq.ar 
	FROM (
		SELECT aid,
		cast(count(rating) AS int) AS nr,
		round(avg(rating),4) AS ar
		FROM ym_ratings GROUP BY aid) AS sq
	WHERE ym_artist.aid = sq.aid ;

DELETE FROM ym_artist WHERE num_ratings IS NULL;

CREATE INDEX ym_user_uid_idx ON ym_user (uid) ;
CREATE INDEX ym_artist_aid_idx ON ym_artist (aid) ;
