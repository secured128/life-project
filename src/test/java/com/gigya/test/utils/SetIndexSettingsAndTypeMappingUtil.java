package com.gigya.test.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/*
sets indexSettings or typeMappings from specified file to specified styx

param1 type  :  INDEX_SETTINGS or TYPE_MAPPING
param2 path to file : Example - /Users/alexbr/Documents/indexSettingshades6.json , "/Users/alexbr/Documents/typeMappinghades6.json"
param3 destinationStyx  :  Example - localhost
 */
@SuppressWarnings("unchecked")
public class SetIndexSettingsAndTypeMappingUtil {

    public static void main(String[] args) throws JsonParseException, IOException {
        DATA_TYPE type;
        String sourceFilePath = null;
        String styxHost = null;
        try {
            if (args != null && args.length == 3 && DATA_TYPE.valueOf(args[0].toUpperCase()) != null && (new File(args[1])).exists()) {
                type = DATA_TYPE.valueOf(args[0].toUpperCase());
                sourceFilePath = args[1];
                styxHost = args[2];
            } else {
                showHelp();
                return;
            }
        } catch (IllegalArgumentException th) {
            showHelp();
            return;
        }
//

        HttpClient httpClient = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        HttpResponse httpResponse = null;
        Map<String, Object> empMap = objectMapper.readValue(new FileInputStream(sourceFilePath), Map.class);
        for (Map.Entry<String, Object> entry : empMap.entrySet()) {
            String body = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(entry.getValue());
            try {
                httpClient = HttpClientBuilder.create().build();
                String key = URLEncoder.encode(entry.getKey(), "UTF-8");
                HttpPost request = new HttpPost("http://" + styxHost + ":9090/admin/" + type.getApi() + "/" + key);
                request.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                request.addHeader("content-type", "application/json");
                request.setHeader("Accept", "application/json");
                StringEntity params = new StringEntity(body, APPLICATION_JSON);
                params.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                request.setEntity(params);
                System.out.println(key + "=" + body);
                httpResponse = httpClient.execute(request);
                Thread.sleep(200);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                System.out.println(httpResponse.getStatusLine().getStatusCode());
                ((CloseableHttpClient) httpClient).close();
                httpClient = null;
            }

        }

    }

    private static void showHelp() {
        System.out.println("Usage : \n\tparam 1 is api " + DATA_TYPE.INDEX_SETTINGS.name() + " or " + DATA_TYPE.TYPE_MAPPING.name() + "\n\tparam 2 path to file : Example - /Users/alexbr/Documents/indexSettingshades6.json" + "\n\tparam 3 is destination styx (example : localhost)");
    }

    enum DATA_TYPE {
        INDEX_SETTINGS("indexSettings"), TYPE_MAPPING("typeMapping");
        String api;

        DATA_TYPE(String api) {
            this.api = api;
        }

        String getApi() {
            return this.api;
        }

    }

}

