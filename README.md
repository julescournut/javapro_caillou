# Caillou

## Utilisation de notre API 

Une fois le projet cloné, lancer CaillouApplication.

Routes disponibles :

- localhost:8080/basket?email=[email]&numBasket=[numBasket]
    > Affiche le contenu du panier selectionné avec les produits présents dans ce panier et les qualités et défauts du panier.
- localhost:8080/addToBasket?code=[codeBar]&email=[email]&numBasket=[numBasket]
    > Ajoute un produit au panier selectionné (plusieurs ajouts du même produit sont possibles)
- localhost:8080/removeFromBasket?code=[codeBar]&email=[email]&numBasket=[numBasket]
    > Enlève la première occurence du produit


## 1 - Objectifs du TP 
L'objectif de ce TP est de réaliser une application Spring Boot permettant d'obtenir des informations de nutritions de différents produits.  
Cette application récupérera des données sur les produits auprès d'une API publique, Open Food Facts. Elle mesurera différentes informations sur les qualités nutritives des produits et exposera les résultats de ces analyses sous forme d'une API REST.  
Vous aurez donc en charge de développer l'application en vous basant sur Spring Boot et sur n'importe qu'elle autre librairie Java disponible. 

## 2 - Présentation de l'application
Il existe une application mobile bien connue, Yuka, permettant d'obtenir les informations nutritives d'un produit en scannant son code barre. Le produit en question est noté et qualifié (Excellent, Bon, Mauvais, Mediocre). C'est très pratique lorsque vous allez faire vos courses en magasin, un peu moins lorsque vous passez par un drive.  

Afin de faire bénéficier du même type de service les clients des drives, vous allez construire une appication exposant une API REST permettant d'obtenir toutes les informations nutritives d'un produit ainsi que son score nutritionnel associé. Exposer ses informations sous forme d'une API REST permettra aux differents drives de pouvoir facilement intégrer ces données sur leurs plateformes.  

De plus, afin de fournir un service encore plus intéressant pour l'utilisateur final, votre application devra enregistrer les données des consomateurs afin de pouvoir à terme leur fournir des services plus complets comme des analyses de paniers, des analyses mensuelles voir des conseils personnalisés sur leur alimentation.  

Pour fournir toutes ces informations, votre application utilisera les données d'Open Food Facts. Elles sont accessibles via une API que vous aurez à interroger.

## 3 - Fonctionalités attendues 

#### US 0 : API REST
Votre application sera une API REST et respectera donc les conventions REST.


#### US 1 : Calculer les qualités et les défauts de chaque produit 
Avec comme seule information un code barre, votre application fournie les qualités et les défauts du produit afin de renvoyer cette synthèse. Vous trouverez en annexe les informations permettant de définir les qualités et les défauts. Par exemple, un produit ayant 6g de sucre pour 100g, c'est une qualité. Par contre, un produit ayant plus de 31g de sucre pour 100g sera considéré comme un défaut (voir Qualités et défauts). 

#### US 2 : Calcul du Score nutritionnel
Votre application calculera le score nutritionnel basé sur les spécifications données dans la partie 7.  

#### US 3 : Sauvegarde des informations d'un utilisateur et d'un panier
En plus du code barre, on doit pouvoir fournir une adresse mail et un numéro de panier afin de pouvoir enregistrer les produits mis au panier par un consomateur. Toutes ces informations seront donc sauvegardées.  


#### US 4 : Synthèse de panier 
Votre application fournira une synthèse d'un panier donné afin de fournir à un consomateur un résumé du panier qu'il s'apprete à commander. Cette synthèse est une aggregation des qualités et des défauts des produits composant le panier.


#### US 5 : Suppression d'un produit d'un panier
Votre application permettra de supprimer un produit d'un panier d'un utilisateur.


#### [Facultatif] US 6 : Documentation de l'API REST générée en automatique
Qui dit API, dit documentation. Sans documentation, impossible d'utiliser votre API. Donc API inutile.

#### [Facultatif] US 7 : Gestion des additifs
Afin de mieux renseigner le consommateur, votre application devra indiquer si un produit donné est composé d'additifs potentiellement dangereux. Vous trouverez dans ce repository un fichier csv listant les principaux additifs ainsi que leur niveau de toxicité.

#### [Facultatif] US 8 : Déploiement
Il existe des services gratuit en ligne permettant de déployer facilement et rapidement une application Spring Boot (Heroku...). Déployez votre applciation pour commencer à rendre un grand service à la communauté.


## 4 - Contraintes techniques 
Votre application exposera donc une API REST, elle devra à ce titre en respecter les conventions. Le format d'échange sera le JSON.  
Vous utiliserez le framework Spring Boot pour réaliser cette application. Si aucune version n'est imposé, je vous conseil de prendre la plus récente.  
Le choix de la base de données est laissé à votre discretion. H2 à l'avantage d'avoir un mode in-memory bien pratique pour les premières phases de développement, néanmoins ce choix n'est pas imposé ! 
Enfin, aucune contrainte n'est imposé sur les choix des librairies à utilisé dans vore application, ce qui veut dire que vous pouvez utiliser n'importe quelle librairie qui vous facilitera la vie.
Maven + java 8


