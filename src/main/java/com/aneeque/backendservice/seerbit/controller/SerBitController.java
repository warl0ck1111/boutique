package com.aneeque.backendservice.seerbit.controller;

import com.aneeque.backendservice.dto.response.ApiResponse;
import com.aneeque.backendservice.seerbit.service.SeerBitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Okala Bashir .O.
 */
@RestController
@RequestMapping("/api/v1/seerbit/")

public class SerBitController {

    @Autowired
    private SeerBitService seerBitService;

    @GetMapping("get-token")
    public ResponseEntity<ApiResponse> getToken() {
        return ResponseEntity.ok(new ApiResponse(seerBitService.getToken()));
    }

    @GetMapping("check-status/{paymentReference}")
    public ResponseEntity<ApiResponse> checkTransactionStatus(@PathVariable String paymentReference) {
        return ResponseEntity.ok(new ApiResponse(seerBitService.checkTransactionStatus(paymentReference)));
    }

}
