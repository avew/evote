CREATE TABLE user
(
    id                 varchar(255) NOT NULL,
    login              varchar(50)  NOT NULL,
    password_hash      varchar(200)          DEFAULT NULL,
    first_name         varchar(50)           DEFAULT NULL,
    last_name          varchar(50)           DEFAULT NULL,
    email              varchar(100)          DEFAULT NULL,
    image_url          varchar(256)          DEFAULT NULL,
    activated          bit(1)       NOT NULL,
    lang_key           varchar(5)            DEFAULT NULL,
    activation_key     varchar(20)           DEFAULT NULL,
    reset_key          varchar(100)          DEFAULT NULL,
    created_by         varchar(50)  NOT NULL,
    created_date       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    reset_date         timestamp    NULL     DEFAULT NULL,
    last_modified_by   varchar(50)           DEFAULT NULL,
    last_modified_date timestamp    NULL     DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY idx_user_login (login),
    UNIQUE KEY idx_user_email (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO user (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key,
                  activation_key, reset_key, created_by, created_date, reset_date, last_modified_by,
                  last_modified_date)
VALUES ('7cf78059-c2e5-42c0-a48d-ce5b1c2495de', 'admin','{bcrypt}$2a$10$0gzzze2majBZnGpq4FH0ROCp8dHyY.AaxGNNHy1sR4j0r5osibNSu','Administrator', 'Administrator', 'admin@localhost', '', b'1', 'en', NULL, NULL, 'system','2018-07-11 13:44:35', NULL, 'system', NULL),
       ('ce7c9bbc-28a3-4ae0-8109-a2f7d5f0175b', 'user','{bcrypt}$2a$10$0gzzze2majBZnGpq4FH0ROCp8dHyY.AaxGNNHy1sR4j0r5osibNSu','User', 'User', 'user@localhost', '', b'1', 'en', NULL, '468b35c2-1d6b-4e87-a38f-17421eb3364f', 'system','2018-07-11 13:44:35', NULL, 'system', NULL),
       ('2923c684-d595-4353-9722-bb96da0ccd4a', 'reporter','{bcrypt}$2a$10$0gzzze2majBZnGpq4FH0ROCp8dHyY.AaxGNNHy1sR4j0r5osibNSu','Reporter', 'Reporter', 'reporter@localhost', '', b'1', 'en', NULL, '468b35c2-1d6b-4e87-a38f-17421eb3364f', 'system','2018-07-11 13:44:35', NULL, 'system', NULL),
       ('e819376e-e1a3-49ed-b562-ff5fa82d82b4', 'system','{bcrypt}$2a$10$0gzzze2majBZnGpq4FH0ROCp8dHyY.AaxGNNHy1sR4j0r5osibNSu', 'System', 'System','system@localhost','',b'1', 'en', NULL, NULL, 'system', '2018-07-11 13:44:35', NULL, 'system', NULL);
CREATE TABLE authority
(
    name varchar(50) NOT NULL,
    PRIMARY KEY (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO authority (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_REPORTER'),
       ('ROLE_USER');

CREATE TABLE user_authority
(
    user_id        varchar(255) NOT NULL,
    authority_name varchar(50)  NOT NULL,
    PRIMARY KEY (user_id, authority_name),
    KEY fk_authority_name (authority_name),
    CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES authority (name),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


INSERT INTO user_authority (user_id, authority_name)
VALUES ('7cf78059-c2e5-42c0-a48d-ce5b1c2495de', 'ROLE_ADMIN'),
       ('ce7c9bbc-28a3-4ae0-8109-a2f7d5f0175b', 'ROLE_USER'),
       ('2923c684-d595-4353-9722-bb96da0ccd4a', 'ROLE_REPORTER');

