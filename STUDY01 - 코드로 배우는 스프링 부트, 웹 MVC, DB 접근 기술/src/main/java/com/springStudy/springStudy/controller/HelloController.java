package com.springStudy.springStudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // '/hello'의 request를 받음
    public String hello(Model model){
        model.addAttribute("data", "simon"); // model 객체에 k-v 데이터를 담아 보냄
        return "hello"; // 'resources/templates/hello.html' 파일을 리턴함
    }

    @GetMapping("hello-mvc") // GET 방식에서 파라미터를 추가하는 방법, (e.g., '/hello-mvc?name=spring!!!')
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // html의 body가 아닌, http의 body 부를 의미하며, 그 부분에 데이터를 직접 넣겠다는 뜻, 쓸 일은 많이 없을 듯
    public String helloString(@RequestParam(value = "name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){ // json 형태로 객체를 반환함
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

    }
}
