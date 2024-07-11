package org.java.spring_crud5.db.service;

import java.util.List;
import java.util.Optional;

import org.java.spring_crud5.db.repo.CustomerRepo;
import org.hibernate.Hibernate;
import org.java.spring_crud5.db.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepository;


    @Transactional
    public List<Customer> findAllWOrder(){
        List<Customer> customers = findAll();

            for(Customer customer : customers){
                Hibernate.initialize(customer.getOrders());
            }

            return customers;
    }

    public Optional<Customer> findById(int id){
        return customerRepository.findById(id);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @Transactional
    public Optional<Customer> findByIdWOrder(int id){
        Optional<Customer> customer = findById(id);

        if(customer.isEmpty())
            return Optional.empty();

        Hibernate.initialize(customer.get().getOrders());

        return customer;

    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }

    public void delete(Customer customer){
       // customer.getOrders().forEach(orders -> orders.setCustomer(null));
        customerRepository.delete(customer);
    }
}
