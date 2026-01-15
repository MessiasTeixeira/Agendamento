/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o orientada a objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package aplicacaoConsole;

import requisito.Fachada;

public class Apagar {

	public Apagar() {
	System.out.println("---------apagando reuniao 1-----");
		try {
			Fachada.cancelarReuniao(1);
		}
		catch(Exception e){
			System.out.println("-->" + e.getMessage());
		}

	}


	public static void main(String[] args){
		new Apagar();
	}
}
