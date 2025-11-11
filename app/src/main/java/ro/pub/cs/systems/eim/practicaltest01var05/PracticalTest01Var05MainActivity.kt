package ro.pub.cs.systems.eim.practicaltest01var05

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ro.pub.cs.systems.eim.practicaltest01var05.Constants.INPUT1

class PracticalTest01Var05MainActivity : AppCompatActivity() {
    private var resultText: TextView? = null
    private var nrClicks: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.practical_test01_var05_main)

        resultText = findViewById<TextView>(R.id.textView)

        val listener = ButtonClickListener()
        findViewById<View>(R.id.topLeftButton).setOnClickListener(listener)
        findViewById<View>(R.id.topRightButton).setOnClickListener(listener)
        findViewById<View>(R.id.centerButton).setOnClickListener(listener)
        findViewById<View>(R.id.bottomLeftButton).setOnClickListener(listener)
        findViewById<View>(R.id.bottomRightButton).setOnClickListener(listener)

        // Navigation
        val activityResultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "The activity returned with result OK", Toast.LENGTH_LONG).show()
            }
            else if (result.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "The activity returned with result CANCELED", Toast.LENGTH_LONG).show()
            }
            nrClicks = 0
            findViewById<TextView>(R.id.textView).text = ""
        }

        val navigateToSecondaryActivityButton = findViewById<Button>(R.id.navigateSecond)
        navigateToSecondaryActivityButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var05SecondaryActivity::class.java)
            intent.putExtra(INPUT1, findViewById<TextView>(R.id.textView).text.toString())
            activityResultsLauncher.launch(intent)
        }


        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.NR_CLICKS)) {
                nrClicks = savedInstanceState.getInt(Constants.NR_CLICKS)
            }
            Toast.makeText(this, "Nr clicks: " + nrClicks.toString(), Toast.LENGTH_LONG).show()
        }
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        if (nrClicks > 0) {
            savedInstanceState.putInt(
                Constants.NR_CLICKS,
                nrClicks
            )
        }
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey(Constants.NR_CLICKS)) {
            nrClicks = savedInstanceState.getInt(Constants.NR_CLICKS)
        }
    }

    // --- Clasa Internă pentru Tratarea Evenimentelor Click ---
    private inner class ButtonClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            when (view.id) {
                // Butoanele de Cifre/Simboluri (* / #)
                R.id.topLeftButton, R.id.topRightButton, R.id.centerButton, R.id.bottomLeftButton, R.id.bottomRightButton-> {
                    // Adaugă simbolul corespunzător la numărul de telefon
                    val button = view as Button
                    var add = button.text.toString() + ", "
                    resultText?.append(add)
                    nrClicks++
                }
            }
        }
    }
}