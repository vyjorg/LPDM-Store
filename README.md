## La place du marché - Microservice Store
Projet 12 - DA JAVA - Openclassrooms

**Domaine** : https://store.lpdm.kybox.fr

**Docker** : [https://hub.docker.com/r/vyjorg/lpdm-store](https://hub.docker.com/r/vyjorg/lpdm-store)

##### Liste des urls disponibles 

| URL                                      | Methode | Description                                                     |
|------------------------------------------|---------|-----------------------------------------------------------------|
| https://store.lpdm.kybox.fr/stores       | GET     | Retourne la liste des objets Stores présents en base de données |
| https://store.lpdm.kybox.fr/stores/{id}  | GET     | Retourne l'objet Store ayant pour identifiant le paramètre {id} |
| https://store.lpdm.kybox.fr/admin/save   | POST    | Persiste l'objet Store passé en paramètre                       |
| https://store.lpdm.kybox.fr/admin/delete | POST    | Supprime l'objet Store passé en paramètre                       |