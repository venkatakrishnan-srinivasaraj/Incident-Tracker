package com.venkat.hackuta.incidenttracker.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venkat.hackuta.incidenttracker.model.Incident;
import com.venkat.hackuta.incidenttracker.repository.IncidentRepository;

@RestController
public class IncidentController {

  @Autowired
  private IncidentRepository incidentRepository;

  @PostMapping(value = "/incident")
  public void postIncident(@RequestBody Incident incident) {
    incident.setId(UUID.randomUUID().toString());
    System.out.println(incident);
    incidentRepository.saveIncident(incident);
  }
  
  @PostMapping(value = "/incidentGeolocation")
  public void postIncidentWithGeoLocation(@RequestBody Incident incident) {
    incident.setId(UUID.randomUUID().toString());
    System.out.println(incident);
    incidentRepository.saveIncidentWithGeoManager(incident);
  }
  
  @PostMapping(value = "/getIncident")
  public void getIncidentsNear(@RequestBody Incident incident) {
    System.out.println(incident);
    incidentRepository.getIncidentsNear(incident);
  }

}
