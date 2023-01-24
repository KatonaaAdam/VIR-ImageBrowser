INSERT INTO users (username, password,password_salt)VALUES ('adam','$shiro1$SHA-256$500000$YOJ+d7gjEZaUxiFHDOwcCw==$i4J6wHPzR0EZE941M75sLhOQTmhigA2O4LAn+OuI5v0=','');
INSERT INTO users (username, password,password_salt)VALUES ('peter','$shiro1$SHA-256$500000$XquTB0sLdgR5Wbc6IH92NA==$Wnw5kX865n/KTnyTP9TrE69MXr78bvVLHnRrdQiYjn8=','');
INSERT INTO users (username, password,password_salt)VALUES ('jani','$shiro1$SHA-256$500000$X/RlVfiL+i1/dPSIx5yNiA==$3OiPrUG5XOIcf/J9qiOatD/sH8rgbkQFkl4li7WEd+U=','');
INSERT INTO users (username, password,password_salt)VALUES ('admin','$shiro1$SHA-256$500000$u5PFkjGKiKD9O6Df1gcUhQ==$V4WUgOJ7jH6FBjRI63JHBWxYCKm5nndoW4UW2zJnGfE=','');

INSERT INTO user_roles (username, role_name)VALUES ('admin','admin');
INSERT INTO user_roles (username, role_name)VALUES ('adam','basic_1');
INSERT INTO user_roles (username, role_name)VALUES ('peter','basic_4');
INSERT INTO user_roles (username, role_name)VALUES ('jani','basic_6');


INSERT INTO roles_permissions (permission, role_name)VALUES ('all','admin');
INSERT INTO roles_permissions (permission, role_name)VALUES ('png','basic_1');
INSERT INTO roles_permissions (permission, role_name)VALUES ('jpg','basic_2');
INSERT INTO roles_permissions (permission, role_name)VALUES ('gif','basic_3');
INSERT INTO roles_permissions (permission, role_name)VALUES ('png-jpg','basic_4');
INSERT INTO roles_permissions (permission, role_name)VALUES ('png-gif','basic_5');
INSERT INTO roles_permissions (permission, role_name)VALUES ('jpg-gif','basic_6');

SELECT * FROM roles_permissions;