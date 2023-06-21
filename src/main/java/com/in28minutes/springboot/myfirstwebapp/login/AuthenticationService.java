package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    //인증
    //name : in28minutes, password : dummy 일 때 welcome으로
    //아니면 다시 login으로 가게 하기 위한 vaild 체크 메서드.
    public boolean authenticate(String username, String password){
        boolean isValidUser = username.equalsIgnoreCase("in28minutes");
        boolean isValidPassword = password.equalsIgnoreCase("dummy");

        return isValidUser && isValidPassword;
    }

}
