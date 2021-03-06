package com.example.assignment3_sandeep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CandidateClass> candidates;
    private TextView txtView1, txtView2, txtView3;
    private Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView1 = findViewById(R.id.txtView1);
        txtView2 = findViewById(R.id.txtView2);
        txtView3 = findViewById(R.id.txtView3);

        btnNext = findViewById(R.id.btnNext);

        candidates = new ArrayList<CandidateClass>();

        Intent activityIntent = getIntent();

        ArrayList<CandidateClass> candidateArrayList = (ArrayList<CandidateClass>) activityIntent.getSerializableExtra("candidates");
        if(candidateArrayList == null){
            candidates.add(new CandidateClass(1,"Alex",0));
            candidates.add(new CandidateClass(2,"Moses",0));
            candidates.add(new CandidateClass(3,"Mike",0));
        }
        else{
            candidates = candidateArrayList;
        }

        txtView1.setText(candidates.get(0).getName()+" = " + candidates.get(0).getVotes() + " Votes");
        txtView2.setText(candidates.get(1).getName()+" = " + candidates.get(1).getVotes() + " Votes");
        txtView3.setText(candidates.get(2).getName()+" = " + candidates.get(2).getVotes() + " Votes");

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, SubmitVoteActivity.class);
                I.putExtra("candidates", candidates);
                startActivity(I);
            }
        });

    }
}