package com.securebank.service.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.securebank.dto.managerdto.ManagerRequestDto;
import com.securebank.dto.managerdto.ManagerResponseDto;
import com.securebank.dto.managerdto.ManagerUpdateRequestDto;
import com.securebank.dto.managerdto.ManagerUpdateResponseDto;
import com.securebank.entity.User;
import com.securebank.enums.RoleName;
import com.securebank.enums.UsersStatus;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.mapper.ManagerMapper;
import com.securebank.repository.UserRepo;
import com.securebank.service.ManagerService;
import com.securebank.service.PermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

	private final UserRepo userRepo;
	private final ManagerMapper managerMapper;
	private final PasswordEncoder encoder;
	private final PermissionService permissionService;

	@Override
	public ManagerResponseDto getMyProfile(String email) {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("No Exists Any Manager On this email " + email));

		permissionService.checkManagerActive(user);
		
		return managerMapper.entityToDto(user);
	}

	@Override
	public ManagerResponseDto updateMyProfile(String email, ManagerRequestDto dto) {

		User exUser = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("No Exists Any Manager On this email " + email));

		 permissionService.checkManagerActive(exUser);
		
		exUser.setUsername(dto.username());
		exUser.setEmail(dto.email());
		exUser.setAddress(dto.address());
		exUser.setMobile(dto.mobile());
		exUser.setPassword(encoder.encode(dto.password()));
		exUser.setStatus(UsersStatus.PENDING);

		User save = userRepo.save(exUser);

		return managerMapper.entityToDto(save);
	}

	@Override
	public ManagerUpdateResponseDto updateMyProfileByAdmin(Integer id, ManagerUpdateRequestDto dto) {

		User exUser = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Exists Any Manager On this email " + id));

		exUser.setUsername(dto.username());
		exUser.setEmail(dto.email());
		exUser.setAddress(dto.address());
		exUser.setMobile(dto.mobile());
		exUser.setRoles(new HashSet<>(Set.of(dto.roles())));
		exUser.setPassword(encoder.encode(dto.password()));
		exUser.setStatus(dto.status());

		User save = userRepo.save(exUser);

		return managerMapper.updateEntityToDto(save);
	}

	@Override
	public List<ManagerResponseDto> getPendingManager() {
		
		List<ManagerResponseDto> list = 
				userRepo.findByRolesAndStatus(RoleName.MANAGER,UsersStatus.PENDING)
		.stream().map(managerMapper::entityToDto).toList();
		
		return list;
	}

	@Override
	public List<ManagerResponseDto> getRejectedManager() {
		
		List<ManagerResponseDto> list =
				userRepo.findByRolesAndStatus(RoleName.MANAGER,UsersStatus.REJECTED)
				.stream().map(managerMapper::entityToDto).toList();
				return list;
	}

	@Override
	public List<ManagerResponseDto> getBlockedManager() {
		
		List<ManagerResponseDto> list = 
				userRepo.findByRolesAndStatus(RoleName.MANAGER,UsersStatus.BLOCKED)
				.stream().map(managerMapper::entityToDto).toList();
				return list;
	}
	
	@Override
	public List<ManagerResponseDto> getUnblockedManager() {
		
		List<ManagerResponseDto> list = 
				userRepo.findByRolesAndStatus(RoleName.MANAGER,UsersStatus.UNBLOCKED)
				.stream().map(managerMapper::entityToDto).toList();
				return list;
	}

	@Override
	public List<ManagerResponseDto> getInactiveManager() {
		
		List<ManagerResponseDto> list = 
				userRepo.findByRolesAndStatus(RoleName.MANAGER,UsersStatus.INACTIVE)
				.stream().map(managerMapper::entityToDto).toList();
				return list;
	}

	@Override
	public List<ManagerResponseDto> getAllManager() {
		
		List<ManagerResponseDto> list = 
				userRepo.findByRolesAndStatus(RoleName.MANAGER,UsersStatus.ACTIVE).stream().map(managerMapper::entityToDto).toList();
		
		return list;
	}
	

	@Override
	public ManagerResponseDto getManagerById(Integer id) {
		
		User user = userRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("No Exists Any Manager On this email " + id));
		
		permissionService.checkManagerActive(user);
		
		return managerMapper.entityToDto(user);
	}
	
	@Override
	public ManagerResponseDto changeManagerRole(Integer id, RoleName role) {
		
		 User exUser = userRepo.findById(id).orElseThrow(() -> 
         new ResourceNotFoundException("User Not found on this id "+id));
		 
		 exUser.setRoles(new HashSet<>(Set.of(role)));
		 
		 if(!exUser.getRoles().contains(RoleName.MANAGER)) {
			 throw new ResourceNotFoundException("Only can change Manager Role");
		 }
		
		 User save = userRepo.save(exUser);
		 
		return managerMapper.entityToDto(save);
	}

}
