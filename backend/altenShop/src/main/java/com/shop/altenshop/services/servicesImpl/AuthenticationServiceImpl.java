package com.shop.altenshop.services.servicesImpl;

import com.shop.altenshop.DTOs.AuthenticationRequest;
import com.shop.altenshop.DTOs.AuthenticationResponse;
import com.shop.altenshop.DTOs.RegisterRequest;
import com.shop.altenshop.entities.Token;
import com.shop.altenshop.entities.User;
import com.shop.altenshop.enums.TokenType;
import com.shop.altenshop.repositories.TokenRepository;
import com.shop.altenshop.repositories.UserRepository;
import com.shop.altenshop.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.shop.altenshop.entities.Role.USER;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        try {
            if (repository.findByEmail(request.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email already in use: " + request.getEmail());
            }
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(USER)
                    .build();

            var savedUser = repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);

            saveUserToken(savedUser, jwtToken);

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .id(savedUser.getId())
                    .email(savedUser.getEmail())
                    .firstname(savedUser.getFirstname())
                    .username(savedUser.getUsername())
                    .role(savedUser.getRole())
                    .build();

        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred during registration.", ex);
        }
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .id(user.getId())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());

        if (validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}