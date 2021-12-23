package com.aneeque.backendservice.seerbit.service;

import com.aneeque.backendservice.seerbit.response.TransactionStatusResponse;
import com.aneeque.backendservice.seerbit.request.GetTokenRequest;
import com.aneeque.backendservice.seerbit.response.GetTokenResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Okala Bashir .O.
 */

@Slf4j
@Service
public class SeerBitService {


    @Value("${payment-platform.seerbit.get-token-url}")
    private String GET_TOKEN_URL;

    @Value("${payment-platform.seerbit.check-transaction-status-url}")
    private String CHECK_PAYMENT_TRANSACTION_STATUS_URL;

    @Value(value = "${payment-platform.seerbit.public-key}")
    public String PUBLIC_KEY;

    @Value(value = "${payment-platform.seerbit.private-key}")
    public String PRIVATE_KEY;

    public GetTokenResponse getToken(){
        log.info("getting token...");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        GetTokenRequest getTokenRequest = new GetTokenRequest();

        HttpEntity<GetTokenRequest> request = new HttpEntity<>(getTokenRequest, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(GET_TOKEN_URL, HttpMethod.POST, request, String.class);
        Gson gson = new Gson();
        System.out.println("the response body: "+response.getBody());

        String jsonResponse = response.getBody().toString().replace("jsonp (", "").replace(")", "").replace(",\"responseParams\":{}", "").trim();
        GetTokenResponse getTokenResponse = gson.fromJson(jsonResponse, GetTokenResponse.class);
        System.out.println(getTokenResponse);
        return getTokenResponse;

    }

    public Object checkTransactionStatus(String paymentReference){

        String token = getToken().getData().getEncryptedSecKey().getEncryptedKey();
        log.info("Checking transaction status...");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<String> response = restTemplate.exchange(
                    CHECK_PAYMENT_TRANSACTION_STATUS_URL + paymentReference, HttpMethod.GET, new HttpEntity<Object>(headers),
                    String.class);
            Gson gson = new Gson();

            String jsonResponse = response.getBody().toString().replace("jsonp (", "").replace(")", "").replace(",\"responseParams\":{}", "").trim();
//        System.out.println(response.getBody().getData().getMessage());
            TransactionStatusResponse transactionStatusResponse = gson.fromJson(jsonResponse, TransactionStatusResponse.class);

            return transactionStatusResponse;

        }catch(HttpStatusCodeException e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }

    }



}
