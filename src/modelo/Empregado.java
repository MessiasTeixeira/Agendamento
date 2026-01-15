package modelo;

import java.util.ArrayList;

public class Empregado extends Participante {
    private String setor;
    
    public Empregado(String nome, String email, String setor) {
        super(nome, email);
        this.setor = setor;
    }
    
    @Override
    public String getSetor() {
        return setor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(getNome())
        .append(" | Email: ").append(getEmail())
        .append(" | Setor: ").append(getSetor())
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
