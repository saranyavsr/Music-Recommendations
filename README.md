# YAHOO-Music-Recommendations (On-going)
Yahoo Music Recommendation system based on several user ratings for albums and provide song recommendations to the users.

## Dataset

* Dataset name - Yahoo! Music user ratings of musical tracks, albums, artists and genres
* Link  -  https://webscope.sandbox.yahoo.com/catalog.php?datatype=c
* Size  - 1.5 GB

### Dataset Description

Yahoo! Music offers a wealth of information and services related to many aspects of music. This dataset represents a snapshot of the Yahoo! Music community's preferences for various musical items. A distinctive feature of this dataset is that user ratings are given to entities of four different types: tracks, albums, artists, and genres. In addition, the items are tied together within a hierarchy. That is, for a track we know the identity of its album, performing artist and associated genres. Similarly we have artist and genre annotation for the albums. The dataset contains ratings provided by true Yahoo Music customers during 1999-2009. Both users and items (tracks, albums, artists and genres) are represented as meaningless anonymous numbers.

### Project Description

On Yahoo Music Dataset - Artists, Albums, Songs, Genres
* Track 1: predict user rating
* Track 2: decide whether a user rates a song or not

![alt text](https://github.com/saranyavsr/YAHOO-Music-Recommendations/blob/master/Images/Data%20Links.png)


#### Track 1

* Typical collaboration filtering task.
* Predict the rating of a specific user for unrated songs.
* Includes hierarchy of items
  - User might have rated other songs of same album/artist.
  - No user might have rated the song but the same album/artist.
* Includes time stamp of rating
  - Rating behavior might have changed over time. 
  - Older songs rated differently than newer songs?
  
![alt text](https://github.com/saranyavsr/YAHOO-Music-Recommendations/blob/master/Images/Track1.png)


#### Track 2

* Predict if a user would rate a given song highly or not at all. 
* Need a model for rate behavior.
* Ratings on songs only.
* No timestamps.
* Given six songs, which three will most likely not be rated.

![alt text](https://github.com/saranyavsr/YAHOO-Music-Recommendations/blob/master/Images/Track2.png)

