package com.example.assignment3_sandeep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class SubmitVoteActivity extends AppCompatActivity {
    private Spinner candidateSpinner;
    ToggleButton toggleButton;
    Button btnSubmit;
    EditText voterName, voterID;
    private ArrayList<CandidateClass> candidateList;
    ArrayList<VoterClass> voterClassArrayList;
    ArrayAdapter<CandidateClass> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_vote);

        initialize();

        Intent I = getIntent();
        ArrayList<CandidateClass> candidates = (ArrayList<CandidateClass>) I.getSerializableExtra("candidates");
        candidateList = candidates;

        adapter = new ArrayAdapter<CandidateClass>(this,
                android.R.layout.simple_spinner_item, candidateList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        candidateSpinner.setAdapter(adapter);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(voterName.getText().toString().isEmpty()){
                    Toast.makeText(SubmitVoteActivity.this, "Name is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(voterID.getText().toString().isEmpty()){
                    Toast.makeText(SubmitVoteActivity.this, "ID is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (VoterClass V : voterClassArrayList) {
                    if(V.getId() == Integer.parseInt(voterID.getText().toString())){
                        Toast.makeText(SubmitVoteActivity.this, "Duplicate ID not allowed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!toggleButton.isChecked()){
                    Toast.makeText(SubmitVoteActivity.this, "click on accept terms and conditions", Toast.LENGTH_SHORT).show();
                    return;
                }

                voterClassArrayList.add(new VoterClass(Integer.parseInt(voterID.getText().toString()), voterName.getText().toString()));
                int selectedCandidateIndex = candidateSpinner.getSelectedItemPosition();
                CandidateClass selectedCandidate = candidateList.get(selectedCandidateIndex);
                selectedCandidate.setVotes(selectedCandidate.getVotes() + 1);

                Toast.makeText(SubmitVoteActivity.this, "Vote Submitted!!", Toast.LENGTH_SHORT).show();


            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    toggleButton.setTextOn("Refuse");

                } else {

                    toggleButton.setTextOff("Accept");
                }
            }
        });


    }

    private void initialize() {
        voterClassArrayList = new ArrayList<VoterClass>();
        candidateSpinner = findViewById(R.id.candidateSpinner);
        toggleButton = findViewById(R.id.toggleButton);
        btnSubmit = findViewById(R.id.btnSubmitVote);
        voterName = findViewById(R.id.editTextName);
        voterID = findViewById(R.id.editTextId);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SubmitVoteActivity.this, MainActivity.class);
        intent.putExtra("candidates", candidateList);
        startActivity(intent);
    }
}