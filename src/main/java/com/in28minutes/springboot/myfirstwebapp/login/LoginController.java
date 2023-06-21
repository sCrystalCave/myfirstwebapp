package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    private AuthenticationService authenticationService;

    //constructor injection

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //컨트롤러에서 jsp로 인자를 전달할 때
    //모델을 이용한다.
    @RequestMapping(value="login", method= RequestMethod.GET)
    public String gotoLoginPage(){
        return "login";
    }

    //로그인 sumit 하면 post 요청으로 처리되기 때문에
    //아래 메서드가 실행된다.
    @RequestMapping(value="login", method= RequestMethod.POST)
    public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model){
        if(authenticationService.authenticate(name, password)){
            model.put("name", name);
            return "welcome";
        }
        else{
            model.put("errorMessage", "Invalid Credential! Please try again.");
            return "login";
        }
    }

}
