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

    @Override
    public void ToString(){
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Função: " + getFuncao());
    }


    @Override
    public Boolean Inserir() {
        try (FileWriter fw = new FileWriter("funcionarios.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toString() + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
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

     public ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("funcionarios.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(", ");
                if (partes.length == 4) {
                    String cpf = partes[0].split(": ")[1];
                    String nome = partes[1].split(": ")[1];
                    int idade = Integer.parseInt(partes[2].split(": ")[1]);
                    String funcao = partes[3].split(": ")[1];
                    lista.add(new Funcionario(cpf, nome, idade, funcao));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    List<Funcionario> funcionarios = new ArrayList<>();
    public Funcionario Consultar(int id) {
        if (id >= 0 && id < funcionarios.size()) {
            return funcionarios.get(id);
        } else {
            return null; 
        }
    }
}
