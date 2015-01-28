/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdnc.testes;

/**
 *
 * @author Junior
 */
public class app {
    
    
    public static void sequenciatestes(Teste teste) {
        teste.salvar();
        teste.buscar();
        teste.atalizar();
        teste.buscar();
        teste.remover();
    }
    public static void main(String[] args) {
//        System.out.println("Sequencia de teste bdoo banco oracle");
//        sequenciatestes(new TesteBdoo());
        System.out.println("Sequencia de teste bdr banco oracle");
        sequenciatestes(new TesteBdor());
    }
}
