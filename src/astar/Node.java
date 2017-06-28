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
public class Node {
    int f;
    int g;
    int h1;
    int h2;
    Node pai;
    int[][] estado;
    
    public List<Node> calculaAdjacentes(quebraCabecadosOitos quebrac){
        List<Node> adjacentes = new ArrayList<>();
        quebrac.verificaProximosEstado(adjacentes, this);
        return adjacentes;
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
    
}
