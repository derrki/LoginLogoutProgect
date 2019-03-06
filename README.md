# LoginLogoutProgect
Проект згадно тестового завдання 
"
Створити додаток реєстрації і перегляду списку користувачів. 
Технології Java, MySQL, angular, bootstrap.
Користувачі можуть мати дві ролі - user або admin.
Адміністратор може створювати, перегладати, видаляти, редагувати користувачів. User може переглядати і редагувати лише свої дані.
Поля реєстрації: Імя, Прізвище, Логін, Пароль, Країна. Список країн задати окремою таблицею.
Передбачити можливість завести першого користувача як адміністратора.
Валідація введених даних на фронтенді і бекенді.
Можна використовувати будь які бібліотеки для java і angular.
До файлів проекту додати опис як розгорнути і запустити додаток.
Файли проекту розмістіть в публічному репозиторії (github, bitbucket).
(використання angular не є обов'язковим, але буде nice to have)
"

Що зроблено:
-Веб додаток дозволяє реєструвати користувачів да допомогою форми реєстрації.
-Перший зареєстрований користувач вважається адміністратором та одержує право перегляду даних про всіх користувачів.
-Перпеглад даних можливий після вводу коректного логіну та паролю в формі входу.
-Дані зберігаються в двох таблицях

-виконано просту валідацію введених даних в форму на стороні браузера та стороні сервера.

Що не зроблено з запланованого:
-Не реалізована можливість редагування даних
-Не проведено рефакторингу коду
-Не використано MVC для розподілу компонентів проекту на ролі
-Не додано дизайну для гарного вигляду проекту в фронті
причина - неграмотне розподілення часу наданого на виконання проекту!!!

Щоб запустити на виконання проект, потрібно склонувати проект. Налаштувати Tomcat.
Створити базу даних users_db логін - root, пароль - root
Додати дві таблиці: 
CREATE TABLE `users_db`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `users_db`.`user_country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
  Запустити проект на виконання. Насолоджуватись користуванням!
