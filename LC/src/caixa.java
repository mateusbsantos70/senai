public class caixa{
    double saldo;
    public caixa(double saldoInicial){
        this.saldo=saldoInicial;
    }
    public void entrada(double valor){
        saldo = saldo+valor;
    }
    public void saida(double valor){
        saldo = saldo-valor;
    }
    public  void exibir(){
        System.out.println("saldo do caixa: R$"+saldo);

    }
}