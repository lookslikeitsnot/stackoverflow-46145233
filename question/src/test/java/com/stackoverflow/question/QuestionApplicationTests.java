package com.stackoverflow.question;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.stackoverflow.question.entities.Expense;
import com.stackoverflow.question.entities.User;
import com.stackoverflow.question.repositories.ExpenseRepository;
import com.stackoverflow.question.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

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

	// Can persist with userId
	@Test
	void saveExpense_withUserId_expenseSaved() {
		Expense expense = new Expense();
		expense.setUserId(1);


		assertTrue(expenseRepository.save(expense).getId() != null);
	}

	// Cannot persist with user anymore
	@Test
	void saveExpense_withUser_throwsNullUserId() {
		User testUser = userRepository.findById(1).orElseThrow();

		Expense expense = new Expense();
		expense.setUser(testUser);

		assertThrows(DataIntegrityViolationException.class, () -> expenseRepository.save(expense));
	}

	// Needs extra step for user's id
	@Test
	void saveExpense_withUserGetId_expenseSaved() {
		User testUser = userRepository.findById(1).orElseThrow();

		Expense expense = new Expense();
		expense.setUserId(testUser.getId());


		assertTrue(expenseRepository.save(expense).getId() != null);
	}
}
