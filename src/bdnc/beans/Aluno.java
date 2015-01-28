/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdnc.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 *
 * @author Junior
 */
public class Aluno implements SQLData {

    private int matricula;
    private String nome;
    private Turma turma;

    public Aluno() {
    }

    public Aluno(int matricula, String nome, Turma turma) {
        this.matricula = matricula;
        this.nome = nome;
        this.turma = turma;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the matricula
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * @return the turma
     */
    public Turma getTurma() {
        return turma;
    }

    @Override
    public String toString() {
        return "Aluno{" + "matricula=" + matricula + ", nome=" + nome + ", " + turma + '}';
    }

    /**
     * @param turma the turma to set
     */
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String getSQLTypeName() throws SQLException {
        return "ALUNO";
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        this.setMatricula(stream.readInt());
        this.setNome(stream.readString());
        this.turma = (Turma) stream.readObject();
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeInt(matricula);
        stream.writeString(nome);
        stream.writeObject(turma);
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

}
