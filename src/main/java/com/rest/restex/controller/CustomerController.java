package com.rest.restex.controller;
import com.rest.restex.one.CustomberModel;
import com.rest.restex.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @PostMapping("/add")
    public CustomberModel addCustomer(@RequestBody CustomberModel customer){
        CustomberModel customer1=new CustomberModel();
        customer1.setName(customer.getName());
        customer1.setEmail(customer.getEmail());
        customerRepo.save(customer1);
        return customer1;
    }

    //view all customer
    @GetMapping("/view/all")
    public @ResponseBody Iterable<CustomberModel> getAllCustomber(){
        return customerRepo.findAll();
    }

  // Asutosh

    @GetMapping("view/{id}")
    public Optional<CustomberModel> getCustomer(@PathVariable Integer id) {
        return customerRepo.findById(id);
    }

    // update an existing customer
    @PutMapping("/edit/{id}")
    public String update( @RequestBody CustomberModel updateCustomer, @PathVariable Integer id) {
        return customerRepo.findById(id)
                .map(customer -> {
                    customer.setName(updateCustomer.getName());
                    customer.setEmail(updateCustomer.getEmail());
                    customerRepo.save(customer);
                    return "Customer details have been successfully updated!";
                }).orElseGet(() -> {
                    return "This customer doesn't exist";
                });
    }

    // delete customer
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id")Integer id) {
        customerRepo.deleteById(id);
        return "Customer has been successfully deleted!";
    }


}
