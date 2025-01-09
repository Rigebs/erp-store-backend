package com.rige.services.impl;

import com.rige.dto.request.LoginRequest;
import com.rige.dto.request.RegisterRequest;
import com.rige.entities.RoleEntity;
import com.rige.entities.UserEntity;
import com.rige.repositories.IRoleRepository;
import com.rige.repositories.IUserRepository;
import com.rige.security.JwtUtils;
import com.rige.services.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final IUserRepository iUserRepository;
    private final IRoleRepository iRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String login(LoginRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
        return jwtUtils.createToken(authenticatedUser.getId(), authenticatedUser.getUsername(), authenticatedUser.getEmail(), authenticatedUser.getAuthorities());
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        if (iUserRepository.existsByEmailOrUsername(registerRequest.getEmail(), registerRequest.getUsername())) {
            throw new IllegalArgumentException("El correo electrónico o nombre de usuario ya está en uso");
        }

        RoleEntity userRole = iRoleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalArgumentException("El rol ROLE_USER no existe en la base de datos"));

        UserEntity newUser = UserEntity.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .roles(Set.of(userRole))
                .enabled(true)
                .expired(false)
                .locked(false)
                .build();

        iUserRepository.save(newUser);

        return "Usuario registrado correctamente";
    }


}
