package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // hello url로 들어오는 get 방식의 통신은 여기서 잡음.
    @GetMapping("hello")
    public String hello(Model model){ //model로 매핑 시켜줌.
        model.addAttribute("inData", "hello!! it's controller"); //"inData"에 "hello!!"라는 값이 들어감.
        return "helloFile"; //templates패키지안의 helloFile 파일에 들어감
    }
    // 컨트롤 + P를 누르면 파라미터 정보를 볼 수 있음.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("param") String param, Model model){
        //@RequestParam( required=false)로하면 파라미터가 꼭 넘어오지 않아도 괜찮음.
        model.addAttribute("nameInHere", param); //"name"안에 name에 해당하는 값이 들어간다. model.addAttribute(name, value);
        return "hello-template-file"; //templates패키이 안의 hello-template-file 파일에 들어감.
    }

    @GetMapping("hello-string")
    @ResponseBody //응답 바디부분에 return 내용을 직접 넣어주는 역할을 해줌.
        public String  helloString(@RequestParam("name") String name){
            return "<h1>hello <em>" + name + "</em></h1>"; //"hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody //JSON 방식으로 응답 바디 부분에 return 내용을 보내줌.
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }

}
