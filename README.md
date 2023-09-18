# OnTheRun Project

## Setting up Environment Variables

---
1. **Navigate to "Run"** in the top menu and select **"Edit Configurations..."**
2. Select the necessary profile for configuration in the **Environments folder**.
3. In the section **Build and run** , click on **Modify options** and select **Environment Variables**.
5. **Click on the "..."** button next to **"Environment variables"** in the **"Environment"** section.
6. **In the "Environment Variables"** window, click the **"+"** button in the top right corner.
7. **Enter the name and value** of the environment variable, and then click **"OK"**.

---

#### Postgres DB
 - `POSTGRES_NAME="your_db_name"` - a Postgres DB name 
 - `POSTGRES_PASS="your_db_password"` - a Postgres DB password

## Build

---
```bash
./mvnw clean install
./mvnw compile
```

---
## Profiles
```bash
./mvnw clean install 
```
>[Spring Profiles](https://www.baeldung.com/spring-profiles)
*-Spring Profiles provide a way to segregate parts of your application configuration and make it only available in certain environments.*
---
