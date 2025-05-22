<h1 align="center">Phone Book Project</h1>

<div align="center">
  <img src="https://github.com/user-attachments/assets/9be4f86a-d88c-4a25-a0ea-faeb0ccc3540" alt="phone_contact_repre" width="300"/>
</div>

### Why I built this project?

The phone-book project is my **internship training project** and it is designed to teach me *Spring Boot Framework's* fundamentals and to include many different technologies I will explain in detail in the below. 

## Basic Structure

+ It has phone book represenation for two entities which are Person and Contact. I aimed to build one-to-many relationship between them.
<div align= "center">
  <img src="https://github.com/user-attachments/assets/074868c8-b286-4bbb-b309-b6cd3bca56e4" width= "800" height= "300"/>
</div>

+ There is a 3 layered structure in the project Controller --> Service --> Repository.

<div align= "center">
  <img src="https://github.com/user-attachments/assets/34197383-3ce7-4c45-beee-f1b7fe48773f" width= "500" height= "200"/>
</div>

+ Project has examples of Java 8's future implmentations like Stream Class and lambda functions and Optional tag as well as Aspect Oriented Programming.

<div align= "center">
  <img src="https://github.com/user-attachments/assets/ede06994-b6ee-41ef-9a69-0380f55a6156" width= "400" height= "200"/>
</div>


- [ ] Even the representation of the db is not complex, the project reaches its target by combining many features.

## Important Logics I want to Mention
### 1. Map Struct
In general term,  *a mapper is a function or a class responsible for converting an object of one type into an object of another type*.
In this project we used mapper classes to convert RequestDTO to Entity and vice-versa. The reason for that is describing the entity -object that will be stored in the db- in a certain way. 
For example Entity includes id but the user will not provide the program with an id.
Furthermore, we may also change the structure of field in conversion like LocalDateTime. For ex.
```
@Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String birthDate) { //it will automatically be used by "fromPersonRequestDTOToPersonEntity"
        return birthDate != null ? LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")) : null;
    }
 ```
That's why it is good to know:
```
@Mapper(uses = )
@Mapping (target =  , source = ,  qualifiedByName = )
@Named(" ")
```
2. ### Exception Handling
3. ### Validation
4. ### Caching
5. ### Rate Limiting
## Technology Stack
## Missing Parts
## Tools, Frameworks, Libraries
- Intellij IDEA 2020
- Gradle 8
- Java 17
- Spring Boot 3.0.4
- Spring Data 3.0.4
- OpenAPI v3
- Lombok 1.18.26
- Elastic APM 1.38.0
