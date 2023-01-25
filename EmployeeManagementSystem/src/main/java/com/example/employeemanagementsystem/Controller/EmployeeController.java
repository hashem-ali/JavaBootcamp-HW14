package com.example.employeemanagementsystem.Controller;

import com.example.employeemanagementsystem.ApiResponse;
import com.example.employeemanagementsystem.Pojo.Employee;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/emp/")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping("get")
    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    @PostMapping("add")
    public ResponseEntity addEmp(@Valid @RequestBody Employee employee, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.add(employee);
        return ResponseEntity.status(201).body("Employee Added");
    }
    @PutMapping("update/{index}")
    public ResponseEntity updateEmployee(@Valid @PathVariable int index, @RequestBody Employee employee, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.set(index, employee);
        return ResponseEntity.status(200).body("Updated Successfully");
    }
    //emty string
    @DeleteMapping("delete/{index}")
    public ApiResponse deleteEmp(@PathVariable int index){
        employees.remove(index);
        return new ApiResponse("Deleted Successfully");
    }
@PostMapping("apply/{id}")
    public ResponseEntity apply(@Valid @PathVariable String id){

        for (Employee emp: employees) {
            if(emp.getId().equals(id)){
                if(emp.isOnLeave() || emp.getAnnualLeave() == 0){
                    return ResponseEntity.status(400).body("Bad Request");
                }else {
                    emp.setOnLeave(true);
                    emp.setAnnualLeave(emp.getAnnualLeave() - 1);
                }
            }
    }
    return ResponseEntity.status(200).body("Success");
}

}
