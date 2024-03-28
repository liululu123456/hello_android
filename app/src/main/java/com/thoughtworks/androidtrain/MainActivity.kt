package com.thoughtworks.androidtrain

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest;

class MainActivity : AppCompatActivity() {

    private val REQUEST_SELECT_CONTACT = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        addButtons()
    }

    private fun addButtons(){
        val linearlayout = findViewById<LinearLayout>(R.id.main)
        for (i in 1..20) {
            val button = Button(this)

            val buttonStringId = resources.getIdentifier("button$i", "string", packageName)
            val buttonBackgroundColor = ContextCompat.getColor(this,R.color.gray)
            button.text = resources.getString(buttonStringId)
            button.setBackgroundColor(buttonBackgroundColor)

            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.topMargin = 16
            button.layoutParams = layoutParams
            linearlayout.addView(button)

            if (i == 1){
                button.setOnClickListener {
                    val intent = Intent(this, ConstraintActivity::class.java)
                    startActivity(intent)
                }
            }
            if (i == 2){
                button.setOnClickListener {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }

            if (i == 3){
                button.setOnClickListener {
                    requestContactsPermission()
                }
            }

        }

    }
    private fun requestContactsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf<String>(Manifest.permission.READ_CONTACTS),
                REQUEST_SELECT_CONTACT
            )
        } else {
            selectContact()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_SELECT_CONTACT) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectContact()
            } else {
            }
        }
    }

    private fun selectContact() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = ContactsContract.Contacts.CONTENT_TYPE
        }
        startActivityForResult(intent, REQUEST_SELECT_CONTACT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val contactUri: Uri? = data?.data
            if (contactUri != null) {
                val cursor: Cursor? = contentResolver.query(contactUri, null, null, null, null)
                cursor?.use {
                    if (it.moveToFirst()) {
                        val nameIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                        val name = it.getString(nameIndex)

                        val cursor1: Cursor? = contentResolver.query(
                            ContactsContract.Data.CONTENT_URI,
                            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)  ,
                            null,
                            null,
                            null
                        )

                        cursor1?.use {
                            while (cursor1.moveToFirst()) {
                                val index = cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                val phone = cursor1.getString(index)
                                val message = "$name         $phone"
                                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                                println(message)
                            }}
                    }
                }
            }
        }
    }
}