package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")
@NoArgsConstructor
@AllArgsConstructor
@Data
class UserBean {

    private Integer id;
    private String name;

    public static int compareById(UserBean source, UserBean target) {
        return source.getId().compareTo(target.getId());
    }

}
