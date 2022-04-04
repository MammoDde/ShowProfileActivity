package it.polito.showprofileactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.icon -> {
                editProfile()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun editProfile(){
        val i = Intent(this,EditProfileActivity::class.java)
        var tv1: TextView = findViewById(R.id.textView)
        var tv2: TextView = findViewById(R.id.textView2)
        var tv3: TextView = findViewById(R.id.textView3)
        var tv4: TextView = findViewById(R.id.textView4)
        var tv5: TextView = findViewById(R.id.textView12)
        var tv6: TextView = findViewById(R.id.textView14)
        var tv7: TextView = findViewById(R.id.textView13)
        var tv8: TextView = findViewById(R.id.textView15)
        i.putExtra("Name",tv1.text)
        i.putExtra("Nickname",tv2.text)
        i.putExtra("email",tv3.text)
        i.putExtra("location",tv4.text)
        i.putExtra("skill1",tv5.text)
        i.putExtra("skill2",tv6.text)
        i.putExtra("description1",tv7.text)
        i.putExtra("description2",tv8.text)
        //launcher.launch(i)
        startActivityForResult(i,123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var tv1: TextView = findViewById(R.id.textView)
        var tv2: TextView = findViewById(R.id.textView2)
        var tv3: TextView = findViewById(R.id.textView3)
        var tv4: TextView = findViewById(R.id.textView4)
        var tv5: TextView = findViewById(R.id.textView12)
        var tv6: TextView = findViewById(R.id.textView14)
        var tv7: TextView = findViewById(R.id.textView13)
        var tv8: TextView = findViewById(R.id.textView15)

        if (data != null) {
            var str1: String? = data.getStringExtra("Name")
            var str2: String? = data.getStringExtra("Nickname")
            var str3: String? = data.getStringExtra("email")
            var str4: String? = data.getStringExtra("location")
            var str5: String? = data.getStringExtra("skill1")
            var str6: String? = data.getStringExtra("skill2")
            var str7: String? = data.getStringExtra("description1")
            var str8: String? = data.getStringExtra("description2")
            tv1.setText(str1)
            tv2.setText(str2)
            tv3.setText(str3)
            tv4.setText(str4)
            tv5.setText(str5)
            tv6.setText(str6)
            tv7.setText(str7)
            tv8.setText(str8)
        } else {
            tv1.setText("error")
            tv2.setText("error")
            tv3.setText("error")
            tv4.setText("error")
            tv5.setText("error")
            tv6.setText("error")
            tv7.setText("error")
            tv8.setText("error")


        }
        super.onActivityResult(requestCode, resultCode, data)

    }

}

