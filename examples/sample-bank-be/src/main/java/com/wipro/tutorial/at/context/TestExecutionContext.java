package com.wipro.tutorial.at.context;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wipro.ta.samplebank.domain.Account;
import com.wipro.ta.samplebank.response.AccountDTO;
import com.wipro.ta.samplebank.response.ResponseDTO;

@Component
public class TestExecutionContext {

	private ResponseDTO<?> lastResponse;
	private List<AccountDTO> createdAccounts = new ArrayList<>();

	public void registerCreatedAccount(AccountDTO account) {
		createdAccounts.add(account);
	}

	public void removeCreatedAccount(Account account) {
		createdAccounts.remove(account);
	}

	public List<AccountDTO> getCreatedAccounts() {
		return createdAccounts;
	}

	public ResponseDTO<?> getLastResponse() {
		return this.lastResponse;
	}

	public void setResponseDTO(ResponseDTO<?> responseDTO) {
		this.lastResponse = responseDTO;
	}
}