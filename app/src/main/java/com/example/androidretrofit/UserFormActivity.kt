package com.example.androidretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidretrofit.models.UserDataResponse
import kotlinx.android.synthetic.main.activity_user_form.*

class UserFormActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_USER = "user"
        const val EXTRA_USER_ID ="id"
    }

    private lateinit var viewModel: UserFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_form)

        initViewModel()
        setClickButton()
    }

    // init for view model
    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(UserFormViewModel::class.java)
    }

    // create function
    private fun createUser(newUserData: UserDataResponse) {
        viewModel.createUser(newUserData).observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, "Successfully Added!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // set up for button click
    private fun setClickButton() {
        btnUserFormAdd.setOnClickListener(this)
    }

    // implement OnClickListener interface
    override fun onClick(view: View) {
        when(view.id){
            R.id.btnUserFormAdd -> {
                val newUserData = UserDataResponse(
                    etUserFormAddress.text.toString(),
                    0,
                    etUserFormName.text.toString(),
                    etUserFormHp.text.toString()
                )

                pbUserForm.visibility = View.VISIBLE
                createUser(newUserData)
            }
        }
    }
}
