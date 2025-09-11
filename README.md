# 🚀 Пример веб-приложения на Spring Boot + Thymeleaf

Этот проект представляет собой простое CRUD (Create, Read, Update, Delete) веб-приложение для управления пользователями.
В качестве базы данных используется MySQL, приложение развёртывается на Spring Boot и шаблонизаторе Thymeleaf.

# ✨ Особенности

- Spring Boot: быстрое развертывание без ручной настройки сервера.

- Thymeleaf: удобный шаблонизатор для HTML-страниц.

- JPA + Hibernate: для работы с MySQL через ORM.

- Docker Compose: для запуска MySQL в контейнере.

- CRUD операции: создание, чтение и удаление пользователей.

# 🛠️ Технологии:

- Java 21

- Maven

- Spring Boot 3

- Spring Data JPA + Hibernate

- Thymeleaf

- MySQL 8.0

- Docker & Docker Compose

# 🏃 Быстрый старт

Для запуска проекта у вас должны быть установлены:

- Java Development Kit (JDK) 21

- Maven

- Docker и Docker Compose

# ⚙️ Сборка и запуск: 
# 1) Соберите проект: 

- mvn clean package
- mvn spring-boot:run


# 2) Запустите базу данных
- Используйте Docker Compose для запуска MySQL-контейнера:
- docker-compose up -d
- Это запустит MySQL-сервер на порту 3306 и создаст базу данных userExample.

# 3) Перейдите в браузере

- Откройте:
http://localhost:8081/users


Вы увидите страницу со списком пользователей и формой для добавления нового.

# 📂 Основные компоненты

# 1) Entity

- User — сущность с полями id, firstName, lastName, age.

# 2) Repository

- UserRepository — интерфейс для работы с БД (расширяет JpaRepository).

# 3) Service

- UserService — бизнес-логика для работы с пользователями.

# 4) Controller

- UserController — обрабатывает HTTP-запросы:

1) - GET /users — список пользователей

2) - POST /users — добавить пользователя

3) - POST /users/delete/{id} — удалить пользователя

# 5) Views (Thymeleaf)

- users.html:

