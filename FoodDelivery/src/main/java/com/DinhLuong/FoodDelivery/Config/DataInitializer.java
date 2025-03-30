package com.DinhLuong.FoodDelivery.Config;

import com.DinhLuong.FoodDelivery.entity.Roles;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.repository.RoleRepository;
import com.DinhLuong.FoodDelivery.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Tạo role ADMIN nếu chưa có
        Roles adminRole = roleRepository.findByRoleName("ROLE_ADMIN").orElseGet(() -> {
            Roles newRole = new Roles();
            newRole.setRoleName("ROLE_ADMIN");
            return roleRepository.save(newRole);
        });

        
        if (userRepository.findByUserName("admin").isEmpty()) {
            Users admin = new Users();
            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("Admin");
            admin.setRoles(adminRole);
            userRepository.save(admin);
            System.out.println(" admin created");
        } else {
            System.out.println(" admin found");
        }
    }
}
