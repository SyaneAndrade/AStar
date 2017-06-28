/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;
import java.util.Arrays;
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
            if(node.estado != fechado.estado) {
            } 
            else {
                return true;
            }
        }
        return false;
    }
    
    public void ProximoNoAProcessar(){
        this.processado.estado = this.abertos.get(0).estado.clone();
        this.processado.f = this.abertos.get(0).f;
        this.processado.g = this.abertos.get(0).g;
        this.processado.h1 = this.abertos.get(0).h1;
        this.processado.h2 = this.abertos.get(0).h2;
        for(Node aberto: this.abertos){
            if(this.processado.f > aberto.f){
                this.processado.estado = aberto.estado.clone();
                this.processado.f = aberto.f;
                this.processado.g = aberto.g;
                this.processado.h1 = aberto.h1;
                this.processado.h2 = aberto.h2;
            }
        }
    }
    
    /**
     *
     * @param node
     * @return true para no encontrado, false caso contrario
     * se o nó for encontrado e o no_adjacente tiver um custo de caminho menor,
     * o nó existente na lista será atualizado, se não ele mantém os valores originais
     * e descarta o no_adj
     */
    public boolean inAbertos(Node node){
        for(Node aberto: this.abertos){
            if(node.estado != aberto.estado) {
            } 
            else {
                if(aberto.g > node.g){
                    aberto.g = node.g;
                    aberto.f = node.f;
                    aberto.h1 = node.h1;
                    aberto.h2 = node.h2;
                    aberto.pai = node.pai;
                    return true;
                }
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
    
    public List<Node> CaminhoCompleto(Node finalObjetivo){
        List<Node> caminhoCompleto = new ArrayList<>();
        caminhoCompleto.add(finalObjetivo);
        Node pai = finalObjetivo.pai;
        while(pai.pai != null){
            caminhoCompleto.add(pai);
            pai = pai.pai;
        }
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
            
        }
    }
    
    
    /**
     *
     * @param node
     * @return retorna verdadeiro se nó é estado objetivo, caso contrario
     * retorna falso
     */
    public boolean estadoFinal(Node node){
        System.out.println(node.estado[1][1]);
        
        return Arrays.equals(node.estado, this.estadoObjetivo);
          
    } 
    
}
