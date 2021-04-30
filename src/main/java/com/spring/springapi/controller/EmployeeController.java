package com.spring.springapi.controller;

import com.spring.springapi.model.Employee;
import com.spring.springapi.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;
    //Get All Employees
    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        try {
             List<Employee> getAllEmployee = employeeService.getAllEmployee();
            logger.info("getAllEmployee successfully");
             return new ResponseEntity<List<Employee>>(getAllEmployee,HttpStatus.OK);
        }catch (Exception e){
            logger.warn("Please fill correct details");
             return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
    }
    //Get one Employee
    @GetMapping("/getEmployeeById/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) {
        try {
            Employee employeeById = employeeService.getEmployeeById(Long.parseLong(employeeId));
            logger.info("getEmployeeById successfully");
            return new ResponseEntity<Employee>(employeeById,HttpStatus.OK);
        }catch (Exception e){
            logger.warn("Please fill correct employeeId");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }
    //Add Employee
    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        try {
            Employee employeeAdd = employeeService.addEmployee(employee);
            logger.info("addEmployee successfully");
            return new ResponseEntity<Employee>(employeeAdd, HttpStatus.CREATED);
        }catch (Exception e){
            logger.warn("Please fill correct details");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }
    //Update Employee
    @PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        try {
            Employee employeeUpdate = employeeService.updateEmployee(employee);
            logger.info("updateEmployee successfully");
            return new ResponseEntity<Employee>(employeeUpdate, HttpStatus.OK);
        }catch (Exception e) {
            logger.warn("Please fill correct updateEmployee details");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }
    //Delete Employee
    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable String employeeId) {
        try {
            this.employeeService.deleteEmployee(Long.parseLong(employeeId));
            logger.info("deleteEmployee successfully");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            logger.warn("Please fill correct employeeId");
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
  }
}
