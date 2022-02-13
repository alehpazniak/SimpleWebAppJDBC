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

    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable long id) {
        return employeeService.findById(id);
    }

    @GetMapping
    public List<EmployeeResponse> getAll() {
        return employeeService.findAll();
    }

    @PostMapping
    public EmployeeResponse add(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.save(employeeRequest);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateById(@RequestBody EmployeeRequest employeeRequest,
                                       @PathVariable long id) {
        return employeeService.updateById(employeeRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        employeeService.deleteById(id);
    }

}
