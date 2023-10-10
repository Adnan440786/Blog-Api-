package com.coder.service;

import com.coder.Entity.*;
import com.coder.config.AppConstants;
import com.coder.execpation.ResourceNotFoundExpection;
import com.coder.repo.CategoryRepo;
import com.coder.repo.PostRepo;
import com.coder.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPost {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;

    @Override
    public PostDto
    createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundExpection("User", "id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundExpection("Category", "id", categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setAddDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        post.setImageName("Default.png");

        Post post1 = this.postRepo.save(post);

        return this.modelMapper.map(post1, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExpection("Post", "id", id));
        post.setImageName(postDto.getImageName());
        post.setAddDate(postDto.getAddDate());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post post1 = this.postRepo.save(post);
        return this.modelMapper.map(post1, PostDto.class);
    }


    @Override
    public void deletePost(Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExpection("Post", "id", id));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse postList(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        System.out.println("done : "+sortDir);
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc")){
          sort=Sort.by(sortBy).ascending();
        }else{
            sort=Sort.by(sortBy).descending();
           }
        Pageable p =PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> postPage = this.postRepo.findAll(p);
        List<Post> postList = postPage.getContent();
        List<PostDto> postDtoList = postList.stream().map((plist) -> this.modelMapper.map(plist, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPage(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }

    @Override
    public PostDto getById(Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExpection("Post", "id", id));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundExpection("User", "id", userId));
       Sort sort=null;
       if(sortDir.equalsIgnoreCase(AppConstants.SORT_DIR)){
           sort=Sort.by(sortBy).ascending();
       }else{
           sort=Sort.by(sortBy).descending();
       }

        Pageable p = PageRequest.of(pageNumber, pageSize,sort);

        Page<Post> postPage = this.postRepo.getAllByUser(user, p);
        List<Post> postList = postPage.getContent();
        List<PostDto> postDtoList = postList.stream().map((ulist) -> this.modelMapper.map(ulist, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPage(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundExpection("Category", "id", categoryId));

        Sort sort=null;
        if(sortDir.equalsIgnoreCase(AppConstants.SORT_DIR)){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort=Sort.by(sortBy).descending();
        }

        Pageable p1 = PageRequest.of(pageNumber, pageSize);

        Page<Post> postPage =  this.postRepo.getAllByCategory(category, p1);
          List<Post>postList=postPage.getContent();
        List<PostDto> postDtoList = postList.stream().map((cList) -> this.modelMapper.map(cList, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPage(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }
}