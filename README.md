# Guide de Création et Utilisation d'un Projet Spring

Ce guide explique les étapes pour créer un nouveau projet Spring ainsi que l'utilisation des annotations clés dans le framework Spring.

## 1. Créer un Nouveau Projet Spring

Pour créer un nouveau projet Spring :
- Rendez-vous sur le site officiel : [Spring Initializr](https://start.spring.io/).
- Configurez votre projet en choisissant :
    - **Type de projet** : Maven ou Gradle.
    - **Langage** : Java, Kotlin ou Groovy.
    - **Version de Spring Boot** : Choisissez une version stable.
    - **Dépendances** : Ajoutez les dépendances nécessaires (par exemple, Spring Web, Spring Data JPA, etc.).
- Cliquez sur **Generate** pour télécharger le projet, puis extrayez et ouvrez-le dans votre IDE.

## 2. Annotations Clés dans Spring

### `@RestController`
- Utilisé pour marquer une classe comme un **contrôleur REST**.
- Permet de gérer les requêtes HTTP entrantes et de renvoyer des réponses (souvent au format JSON).
- Exemple :
  ```java
  @RestController
  @RequestMapping("/api/students")
  public class StudentController {
      @GetMapping("/{id}")
      public ResponseEntity<Student> getStudent(@PathVariable Long id) {
          // Logique pour retourner un étudiant
      }
  }
  ```

### `@Service`
- Utilisé pour marquer une classe comme un **service**.
- Contient la logique métier de l'application.
- Exemple :
  ```java
  @Service
  public class StudentService {
      public List<Student> getAllStudents() {
          // Logique pour retourner tous les étudiants
      }
  }
  ```

### `@Autowired`
- Utilisé pour **l'injection de dépendances** dans une classe.
- Permet à Spring de gérer les instances des classes automatiquement.
- Exemple :
  ```java
  @RestController
  public class StudentController {
      private final StudentService studentService;

      @Autowired
      public StudentController(StudentService studentService) {
          this.studentService = studentService;
      }
  }
  ```

### `@Repository`
- Utilisé pour marquer une classe comme une **couche de persistance**.
- Représente le niveau d'accès aux données (DAO).
- Peut être utilisé avec des frameworks comme JPA ou Hibernate.
- Exemple :
  ```java
  @Repository
  public interface StudentRepository extends JpaRepository<Student, Long> {
      Optional<Student> findByEmail(String email);
  }
  ```

## 3. Architecture Typique d'un Projet Spring

Un projet Spring est souvent organisé en couches pour séparer les responsabilités :

1. **Controller** : Gère les requêtes HTTP.
    - Annotation : `@RestController`

2. **Service** : Contient la logique métier.
    - Annotation : `@Service`

3. **Repository** : Gère l'accès aux données.
    - Annotation : `@Repository`

4. **Modèle** : Représente les entités de la base de données.
    - Annotation : `@Entity`

---

# Guide de Configuration pour un Projet Spring avec PostgreSQL

Comment configurer un projet Spring Boot avec une base de données PostgreSQL, en incluant les dépendances nécessaires et la configuration de base.

## 1. Configuration des Dépendances

Ajoutez les dépendances suivantes dans votre fichier `pom.xml` pour intégrer Spring Data JPA et le driver PostgreSQL :

```xml
<dependencies>
    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

# Explications des annotations

## 1. `@PostMapping`
Utilisé pour définir un point de terminaison HTTP `POST`. Ce type de méthode est généralement utilisé pour créer des ressources sur le serveur.  
Dans ce cas, la méthode `Save()` est utilisée pour créer un nouvel étudiant.
  ```java
   @PostMapping
    public Student Save(@RequestBody Student student){
        return service.Save(student);   
    }
```
    

## 2. `@GetMapping`
Utilisé pour définir un point de terminaison HTTP `GET`. Ce type de méthode est utilisé pour récupérer des données depuis le serveur.  
La méthode `findAllStudents()` récupère tous les étudiants, et `findByEmail()` récupère un étudiant spécifique par son e-mail.

```java
        @GetMapping
        public List<Student> findAllStudents() {
            return service.findAllStudents();
        }
```
## 3. `@PutMapping`
Utilisé pour définir un point de terminaison HTTP `PUT`. Ce type de méthode est utilisé pour mettre à jour une ressource existante sur le serveur.  
La méthode `updateStudent()` permet de mettre à jour les informations d'un étudiant.

```java
    @PutMapping
    public Student updateStudent(@RequestBody Student student){
        return service.update(student);
    }
```
## 4. `@DeleteMapping`
Utilisé pour définir un point de terminaison HTTP `DELETE`. Ce type de méthode est utilisé pour supprimer une ressource du serveur.  
La méthode `delete()` permet de supprimer un étudiant en utilisant son e-mail.
```java
    @DeleteMapping("/{email}")
    public void delete(@PathVariable("email") String email){
        service.delete(email);
    }
 ```
## 5. `@RequestBody`
Utilisé pour lier le corps de la requête HTTP à un objet Java. Cela permet de passer des données JSON dans le corps de la requête et de les convertir en un objet Java.
```java
    public Student Save(@RequestBody Student student){
        return service.Save(student);
    }
```
## 6. `@PathVariable`
Utilisé pour extraire les variables d'URL. Dans ce cas, l'e-mail de l'étudiant est passé dans l'URL et récupéré via cette annotation.
```java
    public void delete(@PathVariable("email") String email){
        service.delete(email);
    }
```

# Configuration de la Base de Données PostgreSQL avec Spring Boot

Ce fichier explique les différentes clés utilisées dans le fichier `application.yml` pour configurer une base de données PostgreSQL avec Spring Boot.

## Configuration Complète

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/student_db
    username: hamidou
    password: hamidou
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: create
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```

---

## Explication des Clés

### `spring.datasource`
Cette section configure la source de données utilisée par Spring Boot.

- **`url`** : Définit l'URL de connexion à la base de données.
    - Format : `jdbc:postgresql://<host>:<port>/<database_name>`
    - Exemple : `jdbc:postgresql://localhost:5432/student_db`
    - Ici, `localhost` désigne l'adresse du serveur de base de données, `5432` est le port par défaut de PostgreSQL, et `student_db` est le nom de la base de données.

- **`username`** : Nom d'utilisateur pour se connecter à la base de données.
    - Exemple : `hamidou`.

- **`password`** : Mot de passe associé à l'utilisateur pour accéder à la base de données.
    - Exemple : `hamidou`.

- **`driver-class-name`** : Spécifie le driver JDBC à utiliser pour PostgreSQL.
    - Exemple : `org.postgresql.Driver`.

---

### `spring.jpa`
Cette section configure les propriétés liées à JPA (Java Persistence API).

#### `spring.jpa.hibernate.ddl-auto`
- Définit comment JPA doit gérer le schéma de la base de données :
    - `create` : Crée un nouveau schéma à chaque démarrage (attention, cela supprime les données existantes).
    - `update` : Met à jour le schéma sans supprimer les données existantes.
    - `validate` : Vérifie que le schéma correspond au modèle JPA sans effectuer de modifications.
    - `none` : Ne fait rien concernant le schéma.

#### `spring.jpa.database`
- Spécifie le type de base de données utilisée.
    - Exemple : `postgresql`.

#### `spring.jpa.database-platform`
- Définit le dialecte Hibernate à utiliser pour la base de données.
    - Exemple : `org.hibernate.dialect.PostgreSQLDialect`.

#### `spring.jpa.show-sql`
- Si défini sur `true`, les requêtes SQL exécutées par Hibernate sont affichées dans la console.
    - Utile pour le débogage.

---

### `spring.jpa.properties.hibernate`
Cette section contient des propriétés spécifiques à Hibernate.

#### `hibernate.format_sql`
- Si défini sur `true`, les requêtes SQL affichées dans la console seront formatées pour une meilleure lisibilité.

---

## Remarques

- **Fichier `application.yml`** : Placez cette configuration dans un fichier nommé `application.yml` dans le répertoire `src/main/resources`.
- **Base de données PostgreSQL** : Assurez-vous que PostgreSQL est installé et configuré sur votre machine, avec la base de données et les identifiants correspondants.
