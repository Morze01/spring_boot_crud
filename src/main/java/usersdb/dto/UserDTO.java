package usersdb.dto;

import usersdb.model.Role;

import java.util.Set;

public class UserDTO {
    private Set<Role> roleList;

    public Set<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<Role> rolesList) {
        this.roleList = rolesList;
    }
}
