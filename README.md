## Backend Application for Trading Company

================

This project is a backend application built for trading companies which can order and sell products from provider to end customers. The outcomes of the project is providing a list of API for mulpiple uasges regarding to business requirements.

## Features 

- Customers can be searched by name, address, phone. 

- Note and invoice can be filtered by date. 

Some additional REST API:

- List all inventory note, sale invoice by a period: start date and end date

- All sales invoice by a customer and by a sale staff in a period: start date and end date. 

- Revenue, revenue by a customer, revenue by a sale staff. Input params: start date and end date.  Revenue = total value of all sales invoices in a period. Total value of an invoice = all products quantity * price.

- Inventory of all products in warehouse at a particular date. For example

## Tools 
- Back-end: 
  - Spring Boot 2.4.5
  - Hibernate 5.4.30
  - Apache Tomcat 9.0.45
- Database: 
  - PostgresSQL 13.2
  
## Installation

- Ensure that you have install Maven, Java and Postgres 
- Chagne data source in config/AppConfig with your own PostgreSQL username and password

- Starting server: Run TradingBackendApplication

## Credits

- Nguyen Quang Linh (s3697110)
- Nguyen Quang Huy (s3697272)



