# API WordView
The backend of the WordView App

# Running
```sh
  # Clone the repository
  git clone https://github.com/word-view/APIWordView

  # Enter the folder
  cd APIWordView/

  # run
  ./mvnw spring-boot:run
```

# Running (Docker)
First, build the docker image
```sh
docker build --platform linux/amd64 -t api-wordview .
```

Run the API forwarding port 8080
```sh
docker run -p 8080:8080 -t api-wordview
```

# Running (JAR)
Running from the jar is not recommended as i only deploy a new version when i update the server
```sh
  # Download from releases
  # And run either with --prod (production) or --dev
  java -jar ./wordview-0.0.4-SNAPSHOT-exec.jar --prod
```
