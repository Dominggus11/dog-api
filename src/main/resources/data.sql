CREATE TABLE breed (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE subbreed (
  id INT AUTO_INCREMENT PRIMARY KEY,
  breed_id INT,
  name VARCHAR(255),
  FOREIGN KEY (breed_id) REFERENCES breed(id)
);

CREATE TABLE image (
  id INT AUTO_INCREMENT PRIMARY KEY,
  subbreed_id INT,
  url VARCHAR(255),
  FOREIGN KEY (subbreed_id) REFERENCES subbreed(id)
);
