package inventory.security;

import inventory.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String role = ((User) request.getSession().getAttribute("loggedUser")).getRole();
        if(role.equals(User.USER_ROLE_ADMINISTRATOR))
            return true;
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/authentication.htm"));
        return false;
    }

}
