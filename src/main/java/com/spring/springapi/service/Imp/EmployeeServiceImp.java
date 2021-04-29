package com.spring.springapi.service.Imp;


import com.spring.springapi.db.Repository.EmployeeRepository;
import com.spring.springapi.entity.Employee;
import com.spring.springapi.exception.businessException.BusinessException;
import com.spring.springapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImp() {

    }

    public List<Employee> getAllEmployee()
    {
        List<Employee> empList = null;

        try{
            empList = employeeRepository.findAll();

        }catch (Exception e)
        {
            throw new BusinessException("602","Something went wrong in service layer while fetching all employees"+e.getMessage());
        }
        if(empList.isEmpty())
            throw new BusinessException("601","Hey list completely empty, we have nothing to return");
        return empList;

    }

    @Override
    public Employee getEmployeeById(long employeeId) {

        try {
            return employeeRepository.findById(employeeId).get();
        }catch (IllegalArgumentException e)
        {
            throw new BusinessException("603","given employee is null, please send some id to be searched"+e.getMessage());
        }catch (java.util.NoSuchElementException e)
        {
            throw new BusinessException("604","given employee id does not exist in DB. "+e.getMessage());
        }

    }

    @Override
    public Employee addEmployee(Employee employee) {


            if(employee.getName().isEmpty() || employee.getName().length() == 0) {
                throw new BusinessException("605", "Please send proper name, It blank");
            }
          try{
            employeeRepository.save(employee);
            return employee;
        }catch (IllegalArgumentException e)
        {
            throw new BusinessException("606","given employee is null"+e.getMessage());
        }catch (Exception e)
        {
            throw new BusinessException("607","Something went wrong in service layer while saving the employee"+e.getMessage());
        }

    }

    @Override
    public Employee updateEmployee(Employee employee) {

        if(employee.getName().isEmpty() || employee.getName().length() == 0) {
            throw new BusinessException("608", "Please send proper name, It blank");
        }
        try{
            employeeRepository.save(employee);
            return employee;
        }catch (IllegalArgumentException e)
        {
            throw new BusinessException("609","given employee is null"+e.getMessage());
        }catch (Exception e)
        {
            throw new BusinessException("610","Something went wrong in service layer while saving the employee"+e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(long employeeId) {

        try {
            employeeRepository.deleteById(employeeId);
        }catch (IllegalArgumentException e){
            throw new BusinessException("611","given employee id is null, please send some id to be searched"+e.getMessage());
        }


    }
}
