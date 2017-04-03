# Jokes Application

## Udacity Project # 4 - Build It Bigger
The objective of this project was to educate students on 5 Android building concepts:

* Java Libraries
* Android Libraries
* Google Cloud Endpoint
* Functional Tests
* Android Application Flavors

### Java Library
The Java library used in this project is `jokelib`. The `Jokes.java` class contains 3 different `List<List<String>>` arrays.

* Knock Knock Jokes
* Question & Answer Jokes
* Story Jokes

The data inside of each type contains the following
* Joke Number
* Joke Type
* Depending on the joke type - Subject / Question / Title
* Depending on the joke type - Punch Line / Answer / Story

Each joke type has a method that allows the application to retrieve sequential jokes, provided a number is sent in. The returning type is a `List<String>`. 
A method is provide to retrieve the number of jokes provided the appropriate type is sent in.

### Android Library
The Android Library in the project is `jokeactivitylib`, which contains both the `JokeActivity` and `JokeActivityFragment` classes. 
`JokeActivity.java` is setup to allow the application to be navigated through the list of jokes and to also update the `SharedPreferences` preferences file.

`JokeActivityFragment` is setup to inflate the view associated with the type of joke that is currently being viewed.

### Google Cloud Endpoint
Provides RESTful api services to pull jokes from the `jokelib`. A REST endpoint is provided for each type of joke in the library. 

### Function Tests
`JokeAsynTest` is a simple test to verify that the GCE returns something other than an empty String.

### Android Application Flavor
Two flavors were build into this project

* Free - Allows the person to view Knock Knock Jokes only and shows Ads at the bottom of the screen.
* Paid - Allows the person to view all jokes currently in the joke library. Additional ideas on the paid version, were to allow the person to add there own jokes and to share them with other people.

## Notes For Clone / Forking
The current setup for GCE is to be run locally with an external device connecting to it.

`gradle.properties`
```
gceBaseURL="http://your.localhost.name"
gcePort="8080"
endPoint="/_ah/api"
```

`jokesapi`
```
appengine {
...
httpAddress = "0.0.0.0"
...
}
```
