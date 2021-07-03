package com.example.flowtrandingsystem.gui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.flowtrandingsystem.R

class ReportActivity : AppCompatActivity() {

    lateinit var pieChart: Pie
    lateinit var anyChartView: AnyChartView
    lateinit var reportSaleToday: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        pieChart = AnyChart.pie()

        var statement =  ArrayList<DataEntry>()
        statement.add( ValueDataEntry("Lucro", 70))
        statement.add( ValueDataEntry("Prejuizo", 30))

        pieChart.data(statement)

        anyChartView = findViewById(R.id.pie_chart_report)
        anyChartView.setChart(pieChart)

    }
}