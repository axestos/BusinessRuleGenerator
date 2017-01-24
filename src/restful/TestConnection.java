package restful;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bluedog on 1/24/2017.
 */
public class TestConnection {

    // http://localhost:8080/RESTfulExample/json/product/get
    public static void main(String[] args) {

        try {

            URL url = new URL("https://ondora02.hu.nl:8080/ords/stud1681260/123getBR");
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
            System.out.println(gson.toJson(businessRule));

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}