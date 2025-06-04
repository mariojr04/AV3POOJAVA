import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hospede extends Pessoa{

    private String rg;
    private Boolean fidelidade;
    List<Hospede> hospedes = new ArrayList<>();
    private static final String FILE_PATH = "hospedes.txt";

    public Hospede(String cpf, String nome, int idade, String rg, Boolean fidelidade){
        super(cpf, nome, idade);
        this.rg = rg;
        this.fidelidade = fidelidade;
    }

    public void setRg(String rg){
        this.rg = rg;   
    }

    public String getRg(){
        return rg;
    }

    public void setRg(Boolean fidelidade){
        this.fidelidade = fidelidade;
    }

    public Boolean getFidelidade(){
        return fidelidade;
    }

    @Override
    public void ToString(){
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Rg: " + getRg());
        System.out.println("Tem fidelidade: " + getFidelidade());
    }


    @Override
    public Boolean Inserir() {
        try {
            for (Hospede h : hospedes) {
                if (h.getNome().equalsIgnoreCase(this.getNome())) {
                    System.out.println("hospede já cadastrado: " + this.getNome());
                    return false;
                }
            }
            hospedes.add(this);
            return true;
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean Editar() {
        Scanner s = new Scanner(System.in);

        System.out.print("Novo nome do hospede: ");
        String novoNome = s.nextLine();
        this.setNome(novoNome);

        System.out.print("Nova idade do hospede : ");
        int novaIdade = Integer.parseInt(s.nextLine());
        this.setIdade(novaIdade);

        System.out.print("Novo rg do hospede: ");
        String novoRg = s.nextLine();
        this.setRg(novoRg);

        return true;
    }

    public List<Hospede> Listar() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5) {
                    String cpf = partes[0];
                    String nome = partes[1];
                    int idade = Integer.parseInt(partes[2]);
                    String rg = partes[3];
                    Boolean fidelidade = Boolean.parseBoolean(partes[4]);
                    hospedes.add(new Hospede(cpf, nome, idade, rg, fidelidade ));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler produtos: " + e.getMessage());
        }
        return hospedes;
    }

    public Hospede Consultar(int id) {
        if (id >= 0 && id < hospedes.size()) {
            return hospedes.get(id);
        } else {
            return null; 
        }
    }
}
