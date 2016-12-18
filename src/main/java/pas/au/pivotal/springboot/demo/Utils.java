package pas.au.pivotal.springboot.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class Utils
{
    public static String getVcapServices ()
    {
        String jsonString = null;
        jsonString = System.getenv().get("VCAP_SERVICES");
        return jsonString;
    }

    public static String applicationIndex ()
    {
        String instanceIndex = "N/A";

        try
        {
            instanceIndex = getVcapApplicationMap().getOrDefault("instance_index", "N/A").toString();
        }
        catch (Exception ex)
        {

        }

        return instanceIndex;
    }

    public static Map<String, String> jvmPropertyMap ()
    {
        Properties props = System.getProperties();
        Map<String, String> map = new HashMap<String, String>((Map) props);

        return map;
    }

    public static Map getVcapApplicationMap() throws Exception {
        return getEnvMap("VCAP_APPLICATION");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map getEnvMap(String vcap) throws Exception {
        String vcapEnv = System.getenv(vcap);
        ObjectMapper mapper = new ObjectMapper();

        if (vcapEnv != null) {
            Map<String, ?> vcapMap = mapper.readValue(vcapEnv, Map.class);
            return vcapMap;
        }

        return new HashMap<String, String>();
    }

    public static String getDBService ()
    {
        String vcapServices = getVcapServices();
        if (vcapServices == null)
        {
            return "H2";
        }
        else
        {
            JsonParser parser = JsonParserFactory.getJsonParser();
            Map<String, Object> jsonMap = parser.parseMap(vcapServices);

            List mysqlService = (List) jsonMap.get("cleardb");

            if (mysqlService == null)
            {
                // just check if it's "p-mysql"
                mysqlService = (List) jsonMap.get("p-mysql");
                if (mysqlService == null) {
                    return "H2";
                }
                else {
                    return "MySQL {p-mysql}";
                }
            }
            else
            {
                return "MySQL {cleardb}";
            }
        }
    }

}

