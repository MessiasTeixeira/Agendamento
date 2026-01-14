package modelo;
import java.util.ArrayList;

import requisito.Fachada;

public class Teste {

    public static void main(String[] args) {
        System.out.println("=== TESTE ROBUSTO DO SISTEMA DE REUNIÕES ===\n");

        try {
            // =========================
            // 1. Criar participantes
            // =========================
            Fachada.criarEmpregado("Ana", "ana@empresa.com", "TI");
            Fachada.criarEmpregado("Bruno", "bruno@empresa.com", "RH");
            Fachada.criarConvidado("Carlos", "carlos@gmail.com", "UFABC");
            Fachada.criarConvidado("Daniela", "daniela@gmail.com", "USP");

            System.out.println("Participantes criados.\n");

            // =========================
            // 2. Criar reuniões (com lista de nomes)
            // =========================
            ArrayList<String> nomesR1 = new ArrayList<>();
            nomesR1.add("Ana");
            nomesR1.add("Bruno");

            Fachada.criarReuniao("10/05/2024", "Planejamento", nomesR1);

            ArrayList<String> nomesR2 = new ArrayList<>();
            nomesR2.add("Ana");
            nomesR2.add("Carlos");

            Fachada.criarReuniao("15/05/2024", "Retrospectiva", nomesR2);

            ArrayList<String> nomesR3 = new ArrayList<>();
            nomesR3.add("Daniela");
            nomesR3.add("Bruno");

            Fachada.criarReuniao("20/06/2024", "Orçamento", nomesR3);

            System.out.println("Reuniões criadas.\n");

            // =========================
            // 3. Listar reuniões
            // =========================
            System.out.println("Reuniões cadastradas:");
            for (Reuniao r : Fachada.listarReunioes()) {
                System.out.println(
                    "ID: " + r.getId() +
                    " | Data: " + r.getData() +
                    " | Assunto: " + r.getAssunto() +
                    " | Participantes: " + r.getParticipantes().size()
                );
            }

            // =========================
            // 4. Consulta 1
            // =========================
            System.out.println("\nConsulta 1 (>= 2 reuniões):");
            for (Participante p : Fachada.consulta1(2)) {
                System.out.println(
                    p.getNome() + " | reuniões: " + p.getReunioes().size()
                );
            }

            // =========================
            // 5. Consulta 2
            // =========================
            int qtd = Fachada.consulta2(5, 2024);
            System.out.println("\nConsulta 2:");
            System.out.println("Reuniões em 05/2024: " + qtd);

            // =========================
            // 6. Cancelar reunião (ID autogerado)
            // =========================
            int idParaCancelar = Fachada.listarReunioes().get(0).getId();
            Fachada.cancelarReuniao(idParaCancelar);

            System.out.println("\nReunião cancelada: ID " + idParaCancelar);

            // =========================
            // 7. Estado final
            // =========================
            System.out.println("\nEstado dos participantes:");
            for (Participante p : Fachada.listarParticipante()) {
                System.out.println(
                    p.getNome() + " | reuniões: " + p.getReunioes().size()
                );
            }

            System.out.println("\nTotal de reuniões restantes: "
                    + Fachada.listarReunioes().size());

            System.out.println("\n=== FIM DO TESTE ===");

        } catch (Exception e) {
            System.out.println("ERRO NO TESTE: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

