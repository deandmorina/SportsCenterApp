package al.deandmorina.sportscenter.service;

import al.deandmorina.sportscenter.entity.Role;
import al.deandmorina.sportscenter.entity.User;
import al.deandmorina.sportscenter.payload.responseDTO.UserResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.UserSaveDTO;
import al.deandmorina.sportscenter.payload.updateDTO.UserUpdateDTO;
import al.deandmorina.sportscenter.repository.RoleRepository;
import al.deandmorina.sportscenter.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> response = users.stream().map(user -> modelMapper.map(user, UserResponseDTO.class)).collect(Collectors.toList());
        return response;
    }

    public UserResponseDTO createUser(UserSaveDTO userSaveDTO) {
        User user = modelMapper.map(userSaveDTO, User.class);
        Role role = this.roleRepository.findByName("ROLE_CLIENT");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userSaveDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    public UserResponseDTO updateUser(UserUpdateDTO userUpdateDTO, long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }
}
