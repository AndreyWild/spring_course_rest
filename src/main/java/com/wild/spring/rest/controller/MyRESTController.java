package com.wild.spring.rest.controller;

import com.wild.spring.rest.entity.Employee;
import com.wild.spring.rest.exeption_handling.EmployeeIncorrectData;
import com.wild.spring.rest.exeption_handling.NoSuchEmployeeException;
import com.wild.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // контроллер управляющий rest запросами и ответами
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    private Employee getEmployee(@PathVariable int id){ // @PathVariable - получает значение из запроса
        Employee employee = employeeService.getEmployee(id);

        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with ID = "
            + id + " in Database");
        }
        return employee;
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException( // Выбрасывает исключение по id
            NoSuchEmployeeException exception){
        EmployeeIncorrectData data= new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException( // Выбрасывает исключение по некорректному id
            Exception exception){
        EmployeeIncorrectData data= new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }




}
