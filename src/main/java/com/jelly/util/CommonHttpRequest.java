package com.jelly.util;

import net.sf.json.JSONObject;

import java.net.URLEncoder;

/**
 * @author dongxiaohong
 * @date 2019/1/8 10:39
 */
public class CommonHttpRequest {
    public static void main(String[] args) {
        try {
            StringBuilder url = new StringBuilder("http://localhost:8080/api/3.0/m/");
            url.append("unConcern");
            url.append("?");
            JSONObject params = new JSONObject();
            params.put("token","3f09aa160289427881e3081bf27cabed");
            params.put("enterpriseId","33");
            url.append("params="+ URLEncoder.encode(params.toString(),"utf-8"));
            url.append("&");
            url.append("from=W");
            System.out.println(url);
            HttpClientUtil.getAndPost(url.toString(), "");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
