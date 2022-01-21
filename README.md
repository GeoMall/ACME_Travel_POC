# ACME_Travel_POC

## ACME Travel POC Using Spring Framework

### The project is setup as follows:

#### Main Classes:

- For the API Layer, the class acmeTravelController.java *(found under the class path: \src\main\java\com\API_Technical_Exercise\ACME_Travel_POC\)* was created, which will be receiving the requests (Get, Post, Patch). 

- For the Service Layer, the class acmeTravelService.java *(found under the class path: \src\main\java\com\API_Technical_Exercise\ACME_Travel_POC\service\)* was created, which will be perfroming the business logic.

- For the Data Access Layer, the classes acmeTravelRepo.java, airportRepo.java, routeRepo.java *(found under the class path: \src\main\java\com\API_Technical_Exercise\ACME_Travel_POC\repo\)* were created. These classes will integrating with the database in order to fetch, update or inserting data from/into the database.

#### Test Classes:

Unit Tests and Mock Tests were constructed to make sure that the Data Access Layer and the Service Layers were performing correctly.

- The Data Access Layer testing can be found under the path *src\test\java\com\API_Technical_Exercise\ACME_Travel_POC\repo* where the functionality implemented within the repository classes is tested.

- The Service Layer testing can be found under the path *src\test\java\com\API_Technical_Exercise\ACME_Travel_POC\service* where the functionality implemented within the acmeTravelService.java is tested. Mocking was used to mock the repository classes to create a controlled environment. 
