package com.mastery.java.task.rest;


import com.mastery.java.task.rest.protocol.EmployeeRequest;
import com.mastery.java.task.rest.protocol.EmployeeResponse;
import com.mastery.java.task.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.saveEmployee(employeeRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(value = "id") long id) {
        employeeService.deleteDismissedEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployeeById(@RequestBody EmployeeRequest employeeRequest,
                                               @PathVariable(value = "id") long id) {
        return employeeService.updateEmployeeById(employeeRequest, id);
    }

    @GetMapping("/all-employees")
    public List<EmployeeResponse> getAllEmployee() {
        return employeeService.findAllEmployee();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable(value = "id") long id) {
        return employeeService.findById(id);
    }

}
