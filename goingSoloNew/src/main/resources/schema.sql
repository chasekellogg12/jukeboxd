CREATE TABLE IF NOT EXISTS users (
  username VARCHAR PRIMARY KEY,
  password VARCHAR NOT NULL,
  enabled BOOLEAN NOT NULL,
  name VARCHAR NOT NULL,
  email VARCHAR UNIQUE NOT NULL,
  avatar VARCHAR,
  rating INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
  username VARCHAR(50),
  authority VARCHAR(50),
  FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX IF NOT EXISTS ix_auth_username ON authorities (username, authority);

CREATE TABLE IF NOT EXISTS tracks (
  track_id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255),
  artist VARCHAR(255),
  album VARCHAR(255),
  year VARCHAR(4),
  album_cover_art VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS posts (
  post_id BIGSERIAL PRIMARY KEY,
  author_username VARCHAR(255) REFERENCES users(username),
  text TEXT,
  date_posted TIMESTAMP,
  track_id VARCHAR(255) REFERENCES tracks(track_id)
);

CREATE TABLE IF NOT EXISTS my_likes (
  like_id BIGSERIAL PRIMARY KEY,
  user_username VARCHAR(255) REFERENCES users(username),
  post_id BIGINT REFERENCES posts(post_id)
);

CREATE TABLE IF NOT EXISTS followers (
  follower_username VARCHAR(255),
  following_username VARCHAR(255),
  PRIMARY KEY (follower_username, following_username),
  FOREIGN KEY (follower_username) REFERENCES users(username),
  FOREIGN KEY (following_username) REFERENCES users(username)
);

CREATE TABLE IF NOT EXISTS user_top_tracks (
  user_username VARCHAR(255) REFERENCES users(username),
  track_id VARCHAR(255),
  top_four_order integer NOT NULL,
  name VARCHAR(255),
  album_cover_art VARCHAR(255),
  artist VARCHAR(255),
  year VARCHAR(4),
  PRIMARY KEY (top_four_order, user_username)
);

CREATE TABLE IF NOT EXISTS user_recent_activities (
  user_username VARCHAR(255) REFERENCES users(username),
  user_who_acted VARCHAR(255),
  activity_string VARCHAR(255),
  thing_acted_upon VARCHAR(255),
  time_created TIMESTAMP,
  PRIMARY KEY (user_username, time_created)
);