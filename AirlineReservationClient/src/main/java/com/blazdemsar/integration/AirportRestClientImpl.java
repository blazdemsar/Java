package com.blazdemsar.integration;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AirportRestClientImpl implements AirportRestClient {
	
	private static String USER_NAME ="blaz";
	private static String PASSWORD ="blaz";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public JSONObject getAllAirports() throws JSONException, JsonProcessingException {

		System.out.println("Request for getting all airports received .............");

		String uri = "http://localhost:8080";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> responseEntity = restTemplate.postForEntity(uri +"/getAllAirports", request, Object[].class);
		Object[] listOfAirports = responseEntity.getBody();

		JSONArray jsonArray = new JSONArray();

		for(Object obj : listOfAirports){
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(obj));
			jsonArray.put(jsonObj);
		}

		JSONObject jsonResponseObject = new JSONObject();
		jsonResponseObject.put("listOfAirports", jsonArray);

		return jsonResponseObject;
		
	}
	
	public static HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		String auth = USER_NAME + ":"+PASSWORD;
	//	byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF8")));
		String base64Credential =   Base64.encodeBase64String(auth.getBytes());
		String authHeader = "Basic "+ base64Credential;
		headers.set("Authorization", authHeader);
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
	
}
