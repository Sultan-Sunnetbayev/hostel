package com.group.newpage.hostel.controllers;

import com.group.newpage.hostel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    private final UserService userService;

    @Value("${image.path}")
    private String userImagePath;

    @Autowired
    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity login(final @RequestParam("email")String email,
                                final @RequestParam("password")String password){

        return null;
    }

    @PostMapping(path = "/logout", produces = "application/json")
    public ResponseEntity logout(final @RequestHeader("Authorization")String accessToken){

        return null;
    }

    @GetMapping(path ="/home/sultan/data/imageUsers/{image}",produces = "application/json")
    public ResponseEntity getImage(@PathVariable("image")String imageName) throws IOException {

        File image=new File(userImagePath+"/"+imageName);
        Map<String, Object> response=new LinkedHashMap<>();

        if(image.exists()){

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(image)))
                    .body(Files.readAllBytes(image.toPath()));
        }else{
            response.put("status",false);
            response.put("message","error image not found");

            return ResponseEntity.ok(response);
        }
    }

}
