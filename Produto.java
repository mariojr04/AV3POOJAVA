import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Produto {

    private int idProduto;
    private Double valor;
    private String descProduto;
    List<Produto> produtos = new ArrayList<>();
    private static final String FILE_PATH = "produtos.txt";

    public Produto(int idProduto, Double valor, String descProduto){
        this.idProduto = idProduto;
        this.valor = valor;
        this.descProduto = descProduto;
    }

    public void setIdProduto(int idProduto){
        this.idProduto = idProduto;
    }

    public int getIdProduto(){
        return idProduto;
    }

    public void setValor(Double valor){
        this.valor = valor;
    }

    public Double getValor(){
        return valor;
    }

    public void setDescProduto(String descProduto){
        this.descProduto = descProduto;
    }

    public String getDescProduto(){
        return descProduto;
    }

    public static void SalvarProduto(Produto produto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(produto.getIdProduto() + ";" + produto.getValor() + ";" + produto.getDescProduto());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
        }
    }

    public void ToString(){
        System.out.println("Id do Produto: " + getIdProduto());
        System.out.println("Valor do produto: " + getValor());
        System.out.println("Descrição do produto: " + getDescProduto());
    }
    public Boolean Inserir() {
        try {
            for (Produto p : produtos) {
                if (p.getDescProduto().equalsIgnoreCase(this.getDescProduto())) {
                    System.out.println("Descrição do Produto já cadastrado: " + this.getIdProduto());
                    return false;
                }
            }
            produtos.add(this);
            return true;
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            return false;
        }
    }
 
    public Boolean Editar() {
        Scanner s = new Scanner(System.in);

        System.out.print("Novo Produto: ");
        int novoId = Integer.parseInt(s.nextLine());
        this.setIdProduto(novoId);

        System.out.print("Valor do novo produto: ");
        Double novoValor = s.nextDouble();
        this.setValor(novoValor);

        System.out.print("Nova descrição: ");
        String novaDesc = s.nextLine();
        this.setDescProduto(novaDesc);
        s.close();
        return true;
    }

    public List<Produto> Listar() {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    int idProduto = Integer.parseInt(partes[0]);
                    double valor = Double.parseDouble(partes[1]);
                    String descProduto = partes[2];
                    produtos.add(new Produto(idProduto, valor, descProduto));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler produtos: " + e.getMessage());
        }
        return produtos;
    }
    
    public Produto Consultar(int id) {
        if (id >= 0 && id < produtos.size()) {
            return produtos.get(id);
        } else {
            return null; 
        }
    }
}
