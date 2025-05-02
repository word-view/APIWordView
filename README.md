# API WordView
The backend of the WordView App

# Running (Docker)
First, build the docker image
```sh
docker build --platform linux/amd64 -t api-wordview .
```

Run the API forwarding port 8080
```sh
docker run -p 8080:8080 -t api-wordview
```
