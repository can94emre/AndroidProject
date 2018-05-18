package ybu.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TeacherLogin extends AppCompatActivity {
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginteacher);
        final EditText mail = findViewById(R.id.mail2);
        final EditText password = findViewById(R.id.password2);
        btn3 = (Button) findViewById(R.id.logteachbtn);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loginCheck = WebserviceHelper.checkLogin(mail.getText().toString(), password.getText().toString(), 1);
                if(loginCheck) {
                    Intent intent3 = new Intent(TeacherLogin.this, Teachermessage.class);
                    startActivity(intent3);
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());
                    alertDialog.setMessage("Login credentials are not correct");
                    alertDialog.show();
                }
            }
        });

    }
}
