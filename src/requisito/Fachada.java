package requisito;

import java.util.ArrayList;

import modelo.Convidado;
import modelo.Empregado;
import modelo.Participante;
import modelo.Reuniao;
import repositorio.Repositorio;

public class Fachada {
	 private static Repositorio repositorio = new Repositorio();
	 
	 public Fachada() {}
	 
	 public static ArrayList<Reuniao> listarReunioes(){
		return repositorio.getReuniao();
	 }
	 
	 public static ArrayList<Participante> listarParticipante(){
		 return repositorio.getParticipante();
	 }
	 
	 public static ArrayList<Convidado> listarConvidado(){
		 return repositorio.getConvidado();
	 }
	 
	 public static ArrayList<Empregado> listarEmpregado(){
		 return repositorio.getEmpregado();
	 }

	 public static void criarEmpregado(String nome, String email, String setor) throws Exception {
		    if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com$")) {
		        throw new Exception("Formato do Email está incorreto");
		    }

		    for (Participante p : listarParticipante()) {
		        if (p.getNome().equals(nome)) {
		            throw new Exception("Participante com esse noma já foi registrado");
		        }
		    }

		    Empregado empregado = new Empregado(nome, email, setor);
		    repositorio.adicionar(empregado);
			repositorio.gravarObjetos();
		}

	 public static void criarConvidado(String nome, String email, String instituicao) throws Exception {
		    if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com$")) {
		        throw new Exception("Formato do Email está incorreto");
		    }

		    for (Participante p : listarParticipante()) {
		        if (p.getNome().equals(nome)) {
		            throw new Exception("Participante com esse noma já foi registrado");
		        }
		    }

		 Convidado convidado = new Convidado(nome, email, instituicao);
		 repositorio.adicionar(convidado);
		 repositorio.gravarObjetos();
	 }
	 
	 public static void criarReuniao(String data, String assunto, ArrayList<String> listaNome) throws Exception {
			if (listaNome.size() < 2) {
				throw new Exception("Uma reunião deve ter no mínimo dois participantes");
			}
			
			for (Reuniao r : listarReunioes()) {
				if (r.getData().equals(data)) {
					throw new Exception("Reunião com essa data já foi registrada");
				}
			}
		
			ArrayList<Participante> participantes = new ArrayList<>();
			for (String nome : listaNome) {
				Participante p = repositorio.localizarParticipante(nome);

				if (p == null) {
					throw new Exception("participante inexistente: " + nome);
				} else {
					participantes.add(p);
				}
			}
			int id = repositorio.incrementarId();
			Reuniao reuniao = new Reuniao(id, data, assunto);
			repositorio.adicionar(reuniao);
			
			for (Participante pa : participantes) {
				adicionarParticipanteReuniao(pa.getNome(), reuniao.getId());
			}

			repositorio.gravarObjetos();
	 }
	 
	 public static void adicionarParticipanteReuniao(String nome, int id) throws Exception {
		    Reuniao r = repositorio.localizarReuniao(id);
	
		    if (r == null)
		        throw new Exception("Reunião inexistente: " + id);

		    Participante p = repositorio.localizarParticipante(nome);
		    if (p == null)
		        throw new Exception("Participante inexistente: " + nome);

		    if (r.getParticipantes().contains(p))
		        throw new Exception("Participante já está na reunião");

		    r.adicionar(p);
			repositorio.gravarObjetos();
	}
	 
	 public static void removerParticipanteReuniao(String nome, int id) throws Exception {
		    Reuniao r = repositorio.localizarReuniao(id);
			
		    if (r == null)
		        throw new Exception("Reunião inexistente: " + id);

		    Participante p = repositorio.localizarParticipante(nome);
		    if (p == null)
		        throw new Exception("Participante inexistente: " + nome);

		    if (!r.getParticipantes().contains(p))
		        throw new Exception("Participante não está na reunião");

		    r.getParticipantes().remove(p);
			if (r.getParticipantes().size() < 2) {
				cancelarReuniao(id);
				throw new Exception("A reunião foi cancelada, pois deve ter no mínimo dois participantes.");
			}
	}
	 
	public static void cancelarReuniao(int id) throws Exception {
	    Reuniao r = repositorio.localizarReuniao(id);
	    
	    if (r == null)
	        throw new Exception("Reunião inexistente: " + id);
	    
	    ArrayList<Participante> copia = new ArrayList<>(r.getParticipantes());
	    for (Participante p : copia) {
	        r.remover(p);
	    }
	    
	    repositorio.remover(r);
		repositorio.gravarObjetos();
	}
	
	
	public static ArrayList<Participante> consulta1(int n) throws Exception {
		if (n <= 0) {
			throw new Exception("Número de reuniões tem que ser maior que zero");
		}
		
		ArrayList<Participante> participante = new ArrayList<>();
		
		for (Participante p : listarParticipante()) { 
			if(p.getReunioes().size() >= n) {
				participante.add(p);
			}
		}
		
		if (participante.size() == 0) 
			throw new Exception("Nenhum participante participou de " + n + " reuniões");
		
		return participante;
	}

	public static int consulta2(int m, int a) {
		int quantidade = 0;
		for (Reuniao r : listarReunioes()) {
			String data = r.getData();
			String[] parte; 
			parte = data.split("/");
			int mes = Integer.parseInt(parte[1]);
			int ano = Integer.parseInt(parte[2]);
			
			if (mes == m && ano == a) {
				quantidade ++;
			}
		}
		return quantidade;
	}
}
