package com.anasbouabid.centre_dentaire.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anasbouabid.centre_dentaire.models.ERole;
import com.anasbouabid.centre_dentaire.models.Role;
import com.anasbouabid.centre_dentaire.models.User;
import com.anasbouabid.centre_dentaire.payload.request.LoginRequest;
import com.anasbouabid.centre_dentaire.payload.request.SignupRequest;
import com.anasbouabid.centre_dentaire.payload.response.JwtResponse;
import com.anasbouabid.centre_dentaire.payload.response.MessageResponse;
import com.anasbouabid.centre_dentaire.repository.RoleRepository;
import com.anasbouabid.centre_dentaire.repository.UserRepository;
import com.anasbouabid.centre_dentaire.security.jwt.JwtUtils;
import com.anasbouabid.centre_dentaire.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getFullName(), signUpRequest.getAvatar(), signUpRequest.getIdNumber(),
                signUpRequest.getGender(), signUpRequest.getPhoneNumber(), signUpRequest.getBirthday(),
                signUpRequest.getCreatedAt(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<Role> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role recepRole = roleRepository.findByName(ERole.ROLE_RECEPTIONIST)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(recepRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toString()) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "ROLE_DOCTOR":
                        Role docRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(docRole);

                        break;
                    default:
                        Role recepRole = roleRepository.findByName(ERole.ROLE_RECEPTIONIST)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(recepRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}