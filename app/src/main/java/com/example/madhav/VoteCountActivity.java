package com.example.madhav;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VoteCountActivity extends AppCompatActivity {

    private TextView itVoteCountTextView;
    private TextView cseVoteCountTextView;
    private TextView adsVoteCountTextView;
    private TextView mctVoteCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_count);

        itVoteCountTextView = findViewById(R.id.itVoteCountTextView);
        cseVoteCountTextView = findViewById(R.id.cseVoteCountTextView);
        adsVoteCountTextView = findViewById(R.id.adsVoteCountTextView);
        mctVoteCountTextView = findViewById(R.id.mctVoteCountTextView);

        int itVoteCount = getVoteCountForIT(); // Replace with your actual implementation
        int cseVoteCount = getVoteCountForCSE(); // Replace with your actual implementation
        int adsVoteCount = getVoteCountForADS(); // Replace with your actual implementation
        int mctVoteCount = getVoteCountForMCT(); // Replace with your actual implementation

        itVoteCountTextView.setText("IT Vote Count: " + itVoteCount);
        cseVoteCountTextView.setText("CSE Vote Count: " + cseVoteCount);
        adsVoteCountTextView.setText("ADS Vote Count: " + adsVoteCount);
        mctVoteCountTextView.setText("MCT Vote Count: " + mctVoteCount);
    }

    // Replace with actual implementation to retrieve vote count for each category
    private int getVoteCountForIT() {
        return 5; // Example vote count value for IT
    }

    private int getVoteCountForCSE() {
        return 4; // Example vote count value for CSE
    }

    private int getVoteCountForADS() {
        return 3; // Example vote count value for ADS
    }

    private int getVoteCountForMCT() {
        return 2; // Example vote count value for MCT
    }
}
