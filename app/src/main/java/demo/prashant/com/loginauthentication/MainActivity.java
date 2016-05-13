package demo.prashant.com.loginauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Button btnSignIn, btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String[] userType = {"Admin", "User",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        // Get The Refference Of Spiner
        Spinner spin = (Spinner) findViewById(R.id.spiner);

        // Set OnClick Listener on spiner button
        spin.setOnItemSelectedListener(this);

        // Get The Refference Of Buttons
        btnSignIn = (Button) findViewById(R.id.buttonSignIN);
        btnSignUp = (Button) findViewById(R.id.buttonSignUP);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, userType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.buttonSignIN:

                final EditText editTextUserName = (EditText) findViewById(R.id.editTextUserNameToLogin);
                final EditText editTextPassword = (EditText) findViewById(R.id.editTextPasswordToLogin);

                // get The User name and Password
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                // check if the Stored password matches with Password entered by user
                if (password.equals(storedPassword)) {
                    Intent userintent = new Intent(MainActivity.this, UserProfile.class);
                    startActivity(userintent);
                } else {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
                break;


            case R.id.buttonSignUP:
                Intent intentSignUP = new Intent(getApplicationContext(), SignUPActivity.class);
                startActivity(intentSignUP);
                break;
        }

    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}