package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.adapter.ReportSaleAdapter
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.api.SaleCalls
import com.example.flowtrandingsystem.gui.model.ReportSale
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import kotlinx.android.synthetic.main.report.*
import retrofit2.Call
import retrofit2.Response

class ReportActivity : AppCompatActivity() {

    lateinit var adapterSales: ReportSaleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Relatorios")

        adapterSales = ReportSaleAdapter(this)


        loadReports()
    }

    private fun loadReports() {


        //loadReports()
        setPieChartData()
    }

    fun setPieChartData(){
        val xvalues = ArrayList<String>()
        xvalues.add("Compras")
        xvalues.add("Vendas")

        val pieChartEntry = ArrayList<Entry>()
        pieChartEntry.add(Entry(30f, 0))
        pieChartEntry.add(Entry(70f, 0))

        //colors
        val colorsChart = ArrayList<Int>()
        colorsChart.add(resources.getColor(R.color.primaryDark))
        colorsChart.add(resources.getColor(R.color.primary))


        //fill the chart
        val piedataSet = PieDataSet(pieChartEntry, "Compras e Vendas")

        piedataSet.colors = colorsChart

        piedataSet.sliceSpace = 2f

        val data = PieData(xvalues, piedataSet)
        pieChart.data = data

        pieChart.holeRadius = 5f


    }

   /* private  fun loadReports() {
>>>>>>> 074069adedb4b4f7e8befe1331ae7f1fdc03bd82
        val prefs: SharedPreferences = this@ReportActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val retrivedToken = prefs.getString("TOKEN", "Nada foi recebido")
        val retrivedBranchId = prefs.getInt("BRANCHID", 0)

        var report: List<Sale>
        val retrofit = RetrofitApi.getRetrofit()
        val reportCall = retrofit.create(SaleCalls::class.java)
        val call = reportCall.getSalesInfo(retrivedBranchId,"Bearer ${retrivedToken}")

        call.enqueue(object : retrofit2.Callback<List<Sale>>{

            override fun onFailure(call: Call<List<Sale>>, t: Throwable) {
                Toast.makeText(this@ReportActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("Erro_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<List<Sale>>, response: Response<List<Sale>>) {
                if (response.code() == 200 || response.code() == 201){
                    report = response.body()!!

                    Log.i("XPTO", response.body().toString())
                    adapterSales.updateSaleList(report)
                }else{
                    Toast.makeText(this@ReportActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }*/
}