package com.example.demo.service;

import com.example.demo.model.Income;
import com.example.demo.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface IncomeService {
    List<Income> getAllIncome();
    List<Income> getIncomeForTheLastDay();
    List<Income> getIncomeForTheLastWeek();
    List<Income> getIncomeInRange(LocalDateTime from, LocalDateTime to);
    Income createIncome(Income income);
    Income updateIncome(Income income);
    void deleteIncome(Long id);
    Income getById(Long id);
    List<Income> getByClient(Long id);
}
