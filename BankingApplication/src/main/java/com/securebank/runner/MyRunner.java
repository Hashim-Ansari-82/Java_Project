   package com.securebank.runner;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.securebank.entity.User;
import com.securebank.enums.RoleName;
import com.securebank.enums.UsersStatus;
import com.securebank.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {

	private final UserRepo userRepo;
	private final PasswordEncoder encode;
	
	@Override
	public void run(String... args) throws Exception {
		
     if(!userRepo.existsByEmail("admin123@gmail.com")) {
    	 
    	 User admin = new User();
    	 admin.setUsername("Hashim Ansari");
    	 admin.setEmail("admin123@gmail.com");
    	 admin.setPassword(encode.encode("admin143"));
    	 admin.setRoles(Set.of(RoleName.ADMIN));
    	 admin.setMobile("7876535486");
    	 admin.setEnabled(true);
    	 admin.setStatus(UsersStatus.ACTIVE);
    	 admin.setAddress("Koilsa sant kabir nagar");
    	 
    	 userRepo.save(admin);
       }
	}

	
}
