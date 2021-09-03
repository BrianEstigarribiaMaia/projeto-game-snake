package br.com.snakegame;


import java.awt.Color;
import javax.swing.JPanel;

public class Painel extends JPanel {

    private static final long serialVersionUID = 1L;

    public Painel(Color desenhar) {
        this.setBackground(desenhar);
    }

    public void mudarCor(Color desenhar) {
        this.setBackground(desenhar);
        this.repaint();
    }

}
