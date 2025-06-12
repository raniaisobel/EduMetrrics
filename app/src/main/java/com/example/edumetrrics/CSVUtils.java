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
                        line[0],
                        Integer.parseInt(line[1]),
                        line[2],
                        Integer.parseInt(line[3]),
                        line[4],
                        Integer.parseInt(line[5]),
                        Integer.parseInt(line[6]),
                        Integer.parseInt(line[7]),
                        Integer.parseInt(line[8])
                );

                studentList.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList;
    }
}
