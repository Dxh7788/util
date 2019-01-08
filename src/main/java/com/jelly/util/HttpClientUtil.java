package com.jelly.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.net.URLEncoder;

/**
 * @author dongxiaohong
 * @date 2018/12/29 10:24
 */
public class HttpClientUtil {
    public static final String DEFAULT_LOCAL_URL = "http://localhost:8080/api/3.0/m/";
    public static final String DEFAULT_BASE_URL = "http://10.10.1.58:8080/api/3.0/m/";
    public static void sendGet(String url, String s1) {
        BufferedReader in = null;

        String content = null;

        // 定义HttpClient
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 实例化HTTP方法
        //参数
        StringBuffer params = new StringBuffer();
        try {
            params.append("params="+ URLEncoder.encode("{\"token\":\"3f09aa160289427881e3081bf27cabed\",\"enterpriseId\":31}","utf-8"));
            params.append("&");
            params.append("from=W");
        }catch (Exception e){
            e.printStackTrace();
        }
        HttpGet request = new HttpGet(url+"?"+params);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true).build();
        request.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);

            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                System.out.println("响应内容为:" + EntityUtils.toString(httpEntity));
            }
            /*in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            content = sb.toString();*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();// 最后要关闭BufferedReader
                }
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void getAndPost(String requestShortName, String s1, JSONObject params) {
        String url = request(requestShortName,params);
        BufferedReader in = null;

        String content = null;

        // 定义HttpClient
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 实例化HTTP方法
        //参数
        HttpGet request = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true).build();
        request.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);

            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                System.out.println("响应内容为:" + EntityUtils.toString(httpEntity));
            }
            /*in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            content = sb.toString();*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();// 最后要关闭BufferedReader
                }
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String request(String request,JSONObject params){
        String baseUrl = HttpClientUtil.DEFAULT_BASE_URL;
        StringBuilder url = new StringBuilder(baseUrl);
        url.append(request);
        url.append("?");
        try {
            url.append("params="+URLEncoder.encode(params.toString(),"utf-8"));
        }catch (Exception e){
            e.printStackTrace();
        }
        url.append("&");
        url.append("from=W");
        System.out.println(url.toString());
        return url.toString();
    }
}
