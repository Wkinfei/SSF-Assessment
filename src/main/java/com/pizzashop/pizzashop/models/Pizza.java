package com.pizzashop.pizzashop.models;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Pizza implements Serializable{
    private String pizza;
    private String size;
    private int quantity;

    @Size(min=3, message="Name must have at least 3 characters")
    private String name;
  
    @NotNull(message = "Cannot be null")
    private String address;

    @Min(value=8, message="phone number must contain at least 8 digits")
    private String phone;

    private Boolean rush=false;
    private String comments;
    private String orderId;
    private double total;
    private double pizzaCost;

    public Pizza() {
        this.orderId = generateId(8);
    }
    
    
    


    public String getPizza() {
        return pizza;
    }





    public void setPizza(String pizza) {
        this.pizza = pizza;
    }





    public String getSize() {
        return size;
    }





    public void setSize(String size) {
        this.size = size;
    }





    public int getQuantity() {
        return quantity;
    }





    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }





    public String getName() {
        return name;
    }





    public void setName(String name) {
        this.name = name;
    }





    public String getAddress() {
        return address;
    }





    public void setAddress(String address) {
        this.address = address;
    }





    public String getPhone() {
        return phone;
    }





    public void setPhone(String phone) {
        this.phone = phone;
    }





    public Boolean getRush() {
        return rush;
    }





    public void setRush(Boolean rush) {
        this.rush = rush;
    }





    public String getComments() {
        return comments;
    }





    public void setComments(String comments) {
        this.comments = comments;
    }





    public String getOrderId() {
        return orderId;
    }





    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }





    public double getTotal() {
        return total;
    }





    public void setTotal(double total) {
        this.total = total;
    }





    public double getPizzaCost() {
        return pizzaCost;
    }





    public void setPizzaCost(double pizzaCost) {
        this.pizzaCost = pizzaCost;
    }





    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numChars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numChars);
    }

   

    public double calTotalPizzaPrice(double getPizzaPrice, int getPizzaSizePrice, int quantity, boolean rush){
        double total = getTotal();
        if(rush=true){
            total=(getPizzaPrice * getPizzaSizePrice * quantity)+2;
        }else{
            total = getPizzaPrice * getPizzaSizePrice * quantity;
        }
        return total;
    }
    public double calPizzaPrice(double getPizzaNamePrice, int getPizzaSizePrice, int quantity){
        double totalPizzaCost = getPizzaCost();
        
        totalPizzaCost = getPizzaNamePrice * getPizzaSizePrice * quantity;
        
        return totalPizzaCost;
    }
    public double getPizzaNamePrice(String pizzaName){
        return switch(pizzaName.toUpperCase()){
            case "BELLA","MARINARA","SPIANATA CALABRESE"-> 30.0;
            case"MARGHERITA"->22.0;
            case"TRIO FORMAGGIO"->25.0;
            default->0.0;
        };
    }
    public int getPizzaSizePrice(){
        return switch(getSize().toUpperCase()){
            case "SMALL"-> 1;
            case"MEDIUM"->2;
            case"LARGE"->3;
            default->0;
        };
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                    .add("orderId",this.getOrderId())
                    .add("name",this.getName())
                    .add("address",this.getAddress())
                    .add("phone",this.getPhone())
                    .add("rush",this.getRush())
                    .add("comments",this.getComments())
                    .add("pizza",this.getPizza())
                    .add("size",this.getSize())
                    .add("qunantity",this.getQuantity())
                    .add("total",this.getTotal())
                    .build();
    }

    public static Pizza create(String json) throws IOException {
        Pizza p = new Pizza();
        if(json != null)
            try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
                JsonReader r = Json.createReader(is);
                JsonObject o = r.readObject();
                
                p.setOrderId(o.getString("orderId"));
                p.setName(o.getString("name"));
                p.setAddress(o.getString("address"));
                p.setPhone(o.getString("phone"));
                p.setRush((o.getBoolean("rush")));
                p.setComments(o.getString("comments"));
                p.setPizza(o.getString("pizza"));
                p.setSize(o.getString("size"));
                JsonNumber quan =(o.getJsonNumber("quantity"));
                p.setQuantity(quan.intValue());
                JsonNumber t =(o.getJsonNumber("total"));
                p.setTotal(t.intValue()); 
            }
        return p;
    }


    @Override
    public String toString() {
        return "Pizza [pizza=" + pizza + ", size=" + size + ", quantity=" + quantity + "]";
    }


  


    
   

 


    }

    
    


