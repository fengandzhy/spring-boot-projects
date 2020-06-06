package org.zhouhy.springboot.project1.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.zhouhy.springboot.project1.domain.Auth;


@Component("authServiceMapper")
public class AuthServiceMapper {

    public Auth createNewAuth(int id, String childname,int maxId){
        Auth newAuth=new Auth();
        newAuth.setFullName(childname);//一级节点的全称和树型显示名称一致
        newAuth.setPid(id);
        newAuth.setId(maxId);
        newAuth.setTreeName(childname);//一级节点的全称和树型显示名称一致
        return newAuth;
    }

    public String createResult(String content){
        JSONObject result = new JSONObject();
        result.put("msg",content);
        return result.toJSONString();
    }
}
