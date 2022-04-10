package com.example.ventazapas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ventazapas.AppNiceShoes.Companion.preferences
import com.example.ventazapas.data.fireStore.FireStoreImp
import com.example.ventazapas.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    val prueba = FireStoreImp()

    private val GOOGLE_SIGN = 100
    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogin.setOnClickListener {
            loginToGoogle()
        }
    }

    private fun loginToGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("285370486898-mp0mjk23rtot1sb4dae63jmud7qhhb99.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        startActivityForResult(googleSignInClient.signInIntent, GOOGLE_SIGN)
    }

    //conection whit Google Acount
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN) {
            val tast = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = tast.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Log.d("succes", account.email.toString())

                                preferences.saveUserEmail(account.email.toString())

                                prueba.getUser(preferences.getUserEmail()).observe(this,{
                                    if (it.name!="empty") {
                                        // salvar todos los datos importante en variables globales

                                        startActivity(Intent(this, DrawerActivity::class.java))
                                    }else{
                                        // crear la activity omboarding
                                        Toast.makeText(this,"enviar al omboarding",Toast.LENGTH_LONG).show()
                                    }
                                })


                            } else {
                                Toast.makeText(this, "Error login", Toast.LENGTH_LONG).show()
                            }
                        }
                }
            } catch (e: Exception) {
                Log.d("error", "Internet Conection")
            }
        }
    }
}