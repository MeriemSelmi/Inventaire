package inventaire.web;

import inventaire.domain.User;
import inventaire.service.UserAdd;
import inventaire.service.UserFind;
import inventaire.service.UserManager;
import inventaire.service.UserUpdate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user")
public class UserManagementController {

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "usermanagement.htm", method = RequestMethod.GET)
    public ModelAndView manageUsers() {
        return new ModelAndView("usermanagement");
    }

    @RequestMapping(value = "userupdate.htm", method = RequestMethod.POST)
    public ModelAndView updateUser(
            HttpServletRequest request, @ModelAttribute("userupdate") @Valid UserUpdate userUpdate,
            BindingResult result) {

        if (result.hasErrors()) {
            return manageUsers();
        }

        User user = userUpdate.getUser();
        user.setId(Integer.parseInt(request.getParameter("id")));

        try {
            userManager.update(user);
            return new ModelAndView(new RedirectView("/user/usermanagement.htm", true));
        } catch (Exception e) {
            result.rejectValue("login", "error.update.failed");
            return manageUsers();
        }
    }

    @RequestMapping(value = "useradd.htm", method = RequestMethod.POST)
    public ModelAndView addUser(
            HttpServletRequest request, @ModelAttribute("useradd") @Valid UserAdd userAdd,
            BindingResult result) {
        
        if (result.hasErrors()) {
            return manageUsers();
        }
        
        User user = userAdd.getUser();

        try {
            userManager.add(user);
            return new ModelAndView(new RedirectView("/user/usermanagement.htm", true));
        } catch (Exception e) {
            result.rejectValue("login", "error.update.failed");
            return manageUsers();
        }
    }

    @RequestMapping(value = "userdelete.htm", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request) {

        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        userManager.delete(user);
        return new ModelAndView(new RedirectView("/user/usermanagement.htm", true));
    }

    @RequestMapping(value = "userfind.htm", method = RequestMethod.POST)
    public ModelAndView findUsers(
            HttpServletRequest request, @ModelAttribute("userfind") @Valid UserFind userFind,
            BindingResult result) {
        
        if (result.hasErrors()) {
            return manageUsers();
        }
        
        String keyword = userFind.getKeyword();
        List<User> usersFound;

        try {
            usersFound = userManager.findUsers(keyword);
            return manageUsers().addObject("usersFound", usersFound);
        } catch (Exception e) {
            result.rejectValue("keyword", "error.find.failed");
            return manageUsers();
        }
    }

    @ModelAttribute("userRoles")
    public Map<String, String> getUserRoles() {
        return User.USER_ROLES;
    }

    @ModelAttribute("userfind")
    public UserFind getUserFind() {
        return new UserFind();
    }

    @ModelAttribute("userupdate")
    public UserUpdate getUserUpdate() {
        return new UserUpdate();
    }

    @ModelAttribute("useradd")
    public UserAdd getUserAdd() {
        return new UserAdd();
    }

    @ModelAttribute("users")
    private List<User> listUsers() {
        List<User> users = new LinkedList<User>();
        try {
            users = userManager.getUsers();
        } catch (Exception e) {
        }
        return users;
    }
}
