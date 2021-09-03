package br.com.snakegame;


import java.util.ArrayList;

//controlando a logica do jogo
public class ThreadsController extends Thread {

    ArrayList<ArrayList<Dados>> quadrados = new ArrayList<ArrayList<Dados>>();
    Tupla cabecaDaCobra;
    int tamanhoCobra = 3;
    long velocidade = 50;
    public static int direcaoDaCobra;

    ArrayList<Tupla> posicoes = new ArrayList<Tupla>();
    Tupla posicaoComida;

    //Construtor 
    ThreadsController(Tupla posicaoInicial ){
        //pegar as threads
        quadrados = Janela.Grid;

        cabecaDaCobra = new Tupla(posicaoInicial.x, posicaoInicial.y);
        direcaoDaCobra = 1;

        //ponteiro
        Tupla posicaoCabecaDaCobra = new Tupla(cabecaDaCobra.getX(), cabecaDaCobra.getY());
        posicoes.add(posicaoCabecaDaCobra);

        posicaoComida = new Tupla(Janela.altura - 1, Janela.largura - 1);
        gerarComida(posicaoComida);

    }

    @Override
    public void run() {
        while (true) {
            moverInternamente(direcaoDaCobra);
            checarColisao();
            moverExternamente();
            deletarCauda();
            pausar();
        }
    }

    //delay entre os movimentos da cobra
    private void pausar() {
        try {
            sleep(velocidade);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //checar se a cobra colidiu com ela mesma ou comeu a 
    private void checarColisao() {
        Tupla posicaoCritica = posicoes.get(posicoes.size() - 1);
        for (int i = 0; i <= posicoes.size() - 2; i++) {
            boolean autoColisao = posicaoCritica.getX() == posicoes.get(i).getX() && posicaoCritica.getY() == posicoes.get(i).getY();
            if (autoColisao) {
                pararJogo();
            }
        }

        boolean comerComida = posicaoCritica.getX() == posicaoComida.y && posicaoCritica.getY() == posicaoComida.x;
        if (comerComida) {
            System.out.println("Yummy!");
            tamanhoCobra = tamanhoCobra + 1;
            posicaoComida = retornarEspacoVazio();

            gerarComida(posicaoComida);
        }
    }

    //parar o jogo
    private void pararJogo() {
        System.out.println("COLISION! \n");
        while (true) {
            pausar();
        }
    }

    //colocar comida na posição e mostrar 
    private void gerarComida(Tupla foodPositionIn) {
        quadrados.get(foodPositionIn.x).get(foodPositionIn.y).mudar(1);
    }

    //retornar uma posição vazia em que a cobra não está
    private Tupla retornarEspacoVazio() {
        Tupla posicao;
        int ranX = 0 + (int) (Math.random() * 19);
        int ranY = 0 + (int) (Math.random() * 19);
        posicao = new Tupla(ranX, ranY);
        for (int i = 0; i <= posicoes.size() - 1; i++) {
            if (posicao.getY() == posicoes.get(i).getX() && posicao.getX() == posicoes.get(i).getY()) {
                ranX = 0 + (int) (Math.random() * 19);
                ranY = 0 + (int) (Math.random() * 19);
                posicao = new Tupla(ranX, ranY);
                i = 0;
            }
        }
        return posicao;
    }

    //move a cabeça da cobra e atualiza as posições no arraylist
    private void moverInternamente(int diracao) {
        switch (diracao) {
            case 4:
                cabecaDaCobra.alterarDados(cabecaDaCobra.x, (cabecaDaCobra.y + 1) % 20);
                posicoes.add(new Tupla(cabecaDaCobra.x, cabecaDaCobra.y));
                break;
            case 3:
                if (cabecaDaCobra.y - 1 < 0) {
                    cabecaDaCobra.alterarDados(cabecaDaCobra.x, 19);
                } else {
                    cabecaDaCobra.alterarDados(cabecaDaCobra.x, Math.abs(cabecaDaCobra.y - 1) % 20);
                }
                posicoes.add(new Tupla(cabecaDaCobra.x, cabecaDaCobra.y));
                break;
            case 2:
                if (cabecaDaCobra.x - 1 < 0) {
                    cabecaDaCobra.alterarDados(19, cabecaDaCobra.y);
                } else {
                    cabecaDaCobra.alterarDados(Math.abs(cabecaDaCobra.x - 1) % 20, cabecaDaCobra.y);
                }
                posicoes.add(new Tupla(cabecaDaCobra.x, cabecaDaCobra.y));

                break;
            case 1:
                cabecaDaCobra.alterarDados(Math.abs(cabecaDaCobra.x + 1) % 20, cabecaDaCobra.y);
                posicoes.add(new Tupla(cabecaDaCobra.x, cabecaDaCobra.y));
                break;
        }
    }

    private void moverExternamente() {
        posicoes.forEach((t) -> {
            int y = t.getX();
            int x = t.getY();
            quadrados.get(x).get(y).mudar(0);
        });
    }

    //atualizar a cauda da cobra, movendo os dados superflos nas posições do arraylist
    //e atualizar o display onde os objetos são removidos
    private void deletarCauda() {
        int cmpt = tamanhoCobra;
        for (int i = posicoes.size() - 1; i >= 0; i--) {
            if (cmpt == 0) {
                Tupla t = posicoes.get(i);
                quadrados.get(t.y).get(t.x).mudar(2);
            } else {
                cmpt--;
            }
        }
        cmpt = tamanhoCobra;
        for (int i = posicoes.size() - 1; i >= 0; i--) {
            if (cmpt == 0) {
                posicoes.remove(i);
            } else {
                cmpt--;
            }
        }
    }
}
