package com.nlu.digital.conversationapi.dialogflow.client;

import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;

public class MyActionsApp extends DialogflowApp {

	@ForIntent("Default Welcome Intent")
	public ActionResponse welcome(ActionRequest request) {
		ResponseBuilder responseBuilder = getResponseBuilder(request);
		responseBuilder.add("Welcome to my app");
		return responseBuilder.build();
	}
}