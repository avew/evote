# noinspection SpellCheckingInspectionForFile

CREATE TABLE IF NOT EXISTS t_candidate
(
    id                 varchar(37) PRIMARY KEY NOT NULL,
    name               varchar(255)            NOT NULL,
    vision             text                    NOT NULL,
    mission            text                    NOT NULL,
    periode            varchar(100)            NOT NULL,
    image_url          varchar(100)                     DEFAULT NULL,
    created_by         varchar(50)             NOT NULL,
    created_date       timestamp               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_by   varchar(50)                      DEFAULT NULL,
    last_modified_date timestamp               NULL     DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX idx_t_candidate_name on t_candidate (name) USING BTREE;




