
CREATE TABLE ym_ratings (
	uid int,
	aid int,
	rating smallint,
	primary key (uid,aid)
	);

CREATE TABLE ym_artist (
	aid int primary key,
	name varchar,
	num_ratings int,
	avg_rating float(4)
	);

CREATE TABLE ym_user (
	uid int primary key,
	num_ratings int,
	avg_rating float(4)
	);
