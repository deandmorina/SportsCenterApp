package al.deandmorina.sportscenter.controller;

import al.deandmorina.sportscenter.payload.responseDTO.JWTAuthResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.LoginDTO;
import al.deandmorina.sportscenter.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    private JWTAuthResponseDTO login(@RequestBody LoginDTO loginDTO) {
        return this.authService.login(loginDTO);
    }
}
