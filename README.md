# Scoreboard
Scoreboard

## How to setup de application

prepare:
- Install Mysql db
- Install your favorite IDE

Install
- Open the project in your favorite IDE, we all know Intellij is the best
- Go to the application.yml and edit your username and password to your mysql config
- Run the ScoreboardApplication.class


## How to get a oauth token

1. get access token:
```
curl -X POST --user 'scoreboard:123456' -d 'grant_type=password&username=admin&password=password' http://localhost:8080/oauth/token
```

2. user the access token, replace $TOKEN with the token from request 1
```
curl -i -H "Accept: application/json" -H "Authorization: Bearer $TOKEN" -X GET http://localhost:8080/api/v0/role/1
```
## How to build front-end

First, install NPM. Then in project root directory:

* ```npm install```, then
* ```npm install -g grunt``` and finally
* ```grunt watch``` will recompile your JS immediately on change and place it in bundle.js.

We use ES6 imports and browserify to deliver the dependencies to the backend.
