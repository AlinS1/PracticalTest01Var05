package ro.pub.cs.systems.eim.practicaltest01var05;

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class PracticalTest01Var05SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.practical_test01_var05_secondary)

        val textSablon = findViewById<TextView>(R.id.secondaryTextView)
        val input1 = intent.getStringExtra(Constants.INPUT1)
        textSablon.text = input1

        val okButton = findViewById<Button>(R.id.verifyButton)
        okButton.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        val cancelButton = findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

}