# Database Module for PF2 Companion App

This module is a central part of the PF2 Companion App functioning as the main component for data handling and
persistence.

## Features

### Adapters

Adapters enable interaction between the user interfaces and the database module, facilitating actions such as data
presentation and user input.

### Domain

The domain contains entities and objects pivotal to the functioning of the application, defining the core structure and
logic of the system.

### Infrastructure

Infrastructure comprises the external services and configurations necessary for the processing, transmission, and
storage of data.

### Operations

Also known as the CQRS model, this component of the system establishes a clear boundary between the Query (read) and
Command (write) actions thus ensuring their independent scalability and performance optimization.

### Persistence

The Persistence component manages data storing, caching, and database connections. This ensures efficient retrieval and
storage of data.

### Ports

Ports facilitate the internal communication within the system, enabling interaction between the use cases and the rest
of the system.

### Usecases

Usecases encapsulate specific functionalities of the application, spanning from user requirements up to their execution.

## Dependencies

MongoDB