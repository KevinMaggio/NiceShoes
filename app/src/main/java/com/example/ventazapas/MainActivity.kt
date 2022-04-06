package com.example.ventazapas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ventazapas.AppNiceShoes.Companion.preferences
import com.example.ventazapas.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val GOOGLE_SIGN = 100
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLoginGoogle.setOnClickListener {
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
                                //move and send email, to next activity
                                Log.d("succes", account.email.toString())


                                preferences.saveUserEmail(account.email.toString()!!)
                                preferences.saveAccessToken(it.result.credential.toString())
                                preferences.saveFireBaseAuth(it.result.user?.uid.toString())
                                //ACCESS_TOKEN= preferences.getAccessToken()



                                startActivity(Intent(this, DrawerActivity::class.java))
                               // userViewModel.userLogin(preferences.getFireBaseAuth(), preferences.getAccessToken())

                                Log.d("id credential",it.result.credential.toString())
                                Log.d("id uid", it.result.user?.uid.toString())
                                Log.d("idToken google",account.idToken.toString())
                                Log.d("id google",account.id.toString())
                                Log.d("id fireBase userName",it.result.additionalUserInfo?.username.toString())
                                Log.d("id",it.result.additionalUserInfo?.providerId.toString())

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