## 5 - Evaluation
L'application à réaliser étant assez simple, la complétude sera évalué. Mais réaliser entièrement l'application ne sera pas suffisant puisque d'autres points seront évalués :
- Le niveau de test : Une application non testé ne peut pas être déployée en production sans prendre de risques important. Et une application qui n'est pas en production est une application qui ne sert à rien
- L'organisation du code : Repensez au premier TP, rappelez vous des concepts évoqués (modularité, identification des responsabilités, raisons de changer...) afin de concevoir une application bien pensée, évolutive et testable
- L'utilisation de Spring Boot : SB vous fournit un cadre de travail ainsi que différentes fonctionalités vous permettant de développer rapidement et proprement votre application. Autant s'en servir. Bien evidement l'utilisation de l'injection de dépendances de SB sera scruté avec intéret !

Pour ce TP, la clarté du code sera également évalué. Indentation, nommage des variables, classes, méthodes... et commentaires sont vos amis. 

## 6 - Open Food Facts
L'API d'Open Food Facts est disponible à l'adresse suivante :  
https://fr.openfoodfacts.org/data  
Exemple : https://fr.openfoodfacts.org/api/v0/produit/3029330003533.json  
La documentation de l'API est diponible ici : https://en.wiki.openfoodfacts.org/API  

## 7 - Score nutritionnel

Le score nutritionnel des aliments repose sur le calcul d’un score unique et global prenant en
compte, pour chaque aliment :
- une composante dite « négative » N,
- une composante dite « positive » P.
- La composante N du score prend en compte les éléments nutritionnels dont il est recommandé
de limiter la consommation : densité énergétique (apport calorique en kJ pour 100 g d’aliment),
teneurs en acides gras saturés (AGS), en sucres simples (en g pour 100g d’aliment) et en sel (en
mg pour 100g d’aliment). Sa valeur correspond à la somme des points attribués, de 1 à 10, en
fonction de la teneur de la composition nutritionnelle de l’aliment (cf. tableau 1). La note pour la
composante N peut aller de 0 à 40.

Tableau 1 : Points attribués à chacun des éléments de la composante dite « négative » N  

| Points | Densité énergétique (kJ/100g) (energy_100g) | Graisses saturées (g/100g) (saturated-fat_100g) | Sucres simples (g/100g) (sugars_100g) | Sodium1 (mg/100g) (salt_100g) |
| ------ | ----------------------------- | -------------------------- | ----------------------- | ----------------- |
| 0      | < 335                         | < 1                        | < 4,5                   | < 90              |
| 1      | > 335                         | > 1                        | > 4,5                   | > 90              |
| 2      | > 670                         | > 2                        | > 9                     | > 180             |
| 3      | > 1005                        | > 3                        | > 13,5                  | > 270             |
| 4      | > 1340                        | > 4                        | > 18                    | > 360             |
| 5      | > 1675                        | > 5                        | > 22,5                  | > 450             |
| 6      | > 2010                        | > 6                        | > 27                    | > 540             |
| 7      | > 2345                        | > 7                        | > 31                    | > 630             |
| 8      | > 2680                        | > 8                        | > 36                    | > 720             |
| 9      | > 3015                        | > 9                        | > 40                    | > 810             |
| 10     | > 3350                        | > 10                       | > 45                    | > 900             |

- La composante P est calculée, en fonction de la teneur de l’aliment en fibres et en protéines (exprimées en g pour 100 g d’aliment). Pour chacun de ces éléments, des points, allant de 1 à 5 sont attribués en fonction de leur teneur dans l’aliment (cf. tableau 2). La composante positive P du score nutritionnel est la note correspondant à la somme des points définis pour ces deux éléments : cette note est donc comprise entre 0 et 10.  

Tableau 2 : Points attribués à chacun des nutriments de la composante dite « positive » P  

| Points | Fibres (g/100g) (fiber_100g) | Protéines (g/100g) (proteins_100g) |
| ------ | --------------- | ------------------ |
| 0      | < 0,9           | < 1,6              |
| 1      | > 0,9           | > 1,6              |
| 2      | > 1,9           | > 3,2              |
| 3      | > 2,8           | > 4,8              |
| 4      | > 3,7           | > 6,4              |
| 5      | > 4,7           | > 8,0              |

Calcul du score nutritionnel  
Le calcul final du score nutritionnel se fait en soustrayant à la note de la composante négative N la note de la composante positive P avec quelques conditionnalités décrites ci après.  

**Score nutritionnel = Total Points N – Total Points P**

La note finale du score nutritionnel attribuée à un aliment est donc susceptible d’être comprise entre une valeur théorique de - 10 (le plus favorable sur le plan nutritionnel) et une valeur théorique de + 40 (le plus défavorable sur le plan nutritionnel).  

Classement de l’aliment dans l’échelle nutritionnelle à cinq niveaux sur la base du score calculé  

| Classe    | Bornes du score | Couleur     |
| --------- | --------------- | ----------- |
| Trop Bon  | Min à -1        | green       |
| Bon       | 0 à 2           | light green |
| Mangeable | 3 à 10          | yellow      |
| Mouai     | 11 à 18         | orange      |
| Degueu    | 19 à Max        | red         |


## 8 - Qualités et défauts
Votre application devra lister pour chaque produit ses qualités et ses défauts basés sur les tableaux 1 et 2 listés précédemment. Les composantes négatives (tableau 1) seront classées suivant ces règles :
 - Considéré comme une qualité si les points du nutriment <=3  
 - Considéré comme un défaut si les points du nutriment >= 7
 
 En ce qui concerne les composantes positives (tableau 2), elles seront classées suivant ces règles :
 - Considéré comme une qualité si les points du nutriment >= 2  
 - Considéré comme un défaut si les points du nutriment <= 0
 
 
 ## 9 - Conseils pour les outils à utiliser
 
 - Requête API -> Unirest
 - BDD -> H2