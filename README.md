# Calculatrice API

API REST permettant d'effectuer des opérations mathématiques de base.

## Fonctionnalités

- Addition de deux nombres
- Soustraction de deux nombres
- Multiplication de deux nombres
- Division de deux nombres (avec gestion de la division par zéro)

## Prérequis techniques

- Java 21 ou supérieur
- Maven 4.0.0 ou supérieur

## Installation

1. Cloner le dépôt
   ```
   git clone https://github.com/example/calculatrice-api.git
   cd calculatrice-api
   ```

2. Compiler le projet
   ```
   mvn clean package
   ```

3. Exécuter l'application
   ```
   java -jar target/calculatrice-api-0.1.0.jar
   ```

## Utilisation de l'API

L'API est accessible à l'adresse `http://localhost:8080/api/v1/calculatrice`.

### Points d'accès

- `POST /api/v1/calculatrice/addition` - Addition de deux nombres
- `POST /api/v1/calculatrice/soustraction` - Soustraction de deux nombres
- `POST /api/v1/calculatrice/multiplication` - Multiplication de deux nombres
- `POST /api/v1/calculatrice/division` - Division de deux nombres

### Format des requêtes

Toutes les requêtes doivent être au format JSON et contenir les champs suivants :

```json
{
  "number1": 10.5,
  "number2": 5.2
}
```

### Format des réponses

Les réponses sont au format JSON et contiennent les champs suivants :

```json
{
  "result": 15.7,
  "operation": "addition"
}
```

### Documentation Swagger

La documentation interactive de l'API est accessible à l'adresse `http://localhost:8080/swagger-ui.html`.

## Gestion des erreurs

L'API retourne les codes d'erreur HTTP standards :

- `400 Bad Request` - Requête invalide (par exemple, paramètres manquants ou division par zéro)
- `500 Internal Server Error` - Erreur interne du serveur

## Licence

Ce projet est sous licence MIT.