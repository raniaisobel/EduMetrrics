package com.example.edumetrrics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentList = CSVUtils.readStudentsFromCSV(this);

        // Sort students by examScore descending
        Collections.sort(studentList, (s1, s2) -> s2.examScore - s1.examScore);

        // Get top 10
        List<Student> top10 = studentList.subList(0, Math.min(10, studentList.size()));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTopStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StudentAdapter(top10));

        Button searchButton = findViewById(R.id.buttonSearch);
        EditText searchInput = findViewById(R.id.editTextStudentId);
        searchButton.setOnClickListener(v -> {
            String id = searchInput.getText().toString().trim();
            boolean found = false;

            for (Student s : studentList) {
                if (s.id.equalsIgnoreCase(id)) {
                    Intent intent = new Intent(MainActivity.this, StudentDetailsActivity.class);
                    intent.putExtra("student_id", id);
                    startActivity(intent);
                    found = true;
                    break;
                }
            }

            if (!found) {
                Toast.makeText(MainActivity.this, "Student ID not found", Toast.LENGTH_SHORT).show();
            }
        });

        Button chartButton = findViewById(R.id.buttonChart);
        chartButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChartActivity.class);
            startActivity(intent);
        });
    }
}
