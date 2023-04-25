## Boardgame information retrieval service from MongoDB database

## Before running:

- Please set env variables for MongoDB

## Sample MongoDB Document:

- {
  \_id: ObjectId("643e2c8f8fdefbbf43a10b4d"),
  gid: 182028,
  name: 'Through the Ages: A New Story of Civilization',
  year: 2015,
  ranking: 3,
  users_rated: 15551,
  url: 'https://www.boardgamegeek.com/boardgame/182028/through-ages-new-story-civilization',
  image: 'https://cf.geekdo-images.com/micro/img/APvZ_BYOt4ElpIXVl7i6wUp2BvM=/fit-in/64x64/pic2663291.jpg'
  }

## /games

- Retrieve all games from database (Content-Type: application/json)
- Optional query parameters: offset (default = "0") and limit (default ="25")

## /games/rank

- Find all games from database sorted in ascending order of "ranking" field (Content-Type: application/json)
- Optional query parameters: offset (default = "0") and limit (default ="25")

## /game/{ObjectId}

- Find game by ObjectId (i.e. \_id field in MongoDB)
