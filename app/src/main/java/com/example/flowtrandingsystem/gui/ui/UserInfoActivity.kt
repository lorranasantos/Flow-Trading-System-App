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
import com.example.flowtrandingsystem.gui.api.RetrofitApi
import com.example.flowtrandingsystem.gui.api.UserCalls
import com.example.flowtrandingsystem.gui.model.User
import com.example.flowtrandingsystem.gui.model.UserToken
import kotlinx.android.synthetic.main.user_info.*
import retrofit2.Call
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

        var userInfo: UserToken = UserToken()

        val retrofit = RetrofitApi.getRetrofit()
        val userCall = retrofit.create(UserCalls::class.java)

        val call = userCall.getInfoFromUser(userInfo.id)

        call.enqueue(object : retrofit2.Callback<UserToken>{

            override fun onFailure(call: Call<UserToken>, t: Throwable) {
                Toast.makeText(this@UserInfoActivity, "Ops! Acho que ocorreu um problema.", Toast.LENGTH_SHORT).show()
                Log.e("ERRO_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                userInfo = response.body()!!

                cpfUser.text = userInfo.user_cpf
                nameUser.text = userInfo.user_name
                rgUser.text = userInfo.user_rg
//                roleUser.text = userInfo.Role.role_name
                branchUser.text = userInfo.branch.branch_name

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
