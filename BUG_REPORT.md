## Bug ID: QA-1
### Summary: Sorting algorithm always sets zero at first place
#### Severity: 
HIGH 
#### Priority: 
____
#### Assigned to: 
____
#### Reported By: 
Yevhen Bohaienko
#### Reported On: 
July 24, 2020 
#### Reason: 
Defect
#### Status: 
New
#### Environment: 
- Server: nginx/1.18.0 (Ubuntu)
- Clien: MacOS, Postman, 

#### Description:
When POST to the sorting URL data set with the zero ('0') value, it put 
after sorting at first place no matter of numbers with less values

#### Steps To Reproduce:
##### 1) Prepare the payload set of number including zero and lesser, e.g.
```
{ "numbers": [0, 1, -1] }
```
##### 2) Send POST request to the URL:
```
http://ec2-54-219-199-55.us-west-1.compute.amazonaws.com/api/sort/
```
with the prepared payload in body.
##### 3) Observe the body of the response.

#### Expected Result:
``` 
{
    "numbers": [
        -1,
        0,
        1
    ]
}
```

#### Actual Result:
```
{
    "numbers": [
        0,
        -1,
        1
    ]
}
```
