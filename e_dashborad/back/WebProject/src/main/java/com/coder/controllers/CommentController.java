package com.coder.controllers;

import com.coder.Entity.CommentDto;
import com.coder.execpation.ApiResponse;
import com.coder.repo.CommentRepo;
import com.coder.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/comment/api")
public class CommentController {

        @Autowired
        private CommentService commentService;
            //post---create-Comment
            @PostMapping("/Save/Comment/uId/{userId}/pId/{postId}")
            public ResponseEntity<CommentDto>saveComment(@RequestBody CommentDto commentDto,
                                                         @PathVariable Integer userId,@PathVariable Integer postId){
                CommentDto commentDto1= this.commentService.createComment(commentDto,userId,postId);

                return new ResponseEntity<CommentDto>(commentDto, HttpStatus.CREATED);
            }
            //delete-----delete-Comment
            @DeleteMapping("/delete/comment/{id}")
            public ResponseEntity<ApiResponse>deteleComment(@PathVariable Integer id ){
                this.commentService.deleteComment(id);
                return new ResponseEntity<ApiResponse>(new ApiResponse("Delete Comment SuccessFull",true),HttpStatus.OK);
            }
}
