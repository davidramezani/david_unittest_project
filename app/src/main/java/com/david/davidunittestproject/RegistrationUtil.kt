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
        if(username.isEmpty()) return false
        if(password.isEmpty()) return false
        if(password != confirmedPassword) return false

        val regEx = Regex("^.*[0-9]{2}\$")
        if(!password.contains(regEx)) return false

        if(password.count { it.isDigit() } < 2) {
            return false
        }

        if(existingUsers.contains(username)) return false

        return true
    }

}