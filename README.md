# cordial-demo-test
 
REST API automated tests demo for Cordial.com
 
### Prerequisites
* **JDK = 1.8.0_x**;

## Build
- Clone project
```
git clone https://gitlab.1touch.corp/qa/e2e-tests.git
```
- Run command
```
mvn clean install -Dsurefire.test.skip=true -Dfailsafe.test.skip=true 
```
 
## Run
- To run tests execute in parent dir:
 ```
 mvn clean install <OPTIONS>
 ```
#### Run options:
 - To run all tests just run the `mvn` command without options;
 - To run only one group tests add to the `mvn` command `-Dgroups=<groupName>`;
 
#### Test groups list by modules:
 - "Smoke" - smoke tests to start with - `-Dgroups=rm-pipeline` 
 - "Func" - functional tests - `-Dgroups=rm` 
 - "Load" - performance tests - `-Dgroups=nlp`
 
### Ports exposed by component
The component does not need to be exposed
 
### Service Endpoints
The TestNG report will be generated into file:
```
[target/surefire-reports/index.html](../target/surefire-reports/index.html)
```
