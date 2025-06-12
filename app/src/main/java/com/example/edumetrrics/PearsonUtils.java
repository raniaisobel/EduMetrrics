package com.example.edumetrrics;

import java.util.List;

public class PearsonUtils {

    public static double calculateCorrelation(List<Student> students) {
        int n = students.size();
        if (n == 0) return 0;

        double sumX = 0, sumY = 0, sumXY = 0;
        double sumX2 = 0, sumY2 = 0;

        for (Student s : students) {
            double x = s.studyHoursPerWeek;
            double y = s.examScore;

            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumX2 += x * x;
            sumY2 += y * y;
        }

        double numerator = (n * sumXY) - (sumX * sumY);
        double denominator = Math.sqrt((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY));
        return denominator == 0 ? 0 : numerator / denominator;
    }
}
