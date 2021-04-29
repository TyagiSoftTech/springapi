package com.spring.springapi.controller;


import com.spring.springapi.entity.Employee;
import com.spring.springapi.exception.ResourceNotFoundException;
import com.spring.springapi.exception.businessException.BusinessException;
import com.spring.springapi.exception.controllerException.ControllerException;
import com.spring.springapi.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //Get All Employees
    @GetMapping("/getAllEmployee")
    public ResponseEntity<?> getAllEmployees()
    {
        try {
        List<Employee> getAllEmployee = employeeService.getAllEmployee();
        return new ResponseEntity<List<Employee>>(getAllEmployee,HttpStatus.OK);
    }catch (BusinessException e){
        ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
        return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
    }catch (Exception e){
        ControllerException ce = new ControllerException("612","Something went wrong in controller");
        return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
    }
}
    //Get one Employee
    @GetMapping("/getEmployeeById/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String employeeId)
    {
        try {
            Employee employeeById = employeeService.getEmployeeById(Long.parseLong(employeeId));
            return new ResponseEntity<Employee>(employeeById,HttpStatus.OK);
        }catch (BusinessException e){
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("612","Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

    }

    //Add Employee
    @PostMapping("/addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee)
    {
        try {
            Employee employeeAdd = employeeService.addEmployee(employee);
            return new ResponseEntity<Employee>(employeeAdd, HttpStatus.CREATED);
        }catch (BusinessException e){
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("613","Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

    }

    //Update Employee
    @PutMapping("/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        try {
            Employee employeeUpdate = employeeService.updateEmployee(employee);

            return new ResponseEntity<Employee>(employeeUpdate, HttpStatus.OK);
        } catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ControllerException ce = new ControllerException("613", "Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }
    //Delete Employee
    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String employeeId) {
        try {
            this.employeeService.deleteEmployee(Long.parseLong(employeeId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ControllerException ce = new ControllerException("613", "Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }
}
