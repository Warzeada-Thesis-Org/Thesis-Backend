# Thesis Project Backend
Git repository for project simulation for my bachelor's thesis

Also contains Spring Boot application implementing automation for the GitHub Project
Server listens to issue update POST requests sent via a webhook from GitHub. In case
of an issue being moved to the "In Progress" status column, the application sends a
GraphQL request to assign the user who did the move to the issue.

# Requirements
- Java JDK 17

# Running the server
run `./gradlew bootRun` on Unix-like OS or `gradlew.bat bootRun` in Windows
