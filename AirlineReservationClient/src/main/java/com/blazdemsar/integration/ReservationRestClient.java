package com.blazdemsar.integration;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ReservationRestClient {
	
	public JSONObject getAllReservations() throws JSONException, JsonProcessingException;
	
}
