package com.stackoverflow.question;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.stackoverflow.question.entities.Expense;
import com.stackoverflow.question.repositories.ExpenseRepository;
import com.stackoverflow.question.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuestionApplicationTests {
	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {}

	@Test
	void findUser_id1_validUser() {
		assertTrue(userRepository.findById(1).orElseThrow().getUsername().equals("user1"));
	}

	// Can persist with user reference
	@Test
	void saveExpense_withUserReference_expenseSaved() {
		Expense expense = new Expense();
		expense.setUser(userRepository.getById(1));

		assertTrue(expenseRepository.save(expense).getId() != null);
	}

	// Can persist with user entity
	@Test
	void saveExpense_withUserEntity_expenseSaved() {
		Expense expense = new Expense();
		expense.setUser(userRepository.findById(1).orElseThrow());


		assertTrue(expenseRepository.save(expense).getId() != null);
	}
}
