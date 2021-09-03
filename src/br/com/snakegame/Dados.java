package br.com.snakegame;


import java.util.ArrayList;
import java.awt.Color;

public class Dados {

    //ArrayList que contem as cores
    ArrayList<Color> C = new ArrayList<Color>();
    int cores; //2: cobra , 1: comida, 0:vazio 
    Painel quadrado;

    public Dados(int cor) {

        //adicionando cores ao arraylist
        C.add(Color.darkGray);//0
        C.add(Color.BLUE);    //1
        C.add(Color.WHITE);   //2
        cores = cor;
        quadrado = new Painel(C.get(cores));
    }

    public void mudar(int cor) {
        quadrado.mudarCor(C.get(cor));
    }
}
