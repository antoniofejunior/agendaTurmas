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
import java.util.Map;

/**
 *
 * @author Junior
 */
public class AlunoDAO implements DAO<Aluno> {

    @Override
    public void cadastrar(Aluno object) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConectonFactory.getOracleConnection();

//            pstmt = connection.prepareStatement("insert into alunos values(?)");
            pstmt = connection.prepareStatement("INSERT INTO ALUNOS VALUES (?)");
            pstmt.setObject(1, object);
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

//            pstmt = connection.prepareStatement("update from alunos where matricula = ?");
            pstmt = connection.prepareStatement("UPDATE ALUNOS a SET VALUE(a) = ? WHERE a.matricula = ?");
            pstmt.setObject(1, object);
            pstmt.setInt(2, object.getMatricula());
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

            // atualizandou mapas
            Map mapas = connection.getTypeMap();
            mapas.put("ALUNO", Aluno.class);
            mapas.put("TURMA", Turma.class);
//            connection.setTypeMap(mapas);

            pstmt = connection.createStatement();
            ResultSet result = pstmt.executeQuery("SELECT VALUE(a) FROM ALUNOS a ORDER BY a.matricula");

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
        Aluno aluno = null;
        try {
            connection = ConectonFactory.getOracleConnection();

            // atualizandou mapas
            Map mapas = connection.getTypeMap();
            mapas.put("ALUNO", Aluno.class);
            mapas.put("TURMA", Turma.class);
            connection.setTypeMap(mapas);

            pstmt = connection.prepareStatement("select value(a) from Alunos a where a.matricula = ?");
            pstmt.setInt(1, (int) id);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                aluno = ((Aluno) result.getObject(1));
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

            pstmt = connection.prepareStatement("DELETE ALUNOS a WHERE a.matricula = ?");
            pstmt.setInt(1, object.getMatricula());
            pstmt.execute();

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
    }
    
    
    @Override
    public void removerById(Object id) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConectonFactory.getOracleConnection();

            pstmt = connection.prepareStatement("DELETE ALUNOS a WHERE a.matricula = ?");
            pstmt.setInt(1,(int) id);
            pstmt.execute();

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
    }
}