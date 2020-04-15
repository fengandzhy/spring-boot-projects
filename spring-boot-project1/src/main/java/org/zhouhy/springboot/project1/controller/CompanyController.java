package org.zhouhy.springboot.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zhouhy.springboot.project1.domain.Company;
import org.zhouhy.springboot.project1.service.CompanyService;

import javax.annotation.Resource;


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


    @GetMapping("/demo")
    public String page(@RequestParam String uuid){
        return "redirect:/demo.html";
    }

}
