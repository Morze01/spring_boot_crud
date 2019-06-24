package usersdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.plugin.liveconnect.SecurityContextHelper;
import usersdb.model.Role;
import usersdb.model.User;
import usersdb.service.RoleService;
import usersdb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/home")
    public String getHomePage(SecurityContextHolder securityContextHolder, Model model) {
        Authentication authentication = securityContextHolder.getContext().getAuthentication();
        User currentName = (User)authentication.getPrincipal();
        model.addAttribute("user",currentName);
        return "userHomePage";
    }

    @RequestMapping("/list")
    public ModelAndView getUsersList() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("users", userService.getUsers());
        mv.setViewName("user-list");
        return mv;
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("user", user);
        //model.addAttribute("userRoleList",new List)
        model.addAttribute("roleList",roleList);
        return "user-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int theId) {
        userService.deleteUser(theId);
        return "redirect:/list";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        List<Role> roleList = roleService.getRoles();
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        theModel.addAttribute("roleList",roleList);
        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User theUser,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("id") String id,
                           @RequestParam("role") List<String> roleList) {

        //Set<Role> userRoleSet = Collections.singleton(roleService.findRoleByName(role));
//        Set<Role> userRoleSet = roleService.getRolSetByRoleName(role);
        Set<Role> userRoleSet = new HashSet<>();
        for (String sRole: roleList) {
            userRoleSet.add(roleService.findRoleByName(sRole));
        }

        User user = userService.getUser(Integer.parseInt(id));
        if (user == null) {
            user = new User(username,passwordEncoder.encode(password),userRoleSet);
            userService.saveUser(user, true);
        } else {
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoleSet(userRoleSet);
            user.setEnabled(true);
            userService.saveUser(user, false);
        }

        return "redirect:/list";
    }

    @PostMapping("/register/process")
    public String processRegistration(@RequestParam("username") String username,
                                      @RequestParam("password") String password) {

        userService.registerNewUser(username,password);

        return "redirect:/login";
    }

    @GetMapping ("/login")
    public ModelAndView authorize() {

        return new ModelAndView("login", "message", "");
    }

    @GetMapping("/error")
    public String errorForm(HttpServletRequest request, HttpServletResponse response) {
        String s = ";";
        return "error";
    }



}
