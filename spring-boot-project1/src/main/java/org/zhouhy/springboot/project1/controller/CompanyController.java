package org.zhouhy.springboot.project1.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zhouhy.springboot.project1.domain.Company;
import org.zhouhy.springboot.project1.service.CompanyService;

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

    @RequestMapping(value = "/save",method =  RequestMethod.POST)
    public void save(Company company){
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
        int page=0;
        int size=3;
        if(reqMap!=null)
        {
            if(reqMap.get("page").toString()!=null){page= Integer.parseInt(reqMap.get("page").toString());}
            if(reqMap.get("size").toString()!=null){size= Integer.parseInt(reqMap.get("size").toString());}
        }

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"companyName"));
        orders.add(new Sort.Order(Sort.Direction.ASC,"companyAddress"));

        //以下是代码的核心点
        Page<Company> pageInfo=companyService.findAllSimplePage(PageRequest.of(page,size,Sort.by(orders)));
        List<Company> companies =pageInfo.getContent();
        JSONObject result = new JSONObject();//maven中配置alibaba的fastjson依赖
        //"rows"和"total"这两个属性是为前端列表插件"bootstrap-table"服务的
        result.put("rows", companies);
        result.put("total",pageInfo.getTotalElements());
        return result.toJSONString();
    }



    @GetMapping("/listCompany")
    public String page(){
        log.info("aaaaaaaaaaaaaaaaaaaaa");
        return "/company/ListCompany";
    }




}
