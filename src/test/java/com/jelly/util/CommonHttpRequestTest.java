package com.jelly.util;

import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * @author dongxiaohong
 * @date 2019/1/8 10:39
 */
public class CommonHttpRequestTest {

    @Test
    public void userConcernCoreEnterprise(){
        try {
            JSONObject params = new JSONObject();
            params.put("token","3f09aa160289427881e3081bf27cabed");
            params.put("enterpriseId","33");
            params.put("manner","1");
            HttpClientUtil.getAndPost("userConcernCoreEnterprise","",params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void coreEnterpriseHomeBaseInfo(){
        try {
            JSONObject params = new JSONObject();
            params.put("token","bfda4441c6ad41c28c582469543c42db");
            HttpClientUtil.getAndPost("dataAuditList", "", params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void listCoreEnterprise(){
        JSONObject params = new JSONObject();
        params.put("token","");
        params.put("page","");
        params.put("size",1000);
        System.out.println(HttpClientUtil.getAndPost("listCoreEnterprise","",params));
    }@Test
    public void listCoreEnterprise2(){
        System.out.println(HttpClientUtil.getAndPost("manual_audit","",null));
    }
}
