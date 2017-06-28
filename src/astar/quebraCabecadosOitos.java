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
    
    //int[][] estadoInicial = {{1,2,3},{8,6,4},{0,7,5}};
    int[][] estadoInicial = {{7,2,4},{5,0,6},{8,3,1}};
    //int[][] estadoObjetivo = {{1,2,3},{8,0,4},{7,6,5}};
    int[][] estadoObjetivo = {{1,2,3},{4,5,6},{7,8,0}};
    List<Node> Caminho = new ArrayList<>();
    
    public void copyArray(int[][] recCopy, int[][] sendCopy){
        for(int lin=0; lin < 3; lin++){
            for(int col=0; col < 3; col++){
                recCopy[lin][col] = sendCopy[lin][col];
            }
        }
    }
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
                    break;
                }
            }
            if(col < 3 && node.estado[lin][col] == 0)
                break;
        }
        //Cria os proximos estados
        int pecaMove = 0;
        //utilizo o conceito que no melhores dos casos o jogador
        //vai poder fazer no maximo 4 movimentos
        //verifico para cada movimento o proximo estado do jogo obtido e 
       //é adcionado na lista de adjacentes
        while(count <= 4){
            int[][] possivelEstado = new int[3][3];
            copyArray(possivelEstado, node.estado);
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
                 adjacente.pai = (Node)node.Clone();
                 //adjacente.printEstado();
                 /*System.out.println("Pai");
                 adjacente.pai.printEstado();*/
                 System.out.println();
                 adjacentes.add(adjacente);   
            }
            catch(Exception e){
                //System.out.println(e.getMessage());
                //Ignora se ocorrer alguma exception, devido a espaço inexistente
                continue;
            }finally{
                count++;
            }
        }
    }

    public int calcula_dist(int x1,int y1,int x2,int y2){
        return (Math.abs(x1-x2) + Math.abs(y1-y2) );
    }
    
    public int[] getXY(int elem){
        int[] coordenadas = new int[2];
        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                if(this.estadoObjetivo[x][y] == elem){
                    coordenadas[0] = x;
                    coordenadas[1] = y;
                    return coordenadas;
                }
            }
        }
        coordenadas[0]=999;
        return coordenadas;
    } 
    
    /**
     *
     * @param node
     * função para retornar as heuristicas para o node
     */
    public void CalcHeuristicas(Node node){
        int h1 = 0, h2 = 0,count = 0;
        int[] distancias = new int[9];
        int[] coordenadas = new int[2];
        
        for(int x=0;x<3;x++ ){
            for(int y=0;y<3;y++){
                if(node.estado[x][y] != count){                    
                    h1 = h1 + 1;
                    coordenadas = this.getXY(node.estado[x][y]);
                    h2 = h2 + this.calcula_dist(x, y, coordenadas[0], coordenadas[1]);
                }
                count = count + 1;
            }
        }
        node.h1 = h1;                      
        node.h2 = h2;
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
        int heuristica = 0;
        if(node.estado[0][0] != 0)
          heuristica += 1;
        if(node.estado[0][1] != 1)
          heuristica += 1;
        if(node.estado[0][2] != 2)
          heuristica += 1;
        if(node.estado[1][0] != 3)
          heuristica += 1;
        if(node.estado[1][1] != 4)
          heuristica += 1;
        if(node.estado[1][2] != 5)
          heuristica += 1;
        if(node.estado[2][0] != 6)
          heuristica += 1;
        if(node.estado[2][1] != 7)
          heuristica += 1;
        if(node.estado[2][2] != 8)
          heuristica += 1;

        return heuristica;
      }
    }
