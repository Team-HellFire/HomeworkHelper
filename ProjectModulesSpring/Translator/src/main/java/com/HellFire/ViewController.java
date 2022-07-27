package com.HellFire;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //통로에요
public class ViewController {
    @RequestMapping("/") //주소창 끝자락에 이게 있으면
    public String MoveToJspDefault () {
        System.out.println("MoveToJspDefault Started");
        return "/WebSource"; //이 파일 여세요~
    }
    @RequestMapping("/test")
    public String MoveToJsp () {
        System.out.println("MoveToJsp Started");
        return "/WebSource";
    }
}
