/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_hmw;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class TicTacToe_HMW extends JPanel {

    char playerMark = 'X';
    
    
    
    JButton[] buttons = new JButton[9];
    
    

    public TicTacToe_HMW() {
        setLayout(new GridLayout(3, 3));
        initializeButtons();
    }

    private void initializeButtons() {

        for (int i = 0; i <= 8; i++) {
            buttons[i] = new JButton();
            buttons[i].setText("-");
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setFont(new Font("TimesRoman", Font.PLAIN, 30));
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton) e.getSource();
                    buttonClicked.setText(String.valueOf(playerMark));
                    if (playerMark == 'X') {
                        playerMark = 'O';
                        buttonClicked.setBackground(Color.yellow);
                    } else {
                        playerMark = 'X';
                        buttonClicked.setBackground(Color.cyan);
                    }
                    displayWinner();

                }
            });
            add(buttons[i]);

        }

    }

    //display the winner
    public void displayWinner() {
        if (checkWinner() == true) {
            if (playerMark == 'X') {
                playerMark = 'O';
            } else {
                playerMark = 'X';
            }

            JOptionPane pane = new JOptionPane();
            int result = JOptionPane.showConfirmDialog(pane,   playerMark
                    + " wins. Would you like to play again?", "Game Over.", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_NO_OPTION) {
                resetTheButtons();
            } else {
                System.exit(0);
            }

        } else if (checkDraw()) {
            JOptionPane drawPane = new JOptionPane();
            int result = JOptionPane.showConfirmDialog(drawPane, "Draw.Wanna Play Again?", "Game over.", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                resetTheButtons();
            } else {
                System.exit(0);
            }

        }
    }

    //buttons reset
    public void resetTheButtons() {
        for (int i = 0; i < 9; i++) {
            playerMark = 'X';
            buttons[i].setText("-");
            buttons[i].setBackground(Color.white);
        }
    }

    //Draw Check
    private boolean checkDraw() {
        boolean full = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().charAt(0) == '-') {
                full = false;
            }
        }
        return full;
    }

    //check for winner
    public boolean checkWinner() {
        if (checkRow() == true || checkColum() == true || checkDiagonal() == true) {
            return true;
        } else {
            return false;
        }

    }

    //check for Row
    public boolean checkRow() {
        int i = 0;
        for (int j = 0; j < 3; j++) {
            if (buttons[i].getText().equals(buttons[i + 1].getText())
                    && buttons[i].getText().equals(buttons[i + 2].getText())
                    && buttons[i].getText().charAt(0) != '-') {
                return true;
            }
            i = i + 3;
        }
        return false;

    }

    //check for colum
    public boolean checkColum() {
        int i = 0;
        for (int j = 0; j < 3; j++) {
            if (buttons[i].getText().equals(buttons[i + 3].getText())
                    && buttons[i].getText().equals(buttons[i + 6].getText())
                    && buttons[i].getText().charAt(0) != '-') {
                return true;
            }

            i++;
        }

        return false;
    }

    //check for diagonal
    public boolean checkDiagonal() {
        int i = 0;

        if (buttons[0].getText().equals(buttons[4].getText())
                && buttons[0].getText().equals(buttons[8].getText())
                && buttons[0].getText().charAt(0) != '-') {
            return true;
        } else if (buttons[2].getText().equals(buttons[4].getText())
                && buttons[2].getText().equals(buttons[6].getText())
                && buttons[2].getText().charAt(0) != '-') {
            return true;
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        JFrame f = new JFrame("TicTacToe_HMW");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new TicTacToe_HMW());
        f.setBounds(600, 600, 600, 600);
        f.setVisible(true);//show the game
        f.setLocationRelativeTo(null);//center
    }
}
