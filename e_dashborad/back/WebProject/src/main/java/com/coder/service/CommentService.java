package com.coder.service;

import com.coder.Entity.CommentDto;
import com.coder.Entity.Comments;
import com.coder.Entity.Post;
import com.coder.Entity.User;
import com.coder.execpation.ResourceNotFoundExpection;
import com.coder.repo.CommentRepo;
import com.coder.repo.PostRepo;
import com.coder.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements IComment{

        @Autowired
        private ModelMapper modelMapper;
        @Autowired
        private CommentRepo commentRepo;
        @Autowired
        private UserRepo userRepo;
        @Autowired
        private PostRepo postRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
        Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundExpection("Post","id",postId));
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundExpection("User","id",userId));
         Comments comments=this.modelMapper.map(commentDto, Comments.class);
        comments.setPost(post);
        comments.setUser(user);
        return this.modelMapper.map(comments,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer id) {
    Comments comments=this.commentRepo.findById(id).orElseThrow(()->new ResourceNotFoundExpection("Comment","id",id));
            this.commentRepo.delete(comments);
    }
}
