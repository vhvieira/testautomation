package com.wipro.tutorial.at.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wipro.ta.samplebank.response.AccountDTO;
import com.wipro.ta.samplebank.response.ResponseDTO;
import com.wipro.tutorial.at.context.TestExecutionContext;
import com.wipro.tutorial.at.service.AccountServiceTestClient;

@Component
public class AccountTransactionSteps {
	
	@Autowired
	private TestExecutionContext context;
	
	@Autowired
	private AccountServiceTestClient accountService;
	
	@Given("an account for cpf number $cpf exists in the system")
	public void givenAnAccountExists(@Named("cpf") String cpf) {
		accountService.createAccount(cpf);
	}
	
	@Given("an account for cpf number $cpf that has the balance $previousBalance")
	public void givenAnAccountExistsAndHasBalnce(@Named("cpf") String cpf, 
			@Named("previousBalance") String previousBalance) {
		accountService.createAccount(cpf);
		accountService.makeDeposit(cpf, previousBalance);
	}
	
	@When("a deposit of $ammount is made for the account $cpf")
	public void whenMakeADeposit(@Named("ammount") String ammount, @Named("cpf") String cpf) {
		accountService.makeDeposit(cpf, ammount);
	}
	
	@When("a withdrawal of $amount is made for the account $cpf")
	public void whenMakeAWithdrawal(@Named("ammount") String ammount, @Named("cpf") String cpf) {
		accountService.makeWithdrawal(cpf, ammount);
	}
	
	@When("the account $cpf informations are requested")
	public void whenRequestAccountInfo(@Named("cpf") String cpf) {
		accountService.getAccount(cpf);
	}	
	
	@When("a transfer of $ammount is made from the account $cpfOrigin to the account $cpfDestination")
	public void whenMakeADeposit(@Named("ammount") String ammount, 
								 @Named("cpfOrigin") String cpfOrigin,
								 @Named("cpfDestination") String cpfDestination) {							
		accountService.makeTransfer(cpfOrigin, cpfDestination, ammount);
	}
	
	@Then("the response code should be $responseCode and the message should be $message")
	public void assertResponseCodeAndMessage(@Named("responseCode") String responseCode,
			@Named("message") String message) {
		
		ResponseDTO<?> responseDTO = context.getLastResponse();
		
		Assert.assertEquals(String.format("Expected to get the error code '%s' but got '%s'", 
				responseCode, responseDTO.getCode()), responseCode, responseDTO.getCode()); 
		
		Assert.assertEquals(String.format("Expected to get the message: '%s' but got '%s'"
				, message, responseDTO.getMessage()), message, responseDTO.getMessage()); 
	}
	
	@Then("an account should exist and it should have the balance $expectedBalance")
	@SuppressWarnings("unchecked")
	public void assertAccountExistsAndHasBalance(@Named("expectedBalance") String expectedBalance) {
		
		ResponseDTO<AccountDTO> responseDTO = (ResponseDTO<AccountDTO>) context.getLastResponse();
		
		AccountDTO account = responseDTO.getData();
		
		Assert.assertNotNull("Account does not exists. Response data is null.", account);
		Assert.assertEquals(String.format("Account balance is incorrect. Expected: '%s' but got '%s'"
				, expectedBalance, account.getBalance()), expectedBalance, account.getBalance());
	}
}