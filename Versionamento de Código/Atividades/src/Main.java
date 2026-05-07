import service.Estoque;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    static Estoque estoque = new Estoque(10);

    public static void main(String[] args) {

        cadastrarProdutos();

        int opcao;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("(1). Registrar entrada");
            System.out.println("(2). Registrar saída");
            System.out.println("(3). Listar produtos");
            System.out.println("(4). Encerrar");
            System.out.print("Escolha uma opção: ");
            opcao = input.nextInt();

            switch (opcao) {
                case 1 -> registrarEntrada();
                case 2 -> registrarSaida();
                case 3 -> estoque.listar();
                case 4 -> estoque.relatorio();
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    public static void cadastrarProdutos() {
        System.out.print("Quantos produtos (máx 10): ");
        int total = input.nextInt();
        if (total > 10) total = 10;

        for (int i = 0; i < total; i++) {
            System.out.println("\n-- Produto " + (i + 1) + " --");
            System.out.print("Nome: ");
            String nome = input.next();
            System.out.print("Unidade (kg, L, un): ");
            String unidade = input.next();
            System.out.print("Preço: R$ ");
            double preco = input.nextDouble();
            System.out.print("Quantidade inicial: ");
            double saldo = input.nextDouble();

            estoque.cadastrar(nome, unidade, preco, saldo);
        }
    }

    public static void registrarEntrada() {
        estoque.listar();
        System.out.print("Escolha o produto: ");
        int indice = input.nextInt() - 1;
        System.out.print("Quantidade: ");
        double quantidade = input.nextDouble();
        estoque.registrarEntrada(indice, quantidade);
    }

    public static void registrarSaida() {
        estoque.listar();
        System.out.print("Escolha o produto: ");
        int indice = input.nextInt() - 1;
        System.out.print("Quantidade: ");
        double quantidade = input.nextDouble();
        estoque.registrarSaida(indice, quantidade);
    }

}