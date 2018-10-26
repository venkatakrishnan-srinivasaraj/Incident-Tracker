package com.venkat.hackuta.incidenttracker.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@DynamoDBTable(tableName = "incident")
@ToString
@Getter
@Setter
public class Incident {

  @DynamoDBHashKey
  private String id;
  @DynamoDBAttribute(attributeName="sensorId")
  private String sensorId;
  @DynamoDBAttribute(attributeName="eventDateTime")
  private String eventDateTime;
//  @DynamoDBAttribute
//  private Impact impact;
//  @DynamoDBAttribute
//  private SpeedOfVehicle speedOfVehicle;
  private Location location;
}
