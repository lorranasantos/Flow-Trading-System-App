package com.example.flowtrandingsystem.gui.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.flowtrandingsystem.R
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.api.UserCalls
import com.example.flowtrandingsystem.gui.model.User
import kotlinx.android.synthetic.main.user_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoActivity() : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var nameUser : TextView
    private lateinit var cpfUser : TextView
    private lateinit var rgUser : TextView
    private lateinit var roleUser : TextView
    private lateinit var branchUser : TextView

    private fun goToInfoCompany(){

        val companyScreen = Intent(this, CompanyInfoActivity::class.java)
        startActivity(companyScreen)
    }

    private fun goToMenu(){

        val menuScreen = Intent(this, MenuActivity::class.java)
        startActivity(menuScreen)
    }

    private fun goToLogin(){

        val loginScreen = Intent(this, MainActivity::class.java)
        startActivity(loginScreen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info)

        toggle = ActionBarDrawerToggle(this, drawerLayoutUserInfo, R.string.open, R.string.close)
        drawerLayoutUserInfo.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        nameUser = findViewById(R.id.user_name)
        cpfUser = findViewById(R.id.cpf_from_user)
        rgUser = findViewById(R.id.rg_from_user)
        roleUser = findViewById(R.id.role_from_user)
        branchUser = findViewById(R.id.branch_from_user)
        branchUser = findViewById(R.id.branch_from_user)

        user_navigation_view.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_option_home -> goToMenu()
                R.id.nav_option_profile -> Toast.makeText(this, "Tela Atual", Toast.LENGTH_SHORT).show()
                R.id.nav_option_company -> goToInfoCompany()
                R.id.nav_option_logout -> goToLogin()
            }
            true
        }

        loadInfo()
    }

    private  fun loadInfo() {

        //recuperar o token do sharedPreferences
        val prefs: SharedPreferences =
            this@UserInfoActivity.getSharedPreferences("preferencias", Context.MODE_PRIVATE)

        val retrivedToken =
            prefs.getString("TOKEN", "Nada foi recebido")

        val retrivedId =
            prefs.getInt("ID", 0)

        val retrivedCompanyId =
            prefs.getInt("COMPANYID", 0)

        Log.e("RETRIEVED", "Id: ${retrivedId} CompanyId: ${retrivedCompanyId} Token: ${retrivedToken}")

        var userInfo: User

        val retrofit = RetrofitApi.getRetrofit()
        val userCall = retrofit.create(UserCalls::class.java)

        val call = userCall.getInfoFromUser(retrivedId, "Bearer ${retrivedToken}")

        call.enqueue(object : Callback<User>{

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@UserInfoActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("ERRO_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                userInfo = response.body()!!

                Log.i("TESTE", response.body().toString())

                cpfUser.text = userInfo.cpf
                nameUser.text = userInfo.user_name
                rgUser.text = userInfo.rg
                roleUser.text = userInfo.Role.role_name
                branchUser.text = userInfo.Branch.branch_name
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
