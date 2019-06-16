package REST;

import Socketcomm.SocketMsg;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RESTCommunicator implements  IRESTCommunnicator{
    private final String url = "http://0.0.0.0:8093/login/";


    private final Gson gson = new Gson();



    public RESTMsg postRegister(RESTMsg restMsg){

        final String query = url + "register";

        HttpPost httPostQuery = new HttpPost(query);
        httPostQuery.addHeader("content-type","application/json");

        StringEntity params;

        try {
            params = new StringEntity(gson.toJson(restMsg, RESTMsg.class));
            httPostQuery.setEntity(params);

        } catch(Exception e){
            System.out.println("fout" + e);

        }
        return executeQuery(httPostQuery);
    }



    private RESTMsg executeQuery(HttpRequestBase requestBaseQuery){
        //Greeting greeting = null;
        RESTMsg info = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(requestBaseQuery)) {

            System.out.println("status: "+ response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);

            System.out.println("json "+ entityString);

            info = gson.fromJson(entityString, RESTMsg.class);

        } catch (Exception e) {
            System.out.println(e.toString());

        }

        return info;
    }
}
