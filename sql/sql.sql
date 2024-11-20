CREATE schema qaqchat;

use qaqchat;

-- 创建用户表
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,       -- 用户ID，主键
    username VARCHAR(255) NOT NULL UNIQUE,      -- 用户名，唯一
    email VARCHAR(255) UNIQUE,                  -- 邮箱，唯一
    password VARCHAR(255) NOT NULL,             -- 密码
    nickname VARCHAR(255),                      -- 昵称
    avatar VARCHAR(255),                        -- 头像URL
    motto VARCHAR(255),                         -- 个性签名
    regTime DATETIME                            -- 使用 DATETIME 存储注册时间，默认当前时间
);

-- 插入测试数据
INSERT INTO users (username, email, password, nickname, avatar)
VALUES
    ('john', 'john.doe@example.com', '123456', 'John', 'https://example.com/avatar1.jpg'),
    ('jane_doe', 'jane.doe@example.com', 'hashed_password_2', 'Jane', 'https://example.com/avatar2.jpg'),
    ('alice_smith', 'alice.smith@example.com', 'hashed_password_3', 'Alice', 'https://example.com/avatar3.jpg'),
    ('bob_jones', 'bob.jones@example.com', 'hashed_password_4', 'Bob', 'https://example.com/avatar4.jpg');

SELECT * FROM users;