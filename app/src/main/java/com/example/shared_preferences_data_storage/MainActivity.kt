package com.example.shared_preferences_data_storage

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //Instance of textview -> will connect to saved username display
    private lateinit var name_textView: TextView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //connecting instance of edit text to take input
        editText = findViewById(R.id.editText)

        //connecting instance of text view to display saved username
        name_textView = findViewById(R.id.textViewName)

        //connecting instance of button
        val btn: Button = findViewById(R.id.btn)

        //handling clicks
        btn.setOnClickListener {

            //extracting the data from userInput(i.e. editText) nad making an instance in string type
            val enteredText: String = editText.text.toString()
            //passing the string to saving function (custom)
            saveNameInSharedPref(enteredText)

            Toast.makeText(this, "Saved username : ${enteredText}", Toast.LENGTH_SHORT).show()
        }

        //execute this custom function of displaying the username
        displaySavedName()
    }

    //name saving custom function (taking input a string )
    private fun saveNameInSharedPref(editText: String) {
        //making an instance of share preference    -> getSharedPreference(name:String , mode:Int)
        // the (name : String) should be same when invoking a new instance of sharedPreference while reading data , so that both the shared preference identify and act as one
        //its like putting a sticker on your flat door , so that when you return back you identify it.
        val sharedPreference: SharedPreferences = getSharedPreferences("xyz", MODE_PRIVATE)

        //writing data to shared preferences
        //this line makes and instance of editor for shared preferences , making shared preference editable
        val editor: SharedPreferences.Editor = sharedPreference.edit()

        // editor will add string to sharedPreferences (key,value) pair
        editor.putString("name", editText)
        editor.apply()

    }

    //Reading data from shared preferences
    // a custom function to do so , read and display
    fun displaySavedName() {
        //connecting sharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("xyz", MODE_PRIVATE)

        //this instance of a non-nullable string , which will store the data fetched by sharedPreference
        // initially it will match the key to nothing as nothing is in the storage
        //then it will match data saved with the key (name) and place it in the empty place in s1 while data reading

        val s1: String? = sharedPreferences.getString("name"," ")

        //set s1 value to textview to display
        name_textView.text = s1
    }
}



