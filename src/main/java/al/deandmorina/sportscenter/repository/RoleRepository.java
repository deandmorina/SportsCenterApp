package al.deandmorina.sportscenter.repository;

import al.deandmorina.sportscenter.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(long id);

    Role findByName(String name);
}
