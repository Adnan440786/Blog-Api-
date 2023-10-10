package com.coder.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer categoryId;
        @Column(name = "title",length = 100,nullable = false)
        @NotEmpty(message = "Categroy title Can't be Null !!")
        private String categoryTitle;
        private String categoryDescription;

        @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        private List<Post>categoryList=new ArrayList<>();

}
