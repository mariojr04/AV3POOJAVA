import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quarto{

    private int idQuarto;
    private String descQuarto;
    List<Quarto> quartos = new ArrayList<>();
    

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
    
     public Boolean inserir() {
        try (FileWriter fw = new FileWriter("quartos.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(this.toString() + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao inserir quarto: " + e.getMessage());
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

    public ArrayList<Quarto> Listar() {
        ArrayList<Quarto> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("quartos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    public Quarto Consultar(int id) {
        if (id >= 0 && id < quartos.size()) {
            return quartos.get(id);
        } else {
            return null; 
        }
    }
}
