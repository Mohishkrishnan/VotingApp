package com.example.madhav;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private List<String> selectedVotingOptions = new ArrayList<>();
    private ListView listView;
    private Map<String, Integer> votingOptionsPrices = new HashMap<>();
    private String[] votingOptions = {"IT", "CSE", "ADS", "MCT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.votingListView);
        Button submitButton = findViewById(R.id.submitbutton);

        for (String option : votingOptions) {
            votingOptionsPrices.put(option, 0);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, votingOptions);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String option = votingOptions[position];
                if (listView.isItemChecked(position)) {
                    if (selectedVotingOptions.size() > 0) {
                        listView.setItemChecked(position, false);
                        showAlertDialog("Invalid Selection", "You can select only one option at a time.");
                    } else {
                        selectedVotingOptions.add(option);
                        votingOptionsPrices.put(option, votingOptionsPrices.get(option) + 1); // Increment the vote count for the option
                        sendVoteCountToAdmin(option, votingOptionsPrices.get(option));
                    }
                } else {
                    selectedVotingOptions.remove(option);
                    votingOptionsPrices.put(option, votingOptionsPrices.get(option) - 1); // Decrement the vote count for the option
                    sendVoteCountToAdmin(option, votingOptionsPrices.get(option));
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedVotingOptions.isEmpty()) {
                    showAlertDialog("No Selection", "Please select an option to submit your vote.");
                } else {
                    showAlertDialogAndMoveToLogin("Vote Submitted", "Vote was Submitted");
                }
            }
        });
    }

    private void sendVoteCountToAdmin(String option, int voteCount) {
        // Assuming you have some method to send this data to the admin, you can implement it here
        // For example, you can use an API call or some other communication method to update the admin's vote count

        // Example implementation using logs
        Log.d("Vote Count Update", "Option: " + option + ", Vote Count: " + voteCount);
    }

    private void showAlertDialogAndMoveToLogin(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                moveToLoginPage();
            }
        });
        builder.show();
    }

    private void moveToLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
