package com.securebank.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.securebank.entity.User;
import com.securebank.enums.RoleName;
import com.securebank.enums.UsersStatus;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.repository.UserRepo;
import com.securebank.service.PermissionService;
import com.securebank.service.UserManagementService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService{

    private final UserRepo userRepository;	
    private final PermissionService permissionService;

    @Override
    public String approveUser(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found "+userId));
        
        if(user.getRoles().contains(RoleName.MANAGER)) {
        	permissionService.checkManagerActive(user);
        }

        if (user.getStatus() != UsersStatus.PENDING && 
        	user.getStatus() != UsersStatus.REJECTED &&
        	user.getStatus() != UsersStatus.UNBLOCKED &&
        	user.getStatus() != UsersStatus.INACTIVE
        		) {
            throw new ResourceNotFoundException(
            		"Only pending, rejected , unblocked and Inactive users can be activated");
        }
        user.setStatus(UsersStatus.ACTIVE);

        userRepository.save(user);

        return "User approved successfully";
    }

    @Override
    public String rejectUser(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found "+userId));

        if(user.getRoles().contains(RoleName.MANAGER)) {
        	permissionService.checkManagerActive(user);
        }
        
        if (user.getStatus() != UsersStatus.PENDING) {
            throw new ResourceNotFoundException(
                    "Only pending users can be rejected");
        }

        user.setStatus(UsersStatus.REJECTED);

        userRepository.save(user);

        return "User rejected successfully";
    }

    @Override
    public String blockUser(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found "+userId));

        if(user.getRoles().contains(RoleName.MANAGER)) {
        	permissionService.checkManagerActive(user);
        }
        
        user.setStatus(UsersStatus.BLOCKED);

        userRepository.save(user);

        return "User blocked...! ";
    }

	@Override
	public String unblockUser(Integer userId) {
		
		 User user = userRepository.findById(userId)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("User not found "+userId));

	        if(user.getRoles().contains(RoleName.MANAGER)) {
	        	permissionService.checkManagerActive(user);
	        }
	        
	        user.setStatus(UsersStatus.UNBLOCKED);

	        userRepository.save(user);

	        return "User Unblocked successfully";
	}
}