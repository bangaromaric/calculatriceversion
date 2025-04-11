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

## Exemples d'utilisation des fonctions avancées

### Fonctions trigonométriques

```bash
# Calculer le sinus d'un angle en radians.
curl -X POST http://localhost:8080/api/v1/calculatrice/avancee/sinus \
     -H "Content-Type: application/json" \
     -d '{"angleRadians": 1.5708, "degres": false}'

# Calculer le cosinus d'un angle en degrés
curl -X POST http://localhost:8080/api/v1/calculatrice/avancee/cosinus \
     -H "Content-Type: application/json" \
     -d '{"angleRadians": 60, "degres": true}'

# Calculer la tangente d'un angle
curl -X POST http://localhost:8080/api/v1/calculatrice/avancee/tangente \
     -H "Content-Type: application/json" \
     -d '{"angleRadians": 0.7854, "degres": false}'
```

### Fonctions exponentielles et logarithmiques

```bash
# Calculer e^x
curl -X POST http://localhost:8080/api/v1/calculatrice/avancee/exponentielle \
     -H "Content-Type: application/json" \
     -d '{"number": 2}'

# Calculer le logarithme naturel
curl -X POST http://localhost:8080/api/v1/calculatrice/avancee/logarithme-naturel \
     -H "Content-Type: application/json" \
     -d '{"number": 7.389}'
```

### Arrondi personnalisé

```bash
# Arrondir un nombre à 2 décimales
curl -X POST http://localhost:8080/api/v1/calculatrice/avancee/arrondir \
     -H "Content-Type: application/json" \
     -d '{"valeur": 3.14159, "decimales": 2}'
```


## Licence

Ce projet est sous licence MIT.