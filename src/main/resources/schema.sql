CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    joinDate DATE NOT NULL
);

CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT REFERENCES users(id),
    content TEXT NOT NULL,
    postDate DATE NOT NULL
);

CREATE TABLE likes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT REFERENCES users(id),
    postId INT REFERENCES posts(id)
);