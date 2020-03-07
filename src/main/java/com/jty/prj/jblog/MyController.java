package com.jty.prj.jblog;

import com.jty.prj.jblog.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jinit
 * @project Jtest
 * @file MyController
 * @date 19-12-29 23:12
 * @todo 测试
 */
@Controller
public class MyController {

    private NamedParameterJdbcTemplate jdbcTemplate;
    @RequestMapping("/test")
    @ResponseBody
    public String out(){
        String sql = "insert into sys_user(user_number,user_name) " +
                "values(:user_number,:user_name)";

        User user = new User();
        user.setUser_number("1111111111");
        user.setUser_name("张三");
        Map<String, Object> param = new HashMap<>();
        param.put("user_number", user.getUser_number());
        param.put("user_name",user.getUser_name());
        jdbcTemplate.update(sql, param);

        return "success";
    }
    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
