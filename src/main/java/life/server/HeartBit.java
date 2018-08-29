package life.server;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import life.WebApiController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static life.WebApiController.DATE_FORMAT_TEMPLATE;

@Component
@JsonPropertyOrder({"birth_day", "now", "heart_bits", "errors_count", "latest_errors"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
public class HeartBit extends Thread implements Serializable {

    @JsonProperty("heart_bits")
    public final AtomicInteger bitCounter = new AtomicInteger(0);

    @JsonProperty("birth_day")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_TEMPLATE)
    public final Calendar BIRTH_DATE = Calendar.getInstance();

    @JsonProperty("errors_count")
    public final AtomicInteger errorsCount = new AtomicInteger(0);

    ;
    @JsonProperty("latest_errors")
    List<String> LatestErrors = new ArrayList<>();

    @Value("${web.url}")
    private String webUrl;

    @Value("${heart-bit.rate}")
    private long bitRate;

    private URL url;
    private HttpURLConnection con;

    public HeartBit() throws IOException {
        super("HeartBit");
    }

    @JsonProperty("now")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_TEMPLATE)
    public Calendar now() {
        return Calendar.getInstance();
    }

    private void logError(Throwable e) {
        errorsCount.addAndGet(1);
        if (LatestErrors.size() < 10) {
            LatestErrors.add(WebApiController.DATE_FORMAT.format(Calendar.getInstance().getTime()) + " : " + e.toString());
        }
    }

    @Override
    public void run() {
        try {
            url = new URL(webUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logError(e);
            throw new IllegalArgumentException("invalid or missing 'web.url' property");
        }
        int response = -1;
        BufferedReader in = null;
        String inputLine;
        StringBuffer content;
        System.out.println(HeartBit.class.getName() + " initialized with heart-bit.rate : " + bitRate);
        while (true) {
            try {
                Thread.sleep(bitRate);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                HttpURLConnection.setFollowRedirects(false);
                response = con.getResponseCode();
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                System.out.println(Thread.currentThread().getName() + " [" + bitCounter.getAndAdd(1) + "] [" + response + "] : " + content.toString());
            } catch (Throwable e) {
                logError(e);
                e.printStackTrace();
            } finally {
                content = null;
                try {
                    in.close();
                } catch (IOException e) {
                    logError(e);
                    in = null;
                    e.printStackTrace();
                }
                if (con != null) {
                    con.disconnect();
                    con = null;
                }
            }
        }
    }

}
