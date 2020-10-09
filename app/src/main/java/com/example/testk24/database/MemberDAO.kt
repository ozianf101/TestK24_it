package com.example.testk24.database

import androidx.room.*
import com.example.testk24.model.Member

@Dao
interface MemberDAO {
    @Insert
    fun insert(member: Member)

    @Update
    fun update(member: Member)

    @Delete
    fun delete(member: Member)

    @Query("SELECT * FROM member")
    fun getData() : List<Member>

    @Query("SELECT * FROM member WHERE kode_member = :id")
    fun getDataById(id:Int) : List<Member>

    @Query("SELECT * FROM member WHERE username = :uname AND password = :pass")
    fun getDataBySearch(uname:String,pass:String) : List<Member>
}