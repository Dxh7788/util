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
            HttpClientUtil.getAndPost("userConcernCoreEnterprise","",params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void coreEnterpriseHomeBaseInfo(){
        try {
            JSONObject params = new JSONObject();
            params.put("token","3f09aa160289427881e3081bf27cabed");
            params.put("enterpriseId",151);
            HttpClientUtil.getAndPost("coreEnterpriseHomeBaseInfo", "", params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
