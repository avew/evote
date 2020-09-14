create table if not exists t_upload_image
(
    id                 varchar(37)  NOT NULL,
    category           varchar(100) not null,
    path               varchar(255) not null,
    filename           text         not null,
    content_type       varchar(100) not null,
    original_filename  text         not null,
    length             bigint       not null,
    md5                varchar(100) not null,
    created_by         varchar(50)  NOT NULL,
    created_date       timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_by   varchar(50)           DEFAULT NULL,
    last_modified_date timestamp    NULL     DEFAULT NULL,
    PRIMARY KEY (id)
);




