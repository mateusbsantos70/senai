package usuario;

public class usuario {
    public String id;
    public String nome;
    public String senhahash;
    public int nivel;

    public usuario( String id,String nome,String senhahash, int nivel){
        this.id = id;
        this.nome =nome;
        this.senhahash=senhahash;
        this.nivel= nivel;
    }
}
