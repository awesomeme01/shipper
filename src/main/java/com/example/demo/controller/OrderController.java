package com.example.demo.controller;

import com.example.demo.helper.OrderStatusWrapper;
import com.example.demo.helper.OrderTotalUpdateWrapper;
import com.example.demo.helper.OrderWrapper;
import com.example.demo.helper.Response;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @GetMapping("/upServerChecks")
    public Response upServerCheck(){
        return new Response(true, "Server is up", true);
    }

    @Secured("ROLE_USER")
    @PostMapping("/create")
    public Response create(Principal principal, @RequestBody OrderWrapper wrapper){
//        try{
        User user = userService.getByUsername(principal.getName());
        if(user.getAddress()!=null && user.getCountry() !=null && user.getDocumentNumber() !=null && user.getDocumentType()!=null){

            Order order = new Order();
            order.setDescription(wrapper.getDescription());
            order.setPriceFromInvoice(wrapper.getPriceFromInvoice());
            order.setTrackNumber(wrapper.getTrackNumber());
            order.setUser(user);
            return new Response(true, "Order successfully created!", orderService.create(order));
        }
        return new Response(false, "Necessary personal information like address, country, documentNumber, documentType are null", wrapper);

//        }catch (Exception ex){
//            return new Response(false, "Unexpected error!", ex.getMessage());
//        }
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/updateStatus/{orderId}")
    public Response updateStatus(@PathVariable Long orderId, @RequestBody OrderStatusWrapper orderStatusWrapper){
//        try{
            if(orderId!=null){
                if(orderService.exists(orderId)){
                    return new Response(true, "Order status changed to " +orderStatusWrapper.getOrderStatus()+"!", orderService.updateStatus(orderId,orderStatusWrapper.getOrderStatus()));
                }
            }
            return new Response(false, "provided order id doesn't lead to a proper result", null);
//        }catch (Exception ex){
//            return new Response(false, "Unexpected error!", ex.getMessage());
//        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/updateTotalAndVolume/{orderId}")
    public Response updateTotalAndVolume(@PathVariable Long orderId, @RequestBody OrderTotalUpdateWrapper wrapper){
        return new Response(true, "Order total changed to " + wrapper.getTotal(), orderService.updateTotal(orderId, wrapper));
    }

    @Secured( "ROLE_USER")
    @PostMapping("/update")
    public Response update (@RequestBody Order order, Principal principal){
//        try{
        Order orderFromDb = orderService.getById(order.getId());

        if(orderFromDb.getUser().equals(userService.getByUsername(principal.getName()))){
            return new Response(true, "Updated order with id = " + order.getId(), orderService.update(order));
        }
        return new Response(false, "Order doesn't belong to current User!", null);

         //}catch (Exception ex){
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
    @Secured("ROLE_USER")
    @DeleteMapping("/deleteMyOrder/{orderId}")
    public Response deleteMyOrder(Principal principal, @PathVariable Long orderId){
//        try{
//
//        }
        Order order = orderService.getById(orderId);
        if(order.getUser().equals(userService.getByUsername(principal.getName()))){
            return new Response(true, "Deleted order with id = " + orderId, null);
        }
        return new Response(false, "Order doesn't belong to current User!", null);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/getAll")
    public Response getAll(){
        try{
            return new Response(true, "All orders in db", orderService.getAll());
        }catch (Exception ex){
            return new Response(false,"Unexpected error!", ex.getMessage());
        }
    }
}

