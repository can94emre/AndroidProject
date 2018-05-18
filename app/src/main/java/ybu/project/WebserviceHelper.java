package ybu.project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class WebserviceHelper {


    public static boolean checkLogin(String mail, String password, int type) {
        HttpHelper httpHelper = new HttpHelper(new HttpHelper.JsonRequest() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public JSONObject parse(String jsonString) throws JSONException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", jsonString.equals("true"));
                return jsonObject;
            }

            @Override
            public void onPostExecute(JSONObject json) {

            }
        });
        try {
            JSONObject jsonObject = httpHelper.execute("operation", "login",
                    "mail", mail,
                    "password", password,
                    "type", String.valueOf(type)).get();
            return jsonObject.getBoolean("result");
        } catch (InterruptedException | JSONException | ExecutionException | NullPointerException e) {
            return false;
        }
    }

    public static LinkedList<Message> listMessage(int lastMessageID) {
        HttpHelper httpHelper = new HttpHelper(new HttpHelper.JsonRequest() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public JSONObject parse(String jsonString) throws JSONException {
                if (jsonString.equals("false")) return null;
                JSONArray messages = new JSONArray(jsonString);
                return new JSONObject().put("messages", messages);
            }

            @Override
            public void onPostExecute(JSONObject json) {

            }
        });
        try {
            JSONObject json = httpHelper.execute("operation", "list_msg",
                    "last_id", String.valueOf(lastMessageID)
            ).get();
            JSONArray messages = json.getJSONArray("messages");
            LinkedList<Message> messageLinkedList = new LinkedList<>();
            for (int i = 0; i < messages.length(); i++) {
                messageLinkedList.add(
                        new Message(
                                messages.getJSONObject(i).getString("id"),
                                messages.getJSONObject(i).getString("subject"),
                                messages.getJSONObject(i).getString("content"))
                );
            }
            return messageLinkedList;
        } catch (InterruptedException | JSONException | ExecutionException e) {
            return null;
        }
    }

    public static boolean sendMessage(String subject, String content) {
        HttpHelper httpHelper = new HttpHelper(new HttpHelper.JsonRequest() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public JSONObject parse(String jsonString) throws JSONException {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", jsonString.equals("true"));
                return jsonObject;
            }

            @Override
            public void onPostExecute(JSONObject json) {

            }
        });
        try {
            JSONObject json = httpHelper.execute("operation", "send_msg",
                    "subject", subject,
                    "content", content).get();
            return json.getBoolean("result");
        } catch (JSONException | InterruptedException | ExecutionException e) {
            return false;
        }
    }
}
