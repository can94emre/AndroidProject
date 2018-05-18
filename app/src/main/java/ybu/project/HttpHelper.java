package ybu.project;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class HttpHelper extends AsyncTask<String, String, JSONObject> {

    private final JsonRequest implemented;
    public boolean finished;

    public HttpHelper(JsonRequest implemented) {
        this.implemented = implemented;
        finished = false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        implemented.onPreExecute();
        finished = false;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            URL url = new URL("http://api.melihyildiz.com/api.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            OutputStreamWriter request = new OutputStreamWriter(connection.getOutputStream());
            StringBuilder requestParamsBuilder = new StringBuilder();
            for (int i = 0; i < params.length; i += 2) {
                requestParamsBuilder.append(URLEncoder.encode(params[i], "utf-8")).append("=").append(URLEncoder.encode(params[i + 1], "utf-8")).append("&");
            }
            String requestParams = requestParamsBuilder.toString();
            requestParams = requestParams.substring(0, requestParams.length() - 1);
            request.write(requestParams);
            request.flush();
            request.close();
            InputStream stream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }
            connection.disconnect();
            return implemented.parse(builder.toString());
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        super.onPostExecute(json);
        implemented.onPostExecute(json);
        finished = true;
    }

    public interface JsonRequest {
        void onPreExecute();
        JSONObject parse(String jsonString) throws JSONException;
        void onPostExecute(JSONObject json);
    }
}