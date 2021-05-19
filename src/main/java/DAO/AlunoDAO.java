package DAO;

import configuracao.BD;
import entidades.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AlunoDAO {

    final Logger LOG = Logger.getLogger("AlunoDAO");

    public List<Aluno> todosAlunos(){

        List<Aluno> alunos = new ArrayList<>();

        try {

            Connection conn = BD.conexao();

            PreparedStatement estatement =  conn.prepareStatement("SELECT * FROM aluno");
            ResultSet rs = estatement.executeQuery();

            while (rs.next()){
                alunos.add(new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("estado") ));
            }

        } catch (SQLException e) {
            LOG.info("ERRO AO RECUPERAR CONSULTA " + e.getMessage());
        }
        return alunos;
    }

    public Aluno getById(Integer id){

        Connection conn = BD.conexao();
        Aluno aluno = new Aluno();
        try {

            PreparedStatement estatement =  conn.prepareStatement("SELECT * FROM aluno WHERE id = ?");
            estatement.setInt(1, id);
            ResultSet rs = estatement.executeQuery();

            if(rs.next()){
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));
            }

        } catch (SQLException e) {
            LOG.info("ERRO AO RECUPERAR ALUNO POR ID  " + e.getMessage());
        }

        return aluno;
    }
}
