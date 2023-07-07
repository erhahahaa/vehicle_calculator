# Java MySQL CRUD - 4 Stroke Vehicle Measurement Tracking System

This project is developed in Java and MySQL, and it allows users to calculate various measurements related to vehicles, such as combustion, transmission, air-fuel ratio, and electrical values. The system also provides the functionality to print measurement outputs and store them in a database for future reference.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Database Schema](#database-schema)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Vehicle Measurement Tracking System is designed to assist users in tracking and managing various measurements of their vehicles. By using this system, users can perform calculations related to combustion, transmission, air-fuel ratio, and electrical values. The system also offers the ability to print measurement outputs and store them in a MySQL database, allowing users to keep track of the previous measurements for each vehicle.

## Features

1. **Combustion Calculation:** The system enables users to calculate combustion-related measurements, such as fuel efficiency, engine power, and exhaust emissions.
2. **Transmission Calculation:** Users can calculate various transmission-related values, such as gear ratios, torque multiplication, and vehicle speed.
3. **Air-Fuel Ratio Calculation:** The system allows users to determine the air-fuel ratio based on inputs such as fuel flow rate and air mass flow rate.
4. **Electrical Calculation:** Users can calculate electrical measurements, including voltage, current, and power.
5. **Print Measurement Output:** Users have the option to print the calculated measurement outputs for future reference.
6. **Database Storage:** The system stores the measurement outputs in a MySQL database, allowing users to track and manage the measurements for each vehicle.

## Technologies Used

The following technologies were used in the development of this project:

- Java: The core programming language used for implementing the system's functionalities.
- MySQL: The relational database management system used for storing measurement outputs.
- JDBC: The Java Database Connectivity API used for connecting to the MySQL database. 

## Installation

To install and set up the Vehicle Measurement Tracking System, follow these steps:

1. Clone the repository: `git clone <repository-url>`
2. Made a new db named `kalkulator_mobil` and import the provided SQL schema.
3. Configure the database connection settings in the project's configuration file.
4. Build the project using a Java IDE, in this development i used `Netbeans`. 
5. Run the application to start using the system.

## Usage

Once the Vehicle Measurement Tracking System is set up, users can perform the following actions:
1. Input required user info like name, license plate, etc.
2. Enter the required inputs for the desired measurement calculation.
3. Choose the type of measurement to calculate (combustion, transmission, air-fuel ratio, or electrical).
4. View the calculated measurement output on the `Print` tab. 
5. The measurement outputs will be stored in the database, allowing users to track and manage them.

 
