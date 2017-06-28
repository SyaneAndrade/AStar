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
public class AStar {
    
    Node raiz = new Node();
    Node processado = new Node();
    List<Node> abertos = new ArrayList<>();
    List<Node> fechados = new ArrayList<>();
    int[][] estadoObjetivo = {{0,1,2},{3,4,5},{6,7,8}};
    
    /**
     *
     * @param node
     * @return retorna o no da lista de fechados, se eles forem iguais.
     */
    public boolean jaExpandido(Node node){
        for(Node fechado: this.fechados){
            if(verifyMatriz(fechado.estado, node.estado)) {
                return true;
            } 
        }
        return false;
    }
    
    public void ProximoNoAProcessar(){
        this.processado=(Node) this.abertos.get(0).Clone();
        //int tam = this.abertos.size();
        for(Node aberto: this.abertos){
            if(this.processado.f >= aberto.f){
                this.processado = (Node)aberto.Clone();
            }
        }
    }
    
    public boolean verifyMatriz(int[][]adj, int[][] aberta){
        for(int lin=0; lin <3; lin++){
            for(int col=0; col <3; col++){
                if(adj[lin][col] == aberta[lin][col]){
                    
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     *
     * @param node
     * @return true para no encontrado, false caso contrario
     * se o nó for encontrado e o no_adjacente tiver um custo de caminho menor,
     * o nó existente na lista será atualizado, se não ele mantém os valores originais
     * e descarta o no_adj
     */
    public boolean inAbertos(Node node_adj){
        for(Node aberto: this.abertos){
            if(verifyMatriz(node_adj.estado,aberto.estado)) {
                if(aberto.g > node_adj.g){
                    aberto.g = node_adj.g;
                    aberto.f = node_adj.f;
                    aberto.h1 = node_adj.h1;
                    aberto.h2 = node_adj.h2;
                    aberto.pai = (Node)node_adj.pai.Clone();
                }
                return true;
            } 
            else {
                return false;
            }
        }
        return false;
    }
    
    
     /**
     * @param g
     * @return um inteiro que representa o valor do custo g
     * que representa o custo do caminho pecorrido até o estado atual
     */
    public int calcG(int g){
        return g + 1;
    }
    
    /**
     * @param custoG 
     * @param heuristica
     * @return um inteiro que representa o valor do custo f
     */
    public int calcF(int custoG, int heuristica){
        return heuristica + custoG;
    }
    
    public List<Node> CaminhoCompleto(Node finalObjetivo, List<Node> caminhoCompleto){
        Node pai = (Node)finalObjetivo.Clone();
        while(pai.pai != null){
            caminhoCompleto.add(pai);
            pai = (Node)pai.pai.Clone();
        }
        caminhoCompleto.add(raiz);
        return caminhoCompleto;
    }
    
    /**
     *
     * @param adjacentes
     * @param quebrac
     * Atualiza informações para heuristica e custo g do nodo
     */
    public void AtualizaAdjacentes(List<Node> adjacentes, quebraCabecadosOitos quebrac){
        for(Node node: adjacentes){
            node.calcHeuristicas(node, quebrac);
            node.g = calcG(this.processado.g);
            //System.out.print("\n\tAdjacente: h1 = "+node.h1+", h2 = "+node.h2+", g = "+node.g);
        }
    }
    
    
    /**
     *
     * @param node
     * @return retorna verdadeiro se nó é estado objetivo, caso contrario
     * retorna falso
     */
    public boolean estadoFinal(Node node){ 
        return verifyMatriz(node.estado, this.estadoObjetivo);
          
    } 
    
}
