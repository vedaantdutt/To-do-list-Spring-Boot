package com.code.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

//import java.util.logging.Logger;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    @RequestMapping(
            value="/",
            method= RequestMethod.GET
    )
    public String welcome(ModelMap model) {
        model.put("name",getLoggedInUserName());
        return "welcome";
    }

    private String getLoggedInUserName() {
        org.springframework.security.core.Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

       return authentication.getName();

    }

}
