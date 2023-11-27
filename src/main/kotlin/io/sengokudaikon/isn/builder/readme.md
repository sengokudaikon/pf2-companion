# Fixture Loader for PF2 Companion App

The Fixture Loader is a crucial service toolset responsible for dynamically filling the Database module with test data.
It is designed for use in development and testing environments and is not intended for production use.

## Features

### Data Generation

The Fixture Loader can automatically generate data from shared databases (VTT, aonprd), helping to validate database
operations under realistic conditions.

### Database Population

The generated test data is used to populate the Database module, thereby supporting thorough testing and development of
this component.

### Scalability

The toolset can generate and load a large volume of data, supporting scalability and performance testing of the Database
module.

### Consistency

It ensures that the Database module starts each test cycle with a well-defined, consistent set of data.

## Dependencies

Depends on [Database](../compendium/readme.md) for data storage, and providing entities and objects to populate.