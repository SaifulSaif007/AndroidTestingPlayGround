package com.saiful.testingplayground.unittest.util

object RegistrationUtil {

    fun validateInput(
        userName: String?,
        password: String?,
        confirmPassword: String?
    ): Boolean {
        if (userName.isNullOrEmpty() || password.isNullOrEmpty() || confirmPassword.isNullOrEmpty()){
            return false
        }
        if (password.length< 4 || password != confirmPassword){
            return false
        }
        return true
    }
}