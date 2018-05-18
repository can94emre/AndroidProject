package ybu.project;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Studentmessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentmessage);
        Button getMessagesButton = findViewById(R.id.get_messages);
        final ListView listView = findViewById(R.id.messages_list);
        getMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinkedList<Message> messages = WebserviceHelper.listMessage(0);
                if(messages != null){
                    List<Map<String, String>> data = new ArrayList<Map<String, String>>();
                    for(Message message : messages){
                        Map<String, String> datum = new HashMap<String, String>(2);
                        datum.put("title", message.subject);
                        datum.put("date", message.content);
                        data.add(datum);
                    }
                    listView.setAdapter(new SimpleAdapter(getApplicationContext(), data, android.R.layout.simple_list_item_2,
                            new String[] {"title", "date"},
                            new int[] {android.R.id.text1,
                                    android.R.id.text2}));
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Studentmessage.this, R.style.Theme_AppCompat_Dialog);
                    alertDialog.setMessage("There was an error while retrieving the messages.");
                    alertDialog.show();
                }

            }
        });
    }
}
