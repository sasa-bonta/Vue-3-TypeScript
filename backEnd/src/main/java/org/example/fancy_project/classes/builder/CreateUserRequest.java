package org.example.fancy_project.classes.builder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequest {
    private String email;
    private String username;
    private String password;
}
