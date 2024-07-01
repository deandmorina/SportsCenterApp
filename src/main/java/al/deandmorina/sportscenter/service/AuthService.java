package al.deandmorina.sportscenter.service;

import al.deandmorina.sportscenter.payload.responseDTO.JWTAuthResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.LoginDTO;
import al.deandmorina.sportscenter.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JWTProvider provider;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JWTProvider provider) {
        this.authenticationManager = authenticationManager;
        this.provider = provider;
    }

    public JWTAuthResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication;
        try {
            authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        String accessToken = this.provider.generateToken(authentication);
        String refreshToken = this.provider.generateRefreshToken(authentication);
        return new JWTAuthResponseDTO(accessToken, refreshToken);
    }
}
