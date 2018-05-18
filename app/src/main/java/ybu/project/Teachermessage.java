package ybu.project;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Teachermessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachermessage);
        final EditText message = findViewById(R.id.edt);
        Button sendButton = findViewById(R.id.button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = WebserviceHelper.sendMessage("", message.getText().toString());
                if(result) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Teachermessage.this, R.style.Theme_AppCompat_Dialog);
                    alertDialog.setMessage("Message sent!");
                    alertDialog.show();
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Teachermessage.this, R.style.Theme_AppCompat_Dialog);
                    alertDialog.setMessage("There was an error when sending the message.");
                    alertDialog.show();
                }
            }
        });
    }
    public void sendNotification(View view) {

        //Get an instance of NotificationManager//
        Intent notintent = new Intent(Teachermessage.this,Teachermessage.class);
        notintent.putExtra("key1","value1");

        final EditText edit = (EditText) findViewById(R.id.edt);
        edit.getText().toString();
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentText(edit.getText().toString());
        PendingIntent pending = PendingIntent.getActivity(this,0,notintent , 0);
        mBuilder.setContentIntent(pending);


        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than create a new one. In this example, the notification’s ID is 001//



        mNotificationManager.notify(001, mBuilder.build());
    }
}
