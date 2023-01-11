package com.glearning.lab.labassignment.controller;

import com.glearning.lab.labassignment.entity.Employee;
import com.glearning.lab.labassignment.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    public EmployeeServiceImpl employeeService;

    @GetMapping("/employees")
    public  String getList(Model model){


        model.addAttribute("employees",employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/employees/new")
    public  String createEmployeeForm(Model model){

        Employee employee = new Employee();

        model.addAttribute("employee",employee);
        return "create_employee";
    }


    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee")Employee employee){

        Employee employee1= employeeService.saveEmployee(employee);
        return  "employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployee(@PathVariable Long id,Model model){

        model.addAttribute("employee",employeeService.getEmployeeById(id));
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id,@ModelAttribute ("employee") Employee employee,Model model){

        Employee e1 = employeeService.getEmployeeById(id);
        e1.setId(id);
        e1.setFirstName(employee.getFirstName());
        e1.setLastName(employee.getLastName());
        e1.setEmail(employee.getEmail());

        Employee employee1= employeeService.updateEmployee(e1);
        return  "redirect:/employees";
    }

    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id){

        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
