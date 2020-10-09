package com.example.testk24.data

import android.content.Context
import com.example.testk24.DataConst
import com.example.testk24.data.model.LoggedInUser
import com.example.testk24.database.KlinikDB
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String, context: Context): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val database = KlinikDB.getDatabase(context)
            val dao = database.getAdminDao()
            val user =  dao.getDataBySearch(username,password)
            DataConst.DataLogin = emptyList()
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), user)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            try {
                val database = KlinikDB.getDatabase(context)
                val dao = database.getMemberDao()
                val user =  dao.getDataBySearch(username,password)
                DataConst.DataLogin = user
                val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), user.get(0).nama.toString())
                return Result.Success(fakeUser)
            }catch (e: Throwable){
                return Result.Error(IOException("Error logging in", e))
            }
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}