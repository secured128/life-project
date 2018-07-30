package life;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import life.core.model.AMINO_ACID;
import life.server.HeartBit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class WebApiController {

    public static final String DATE_FORMAT_TEMPLATE = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_TEMPLATE);
    //    private static final String template = "STARTED : " + DATE_FORMAT.format(Calendar.getInstance().getTime()) + "<br/>NOW : %s<br/>Hello, %s! , counter : %d";
    private static final String homePage = "STARTED :  %s<br/>NOW : %s<br/>Hello, %s!";
    private static final String EMPTY_RESULT_TEMPLATE = "{\"NOT_FOUND\",\"%s\"}";
    private static final String ERROR_HEALTH_RESULT = "{\"ERROR\" : \"health can not be checked.\"}";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final AtomicLong counter = new AtomicLong();

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private HeartBit heartBit;

    @GetMapping("/")
    @ResponseBody
    public String home(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
        return String.format(homePage, DATE_FORMAT.format(heartBit.BIRTH_DATE.getTime()), DATE_FORMAT.format(Calendar.getInstance().getTime()), name);
    }

    @GetMapping("/health")
    @ResponseBody
    public String info(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
        try {
            return mapper.writeValueAsString(heartBit);
        } catch (JsonProcessingException e) {
            return ERROR_HEALTH_RESULT;
        }
    }

    @GetMapping("/aminoacid")
    @ResponseBody
    public String aminoacid(@RequestParam(name = "name", required = true) String name) {
        String result = String.format(EMPTY_RESULT_TEMPLATE, name);
        AMINO_ACID aminoAcid = AMINO_ACID.getAminoAcidByName(name);
        if (aminoAcid != null) {
            try {
                result = objectMapper.writeValueAsString(aminoAcid);
            } catch (Throwable e) {
                result = "{\"error\",\"" + e.getMessage() + "\"}";
            }
        }
        return result;
    }

}
