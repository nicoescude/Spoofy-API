package com.nescude.spoofy.controllers;

import com.nescude.spoofy.forms.UserForm;
import com.nescude.spoofy.model.users.Premium;
import com.nescude.spoofy.model.users.Standard;
import com.nescude.spoofy.model.users.User;
import com.nescude.spoofy.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@RequestMapping( path = "/users" )
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping("/{username}")
    public User getUsuario(@PathVariable String username){
        return service.getUser(username);
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers(){
        return service.getAllUser();
    }

    @DeleteMapping("/{username}")
    public Result deleteUsuario(@PathVariable String username){
        boolean b = service.deleteUser(username);
        return new Result(b);
    }

    @PostMapping("/add")
    public User postUser(
        @RequestBody UserForm user){
        User u;
        if (user.isPremium()){
            u =  new Premium(user.getUsername(),user.getFullname(),null);
        }
        else{
            u = new Standard(user.getUsername(),user.getFullname(),null);
        }
        service.postUser(u);
        return u;
    }

    @Data
    public class Result{
        private boolean result;
        public Result(boolean result){
            this.result = result;
        }
    }
}


