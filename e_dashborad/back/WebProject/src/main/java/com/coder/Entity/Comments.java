package com.coder.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Comments {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String comment;

        @ManyToOne
        @JoinColumn(name = "post_id")
        private Post post;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
}
