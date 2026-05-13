public class cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;

    public cliente(String nome, String cpf, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;

    }

    public String getNome() {
        return nome;
    }

    public void exbir() {
        System.out.println("===CLIENTE====");
        System.out.println("nome:  " + nome);
        System.out.println("CPF:   " + cpf);
        System.out.println(" telefone  " + telefone);
        System.out.println("Endereço  " + endereco);
    }




}
