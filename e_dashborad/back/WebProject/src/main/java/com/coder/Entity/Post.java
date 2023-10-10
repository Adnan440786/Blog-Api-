package com.coder.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Post {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private int id;
            private String title;
            private String content;
            private String imageName;
            private Date addDate;

            @ManyToOne
            @JoinColumn(name = "cat_id")
            private Category category;

            @ManyToOne
            @JoinColumn(name = "user_id")
            private User user;
            @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
            private Set<Comments> comments=new HashSet<>();

}
