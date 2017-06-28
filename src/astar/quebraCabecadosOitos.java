/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author syane
 */
public class quebraCabecadosOitos  {
    
    int[][] estadoInicial = {{7,2,4},{5,0,6},{8,3,1}};
    int[][] estadoObjetivo = {{0,1,2},{3,4,5},{6,7,8}};
    List<Node> Caminho = new ArrayList<>();
    
    
    /**
     *
     * @param adjacentes
     * popula a lista com os proximos nós possiveis
     */
    public void verificaProximosEstado(List<Node> adjacentes, Node node){
        int lin = 0;
        int col = 0;
        int count = 1;
        //Encontra onde está o espaço vazio
        for (lin = 0; lin < 3; lin++){
            for(col =0; col < 3; col++){
                if(node.estado[lin][col] == 0){
                    System.out.println("Achei o 0 " + node.estado[lin][col]);
                    break;
                }
            }
            System.out.println(lin +" "+ col);
            if(col < 3 && node.estado[lin][col] == 0)
                break;
            /*if(node.estado[lin][col] == 0)
                break;*/
            System.out.println("Ainda to no for");
        }
        //Cria os proximos estados
        int pecaMove = 0;
        //utilizo o conceito que no melhores dos casos o jogador
        //vai poder fazer no maximo 4 movimentos
        //verifico para cada movimento o proximo estado do jogo obtido e 
       //é adcionado na lista de adjacentes
        while(count <= 4){
            int[][] possivelEstado = node.estado.clone();
            //Verifica se posição é valida
            try{
                if(count == 1){
                    //move peca a esquerda do espaco vazio
                    pecaMove = possivelEstado[lin][col - 1];
                    possivelEstado[lin][col -1] = 0;
                    possivelEstado[lin][col] = pecaMove;
                }
                if(count == 2){
                    //move peca a direita do espaco vazio
                    pecaMove = possivelEstado[lin][col +1];
                    possivelEstado[lin][col +1] = 0;
                    possivelEstado[lin][col] = pecaMove;
                }
                if(count == 3){
                    //move peca abaixo do espaco vazio
                    pecaMove = possivelEstado[lin -1][col];
                    possivelEstado[lin -1][col] = 0;
                    possivelEstado[lin][col] = pecaMove;
                }
                if(count == 4){
                    //move peca acima do espaco vazio
                    pecaMove = possivelEstado[lin +1][col];
                    possivelEstado[lin +1][col] = 0;
                    possivelEstado[lin][col] = pecaMove;
                }
                 Node adjacente = new Node();
                 adjacente.estado = possivelEstado.clone();
                 adjacente.pai = node;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                //Ignora se ocorrer alguma exception, devido a espaço inexistente
                continue;
            }finally{
                count++;
            }
        }
    }
    
    /**
     *
     * @param node
     * função para retornar as heuristicas para o node
     */
    public void CalcHeuristicas(Node node){

        node.h1 = HeuristicaSomadosPassos(node);
        node.h2 = HeuristicaTotaldePecaForadoLugar(node);
    }
    
    /**
     *
     * @param node
     * @return o valor do calculo da Heuristica para a Soma dos passos
     * Soma das distancias dos blocos as suas posições objetivos
     */
    public int HeuristicaSomadosPassos(Node node){
        return 5;
    }
    
    /**
     *
     * @param node
     * @return o valor do calculo da Heuristica Total de peças fora do lugar
     * O numero de blocos em posição errada
     */
    public int HeuristicaTotaldePecaForadoLugar(Node node){
        return 8;
    }

    
}
