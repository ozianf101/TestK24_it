package com.example.testk24.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "admin")

@Parcelize
data class Admin(
        @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var adminID : Int = 0,
        @ColumnInfo(name = "username") var username : String?,
        @ColumnInfo(name = "password") var password : String?
) : Parcelable