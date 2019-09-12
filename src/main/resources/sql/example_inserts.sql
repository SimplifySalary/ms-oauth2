INSERT INTO user_management.oauth_client_details (client_id,
client_secret,
scope,
authorized_grant_types,
authorities,
access_token_validity,
web_server_redirect_uri,
resource_ids)
  VALUES ('clientId1',
'{bcrypt}$2a$10$vCXMWCn7fDZWOcLnIEhmK.74dvK1Eh8ae2WrWlhr2ETPLoxQctN4.',
'read,write,trust,api',
'password,refresh_token,implicit,client_credentials,authorization_code',
'ROLE_CLIENT',
300,
'http://example.com',
'resource');

INSERT INTO user_management.user (username,
password,
date_created)
VALUES ('user',
'{bcrypt}$2a$10$cyf5NfobcruKQ8XGjUJkEegr9ZWFqaea6vjpXWEaSqTa2xL9wjgQC',
'2019-06-15 22:14:54');

INSERT INTO user_management.authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO user_management.authority (name) VALUES ('ROLE_USER');

INSERT INTO user_management.user_authority (user_id, authority_id) VALUES ( 1, 1);
INSERT INTO user_management.user_authority (user_id, authority_id) VALUES ( 1, 2);




  