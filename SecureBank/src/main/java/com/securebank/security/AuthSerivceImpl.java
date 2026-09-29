package com.securebank.security;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securebank.dto.logindto.LoginRequestDto;
import com.securebank.dto.logindto.LoginResponseDto;
import com.securebank.dto.managerdto.ManagerRequestDto;
import com.securebank.dto.refreshtokendto.AccessTokenResponseDto;
import com.securebank.dto.refreshtokendto.RefreshTokenRequestDto;
import com.securebank.dto.userdto.UsersRegisterRequestDto;
import com.securebank.entity.RefreshToken;
import com.securebank.entity.User;
import com.securebank.enums.MessageType;
import com.securebank.enums.RoleName;
import com.securebank.enums.UsersStatus;
import com.securebank.exception.DuplicateResourceException;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.exception.UnauthorizedException;
import com.securebank.mapper.ManagerMapper;
import com.securebank.mapper.RefreshTokenMapper;
import com.securebank.mapper.UsersMapper;
import com.securebank.repository.RefreshTokenRepo;
import com.securebank.repository.UserRepo;
import com.securebank.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthSerivceImpl implements AuthService{

	private final AuthenticationManager authManager;
	private final JwtService jwtService;
	private final UserRepo userRepo;
	private final UsersMapper userMapper;
	private final ManagerMapper managerMapper;
	private final NotificationService notificationService;
    private final RefreshTokenRepo refreshTokenRepo;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenMapper refreshTokenMapper;
    
	@Override
	@Transactional
	public LoginResponseDto login(LoginRequestDto dto) {

	    Authentication authentication = authManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            dto.getEmail(),
	            dto.getPassword()
	        )
	    );

	    UserDetails userDetails =
	            (UserDetails) authentication.getPrincipal();

	    User user = userRepo.findByEmail(dto.getEmail())
	            .orElseThrow(() ->
	                new ResourceNotFoundException("User not found"));

	    String accessToken = jwtService.generateToken(userDetails);

	    String refreshToken = jwtService.generateRefreshToken(userDetails);

	    RefreshToken refreshTokenEntity = RefreshToken.builder()
	    		.refreshToken(refreshToken)
	    		.user(user)
	            .expiryDate(LocalDate.now().plusDays(7))
	            .build();

	    refreshTokenRepo.save(refreshTokenEntity);
	    notificationService.createNotification(user,
	    		"Your are login Successfully", MessageType.SYSTEM, "Welcome to our Application");

	    return new LoginResponseDto(accessToken, refreshToken);
	}

	@Override
	public String userRegister(UsersRegisterRequestDto dto) {

		if (userRepo.existsByEmail(dto.getEmail()) || userRepo.existsByMobile(dto.getMobile())) {
			throw new DuplicateResourceException(
					"User already exists on this email (" + dto.getEmail() + ") or mobile (" + dto.getMobile() + ")");
		}

		User user = userMapper.dtoToEntity(dto);

		user.setRoles(Set.of(RoleName.USER));
		user.setStatus(UsersStatus.ACTIVE);
		user.setEnabled(true);
		userRepo.save(user);
		
		notificationService.createNotification(
		        user,
		        "Registration Successful",
		        MessageType.SYSTEM,
		        "Welcome to Our Application. Your registration was successful."
		);

		return "User Register Successfully";
	}

	@Override
	public String managerRegister(ManagerRequestDto dto) {

		if (userRepo.existsByEmail(dto.email()) || userRepo.existsByMobile(dto.mobile())) {
			throw new DuplicateResourceException(
					"User already exists on this email (" + dto.email() + ") or mobile (" + dto.mobile() + ")");
		}

		User user = managerMapper.dtoToEntity(dto);

		user.setRoles(Set.of(RoleName.MANAGER));
		user.setStatus(UsersStatus.PENDING);
		user.setEnabled(true);
		userRepo.save(user);
		
		notificationService.createNotification(
		        user,
		        "Registration Successful",
		        MessageType.SYSTEM,
		        "Welcome to Our application. Your registration was successful."
		);

		return "Manager Register Successfully";
	}

	@Override
	@Transactional
	public AccessTokenResponseDto refreshAccessToken(RefreshTokenRequestDto token) {

	    RefreshToken refreshToken = refreshTokenRepo
	            .findByRefreshToken(token.getRefreshToken())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Refresh token not found"));

	    if (refreshToken.getExpiryDate()
	            .isBefore(LocalDate.now())) {

	        refreshTokenRepo.delete(refreshToken);

	        throw new UnauthorizedException(
	                "Refresh token expired");
	    }

	    String username = jwtService.extractUsername(
	            token.getRefreshToken());

	    UserDetails userDetails =
	            userDetailsService.loadUserByUsername(username);

	    String accessToken =
	            jwtService.generateToken(userDetails);

	    AccessTokenResponseDto refreshToAccessToken = refreshTokenMapper.refreshToAccessToken(refreshToken);
	    
	    refreshToAccessToken.setAccessToken(accessToken);
	    
	    return refreshToAccessToken;
	}

}
