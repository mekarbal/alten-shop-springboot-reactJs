AltenShop


Prerequisites

Before running the project, ensure you have the following installed:

  - Docker (for database containerization)

  - Node.js >= 20 (for frontend development)

  - Java 17
    
  - Maven
    
Backend Setup ===>

Running the Database with Docker

To set up the PostgreSQL database using Docker, execute the following command:

```
docker run --name altenShopDB \
  -e POSTGRES_DB=altenShop \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin \
  -p 5432:5432 \
  -d postgres
```

Installing Dependencies

Navigate to the backend directory and install the required dependencies:

```
cd backend/altenShop
mvn clean install
```

Running the Backend Server

Start the backend server: 

```
mvn spring-boot:run
```
The API should now be accessible at http://localhost:8081.


Frontend Setup ===>


Installing Dependencies

Navigate to the frontend directory and install the required dependencies:

```
cd frontend/alten-shop-react
npm install
```
Running the Frontend Application

Start the frontend application:

```
npm run dev or yarn run dev
```
This should launch the application at : http://localhost:5173/


