package com.wipro.tutorial.at.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wipro.ta.samplebank.response.AccountDTO;
import com.wipro.ta.samplebank.response.ResponseDTO;
import com.wipro.tutorial.at.context.TestExecutionContext;

@Component
public class AccountServiceTestClient {

	private static final String AccountDTO_CREATE_URL = "accounts/?ownerCpf={ownerCpf}";
	private static final String AccountDTO_DELETE_URL = "accounts/?ownerCpf={ownerCpf}";
	private static final String AccountDTO_DEPOSIT_URL = "accounts/{ownerCpf}/deposit?value={value}";
	private static final String AccountDTO_GET_URL = "accounts/{ownerCpf}";
	private static final String AccountDTO_WITHDRAW_URL = "accounts/{ownerCpf}/withdraw?value={value}";
	private static final String AccountDTO_TRANSFER_URL = "accounts/{ownerCpf}/transfer?targetCpf={targetCpf}&value={value}";

	@Value("${sbservice.api.url}")
	private String SAMPLE_BANK_API_URL;

	@Autowired
	private TestExecutionContext context;

	@Autowired
	private RestTemplate restTemplate;

	public ResponseDTO<AccountDTO> createAccount(String ownerCpf) {

		String uri = SAMPLE_BANK_API_URL.concat(AccountDTO_CREATE_URL);

		ResponseDTO<AccountDTO> responseDTO = null;

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		Map<String, String> params = Collections.singletonMap("ownerCpf", ownerCpf);

		ParameterizedTypeReference<ResponseDTO<AccountDTO>> typeRef = new ParameterizedTypeReference<ResponseDTO<AccountDTO>>() {
		};

		ResponseEntity<ResponseDTO<AccountDTO>> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity,
				typeRef, params);

		responseDTO = response.getBody();

		if (responseDTO.getData() != null) {
			AccountDTO AccountDTO = responseDTO.getData();

			context.registerCreatedAccount(AccountDTO);
		}

		context.setResponseDTO(responseDTO);

		return responseDTO;
	}

	public ResponseDTO<AccountDTO> getAccount(String ownerCpf) {

		String uri = SAMPLE_BANK_API_URL.concat(AccountDTO_GET_URL);

		ResponseDTO<AccountDTO> responseDTO = null;

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		Map<String, String> params = Collections.singletonMap("ownerCpf", ownerCpf);

		ParameterizedTypeReference<ResponseDTO<AccountDTO>> typeRef = new ParameterizedTypeReference<ResponseDTO<AccountDTO>>() {
		};

		ResponseEntity<ResponseDTO<AccountDTO>> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity,
				typeRef, params);

		responseDTO = response.getBody();

		context.setResponseDTO(responseDTO);

		return responseDTO;
	}

	public ResponseDTO<AccountDTO> makeDeposit(String ownerCpf, String value) {

		String uri = SAMPLE_BANK_API_URL.concat(AccountDTO_DEPOSIT_URL);

		ResponseDTO<AccountDTO> responseDTO = null;

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		Map<String, String> params = new HashMap<>();

		params.put("ownerCpf", ownerCpf);
		params.put("value", value);

		ParameterizedTypeReference<ResponseDTO<AccountDTO>> typeRef = new ParameterizedTypeReference<ResponseDTO<AccountDTO>>() {
		};

		ResponseEntity<ResponseDTO<AccountDTO>> response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity,
				typeRef, params);

		responseDTO = response.getBody();

		context.setResponseDTO(responseDTO);

		return responseDTO;
	}

	public ResponseDTO<AccountDTO> makeTransfer(String ownerCpf, String destCpf, String value) {

		String uri = SAMPLE_BANK_API_URL.concat(AccountDTO_TRANSFER_URL);

		ResponseDTO<AccountDTO> responseDTO = null;

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		Map<String, String> params = new HashMap<>();

		params.put("ownerCpf", ownerCpf);
		params.put("targetCpf", destCpf);
		params.put("value", value);

		ParameterizedTypeReference<ResponseDTO<AccountDTO>> typeRef = new ParameterizedTypeReference<ResponseDTO<AccountDTO>>() {
		};

		ResponseEntity<ResponseDTO<AccountDTO>> response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity,
				typeRef, params);

		responseDTO = response.getBody();

		context.setResponseDTO(responseDTO);

		return responseDTO;
	}

	public ResponseDTO<AccountDTO> makeWithdrawal(String ownerCpf, String value) {

		String uri = SAMPLE_BANK_API_URL.concat(AccountDTO_WITHDRAW_URL);

		ResponseDTO<AccountDTO> responseDTO = null;

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		Map<String, String> params = new HashMap<>();

		params.put("ownerCpf", ownerCpf);
		params.put("value", value);

		ParameterizedTypeReference<ResponseDTO<AccountDTO>> typeRef = new ParameterizedTypeReference<ResponseDTO<AccountDTO>>() {
		};

		ResponseEntity<ResponseDTO<AccountDTO>> response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity,
				typeRef, params);

		responseDTO = response.getBody();

		context.setResponseDTO(responseDTO);

		return responseDTO;
	}

	public ResponseDTO<AccountDTO> deleteAccount(String ownerCpf) {

		String uri = SAMPLE_BANK_API_URL.concat(AccountDTO_DELETE_URL);

		ResponseDTO<AccountDTO> responseDTO = null;

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		Map<String, String> params = Collections.singletonMap("ownerCpf", ownerCpf);

		ParameterizedTypeReference<ResponseDTO<AccountDTO>> typeRef = new ParameterizedTypeReference<ResponseDTO<AccountDTO>>() {
		};

		ResponseEntity<ResponseDTO<AccountDTO>> response = restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity,
				typeRef, params);

		responseDTO = response.getBody();

		context.setResponseDTO(responseDTO);

		return responseDTO;
	}
}