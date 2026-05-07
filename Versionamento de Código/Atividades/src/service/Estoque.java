package service;

import model.Produto;

public class Estoque {

    private Produto[] produtos;
    private int totalProdutos;
    private double faturamentoGeral;

    public Estoque(int capacidade) {
        produtos = new Produto[capacidade];
        totalProdutos = 0;
        faturamentoGeral = 0;
    }
    public void cadastrar(String nome,String unidade, double preco,double saldoInicial){
        if(totalProdutos < produtos.length){
            produtos[totalProdutos] = new Produto(nome,unidade,preco,saldoInicial);
            totalProdutos++;
        }else {
            System.out.println("Estoque cheio!");
        }

    }
    public void registrarEntrada (int indice,double quantidade){
        if(indice<0|| indice>=totalProdutos){
            System.out.println("Produto inválido!");
            return;
        }
        produtos[indice].entrada(quantidade);
    }
    public void registrarSaida(int indice, double quantidade) {
        if (indice < 0 || indice >= totalProdutos) {
            System.out.println("Produto inválido!");
            return;
        }
        boolean conseguiu = produtos[indice].sair(quantidade);
        if (conseguiu) {
            faturamentoGeral += quantidade * produtos[indice].getPreco();
            System.out.println("Saída registrada!");
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }
    public void listar() {
        if (totalProdutos == 0) {
            System.out.println("Nenhum produto cadastrado!");
            return;
        }
        for (int i = 0; i < totalProdutos; i++) {
            System.out.println((i + 1) + ". " + produtos[i].getNome() +
                    " | Saldo: " + produtos[i].getSaldo() +
                    " " + produtos[i].getUnidade());
        }
    }
    public void relatorio() {
        System.out.println("\n======= RELATÓRIO FINAL =======");
        int indiceMaior = 0;
        for (int i = 0; i < totalProdutos; i++) {
            System.out.println(produtos[i].getNome() +
                    " | Saldo: " + produtos[i].getSaldo() +
                    " | Saídas: " + produtos[i].getTotalSaidas() +
                    " | Faturamento: R$ " + produtos[i].getFaturamento());
            if (produtos[i].getTotalSaidas() > produtos[indiceMaior].getTotalSaidas()) {
                indiceMaior = i;
            }
        }
        System.out.println("\nMaior volume de saídas: " + produtos[indiceMaior].getNome());
        System.out.println("Faturamento geral: R$ " + faturamentoGeral);
    }

}