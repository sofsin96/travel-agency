# Travel Agency
## Login
### POST
http://localhost:8080/login
#### Request with Form URL Encoded
![img.png](img.png)

#### Response body example
```json
{
  "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwb250dXMiLCJyb2xlcyI6WyJST0xFX0FETUlOIiwiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9sb2dpbiIsImV4cCI6MTY0MDAyMjEwNH0.NJDmynzoLVPDhaXi15SK-62qEQ4CSneSkEBngj2cUgU"
}
```
## Users
### POST
#### Create user
http://localhost:8080/api/v1/users/create
#### Request body example
```json
{
  "name": "Test User",
  "username": "user",
  "password": "1234"
}
```
#### Add role to user
http://localhost:8080/api/v1/users/add/role?username=user&roleName=ADMIN
#### Delete role from user
http://localhost:8080/api/v1/users/delete/role?username=user&roleName=ADMIN
### GET
#### Get all users
http://localhost:8080/api/v1/users
#### Get user by ID
http://localhost:8080/api/v1/users/{id}
### DELETE
#### Delete user
http://localhost:8080/api/v1/users/{id}

## Roles
### POST
#### Create Role
http://localhost:8080/api/v1/roles/create
### GET
#### Get all roles
http://localhost:8080/api/v1/roles

## Customers
### POST
#### Create customer
http://localhost:8080/api/v1/customers/create
#### Request body example
```json
{
  "firstName": "Test",
  "lastName": "Customer",
  "email": "customer@test.com"
}
```
#### Add itinerary to customer
http://localhost:8080/api/v1/customers/add/itinerary?customerid=1&bookingid=1
#### Delete itinerary from customer
http://localhost:8080/api/v1/customers/delete/itinerary?customerid=1&bookingid=1
### GET
#### Get all customers
http://localhost:8080/api/v1/customers
#### Get customer by ID
http://localhost:8080/api/v1/customers/{id}
### DELETE
#### Delete customer
http://localhost:8080/api/v1/customers/{id}

## Bookings
### POST
#### Create booking
http://localhost:8080/api/v1/bookings/create
#### Request body example
```json
{
  "bookingCost": 100.0
}
```
### GET
#### Get all bookings
http://localhost:8080/api/v1/bookings
#### Get customer by ID
http://localhost:8080/api/v1/bookings/{id}
### DELETE
#### Delete customer
http://localhost:8080/api/v1/bookings/{id}


 


