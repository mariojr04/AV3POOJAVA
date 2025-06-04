import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Cadastrar Hóspede");
            System.out.println("2. Listar Hóspedes");
            System.out.println("3. Cadastrar Funcionário");
            System.out.println("4. Listar Funcionários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = Integer.parseInt(scanner.nextLine());
                    System.out.print("RG: ");
                    String rg = scanner.nextLine();
                    System.out.print("Fidelidade (true/false): ");
                    boolean fidelidade = Boolean.parseBoolean(scanner.nextLine());

                    Hospede hospede = new Hospede(cpf, nome, idade, rg, fidelidade);
                    if (hospede.Inserir()) {
                        System.out.println("Hóspede cadastrado com sucesso.");
                    }
                    break;



                case 2:
                    Hospede hTemp = new Hospede("", "", 0, "", false);
                    for (Hospede h : hTemp.Listar()) {
                        h.ToString();
                        System.out.println("----------------------");
                    }
                    break;



                case 3:
                    System.out.print("CPF: ");
                    String cpfF = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nomeF = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idadeF = Integer.parseInt(scanner.nextLine());
                    System.out.print("Função: ");
                    String funcao = scanner.nextLine();

                    Funcionario funcionario = new Funcionario(cpfF, nomeF, idadeF, funcao);
                    if (funcionario.Inserir()) {
                        System.out.println("Funcionário cadastrado com sucesso.");
                    }
                    break;



                case 4:
                    Funcionario fTemp = new Funcionario("", "", 0, "");
                    for (Funcionario f : fTemp.Listar()) {
                        f.ToString();
                        System.out.println("----------------------");
                    }
                    break;



                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
