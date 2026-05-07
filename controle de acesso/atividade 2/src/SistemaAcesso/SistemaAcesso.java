package SistemaAcesso;
import java.util.ArrayList;
import usuario.usuario;
import area.area;
import registroAcesso.registroAcesso;
public class SistemaAcesso {
   public ArrayList<usuario>usarios;
    public ArrayList<area>Areas;
    public  ArrayList< registroAcesso> registros;

    public SistemaAcesso(){
       this.usarios = new ArrayList<>();
        this.Areas = new ArrayList<>();
        this.registros =new ArrayList<>();

    }
    public static String gerarHash(String texto) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(texto.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return texto;
        }
    }

    public void cadastrarUsuario( String id,String nome,String senhahash,int nivel) {
        usuario novoUsuario = new usuario(id, nome, gerarHash(senhahash), nivel);
        this.usarios.add(novoUsuario);

    }

    public usuario autenticar(String id, String senhahash){

            for (usuario u : usarios) {
                if (u.id.equals(id) && u.senhahash.equals(gerarHash(senhahash))) {
                    return u;
                }
            }
            return null;
        }

    public boolean verificarAcesso(usuario u, area a) {
        return u.nivel >= a.nivelminimo;
    }

    public void registrar(String idUsuario, String nomeArea, boolean autorizado) {
        String dataHora = java.time.LocalDateTime.now().toString();
        registroAcesso r = new registroAcesso(idUsuario, nomeArea, dataHora, autorizado);
        this.registros.add(r);
    }



}

