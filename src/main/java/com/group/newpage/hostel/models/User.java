package com.group.newpage.hostel.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotBlank(message = "user name is mandatory")
    @NotEmpty(message = "user name is empty")
    private String name;
    @Column(name = "surname")
    @NotBlank(message = "user surname is mandatory")
    @NotEmpty(message = "user surname is empty")
    private String surname;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "email")
    @NotBlank(message = "user email is mandatory")
    @NotEmpty(message = "user email is empty")
    @Email(message = "user email is invalid")
    private String email;
    @Column(name = "password")
    @NotBlank(message = "user password is mandatory")
    @NotEmpty(message = "user passwrod is empty")
    private String password;
    @Column(name = "created")
    @CreationTimestamp
    private Date created;
    @Column(name = "updated")
    @UpdateTimestamp
    private Date updated;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

}
