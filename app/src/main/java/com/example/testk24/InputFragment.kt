package com.example.testk24

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import com.example.testk24.database.KlinikDB
import com.example.testk24.model.Member
import com.google.android.material.textfield.TextInputEditText
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [InputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputFragment(private val onInput: OnInput) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = inflater.inflate(R.layout.fragment_input, container, false)

        val nama = view.findViewById<TextInputEditText>(R.id.tifed_nama)
        val alamat = view.findViewById<TextInputEditText>(R.id.tifed_alamat)
        val jenisKelamin = view.findViewById<TextInputEditText>(R.id.tifed_jk)
        val username = view.findViewById<TextInputEditText>(R.id.tifed_username)
        val password = view.findViewById<TextInputEditText>(R.id.tifed_pass)

        val mPickTimeBtn = view.findViewById<Button>(R.id.pickDateBtn)
        val textView     = view.findViewById<TextView>(R.id.dateTv)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        mPickTimeBtn.setOnClickListener {

            val dpd = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                textView.setText("" + dayOfMonth + " - " + month + ", " + year)
            }, year, month, day)
            dpd.show()

        }
        val id = arguments!!.getInt("edit")
        if (id != 0){
            val db = KlinikDB.getDatabase(context!!)
            val dao = db.getMemberDao()
            val akun =  dao.getDataById(id)
            nama.setText( akun.get(0).nama.toString())
            alamat.setText( akun.get(0).alamat.toString())
            textView.setText( akun.get(0).tgllahir.toString())
            jenisKelamin.setText( akun.get(0).jenisKelamin.toString())
            username.setText( akun.get(0).username.toString())
            password.setText( akun.get(0).password.toString())
        }

        val butSelesai: Button = view!!.findViewById(R.id.but_input)
        butSelesai.setOnClickListener {
            val db = KlinikDB.getDatabase(context!!)
            val dao = db.getMemberDao()
            if (id == 0){
                dao.insert(Member(nama = nama.text.toString(),tgllahir = textView.text.toString(),jenisKelamin = jenisKelamin.text.toString(),alamat = alamat.text.toString(),username = username.text.toString(),password = password.text.toString()))
            }else{
                dao.update(Member(memberID = id, nama = nama.text.toString(),tgllahir = textView.text.toString(),jenisKelamin = jenisKelamin.text.toString(),alamat = alamat.text.toString(),username = username.text.toString(),password = password.text.toString()))
                onInput.onEdit(id)
            }
            onInput.onProgress()
            dismiss()
        }

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
        val window: Window = dialog?.window!!
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        window.setGravity(Gravity.CENTER)
    }

    override fun onResume() {
        super.onResume()
        val window: Window = dialog?.window!!
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        window.setGravity(Gravity.CENTER)
    }

}