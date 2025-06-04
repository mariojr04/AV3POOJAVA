import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consumo{

    private int idConsumo;
    private Produto produto;
    private Reserva reserva;
    private Double quantidade;
    List<Consumo> consumos = new ArrayList<>();
    private static final String FILE_PATH = "consumos.txt";


    public Consumo(int idConsumo, Produto produto, Reserva reserva, Double quantidade){
        this.idConsumo = idConsumo;
        this.produto = produto;
        this.reserva = reserva;
        this.quantidade = quantidade;
    }

    public void setProduto(Produto produto){
        this.produto = produto;
    }
    public Produto getProduto(){
        return produto;
    }

    public void setReserva(Reserva reserva){
        this.reserva = reserva;
    }
    public Reserva getReserva(){
        return reserva;
    }

    public void setQuantidade(Double quantidade){
        this.quantidade = quantidade;
    }
    public Double getQuantidade(){
        return quantidade;
    }

    public void setIdConsumo(int idConsumo){
        this.idConsumo = idConsumo;
    }
    public int getIdConsumo(){
        return idConsumo;
    }

    public void ToString(){
        System.out.println("Produto: " + getProduto());
        System.out.println("Reserva: " + getReserva());
        System.out.println("Quantidade: " + getQuantidade());
        System.out.println("Id Cnsumo: " + getIdConsumo());
    }


    public Boolean Inserir() {
        try {
            for (Consumo c : consumos) {
                if (c.getIdConsumo() == this.getIdConsumo()) {
                    System.out.println("Id de Consumo já adicionado: " + this.getIdConsumo());
                    return false;
                }
            }
            consumos.add(this);
            return true;
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            return false;
        }
    }

    public Boolean Editar() {
        Scanner s = new Scanner(System.in);

        System.out.print("Diga a nova quantidade do produto: ");
        Double novaQuantidade = s.nextDouble();
        this.setQuantidade(novaQuantidade);
        s.close();
        return true;
    }

    public List<Consumo> Listar() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 6) {
                int idConsumo = Integer.parseInt(partes[0]);
                double quantidade = Double.parseDouble(partes[1]);

                consumos.add(new Consumo(idConsumo, produto, reserva, quantidade));
            } else {
                System.out.println("Linha inválida: " + linha);
            }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler produtos: " + e.getMessage());
        }
        return consumos;
    }

    
    public Consumo Consultar(int id) {
        if (id >= 0 && id < consumos.size()) {
            return consumos.get(id);
        } else {
            return null; 
        }
    }
}
