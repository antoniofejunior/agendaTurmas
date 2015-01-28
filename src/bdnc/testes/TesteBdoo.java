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

    private final DAO<Aluno> dao = new AlunoDAO();
    private final int fim = 20;
    private long tempInicio = 0;
    private final int ini = 1;

    private void mensagen(long tempofimal, String msg) {
        System.out.println("================================================================");
        System.out.println("            "+msg);
        System.out.println("            Tempo decorrido: " + (tempofimal - tempInicio) / 1000f + "s.");
        System.out.println("            Tempo decorrido: " + (tempofimal - tempInicio) + "ms.");
        System.out.println("================================================================\n");

    }

    @Override
    public void salvar() {
        tempInicio = System.currentTimeMillis();
        for (int n = ini; n <= fim; n++) {
        
            try {
                dao.cadastrar(new Aluno(n, "aluno " + n, new Turma(n, "" + (2014.1 + n / 10000), "turma " + n)));
            } catch (SQLException ex) {
                Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.mensagen(System.currentTimeMillis(), "Alunos Cadastrados com sucesso!!");
    }

    @Override
    public void atalizar() {
        tempInicio = System.currentTimeMillis();
        for (int n = ini; n <= fim; n++) {
            try {
                dao.atualizar(new Aluno(n, "aluno novo " + n, new Turma(n, "" + (2014.1 + n / 10), "turma nova" + n)));
            } catch (SQLException ex) {
                Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.mensagen(System.currentTimeMillis(), "Alunos atualzados com sucesso!!");

    }

    @Override
    public void buscar() {
        tempInicio = System.currentTimeMillis();
        for (int n = ini; n <= fim; n++) {
            try {
                dao.buscar(n);
            } catch (SQLException ex) {
                Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.mensagen(System.currentTimeMillis(), "Alunos listados com sucesso!!");

    }

    @Override
    public void remover() {
        tempInicio = System.currentTimeMillis();
        for (int n = ini; n <= fim; n++) {
            try {
                dao.removerById(n);
            } catch (SQLException ex) {
                Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.mensagen(System.currentTimeMillis(), "Alunos removidos com sucesso!!");
    }

}
