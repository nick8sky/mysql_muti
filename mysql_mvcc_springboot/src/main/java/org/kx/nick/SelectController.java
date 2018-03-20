package org.kx.nick;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * create by sunkx on 2018/3/17
 */
@RestController
@RequestMapping("/mvcc")
public class SelectController {



    @RequestMapping(value = "/vi")
    public String login() {
        return  "hello";
    }



}
