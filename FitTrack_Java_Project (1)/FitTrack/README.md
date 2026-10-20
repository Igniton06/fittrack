# FitTrack — Online Fitness Tracker

A beginner-friendly Java web application built with Spring Boot, Maven, HTML, CSS and JavaScript.

## Features
- Fitness dashboard with workout count, total minutes, estimated calories and water intake
- Add and delete workouts
- Water intake tracker (daily goal of 8 glasses)
- BMI calculator processed by Java
- Responsive dark UI and basic form validation
- REST endpoints implemented in Java

## Requirements
- JDK 17 or later
- Internet connection on the first Maven build (to download dependencies)
- An IDE such as IntelliJ IDEA, Eclipse, or VS Code (optional; command line is enough)

## Run on Windows
1. Install a JDK (17 or later) and Maven, if not already installed.
2. Extract this ZIP.
3. Open the extracted `FitTrack` folder.
4. Click the folder address bar in File Explorer, type `cmd`, and press Enter.
5. Run `java -version` and `mvn -version` to verify installations.
6. Run `mvn spring-boot:run`.
7. Wait until the terminal says the application has started.
8. Open http://localhost:8080 in Chrome or Edge.

Stop the server with `Ctrl+C` in the terminal. To start it again, repeat step 6.

## Build a runnable JAR
From the project folder:
```bash
mvn clean package
java -jar target/fittrack-1.0.0.jar
```

## API endpoints
- `GET /api/workouts` — list workouts
- `POST /api/workouts` — add workout JSON: `{"name":"Cycling","minutes":25,"calories":160}`
- `DELETE /api/workouts/{id}` — delete a workout
- `GET /api/water` — read water count
- `POST /api/water` — add one glass
- `DELETE /api/water` — reset water count
- `POST /api/bmi` — calculate BMI JSON: `{"heightCm":170,"weightKg":65}`
- `GET /api/summary` — dashboard summary

## Important limitation
This starter project keeps workout and water data in server memory. Data resets when the server stops/restarts. It does not include user accounts or a persistent database. Explain this honestly in the demonstration. A database such as MySQL or H2 can be added as a future enhancement.

## Suggested demonstration
1. Open the dashboard.
2. Add “Strength Training”, 30 minutes, 200 calories.
3. Add a second workout, such as “Walking”, 20 minutes, 80 calories.
4. Show the dashboard totals and delete one workout.
5. Add three glasses of water, then reset.
6. Calculate BMI using sample height and weight.
7. Explain that Java Spring Boot handles the API and business logic, while HTML/CSS/JavaScript render the interface.

## Project abstract
FitTrack is a web-based fitness tracking application designed to help users record physical activity and monitor basic wellness habits. It provides a dashboard, workout logging, water intake tracking, and a BMI calculator. The application uses Java with Spring Boot to process requests through REST endpoints and serves a responsive web interface built with HTML, CSS and JavaScript. The current version demonstrates core web application concepts and uses in-memory storage for simplicity.

## Viva questions
**1. What is FitTrack?**  
A web application for recording workouts, monitoring water intake and calculating BMI.

**2. Why did you use Java?**  
Java handles server-side logic, validation, calculations and API requests.

**3. What is Spring Boot?**  
A Java framework that simplifies creating and running web applications.

**4. What is Maven?**  
A build and dependency management tool. It downloads libraries and builds the project.

**5. What is an API?**  
A defined way for the frontend to communicate with the backend. Here, the browser sends HTTP requests to `/api/...`.

**6. What is REST?**  
An approach to web APIs using HTTP methods such as GET, POST and DELETE.

**7. How is BMI calculated?**  
BMI = weight in kilograms / (height in metres × height in metres).

**8. Does data persist after restart?**  
No. The starter version uses in-memory collections and counters. Database storage is a planned enhancement.

**9. What are the frontend technologies?**  
HTML structures the page, CSS styles it, and JavaScript sends API requests and updates the page.

**10. What can be improved later?**  
Login and registration, database storage, weekly charts, personalized workout plans, and deployment.

## Academic note
Review the code, customize the project with your details, and make sure you can explain the features during your viva. Do not claim that it has a database, authentication, live step counting, or wearable-device integration; those features are not implemented in this version.
