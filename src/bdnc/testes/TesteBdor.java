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

    private final DAO<Aluno> daoAlunos = new AlunoDAOSQL();
    private final DAO<Turma> daoTurma = new TurmaDAOSQL();
    private final int fimTurma = 10;
    private final int fimAlunos = 10;
    private long tempInicio = 0;
    private final int ini = 1;

    private void mensagen(long tempofimal, String msg) {
        System.out.println("================================================================");
        System.out.println("            " + msg);
        System.out.println("            Tempo decorrido: " + (tempofimal - tempInicio) / 1000f + "s.");
        System.out.println("            Tempo decorrido: " + (tempofimal - tempInicio) + "ms.");
        System.out.println("================================================================\n");
    }

    @Override
    public void salvar() {
        tempInicio = System.currentTimeMillis();
        int aux = 0;
        for (int n = ini; n <= fimTurma; n++) {
            Turma turma = new Turma(n, "" + (2014.1 + n / 10), "turma " + n);
            try {
                daoTurma.cadastrar(turma);

                for (int m = ini; m <= fimAlunos; m++) {
                    daoAlunos.cadastrar(new Aluno(m + aux, "aluno " + m + aux, turma));
                }
            } catch (SQLException ex) {
                Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
            }
            aux += fimAlunos;
        }
        this.mensagen(System.currentTimeMillis(), "Alunos Cadastrados com sucesso!!");
    }

    @Override
    public void atalizar() {

        tempInicio = System.currentTimeMillis();
        int aux = 0;
        for (int n = ini; n <= fimTurma; n++) {
            Turma turma = new Turma(n, "" + (2014.1 + n / 10), "turma nova " + n);
            try {
                daoTurma.atualizar(turma);
                for (int m = ini; m <= fimTurma; m++) {
                    daoAlunos.atualizar(new Aluno(m+aux, "aluno novo " + m+aux, turma));
                }
            } catch (SQLException ex) {
                Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
            }
            aux += fimAlunos;
        }
        this.mensagen(System.currentTimeMillis(), "Alunos Atualizados com sucesso!!");
    }

    @Override
    public void buscar() {
        tempInicio = System.currentTimeMillis();
        for (int n = ini; n <= fimTurma; n++) {
            try {
                daoAlunos.buscar(n);
            } catch (SQLException ex) {
                Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.mensagen(System.currentTimeMillis(), "Alunos listados com sucesso!!");
    }

    @Override
    public void remover() {
        tempInicio = System.currentTimeMillis();
        try {
            for (int n = ini; n <= fimAlunos*fimTurma; n++) {
                daoAlunos.removerById(n);
            }
            for (int m = ini; m <= fimTurma; m++) {
                daoTurma.removerById(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TesteBdoo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.mensagen(System.currentTimeMillis(), "Alunos removidos com sucesso!!");
    }
}
