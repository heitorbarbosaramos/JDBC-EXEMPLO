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

    public void insert(Aluno aluno){

        Connection conn = BD.conexao();

        try {

            PreparedStatement estatement = conn.prepareStatement("INSERT INTO  aluno (nome, idade, estado) values (?, ? ,?)");
            estatement.setString(1, aluno.getNome());
            estatement.setInt(2,aluno.getIdade());
            estatement.setString(3, aluno.getEstado());
            Integer linhasAfetadas = estatement.executeUpdate();

            if (linhasAfetadas > 0) {
                LOG.info("ALUNO INSERIDO COM SUCESSO!");
            } else {
                LOG.info("ERRO AO INSERIR NOVO ALUNO");
            }

        }catch (SQLException e){
            LOG.info("ERROR AO INSERIR NOVO ALUNO: " + e.getMessage());
        }
    }

    public void delete(Integer id){

        Connection conn = BD.conexao();

        try {

            PreparedStatement statement = conn.prepareStatement("DELETE FROM aluno where id = ?");
            statement.setInt(1, id);
            Integer linhas = statement.executeUpdate();

            if(linhas > 0){
                LOG.info("ALUNO EXCLUIDO");
            }else{
                LOG.info("ALUNO NAO FOI EXCLUIDO TENTE NOVAMENTE");
            }

        }catch (SQLException e){
            LOG.info("NAO POSSIVEL EXCLUIR O ALUNO DO ID: " + id + " : " + e.getMessage());
        }
    }

    public void atualizar(Aluno aluno){

        Connection conn = BD.conexao();

        try {


            PreparedStatement statement = conn.prepareStatement("UPDATE aluno set nome=?, idade=?, estado=? WHERE id=?");
            statement.setString(1, aluno.getNome());
            statement.setInt(2, aluno.getIdade());
            statement.setString(3, aluno.getEstado());
            statement.setInt(4, aluno.getId());
            Integer linhas = statement.executeUpdate();

            if(linhas > 0){
                LOG.info("ALUNO ATUALIZADO");
            }else{
                LOG.info("ERRO AO ATUALIZAR O ALUNO");
            }

        }catch (SQLException e){
            LOG.info("ERROR AO ATUALIZAR O ALUNO: " + e.getMessage());
        }
    }
}
