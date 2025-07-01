-- data.sql

-- Insert a single user
INSERT INTO users (email, pass_hash)
VALUES
    ('your.email@example.com', 'MyS3cretPass!');

-- Assuming this user gets id = 1, insert 10 dummy transactions
INSERT INTO transactions (user_id, type, amount, description, created_at) VALUES
                                                                              (1, 'income', 3000.00, 'Salary for March 2024',    '2024-04-01 08:30:00'),
                                                                              (1, 'expense', 45.50,  'Grocery Shopping',          '2024-04-03 12:15:00'),
                                                                              (1, 'expense', 120.00, 'Electricity Bill',          '2024-04-05 18:47:00'),
                                                                              (1, 'expense', 15.75,  'Coffee and Snacks',         '2024-04-07 09:00:00'),
                                                                              (1, 'income', 150.00,  'Freelance Project Payment', '2024-04-10 14:30:00'),
                                                                              (1, 'expense', 60.00,  'Gas Refill',                '2024-04-12 19:00:00'),
                                                                              (1, 'income', 75.00,   'Sold Used Books',           '2024-04-14 11:30:00'),
                                                                              (1, 'income', 20.00,   'Referral Bonus',            '2024-04-18 07:20:00'),
                                                                              (1, 'income', 250.00,  'Stock Dividend',            '2024-04-20 13:00:00'),
                                                                              (1, 'expense', 89.99,  'New Shoes',                 '2024-04-22 22:10:00');
