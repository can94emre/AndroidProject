package ybu.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentLogin extends AppCompatActivity {
    Button btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginstudent);
        final EditText mail = findViewById(R.id.editText);
        final EditText password = findViewById(R.id.editText2);
        btn4=(Button)findViewById(R.id.logstubtn);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loginCheck = WebserviceHelper.checkLogin(mail.getText().toString(), password.getText().toString(), 2);
                if(loginCheck) {
                    Intent intent3 = new Intent(StudentLogin.this, Studentmessage.class);
                    startActivity(intent3);
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext(), R.style.Theme_AppCompat_Dialog);
                    alertDialog.setMessage("Login credentials are not correct");
                    alertDialog.show();
                }
            }
        });
    }
}

