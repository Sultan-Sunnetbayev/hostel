package com.group.newpage.hostel.services;

import com.group.newpage.hostel.dtos.UserDTO;
import com.group.newpage.hostel.models.User;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    @Transactional
    void addUser(User user, MultipartFile image, String roleName);

    boolean isUserExists(String email);

    @Transactional
    void editUserById(User user, MultipartFile image);

    @Transactional
    void removeUserById(int userId);

    List<UserDTO> getAllUserDTOSBesideAdmin();

    UserDTO getUserDTOById(int userId);

    boolean isUserExistsById(int userId);
}
