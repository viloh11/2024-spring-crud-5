package org.java.spring_crud5.db.pojo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 128)
    private String lastname;

    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @Column(nullable = false, unique = true, length = 16)
    private String phone;

    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer(){}

    public Customer(String name, String lastname, String email, String phone, Order order){
        this.orders = new ArrayList<>();
        setName(name);
        setLastname(lastname);
        setEmail(email);
        setPhone(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrders(Order order){
        orders.add(order);
    }

    public void removeOrder(Order order){
        orders = orders.stream().filter(o -> o.getId() !=  order.getId()).toList();
    }

    @Override
    public String toString() {
        return "Customer{" + "name: " + name + ", lastname: " + lastname + ", email: " + email + ", phone: " + phone + "}";
    }
}
