INSERT INTO BASE VALUES ( 'b8247b50-567b-4602-a83b-ca7ee5cba818', 'MPZ', 'Leningraskoe 22', true );
INSERT INTO GAS VALUES ( '18247b50-567b-4602-a83b-ca7ee5cba818', '92', true );
INSERT INTO ROLE VALUES ( '123e4567-e89b-12d3-a456-426655440000', 'ADMIN' );
INSERT INTO ROLE VALUES ( '223e4567-e89b-12d3-a456-426655440000', 'USER' );
INSERT INTO USERS VALUES ( '323e4567-e89b-12d3-a456-426655440000',
                          'Admin',
                          77777777,
                          'admin@neftlink.ru',
                          '$2a$08$WCAY6v2fN5AkjSHi9EGkIOs3C9tQze.L/rwPHht/g2rE/ZQxLUFyq',
                          'NEFTLINK');
INSERT INTO USERS_ROLES VALUES ( '323e4567-e89b-12d3-a456-426655440000',
                                '123e4567-e89b-12d3-a456-426655440000');
INSERT INTO USERS_ROLES VALUES ( 'f671ef18-59e2-4dba-9394-9bf55c137366',
                                '223e4567-e89b-12d3-a456-426655440000');
INSERT INTO ORDERS VALUES ( 'fabda3ac-540e-4945-876d-8e1cac17cf75',
                            55500,
                            '2022-07-19 12:47:33.176121',
                            '2022-07-19 12:47:33.177193',
                            '18247b50-567b-4602-a83b-ca7ee5cba818',
                            'b8247b50-567b-4602-a83b-ca7ee5cba818',
                            '323e4567-e89b-12d3-a456-426655440000',
                            true);
