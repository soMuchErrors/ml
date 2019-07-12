package com.rainbow.machinelearning;

import com.alibaba.fastjson.JSONObject;
import com.rainbow.machinelearning.model.User;
import com.rainbow.machinelearning.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MachineLearningApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		Map<String,Object> result = new HashMap();
		User user = new User();
		user.setAge(10);
		user.setName("Tom");
		userService.insert(user);
		result.put("code","200");
		result.put("body",user);
		result.put("msg","success");
		System.out.println(JSONObject.toJSONString(result));
	}

}

