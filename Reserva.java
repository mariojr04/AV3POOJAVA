    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;

    public class Reserva {

        private int idReserva;
        private Double valor;
        private String dataEntrada;
        private String dataSaida;
        private Boolean checkin;
        private Boolean checkout;
        private Quarto quarto;
        private Hospede hospede;
        List<Reserva> reservas = new ArrayList<>();
        private static final String FILE_PATH = "reservas.txt";

        public Reserva(int idReserva, Double valor, String dataEntrada, String dataSaida, Boolean checkin, Boolean checkout, Quarto quarto, Hospede hospede){
            this.idReserva = idReserva;
            this.valor = valor;
            this.dataEntrada = dataEntrada;
            this.dataSaida = dataSaida;
            this.checkin = checkin;
            this.checkout = checkout;
            this.quarto = quarto;
            this.hospede = hospede;
        }

        public void setIdReserva(int idReserva){
            this.idReserva = idReserva;
        }

        public int getIdReserva(){
            return idReserva;
        }

        public void setValor(Double valor){
            this.valor = valor;
        }

        public Double getValor(){
            return valor;
        }

        public void setDataEntrada(String dataEntrada){
            this.dataEntrada = dataEntrada;
        }

        public String getDataEntrada(){
            return dataEntrada;
        }

        public void setDataSaida(String dataSaida){
            this.dataSaida = dataSaida;
        }

        public String getDataSaida(){
            return dataSaida;
        }
        public void setCheckin(Boolean checkin){
            this.checkin = checkin;
        }

        public Boolean getCheckin(){
            return checkin;
        }

        public void setCheckout( Boolean checkout){
            this.checkout = checkout;
        }

        public  Boolean getCheckout(){
            return checkout;
        }
        public void setQuarto(Quarto quarto){
            this.quarto = quarto;
        }

        public Quarto getQuarto(){
            return quarto;
        }

        public void setHospede(Hospede hospede){
            this.hospede = hospede;
        }

        public Hospede getHospede(){
            return hospede;
        }

        public void ToString(){
            System.out.println("Id da reserva: " + getIdReserva());
            System.out.println("Valor da reserva: " + getValor());
            System.out.println("Data de entrada: " + getDataEntrada());
            System.out.println("Data de saida: " + getDataSaida());
            System.out.println("Fez checkin?: " + getCheckin());
            System.out.println("Fez checkout: " + getCheckout());
            System.out.println("Quarto: " + getQuarto());
            System.out.println("Hospede: " + getHospede());
        }

        public Boolean Inserir() {
            try {
                for (Reserva r : reservas) {
                    if (r.getIdReserva() == (this.getIdReserva())) {
                        System.out.println("Id de Reserva já cadastrado: " + this.getIdReserva());
                        return false;
                    }
                }
                reservas.add(this);
                return true;
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                return false;
            }
        }

        
        public Boolean Editar() {
            Scanner s = new Scanner(System.in);

            System.out.print("Nova data de entrada: ");
            String novaDtEntrada = s.nextLine();
            this.setDataEntrada(novaDtEntrada);

            System.out.print("Nova data de Saida: ");
            String novaDtSaida = s.nextLine();
            this.setDataSaida(novaDtSaida);
            s.close();
            return true;
        }

        public List<Reserva> Listar() {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] partes = linha.split(";");
                    if (partes.length == 6) {
                        int idReserva = Integer.parseInt(partes[0]);
                        Double valor = Double.parseDouble(partes[1]);
                        String dataEntrada = partes[2];
                        String dataSaida = partes[3];
                        Boolean checkin = Boolean.parseBoolean(partes[4]);
                        Boolean checkout = Boolean.parseBoolean(partes[5]);
                        reservas.add(new Reserva( idReserva, valor, dataEntrada, dataSaida, checkin, checkout, quarto, hospede));
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler produtos: " + e.getMessage());
            }
            return reservas;
        }

        
        public Reserva Consultar(int id) {
            if (id >= 0 && id < reservas.size()) {
                return reservas.get(id);
            } else {
                return null; 
            }
        }
    }
