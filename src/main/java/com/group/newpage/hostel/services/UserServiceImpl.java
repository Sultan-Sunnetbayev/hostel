package com.group.newpage.hostel.services;

import com.group.newpage.hostel.daos.RoleRepository;
import com.group.newpage.hostel.daos.UserRepository;
import com.group.newpage.hostel.dtos.UserDTO;
import com.group.newpage.hostel.helper.FileUploadUtil;
import com.group.newpage.hostel.models.Role;
import com.group.newpage.hostel.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${image.path}")
    private String imagePath;
    @Value("${path.default.image.user}")
    private String defaultImageUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void addUser(final User user, final MultipartFile image, final String roleName){

        if(isUserExists(user.getEmail())){

            return;
        }
        Role role=roleRepository.findRoleByName(roleName);

        if(role==null){

            return;
        }
        User savedUser=User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(role)
                .build();

        if(image!=null && !image.isEmpty()){

            final String fileName= UUID.randomUUID().toString()+"_"+image.getOriginalFilename();

            try {

                FileUploadUtil.loadFile(imagePath,fileName, image);
                savedUser.setImagePath(imagePath+"/"+fileName);

            } catch (IOException ioException) {

                ioException.printStackTrace();
            }
        }else{

            File defaultImage=new File(defaultImageUser);
            if(defaultImage.exists()) {
                final String fileName = UUID.randomUUID().toString() + defaultImage.getName();
                try {

                    FileUploadUtil.saveDefaultImageUser(imagePath, fileName, defaultImage);
                    savedUser.setImagePath(imagePath+"/"+fileName);

                } catch (IOException ioException) {

                    ioException.printStackTrace();
                }
            }
        }

        userRepository.save(savedUser);

        return;
    }

    @Override
    public boolean isUserExists(final String email) {

        if(userRepository.findUserByEmail(email)!=null){

            return true;
        }else{

            return false;
        }
    }

    @Override
    @Transactional
    public void editUserById(final User user, final MultipartFile image){

        User editedUser=userRepository.findUserById(user.getId());

        if(editedUser==null){

            return;
        }
        User check=userRepository.findUserByEmail(user.getEmail());
        if(check!=null && check.getId()!=user.getId()){

            return;
        }
        if(user.getName()!=null && !user.getName().isEmpty()) {
            editedUser.setName(user.getName());
        }
        if(user.getSurname()!=null && !user.getSurname().isEmpty()) {
            editedUser.setSurname(user.getSurname());
        }
        if(user.getEmail()!=null && !user.getEmail().isEmpty()) {
            editedUser.setEmail(user.getEmail());
        }
        if(user.getPassword()!=null && !user.getPassword().isEmpty()) {
            editedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(image!=null && !image.isEmpty()){

            if(editedUser.getImagePath()!=null && !editedUser.getImagePath().isEmpty()) {
                File file = new File(editedUser.getImagePath());

                if(file.exists()){

                    file.delete();
                }
                final String fileName=UUID.randomUUID().toString()+"/"+image.getOriginalFilename();

                try {

                    FileUploadUtil.loadFile(imagePath, fileName, image);
                    editedUser.setImagePath(imagePath+"/"+fileName);

                } catch (IOException ioException) {

                    ioException.printStackTrace();
                }

            }
        }
        userRepository.save(editedUser);

        return;
    }

    @Override
    @Transactional
    public void removeUserById(final int userId){

        User user=userRepository.findUserById(userId);

        if(user==null){

            return;
        }
        if(Objects.equals(user.getRole().getName(),"ROLE_ADMIN")){

            return;
        }
        if(user.getImagePath()!=null && !user.getImagePath().isEmpty()){
            File image=new File(user.getImagePath());

            if(image.exists()){
                image.delete();
            }
        }
        userRepository.deleteById(userId);

        return;
    }

    @Override
    public List<UserDTO>getAllUserDTOSBesideAdmin(){

        final String roleName="ROLE_ADMIN";

        return userRepository.findUsersByRole(roleName).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private UserDTO toDTO(User user) {

        UserDTO userDTO=UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .imagePath(user.getImagePath())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();

        return userDTO;
    }

    @Override
    public UserDTO getUserDTOById(final int userId){

        User user=userRepository.findUserById(userId);

        if(user!=null){

            return toDTO(user);
        }else{

            return null;
        }
    }

    @Override
    public boolean isUserExistsById(final int userId){

        if(userRepository.findUserById(userId)!=null){

            return true;
        }else{

            return false;
        }
    }

}
