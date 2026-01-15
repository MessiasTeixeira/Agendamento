package modelo;

import java.util.ArrayList;

public class Reuniao{
    private int id;
    private String data;
    private String assunto;
    private ArrayList<Participante> participantes = new ArrayList<>();

    public Reuniao(int id, String data, String assunto) {
        this.id = id;
        this.data = data;
        this.assunto = assunto;
    }

    public int getId() {
        return id;
    }
    
    public String getData() {
        return data;
    }

    public String getAssunto() {
        return assunto;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void adicionar (Participante p) {
        participantes.add(p);
        p.adicionar(this);
    }

    public void remover (Participante p) {
        participantes.remove(p);
        p.remover(this);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Id: ").append(id).append(" | Data: ").append(data).append(" | Assunto: ").append(assunto).append("\nParticipantes: ");

        if (participantes.isEmpty()) {
            sb.append("nenhum");
        } else {
            for (int i = 0; i < participantes.size(); i++) {
                sb.append(participantes.get(i).getNome());
                if (i < participantes.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        return sb.toString();
    }

}