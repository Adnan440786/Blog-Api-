package com.coder.controllers;

import com.coder.Entity.PostDto;
import com.coder.Entity.PostResponse;
import com.coder.config.AppConstants;
import com.coder.execpation.ApiResponse;
import com.coder.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post/api")
public class PostController {

    @Autowired
    private PostService postService;
    // post- save-post
        @PostMapping("/userId/{uId}/categoryId/{catId}/savePos")
        public PostDto createPost(@RequestBody PostDto postDto,
                                                  @PathVariable Integer uId,
                                                  @PathVariable Integer catId){
            PostDto postDto1=this.postService.createPost(postDto,uId,catId);
            return postDto1;
        }

        //put update-Post
        @PutMapping("/updatePost/{id}")
        public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable Integer id){
            PostDto postDto1=this.postService.updatePost(postDto,id);
            return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);
        }

        //Delete delete-Post
        @DeleteMapping("/deletePost/{id}")
        public ResponseEntity<ApiResponse>deletePost(@PathVariable Integer id){
            this.postService.deletePost(id);
            return new ResponseEntity<ApiResponse>(new ApiResponse("Delete Record SuccessFully",true),HttpStatus.OK);
        }

        //Get PostById
        @GetMapping("/postById/{id}")
        public ResponseEntity<PostDto>getPostById(@PathVariable Integer id){
            PostDto postDto=this.postService.getById(id);
            return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
        }

        //get getAll-post
        @GetMapping("/getList")
        public ResponseEntity<PostResponse> getList(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                    @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                    @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                    @RequestParam(value = "sortDir",defaultValue =AppConstants.SORT_DIR,required = false)String sortDir)


        {
            System.out.println("Default : "+sortDir);
            PostResponse postResponse=this.postService.postList(pageNumber,pageSize,sortBy,sortDir);
            return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);}

        // get getPostByUser

   @GetMapping("/userPost/{id}")
    public ResponseEntity<PostResponse>getPostByUser(@PathVariable Integer id,@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                      @RequestParam(value = "pageSize",defaultValue =AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                     @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                     @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir)

       {
            PostResponse postResponse=this.postService.getPostByUser(id,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
        }

        //get getPostByCategory
       @GetMapping("/categoryPost/{id}")
        public ResponseEntity<PostResponse>getPostByCategory(@PathVariable Integer id,@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                              @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                             @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                             @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir
       ){
            PostResponse postResponse=this.postService.getPostByCategory(id,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
        }



}
