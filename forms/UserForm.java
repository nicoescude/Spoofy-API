package com.nescude.spoofy.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserForm {
    private String username;
    private String fullname;
    private boolean premium;
}
