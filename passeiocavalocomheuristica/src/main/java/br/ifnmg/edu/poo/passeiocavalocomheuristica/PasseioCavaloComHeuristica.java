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
        int[] acessibilidade = {2, 3, 4, 6, 8};

        boolean quadradoValido = true; // booleano p/ impedir laço while infinito

        int contador = 1;

        tabuleiro[linhaAtual][colunaAtual] = 1;

        while (contador <= 64 && quadradoValido) {
            int proxLinha;
            int proxColuna;
            quadradoValido = false;

            for (int moveNumber = 0; moveNumber < 8; moveNumber++) {
                proxLinha = linhaAtual + vertical[moveNumber];
                proxColuna = colunaAtual + horizontal[moveNumber];

                if (proxLinha >= 0 && proxLinha < 8 && proxColuna >= 0 && proxColuna < 8 && tabuleiro[proxLinha][proxColuna] == 0) {
                    
                    // passa por todos os pesos, priorizando os menores (+ inacessíveis)
                    for (int acesso = 0; acesso < 5; acesso++) {
                        
                    }
                    
                    linhaAtual = proxLinha;
                    colunaAtual = proxColuna;
                    contador++;
                    tabuleiro[linhaAtual][colunaAtual] = contador;
                    quadradoValido = true;
                    break; // volta pro while(), p/ avançar a próx. posição
                }
            }
        }

        return contador;
    }

    private static void imprimeTabuleiro(int[][] tabuleiro) {
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

    }

}