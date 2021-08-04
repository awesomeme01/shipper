package com.example.demo.controller;

import com.example.demo.helper.OrderStatusWrapper;
import com.example.demo.helper.OrderWrapper;
import com.example.demo.helper.Response;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @Secured("ROLE_USER")
    @PostMapping("/create")
    public Response create(Principal principal, @RequestBody OrderWrapper wrapper){
//        try{
            Order order = new Order();
            order.setDescription(wrapper.getDescription());
            order.setPriceFromInvoice(wrapper.getPriceFromInvoice());
            order.setTrackNumber(wrapper.getTrackNumber());
            order.setUser(userService.getByUsername(principal.getName()));
            return new Response(true, "Order Successfuly created!", orderService.create(order));
//        }catch (Exception ex){
//            return new Response(false, "Unexpected error!", ex.getMessage());
//        }
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/updateStatus/{orderId}")
    public Response updateStatus(@PathVariable Long orderId, @RequestBody OrderStatusWrapper orderStatusWrapper){
//        try{
            return new Response(true, "Order status changed to " +orderStatusWrapper.getOrderStatus()+"!", orderService.updateStatus(orderId,orderStatusWrapper.getOrderStatus()));
//        }catch (Exception ex){
//            return new Response(false, "Unexpected error!", ex.getMessage());
//        }
    }
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/update")
    public Response update (@RequestBody Order order){
//        try{
            return new Response(true, "Order with id = " + order.getId() + " has been updated!", orderService.update(order));
//        }catch (Exception ex){
//            return new Response(false,"Unexpected error!", ex.getMessage());
//        }
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}")
    public Response deleteByID (@PathVariable Long id){
//        try{
            orderService.delete(id);
            return new Response(true, "Order has been deleted!", null);
//        }catch (Exception ex){
//            return new Response(false,"Unexpected error!", ex.getMessage());
//        }
    }
    @Secured("ROLE_USER")
    @GetMapping("/getMyOrders")
    public Response getMyOrders(Principal principal){
//        try{
            User user = userService.getByUsername(principal.getName());
            return new Response(true, "All orders of the current user!", orderService.getMyOrders(user));
//        }
//        catch (Exception ex){
//            return new Response(false,"Unexpected error!", ex.getMessage());
//        }
    }


}

