# About the project
This is a REST API wrapper which exposes 5 GET endpoints and 2 POST endpoints.
#### GET Endpoints
- /api/is-word    
[returns true/false if string is a single word]
- /api/is-number  
[returns true/false if string is a number]
- /api/is-lower   
[returns true/false if string is a lowercase word]
- /api/is-upper   
[returns true/false if string is an uppercase word]
- /api/statistics   
[returns body with statistics containg flags above and number of occurrences of certain character types]
#### Request parameters
- data - any textual data to be processed
- requestFormat - desired request format type returned by processing server [available: json, xml, txt, csv]
- responseFormat - desired output format type returned by this server [available: json, xml, txt, csv]

#### POST Endpoints
- /api/convert/statistics   
[accepts body with statistics in any format]    
[returns body with statistics in desired format specified by request parameter]
- /api/convert/boolean  
[accepts body with boolean in any format]    
[returns true/false in desired format specified by request parameter]
#### Request parameters
- responseFormat - desired output format type returned by this server [available: json, xml, txt, csv]

## Tech stack
- Spring Boot 2.5.6
- Java 11

## Building the project
Clone the project and use Maven to build the app.
```
$ mvn clean install
```

## Examples of use
After you deploy the application (port 8082 by default) you can send requests as follows:

### Example #1
```
GET http://localhost:8082/api/is-word?data=eXAMPLE12$&requestFormat=csv&responseFormat=xml
```
```
HTTP 200 OK
<Boolean>false</Boolean>
```
### Example #2
```
GET http://localhost:8082/api/statistics?data=eXAMPLE12$&requestFormat=xml&responseFormat=json
```
```
HTTP 200 OK
{
    "isWord": false,
    "isNumber": false,
    "isLower": false,
    "isUpper": false,
    "characterCount": 10,
    "letterCount": 7,
    "digitCount": 2,
    "lowercaseLetterCount": 1,
    "uppercaseLetterCount": 6,
    "whitespaceCount": 0,
    "specialCharactersCount": 1
}
```
### Example #3
```
GET http://localhost:8082/api/statistics?data=eXAMPLE12$&requestFormat=txt&responseFormat=csv
```
```
HTTP 200 OK
characterCount,digitCount,isLower,isNumber,isUpper,isWord,letterCount,lowercaseLetterCount,specialCharactersCount,uppercaseLetterCount,whitespaceCount
10,2,false,false,false,false,7,1,1,6,0

```
### Example #4
```
GET http://localhost:8082/api/statistics?data=eXAMPLE12$&requestFormat=csv&responseFormat=txt
```
```
HTTP 200 OK
isWord: false
isNumber: false
isLower: false
isUpper: false
characterCount: 10
letterCount: 7
digitCount: 2
lowercaseLetterCount: 1
uppercaseLetterCount: 6
whitespaceCount: 0
specialCharactersCount: 1
```
### Example #5
```
GET http://localhost:8082/api/statistics?data=eXAMPLE12$&requestFormat=WRONG&responseFormat=txt
```
```
400 Bad Request
[400] during [GET] to [http://localhost:8081/api/statistics?data=eXAMPLE12%24&format=WRONG] [StringApiWrapperClient#getStatistics(Map)]: [Failed for: [WRONG]. Given format is not supported]
```
### Example #6
```
GET http://localhost:8082/api/statistics?data=eXAMPLE12$&requestFormat=txt&responseFormat=WRONG
```
```
400 Bad Request
Failed for: [WRONG]. Given format is not supported
```
