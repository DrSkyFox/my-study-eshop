package ru.geekbrains.serviceImplementation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.controllers.repr.RoleRepr;
import ru.geekbrains.persist.model.accounts.Role;
import ru.geekbrains.persist.repositories.accounts.RoleRepository;
import ru.geekbrains.service.RoleService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(RoleRepr roleRepr) {
        Role role = new Role();
        role.setId(roleRepr.getId());
        role.setName(roleRepr.getName());
        roleRepository.save(role);
    }

    @Override
    public List<RoleRepr> findAll() {
        return roleRepository.findAll().stream().map(RoleRepr::new).collect(Collectors.toList());
    }

    @Override
    public Optional<RoleRepr> findById(Long id) {
        return roleRepository.findById(id).map(RoleRepr::new);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

}
