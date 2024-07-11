package org.java.spring_crud5.db.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_crud5.db.pojo.Product;
import org.java.spring_crud5.db.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id){
        return productRepository.findById(id);
    }

    @Transactional
    public Optional<Product> findByIdWOrders(int id){
        Optional<Product> optProduct = productRepository.findById(id);

        if(optProduct.isEmpty())
            return Optional.empty();

        Product product = optProduct.get();
        Hibernate.initialize(product.getOrder());

        return Optional.of(product);
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public void delete(Product product){
        productRepository.delete(product);
    }
}
