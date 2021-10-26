package com.stackoverflow.question.repositories;

import com.stackoverflow.question.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
