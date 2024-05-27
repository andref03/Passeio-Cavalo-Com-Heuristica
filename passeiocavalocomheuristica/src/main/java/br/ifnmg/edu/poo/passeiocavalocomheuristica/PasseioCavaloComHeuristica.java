package br.ifnmg.edu.poo.passeiocavalocomheuristica;

/**
 *
 * @author André (@andref03)
 */
public class PasseioCavaloComHeuristica {

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

    private static void imprimeTabuleiro(int[][] tabuleiro) {
        System.out.println("> Tabuleiro: ");
        System.out.println();
        for (int[] linha : tabuleiro) {
            for (int elemento : linha) {
                System.out.printf("%2d ", elemento);
            }
            System.out.println();
        }
    }

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
