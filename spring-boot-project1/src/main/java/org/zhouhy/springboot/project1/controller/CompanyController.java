package org.zhouhy.springboot.project1.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zhouhy.springboot.project1.domain.Company;
import org.zhouhy.springboot.project1.service.CompanyService;
import org.zhouhy.springboot.project1.util.UploadFileUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Resource(name="companyService")
    private CompanyService companyService;

    @Autowired
    private UploadFileUtil uploadFileUtil;

    @RequestMapping(value = "/save",method =  RequestMethod.POST)
    @ResponseBody //这里必须要加@ResponseBody 如果不加就会有默认ModelandView 而且返回的路径默认是/company/save
    public void save(Company company){
        log.info("In the method now!!!");
        companyService.Save(company);
    }

    @GetMapping("/delete")
    @ResponseBody
    public void delete(@RequestParam String uuid){
        companyService.delete(uuid);
    }


    @PostMapping("/findAllSimplePage")
    @ResponseBody
    public Page<Company> findAllSimplePage(@RequestParam(name="page",required=false,defaultValue = "1") int page, @RequestParam(name="size",required=false,defaultValue = "2") int size)
    {
        /**
         * 多条件排序及分页查询
         */
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"comname"));
        orders.add(new Sort.Order(Sort.Direction.ASC,"comaddress"));

        return companyService.findAllSimplePage(PageRequest.of(page,size,Sort.by(orders)));
    }

    @PostMapping("/findAllSimplePageMap")
    @ResponseBody
    public String findAllSimplePageMap(@RequestBody(required = false) Map<String,Object> reqMap)
    {
        /**
         * 简单分页查询
         */
        int page=this.getPage(reqMap);
        int size=this.getSize(reqMap);
        List<Sort.Order> orders = getSortRule(reqMap);

        //以下是代码的核心点
        Page<Company> pageInfo=companyService.findAllSimplePage(PageRequest.of(page,size,Sort.by(orders)));
        return getResult(pageInfo);
    }



    @GetMapping("/listCompany")
    public String page(){
        return "/company/listCompany";
    }


    @GetMapping("/addCompanyPage")
    public String addCompanyHtml(){
        return "company/addCompany.html";
    }

    @RequestMapping("validateEmail")
    @ResponseBody
    public String validateEmail(@RequestParam String contactorEmail){
        /**
         * 验证邮箱是否唯一，唯一:{"valid":true},不唯一：{"valid"：false}
         */
        boolean blStatus=companyService.validateEmail(contactorEmail);
        JSONObject result = new JSONObject();
        result.put("valid", blStatus);
        return result.toJSONString();
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam MultipartFile file){
        /**
         * 接收上传文件，反馈回服务器端的文件保存路径（全路径）
         */
        return uploadFileUtil.receiveFile(file,"myfile");
    }

    @PostMapping("/queryDynamic")
    @ResponseBody
    public String queryDynamic(@RequestBody(required = false) Map<String,Object> reqMap){

        int page=this.getPage(reqMap);
        int size=this.getSize(reqMap);
        List<Sort.Order> orders = getSortRule(reqMap);
        Page<Company> pageInfo=companyService.queryDynamic(reqMap,PageRequest.of(page,size,Sort.by(orders)));
        return getResult(pageInfo);
    }

    private List<Sort.Order> getSortRule(Map<String,Object> reqMap){


        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"companyName"));
        orders.add(new Sort.Order(Sort.Direction.ASC,"companyAddress"));
        return orders;
    }

    private int getPage(Map<String,Object> reqMap){
        int page=0;
        if(reqMap!=null){
            if(reqMap.get("page").toString()!=null){
                page= Integer.parseInt(reqMap.get("page").toString());
            }
        }
        return page;
    }

    private int getSize(Map<String,Object> reqMap){
        int size=3;
        if(reqMap!=null){
            if(reqMap.get("size").toString()!=null){
                size= Integer.parseInt(reqMap.get("size").toString());
            }
        }
        return size;
    }

    private String getResult(Page<Company> pageInfo){
        List<Company> companies =pageInfo.getContent();
        JSONObject result = new JSONObject();//maven中配置alibaba的fastjson依赖
        //"rows"和"total"这两个属性是为前端列表插件"bootstrap-table"服务的
        result.put("rows", companies);
        result.put("total",pageInfo.getTotalElements());
        return result.toJSONString();
    }
}
