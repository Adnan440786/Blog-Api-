package com.coder.service;

import com.coder.Entity.PostDto;
import com.coder.Entity.PostResponse;

import java.util.List;

public interface IPost {

            PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

            PostDto updatePost(PostDto postDto,Integer id );

            void deletePost(Integer id);

            PostResponse postList(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

            PostDto getById(Integer id);

            PostResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
            PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);


}
