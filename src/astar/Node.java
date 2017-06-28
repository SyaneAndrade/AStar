/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.List;

/**
 *
 * @author syane
 */
public class Node implements Cloneable {
    int f;
    int g;
    int h1;
    int h2;
    Node pai;
    int[][] estado;
    
    public Object Clone(){
        try{
            return (Node)super.clone();
        }
        catch(CloneNotSupportedException e){
            System.out.println("Cloning not allowed.");
            return this;
        }
    }
    
    public void calculaAdjacentes(quebraCabecadosOitos quebrac, List<Node> adjacentes){
        quebrac.verificaProximosEstado(adjacentes, this);
    }
    
    /**
     *
     * @param node
     * @param quebrac
     * a soma das heuristicas para um dado nó é calculado e adcionado em h1 e h2
     */
    public void calcHeuristicas(Node node, quebraCabecadosOitos quebrac){
        quebrac.CalcHeuristicas(node);
    }
    
    public void printEstado(){
        for(int lin= 0; lin < 3; lin++){
            for(int col = 0; col < 3; col++){
                System.out.print(this.estado[lin][col]+ " ");
            }
            System.out.println();
        }
    }
}
