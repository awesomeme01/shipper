package com.example.demo.service;

import com.example.demo.model.Income;
import com.example.demo.model.User;
import com.example.demo.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImpl implements IncomeService{
    @Autowired
    IncomeRepository incomeRepository;

    @Override
    public List<Income> getAllIncome() {
        return incomeRepository.findAll();
    }

    @Override
    public List<Income> getIncomeForTheLastDay() {
        LocalDateTime dateTime = LocalDateTime.now();
        return incomeRepository.findAll().stream().filter(x->x.getDateCreated().isAfter(dateTime.minusDays(1))).collect(Collectors.toList());
    }

    @Override
    public List<Income> getIncomeForTheLastWeek() {
        LocalDateTime dateTime = LocalDateTime.now();
        return incomeRepository.findAll().stream().filter(x->x.getDateCreated().isAfter(dateTime.minusWeeks(1))).collect(Collectors.toList());
    }

    @Override
    public List<Income> getIncomeInRange(LocalDateTime from, LocalDateTime to) {
        return incomeRepository.findAll().stream().filter(x->x.getDateCreated().isAfter(from) && x.getDateCreated().isBefore(to)).collect(Collectors.toList());
    }

    @Override
    public Income createIncome(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public Income updateIncome(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }

    @Override
    public Income getById(Long id) {
        return incomeRepository.getById(id);
    }

    @Override
    public List<Income> getByClient(Long clientId) {
        return incomeRepository.findAll().stream().filter(x->x.getClient().getId().equals(clientId)).collect(Collectors.toList());
    }
}
