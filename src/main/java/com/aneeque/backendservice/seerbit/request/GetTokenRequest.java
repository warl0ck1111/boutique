package com.aneeque.backendservice.seerbit.request;

import lombok.Data;

/**
 * @author Okala Bashir .O.
 */

@Data
public class GetTokenRequest {
    private String key;

    public GetTokenRequest() {
        this.key= "SBTESTSECK_HQuL3EJpMfOdAPQaASwdC3Y2aZxDD8fUhvHKLrKo.SBTESTPUBK_qv5b1rVlVVmxFPiPmr5IHtQIvvYAZ1Yg";
    }
}
