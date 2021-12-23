package com.aneeque.backendservice.seerbit.model;

import lombok.Data;

@Data
public class Payments{
	private String country;
	private String reason;
	private String mobilenumber;
	private String channelType;
	private String publicKey;
	private String type;
	private String paymentType;
	private String mode;
	private String gatewayCode;
	private String sourceIP;
	private String cardBin;
	private String processorMessage;
	private String currency;
	private String gatewayMessage;
	private String email;
	private String productDescription;
	private String redirectLink;
	private String deviceType;
	private double amount;
	private String redirecturl;
	private String productId;
	private String maskedPan;
	private String paymentReference;
	private String lastFourDigits;
	private String gatewayref;
	private String callbackurl;
	private String processorCode;


}
