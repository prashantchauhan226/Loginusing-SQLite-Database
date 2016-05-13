package demo.prashant.com.loginauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnSignIn, btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

// Get The Refference Of Buttons
        btnSignIn = (Button) findViewById(R.id.buttonSignIN);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editTextUserName = (EditText) findViewById(R.id.editTextUserNameToLogin);
                final EditText editTextPassword = (EditText)findViewById(R.id.editTextPasswordToLogin);
                // get The User name and Password
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                      // fetch the Password form database for respective user name
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                 // check if the Stored password matches with Password entered by user
                if (password.equals(storedPassword)) {
                    Intent userintent = new Intent(MainActivity.this, UserProfile.class);
                    startActivity(userintent);
                    //Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    //dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSignUp = (Button) findViewById(R.id.buttonSignUP);

         // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

               /// Create Intent for SignUpActivity abd Start The Activity
                Intent intentSignUP = new Intent(getApplicationContext(), SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
          // Close The Database
        loginDataBaseAdapter.close();
    }
}