package al.deandmorina.sportscenter.controller;

import al.deandmorina.sportscenter.payload.responseDTO.JWTAuthResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.LoginDTO;
import al.deandmorina.sportscenter.service.AuthService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/refreshToken")
    public JWTAuthResponseDTO refreshToken() {
        return this.authService.refreshToken();
    }
}
