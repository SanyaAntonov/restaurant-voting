**REST / Spring Boot / Security / JPA / H2 / Lombok**
##
**Graduation Project**
##

This Java project offers a `RESTful API` with basic authentication for admin and regular users.


 **Voting system for deciding where to have lunch.**
>2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

**Technology stack:**
- Spring Boot
- Spring Security
- REST
- Spring Data JPA
- H2 DB
- Maven
- JUnit
- Lombok

##
**How to use this program**

**1. Clone a repository:**

```sh
 git clone https://github.com/SanyaAntonov/restaurant-voting.git
```

**2. Open the project using the IDE**

**3. Run your program.**

**4. Use Swagger UI to test this API :**
```sh
  http://localhost:8080/swagger-ui.html#/
```
**5. Some curl commands :**
```sh
Get all Restaurants : curl -s http://localhost:8080/api/v1/restaurant --user admin@gmail.com:admin
Get all Restaurant dishes : curl -s http://localhost:8080/api/v1/restaurant/1/dish/3 --user admin@gmail.com:admin
Get voting history : curl -s http://localhost:8080/api/account/history --user user@gmail.com:password
Create or update vote : curl -s POST http://localhost:8080/api/account/vote/1 --user user@gmail.com:password
```
##
