package modelo;

import java.util.ArrayList;

public class Convidado extends Participante {
    private String instituicao;

    public Convidado(String nome, String email, String instituicao) {
        super(nome, email);
        this.instituicao = instituicao;
    }

    @Override
    public String getInstituicao() {
        return instituicao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(getNome())
        .append(" | Email: ").append(getEmail())
        .append(" | Instituição: ").append(getInstituicao())
        .append("\nReuniões: ");

        if (getReunioes().isEmpty()) {
            sb.append("nenhuma");
        } else {
            ArrayList<Integer> idsJaImpressos = new ArrayList<>();

            for (Reuniao r : getReunioes()) {
                int id = r.getId();

                if (!idsJaImpressos.contains(id)) {
                    if (!idsJaImpressos.isEmpty()) {
                        sb.append(", ");
                    }
                    sb.append(id);
                    idsJaImpressos.add(id);
                }
            }
        }

        return sb.toString();
    }



}
