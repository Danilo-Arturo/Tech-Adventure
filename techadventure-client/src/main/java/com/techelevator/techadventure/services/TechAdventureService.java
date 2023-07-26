package com.techelevator.techadventure.services;

import java.math.BigDecimal;

import com.techelevator.techadventure.model.Interaction;
import com.techelevator.techadventure.model.User;

import com.techelevator.util.BasicLogger;
import io.cucumber.java.an.E;
import okhttp3.ResponseBody;

import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TechAdventureService {

    private final String API_BASE_URL;
    private final RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;
    public void setAuthToken(String authToken){this.authToken = authToken;}

    
    
    public TechAdventureService(String API_BASE_URL) {
        this.API_BASE_URL = API_BASE_URL;

    }

    private HttpEntity<Interaction> makeTransactionEntity(Interaction interaction) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(interaction, headers);
    }

    //HttpEntity<Void> -Includes only authtoken (credentials)
    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

    public BigDecimal getPoints(){
        BigDecimal balance = null;
        try{
            ResponseEntity<BigDecimal> response = restTemplate.exchange(API_BASE_URL + "/balance", HttpMethod.GET, makeAuthEntity(), BigDecimal.class);
            balance = response.getBody();
        }catch(RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }



        return balance;
    }
}
