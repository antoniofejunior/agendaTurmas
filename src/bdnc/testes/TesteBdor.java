/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdnc.testes;

import bdnc.beans.Aluno;
import bdnc.beans.Turma;
import bdnc.dao.AlunoDAOSQL;
import bdnc.dao.DAO;
import bdnc.dao.TurmaDAOSQL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class TesteBdor implements Teste {

   private DAO<Aluno> dao = new AlunoDAOSQL();
   private DAO<Turma> dao2 = new TurmaDAOSQL();

    @Override
    public void salvar() {
        int n = 1;
        Turma turma = new Turma(n, "" + (2014.1 + n / 10), "turma " + n);
        try {
            dao2.cadastrar(turma);            
            dao.cadastrar(new Aluno(n, "aluno " + n, turma));
            System.out.println("Aluno Cadastrado com sucesso!!");
        } catch (SQLException ex) {
            Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void atalizar() {
        int n = 1;
        Turma turma = new Turma(n, "" + (2014.1 + n / 10), "turma nova" + n);
        try {
            
            dao2.atualizar(turma);
            dao.atualizar(new Aluno(n, "aluno novo " + n, turma));
            System.out.println("Aluno atualzado com sucesso!!");
        } catch (SQLException ex) {
            Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void buscar() {
        try {
//            System.out.println(dao.listar());
            System.out.println(dao.buscar(1) + " " + dao2.buscar(1));
        } catch (SQLException ex) {
            Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remover() {
        try {
            dao.remover(dao.buscar(1));
            dao2.remover(dao2.buscar(1));
            System.out.println("Aluno removido com sucesso!!");
        } catch (SQLException ex) {
            Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
