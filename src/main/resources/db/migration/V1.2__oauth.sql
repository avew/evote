CREATE TABLE oauth_client_details (
  client_id varchar(255) NOT NULL,
  resource_ids varchar(255) DEFAULT NULL,
  client_secret varchar(255) DEFAULT NULL,
  scope varchar(255) DEFAULT NULL,
  authorized_grant_types varchar(255) DEFAULT NULL,
  web_server_redirect_uri varchar(255) DEFAULT NULL,
  authorities varchar(255) DEFAULT NULL,
  access_token_validity int(11) DEFAULT NULL,
  refresh_token_validity int(11) DEFAULT NULL,
  additional_information varchar(4000) DEFAULT NULL,
  autoapprove varchar(4000) DEFAULT NULL,
  PRIMARY KEY (client_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
	('abbeb89a-2c00-4c17-b276-008800ed2be7','eVote','43d42ac1-975a-4a88-b3d4-175f01d9a633','read,write','password,refresh_token,authorization_code,implicit','','ROLE_ADMIN,ROLE_USER',86400,87000,'{}','true');


CREATE TABLE oauth_client_token (
  token_id varchar(255) DEFAULT NULL,
  token blob,
  authentication_id varchar(255) DEFAULT NULL,
  user_name varchar(50) DEFAULT NULL,
  client_id varchar(255) DEFAULT NULL,
  KEY fk_oauth_client_token_username (user_name),
  CONSTRAINT fk_oauth_client_token_username FOREIGN KEY (user_name) REFERENCES user (login)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE oauth_access_token (
  token_id varchar(255) DEFAULT NULL,
  token blob,
  authentication_id varchar(255) NOT NULL,
  user_name varchar(50) DEFAULT NULL,
  client_id varchar(255) DEFAULT NULL,
  authentication blob,
  refresh_token varchar(255) DEFAULT NULL,
  PRIMARY KEY (authentication_id),
  KEY fk_oauth_access_token_username (user_name),
  CONSTRAINT fk_oauth_access_token_username FOREIGN KEY (user_name) REFERENCES user (login)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE oauth_refresh_token (
  token_id varchar(255) DEFAULT NULL,
  token blob,
  authentication blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE oauth_code (
  code varchar(255) DEFAULT NULL,
  authentication blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE oauth_approvals (
  userId varchar(255) DEFAULT NULL,
  clientId varchar(255) DEFAULT NULL,
  scope varchar(255) DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  expiresAt timestamp NULL DEFAULT NULL,
  lastModifiedAt timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;