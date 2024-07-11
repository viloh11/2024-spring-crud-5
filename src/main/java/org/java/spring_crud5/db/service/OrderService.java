package org.java.spring_crud5.db.service;


import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_crud5.db.pojo.Order;
import org.java.spring_crud5.db.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    @Transactional
    public List<Order> findAllWCustomers(){
        List<Order> orders = findAll();

        for(Order order : orders){
            Hibernate.initialize(order.getCustomer());
        }

        return orders;
    }

    public Optional<Order> findById(int id){
        return orderRepository.findById(id);
    }

    @Transactional
    public Optional<Order> findByIdWCustomer(int id){
        Optional<Order> order = findById(id);

        if(order.isEmpty())
            return Optional.empty();

        Hibernate.initialize(order.get().getCustomer());

        return order;
    }

    public void save(Order order){
        orderRepository.save(order);
    }

    public void delete(Order order){
        orderRepository.delete(order);
    }

}
