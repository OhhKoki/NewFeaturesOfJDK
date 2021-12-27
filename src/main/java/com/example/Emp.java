package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
class Emp {
    private String name;
    private Integer age;
    private Double salary;
    private Status status;

    @SuppressWarnings("all")
    public enum Status {
        FREE,
        BUSY,
        VOCHATION;
    }

}
