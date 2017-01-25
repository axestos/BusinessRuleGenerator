package restful;

import java.io.IOException;

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


    public static void main(String[] args) throws ClientProtocolException, IOException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://ondora02.hu.nl:8080/ords/stud1681260/123getBR");

            String json = "{\"author\":\"Quinten\",\"generatedcode\":\"JAVACODE\"}";
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);
//        assert(response.getStatusLine().getStatusCode().equals(200));
            client.close();


        }
        catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}