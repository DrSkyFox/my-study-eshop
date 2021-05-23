package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geekbrains.controllers.repr.RoleRepr;
import ru.geekbrains.error.NotFoundException;
import ru.geekbrains.service.RoleService;

import javax.validation.Valid;


@Controller
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public String adminRolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("roles", roleService.findAll());
        return "users";
    }

    @GetMapping("/role/{id}/edit")
    public String adminEditRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("user", roleService.findById(id).orElseThrow(NotFoundException::new));
        return "role_form";
    }

    @GetMapping("role/create")
    public String adminCreateRole(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("user", new RoleRepr());
        return "role_form";
    }

    @PostMapping("/role")
    public String adminUpsertRole(@Valid RoleRepr roleRepr, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Roles");
        if(bindingResult.hasErrors()) {
            return "user_form";
        }

        roleService.save(roleRepr);
        return "redirect:/roles";
    }

    @DeleteMapping("/role/{id}/delete")
    public String adminDeleteRole(Model model, @PathVariable("id") Long id) {
        roleService.delete(id);
        return "redirect:/roles";
    }





}
