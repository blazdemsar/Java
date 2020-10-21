package com.blazdemsar.integration;


import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserRestClient {
	
	public JSONObject getAllUsers() throws JSONException, JsonProcessingException;
	
}
