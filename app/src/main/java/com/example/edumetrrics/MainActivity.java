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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, Student> studentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentMap = CSVUtils.readStudentsFromCSV(this);

        // convert hashmap to list for sorting
        List<Student> studentList = new ArrayList<>(studentMap.values());

        // sort by descending
        Collections.sort(studentList, (s1, s2) -> s2.examScore - s1.examScore);

        //getting top 10
        List<Student> top10 = studentList.subList(0, Math.min(10, studentList.size()));

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTopStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StudentAdapter(top10));

        Button searchButton = findViewById(R.id.buttonSearch);
        EditText searchInput = findViewById(R.id.editTextStudentId);
        searchButton.setOnClickListener(v -> {
            String id = searchInput.getText().toString().trim();
            Student foundStudent = studentMap.get(id); // Directly access student by ID

            if (foundStudent != null) {
                Intent intent = new Intent(MainActivity.this, StudentDetailsActivity.class);
                intent.putExtra("student_id", id);
                startActivity(intent);
            } else {
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