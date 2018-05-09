#!/bin/bash

# Edit the line below to reflect your own path
REPODIR = /home/btq/GitHub/Recommender_Yahoo_Music
cd $REPODIR

createdb ymusic_data

psql ymusic_data -f scripts/create_ymusic_schema.sql

# wc -l ydata-ymusic-artist-names-v1_0.txt  = 97956
tail -n 97954 data/ydata-ymusic-artist-names-v1_0.txt > data/ydata-ymusic-artist-names-v2_0.txt
#iconv -f ISO-8859-1 -t UTF-8 ydata-ymusic-artist-names-v2_0.txt > ydata-ymusic-artist-names-v2_0.txt

psql ymusic_data -f scripts/import_ymusic_data.sql
