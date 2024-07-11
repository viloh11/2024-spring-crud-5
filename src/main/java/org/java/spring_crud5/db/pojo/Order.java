package org.java.spring_crud5.db.pojo;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ordine")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Customer customers;

    @ManyToMany(mappedBy = "order")
    private List<Product> products;

    public int getId() {
        return id;
    }

    public Order(){}

    public Order(Product product){}

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customers;
    }

    public void setCustomer(Customer customer) {
        this.customers = customer;
    }

    @Override
    public String toString() {
        return "Order [Order id=" + id + ", customer=" + customers + "]";
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products = products.stream().filter(p -> p.getId() != product.getId()).toList();
    }

}
