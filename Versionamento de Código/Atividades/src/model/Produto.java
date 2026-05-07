package model;



public class Produto {

    private String nome;
    private String unidade;
    private double preco;
    private double saldo;
    private double totalSaidas;
    private double faturamento;

    public Produto(String nome, String unidade, double preco, double saldoInicial) {
        this.nome = nome;
        this.unidade = unidade;
        this.preco = preco;
        this.saldo = saldoInicial;
    }

    public void entrada(double quantidade){
        saldo+=quantidade;
    }

    public boolean sair(double quantidade){
        if (quantidade>saldo){
            return false;
        }
        saldo-=quantidade;
        totalSaidas+=quantidade;
        faturamento+=quantidade*preco;
        return true;
    }

    public String getNome()   { return nome;}
    public String getUnidade() { return unidade;}
    public double getPreco()  { return preco;}
    public double getTotalSaidas(){ return totalSaidas;}
    public double getFaturamento(){ return faturamento;}
    public double getSaldo() { return saldo;}


}
