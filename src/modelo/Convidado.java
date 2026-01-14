package modelo;

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

}
