# noinspection SpellCheckingInspectionForFile

CREATE TABLE IF NOT EXISTS t_voting
(
    id                 varchar(37) PRIMARY KEY NOT NULL,
    candidate_id       varchar(37),
    user_id            varchar(37),
    count              smallint(5) UNSIGNED    NOT NULL,
    created_by         varchar(50)             NOT NULL,
    created_date       timestamp               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_by   varchar(50)                      DEFAULT NULL,
    last_modified_date timestamp               NULL     DEFAULT NULL,
    CONSTRAINT fk_t_vote_user_id FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_t_vote_candidate_id FOREIGN KEY (candidate_id) REFERENCES t_candidate (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE UNIQUE INDEX idx_t_vote on t_voting (candidate_id, user_id) USING BTREE;




