package com.example.demo.controller;

import com.example.demo.helper.DateRangeWrapper;
import com.example.demo.helper.Response;
import com.example.demo.model.Income;
import com.example.demo.service.IncomeService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;
    @Autowired
    UserService userService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/getAll")
    public Response getAll(){
        try{
            return new Response(true, "All Income records!", incomeService.getAllIncome());
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/getIncomeForTheLastDay")
    public Response getIncomeForTheLastDay(){
        try{
            return new Response(true, "Income records for the last day!", incomeService.getIncomeForTheLastDay());
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/getIncomeForTheLastWeek")
    public Response getIncomeForTheLastWeek(){
        try{
            return new Response (true, "Income records for the last week", incomeService.getIncomeForTheLastWeek());
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/getIncomeInRange")
    public Response getIncomeInRange(@RequestBody DateRangeWrapper d){
        try{
            return new Response (true, "Income recorded from " + d.getFrom() + " to " + d.getTo(), incomeService.getIncomeInRange(d.getFrom(), d.getTo()));
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/create/{clientId}")
    public Response create(@PathVariable Long clientId, @RequestBody Income income){
        try{
            income.setClient(userService.getById(clientId));
            return new Response (true, "New income record added!", incomeService.createIncome(income));
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/update")
    public Response update(@RequestBody Income income){
        try{
            return new Response (true, "Income record updated!", incomeService.updateIncome(income));
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/getById/{incomeId}")
    public Response getById(@PathVariable Long incomeId){
        try{
            return new Response(true, "Income record with id = " + incomeId, incomeService.getById(incomeId));
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }

    @Secured ("ROLE_ADMIN")
    @PostMapping("/delete/{incomeId}")
    public Response delete(@PathVariable Long incomeId){
        try{
            incomeService.deleteIncome(incomeId);
            return new Response(true, "Deleted income with id = " + incomeId, null);
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/getByClient/{clientId]")
    public Response getByClient(@PathVariable Long id){
        try{
            return new Response(true, "All income records from specific client with id = " + id, incomeService.getByClient(id));
        }catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }

}
