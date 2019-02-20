package com.ems.Web.Controller;


import com.ems.Persistence.model.User;
import com.ems.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ServletRequestAttributes attr = (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        System.out.println("--------session-----" + session.getId());
        System.out.println("--> " + session.getMaxInactiveInterval());
        System.out.println("");
        User user = userService.findUserByEmail(auth.getName());
        if (user == null) {
            modelAndView.setViewName("login");
            throw new UsernameNotFoundException("Invalid username or password.");
        } else {
            modelAndView.addObject("userName", "Welcome "
                    + user.getName() + " "
                    + user.getLastName() + " ("
                    + user.getEmail() + ")");
            modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
            modelAndView.setViewName("admin");
        }
        return modelAndView;
    }
}
