package al.deandmorina.sportscenter.service;

import al.deandmorina.sportscenter.entity.Role;
import al.deandmorina.sportscenter.payload.saveDTO.RoleSaveDTO;
import al.deandmorina.sportscenter.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role createRole(RoleSaveDTO roleSaveDTO) {
        Role role = new Role();
        role.setName(roleSaveDTO.getName());
        return roleRepository.save(role);
    }

    public Role updateRole(long id, RoleSaveDTO roleSaveDTO) {
        Role role = roleRepository.findRoleById(id);
        role.setName(roleSaveDTO.getName());
        return roleRepository.save(role);
    }

    public void deleteRole(long id) {
        Role role = roleRepository.findRoleById(id);
        role.setDeletedAt(new Date());
        roleRepository.save(role);
    }
}
