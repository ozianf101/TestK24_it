package com.example.testk24

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testk24.database.KlinikDB
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(),OnInput {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        nomor.text = DataConst.DataLogin.get(0).memberID.toString()
        nama_member.text = DataConst.DataLogin.get(0).nama
        alamat.text = DataConst.DataLogin.get(0).alamat
        jk.text = DataConst.DataLogin.get(0).jenisKelamin
        ttl.text = DataConst.DataLogin.get(0).tgllahir

        but_edit.setOnClickListener {
            val add = InputFragment(this)
            val bundle = Bundle().apply {
                putInt("edit",DataConst.DataLogin.get(0).memberID)
            }
            add.arguments = bundle
            add.show(this.supportFragmentManager,"sukses")
        }
    }

    override fun onProgress() {

    }

    override fun onEdit(id: Int) {
        val db =KlinikDB.getDatabase(applicationContext)
        val dao = db.getMemberDao()
        DataConst.DataLogin = dao.getDataById(id)
        finish()
        startActivity(intent)
    }
}