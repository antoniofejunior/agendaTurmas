/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdnc.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Junior
 * @param <T>
 */
public interface DAO<T> {
    
    public void cadastrar(T object) throws SQLException;
    public void remover(T object)throws SQLException;
    public void removerById(Object id)throws SQLException;
    public void atualizar(T object)throws SQLException;
    public List<T> listar()throws SQLException;
    public T buscar(Object id)throws SQLException;
}

