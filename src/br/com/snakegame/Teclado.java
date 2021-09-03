package br.com.snakegame;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Teclado extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 39:	// -> direita
                //se não esta na direçao oposta 
                if (ThreadsController.direcaoDaCobra != 2) {
                    ThreadsController.direcaoDaCobra = 1;
                }
                break;
            case 38:	// -> cima
                if (ThreadsController.direcaoDaCobra != 4) {
                    ThreadsController.direcaoDaCobra = 3;
                }
                break;

            case 37: 	// -> esquerda
                if (ThreadsController.direcaoDaCobra != 1) {
                    ThreadsController.direcaoDaCobra = 2;
                }
                break;

            case 40:	// -> baixo
                if (ThreadsController.direcaoDaCobra != 3) {
                    ThreadsController.direcaoDaCobra = 4;
                }
                break;

            default:
                break;
        }
    }
}
