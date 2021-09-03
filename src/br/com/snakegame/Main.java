package br.com.snakegame;


import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        //criando a jenela 
        Janela f1 = new Janela();

        //setando os elementos
        f1.setTitle("Snake");
        f1.setSize(300, 300);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
