package com.jelly.util;

import com.jelly.util.base.UrlAsk;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.TreeMap;

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
    }

    @Test
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

    @Test
    public void testMap(){
        TreeMap<String,String> treeMap = new TreeMap<>();
        treeMap.put("1","123");
        treeMap.put("2","1234");
        treeMap.put("3","1235");
        treeMap.put("4","1236");
        treeMap.put("5","1237");
        treeMap.put("7","1238");
        treeMap.put("8","1239");
        treeMap.put("9","1230");
        treeMap.put("10","1218");
        treeMap.put("11","1228");
        treeMap.put("12","1208");
        treeMap.put("13","1248");
        treeMap.put("14","1258");
        treeMap.put("15","1298");
        treeMap.put("16","1288");
        treeMap.put("0","1238");
    }
}
