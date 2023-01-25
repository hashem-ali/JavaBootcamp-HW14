package com.example.employeemanagementsystem.Pojo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    @NotEmpty(message = "Can't be empty")
    @Size(min = 3, message = "Must be more than 2")
    private String id;
    @NotEmpty(message = "Can't be empty")
    @Size(min = 5, message = "Must be more than 4")
    private String name;
    @NotNull(message = "Can't be null")
//    @Pattern(regexp="^[0-9]+$", message="Must be number")
    @Min(25)
    private int age;
    @NotNull(message = "Can't be null")
//    @Pattern(regexp="^[0-9]+$", message="Must be number")
    @Max(3030)
    @Min(4)
    private int employmentYear;
//    @Pattern(regexp="(?:^|\\W)false(?:$|\\W)")
    @AssertFalse(message = "Must be false")
    private boolean onLeave;
    @NotNull(message = "Can't be null")
//    @Pattern(regexp="^[0-9]+$", message="Must be number")
    @Min(0)
    @Max(99999)
    private int annualLeave;
    @NotEmpty(message = "Can't be empty")
    @Pattern(regexp = "(?:^|\\W)supervisor(?:$|\\W)|(?:^|\\W)coordinator(?:$|\\W)")
    private String position;
}
