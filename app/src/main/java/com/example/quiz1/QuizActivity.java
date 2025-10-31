package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    TextView tvQuestion, tvScore, tvSummary;
    RadioGroup radioGroup;
    RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    Button btnNext, btnRetake;

    String username;
    int score = 0;
    int currentQuestion = 0;

    // ✅ 15 Questions
    String[] questions = {
            "1. What is the capital of France?",
            "2. Who developed Java?",
            "3. Which planet is called the Red Planet?",
            "4. What is 5 + 7?",
            "5. Which is the largest ocean?",
            "6. Who wrote 'Romeo and Juliet'?",
            "7. Which gas do plants absorb?",
            "8. What is the currency of Japan?",
            "9. Who painted the Mona Lisa?",
            "10. Which is the fastest land animal?",
            "11. What is H2O commonly known as?",
            "12. Who is known as the Father of Computers?",
            "13. Which planet has rings?",
            "14. Which is the smallest prime number?",
            "15. Which is the national bird of India?"
    };

    String[][] options = {
            {"Berlin", "Madrid", "Paris", "Rome"},
            {"Microsoft", "Sun Microsystems", "Apple", "Google"},
            {"Earth", "Mars", "Jupiter", "Venus"},
            {"10", "11", "12", "13"},
            {"Indian", "Atlantic", "Pacific", "Arctic"},
            {"Shakespeare", "Charles Dickens", "Homer", "Tolstoy"},
            {"Oxygen", "Carbon Dioxide", "Nitrogen", "Helium"},
            {"Yuan", "Dollar", "Yen", "Rupee"},
            {"Van Gogh", "Picasso", "Da Vinci", "Rembrandt"},
            {"Cheetah", "Tiger", "Horse", "Lion"},
            {"Salt", "Water", "Acid", "Alcohol"},
            {"Newton", "Charles Babbage", "Einstein", "Turing"},
            {"Mars", "Jupiter", "Saturn", "Venus"},
            {"0", "1", "2", "3"},
            {"Peacock", "Sparrow", "Eagle", "Crow"}
    };

    int[] answers = {2, 1, 1, 2, 2, 0, 1, 2, 2, 0, 1, 1, 2, 2, 0}; // correct options

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get username from login
        username = getIntent().getStringExtra("USERNAME");

        // Link UI
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        tvSummary = findViewById(R.id.tvSummary);
        radioGroup = findViewById(R.id.radioGroup);
        rbOption1 = findViewById(R.id.rbOption1);
        rbOption2 = findViewById(R.id.rbOption2);
        rbOption3 = findViewById(R.id.rbOption3);
        rbOption4 = findViewById(R.id.rbOption4);
        btnNext = findViewById(R.id.btnNext);
        btnRetake = findViewById(R.id.btnRetake);

        loadQuestion();

        btnNext.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            int answerIndex = -1;
            if (selectedId == R.id.rbOption1) answerIndex = 0;
            else if (selectedId == R.id.rbOption2) answerIndex = 1;
            else if (selectedId == R.id.rbOption3) answerIndex = 2;
            else if (selectedId == R.id.rbOption4) answerIndex = 3;

            if (answerIndex == answers[currentQuestion]) {
                score++;
                tvScore.setText("Score: "+ score);
            }

            currentQuestion++;
            if (currentQuestion < questions.length) {
                loadQuestion();
            } else {
                showSummary();
            }
        });

        btnRetake.setOnClickListener(v -> {
            score = 0;
            currentQuestion = 0;
            tvScore.setText("Score: 0");
            btnNext.setEnabled(true);
            tvSummary.setVisibility(View.GONE);
            btnRetake.setVisibility(View.GONE);
            tvQuestion.setVisibility(View.VISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            loadQuestion();
        });
    }

    private void loadQuestion() {
        tvQuestion.setText(questions[currentQuestion]);
        rbOption1.setText(options[currentQuestion][0]);
        rbOption2.setText(options[currentQuestion][1]);
        rbOption3.setText(options[currentQuestion][2]);
        rbOption4.setText(options[currentQuestion][3]);
        radioGroup.clearCheck();
    }

    private void showSummary() {
        tvQuestion.setVisibility(View.GONE);
        radioGroup.setVisibility(View.GONE);
        btnNext.setVisibility(View.GONE);

        String summary = "Quiz Finished!\n\n" +
                "User: " + username + "\n" +
                "Final Score: " + score + "/" + questions.length;

        tvSummary.setText(summary);
        tvSummary.setVisibility(View.VISIBLE);
        btnRetake.setVisibility(View.VISIBLE);
    }
}
