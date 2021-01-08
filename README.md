# ![pageres](src/main/resources/W.png)
![Days](https://img.shields.io/static/v1?label=Working-Days&message=2&color=blue)
![Status](https://img.shields.io/static/v1?label=Done&message=100%&color=green)


Information  consumed from the next external service that counts
with the data of users, their albums and their photos; in addition to their posts and
comments from other users about them: https://jsonplaceholder.typicode.com/

This information on the service is accessible through this API, contemplating:
1. Users.
2. The photos.
3. The albums of the system and of each user.
4. The photos of a user. 
5. Register an album shared with a user and their permissions.
6. Change a user's permissions for a specific album.
7. Bring in all users who have a certain permission regarding a specific album.


*See [Challenge](src/main/resources/Challenge.md) for the details of the tasks.*

## Example implementation on localhost
##### Get all Users
```java
class WxUser {

    private final String url = "http://localhost:8080/wx";

    List<User> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url + "/users", String.class);
    }
}
```

## API Endpoints

Base path: `/wx`


### Users
##### Get All Users
    
    /users
    
##### Get User by id
Params: `id: Integer`

    /users?id=#

##### Get all Users by Permissions
###### Params required: 
`albumId: Integer`

######Params not required:
`read: Boolean`
`write: Boolean`

    /usersByPermission?albumId=#&read=#&write=#

##### Save User
###### There is a @RequestBody expected

    /user

### Photos
##### Get all Photos

    /photos

##### Get Photos by id
###### Params:
`id: Integer`

    /photo?id=#

##### Get all Photos by User id
###### Params:
`id: Integer`

    /photosByUser?id=#

### Albums
##### Get all Albums 
    
    /albums

##### Get all Albums by id
###### Params:
`id: Integer`

    /album?id=#

##### Get all Albums by User id
###### Params:
`id: Integer`

    /albumsByUser?id=#

##### Register an album shared with a user and their permissions
###### There is a @RequestBody expected

    /register

### Posts
##### Get all Posts

    /posts

##### Get all Posts by id
###### Params:
`id: Integer`

    /post?id=#

### Comments
##### Get all Posts

    /comments

##### Get all Posts by id
###### Params:
`id: Integer`

    /comment?id=#

##### Get all Posts by User id
###### Params:
`id: Integer`

    /commentsByUser?id=#

### Permissions
##### Get all Permissions by User id
###### Params:
`id: Integer`

    /permissionsByUser?id=#

##### Change Permissions
###### Params:
`id: Integer`
`albumId: Integer`
`read: Boolean`
`write: Boolean`

    /permissionsByUserAndAlbum?id=#&albumId=#&read=#&write=#

##### Register a Permission
###### There is a @RequestBody expected

    /permission




Coded with ❤️ by Juan Patricio Doyle ✨2020

