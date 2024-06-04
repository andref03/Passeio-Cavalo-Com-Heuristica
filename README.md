# O que é o "Passeio do Cavalo"?

Um problema interessante para os fãs de xadrez é o problema do ```Passeio do Cavalo```, originalmente proposto pelo matemático Euler. 
A peça do cavalo pode mover-se em um tabuleiro vazio e tocar cada um dos 64 quadrados somente uma única vez?

## Com heurística?

Quanto à ```heurística```, temos por isso o famoso senso comum. Com a presença dessa heurística, o "cavalo" se preocupa com a dificuldade de acessar determinadas posições.
A heurística não garante sucesso, mas ela, cuidadosamente desenvolvida, aprimora significativamente a chance de sucesso. 
Os quadrados externos são mais incômodos que os quadrados próximos do centro do tabuleiro. De fato, os quadrados mais problemáticos ou inacessíveis são os quatro cantos.

A intuição pode sugerir que você deva tentar mover o cavalo para os quadrados mais problemáticos primeiro e deixar abertos aqueles que são fáceis de alcançar, de modo que, quando o tabuleiro ficar congestionado próximo do fim do passeio, haja maior chance de sucesso.

### Explorando a aplicação da heurística

Em um tabuleiro vazio, cada um dos 16 quadrados mais próximos do centro é avaliado como 8, o quadrado de cada canto é avaliado como 2 e os outros quadrados têm números de acessibilidade de 3, 4 ou 6 como segue:

![image](https://github.com/andref03/Passeio-Cavalo-Com-Heuristica/assets/140921456/e54c1d2b-9495-44ed-b50d-87aa111e8077)


# Requisitos

O cavalo só faz movimentos em forma de L (dois espaços em uma direção e um outro em uma direção perpendicular).
Portanto, conforme na imagem abaixo, partindo de um quadrado próximo do centro de um tabuleiro de xadrez vazio, o cavalo (rotulado K) pode fazer oito movimentos diferentes (numerados de 0 a 7).
O cavalo sempre deve se mover para o quadrado com o número de acessibilidade mais baixo.
Em caso de um impasse, o cavalo pode mover-se para qualquer quadrado ainda não visitado.
Portanto, o passeio pode iniciar em qualquer um dos quatro cantos. 

## Observação

À medida que o cavalo se move pelo tabuleiro de xadrez, o aplicativo deve reduzir os números de acessibilidade, uma vez que mais quadrados tornam-se ocupados.
Dessa maneira, em qualquer dado tempo durante o passeio, o número de acessibilidade de cada quadrado disponível permanecerá precisamente igual ao número de quadrados a partir dos quais esse quadrado pode ser alcançado.

## Os Movimentos
![image](https://github.com/andref03/Passeio-Cavalo-Sem-Heuristica/assets/140921456/f4f499bd-abca-4a25-903e-5c37a9b12f15)

# Descrição

O tabuleiro é representado por um array bidimensional oito por oito. Cada quadrado é inicializado conforme a sua acessibilidade. 
Cada um dos oito possíveis movimentos são descritos em termos de seus componentes vertical e horizontal. 
Por exemplo, um movimento do tipo 0, como mostrado na figura acima, consiste em mover dois quadrados horizontalmente para a direita e um quadrado verticalmente para cima.
Um movimento do tipo 2 consiste em mover um quadrado horizontalmente para a esquerda e dois quadrados verticalmente para cima.
Movimentos horizontais para a esquerda e movimentos verticais para cima são indicados com números negativos. 
Os oito movimentos podem ser descritos por dois arrays unidimensionais, horizontal e vertical, como segue:

```horizontal[0] =  2  vertical[0] = -1```

```horizontal[1] =  1  vertical[1] = -2```

```horizontal[2] = -1  vertical[2] = -2```

```horizontal[3] = -2  vertical[3] = -1```

```horizontal[4] = -2  vertical[4] =  1```

```horizontal[5] = -1  vertical[5] =  2```

```horizontal[6] =  1  vertical[6] =  2```

```horizontal[7] =  2  vertical[7] =  1```

# Objetivo 

O objetivo é a escrita de um aplicativo para mover o cavalo pelo tabuleiro, mantendo um contador que varia de 1 a 64.
Cada movimento potencial é testado, para ver se o cavalo já visitou esse quadrado e, também, para assegurar que o cavalo realize o melhor movimento em termos de acessibilidade.

# Sobre

Este projeto consiste na execução de uma atividade da disciplina "Programação Orientada a Objetos".
