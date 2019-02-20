package com.ems.Web.Controller;

import com.ems.Persistence.model.User;
import com.ems.Service.UserService;
import com.ems.Web.dto.PasswordDTO;
import com.ems.Web.dto.UserDto;
import com.ems.captcha.ICaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ICaptchaService captchaService;

    @RequestMapping(value = "/registrations", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        UserDto user = new UserDto();
        modelAndView.addObject("userDto", user);
        modelAndView.setViewName("registrations");
        return modelAndView;
    }


    @RequestMapping(value = "/registrations", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid final UserDto accountDto,
                                      BindingResult bindingResult,
                                      final HttpServletRequest request) {

        final String response = request.getParameter("g-recaptcha-response");
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(accountDto.toString());
        try {
            captchaService.processResponse(response);
        } catch (NullPointerException e) {
            modelAndView.setViewName("registrations");
        }


        User userExists = userService.findUserByEmail(accountDto.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registrations");
        } else {
            userService.createUserAccount(accountDto);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("successRegister");
            LOGGER.debug("Registering user account with information: {}", accountDto);
        }
        return modelAndView;
    }


    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public ModelAndView forgotPassword() {
        System.out.println("Inside forgot password");
        ModelAndView modelAndView = new ModelAndView();
        PasswordDTO resetPwd = new PasswordDTO();
        modelAndView.addObject("resetPwd", resetPwd);
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public ModelAndView resetPassword(@Valid final PasswordDTO passwordDto) {
        System.out.println("Re-set password");
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("Re-set Email " + passwordDto.getEmail());
        System.out.println("Old pwd " + passwordDto.getOldPassword());
        System.out.println("New Pwd " + passwordDto.getNewPassword());

        User userExists = userService.findUserByEmail(passwordDto.getEmail());
        System.out.println(userExists.getId() + "lllll " + userExists.getActive());

        if (userExists != null && Integer.toString(userExists.getActive()).equalsIgnoreCase("1")) {
            System.out.println("Not null");

            userExists.setPassword(passwordDto.getNewPassword());

            userService.updatePassword(passwordDto.getNewPassword(), userExists.getId());
        } else {
            System.out.println("Null");
        }


        modelAndView.addObject("successMessage", "Your Password has been reset.");
        modelAndView.setViewName("successRegister");
        return modelAndView;

    }
}
