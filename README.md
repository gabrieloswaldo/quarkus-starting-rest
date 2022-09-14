# Starting with Quarkus - Simple REST API Project

This project is from the [Starting with Quarkus](https://agoncal.teachable.com/p/course-starting-with-quarkus) course.

From this project I've got a good understanding of Quarkus, and learned an entire development cycle with it: 
bootstrap, develop, test, package and execute a REST application as a Docker container.

The different steps I went through were:
- Understand Quarkus,
- Check my development environment,
- Expose a REST endpoint using JAX-RS,
- Inject beans with CDI,
- Test the REST endpoint with JUnit and RESTAssured,
- Configure the application with MicroProfile Configuration,
- Configure Quarkus,
- Add profiles to the configuration,
- Build executable JARs,
- Build a native executable and a Linux executable with GraalVM,
- Check the performances of a Quarkus application,
- Containerize the application with Docker and execute it.


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-starting-rest-1.0.0-SNAPSHOT-runner`

## Containerizing a native executable

To create a Linux based binary and run it on a Linux based container, use:
```shell script
./mvnw package -Pnative \
  -Dquarkus.native.container-build=true \
  -Dquarkus.container-image.build=true
  ```

Then, run the container: 
```shell script
docker run -i --rm -p 8080:8080 quarkus/quarkus-starting-rest:1.0.0-SNAPSHOT
  ```

## API Documentation
This is a summary of the application endpoints. There is no database connection,
it's just a simple Java instantiated List.

### GET - `http://localhost:8080/api/books`
Get all books.

### GET - `http://localhost:8080/api/books/{book-id}`
Get the books with specified id, or return "null" if doesn't exists.

### GET - `http://localhost:8080/api/books/count`
Get the number of books.
