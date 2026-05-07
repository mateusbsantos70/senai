import java.util.Scanner;
import SistemaAcesso.SistemaAcesso;
import usuario.usuario;
import area.area;
import registroAcesso.registroAcesso;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaAcesso sistema = new SistemaAcesso();

        // Cadastrar áreas fixas
        sistema.Areas.add(new area("Recepção", 1));
        sistema.Areas.add(new area("Escritório", 2));
        sistema.Areas.add(new area("Sala de Servidores", 3));

        // Cadastrar usuários de teste
        sistema.cadastrarUsuario("admin01", "Admin", "1234", 3);
        sistema.cadastrarUsuario("func01", "João", "1234", 2);
        sistema.cadastrarUsuario("visit01", "Maria", "1234", 1);

        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== SISTEMA DE ACESSO ===");
            System.out.print("ID: ");
            String id = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();

            usuario usuarioLogado = sistema.autenticar(id, senha);

            if (usuarioLogado == null) {
                System.out.println("ID ou senha incorretos!");
            } else {
                System.out.println("Bem-vindo, " + usuarioLogado.nome + "!");

                if (usuarioLogado.nivel == 3) {
                    System.out.println("Você é ADMINISTRADOR.");
                } else if (usuarioLogado.nivel == 2) {
                    System.out.println("Você é FUNCIONÁRIO.");
                } else {
                    System.out.println("Você é VISITANTE.");
                }

                boolean logado = true;
                while (logado) {
                    System.out.println("\n--- MENU ---");
                    System.out.println("1. Tentar acessar uma área");
                    if (usuarioLogado.nivel == 3) {
                        System.out.println("2. Cadastrar novo usuário");
                        System.out.println("3. Ver histórico de acessos");
                    }
                    System.out.println("0. Sair");
                    System.out.print("Opção: ");
                    String opcao = sc.nextLine();

                    if (opcao.equals("1")) {
                        System.out.println("\nÁreas disponíveis:");
                        for (int i = 0; i < sistema.Areas.size(); i++) {
                            System.out.println((i + 1) + ". " + sistema.Areas.get(i).nome);
                        }
                        System.out.print("Escolha o número da área: ");
                        String escolha = sc.nextLine();
                        int indice = Integer.parseInt(escolha) - 1;
                        area areaSelecionada = sistema.Areas.get(indice);

                        boolean autorizado = sistema.verificarAcesso(usuarioLogado, areaSelecionada);
                        sistema.registrar(usuarioLogado.id, areaSelecionada.nome, autorizado);

                        if (autorizado) {
                            System.out.println("Acesso AUTORIZADO à " + areaSelecionada.nome);
                        } else {
                            System.out.println("Acesso NEGADO à " + areaSelecionada.nome);
                        }

                    } else if (opcao.equals("2") && usuarioLogado.nivel == 3) {
                        System.out.print("ID do novo usuário: ");
                        String novoId = sc.nextLine();
                        System.out.print("Nome: ");
                        String novoNome = sc.nextLine();
                        System.out.print("Senha: ");
                        String novaSenha = sc.nextLine();
                        System.out.print("Nível (1=visitante, 2=funcionário, 3=admin): ");
                        int novoNivel = Integer.parseInt(sc.nextLine());
                        sistema.cadastrarUsuario(novoId, novoNome, novaSenha, novoNivel);
                        System.out.println("Usuário cadastrado com sucesso!");

                    } else if (opcao.equals("3") && usuarioLogado.nivel == 3) {
                        System.out.println("\n--- HISTÓRICO ---");
                        for (registroAcesso r : sistema.registros) {
                            String resultado = r.autorizado ? "AUTORIZADO" : "NEGADO";
                            System.out.println(r.dataHora + " | " + r.idUsario + " | " + r.nomeArea + " | " + resultado);
                        }

                    } else if (opcao.equals("0")) {
                        logado = false;
                    } else {
                        System.out.println("Opção inválida!");
                    }
                }
            }
        }
    }
}