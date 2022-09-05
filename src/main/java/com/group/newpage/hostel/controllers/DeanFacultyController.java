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
@RequestMapping("/api/v1/deanFaculty")
public class DeanFacultyController {

    private final UserService userService;

    @Autowired
    public DeanFacultyController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/add/liable/participation", produces = "application/json")
    public ResponseEntity addLiableParticipation(final @ModelAttribute User liableParticipation,
                                                 final @RequestParam("image") MultipartFile image){

        Map<String, Object> response=new LinkedHashMap<>();

        if(userService.isUserExists(liableParticipation.getEmail())){
            response.put("status",false);
            response.put("message","error email user already exists");

            return ResponseEntity.ok(response);
        }
        final String roleName="ROLE_LIABLE_PARTICIPATION";

        userService.addUser(liableParticipation, image, roleName);
        if(userService.isUserExists(liableParticipation.getEmail())){
            response.put("status",true);
            response.put("message","accept liable participation successful added");
        }else{
            response.put("status",false);
            response.put("message","error liable participation don't added");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/remove/liableParticipation/by/id", produces = "application/json")
    public ResponseEntity removeLiableParticipationById(final @RequestParam("userId")int userId){

        Map<String, Object>response=new LinkedHashMap<>();

        if(!userService.isUserExistsById(userId)){
            response.put("status",false);
            response.put("message","error user not found with this id");

            return ResponseEntity.ok(response);
        }
        userService.removeUserById(userId);
        if(!userService.isUserExistsById(userId)){
            response.put("status",true);
            response.put("message","accept liable participation successful removed");
        }else{
            response.put("status",false);
            response.put("message","error liable participation don't removed");
        }

        return ResponseEntity.ok(response);
    }

}
