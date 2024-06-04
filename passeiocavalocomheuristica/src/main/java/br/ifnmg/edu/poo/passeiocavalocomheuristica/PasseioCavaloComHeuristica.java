package br.ifnmg.edu.poo.passeiocavalocomheuristica;

/**
 * Esta classe contém os métodos necessários para realizar o Passeio do Cavalo
 * que é realizado com a heurística (senso comum). Contém o método estático
 * main.
 *
 * @author André (@andref03)
 */
public class PasseioCavaloComHeuristica {

    /**
     * Inicializa o tabuleiro com a acessibilidade especificada em cada posição.
     *
     * @return tabuleiro
     */
    private static int[][] inicializaTabuleiro() {
        int[][] tabuleiro = {
            {2, 3, 4, 4, 4, 4, 3, 2},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {4, 6, 8, 8, 8, 8, 6, 4},
            {3, 4, 6, 6, 6, 6, 4, 3},
            {2, 3, 4, 4, 4, 4, 3, 2}
        };
        return tabuleiro;
    }

    /**
     * Este método realiza o Passeio do Cavalo, movimentando o "cavalo" entre as
     * posições especificadas por pares de linha e coluna. É varificado qual é o
     * melhor movimento. É verificado se a posição é válida p/ o movimento. A
     * cada movimento, o contador (movimento: int) é incrementado.
     *
     * @param linhaAtual: int
     * @param colunaAtual: int
     * @param tabuleiro: int[][]
     * @return movimento, que é a quantidade de movimentos.
     */
    private static int realizaPasseio(int linhaAtual, int colunaAtual, int[][] tabuleiro) {

        int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};

        boolean quadradoValido = true; // booleano p/ impedir laço while infinito

        int movimento = 0;

        tabuleiro[linhaAtual][colunaAtual] = 0;

        while (movimento <= 64 && quadradoValido) {
            int proxLinha;
            int proxColuna;
            int melhorMovimento = descobreMelhorMovimento(tabuleiro, linhaAtual, colunaAtual);

            quadradoValido = false;

            for (int moveNumber = 0; moveNumber < 8; moveNumber++) {
                proxLinha = linhaAtual + vertical[moveNumber];
                proxColuna = colunaAtual + horizontal[moveNumber];

                if (proxLinha >= 0 && proxLinha < 8 && proxColuna >= 0 && proxColuna < 8 && tabuleiro[proxLinha][proxColuna] != 0) {

                    if (tabuleiro[proxLinha][proxColuna] == melhorMovimento) {
                        linhaAtual = proxLinha;
                        colunaAtual = proxColuna;
                        movimento++;
                        tabuleiro[linhaAtual][colunaAtual] = 0;
                        quadradoValido = true;
                        atualizaAcessibilidade(linhaAtual, colunaAtual, tabuleiro);
                        break; // volta pro while(), p/ avançar a próx. posição
                    }

                }
            }
        }

        return movimento;
    }

    /**
     * Este método é responsável por descobrir qual é o melhor movimento
     * possível entre as 8 possibilidades de movimentar o cavalo pelo tabuleiro.
     *
     * @param tabuleiro: int[][]
     * @param linhaAtual: int
     * @param colunaAtual: int
     * @return menorNumero, que armazena o melhor movimento possível p/ aquela
     * posição.
     */
    private static int descobreMelhorMovimento(int[][] tabuleiro, int linhaAtual, int colunaAtual) {

        int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
        int proxLinha;
        int proxColuna;
        int menorNumero = 9; // pois 9 com certeza será maior que qlqr nº de acessibilidade desse tabuleiro

        for (int moveNumber = 0; moveNumber < 8; moveNumber++) {
            proxLinha = linhaAtual + vertical[moveNumber];
            proxColuna = colunaAtual + horizontal[moveNumber];

            if (proxLinha >= 0 && proxLinha < 8 && proxColuna >= 0 && proxColuna < 8 && tabuleiro[proxLinha][proxColuna] != 0) {
                if (tabuleiro[proxLinha][proxColuna] < menorNumero) {
                    menorNumero = tabuleiro[proxLinha][proxColuna]; // atualiza com o menor nº
                }
            }
        }

        return menorNumero;
    }

    /**
     * Este método tem o objetivo de atualizar a acessibilidade de cada posição,
     * após cada movimento. Ou seja, atualiza (diminuindo) a quantidade de
     * posições que poderiam ser alcançadas a partir da posição atual.
     *
     * @param linhaAtual
     * @param colunaAtual
     * @param tabuleiro
     */
    private static void atualizaAcessibilidade(int linhaAtual, int colunaAtual, int[][] tabuleiro) {

        int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
        int proxLinha;
        int proxColuna;

        for (int moveNumber = 0; moveNumber < 8; moveNumber++) {
            proxLinha = linhaAtual + vertical[moveNumber];
            proxColuna = colunaAtual + horizontal[moveNumber];

            if (proxLinha >= 0 && proxLinha < 8 && proxColuna >= 0 && proxColuna < 8 && tabuleiro[proxLinha][proxColuna] != 0) {
                linhaAtual = proxLinha;
                colunaAtual = proxColuna;
                tabuleiro[linhaAtual][colunaAtual]--;
                break; // retorna pro laço for() p/ testar um novo movimento
            }
        }
    }

    /**
     * Este método mostra a situação do tabuleiro a cada chamada deste método.
     *
     * @param tabuleiro : int[][]
     */
    private static void imprimeTabuleiro(int[][] tabuleiro) {
        System.out.println("> Tabuleiro: ");
        System.out.println();
        for (int[] linha : tabuleiro) {
            for (int elemento : linha) {
                System.out.printf("(%2d) ", elemento);
            }
            System.out.println();
        }
    }

    /**
     * Método main, que organiza a ordem de execução do programa.
     *
     * @param args : String[]
     */
    public static void main(String[] args) {

        int[][] tabuleiro = inicializaTabuleiro();

        int qtddMovimentos = realizaPasseio(0, 0, tabuleiro); // começa na posição tabuleiro[0][0]

        imprimeTabuleiro(tabuleiro);

        System.out.println();
        System.out.println(">> O cavalo realizou " + qtddMovimentos + " movimentos.");
        System.out.println();
        System.out.println("*Obs.:\t. casas visitadas = {0}");
        System.out.println("\t. acessibilidade final = {1, 2, 3, 4, 5, 6, 7, 8}");

    }

}
