# JavaRUSH_Internship

Тестовое задание сделал на основе: 
https://spring.io/guides/gs/crud-with-vaadin/

Пейджинг сделал но работает на костылях. А именно, пришлость ввести кнопку "Refresh Table", 
т.к. автоматическое обновление перестало работать.

Поиск работает по тому-же принципу, по той-же причине, 
если выпилить «кривой» пейджинг, то все работает нормально.

Базу заполняю в Application.java

Использую инициализацию базы данных через 
spring.jpa.hibernate.ddl-auto=create-drop
в файле applicatoin.properties

FrontEnd — Vaadin

Для сборки и запуска проекта в IntelliJ IDEA
использую команду для Maven clean package spring-boot:run

Всё что мог... расчитываю на благосклонность проверяющих:)
