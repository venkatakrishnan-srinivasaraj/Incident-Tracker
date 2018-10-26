package com.venkat.hackuta.incidenttracker.repository;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.geo.GeoDataManager;
import com.amazonaws.geo.GeoDataManagerConfiguration;
import com.amazonaws.geo.model.GeoPoint;
import com.amazonaws.geo.model.PutPointRequest;
import com.amazonaws.geo.model.QueryRadiusRequest;
import com.amazonaws.geo.model.QueryRadiusResult;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.venkat.hackuta.incidenttracker.model.Incident;

@Component
public class IncidentRepository {

  @Autowired
  private GeoDataManager geoDataManager;

  @Autowired
  private DynamoDBMapper dynamoDbMapper;

  public void saveIncidentWithGeoManager(Incident incident) {
    GeoPoint geoPoint = new GeoPoint(incident.getLocation().getLatitude(), incident.getLocation().getLongitude());
    AttributeValue rangeKeyValue = new AttributeValue().withS(UUID.randomUUID().toString());
    AttributeValue id = new AttributeValue().withS(UUID.randomUUID().toString());
    System.out.println(rangeKeyValue);
    PutPointRequest putPointRequest = new PutPointRequest(geoPoint, rangeKeyValue);
    putPointRequest.getPutItemRequest().getItem().put("id", id);
    geoDataManager.putPoint(putPointRequest);
  }
  
  public void getIncidentsNear(Incident incident) {
    GeoPoint centerPoint = new GeoPoint(incident.getLocation().getLatitude(), incident.getLocation().getLongitude());
    QueryRadiusRequest queryRadiusRequest = new QueryRadiusRequest(centerPoint, 1000);
    QueryRadiusResult result = geoDataManager.queryRadius(queryRadiusRequest);
    GeoDataManagerConfiguration config = geoDataManager.getGeoDataManagerConfiguration();

    for (Map<String, AttributeValue> item : result.getItem()) {
//        System.out.println(item);      
        String geoJsonString = item.get(config.getGeoJsonAttributeName()).getS();
        System.out.println(geoJsonString);
    }
  }

  public void saveIncident(Incident incident) {
    dynamoDbMapper.save(incident);
  }

}
