/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdnc.dao;

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
public class TurmaDAOSQL implements DAO<Turma> {

    @Override
    public void cadastrar(Turma object) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConectonFactory.getOracleConnection();

            pstmt = connection.prepareStatement("INSERT INTO TURMASQL VALUES(?,?,?)");
            pstmt.setInt(1, object.getId());
            pstmt.setString(2, object.getNome());
            pstmt.setString(3, object.getPeriodo());
            pstmt.execute();

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
    }

    @Override
    public void atualizar(Turma object) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConectonFactory.getOracleConnection();

            pstmt = connection.prepareStatement("UPDATE turmasql SET nome = ? , periodo = ? where id = ?");
            pstmt.setString(1, object.getNome());
            pstmt.setString(2, object.getPeriodo());
            pstmt.setInt(3, object.getId());
            pstmt.execute();

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
    }

    @Override
    public List<Turma> listar() throws SQLException {
        Connection connection = null;
        Statement pstmt = null;
        List<Turma> lista = new ArrayList<>();
        try {
            connection = ConectonFactory.getOracleConnection();

          
            pstmt = connection.createStatement();
            ResultSet result = pstmt.executeQuery("SELECT * FROM ALUNOS ORDER BY matricula");

            while (result.next()) {
                lista.add((Turma) result.getObject(1));
            }

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
        return lista;
    }

    @Override
    public Turma buscar(Object id) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Turma turma = new Turma();
        try {
            connection = ConectonFactory.getOracleConnection();
            
            pstmt = connection.prepareStatement("SELECT * FROM TURMASQL WHERE id = ?");
            pstmt.setInt(1,Integer.parseInt(""+id));
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                turma.setNome(result.getString("nome"));
                turma.setId(result.getInt("id"));
                turma.setPeriodo(result.getString("periodo"));
            }

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
        return turma;
    }

    @Override
    public void remover(Turma object) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = ConectonFactory.getOracleConnection();

            pstmt = connection.prepareStatement("DELETE TURMASQL WHERE id = ?");
            pstmt.setInt(1, object.getId());
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

            pstmt = connection.prepareStatement("DELETE TURMASQL WHERE id = ?");
            pstmt.setInt(1, (int)id);
            pstmt.execute();

        } finally {
            ConectonFactory.close(pstmt);
            ConectonFactory.close(connection);
        }
    }
}
