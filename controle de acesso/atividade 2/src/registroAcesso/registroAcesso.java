package registroAcesso;

public class registroAcesso {
    public String idUsario;
    public String nomeArea;
    public String dataHora;
    public boolean autorizado;

    public  registroAcesso(String idUsario,String nomeArea,String dataHora,boolean autorizado){
        this.idUsario = idUsario;
        this.nomeArea = nomeArea;
        this.dataHora = dataHora;
        this.autorizado = autorizado;
    }

}
