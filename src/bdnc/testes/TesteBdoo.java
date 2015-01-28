/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdnc.testes;

import bdnc.beans.Aluno;
import bdnc.beans.Turma;
import bdnc.dao.AlunoDAO;
import bdnc.dao.DAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class TesteBdoo implements Teste {

    private DAO<Aluno> dao = new AlunoDAO();

    @Override
    public void salvar() {
        int n = 1;
        try {
            dao.cadastrar(new Aluno(n, "aluno " + n, new Turma(n, "" + (2014.1 + n / 10), "turma " + n)));
            System.out.println("Aluno Cadastrado com sucesso!!");
        } catch (SQLException ex) {
            Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void atalizar() {
        int n = 1;
        try {
            dao.atualizar(new Aluno(n, "aluno novo " + n, new Turma(n, "" + (2014.1 + n / 10), "turma nova" + n)));
            System.out.println("Aluno atualzado com sucesso!!");
        } catch (SQLException ex) {
            Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void buscar() {
        try {
//            System.out.println(dao.listar());
            System.out.println(dao.buscar(1));
        } catch (SQLException ex) {
            Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remover() {
        try {
            dao.remover(dao.buscar(1));
            System.out.println("Aluno removido com sucesso!!");
        } catch (SQLException ex) {
            Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
