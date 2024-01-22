# [Разработка для диплома к курсу TopJava](http://javaops.ru/view/bootjava?ref=gh)
-------------------------------------------------------------

- Stack: [JDK 21](http://jdk.java.net/17/), Spring Boot 3.2.X, Lombok, H2, SpringDoc OpenApi 3.x
- Run: `mvn spring-boot:run` in root directory.

-----------------------------------------------------

## Technical requirement:

Application for restaurant voting
Design and implement a REST API using Hibernate/Spring/SpringMVC (Spring-Boot preferred!) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menuItem of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote for a restaurant they want to have lunch at today
- Only one vote counted per user
- If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menuItem each day.

As a result, provide a link to GitHub repository. It should contain the code, README.md with API documentation and
couple curl commands to test it (**better - link to Swagger**).

P.S.: Make sure everything works with latest version that is on GitHub :)  
P.P.S.: Assume that your API will be used by a frontend developer to build frontend on top of that.

-----------------------------------------------------
[REST API documentation](http://localhost:8080/swagger-ui/index.html)
Креденшелы:

```
User1:  user1@yandex.ru / password
User2:  user2@yandex.ru / password
Admin: admin1@gmail.com / admin
Guest: guest1@gmail.com / guest
```