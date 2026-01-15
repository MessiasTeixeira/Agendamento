package modelo;

import java.util.ArrayList;

public class Participante {
    private String nome;
    private String email;
    private ArrayList<Reuniao> reunioes = new ArrayList<>();

    public Participante(String nome, String email) {
        super();
        this.nome = nome;
        this.email = email;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getEmail() {
        return email;
    }

    public ArrayList<Reuniao> getReunioes() {
        return reunioes;
    }

    public void adicionar (Reuniao r) {
        reunioes.add(r);
    }

    public void remover (Reuniao r) {
        reunioes.remove(r);
    }
    
    public String getSetor() {
    	return null;
    }
    
    public String getInstituicao() {
    	return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(nome)
        .append(" | Email: ").append(email)
        .append("\nReuniões: ");

        if (reunioes.isEmpty()) {
            sb.append("nenhuma");
        } else {
            ArrayList<Integer> idsJaImpressos = new ArrayList<>();

            for (Reuniao r : reunioes) {
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
