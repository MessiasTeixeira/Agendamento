package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Participante;
import modelo.Reuniao;
import modelo.Convidado;
import modelo.Empregado;


public class Repositorio {
	private ArrayList<Participante> participantes = new ArrayList<>();
	private ArrayList<Reuniao> reunioes = new ArrayList<>();
	private ArrayList<Convidado> convidados = new ArrayList<>();
	private ArrayList<Empregado> empregados = new ArrayList<>();
	
	public Repositorio() {
		carregarObjetos();
	}
	
	public void adicionar(Participante p) {
		participantes.add(p);
	}
	
	public void remover(Participante p) {
		participantes.remove(p);
	}
	
	public void adicionar(Reuniao r) {
		reunioes.add(r);
	}
	
	public void remover(Reuniao r) {
		reunioes.remove(r);
	}

	
	public ArrayList<Participante> getParticipante(){
		return participantes;
	}
	
	public ArrayList<Reuniao> getReuniao(){
		return reunioes;
	}
	
	
	public ArrayList<Convidado> getConvidado(){
		return convidados;
	}
	
	public ArrayList<Empregado> getEmpregado(){
		return empregados;
	}
	
	public Participante localizarParticipante(String nome) {
		for (Participante p : getParticipante()) {
			if (p.getNome().equalsIgnoreCase(nome)) {
				return p;
			}
		}
		return null;
	}
	
	public Reuniao localizarReuniao(int id) {
		for (Reuniao r : getReuniao()) {
			if (r.getId() == id) {
				return r;
			}
		}
		return null;
	}
	
	public int incrementarId() {
		if (reunioes.size() == 0) {
			return 1;
		}
		Reuniao a = reunioes.get(reunioes.size()-1);
		return a.getId()+1;
		
	}
	
	public int getTamanhoParticipantes() {
		return participantes.size();
	}
	
	public int getTamanhoReunioes() {
		return reunioes.size();
	}
	
	
	public void carregarObjetos()  	{
		// carregar os objetos dos arquivos .csv

		try {
			//caso os arquivos nao existam, serao criados vazios
			//obter o caminho do prog. no S.O. para criacao dos arquivos
			File f1 = new File( new File(".\\reunioes.csv").getCanonicalPath() ) ; 
			File f2 = new File( new File(".\\participantes.csv").getCanonicalPath() ) ; 
			if (!f1.exists() || !f2.exists() ) {
				//System.out.println("criando arquivos .csv vazios");
				FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
				return;
			}
		}
		catch(Exception ex)		{
			throw new RuntimeException("prob. na criacao dos arquivos:"+ex.getMessage());
		}

		String linha;	
		String[] partes;	
		String id,data,assunto, nome, email;
		Reuniao reuniao;
		Participante participante;

		Scanner arquivo=null;
		try	{
			File f = new File( new File(".\\reunioes.csv").getCanonicalPath() )  ;
			arquivo = new Scanner(f);	 //  pasta do projeto
			while(arquivo.hasNextLine()) 	{
				linha = arquivo.nextLine().trim();		
				partes = linha.split(";");	
				//System.out.println(Arrays.toString(partes));
				id = partes[0];
				data = partes[1];
				assunto = partes[2];
				reuniao = new Reuniao(Integer.parseInt(id),data, assunto);
				this.adicionar(reuniao);
			} 
			arquivo.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de prateleiras:"+ex.getMessage());
		}

		try	{
			File f = new File( new File(".\\participantes.csv").getCanonicalPath())  ;
			arquivo = new Scanner(f);	 //  pasta do projeto
			while(arquivo.hasNextLine()) 	{
				linha = arquivo.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				nome = partes[0];
				email = partes[1];
				participante = new Participante(nome, email);
				this.adicionar(participante);
				//if(partes.length==3) {
				//	idprat = partes[2];		//campo id
				//	prateleira = this.localizarPrateleira(Integer.parseInt(idprat));
				//	prateleira.adicionar(produto);
				//}
			}
			arquivo.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de participantes:"+ex.getMessage());
		}
	}
	public String idReuniao(Participante p) {
	    StringBuilder sb = new StringBuilder();

	    for (Reuniao r : p.getReunioes()) {
	        if (sb.length() > 0)
	            sb.append(",");
	        sb.append(r.getId());
	    }
	    return sb.toString();
	}
	
	public void	gravarObjetos()  {
		//gravar nos arquivos csv os objetos que est�o no reposit�rio
		FileWriter arquivo=null;
		try	{
			File f = new File( new File(".\\participantes.csv").getCanonicalPath())  ;
			arquivo = new FileWriter(f); 
			for(Participante e : participantes) 	{
				if (e instanceof Empregado) {
					arquivo.write(e.getClass().getSimpleName()+";"+e.getNome()+";"+e.getEmail()+";"+e.getSetor()+";"+idReuniao(e)+"\n");
				} else {
					arquivo.write(e.getClass().getSimpleName()+";"+e.getNome()+";"+e.getEmail()+";"+e.getInstituicao()+";"+idReuniao(e)+"\n");
				}
			} 
			arquivo.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na grava��o do arquivo  prateleiras "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\reunioes.csv").getCanonicalPath())  ;
			arquivo = new FileWriter(f) ; 
			for(Reuniao r : reunioes) {
				arquivo.write(r.getId() +";"+ r.getData() +";"+ r.getAssunto()+"\n");	
			} 
			arquivo.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na grava��o do arquivo  produtos "+e.getMessage());
		}
	}
}
