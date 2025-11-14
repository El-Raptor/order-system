ALTER TABLE users
DROP CONSTRAINT chk_role_valid;

ALTER TABLE users
ADD CONSTRAINT chk_role_valid CHECK (role IN ('ADMIN', 'CUSTOMER'));