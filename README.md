# ContractFirstApiTestsProject

Prerequisites

To run the test cases, ensure the following services are running:

Prism Mocking Service: This service mocks the OpenAPI definition.

Nginx Proxy Server: Acts as a reverse proxy for API requests.

How to Run


Start the Prism mocking service container for the OpenAPI definition.
Start the Nginx proxy server container.
Once both services are up and running, execute the test cases by running the Runner class located at: 
/src/test/java/API/runners/Runner.java.