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
