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
            params.put("token","f244d65b6e614f4d9a0da5885b27409f");
            params.put("enterpriseId",45);
            HttpClientUtil.getAndPost("coreEnterpriseHomeBaseInfo", "", params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
