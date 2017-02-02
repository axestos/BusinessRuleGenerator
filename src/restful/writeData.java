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

    public void execWrite(Integer RuleID, String msg)  throws ClientProtocolException, IOException{
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://ondora02.hu.nl:8080/ords/tosad_2016_2d_team5/118146/BusinessRules");

            JsonObject record = new JsonObject();
            record.addProperty("ruleid", RuleID);
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
//                w.execWrite("Quinten", "Create or replace trigger KlasInterEntity105\n" +
//                        "before insert or update on Klas\n" +
//                        "for each row\n" +
//                        "DECLARE \n" +
//                        "l_passed boolean := true;\n" +
//                        "cursor cursorSchool105 is\n" +
//                        "SELECT min(School.klaslokaal)\n" +
//                        "from School\n" +
//                        "where School.id = p_Klas_row.new_School_id;\n" +
//                        "l_School Klas.leerlingen%type;\n" +
//                        "BEGIN\n" +
//                        "open cursorSchool105;\n" +
//                        "fetch cursorSchool105 into l_Klas;\n" +
//                        "close cursorSchool105;\n" +
//                        "l_passed := p_Klas_row.new_leerlingen < l_School;\n" +
//                        "if not l_passed then\n" +
//                        "raise_application_error (-20800,'Leerlingen in een Klas mag niet groter zijn dan Klaslokaal in School');\n" +
//                        "end if;\n" +
//                        "end;\n");
//        }
}