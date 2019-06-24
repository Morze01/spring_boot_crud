package usersdb.dao;


import usersdb.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDAO {
    List<Role> getRoles();

    void saveRole(Role role);

    Role getRole(int theId);

    void deleteRole(int theId);

    Role findeRoleByName(String role);

    Set<Role> getRolSetByRoleName(String role);

    Set<Role> getRolSetByRoleCollection(List<String> roleList);
}
