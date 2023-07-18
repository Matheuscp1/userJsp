CREATE DATABASE UserJsp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE TABLE
  `users` (
    `id` int NOT NULL AUTO_INCREMENT,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `user_name` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `cpf` varchar(255) DEFAULT NULL,
    `status` tinyint(1) DEFAULT NULL,
    `supervisor_id` int DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`),
    KEY `users_relation_1` (`supervisor_id`),
    CONSTRAINT `users_relation_1` FOREIGN KEY (`supervisor_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
  ) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci


insert into
  `users` (
    `cpf`,
    `created_at`,
    `email`,
    `name`,
    `password`,
    `status`,
    `supervisor_id`,
    `user_name`
  )
values
  ('', DEFAULT,'admin@gmail.com','admin','123','1',
    DEFAULT, 'admin'
  );

  CREATE TABLE
  `permissions` (
    `id` int NOT NULL AUTO_INCREMENT,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci


insert into
  `permissions` (`created_at`, `name`)
values
  (DEFAULT, 'Acesso ao sistema');

  insert into
  `permissions` (`created_at`, `name`)
values
  (DEFAULT, 'Administrador');


  CREATE TABLE
  `user_permissions` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `user_id` int DEFAULT NULL,
    `permission_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_user_id_user_permission` (`user_id`, `permission_id`),
    KEY `user_permissions_relation_2` (`permission_id`),
    CONSTRAINT `user_permissions_relation_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
    CONSTRAINT `user_permissions_relation_2` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`) ON DELETE CASCADE
  ) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci

  insert into
  `user_permissions` (`created_at`, `permission_id`, `user_id`)
values
  (DEFAULT, '1', '1');

  insert into
  `user_permissions` (`created_at`, `permission_id`, `user_id`)
values
  (DEFAULT, '2', '1');