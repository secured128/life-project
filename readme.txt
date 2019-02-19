Heroku commands :

heroku login
heroku container:login (1Qazxsw3-$)
heroku container:push web -a life-project
heroku container:release web -a life-project
heroku open -a life-project

Local run :

docker run -p 8080:8080 life-project/life-project:latest



DB connection :

https://devcenter.heroku.com/articles/heroku-postgresql
