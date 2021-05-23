INSERT INTO users (login, password, email, enabled) VALUES ('admin', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', 'admin@admin.admin', true);
INSERT INTO users (login, password, email, enabled) VALUES ('guest', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', 'guest@guest.guest', true);
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_GUEST');
INSERT INTO users_roles (user_id, role_id)
SELECT (SELECT id FROM users WHERE login = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM users WHERE login = 'guest'), (SELECT id FROM roles WHERE name = 'ROLE_GUEST');
COMMIT ;
