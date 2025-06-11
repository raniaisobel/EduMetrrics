package com.example.edumetrrics;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    private HashMap<String, Student> studentMap;
    private BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        studentMap = CSVUtils.readStudentsFromCSV(this);
        chart = findViewById(R.id.barChart);

        displayBarChart();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // closes the current screen and returns to previous
        return true;
    }

    private void displayBarChart() {
        HashMap<String, List<Integer>> grouped = new HashMap<>();

        // iterates thru hashmap values
        for (Student s : studentMap.values()) {
            grouped.putIfAbsent(s.preferredLearningStyle, new ArrayList<>());
            grouped.get(s.preferredLearningStyle).add(s.examScore);
        }

        List<BarEntry> entries = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        int index = 0;
        for (String style : grouped.keySet()) {
            List<Integer> scores = grouped.get(style);
            float avg = 0;
            for (int score : scores) avg += score;
            avg /= scores.size();

            entries.add(new BarEntry(index, avg));
            labels.add(style);
            index++;
        }

        BarDataSet dataSet = new BarDataSet(entries, "Avg Exam Score");
        dataSet.setColors(
                Color.parseColor("#D9CFC1"),
                Color.parseColor("#767b91"),
                Color.parseColor("#37371F"),
                Color.parseColor("#412234")
        );
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.9f);

        chart.setData(barData);
        chart.setFitBars(true);
        chart.invalidate();

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        Description desc = new Description();
        desc.setText("Avg Score by Learning Style");
        chart.setDescription(desc);
    }
}