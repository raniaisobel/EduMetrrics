package com.example.edumetrrics;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentDetailsActivity extends AppCompatActivity {

    private HashMap<String, Student> studentMap;
    private Student selectedStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        studentMap = CSVUtils.readStudentsFromCSV(this);

        String studentId = getIntent().getStringExtra("student_id");

        // retrieve from hashmap
        selectedStudent = studentMap.get(studentId);

        if (selectedStudent != null) {
            ((TextView) findViewById(R.id.textStudentId)).setText("ID: " + selectedStudent.id);
            ((TextView) findViewById(R.id.textAge)).setText("Age: " + selectedStudent.age);
            ((TextView) findViewById(R.id.textGender)).setText("Gender: " + selectedStudent.gender);
            ((TextView) findViewById(R.id.textStudyHours)).setText("Study Hours/Week: " + selectedStudent.studyHoursPerWeek);
            ((TextView) findViewById(R.id.textAssignments)).setText("Assignments Completed: " + selectedStudent.assignmentCompletionRate + "%");
            ((TextView) findViewById(R.id.textExamScore)).setText("Exam Score: " + selectedStudent.examScore);
        }

        // calculate correlation using method
        double correlation = PearsonUtils.calculateCorrelation(new ArrayList<>(studentMap.values()));
        ((TextView) findViewById(R.id.textCorrelation)).setText("Correlation (Study Time & Score): " + String.format("%.4f", correlation));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // closes the current screen and returns to previous
        return true;
    }

}