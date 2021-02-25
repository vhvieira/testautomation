package com.nlu.digital.conversationapi.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nlu.digital.conversationapi.dialogflow.client.MyActionsApp;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/{version}/fulfillment")
@Slf4j
class FulfillmentController {
	
	//Actions app
	private final MyActionsApp actionsApp = new MyActionsApp();
	
    @GetMapping("/")
    String serveAck() {
        return "App is listening but requires valid POST request to respond with Action response.";
    }

    @PostMapping(value = "/", produces = { "application/json" })
    String serveAction(@RequestBody String body, @RequestHeader Map<String, String> headers) {
        try {
            return actionsApp.handleRequest(body, headers).get();
        } catch (Exception e) {
            return handleError(e);
        }
    }
    
    @PostMapping("/generic")
    public String getTest1(HttpEntity<String> httpEntity) {

        String reqObject = httpEntity.getBody();
        log.debug("request json object = "+reqObject);

        //Get the action
        JSONObject obj = new JSONObject(reqObject);
        String action = obj.getJSONObject("result").getString("action");

        //Get the parameters
        JSONObject params = obj.getJSONObject("result").getJSONObject("parameters");
        String response = "Hello from Java."; 
        return "{'speech': '"+response+"', 'displayText':'"+response+"'}";
    }

    private String handleError(Exception e) {
        log.error("Error in App.handleRequest ", e);
        return "Error handling the intent - " + e.getMessage();
    }
}
