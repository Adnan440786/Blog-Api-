package com.coder.repo;

import com.coder.Entity.Category;
import com.coder.Entity.Post;
import com.coder.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    Page<Post> getAllByUser(User user, Pageable p);
    Page<Post> getAllByCategory(Category category, Pageable p);

}
