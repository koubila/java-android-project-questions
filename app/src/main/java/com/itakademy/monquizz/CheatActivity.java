package com.itakademy.monquizz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.itakademy.monquizz.pojos.Question;


public class CheatActivity extends AppCompatActivity {

    private TextView tvReponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        tvReponse = findViewById(R.id.tvReponse);

        // récupère la toolbar et l'affecte comme ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // récupere la action bar et set a true
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        // récupère l'intention
        Intent intent = getIntent();
        // récupère la question dans l'intention
        Question question = (Question) intent.getSerializableExtra(MainActivity.KEY_QUESTION);
        // remplace le texte "reponse" par la question contenant aussi le bool reponse
        tvReponse.setText(String.format("%s : %s", question.getText(), question.isAnswer()));

    }

    @Override
    public boolean onSupportNavigateUp() {
        // ca termine l'activité
        finish();
        return true;
    }
}