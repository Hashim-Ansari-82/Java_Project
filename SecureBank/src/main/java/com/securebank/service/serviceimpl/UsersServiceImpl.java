package com.securebank.service.serviceimpl;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.securebank.dto.userdto.ChangePasswordRequestDto;
import com.securebank.dto.userdto.UsersRegisterResponseDto;
import com.securebank.dto.userdto.UsersUpdateResponseDto;
import com.securebank.dto.userdto.UserUpdateRequestDto;
import com.securebank.entity.User;
import com.securebank.enums.RoleName;
import com.securebank.enums.UsersStatus;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.mapper.UsersMapper;
import com.securebank.repository.UserRepo;
import com.securebank.service.PermissionService;
import com.securebank.service.UsersService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

	private final UserRepo userRepo;
	private final UsersMapper userMapper;
	private final PasswordEncoder encoder;
	private final PermissionService permissionService;

	@Override
	public List<UsersRegisterResponseDto> getAllUsers(String email) {

		User user = userRepo.findByEmail(email).orElseThrow(() -> 
		new ResourceNotFoundException("No Manager on this email "+email));
		
		permissionService.checkManagerActive(user);
		
		List<UsersRegisterResponseDto> list = userRepo.findByRolesAndStatus(RoleName.USER,UsersStatus.ACTIVE)
				.stream().map(userMapper::entityToDto).toList();
		return list;
	}

	@Override
	public UsersRegisterResponseDto getById(Integer id) {

		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Not found on this id is " + id));

		permissionService.checkUserActive(user);
		
		return userMapper.entityToDto(user);
	}

	@Override
	public UsersRegisterResponseDto getByEmail(String email) {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User Not found on this email " + email));

		permissionService.checkUserActive(user);
		
		return userMapper.entityToDto(user);
	}

	@Override
	public UsersUpdateResponseDto updateByEmail(String email, UserUpdateRequestDto dto) {

		User exUser = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User Not found on this id is " + email));

		permissionService.checkUserActive(exUser);
		
		exUser.setUsername(dto.username());
		exUser.setMobile(dto.mobile());
		exUser.setAddress(dto.address());
		exUser.setEmail(dto.email());
		exUser.setPassword(encoder.encode(dto.password()));
		exUser.setStatus(UsersStatus.PENDING);

		User save = userRepo.save(exUser);

		return userMapper.updateEntityToDto(save);
	}
	
	@Override
	public void deleteByEmail(String email) {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User Not found on this email is " + email));

		permissionService.checkUserActive(user);
		
		userRepo.delete(user);
	}
	
	@Override
	public void changePassword(
	        String email,
	        ChangePasswordRequestDto dto) {

	    User user = userRepo.findByEmail(email)
	            .orElseThrow(() ->
	                new ResourceNotFoundException("User not found"));

	    permissionService.checkUserActive(user);
	    
	    if (!encoder.matches(
	            dto.getOldPassword(),
	            user.getPassword())) {

	        throw new RuntimeException("Incorrect password");
	    }

	    if (!dto.getNewPassword()
	            .equals(dto.getConfirmPassword())) {

	        throw new RuntimeException(
	                "password does not match");
	    }

	    String encodedPassword =
	    		encoder.encode(dto.getNewPassword());

	    user.setPassword(encodedPassword);

	    userRepo.save(user);
	}

	@Override
	public List<UsersRegisterResponseDto> getPendingUsers(String email) {
		
		User user = userRepo.findByEmail(email).orElseThrow(() -> 
		new ResourceNotFoundException("No Manager on this email "+email));
		
		permissionService.checkManagerActive(user);
		
		List<UsersRegisterResponseDto> list =
				userRepo.findByStatus(UsersStatus.PENDING)
				.stream().map(userMapper::entityToDto).toList();
		
		return list;
	}

	@Override
	public List<UsersRegisterResponseDto> getRejectedUsers(String email) {
		
		User user = userRepo.findByEmail(email).orElseThrow(() -> 
		new ResourceNotFoundException("No Manager on this email "+email));
		
		permissionService.checkManagerActive(user);
		
		List<UsersRegisterResponseDto> list =
				userRepo.findByStatus(UsersStatus.REJECTED)
				.stream().map(userMapper::entityToDto).toList();
		
		return list;
	}

	@Override
	public List<UsersRegisterResponseDto> getBlockedUsers(String email) {
		
		User user = userRepo.findByEmail(email).orElseThrow(() -> 
		new ResourceNotFoundException("No Manager on this email "+email));
		
		permissionService.checkManagerActive(user);
		
		List<UsersRegisterResponseDto> list =
				userRepo.findByStatus(UsersStatus.BLOCKED)
				.stream().map(userMapper::entityToDto).toList();
		
		return list;
	}
	@Override
	public List<UsersRegisterResponseDto> getInactiveUsers(String email) {
		
		User user = userRepo.findByEmail(email).orElseThrow(() -> 
		new ResourceNotFoundException("No Manager on this email "+email));
		
		permissionService.checkManagerActive(user);
		
		List<UsersRegisterResponseDto> list =
				userRepo.findByStatus(UsersStatus.INACTIVE)
				.stream().map(userMapper::entityToDto).toList();
		
		return list;
	}
	@Override
	public List<UsersRegisterResponseDto> getUnblockedUsers(String email) {
		
		User user = userRepo.findByEmail(email).orElseThrow(() -> 
		new ResourceNotFoundException("No Manager on this email "+email));
		
		permissionService.checkManagerActive(user);
		
		List<UsersRegisterResponseDto> list =
				userRepo.findByStatus(UsersStatus.UNBLOCKED)
				.stream().map(userMapper::entityToDto).toList();
		
		return list;
	}

	@Override
	public void deactivateUser(Integer id) {
		
         User exUser = userRepo.findById(id).orElseThrow(() -> 
         new ResourceNotFoundException("User Not found on this id "+id));
         
 		permissionService.checkManagerActive(exUser);
         
         exUser.setStatus(UsersStatus.INACTIVE);
         
         userRepo.save(exUser);
	}

	@Override
	public UsersUpdateResponseDto changeUserRole(Integer id, RoleName role) {
		
		 User exUser = userRepo.findById(id).orElseThrow(() -> 
         new ResourceNotFoundException("User Not found on this id "+id));
		 
		 permissionService.checkManagerActive(exUser);
		 
		 if(!exUser.getRoles().contains(RoleName.USER)) {
			 throw new ResourceNotFoundException("Only can change User Role");
		 }
		
		 exUser.setRoles(Set.of(role));
		 User save = userRepo.save(exUser);
		 
		return userMapper.updateEntityToDto(save);
	}

}
