CREATE TABLE `accounts` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `number` int(11) NOT NULL,
    `balance` decimal(13,2) NOT NULL DEFAULT '0.00',
    PRIMARY KEY (`id`),
    UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;