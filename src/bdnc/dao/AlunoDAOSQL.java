/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdnc.dao;

import bdnc.beans.Aluno;
import bdnc.beans.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Junior
 */
public class AlunoDAOSQL implements DAO<Aluno> {

    @Override
    public void cadastrar(Aluno object) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConectonFactory.getOracleConnection();

//            pstmt = connection.prepareStatement("insert into alunos values(?)");
            pstmt = connection.prepareStatement("INSERT INTO ALUNOSQL VALUES(?,?,?)");
            pstmt.setInt(1, object.getMatricula());
            pstmt.setString(2, object.getNome());
            pstmt.setInt(3, object.getTurma().getId());
            pstmt.execute();

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
    }

    @Override
    public void atualizar(Aluno object) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConectonFactory.getOracleConnection();

            pstmt = connection.prepareStatement("UPDATE ALUNOSQL SET nome = ? , turma_id = ? where matricula = ?");
            pstmt.setString(1, object.getNome());
            pstmt.setInt(2, object.getTurma().getId());
            pstmt.setInt(3, object.getMatricula());
            pstmt.execute();

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
    }

    @Override
    public List<Aluno> listar() throws SQLException {
        Connection connection = null;
        Statement pstmt = null;
        List<Aluno> lista = new ArrayList<>();
        try {
            connection = ConectonFactory.getOracleConnection();

            pstmt = connection.createStatement();
            ResultSet result = pstmt.executeQuery("SELECT * FROM ALUNOSQL ORDER BY matricula");

            while (result.next()) {
                lista.add((Aluno) result.getObject(1));
            }

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
        return lista;
    }

    @Override
    public Aluno buscar(Object id) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Aluno aluno = new Aluno();
        DAO daoTurma = new TurmaDAOSQL();
        try {
            connection = ConectonFactory.getOracleConnection();

            pstmt = connection.prepareStatement("SELECT * FROM ALUNOSQL WHERE matricula = ?");
            pstmt.setInt(1, (int) id);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                aluno.setTurma((Turma) daoTurma.buscar(result.getObject("turma_id")));
                aluno.setNome(result.getString("nome"));
                aluno.setMatricula(result.getInt("matricula"));
            }

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
        return aluno;
    }

    @Override
    public void remover(Aluno object) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConectonFactory.getOracleConnection();

            pstmt = connection.prepareStatement("DELETE ALUNOSQL WHERE matricula = ?");
            pstmt.setInt(1, object.getMatricula());
            pstmt.execute();

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
    }
}
