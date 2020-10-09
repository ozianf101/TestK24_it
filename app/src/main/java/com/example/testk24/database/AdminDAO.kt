package com.example.testk24.database

import androidx.room.*
import com.example.testk24.model.Admin

@Dao
interface AdminDAO {
    @Insert
    fun insert(admin: Admin)

    @Update
    fun update(admin: Admin)

    @Delete
    fun delete(admin: Admin)

    @Query("SELECT * FROM admin")
    fun getData() : List<Admin>

    @Query("SELECT * FROM admin WHERE id = :id")
    fun getDataById(id:Int) : List<Admin>

    @Query("SELECT username FROM admin WHERE username = :uname AND password = :pass")
    fun getDataBySearch(uname:String,pass:String) : String
}