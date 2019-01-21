package com.gigya.test.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
Ask alex.br if you need to remove a lot of invalid ES indices

<parent>
        <artifactId>HADES</artifactId>
        <groupId>com.gigya</groupId>
        <version>2.2.11</version>
    </parent>
 */
public class RemoveInvalidIndicesFromEsUtil {

    public static void main(String[] args) throws JsonParseException, IOException {
        String sourceFilePath = null;
        String styxHost = null;
        try {
            if (args != null && args.length == 2 && (new File(args[0])).exists()) {
                sourceFilePath = args[0];
                styxHost = args[1];
            } else {
                showHelp();
                return;
            }
        } catch (IllegalArgumentException th) {
            showHelp();
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(sourceFilePath));

        HttpClient httpClient = null;
        HttpResponse httpResponse = null;
        String indexName;
        int total = 0;
        int deleted = 0;
        int sckipped = 0;
        while ((indexName = br.readLine()) != null) {
            if (StringUtils.isNotBlank(indexName) && indexName.trim().startsWith("auditlog_24_")  && !"201".equals(indexName.substring("auditlog_24_".length(), "auditlog_24_".length() + 3))) {
                try {
                    httpClient = HttpClientBuilder.create().build();
                    HttpDelete request = new HttpDelete("http://" + styxHost + "/" + indexName);
                    System.out.println(styxHost + "/" + indexName);
                    httpResponse = httpClient.execute(request);
                    Thread.sleep(100);
                    deleted++;
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    System.out.println(httpResponse.getStatusLine().getStatusCode());
                    ((CloseableHttpClient) httpClient).close();
                    httpClient = null;
                }
            } else {
                sckipped++;
            }
            total++;
        }
        System.out.println("=====================================");
        System.out.println("deleted  : " + deleted);
        System.out.println("sckipped  : " + sckipped);
        System.out.println("total  : " + total);
    }

    private static void showHelp() {
        System.out.println("Usage : \n\tparam 1 is path to file : Example - /Users/alexbr/Documents/indexSettingshades6.json" + "\n\tparam 2 is destination es (example : il1a-h3-es1-s01:9200)");
    }


}

