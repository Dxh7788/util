package com.jelly.util;

import com.jelly.util.base.UrlAsk;
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
            params.put("curPage","1");
            params.put("size","10");
            HttpClientUtil.getAndPost("overdueEnterpriseList","",params);
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

    @Test
    public void adminListAdminUsers(){
        JSONObject params = new JSONObject();

        UrlAsk urlAsk = new UrlAsk();
        params.put("key","name");
        params.put("value",687);
        HttpClientUtil.sendPost("http://localhost:8081/urlresource/values",params.toString());
    }

}
