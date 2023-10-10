package com.coder.service;

import com.coder.Entity.CategoryDto;
import com.coder.Entity.User;
import com.coder.Entity.UserDto;
import com.coder.execpation.ResourceNotFoundExpection;
import com.coder.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements  IUser{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto user) {
            User user1=this.modelMapper.map(user,User.class);
        User user2=this.userRepo.save(user1);
        return this.modelMapper.map(user2,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto user,Integer id) {
        User user1=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundExpection("User","id",id));
        user.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setAbout(user.getAbout());
       User user2= this.userRepo.save(user1);
        return this.modelMapper.map(user2,UserDto.class);
    }

    @Override
    public UserDto SearchByIdUser(int id) {
      User user=  this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundExpection("User","id",id));

        return this.modelMapper.map(user,UserDto.class);}

    @Override
    public List<UserDto> getListAll() {
        List<User> all = this.userRepo.findAll();
        List<UserDto> userDtosDtos=all.stream().map((us)->this.modelMapper.map(us,UserDto.class)).collect(Collectors.toList());
        return userDtosDtos;
    }

    @Override
    public void deleteUser(Integer id) {
    User us=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundExpection("User","id",id));
    this.userRepo.delete(us);
    }
}
