import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Inserir Funcionário");
            System.out.println("2. Inserir Hóspede");
            System.out.println("3. Listar Funcionários");
            System.out.println("4. Listar Hóspedes");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    sc.nextLine();
                    System.out.print("CPF: ");
                    String cpfF = sc.nextLine();
                    System.out.print("Nome: ");
                    String nomeF = sc.nextLine();
                    System.out.print("Idade: ");
                    int idadeF = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Função: ");
                    String funcao = sc.nextLine();
                    Funcionario f = new Funcionario(cpfF, nomeF, idadeF, funcao);
                    if (f.Inserir()) {
                        System.out.println("Funcionário inserido com sucesso!");
                    } else {
                        System.out.println("Falha ao inserir funcionário.");
                    }
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("CPF: ");
                    String cpfH = sc.nextLine();
                    System.out.print("Nome: ");
                    String nomeH = sc.nextLine();
                    System.out.print("Idade: ");
                    int idadeH = sc.nextInt();
                    sc.nextLine();
                    System.out.print("RG: ");
                    String rg = sc.nextLine();
                    System.out.print("Fidelidade (true/false): ");
                    Boolean fidelidade = sc.nextBoolean();
                    Hospede h = new Hospede(cpfH, nomeH, idadeH, rg, fidelidade);
                    if (h.Inserir()) {
                        System.out.println("Hóspede inserido com sucesso!");
                    } else {
                        System.out.println("Falha ao inserir hóspede.");
                    }
                    break;

                case 3:
                    Funcionario fAux = new Funcionario("", "", 0, "");
                    ArrayList<Funcionario> listaF = fAux.listar();
                    if (listaF.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        System.out.println("\n=== Lista de Funcionários ===");
                        for (Funcionario func : listaF) {
                            func.toString();
                        }
                    }
                    break;

                case 4:
                    Hospede hAux = new Hospede("", "", 0, "", false);
                    ArrayList<Hospede> listaH = hAux.listar();
                    if (listaH.isEmpty()) {
                        System.out.println("Nenhum hóspede cadastrado.");
                    } else {
                        System.out.println("\n=== Lista de Hóspedes ===");
                        for (Hospede hosp : listaH) {
                            hosp.ToString();
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}
