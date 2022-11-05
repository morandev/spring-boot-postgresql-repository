package com.morandev.repoconpostgresql.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpUtils {

    public String sendPost(String url, JSONObject inputJson) {
        String result = "";

        HttpPost post = new HttpPost(url);

        try {
            StringEntity stringEntity = new StringEntity(inputJson.toString());

            post.setEntity(stringEntity);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");

            try (
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    CloseableHttpResponse res = httpClient.execute(post)
            ) {
                result = EntityUtils.toString(res.getEntity());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public JSONObject sendGET(String url, String queryAPI) {
        JSONObject result = null;

        try {
            HttpGet req = new HttpGet(url.concat("?").concat(queryAPI));
            try (
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    CloseableHttpResponse res = httpClient.execute(req);
            ) {
                HttpEntity entity = res.getEntity();

                if (entity != null) {
                    result = new JSONObject(EntityUtils.toString(entity));
                }

                return result;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public JSONArray sendGet(String url) {
        JSONArray result = null;

        try {
            HttpGet req = new HttpGet(url);
            try (
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    CloseableHttpResponse res = httpClient.execute(req);
            ) {
                HttpEntity entity = res.getEntity();

                if (entity != null) {
                    result = new JSONArray(EntityUtils.toString(entity));
                }

                return result;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }


}
