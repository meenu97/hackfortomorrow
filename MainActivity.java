package com.rakesh.arfirstaid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userText,passText,mobileText;

    TextView textView;

    Button button;

    SharedPreferences sharedPreferences;

    public void onClick(View view) {

        if (view.getId() == R.id.loginText) {

            String loginText = textView.getText().toString();

            userText.setText("");
            passText.setText("");
            mobileText.setText("");

            if (loginText.matches("OR, LOGIN")) {

                button.setText("login");

                userText.setText(sharedPreferences.getString("username",""));

                passText.setText(sharedPreferences.getString("password",""));

                mobileText.setVisibility(View.GONE);

                textView.setText("OR, SIGNUP");

            } else {

                button.setText("signup");

                textView.setText("OR, LOGIN");

                mobileText.setVisibility(View.VISIBLE);

            }
        }

    }

    public void loginfun(View view) {

        if (button.getText().toString().matches("signup")) {

            String username = userText.getText().toString();

            String mobile = mobileText.getText().toString();

            String password = passText.getText().toString();

            if (!(username.matches("") || mobile.matches("") || password.matches(""))) {

                sharedPreferences.edit().putString("username", username).apply();

                sharedPreferences.edit().putString("mobile", mobile).apply();

                sharedPreferences.edit().putString("password", password).apply();

                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

                startActivity(intent);

            } else {

                Toast.makeText(this, "All values are required!!!", Toast.LENGTH_SHORT).show();

            }
        } else {

            String username = userText.getText().toString();

            String password = passText.getText().toString();

            String saveName = sharedPreferences.getString("username","");

            String savePass = sharedPreferences.getString("password","");

            if(!(username.matches("") || password.matches(""))) {

                if(username.equals(saveName) && !(saveName.equals(""))) {

                    //Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();

                    if (password.equals(savePass)) {

                        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

                        startActivity(intent);

                    } else {

                        Toast.makeText(this, "Wrong Password : " + sharedPreferences.getString("password",""), Toast.LENGTH_SHORT).show();

                    }
                } else {

                    Toast.makeText(this, "Check Username : " + sharedPreferences.getString("username",""), Toast.LENGTH_SHORT).show();

                }

            } else {

                Toast.makeText(this, "Username & Password Required", Toast.LENGTH_SHORT).show();

            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userText = findViewById(R.id.usernameText);

        passText = findViewById(R.id.passwordText);

        mobileText = findViewById(R.id.phoneNumberText);

        textView = findViewById(R.id.loginText);

        button = findViewById(R.id.signupButton);

        textView.setOnClickListener(this);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.rakesh.afirstaid", MODE_PRIVATE);


    }
}
