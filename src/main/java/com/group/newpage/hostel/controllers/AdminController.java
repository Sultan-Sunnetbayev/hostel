package com.group.newpage.hostel.controllers;

import com.group.newpage.hostel.models.User;
import com.group.newpage.hostel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/add/deanFaculty", produces = "application/json")
    public ResponseEntity addDeanFaculty(final @ModelAttribute User deanFaculty,
                                         final @RequestParam(value = "image",required = false) MultipartFile image){

        Map<String,Object> response=new LinkedHashMap<>();

        if(userService.isUserExists(deanFaculty.getEmail())){
            response.put("status",false);
            response.put("message","error this email already exists");

            return ResponseEntity.ok(response);
        }
        final String roleName="ROLE_DEAN_FACULTY";

        userService.addUser(deanFaculty, image, roleName);
        if(userService.isUserExists(deanFaculty.getEmail())){
            response.put("status",true);
            response.put("message","accept dean faculty successful added");
        }else{
            response.put("status",false);
            response.put("message","error dean faculty don't added");
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/remove/user/by/id", produces = "application/json")
    public ResponseEntity removeUserById(final @RequestParam("userId")int userId){

        Map<String,Object>response=new LinkedHashMap<>();

        if(!userService.isUserExistsById(userId)){
            response.put("status",false);
            response.put("message","error user not found with this id");

            return ResponseEntity.ok(response);
        }
        userService.removeUserById(userId);
        if(!userService.isUserExistsById(userId)){
            response.put("status",true);
            response.put("message","accept user successful removed");
        }else{
            response.put("status",false);
            response.put("message","error user don't removed");
        }

        return ResponseEntity.ok(response);
    }

}
