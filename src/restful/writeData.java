package restful;

import java.io.IOException;

import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by bluedog on 1/24/2017.
 */
public class writeData {

    public void execWrite(String author, String msg)  throws ClientProtocolException, IOException{
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://ondora02.hu.nl:8080/ords/stud1681260/123getBR");

            JsonObject record = new JsonObject();
            record.addProperty("author", author);
            record.addProperty("generatedcode", msg);
            System.out.println(record.toString());
            StringEntity entity = new StringEntity(record.toString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);
            client.close();
    }
    
//        public static void main(String[] args) throws IOException {
//                writeData w = new writeData();
//                w.execWrite("Quinten", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        }
}