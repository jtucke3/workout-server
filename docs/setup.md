# Local Setup Guide

This guide walks you through setting up the project locally using Docker and the Maven wrapper.

---

## Prereqs

Make sure you have the following installed:

- [Docker Desktop](https://www.docker.com/products/docker-desktop)
- `.mvnw` Maven wrapper script (in the project root so you shouldnt need it)

---

## Step 1: Start Docker

Open **Docker Desktop** and wait until it says **"Docker is running"**.

---

## Step 2: Run MySQL Container

Open a **bash** terminal and paste the following command to start a MySQL 8 container:

docker run -d -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=secret \
  -e MYSQL_DATABASE=workout \
  -e MYSQL_USER=workout_user \
  -e MYSQL_PASSWORD=workout_pass \
  --name workout-db \
  mysql:8


  docker run -d -p 3306:3306 --name workout-db -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=workout -e MYSQL_USER=workout_user -e MYSQL_PASSWORD=workout_pass mysql:8

## Step 2: Run SpringBoot

Once the database is running, start the Spring Boot app using the Maven wrapper in a separate terminal:

./mvnw spring-boot:run
