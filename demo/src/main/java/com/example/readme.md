# Rendu 2: Approfondissement sur les DAO (Data Access Objects)

## Contexte
Ce projet vise à approfondir l'utilisation des DAO pour une meilleure séparation des couches dans une application de gestion des événements et des réservations.

## Objectifs
1. Refactoriser les opérations CRUD pour chaque entité (Utilisateurs, Événements, Salles, Terrains, Réservations) en utilisant une structure DAO.
2. Créer une interface générique DAO pour uniformiser les méthodes d'accès aux données.
3. Implémenter un gestionnaire des transactions JDBC afin d'assurer l'intégrité des opérations multi-requêtes.
4. Fournir des tests unitaires pour chaque DAO.

## Structure du Projet
Le projet est structuré de manière à respecter les bonnes pratiques de séparation des responsabilités, avec les composants suivants :

- **DAO** : Accès aux données via des classes DAO.
- **Service** : Logique métier qui utilise les DAO pour effectuer des opérations complexes.
- **Model** : Entités représentant les objets métiers (Utilisateurs, Événements, Salles, etc.).
- **Main** : Exécution de l'application ou des tests.

## Technologies Utilisées
- **JDBC** pour l'accès à la base de données.
- **JUnit** pour les tests unitaires.
- **MySQL** comme base de données.

## Entités
Les principales entités du projet sont les suivantes :

### 1. **Utilisateur**
Représente un utilisateur du système (par exemple un administrateur, un organisateur d'événements, etc.).

### 2. **Evenement**
Représente un événement avec des informations telles que le nom, la date, la description, et l'utilisateur qui a créé l'événement.

### 3. **Salle**
Représente une salle qui peut être réservée pour un événement.

### 4. **Terrain**
Représente un terrain qui peut être réservé pour un événement.

### 5. **Reservation**
Représente une réservation faite par un utilisateur pour un événement, une salle, et un terrain.

## Architecture du DAO
Le modèle DAO est implémenté avec une interface générique `GenericDAO` qui définit les méthodes de base pour l'accès aux données. Chaque entité (Utilisateur, Evenement, Salle, etc.) implémente cette interface avec des méthodes spécifiques pour les opérations CRUD.

### Interface `GenericDAO<T>`
```java
public interface GenericDAO<T> {
    void add(T entity);
    T get(int id);
    List<T> getAll();
    void update(T entity);
    void delete(int id);
}
