package com.code.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    //"say-hello"=>"Hello"

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello World!";
    }

    @RequestMapping("/hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Hello</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("Hello html");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    //hello-jsp => hello.jsp
    // /src/main/resources/META-INF/resources/WEB-INF/jsp/hello.jsp
    @RequestMapping("/hello-jsp")
//    @ResponseBody
    public String sayHelloJsp() {
        return "hello";

    }

    //JSP
//    hello.jsp

}
