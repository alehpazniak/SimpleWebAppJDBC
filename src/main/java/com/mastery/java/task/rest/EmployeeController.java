package com.mastery.java.task.rest;


import com.mastery.java.task.rest.protocol.EmployeeRequest;
import com.mastery.java.task.rest.protocol.EmployeeResponse;
import com.mastery.java.task.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping()
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.save(employeeRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(value = "id") long id) {
        employeeService.deleteById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployeeById(@RequestBody EmployeeRequest employeeRequest,
                                               @PathVariable(value = "id") long id) {
        return employeeService.updateById(employeeRequest, id);
    }

    @GetMapping("/all")
    public List<EmployeeResponse> getAllEmployee() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable(value = "id") long id) {
        return employeeService.findById(id);
    }

}
