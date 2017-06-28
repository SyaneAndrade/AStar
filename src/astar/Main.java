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
public class Main {
    public static void main(String[] args) {
    // TODO code application logic here

        AStar astar = new AStar();

        quebraCabecadosOitos quebrac = new quebraCabecadosOitos();

        astar.raiz.estado = quebrac.estadoInicial.clone();
        
        /*if(raiz.estado != null)
            System.out.println(raiz.estado[1][2]);*/

        astar.processado.estado = astar.raiz.estado.clone();

        boolean h1 = true;

        //Adciona a raiz a lista de nos abertos
        astar.abertos.add(astar.raiz);
        //Enquanto ter elementos na lista
        while(0 != astar.abertos.size()){
            //remove o elemento a ser o proximo processado
            astar.abertos.remove(astar.processado);
            //adciona ao de elementos fechados, pois esta sendo atendido
            astar.fechados.add(astar.processado);
            //Verifica se o nó possui o estado objetivo
            if(astar.estadoFinal(astar.processado)){
                //Calcula o caminho do estado o objetivo utilizando os pais
                quebrac.Caminho = astar.CaminhoCompleto(astar.processado);
                break;
            }
            //Calcula todos os nós adjacentes ao nó em processamento
            List<Node> adjacentes = astar.processado.calculaAdjacentes(quebrac);
            //Atualiza todos os valores do Node(F, G, H)
            //A variavel H1 decide qual heuristica irá usar para o calculo da F
            astar.AtualizaAdjacentes(adjacentes, quebrac);
            if(h1){
                for(Node node_adj: adjacentes){
                    node_adj.f = astar.calcF(node_adj.g, node_adj.h1);
                }
            }
            else{
                for(Node node_adj: adjacentes){
                    node_adj.f = astar.calcF(node_adj.g, node_adj.h2);
                } 
            }
            //Para todo no na lista de adjacentes
            for(Node node_adj: adjacentes){
                //Se o nó já foi expandido ele ignora
                if(astar.jaExpandido(node_adj))
                    continue;
                //Se não verifica se ele ainda está na lista de abertos
                //se a lista de abertos conter o nó, ele verifica se o custo de chegar até esse nó
                //é mais barato do que o que esta na lista
                //se for ele vai atualizar o nó existente da lista de aberto
                //se não o nó será desconsiderado
                if(astar.inAbertos(node_adj)){

                }
                //Adciona o novo nó a lista de abertos
                else{
                    astar.abertos.add(node_adj);
                }
            }
            
            //Pegando o proximo
            astar.ProximoNoAProcessar();
        }
    }
}
