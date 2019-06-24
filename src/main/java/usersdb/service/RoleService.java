package usersdb.service;

import usersdb.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getRoles();

    void saveRole(Role role);

    Role getRole(int theId);

    void deleteRole(int theId);

    Role findRoleByName(String role);

    Set<Role> getRolSetByRoleName(String role);
}
