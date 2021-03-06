package com.lemon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.zw201913.annotation.FileUpload;
import com.github.zw201913.model.FileSaveResult;
import com.lemon.entity.Person;
import com.lemon.service.PersonService;

import com.lemon.util.HttpResponseHelper;

import com.lemon.util.JsonUtilsHelper;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lemon on 2017/8/15.
 */
@Log4j2
@Data
@Controller
public class PersonController extends BaseController {
    @Autowired
    private PersonService personService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    /**
     * 新增
     * @param personEntity
     * @return
     * @throws ControllerException
     */
    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    @ResponseBody
    public String addPerson(@RequestBody Person personEntity) throws ControllerException {
        String userId = getUserID();
        personService.addPerson(personEntity,userId);
        return HttpResponseHelper.successInfoInbox("操作成功");
    }

    /**
     * 查询所有的列表
     * @return
     * @throws ControllerException
     */
    @RequestMapping(value = "/person/list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findAllPerson() throws ControllerException {
        String userId = getUserID();
        List<Person> list = personService.findAll();
        Long count = personService.countAll();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        try {
            return JsonUtilsHelper.objectToJsonString(map);
        } catch (JsonProcessingException e) {
            throw new ControllerException(e.getMessage());
        }

    }
    /*@RequestMapping(value = "/person/list",method = RequestMethod.POST)
    @ResponseBody
    public String findAllPerson(Person person) throws ControllerException {

        Page<Person> personList= personService.findAll(person.getPage(),person.getLimit(),"desc");
        List<Object> list = new ArrayList<>();
        for(Person personEntity : personList){
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("id",personEntity.getId());
            map1.put("city",personEntity.getCity());
            map1.put("identity",personEntity.getIdentity());
            map1.put("sign",personEntity.getSign());
            map1.put("sex",personEntity.getSex());
            map1.put("position",personEntity.getPosition());
            map1.put("name",personEntity.getName());
            list.add(map1);
        }
        Long count = personService.countAll();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code",0);
            map.put("msg","");
            map.put("count",count);
            map.put("data", personList);
            return JsonUtilsHelper.objectToJsonString(map);
        } catch (JsonProcessingException e) {
            throw new ControllerException(e.getMessage());
        }
    }*/
    /**
     * 上传单个文件
     * @param file
     * @return
     */
  /*  @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(MultipartFile file){
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.debug("获取上传文件",file);
        return HttpResponseHelper.successInfoInbox("上传成功");
    }
    *//**
     * 上传多个文件
     * @return
     *//*
    @FileUpload
    @RequestMapping(value = "/upload_list",method = RequestMethod.POST)
    @ResponseBody
    public String filesUpload(@RequestPart("files")  MultipartFile[] files, FileSaveResult result){
        response.setHeader("Access-Control-Allow-Origin", "*");
        log.debug("获取上传文件{}",files);
        for (MultipartFile file : files) {
            log.debug(file.getOriginalFilename());
        };
        return HttpResponseHelper.successInfoInbox("上传成功");
    }
*/

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findAllList() {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "{\n" +
                "  \"users\":[\n" +
                "        {\n" +
                "           \"date\": \"1989-05-02\",\n" +
                "           \"name\": \"王小虎\",\n" +
                "           \"sex\":\"男\",\n" +
                "           \"age\":18,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1960-05-02\",\n" +
                "           \"name\": \"蔡翠娥\",\n" +
                "           \"sex\":\"女\",\n" +
                "           \"age\":56,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1998-05-02\",\n" +
                "           \"name\": \"王大兰\",\n" +
                "           \"sex\":\"女\",\n" +
                "           \"age\":36,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1599 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1963-05-02\",\n" +
                "           \"name\": \"王二麻子\",\n" +
                "           \"sex\":\"男\",\n" +
                "           \"age\":37,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦香玉\",\n" +
                "           \"sex\":\"女\",\n" +
                "           \"age\":30,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1963-05-02\",\n" +
                "           \"name\": \"王二麻子\",\n" +
                "           \"sex\":\"男\",\n" +
                "           \"age\":37,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦香玉\",\n" +
                "           \"sex\":\"女\",\n" +
                "           \"age\":30,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦香玉\",\n" +
                "           \"sex\":\"女\",\n" +
                "           \"age\":30,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦香玉\",\n" +
                "           \"sex\":\"女\",\n" +
                "           \"age\":30,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦香玉\",\n" +
                "           \"sex\":\"女\",\n" +
                "           \"age\":30,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦祥林\",\n" +
                "           \"sex\":\"男\",\n" +
                "           \"age\":21,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦祥林\",\n" +
                "           \"sex\":\"男\",\n" +
                "           \"age\":20,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦祥林\",\n" +
                "           \"sex\":\"男\",\n" +
                "           \"age\":20,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦祥林\",\n" +
                "           \"sex\":\"男\",\n" +
                "           \"age\":20,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦祥林\",\n" +
                "           \"sex\":\"男\",\n" +
                "           \"age\":20,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦祥林\",\n" +
                "           \"sex\":\"男\",\n" +
                "           \"age\":20,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          },\n" +
                "          {\n" +
                "           \"date\": \"1977-05-02\",\n" +
                "           \"name\": \"秦祥林\",\n" +
                "           \"sex\":\"男\",\n" +
                "           \"age\":22,\n" +
                "           \"birth\":\"2002-01-01\",\n" +
                "           \"addr\": \"上海市普陀区金沙江路 1518 弄\"\n" +
                "          }\n" +
                "  ]\n" +
                "}";
    }
}
