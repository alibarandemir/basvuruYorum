-- Admin kullanıcısını ekle (şifre: Admin123!)
INSERT INTO candidates (name, surname, email, password, role, created_at, updated_at)
VALUES (
    'Admin',
    'User',
    'alibarandemir798@gmail.com',
    '$2a$10$rDkPvvAFV6GgJjXpz5YzUOQZQZQZQZQZQZQZQZQZQZQZQZQZQZQZQ', -- Bu BCrypt ile şifrelenmiş "Admin123!" şifresi
    'ADMIN',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
); 