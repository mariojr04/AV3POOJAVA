import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funcionario extends Pessoa{

    private String funcao;
    List<Funcionario> funcionarios = new ArrayList<>();
    private static final String FILE_PATH = "funcionarios.txt";

    public Funcionario(String cpf, String nome, int idade, String funcao){
        super(cpf, nome, idade);
        this.funcao = funcao;
    }

    public void setFuncao(String funcao){
        this.funcao = funcao;
    }
    public String getFuncao(){
        return funcao;
    }

    public static void SalvarFuncionario(Funcionario funcionario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(funcionario.getCpf() + ";" + funcionario.getNome() + ";" + funcionario.getIdade() + ";" + funcionario.getFuncao() );
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
        }
    }
    @Override
    public void ToString(){
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Função: " + getFuncao());
    }


    @Override
    public Boolean Inserir() {
        try {
            for (Funcionario f : funcionarios) {
                if (f.getNome().equalsIgnoreCase(this.getNome())) {
                    System.out.println("Funcionário já cadastrado: " + this.getNome());
                    return false;
                }
            }
            funcionarios.add(this);
            return true;
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean Editar() {
        Scanner s = new Scanner(System.in);

        System.out.print("Novo Funcionario: ");
        String novoNome = s.nextLine();
        this.setNome(novoNome);

        System.out.print("Nova idade do funcionario: ");
        int novaIdade = Integer.parseInt(s.nextLine());
        this.setIdade(novaIdade);

        System.out.print("Nova função: ");
        String novaFuncao = s.nextLine();
        this.setFuncao(novaFuncao);
        s.close();
        return true;
    }

    public List<Funcionario> Listar() {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    String cpf = partes[0];
                    String nome = partes[1];
                    int idade = Integer.parseInt(partes[2]);
                    String funcao = partes[3];
                    funcionarios.add(new Funcionario(cpf, nome, idade, funcao ));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler produtos: " + e.getMessage());
        }
        return funcionarios;
    }

    public Funcionario Consultar(int id) {
        if (id >= 0 && id < funcionarios.size()) {
            return funcionarios.get(id);
        } else {
            return null; 
        }
    }
}