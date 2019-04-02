# Cloud Computing Assignment Number 2: Restful Application Programming Interface
In Assignment number 2 the objective is to develop Restful WebService to host the weather Information.
This homework assignment requires to add a REST API endpoints to the application, to accept the input
data as input parameters to either a GET or POST operation, that will then return JSON result in plain-text to the user.


## Important Information about the web application
Here the webapplication showcases the data of Cincinnati weather for last three years. 
A csv file is provided which gives the data for the Cincinnati's weather for last three years.
The csv file consist of three columns which are 
1) DATE - YYYYMMDD format
2) TMAX - Daily Max Temperature 
3) TMIN -  Daily Min Temperature

This Data is converted into JSON format and consumed by the webservices and can be accessed upon request.

## Getting Started:

### 1. /historical/:
Method that will be used here will be **GET** 
The expected output is to display all the DATES for which the weather report is available.

To test the **/historical/** end point use the following link:
### http://ec2-54-186-40-227.us-west-2.compute.amazonaws.com:8080/Rest_proj/rest/supersumoz/historical

### 2. /historical/dateYYYYMMDD:
Method that will be used here is **GET**
The expected output is to display the weather report for the given particular date

To test the **/historical/20130101** end point use the following link:
### http://ec2-54-186-40-227.us-west-2.compute.amazonaws.com:8080/Rest_proj/rest/supersumoz/historical/20130101

### 3. /forecast/dateYYYYMMDD:

Method that will be used here is **GET**
The expected output is to display the weather report for next seven days

To test the **/forecast/20130101:

### http://ec2-54-186-40-227.us-west-2.compute.amazonaws.com:8080/Rest_proj/rest/supersumoz/forecast/20130101

### Prerequisities

Internet connectivity, 
Browser

## References

1. Deploy Java Web App on Amazon Linux EC2 Instance Using Tomcat 
https://www.youtube.com/watch?v=_d-c9uGcUrU

2. Deploy war file to Docker Image
http://codeomitted.com/deploy-war-file-to-docker-image/

## Author

* **Sumit Ghewade** - *Initial work* - [ghewadesumit](https://github.com/ghewadesumit)




