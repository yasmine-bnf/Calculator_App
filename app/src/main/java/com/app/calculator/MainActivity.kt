package com.app.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.AC
import kotlinx.android.synthetic.main.activity_main.devide
import kotlinx.android.synthetic.main.activity_main.edit
import kotlinx.android.synthetic.main.activity_main.eight
import kotlinx.android.synthetic.main.activity_main.equale
import kotlinx.android.synthetic.main.activity_main.five
import kotlinx.android.synthetic.main.activity_main.four
import kotlinx.android.synthetic.main.activity_main.minus
import kotlinx.android.synthetic.main.activity_main.multiply
import kotlinx.android.synthetic.main.activity_main.nine
import kotlinx.android.synthetic.main.activity_main.one
import kotlinx.android.synthetic.main.activity_main.plus
import kotlinx.android.synthetic.main.activity_main.seven
import kotlinx.android.synthetic.main.activity_main.sign
import kotlinx.android.synthetic.main.activity_main.six
import kotlinx.android.synthetic.main.activity_main.three
import kotlinx.android.synthetic.main.activity_main.two
import kotlinx.android.synthetic.main.activity_main.virgule
import kotlinx.android.synthetic.main.activity_main.zero

class MainActivity : AppCompatActivity() {
    var CURRENT_OPERATION = ""
    var GLOBAL_RESULT = 0.0
    var SECOND_OPERAND = 0.0
    var OPERATION_CLICKED= false
    var TYPING = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButtons()


    }
    fun initButtons() {
        one.setOnClickListener {
            clickDigit("1")
        }
        two.setOnClickListener {
            clickDigit("2")
        }
        three.setOnClickListener {
            clickDigit("3")
        }
        four.setOnClickListener {
            clickDigit("4")
        }
        five.setOnClickListener {
            clickDigit("5")
        }
        six.setOnClickListener {
            clickDigit("6")
        }
        seven.setOnClickListener {
            clickDigit("7")
        }
        eight.setOnClickListener {
            clickDigit("8")
        }
        nine.setOnClickListener {
            clickDigit("9")
        }
        zero.setOnClickListener {
            clickDigit("0")
        }

        plus.setOnClickListener {
            operationClicked("+")

        }
        minus.setOnClickListener {
            operationClicked("-")
        }
        multiply.setOnClickListener {
            operationClicked("*")
        }
        devide.setOnClickListener {
            operationClicked("/")
        }
        equale.setOnClickListener {
            calcResult()
        }
        AC.setOnClickListener {
            reset()
        }
        sign.setOnClickListener {
            addSign()
        }
        virgule.setOnClickListener {
            addPoint()
        }
    }

    fun clickDigit(numberClicked: String) {
        if (!OPERATION_CLICKED && !TYPING) {
            edit.text = numberClicked // in case we did an operation then we clicked a digit
            GLOBAL_RESULT = 0.0
        }else if (edit.text.toString() == "0") {
            edit.text = numberClicked
        } else if (OPERATION_CLICKED) {
            OPERATION_CLICKED = false
            edit.text = numberClicked
        } else if (edit.text=="ERROR"){
            reset()
        } else {
            edit.text = "${edit.text}${numberClicked}"
        }
        TYPING = true

    }

    fun operationClicked(operation: String) {

        if(edit.text!="ERROR"){
            if(CURRENT_OPERATION==""){ // In case we didn't click any operation
                CURRENT_OPERATION = operation
                OPERATION_CLICKED = true
                if(GLOBAL_RESULT==0.0){
                    GLOBAL_RESULT=edit.text.toString().toDouble()
                }else{
                    calcResult()
                    CURRENT_OPERATION=""
                }
                TYPING=false
            }else{ // in case we clicked before an operation and we
                // clicked new one we have to calculate the previous
                // operation before going to the next op
                OPERATION_CLICKED = true
                if(GLOBAL_RESULT==0.0){
                    GLOBAL_RESULT=edit.text.toString().toDouble()
                }else{
                    calcResult()
                    CURRENT_OPERATION = operation
                }
                TYPING=false
            }

        }

    }

    fun calcResult() {

        if (CURRENT_OPERATION == "+") {
            SECOND_OPERAND = edit.text.toString().toDouble()
            if (TYPING) {
                GLOBAL_RESULT += SECOND_OPERAND
                edit.text = "${GLOBAL_RESULT}"
                TYPING = false
            }

        }else if (CURRENT_OPERATION== "-"){
            SECOND_OPERAND = edit.text.toString().toDouble()
            if (TYPING) {
                GLOBAL_RESULT -= SECOND_OPERAND
                edit.text = "${GLOBAL_RESULT}"
                TYPING = false
            }
        }else if (CURRENT_OPERATION=="/"){
            SECOND_OPERAND = edit.text.toString().toDouble()
            if (TYPING) {
                if(SECOND_OPERAND==0.0){
                    edit.text = "ERROR"
                    GLOBAL_RESULT=0.0
                }else{
                    GLOBAL_RESULT /= SECOND_OPERAND
                    edit.text = "${GLOBAL_RESULT}"
                    TYPING = false
                }

            }
        }else if (CURRENT_OPERATION=="*"){
            SECOND_OPERAND = edit.text.toString().toDouble()
            if (TYPING) {
                GLOBAL_RESULT *= SECOND_OPERAND
                edit.text = "${GLOBAL_RESULT}"
                TYPING = false
            }
        }



    }

    fun reset() {
        TYPING=false
        OPERATION_CLICKED=false
        GLOBAL_RESULT=0.0
        CURRENT_OPERATION=""
        edit.text="0"
    }

    fun removeDigit(){
        if(edit.text!="ERROR"){
            if(TYPING){
                if (edit.text.toString().length > 1) {
                    edit.text = edit.text.subSequence(0, edit.text.toString().length - 1)
                } else {
                    edit.text = "0"
                }
            }

        }

    }
    fun addSign(){
        if(edit.text!="ERROR"){
            if(edit.text.toString()!="0"){
                if(edit.text.toString()[0]=='-'){
                    edit.text=edit.text.toString().replace("-","")
                }else{
                    edit.text = "-${edit.text}"
                    if(GLOBAL_RESULT!=0.0){
                        GLOBAL_RESULT=-GLOBAL_RESULT
                    }

                }
            }
        }


    }
    fun addPoint(){
        if(edit.text!="ERROR"){
            if(!edit.text.toString().contains(".")){
                edit.text = "${edit.text}."
            }
        }


    }



        }












