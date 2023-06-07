package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // hello url로 들어오는 get 방식의 통신은 여기서 잡음.
    @GetMapping("hello")
    public String hello(Model model){ //model로 매핑 시켜줌.
        model.addAttribute("inData", "hello!! it's controller"); //"inData"에 "hello!!"라는 값이 들어감.
        return "helloFile"; //templates패키지안의 helloFile 파일에 들어감
    }
}
