package intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.reservation.domain.User;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User naverLoginUser = (User) session.getAttribute("loginUser");
        String path = request.getRequestURI();

        System.out.println("path : " + path);

        if("/myreservation".equals(path)){
            if(naverLoginUser == null){
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }
}