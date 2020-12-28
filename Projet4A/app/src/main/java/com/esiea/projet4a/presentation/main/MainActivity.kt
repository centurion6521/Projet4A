package com.esiea.projet4a.presentation.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.esiea.projet4a.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //delay(1000)

        mainViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSuccess ->{
                    val activityIntent  =  Intent(this@MainActivity, PokemonActivity::class.java)
                    startActivity(activityIntent)
                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Compte Inconnu")
                        .setPositiveButton("ok") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        })
        login_button.setOnClickListener{
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(),password_edit.text.toString())
        }


        create_account__button.setOnClickListener{
            mainViewModel.onClickedCreate(login_edit.text.toString().trim(),password_edit.text.toString())
            MaterialAlertDialogBuilder(this)
                .setTitle("Info")
                .setMessage("Compte créé")
                .setPositiveButton("ok") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            //val activityIntent  =  Intent(this@MainActivity, PokemonActivity::class.java)
            //startActivity(activityIntent)

        }



    }

}