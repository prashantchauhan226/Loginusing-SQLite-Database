package demo.prashant.com.loginauthentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by PRASHANT CHAUHAN on 12-May-16.
 */
public class SignUPActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{
    EditText editTextUserName, editTextPassword, editTextConfirmPassword;
    Button btnCreateAccount;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String[] userType = {"Admin", "User",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        // get Instance of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        // Get The Refference Of Spiner
        Spinner spin = (Spinner) findViewById(R.id.spiner);

        // Set OnClick Listener on spiner button
        spin.setOnItemSelectedListener(this);


// Get Refferences of Views
        editTextUserName=(EditText)findViewById(R.id.editTextUserName);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);

        btnCreateAccount=(Button)findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// TODO Auto-generated method stub

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();

// check if any of the fields are vaccant
                if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
// check if both password matches
                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {

// Save the Data in Database
                    loginDataBaseAdapter.insertEntry(userName, password);
                    Intent homeintent = new Intent(SignUPActivity.this,MainActivity.class);
                    startActivity(homeintent);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, userType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }
    @Override
    protected void onDestroy() {
// TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
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