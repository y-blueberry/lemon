package com.lemon.controller;

import com.lemon.entity.Person;
import com.lemon.service.PersonService;

import com.lemon.service.ServiceException;
import com.lemon.util.HttpResponseHelper;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.Enumeration;


/**
 * Created by jyj on 2017/6/29.
 */
@Log4j2
@Data
@Controller
public class LoginController extends BaseController{

    @Autowired
    private PersonService personService;

    /**
     * 登录
     * @return
     * @throws ControllerException
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(Person personBean) throws ControllerException {
        String name = personBean.getName();
        String password = personBean.getPassword();
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
            throw new ControllerException("exception.login.name.or.password.null");
        }

        try {
            Person person = personService.login(name, password);
            if(person != null){
                httpSession.setAttribute("user_id", person.getId());
                httpSession.setAttribute("user_name", person.getName());
                return HttpResponseHelper.successInfoInbox("登录成功！");
            }else{
                throw new ControllerException("exception.login.name.or.password.wrong");
            }
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        log.debug("开始登出");
        Enumeration<String> eume = httpSession.getAttributeNames();
        while (eume.hasMoreElements()) {
            String name = eume.nextElement();
            httpSession.removeAttribute(name);
        }
        log.debug("登出完成");
        return "index";
    }

}
