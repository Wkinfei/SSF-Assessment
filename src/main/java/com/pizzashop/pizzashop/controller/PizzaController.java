package com.pizzashop.pizzashop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizzashop.pizzashop.models.Pizza;
import com.pizzashop.pizzashop.service.RedisService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {
    @Autowired
    RedisService redisService;

    @GetMapping("/")
    public String getForm(Model model){
    model.addAttribute("pizza", new Pizza());
    return "index";}
   
    @PostMapping(path="/pizza")
    public String getPizza(@Valid Pizza pizza,BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.toString());
            return "pizza";
        }
    return "pizza";}

    @PostMapping(path="/pizza/order")
    public String getOrder(@ModelAttribute Pizza pizza,Model model){
        int pizzaSize = pizza.getPizzaSizePrice();
        double pizzaTypePrice = pizza.getPizzaNamePrice(pizza.getPizza());
        double totalPrice = pizza.calTotalPizzaPrice(pizzaTypePrice, pizzaSize, pizza.getQuantity(),pizza.getRush());
        pizza.setTotal(totalPrice);
        double pizzaCost = pizza.calPizzaPrice(totalPrice, pizzaSize, pizza.getQuantity());
        pizza.setPizzaCost(pizzaCost);
        // redisService.saveGame(pizza);
    model.addAttribute("pizza", pizza);
    return "result";}
}
