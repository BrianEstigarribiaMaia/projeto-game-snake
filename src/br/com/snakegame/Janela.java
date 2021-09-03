package br.com.snakegame;


import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

class Janela extends JFrame {

    private static final long serialVersionUID = -2542001418764869760L;
    public static ArrayList<ArrayList<Dados>> Grid;
    public static int largura = 20;
    public static int altura = 20;

    public Janela() {

        // Criar arraylist que contem as threads
        Grid = new ArrayList<ArrayList<Dados>>();
        ArrayList<Dados> data;

        // Criar as threads e os dados e adicionar no arraylist
        for (int i = 0; i < largura; i++) {
            data = new ArrayList<Dados>();
            for (int j = 0; j < altura; j++) {
                Dados c = new Dados(2);
                data.add(c);
            }
            Grid.add(data);
        }

        // Setar o layout do painel
        getContentPane().setLayout(new GridLayout(20, 20, 0, 0));

        // Iniciar e pausar todas as threads, depois adicionar todos os quadriculados em suas threads
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                getContentPane().add(Grid.get(i).get(j).quadrado);
            }
        }

        // posicao inicial da cobra
        Tupla posicao = new Tupla(10, 10);
        // passando o valor para o controler
        ThreadsController c = new ThreadsController(posicao);
        //inicia o game
        c.start();

        // linkando a janela ao keyboardlistenner.
        this.addKeyListener((KeyListener) new Teclado());

    }
}
