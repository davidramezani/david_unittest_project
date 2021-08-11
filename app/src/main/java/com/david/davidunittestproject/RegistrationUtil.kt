package com.david.davidunittestproject

object RegistrationUtil {

    private val existingUsers = listOf("Peter", "Carl")

    /**
     * the input is not valid if ...
     * ... the username is empty
     * ... the username is already taken
     * ... the confirmed password is not the same as the real password
     * ... the password contains less than 2 digits
     */


    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmedPassword: String
    ): Boolean {
        return true
    }

}