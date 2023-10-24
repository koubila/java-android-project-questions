package com.itakademy.monquizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itakademy.monquizz.pojos.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // declare global scope variables, warning obj null
    private TextView tvQuestion;
    private TextView tvScore;
    private Button trueButton;
    private Button falseButton;
    private Button reset;
    private Context context;
    private List<Question> questions = new ArrayList<>();
    private int index = 0;
    private int score = 0;

    // save data with constante
    public static final String KEY_INDEX = "index";
    public static final String KEY_SCORE = "score";

    public static final String KEY_QUESTION = "question";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //déclare le layout de l'activité principale
        setContentView(R.layout.activity_main);

        // récupère la toolbar et l'affecte comme ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // recupere les éléments
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        trueButton = findViewById(R.id.vrai);
        falseButton = findViewById(R.id.faux);
        reset = findViewById(R.id.reset);
        // recupere context application
        context = getApplicationContext();

        // Créer les questions
        questions.add(new Question(getString(R.string.question_ai), true));
        questions.add(new Question(getString(R.string.question_taxi_driver), true));
        questions.add(new Question(getString(R.string.question_2001), false));
        questions.add(new Question(getString(R.string.question_reservoir_dogs), true));
        questions.add(new Question(getString(R.string.question_citizen_kane), false));

        // quand il y a une destruction (rotation screen)
        if (savedInstanceState != null){
            // récupere les données misent dans le bundle
            index = savedInstanceState.getInt(KEY_INDEX);
            score = savedInstanceState.getInt(KEY_SCORE);
        }

        // afficher la question 1, au début index est déclarer à 0, durant une destruction
        tvQuestion.setText(questions.get(index).getText());
        //affiche le score
        tvScore.setText( "Score :"+ score);

        // Listener avec un click avec interface View
        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                // Si la réponse de la question est bien vrai
                if (questions.get(index).isAnswer()){

                    // affiche un toast
                    CharSequence text = "Bonne réponse +1 point !";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    // incremente score
                    score++;

                    // afficher le score
                    tvScore.setText( "Score :"+ score);
                }else{
                    // affiche un toast
                    CharSequence text = "Mauvaise réponse!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                if ( index < questions.size()-1 ){
                    // incremente index meme si ce nest pas true
                    index++;

                    // affiche la question de l'index suivant
                    tvQuestion.setText(questions.get(index).getText());
                }
                // afficher le bouton retry à la dernière question
                else{
                    reset.setVisibility(View.VISIBLE);
                    trueButton.setVisibility(View.INVISIBLE);
                    falseButton.setVisibility(View.INVISIBLE);
                }


            }
        });

        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                // Si la réponse de la question est bien faux
                if (!questions.get(index).isAnswer()){

                    // affiche un toast
                    CharSequence text = "Bonne réponse!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    // incremente score
                    score++;
                    // afficher le score pas besoin si pas de changement
                    tvScore.setText( "Score :"+ score);
                }else{
                    // affiche un toast
                    CharSequence text = "Mauvaise réponse!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                // si index inf. 4 alors incremente et affiche
                if ( index < questions.size()-1 ){
                    // incremente index meme si ce nest pas true
                    index++;

                    // affiche la question de l'index suivant
                    tvQuestion.setText(questions.get(index).getText());
                }
                // afficher le bouton retry à la dernière question
                else{
                    reset.setVisibility(View.VISIBLE);
                    trueButton.setVisibility(View.INVISIBLE);
                    falseButton.setVisibility(View.INVISIBLE);
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = 0;
                index = 0;
                trueButton.setVisibility(View.VISIBLE);
                falseButton.setVisibility(View.VISIBLE);
                reset.setVisibility(View.INVISIBLE);
                // afficher la question 1
                tvQuestion.setText(questions.get(0).getText());
            }
        });


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // met dans le bundle
        outState.putInt(KEY_INDEX, index);
        outState.putInt(KEY_SCORE, score);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu_main);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Intention to start another activity
        if (item.getItemId() == R.id.cheat){
            // instancier une intention avec parametre
            Intent intent=new Intent(this,CheatActivity.class);

            // class Question a été serialiser car putExtra prend en argument string
            // on ajoute à intent les donnée souhaiter
            intent.putExtra(KEY_QUESTION, questions.get(index));
            // démarre activité
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
