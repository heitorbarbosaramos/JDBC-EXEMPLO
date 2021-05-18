import DAO.AlunoDAO;
import configuracao.BD;
import entidades.Aluno;

import java.sql.SQLException;
import java.util.List;

public class Principal {

    public static void main(String[] args){

        AlunoDAO alunoDAO = new AlunoDAO();

        List<Aluno> alunos = alunoDAO.todosAlunos();

        alunos.stream().forEach(System.out::println);
    }
}
