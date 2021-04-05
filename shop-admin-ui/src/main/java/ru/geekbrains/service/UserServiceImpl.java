package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.persist.repo.RoleRepository;
import ru.geekbrains.persist.repo.UserRepository;
import ru.geekbrains.specifications.UserSpecification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,  RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public List<UserRepr> findAll() {
        return userRepository.findAll().stream().map(UserRepr::new).collect(Collectors.toList());
    }

    @Override
    public List<UserRepr> findWithFilter(String usernameFilter) {
        return userRepository.findUserByUsernameLike(usernameFilter).stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserRepr> findWithFilter(String usernameFilter,Integer minAge, Integer maxAge,
                                         Integer page, Integer size, String sortField) {
        Specification<User> spec = Specification.where(null);
        if (usernameFilter != null && !usernameFilter.isBlank()) {
            spec = spec.and(UserSpecification.usernameLike(usernameFilter));
        }
        if (minAge != null) {
            spec = spec.and(UserSpecification.minAge(minAge));
        }
        if (maxAge != null) {
            spec = spec.and(UserSpecification.maxAge(maxAge));
        }
        return userRepository.findAll(spec, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortField)))
                .map(UserRepr::new);
    }

    @Override
    public Optional<UserRepr> findById(long id) {
        return userRepository.findById(id).map(UserRepr::new);
    }

    @Transactional
    @Override
    public void save(UserRepr user) {
        User userToSave = new User(user);
//        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        logger.info("user to save: {} ", user.toString());

        userRepository.save(userToSave);
        if (user.getId() == null) {
            user.setId(userToSave.getId());
        }

        if(userToSave.getRoles() == null) {
            userToSave.setRoles(roleRepository.findById(1l).stream().collect(Collectors.toSet()));
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
