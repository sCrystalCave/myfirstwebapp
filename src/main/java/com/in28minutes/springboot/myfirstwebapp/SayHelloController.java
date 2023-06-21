package com.in28minutes.springboot.myfirstwebapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //requestMapping 을 사용하기 위해 필요한 어노테이션
public class SayHelloController {
    //"say-hello" -> "Hello! what are you learning today?"

    //http://localhost:8080/say-hello
    @RequestMapping("say-hello") //웹 경로 설정
    @ResponseBody //브라우저로 리턴
    public String sayHello(){
        return "Hello! what are you learning today?";
    }

    @RequestMapping("say-hello-html") //웹 경로 설정
    @ResponseBody //브라우저로 리턴
    public String sayHelloHtml(){
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>My First HTML Page</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("My first html page with body");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    @RequestMapping("say-hello-jsp") //웹 경로 설정
    public String sayHelloJSP(){
        return "sayHello";
    }

}
