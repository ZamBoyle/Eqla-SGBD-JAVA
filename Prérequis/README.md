## 1. Installation
La première chose à installer c'est le connecteur de mariadb:

> MariaDB Connector/C est une bibliothèque client légère et avancée pour l'accès et le streaming de données à haute performance. Il possède des fonctionnalités avancées, des opérations en masse, des API asynchrones/non bloquantes et plusieurs options de connexions chiffrées via TLS/SSL. MariaDB Connector/C est sous licence LGPL.

https://mariadb.com/downloads/connectors

## 2. Création d'un utilisateur MariaDB
Le connecteur n'accepte pas les connexions avec l'utilisateur root.
Il va donc falloir se connecter avec un autre utilisateur dans nos programmes JAVA.

Avant tout connectez-vous à mariadb
```shell
mysql -u root -p
```
### 2.1 Vérification des utilisateurs existants
Ensuite nous allons voir les utilisateurs déjà existants dans mysql
```sql
SELECT User FROM mysql.user;
+-------------+
| User        |
+-------------+
| mariadb.sys |
| mysql       |
| root        |
+-------------+
```

### 2.2 Création d'un utilisateur
Maintenant on va créer notre nouvel utilisateur que nous appelerons 'new_user' avec comme mot de passe 'password1':
```sql
CREATE USER 'new_user'@localhost IDENTIFIED BY 'password1';
```
Vérifions que notre utilisateur est bien créé:
```sql
SELECT User FROM mysql.user;
+-------------+
| User        |
+-------------+
| mariadb.sys |
| mysql       |
| new_user    |
| root        |
+-------------+
```
### 2.3 Ajout des droits à l'utilisateur
Maintenant que notre utilisateur 'new_user' est créé nous allons lui donner des droits car pour le moment, il ne sait rien faire.
```sql
GRANT ALL PRIVILEGES ON *.* TO 'new_user'@localhost IDENTIFIED BY 'password1';
```
Le __\*.*__  dans l'instruction fait référence à la base de données ou à la table pour laquelle l'utilisateur a été accordé des privilèges. Cette commande spécifique donne accès à toutes les bases de données situées sur le serveur. Comme cela peut être un problème de sécurité majeur, vous devriez remplacer le symbole par le nom de la base de données à laquelle vous accordez l'accès.

Pour accorder des privilèges uniquement pour votreDB, tapez la déclaration suivante :

```sql
GRANT ALL PRIVILEGES ON 'votreBaseDeDonnées'.* TO 'new_user'@localhost;
```

Il est crucial de rafraîchir les privilèges une fois qu'ils ont été attribués avec la commande :

```sql
FLUSH PRIVILEGES;
```

L'utilisateur que vous avez créé a maintenant des privilèges complets et un accès à la base de données et aux tables spécifiées.

Une fois cette étape terminée, vous pouvez vérifier que le nouvel utilisateur new_user a les bonnes autorisations en utilisant la déclaration suivante :

```sql
SHOW GRANTS FOR 'new_user'@localhost;
```
Les informations fournies par le système sont affichées sur le terminal.
### 2.4 Test de connexion du nouvel utilisateur
Nous allons tester avec le client mysql si nous savons nous connecter
```shell
mysql -u new_user -p
```
Normalement vous devriez être connecté avec votre nouvel utilisateur 'new_user'




