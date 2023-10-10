package com.coder.controllers;

import com.coder.Entity.User;
import com.coder.Entity.UserDto;
import com.coder.execpation.ApiResponse;
import com.coder.service.IUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/api")
public class UserController {
            @Autowired
            private IUser iUser;

        // Post - create user
        @PostMapping("/save")
        public ResponseEntity<UserDto> saveUser(@Valid @RequestBody  UserDto user){
                UserDto user1=this.iUser.createUser(user);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        }

        // put - update user
        @PutMapping("/update/user/{id}")
        public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user , @PathVariable("id")Integer id){
            UserDto user1=this.iUser.updateUser(user,id);
            return new ResponseEntity<>(user1,HttpStatus.OK);
            }

        // Get - getAllUser
            @GetMapping("/list")
            public ResponseEntity<List<UserDto>> getAllUser(){
            return ResponseEntity.ok(this.iUser.getListAll());
            }

        // Get getByIdUser
            @GetMapping("/userId/{id}")
            public ResponseEntity<UserDto> getUserById(@PathVariable("id")Integer id){
            return ResponseEntity.ok(this.iUser.SearchByIdUser(id));
            }

        // Delete DeleteUser
        @DeleteMapping("/delete/user/{id}")
        public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id")Integer id){
            this.iUser.deleteUser(id);
            return new ResponseEntity<ApiResponse>(new ApiResponse("User Delete  SuccessFully",true),HttpStatus.OK);
        }

}
