package com.rainbow.machinelearning.controller;

import com.rainbow.machinelearning.model.User;
import com.rainbow.machinelearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ${xiami}
 * @version $Id: UserController.java, v 0.1 2019年05月09日 16:30 Exp $
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody User user) {
        Map<String,Object> result = new HashMap();
        userService.insert(user);
        result.put("code","200");
        result.put("body",user);
        result.put("msg","success");
        return result;
    }
}
