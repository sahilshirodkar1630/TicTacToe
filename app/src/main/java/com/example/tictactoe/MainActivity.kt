package com.example.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var PLAYER = true;
    var TURN_COUNT = 0;
    var boardStatus = Array(3){IntArray(3)};

    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        initializeButtonStatus()

        resetBtn.setOnClickListener(){
            TURN_COUNT = 0;
            PLAYER = true;
            initializeButtonStatus()
        }

    }

    private fun initializeButtonStatus() {
        for( i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1;
                board[i][j].isEnabled = true;
                board[i][j].text = "";
            }
        }
    }

    override fun onClick(view: View) {

        when(view.id){

            R.id.button1 -> {
                updtaeValue(row = 0, col = 0 , player = PLAYER)
            }
            R.id.button2 -> {
                updtaeValue(row = 0, col = 1 , player = PLAYER)
            }
            R.id.button3 -> {
                updtaeValue(row = 0, col = 2 , player = PLAYER)
            }
            R.id.button4 -> {
                updtaeValue(row = 1, col = 0 , player = PLAYER)
            }
            R.id.button5 -> {
                updtaeValue(row = 1, col = 1 , player = PLAYER)
            }
            R.id.button6 -> {
                updtaeValue(row = 1, col = 2 , player = PLAYER)
            }
            R.id.button7 -> {
                updtaeValue(row = 2, col = 0 , player = PLAYER)
            }
            R.id.button8 -> {
                updtaeValue(row = 2, col = 1 , player = PLAYER)
            }
            R.id.button9 -> {
                updtaeValue(row = 2, col = 2 , player = PLAYER)
            }
        }
        TURN_COUNT++;
        PLAYER = !PLAYER;
        if(PLAYER){
            updateDisplay("Player X Turn")
        }else{
            updateDisplay("Player O Turn")
        }
        if(TURN_COUNT == 9){
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {
        // Horizontal rows
        for(i in 0..2){
           if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
               if(boardStatus[i][0] == 1){
                   updateDisplay("Congrats! Player X Won");
                   break;
               }else if(boardStatus[i][0] == 0){
                   updateDisplay("Congrats! Player O Won");
                   break;
               }
           }
        }

        //Vertical Columns
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if(boardStatus[0][i] == 1){
                    updateDisplay("Congrats! Player X Won");
                    break;
                }else if(boardStatus[0][i] == 0){
                    updateDisplay("Congrats! Player O Won");
                    break;
                }
            }
        }
        //First Diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if(boardStatus[0][0] == 1){
                updateDisplay("Congrats! Player X Won");
            }else if(boardStatus[0][0] == 0){
                updateDisplay("Congrats! Player O Won");
            }
        }

        //Second Diagonal
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
            if(boardStatus[0][2] == 1){
                updateDisplay("Congrats! Player X Won");
            }else if(boardStatus[0][2] == 0){
                updateDisplay("Congrats! Player O Won");
            }
        }

    }

    private fun updateDisplay(text: String) {
        displayTv.text = text
        if(text.contains("Won")){
            disableButton();
        }

    }

    private fun disableButton() {
        for(i in board){
            for(button in i){
                button.isEnabled = false;
            }
        }
    }

    private fun updtaeValue(row: Int, col: Int, player: Boolean) {

        var text  = if (player) "X" else "O";
        var value  = if (player) 1 else 0;

        board[row][col].apply {
            isEnabled = false;
            setText(text);
        }

        boardStatus[row][col] = value;

    }
}