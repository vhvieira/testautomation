package com.wipro.tutorial.at.lifecycle;

import java.util.List;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterScenario.Outcome;
import org.jbehave.core.annotations.ScenarioType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wipro.ta.samplebank.response.AccountDTO;
import com.wipro.tutorial.at.context.TestExecutionContext;
import com.wipro.tutorial.at.service.AccountServiceTestClient;

@Component
public class ScenarioLifecycle {

	@Autowired
	private TestExecutionContext context;

	@Autowired
	private AccountServiceTestClient accountService;

	@AfterScenario(uponType = ScenarioType.ANY, uponOutcome = Outcome.ANY)
	public void tearDownScenario() {
		
		List<AccountDTO> accounts = context.getCreatedAccounts();
		
		for (AccountDTO account : accounts) {
			if (account != null) {
				accountService.deleteAccount(account.getOwnerCpf());
			}
		}
		
		context.getCreatedAccounts().clear();
	}
}