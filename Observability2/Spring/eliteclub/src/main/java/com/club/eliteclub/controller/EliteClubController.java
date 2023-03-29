package com.club.eliteclub.controller;

import com.club.eliteclub.model.ClubDTO;
import com.club.eliteclub.service.EliteClubService;

import io.micrometer.observation.Observation;
import jakarta.servlet.http.HttpServletRequest;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EliteClubController {
    private static final Logger LOG = LoggerFactory.getLogger(EliteClubController.class);

    private EliteClubService eliteClubService;

  

    @Autowired
    public void setEliteClubService(EliteClubService eliteClubService) {
        this.eliteClubService = eliteClubService;
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String sayHello(HttpServletRequest request) {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        Map<String, String> headers = Collections.list(httpRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(h -> h, httpRequest::getHeader));

        Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();

        it.forEachRemaining( entry -> System.out.println(entry.getKey() + " : "+entry.getValue()));

        return "Hello There";
    }

    @GetMapping(path = "/club", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClubDTO> clubs() {
       List<ClubDTO> clubs= eliteClubService.getAll();

       
       
    //    MDC.put("session_id", request.getSession().getId());
    //    LOG.info("Clubs are : {}", clubs);
    //    MDC.clear();
       
       
        return clubs;
    }

    @PostMapping(path = "/club", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewClub(@RequestBody ClubDTO clubDTO) {
        eliteClubService.addClub(clubDTO.getClubName());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping(path = "/club/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClubDTO> searchClub(@RequestParam String clubName) {
        return eliteClubService.searchClub(clubName);
    }

    @GetMapping(path = "/club/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClubDTO clubWithId(@PathVariable long id) {
        return eliteClubService.getByID(id);
    }


    @DeleteMapping(path = "/club/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteClub(@PathVariable long id) {
        eliteClubService.deleteClub(id);
    }

    @PutMapping(path = "/club/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClubDTO updateClub(@PathVariable long id, @RequestBody ClubDTO clubDTO) {
        return eliteClubService.updateClub(id, clubDTO);
    }
}
