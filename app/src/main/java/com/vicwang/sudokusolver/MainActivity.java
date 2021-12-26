package com.vicwang.sudokusolver;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SudokuBoard gameBoard;
    private Solver gameBoardSolver;
    private Button solveBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameBoard = findViewById(R.id.SudokuBoard);
        gameBoardSolver = gameBoard.getSolver();

        Button num1BTN = findViewById(R.id.button1);
        Button num2BTN = findViewById(R.id.button2);
        Button num3BTN = findViewById(R.id.button3);
        Button num4BTN = findViewById(R.id.button4);
        Button num5BTN = findViewById(R.id.button5);
        Button num6BTN = findViewById(R.id.button6);
        Button num7BTN = findViewById(R.id.button7);
        Button num8BTN = findViewById(R.id.button8);
        Button num9BTN = findViewById(R.id.button9);
        solveBTN = findViewById(R.id.solveButton);

        num1BTN.setOnClickListener(this);
        num2BTN.setOnClickListener(this);
        num3BTN.setOnClickListener(this);
        num4BTN.setOnClickListener(this);
        num5BTN.setOnClickListener(this);
        num6BTN.setOnClickListener(this);
        num7BTN.setOnClickListener(this);
        num8BTN.setOnClickListener(this);
        num9BTN.setOnClickListener(this);
        solveBTN.setOnClickListener(this);
    }

    public void solve() {
        if (solveBTN.getText().toString().equals(getString(R.string.Solve))) {
            solveBTN.setText(R.string.Clear);

            gameBoardSolver.getEmptyBoxIndices();
            SolveBoardThread solveBoardThread = new SolveBoardThread();
            new Thread(solveBoardThread).start();
        } else {
            solveBTN.setText(R.string.Solve);
            gameBoardSolver.resetBoard();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button1) {
            gameBoardSolver.setNumberPos(1);
        } else if (id == R.id.button2) {
            gameBoardSolver.setNumberPos(2);
        } else if (id == R.id.button3) {
            gameBoardSolver.setNumberPos(3);
        } else if (id == R.id.button4) {
            gameBoardSolver.setNumberPos(4);
        } else if (id == R.id.button5) {
            gameBoardSolver.setNumberPos(5);
        } else if (id == R.id.button6) {
            gameBoardSolver.setNumberPos(6);
        } else if (id == R.id.button7) {
            gameBoardSolver.setNumberPos(7);
        } else if (id == R.id.button8) {
            gameBoardSolver.setNumberPos(8);
        } else if (id == R.id.button9) {
            gameBoardSolver.setNumberPos(9);
        } else if (id == R.id.solveButton) {
            solve();
        }
        gameBoard.invalidate();
    }

    class SolveBoardThread implements Runnable {

        @Override
        public void run() {
            gameBoardSolver.solve(gameBoard);
        }
    }
}