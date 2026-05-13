import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private cliente cliente;
    private List<ItemPedido> itens;
    private boolean delivery;

    public Pedido(cliente cliente, boolean delivery) {
        this.cliente  = cliente;
        this.delivery = delivery;
        this.itens    = new ArrayList<>();
    }

    public boolean adicionarItem(produto produto, int quantidade) {
        if (!produto.temEstoque(quantidade)) {
            System.out.println("Estoque insuficiente para: " + produto.getNome());
            return false;
        }
        itens.add(new ItemPedido(produto, quantidade));
        return true;
    }

    public double getTotal() {
        double total = 0;
        for (ItemPedido item : itens) total += item.getSubtotal();
        return total;
    }

    public boolean confirmar(caixa cx) {
        if (itens.isEmpty()) {
            System.out.println("Pedido vazio!");
            return false;
        }
        for (ItemPedido item : itens) item.debitar();
        cx.entrada(getTotal());
        System.out.println("Pedido confirmado!");
        return true;
    }

    public void exibir() {
        System.out.println("=== Pedido ===");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Tipo: " + (delivery ? "Delivery" : "Balcao"));
        for (ItemPedido item : itens) item.exibir();
        System.out.printf("Total: R$ %.2f%n", getTotal());
    }
}