package com.example.testk24

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testk24.database.KlinikDB
import com.example.testk24.model.Member
import kotlinx.android.synthetic.main.activity_member.*

class MemberActivity : AppCompatActivity(),OnInput {
    val listData = arrayListOf<Member>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)

        val fab: View = findViewById(R.id.fab_input)
        fab.setOnClickListener { view ->
            val add = InputFragment(this)
            val bundle = Bundle().apply {
                putInt("edit",0)
            }
            add.arguments = bundle
            add.show(this.supportFragmentManager,"sukses")
        }
        getData()

    }

    override fun onProgress() {
        listData.clear()
        getData()
    }

    override fun onEdit(id: Int) {

    }

    fun getData(){
        val database = KlinikDB.getDatabase(this.applicationContext)
        val dao = database.getMemberDao()
        listData.addAll(dao.getData())
        setupRecyclerView(listData)
        if (listData.isNotEmpty()){
            stat!!.visibility = View.GONE
            Toast.makeText(this,"data ada", Toast.LENGTH_SHORT).show()
        }
        else{
            stat!!.visibility = View.VISIBLE
            Toast.makeText(this,"data kosong", Toast.LENGTH_SHORT).show()
        }

    }


    private fun setupRecyclerView(listItems: ArrayList<Member>){
        rv_member.apply {
            adapter = MemberAdapter(listItems, object : MemberAdapter.Listener{
                override fun OnItemClicked(member: Member) {

                }
            })

            layoutManager = LinearLayoutManager(this@MemberActivity)
        }
    }
}