package restful;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


import businessrule.*;

/**
 * Created by bluedog on 1/24/2017.
 */
public class getData {

    // http://localhost:8080/RESTfulExample/json/product/get
    public static void main(String[] args) {

        try {

            URL url = new URL("https://ondora02.hu.nl:8080/ords/tosad_2016_2d_team5/118146/BusinessRules");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }


            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String jsonString = "";
            System.out.println("Output from Server: \n");
            while ((output = br.readLine()) != null) {
                jsonString = jsonString + output;
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jsonparser = new JsonParser();
            JsonObject businessRule =  (JsonObject)jsonparser.parse(jsonString);
            JsonArray jArray = (JsonArray) businessRule.get("items");

            JsonObject mJsonObject;
            for (int i = 0; i < jArray.size() ; i++)
            {
                mJsonObject = (JsonObject) jArray.get(i);
                BusinessRule b = new BusinessRule(Integer.parseInt(mJsonObject.get("ruleid").getAsString()), mJsonObject.get("authorid").getAsString(), mJsonObject.get("type").getAsString(), mJsonObject.get("operator").getAsString(), mJsonObject.get("firstvalue").getAsString(), mJsonObject.get("lastvalue").getAsString() );
                if(!mJsonObject.get("errorcode").equals("EMPTY")){
                    b.setErrorCode(mJsonObject.get("errorcode").getAsString());
                }
                if(!mJsonObject.get("rangeattribute").equals("EMPTY")){
                    b.setRangeAttribute(mJsonObject.get("rangeattribute").getAsString());
                }
                if(!mJsonObject.get("range").equals("EMPTY")){
                    b.setRange(mJsonObject.get("range").getAsString());
                }
                if(!mJsonObject.get("firstvarvalue").equals("EMPTY")){
                    b.setFirstValue(mJsonObject.get("firstvarvalue").getAsString());
                }
                if(!mJsonObject.get("changeableinterent").equals(0)){
                    int booleanInt = Integer.parseInt(String.valueOf(mJsonObject.get("changeableinterent")));
                    if (booleanInt != 0){
                        b.setInterEntityModifiable(true);
                    }
                }
                if(!mJsonObject.get("beforeafter").equals("EMPTY")){
                    b.setBeforeAfter(mJsonObject.get("beforeafter").getAsString());
                }
                BusinessRule Rule = (BusinessRule) b.generateBusinessRule();
                System.out.println("GENERATED CODE: "+ Rule.getOutput());
                String SQLCode = Rule.getOutput();
                Integer RuleID = b.getRuleid();
                writeData w = new writeData();
                w.execWrite(RuleID, SQLCode);
            }


            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }


    }
}