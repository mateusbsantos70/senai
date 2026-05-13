public class ItemPedido {
    private produto produto;
    private int quantidade;

    public ItemPedido(produto produto, int quantidade) {
        this.produto    = produto;
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }

    public void debitar() {
        produto.debitar(quantidade);
    }

    public void exibir() {
        System.out.printf("  - %-15s x%d  R$ %.2f%n",
                produto.getNome(), quantidade, getSubtotal());
    }
}