## Coverage Report
#### Executed successfully tests: 30 (75%)
#### Failed tests: 2 (5%)
#### Omitted tests: 8 (20%)
 
### The tests were divided into three groups:
#### 1. Smoke
- Simple tests to check the basic functionality of the service.
- Executes at the start of the test run.
- Consists of 2 tests.
#### 2. Schema
- Test to check the JSON schema of the response body.
- Executes at the start of the test run.
- Consists of 1 test.
#### 3. Func
- Functional tests to check the functionality of the service in positive as well as negative ways.
- Executes after the 'smoke' group.
- Consists of 21 tests.
#### 4. Load
- Performance tests to check the functionality of the service under high load using a big amount of data. 
- Executes at the end of the test run as the heaviest in time. 
- Consists of 6 tests.

#### Failed tests are commented within the code and marked by 'TODO' notation.

#### Some of test cases were omitted as of less priority:
##### 1. Check sorting output of data set of different data type values:
- [false, true, -1, 1];
- [false, true, "a", "b"];
- [-1, 1, "a", "b];
- [1, [], null];
- ...
##### as not related to the specific functionality. 
##### 2. Check output of data set of one value of different data type:
- [false];
- [null];
- ["a"];
- [0];
##### as the test result is assumed the same as for data set with one number value.
