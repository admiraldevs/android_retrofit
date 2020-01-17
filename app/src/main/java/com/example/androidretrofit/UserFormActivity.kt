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
        const val EXTRA_USER_ID ="id"
    }

    private lateinit var viewModel: UserFormViewModel
    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_form)

        initViewModel()
        getIntentExtra()
        getUserData()
        setClickButton()
    }

    // get data user id from user activity
    private fun getIntentExtra() {
        userId = intent.getIntExtra(EXTRA_USER_ID, 0)
    }

    // init for view model
    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(UserFormViewModel::class.java)
    }

    // get single data
    private fun getUser(userId: Int){
        viewModel.getUser(userId).observe(this, Observer { userDataReponse ->
            if(userDataReponse != null){
                Toast.makeText(this, "User loaded successfull!", Toast.LENGTH_SHORT).show()
                populateFormData(userDataReponse)
                pbUserForm.visibility = View.GONE
                llMyUserFormLoading.visibility = View.VISIBLE
            }
        })
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

    // update data
    private fun updateUser(id: Int, userData: UserDataResponse) {
        viewModel.updateUser(id, userData).observe(this, Observer { userDataReponse ->

            if (userDataReponse != null) {
                Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to update", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun populateFormData(user: UserDataResponse) {
        userId = user.id
        etUserFormName.setText(user.name)
        etUserFormAddress.setText(user.address)
        etUserFormHp.setText(user.phone)
    }

    // get data user item
    private fun getUserData() {
        pbUserForm.visibility = View.VISIBLE
        llMyUserFormLoading.visibility = View.GONE
        getUser(userId as Int)
    }

    // set up for button click
    private fun setClickButton() {
        btnUserFormAdd.setOnClickListener(this)
        btnUserFormUpdate.setOnClickListener(this)
        btnUserFormDelete.setOnClickListener(this)
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
            R.id.btnUserFormUpdate -> {
                userId?.let { id ->
                    val updateUserData = UserDataResponse(
                        etUserFormAddress.text.toString(),
                        id,
                        etUserFormName.text.toString(),
                        etUserFormHp.text.toString()
                    )

                    updateUser(id, updateUserData)
                }
            }
            R.id.btnUserFormDelete -> {

            }
        }
    }
}
