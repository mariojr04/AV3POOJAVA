import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quarto{

    private int idQuarto;
    private String descQuarto;
    List<Quarto> quartos = new ArrayList<>();
    private static final String FILE_PATH = "quartos.txt";

    public Quarto(int idQuarto, String descQuarto){
        this.idQuarto = idQuarto;
        this.descQuarto = descQuarto;
    }

    public void setIdQuarto(int idQuarto){
        this.idQuarto = idQuarto;
    }

    public int getIdQuarto(){
        return idQuarto;
    }

    public void setDescQuarto(String descQuarto){
        this.descQuarto = descQuarto;
    }

    public String getDescQuarto(){
        return descQuarto;
    }

    public void ToString(){
        System.out.println("Id do quarto: " + getIdQuarto());
        System.out.println("Desc do quarto: " + getDescQuarto());
    }
    
    public Boolean Inserir() {
        try {
            for (Quarto q : quartos) {
                if (q.getIdQuarto() == this.getIdQuarto()) {
                    System.out.println("Quarto já ocupado: " + this.getIdQuarto());
                    return false;
                }
            }
            quartos.add(this);
            return true;
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            return false;
        }
    }

    public Boolean Editar() {
        Scanner s = new Scanner(System.in);

        System.out.print("Novo desc do quarto: ");
        String novaDesc = s.nextLine();
        this.setDescQuarto(novaDesc);
        s.close();
        return true;
    }

    public List<Quarto> Listar() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    int idQuarto = Integer.parseInt(partes[0]);
                    String descQuarto = partes[1];
                    quartos.add(new Quarto(idQuarto, descQuarto));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler produtos: " + e.getMessage());
        }
        return quartos;
    }

    public Quarto Consultar(int id) {
        if (id >= 0 && id < quartos.size()) {
            return quartos.get(id);
        } else {
            return null; 
        }
    }
}
