CREATE TABLE user (
  user_id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255),
  balance DECIMAL(10, 2)
);

CREATE TABLE wallet_transaction (
  transaction_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  transaction_type VARCHAR(255),
  amount DECIMAL(10, 2),
  transaction_time TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);
