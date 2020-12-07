# ![pageres](src/main/resources/W.png)

![Build Status](https://travis-ci.org/juanpatriciodoyle/wx.svg?branch=dev)



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


*See [Challenge](/Challenge Tasks) for the details of the tasks.*

## Example usage on localhost
##### Get all Users
```java
class WxUserTests {

    private final String url = "http://localhost:8080/wx";

    List<User> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url + "/users", String.class);
    }
}
```

## API Endpoints
Params: `id: Integer`

Base path: `/wx`


#### Users
###### Get All Users
    
    /users
    
###### Get User by id

    /users?id=#

##### Photos
###### Get all Photos

    /photos

###### Get Photos by id

    /photos?id=#

###### Get all Photos by User id

    /photosByUser?id=#


##### Albums
###### Get all Albums 
    
    /albums

###### Get all Albums by id

    /albums?id=#

###### Get all Albums by User id

    /albumsByUser?id=#

##### Posts
###### Get all Posts
    
    /posts

###### Get all Posts by id

    /posts?id=#

##### Comments
###### Get all Posts

    /comments

###### Get all Posts by id

    /comments?id=1

###### Get all Posts by User id

    /commentsByUser?id=1


Coded with ❤️   by Juan Patricio Doyle ✨2020

