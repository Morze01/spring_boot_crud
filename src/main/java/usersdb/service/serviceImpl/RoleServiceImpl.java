package usersdb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersdb.dao.RoleDAO;
import usersdb.model.Role;
import usersdb.service.RoleService;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }

    @Override
    public void saveRole(Role role) {
        roleDAO.saveRole(role);
    }

    @Override
    public Role getRole(int theId) {
        return roleDAO.getRole(theId);
    }

    @Override
    public void deleteRole(int theId) {
        roleDAO.deleteRole(theId);
    }

    @Override
    public Role findRoleByName(String role) {
        return roleDAO.findeRoleByName(role);
    }

    @Override
    public Set<Role> getRolSetByRoleName(String role) {
        return roleDAO.getRolSetByRoleName(role);
    }
}
