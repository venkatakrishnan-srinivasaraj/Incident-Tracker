# PotholeAlerter
An application to signal alerts to drivers who come near a proximity of a pothole

Part 1:
The vehicles have to report the incidents and for that purpose we will have a client application reporting that to the central
server. 

Client Application:
We assume that client application will be developed by other developer which reports the incident in the below format
to our Incident tracker application.

Server Application to record incidents:
The sensors attached in the vehicles will send the details to the Incident tracking Application which takes the input
about the incident and saves it to the database.

The event will be of the below format
```json
{
  "sensorId": "idOfTheSensor",
  "eventDateTime": "2018-05-17T17:00:00Z",
  "impact": {
    "sensorValue": 9.22,
    "unit": "CM"
  },
  "speedOfVehicle": {
    "unit": "MPH",
    "value": 23
  },
  "location": {
    "latitude": 52.123,
    "longitude": 29.89,
    "altitude": 23.67
  }
}
```
Part 2:
The users of the application have to open our client application that sends the current location to the server and if it is near
the proximity of any event we will send an alert message to the user

Client Application:
Client application is an android application which sends the current location to the Pothole alerter application and if it receives
an alert it will alert the user by displaying an alert message and giving a voice feedback.

Server Application:
This application will check if there are any incidents are near the proximity of the location reported by the user. If so it will
send an alert to the client application. It will also track the number of vehicles impacted by this one so that 

The event will be of the below format
```json
{
  "userId": "idOfTheUser",
  "eventDateTime": "2018-05-17T17:00:00Z",
  "speedOfVehicle": {
    "unit": "MPH",
    "value": 23
  },
  "location": {
    "latitude": 52.123,
    "longitude": 29.89,
    "altitude": 23.67
  }
}
```

<strong>Part 3:</strong>
This is a reporting application which will show the concerned person a map with markups on the potholes on the location and 
the number of users affected by it. A lot of metrics etc.....So that he can take up the incident with maximum impact first and fix 
that.

Part 4:
A Blockchain application that will take an action if any reported incident is unattended then fine the concerned party with fine for
not maintaining it properly.
