package al.deandmorina.sportscenter.controller;

import al.deandmorina.sportscenter.entity.Role;
import al.deandmorina.sportscenter.payload.saveDTO.RoleSaveDTO;
import al.deandmorina.sportscenter.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/create")
    public Role createRole(@RequestBody RoleSaveDTO roleSaveDTO) {
        return roleService.createRole(roleSaveDTO);
    }
}
