package al.deandmorina.sportscenter.controller;

import al.deandmorina.sportscenter.payload.responseDTO.UserResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.UserSaveDTO;
import al.deandmorina.sportscenter.payload.updateDTO.PasswordUpdateDTO;
import al.deandmorina.sportscenter.payload.updateDTO.UserUpdateDTO;
import al.deandmorina.sportscenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserResponseDTO> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping("/create")
    public UserResponseDTO createUser(@Valid @RequestBody UserSaveDTO userSaveDTO) {
        return this.userService.createUser(userSaveDTO);
    }

    @PatchMapping("/update/{id}")
    public UserResponseDTO updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO, @PathVariable("id") long id) {
        return this.userService.updateUser(userUpdateDTO, id);
    }

    @PatchMapping("/update-password")
    public void updatePassword(@Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO, Principal principal) throws Exception {
        this.userService.updatePassword(passwordUpdateDTO, principal);
    }
}
