# Project-Micro-Services
 
<h1>L'bjectif du projet </h1>
<p>
 On va créer un système distribué basé sur les micro-services permettant de gérer les factures des clients en utilisant
 l'architecture ci-dessous, en y intégrant un Bus de messagerie avec KAFKA, un service de Stream processing avec Kafka Streams
</p>

![image](https://user-images.githubusercontent.com/61559275/173114013-41035ad7-7a80-4d61-b608-64910820adfb.png)

<h2> PARTIE 1 : </h2>
<p>
 Dans cette partie on va créer cinq micro-services, chaque micro-service contient un seul fonctionnalité.<br>
 Voici la liste des micro-services détails :
</p>
<ol>
 <h3> <li> customer-service </li> </h3>
 <p>
  Cette micro-service va gérer les clients de notre entreprise. Alors, on commence par créer une entité et une repository
  pour communiqué à la base de donnée.<br>
  Voici un exemple de consulté les client et la base de donnée après le démarrage de ce service
 </p>
 <p> La base de donnée : </p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173117120-7508ae8d-0aff-48e1-bb32-61a4cc535a5f.png)

 <p> consulté les clients : </p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173117258-e22a6d99-a7d0-4756-89a8-846234d612d3.png)

 <p> Le client numéro 1 : </p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173117301-280e118f-bc58-4690-9ecf-ddb6d06aea8f.png)

 <h3> <li> inventory-service </li> </h3>
 <p>
  Cette micro-service va gérer les produits de notre entreprise. Alors, on commence par créer une entité et une repository
  pour communiqué à la base de donnée.<br>
  Voici un exemple de consulté les produits et la base de donnée après le démarrage de ce service
 </p>
 <p> La base de donnée : </p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173118374-5b26b977-b1be-48aa-8f94-df9290ac880f.png)
 
 <p> consulté les clients : </p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173118638-80a7de77-935b-4505-bbff-ce97c17d4aa6.png)

 <p> Le produit numéro 2 : </p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173118704-328df11c-9ee0-4394-b7cd-4d17f98c8ff1.png)

 <h3> <li> gateway-service </li> </h3>
 <p>
  Maintenant on va créer un micro-service gateway-service qui peut consulté les deux micro-services qu'on a été 
  déjà créer dans le même service gateway.
  Voici un exemple de consulté les produits et les clients après le démarrage de service gateway
 </p>
 <p> consulté le client numéro 7 avec gateway : </p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173121610-388012ce-3901-40f9-b97b-7a41fc77c6cb.png)
 
 <p> consulté le produit numéro 5 avec gateway : </p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173121401-b19e64d2-02d2-48b6-8439-b1aefb7ff976.png)

 <h3> <li> eureka-discovery </li> </h3>
 <p>
  Maintenant on va créer un service d'enregistrement avec eureka discovery pour enregistrer les services.<br>
  Donc pour cunsulté les produits par exemple le service gateway envoi le nom de service qui gére les produits 
  ( inventory-service ) à le service d'enregistrement, puis ce service va retourne l'adresse ip à le gateway.<br>
  Voici l'enregistrement des services dans eureka après le démarrage de service eureka discovery et 
  le redémarrage de tout les service
 </p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173124930-14a5a8f3-5d06-4029-8998-a4f31df004c3.png)
 
 <p>
  Voici un exemple de consulté les produits et les clients après le démarrage de service eureke discovery et 
  le redémarrage de tout les service 
 </p>
 <p> consulté les clients avec Eureka : </p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173125151-49bd4ff0-6014-418b-aaff-542d3f6564d7.png)

 <p> consulté les produits avec Eureka : </p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173125538-13aaac48-0e26-4e29-95dc-253d5af14d87.png)

 <h3> <li> billing-service </li> </h3>
 <p>
  En fin on va créer un micro-service pour gérer les facteurs des clients. Alors on va communiqué à des données dans 
  les autre micro-services de manière declarative.<br>
  on utilise la dépendance OpenFeign avac l'annotation @FeignClient.<br>
  On va créer dans ce service deux entities, deux repository, deux model et deux interface avec l'annotation @FeignClient.
 </p>
 <p> Voici la base de données de la facturation </p>
 <p>On a deux table la table bill et la table product_item</p>
 <p> Dans la table bill on a deux facteur concernant deux clients</p>
 
 ![image](https://user-images.githubusercontent.com/61559275/173128111-84eb2a08-272d-487a-ba7f-8f4d9f7cd776.png)

 <p>Dans la table product_item on a deux produits concernat le client numéro 1 et un produit concernat le client numéro 2
 
 ![image](https://user-images.githubusercontent.com/61559275/173128164-44123f41-b9f8-4407-a097-2b3e0085275c.png)
 
 <p> Voici un exemple pour gérer une facteur d'un client </p>
 
![image](https://user-images.githubusercontent.com/61559275/173128474-10de477a-4c50-42e3-8588-20ba31675de1.png)
  
</ol>

<h2> PARTIE 2 : </h2>












