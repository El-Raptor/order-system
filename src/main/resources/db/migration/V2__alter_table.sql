-- Add Unique constraint to users' email
ALTER TABLE users
ADD CONSTRAINT uc_users_email UNIQUE (email);

-- Alter role type and set it as not nullable
ALTER TABLE users
ALTER COLUMN role TYPE varchar(8),
ALTER COLUMN role SET NOT NULL;

-- Add check constrain on role value.
ALTER TABLE users
ADD CONSTRAINT chk_role_valid CHECK (role IN ('admin', 'customer'));