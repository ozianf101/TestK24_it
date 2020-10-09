package com.example.testk24.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "member")

@Parcelize
data class Member(
        @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "kode_member") var memberID : Int = 0,
        @ColumnInfo(name = "nama") var nama : String?,
        @ColumnInfo(name = "tgl_lahir") var tgllahir : String?,
        @ColumnInfo(name = "jenis_kelamin") var jenisKelamin : String?,
        @ColumnInfo(name = "username") var username : String?,
        @ColumnInfo(name = "password") var password : String?,
        @ColumnInfo(name = "alamat") var alamat : String?
) : Parcelable