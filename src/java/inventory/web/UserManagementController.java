package inventory.web;

import inventory.domain.User;
import inventory.service.user.UserAdd;
import inventory.service.user.UserFind;
import inventory.service.user.UserManager;
import inventory.service.user.UserUpdate;
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
    
    @RequestMapping(value = "useradd.htm", method = RequestMethod.POST)
    public ModelAndView addUser(
            HttpServletRequest request, @ModelAttribute("useradd") @Valid UserAdd userAdd,
            BindingResult result) {
        
        if (result.hasErrors()) {
            return manageUsers();
        }
        
        User user = userAdd.getUser();

        try {
            userManager.addUser(user);
            return new ModelAndView(new RedirectView("/user/usermanagement.htm", true));
        } catch (Exception e) {
            result.rejectValue("login", "error.user.update");
            return manageUsers();
        }
    }

    @RequestMapping(value = "userupdate.htm", method = RequestMethod.POST)
    public ModelAndView updateUser(
            HttpServletRequest request, @ModelAttribute("userupdate") @Valid UserUpdate userUpdate,
            BindingResult result) {

        User user = userUpdate.getUser();
        user.setId(Integer.parseInt(request.getParameter("id")));
        String OriginalLoginUser = request.getParameter("updateLogin");
        if (result.hasErrors()) {
            request.setAttribute("loginErrorUser", OriginalLoginUser);
            request.setAttribute("errorUser",user.getId());            
            return manageUsers();          
        }

        try {
            userManager.updateUser(user);
            return new ModelAndView(new RedirectView("/user/usermanagement.htm", true));
        } catch (Exception e) {
            result.rejectValue("login", "error.user.update");
            return manageUsers();
        }
    }

    @RequestMapping(value = "userdelete.htm", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request) {

        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        userManager.deleteUser(user);
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
        usersFound = userManager.findUsers(keyword);
        return manageUsers().addObject("users", usersFound);
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
            users = userManager.listUsers();
        } catch (Exception e) {
        }
        return users;
    }
}
