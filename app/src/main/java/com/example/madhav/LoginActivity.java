package com.example.madhav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button loginButton;

    private HashMap<String, String> userCredentials = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);

        userCredentials.put("Mohish", "Mohish@123");
        userCredentials.put("Karthik", "Karthik@321");
        userCredentials.put("1", "1");
        userCredentials.put("admin", "admin");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                    if (username.equals("admin")) {
                        openVoteCountActivity();
                    } else {
                        openMainActivity();
                    }
                } else {
                    showInvalidLoginAlert();
                }
            }
        });
    }

    private void openVoteCountActivity() {
        Intent intent = new Intent(LoginActivity.this, VoteCountActivity.class);
        startActivity(intent);
        finish();
    }

    private void openMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showInvalidLoginAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid Login");
        builder.setMessage("Invalid username or password. Please try again.");
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
