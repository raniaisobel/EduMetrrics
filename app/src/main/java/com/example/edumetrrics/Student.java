package com.example.edumetrrics;

public class Student {
    public String id;
    public int age;
    public String gender;
    public int studyHoursPerWeek;
    public String preferredLearningStyle;
    public int onlineCoursesCompleted;
    public int assignmentCompletionRate;
    public int examScore;
    public int attendanceRate;

    public Student(String id, int age, String gender, int studyHoursPerWeek, String preferredLearningStyle,
                   int onlineCoursesCompleted, int assignmentCompletionRate, int examScore, int attendanceRate) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.studyHoursPerWeek = studyHoursPerWeek;
        this.preferredLearningStyle = preferredLearningStyle;
        this.onlineCoursesCompleted = onlineCoursesCompleted;
        this.assignmentCompletionRate = assignmentCompletionRate;
        this.examScore = examScore;
        this.attendanceRate = attendanceRate;
    }
}
