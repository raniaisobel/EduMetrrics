package com.example.edumetrrics;

import android.content.Context;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;

public class CSVUtils {

    public static List<Student> readStudentsFromCSV(Context context) {
        List<Student> studentList = new ArrayList<>();

        try {
            InputStream is = context.getResources().openRawResource(R.raw.student_performance);
            CSVReader reader = new CSVReader(new InputStreamReader(is));
            String[] line;
            boolean firstLine = true;

            while ((line = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                Student student = new Student(
                        line[0],                      // Student_ID
                        Integer.parseInt(line[1]),    // Age
                        line[2],                     // Gender
                        Integer.parseInt(line[3]),    // Study_Hours_per_Week
                        line[4],                     // Preferred_Learning_Style
                        Integer.parseInt(line[5]),    // Online_Courses_Completed
                        Integer.parseInt(line[7]),    // Assignment_Completion_Rate (%)
                        Integer.parseInt(line[8]),    // Exam_Score (%)
                        Integer.parseInt(line[9])     // Attendance_Rate (%)
                );

                studentList.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList;
    }
}
