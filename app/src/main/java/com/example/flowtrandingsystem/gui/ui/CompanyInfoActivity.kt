package com.example.flowtrandingsystem.gui.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.api.CompanyCalls
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.model.Company
import kotlinx.android.synthetic.main.company_info.*
import retrofit2.Call
import retrofit2.Response

class CompanyInfoActivity() : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var branchName: TextView
    private lateinit var companyName : TextView
    private lateinit var companyEmail : TextView
    private lateinit var companyPlan : TextView
    private lateinit var companyBusiness : TextView

    private fun goToMenu(){

        val menuScreen = Intent(this, MenuActivity::class.java)
        startActivity(menuScreen)
    }

    private fun goToInfoUser(){

        val userScreen = Intent(this, UserInfoActivity::class.java)
        startActivity(userScreen)
    }

    private fun goToLogin(){

        val loginScreen = Intent(this, MainActivity::class.java)
        startActivity(loginScreen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.company_info)

        toggle = ActionBarDrawerToggle(this, drawerLayoutCompanyInfo, R.string.open, R.string.close)
        drawerLayoutCompanyInfo.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        branchName = findViewById(R.id.branch_of_company)
        companyName = findViewById(R.id.name_of_company)
        companyEmail = findViewById(R.id.email_of_company)
        companyPlan = findViewById(R.id.plan_of_company)
        companyBusiness = findViewById(R.id.busines_of_company)

        company_navigation_view.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_option_home -> goToMenu()
                R.id.nav_option_profile -> goToInfoUser()
                R.id.nav_option_company -> Toast.makeText(this, "Tela Atual", Toast.LENGTH_SHORT).show()
                R.id.nav_option_logout -> goToLogin()
            }
            true
        }

        loadInfo()
    }

    private  fun loadInfo() {

        var companyInfo: Company = Company()

        val retrofit = RetrofitApi.getRetrofit()
        val companyCall = retrofit.create(CompanyCalls::class.java)

        val call = companyCall.getInfoFromCompany(companyInfo.id)

        call.enqueue(object : retrofit2.Callback<Company>{

            override fun onFailure(call: Call<Company>, t: Throwable) {
                Toast.makeText(this@CompanyInfoActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("ERRO_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<Company>, response: Response<Company>) {
                companyInfo = response.body()!!


                branchName = findViewById(R.id.branch_of_company)
                companyName = findViewById(R.id.name_of_company)
                companyEmail = findViewById(R.id.email_of_company)
                companyPlan = findViewById(R.id.plan_of_company)
                companyBusiness = findViewById(R.id.busines_of_company)

                branchName.text = companyInfo.Branch.branch_name
                companyName.text = companyInfo.fantasy_name
                companyEmail.text = companyInfo.commercial_email
                companyPlan.text = companyInfo.plan_id
                companyBusiness.text = companyInfo.nature_of_the_business

            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
