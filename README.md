# message_board

A microservice based full-stack social media website.

I wrote the backend services with several different languages, frameworks and styles of SQL ORMS. I did this for fun, to be exposed to several solutions for similar problems and to better learn which libraries I actually do enjoy.

## Reflections About Options

- Go and Python are my two favorite options. The two languages and their libraries were the easiest to use and learn.
- Python had the easiest coding. Python code is essentially pseudocode, and the Django framework and ORM has many clean methods. Django, however has an unnecesarry amount of configuration, which makes it annoying for small projects, and while type hinting is good, it's nowhere near the level of a staticly typed language, which increases the need for error checking and input santizing.
- Go is the most lightweight of the options. I had to do more things manually because of fewer features in Gin and Go's http built-in library, however, the simplicity of Go makes it easy to write helper functions. Go does have it's disadvantages. While Go is easy to learn, it does things differently than other languages. There were a couple times this caused issues, for example, I wasn't returning json data from a struct because I didn't know Go required the programmer to explicitly declare marshable fields. Go libraries don't have as many features as other languages, so having a personal library of helper functions would help in future projects.

## Lessons Learned

- Microservice-based Architecture
- Authentication Basics

## Services

- [auth-gin:](auth-gin) GoLang and Gin microservice for handling authintication security
- [posts-express:](posts-express) Typescript and Express microservice for storing posts
- [profile_django:](profile_django) Python and Django microservice for storing user settings and profile information
- [api-actix:](api-actix) Rust and Actix api server for front ends to interact with, does not need a database
- [language-go](language-go) Go server without and external libraries for serving language options
- [spa-react:](spa-react) Typescript and React application for the web browser front end

## Languages Used

[Typescript](https://www.typescriptlang.org/)

[Go](https://golang.org/)

[Rust](https://www.rust-lang.org/)

[Python](https://www.python.org/)

## Frameworks Used

[Express](https://expressjs.com/)

[Gin](https://github.com/gin-gonic/gin)

[Actix](https://actix.rs/)

[DJango](https://www.djangoproject.com/)

[React](https://reactjs.org/)

## Databases And Orms Used

[PostgreSQL](https://www.postgresql.org/)

[GORM](https://gorm.io/)

[Prisma](https://www.prisma.io/)

[DJango ORM](https://www.djangoproject.com/)

# Developer Instructions

## Ports Used

- `3000` spa-react
- `5000`: api-actix
- `5001` auth-gin
- `5002` posts-express
- `5003` profile-django
- `5004` language-go

## Commands

- `./api` luanches main api server
- `./migrate_profile` luanches profile microservice
- `./posts` luanches posts microservice
- `./profile` luanches profile microservice
- `./language` luanches language microservice
- `./migrate_profile` runs database migrations for profile microservice
