package usersdb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import usersdb.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Role> getRoles() {
        return entityManager
                .createQuery("select u from Role u", Role.class)
                .getResultList();
    }

    @Override
    public void saveRole(Role role) {
        Role persistUser = entityManager.find(Role.class,role.getId());
        if (persistUser == null) {
            entityManager.persist(role);
        }
    }

    @Override
    public Role getRole(int theId) {
        return entityManager.find(Role.class, theId);
    }

    @Override
    public void deleteRole(int theId) {
        Role role = entityManager.find(Role.class,theId);
        entityManager.remove(role);
    }

    @Override
    public Role findeRoleByName(String role) {
        return entityManager
                .createQuery("select u from Role u where u.name=:Name", Role.class)
                .setParameter("Name",role)
                .getResultList()
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public Set<Role> getRolSetByRoleName(String role) {
        return entityManager
                .createQuery("select u from Role u where u.name=:Name", Role.class)
                .setParameter("Name",role)
                .getResultList()
                .stream()
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Role> getRolSetByRoleCollection(List<String> roleList) {
//        Map<String, String> paramaterMap = new HashMap<>();
//        List<String> whereCause = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select u from Role u where u.name contains :roleList ");

//        for (String sRole: roleList) {
//            whereCause.add("u.name=:");
//            whereCause.add(sRole);
//            paramaterMap.put(sRole,sRole);
//        }

        //queryBuilder.append(" where " + StringUtils.join(whereCause, " or "));

//        Query jpaQuery = entityManager.createQuery(queryBuilder.toString(), Role.class)
//                .setParameter("roleList",roleList);
//        for(String key :paramaterMap.keySet()) {
//            jpaQuery.setParameter(key, paramaterMap.get(key));
//        }

        return new HashSet<Role>(
                entityManager
                        .createQuery("select u from Role u where u.name contains :RoleList ", Role.class)
                        .setParameter("RoleList",roleList)
                        .getResultList());

    }
}
