package com.echigoya.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HoppyController {

	@RequestMapping("/")
    public String index(){
        return "index";
    }

    // ↓↓↓ 以下の処理を追加 ↓↓↓
    @RequestMapping("/eventdetails")
    public String eventdetalis(){
        return "eventdetails";
    }

    @RequestMapping("/eventlist")
    public String eventlist(){
        return "eventlist";
    }

    @RequestMapping("/eventregist")
    public String eventregist(){
        return "eventregist";
    }

    @RequestMapping("/newuser")
    public String newuser(){
        return "newuser";
    }
}
