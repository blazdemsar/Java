package com.blazdemsar.integration;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RoleRestClient {
	
	public JSONObject getAllRoles() throws JSONException, JsonProcessingException;
	
}
