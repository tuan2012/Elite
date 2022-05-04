package com.example.demo;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    private UserRepository userRepository;

    @Autowired
    public DemoApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//    @Bean
//    CommandLineRunner init (){
//        return args -> {
//            User user = new User("admin","admin@admin.com","admin","admin",Role.builder().name(RoleConstants.ADMIN).build());
//            userRepository.save(user);
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}
