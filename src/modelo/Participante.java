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
}
