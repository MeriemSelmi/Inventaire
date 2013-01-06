/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaire.web;

import inventaire.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Meriem
 */
public class SessionInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("loggedUser");
        if(user!=null)
            return true;
        response.sendRedirect("security.htm");
        return false;
    }
}
