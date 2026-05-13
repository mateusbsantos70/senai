import java.util.Scanner;

public class Main {

    public static produto cadastrarProduto(Scanner sc) {
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();

        System.out.print("Preco: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        System.out.print("Quantidade em estoque: ");
        int estoque = sc.nextInt();
        sc.nextLine();

        return new produto(nome, preco, estoque);
    }

    public static cliente cadastrarCliente(Scanner sc) {
        System.out.print("Nome do cliente: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Endereco: ");
        String endereco = sc.nextLine();

        return new cliente(nome, cpf, telefone, endereco);
    }

    public static Pedido criarPedido(Scanner sc, produto p, caixa cx) {
        cliente cl = cadastrarCliente(sc);

        System.out.print("Delivery? (1-Sim / 2-Nao): ");
        boolean delivery = sc.nextInt() == 1;
        sc.nextLine();

        Pedido pedido = new Pedido(cl, delivery);

        int op;
        do {
            System.out.println("--- Adicionar item ---");
            System.out.println("(1) Adicionar " + p.getNome());
            System.out.println("(2) Confirmar pedido");
            op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                System.out.print("Quantidade: ");
                int qtd = sc.nextInt();
                sc.nextLine();
                pedido.adicionarItem(p, qtd);
            }
        } while (op != 2);

        pedido.exibir();

        System.out.print("Confirmar pagamento? (1-Sim / 2-Nao): ");
        int confirma = sc.nextInt();
        sc.nextLine();

        if (confirma == 1) {
            pedido.confirmar(cx);
        } else {
            System.out.println("Pedido cancelado!");
        }

        return pedido;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        caixa cx   = new caixa(100.0);
        produto p  = cadastrarProduto(sc);

        int opcao;

        do {
            System.out.println("===========================");
            System.out.println("(1) Novo pedido");
            System.out.println("(2) Comprar estoque");
            System.out.println("(3) Ver produto");
            System.out.println("(4) Ver caixa");
            System.out.println("(5) Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    criarPedido(sc, p, cx);
                    break;
                case 2:
                    System.out.print("Quantidade para comprar: ");
                    int qtdComprar = sc.nextInt();
                    sc.nextLine();
                    p.comprar(qtdComprar, cx);
                    break;
                case 3:
                    p.exibir();
                    break;
                case 4:
                    cx.exibir();
                    break;
                case 5:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 5);

        sc.close();
    }
}