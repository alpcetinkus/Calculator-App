package com.example.mycalculatorapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    var lastNumber = false
    var stateError = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {

        if (stateError) {

            resultText.text = (view as TextView).text
            stateError = false
        } else {

            resultText.append((view as TextView).text)
        }
        lastNumber = true
    }

    fun decimalPoint(view: View) {

        if (lastNumber && !stateError && !lastDot) {
            resultText.append(".")
            lastNumber = false
            lastDot = true
        }
    }

    fun operatorActions(view: View) {

        if (lastNumber && !stateError) {
            resultText.append((view as TextView).text)
            lastNumber = false
            lastDot = false
        }
    }

    fun allClear(view: View) {

        resultText.text = ""
        lastNumber = false
        stateError = false
        lastDot = false
    }
    fun deleteActions(view: View) {

        val length = resultText.length()
        if (length > 0) {
            resultText.text = resultText.text.subSequence(0, length - 1)
        }
    }


    fun equalAction(view: View) {
        if (lastNumber && !stateError) {

            val txt = resultText.text.toString()
            val expression = ExpressionBuilder(txt).build()
            try {

                val result = expression.evaluate()
                resultText.text = result.toString()
                lastDot = true

            } catch (ex: ArithmeticException) {

                resultText.text = "Error"
                stateError = true
                lastNumber = false
            }
        }
    }

    fun lebAct(view: View) {
        resultText.text = "LEBLEBI :)"
    }
}

