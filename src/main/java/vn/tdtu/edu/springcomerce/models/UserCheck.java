package vn.tdtu.edu.springcomerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCheck {
    private Long id;
    private String username;
    private String fname;
    private String lname;
    private String password;
    private String email;
    private Role role;
    public UserCheck(String fname, String lname, String password, String email, Role role) {
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
