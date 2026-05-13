public class produto {
    private String nome;
    private double preco;
    private int estoque;

    public produto(String nome, double preco, int estoque) {
        this.nome    = nome;
        this.preco   = preco;
        this.estoque = estoque;
    }

    public String getNome()  { return nome; }
    public double getPreco() { return preco; }

    public boolean temEstoque(int quantidade) {
        return quantidade > 0 && quantidade <= estoque;
    }

    public void debitar(int quantidade) {
        estoque -= quantidade;
    }

    public void vender(int quantidade, caixa cx) {
        if (quantidade <= estoque) {
            estoque -= quantidade;
            cx.entrada(quantidade * preco);
            System.out.println("SEU PEDIDO ESTA SENDO PREPARADO!!");
        } else {
            System.out.println("ESTOQUE ESTA EM FALTA!!");
        }
    }

    public void comprar(int quantidade, caixa cx) {
        estoque += quantidade;
        cx.saida(quantidade * preco);
        System.out.println("Compra realizada!");
    }

    public void exibir() {
        System.out.println("Nome:    " + nome);
        System.out.printf ("Preco:   R$ %.2f%n", preco);
        System.out.println("Estoque: " + estoque + " unidades");
    }
}