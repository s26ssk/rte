package com.rte.config;

import com.rte.dto.response.RespUserLoginDTO;
import com.rte.model.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("loggedInUser");

        if (user != null && user.getRole() == 1) {
            return true;
        }
        return false;
    }
}
