package com.coder.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(name = "user_name",nullable = false,length = 100)
        @NotEmpty
        @Size(min = 4,message = "userName must be min 4 characters")
        private String name;
        @Email(message = "Email Address is not Valid !!")
        private String email;
        @NotEmpty
        @Size(min = 4,max = 8,message = "Password must be min of 3 chars and max of 10 chars !!")
        private String password;
        @NotNull
        private String about;

        @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        private List<Post> postList=new ArrayList<>();

        @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
        private Set<Comments>comments=new HashSet<>();

}
