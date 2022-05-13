# hibernate

Учебный проект для LearnUp.
Суть в том, чтобы сделать сервис покупки книг в магазине.
У нас есть авторы, книги, склады с книгами, а также клиенты и заказы.
Проект пишется с помощью spring boot, JPA, Rest, базы данных на heroku и Posgtgresql.
Есть entity слой, который является основой для бд. Слои репозиториев и сервисов нужны для обработки запросов и обращения с бд.
Также готов слой сонтроллеров для обработки post, get, put и delete запросов (запросы отправляю через  postman).
Есть небольшое представление на фронте (буквально пара страничек со списком книг и авторов), с него пока ничего нельзя отправить.
Добавил базы для юзеров и ролей, а также аутентификацию (для теста можно использовать Andrew - 12345 (Admin) и Ivan - 098765 (User).
Admin может добавлять и удалять книги и авторов, User может изменять свои заказы и имеет доступ к просмотру авторов и книг.

Буду обновлять описание по ходу проекта...
