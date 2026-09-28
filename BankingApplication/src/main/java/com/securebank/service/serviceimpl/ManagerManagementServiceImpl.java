package com.securebank.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.securebank.entity.User;
import com.securebank.enums.UsersStatus;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.repository.UserRepo;
import com.securebank.service.ManagerManagementService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerManagementServiceImpl implements ManagerManagementService {

	private final UserRepo userRepository;

	@Override
	public String approveManager(Integer userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager not found " + userId));

		if (user.getStatus() != UsersStatus.PENDING &&
				user.getStatus() != UsersStatus.REJECTED &&
				user.getStatus() != UsersStatus.UNBLOCKED &&
				user.getStatus() != UsersStatus.INACTIVE) {
			throw new ResourceNotFoundException("Only pending, rejected or blocked Manager can be activated");
		}
		user.setStatus(UsersStatus.ACTIVE);

		userRepository.save(user);

		return "Manager approved successfully";

	}

	@Override
	public String rejectManager(Integer userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager not found " + userId));

		if (user.getStatus() != UsersStatus.PENDING) {
			throw new ResourceNotFoundException("Only pending users can be rejected");
		}

		user.setStatus(UsersStatus.REJECTED);

		userRepository.save(user);

		return "Manager rejected successfully";
	}

	@Override
	public String blockManager(Integer userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found " + userId));

		user.setStatus(UsersStatus.BLOCKED);

		userRepository.save(user);

		return "Manager blocked ...! ";
	}

	@Override
	public String unblockManager(Integer userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found " + userId));

		user.setStatus(UsersStatus.UNBLOCKED);

		userRepository.save(user);

		return "Manager unblocked successfully";
	}

	@Override
	public String inactiveManager(Integer userId) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found " + userId));

		user.setStatus(UsersStatus.INACTIVE);

		userRepository.save(user);

		return "Manager blocked successfully";
	}

}
