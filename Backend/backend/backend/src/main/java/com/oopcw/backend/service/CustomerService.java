package com.oopcw.backend.service;

import com.oopcw.backend.entity.Customer;
import com.oopcw.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public void startCustomerThread(Customer customer) {
        Thread customerThread = new Thread(new CustomerServiceImpl(new TicketPoolService(100), customer.getRetrievalRate()));
        customerThread.start();
        System.out.println("Customer thread started for customer: " + customer.getName());
    }
}
