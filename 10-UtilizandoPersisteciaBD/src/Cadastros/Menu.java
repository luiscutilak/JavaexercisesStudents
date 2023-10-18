package Cadastros;

import java.util.List;

public class Menu {
	public static void main(String[] args) {
		PessoaDao pd = new PessoaDao();

		try {
			// Abaixo passando os dados para a classe Pessoa através do construtor(** super() **) 
			// Depois de construido o objeto pessoa(p1, p2) linhas 13,14
			// É chamado os métodos da classe PessoaDao para incluir os dois objetos "p1,p2" no banco de dados.
			// Incluir uma pessoa

			Pessoa p1 = new Pessoa(3,"Maria", "maria@gmail.com");
			Pessoa p2 = new Pessoa(4,"Lucia", "lucia@gmail.com");
			pd.incluirPessoa(p1);
			pd.incluirPessoa(p2);
		
			
			// Alterar uma pessoa
			// - Alterar a informa��o desejada usando o set(Classe encapsulada)

			
			try {
				Pessoa pes = pd.consultarPessoaIndividual(1);
			
				// se o resultado da consulta a pessoa retornar null e porque nao encontrou
				// a pessoa ou seja se, pes diferente de null, entra no if
				if (pes != null) {
						pes.setEmail("joao@gmail.com");
						// chamar o metodo que contem o comando update
						pd.alterarPessoa(pes);
				} else {
					System.out.println("Pessoa nao encontrada ou falha no acesso ao banco de dados");
				}
				
				// Listando todas as pessoas
				List<Pessoa> listaPessoas = pd.ListarPessoas();
				for(Pessoa p : listaPessoas){
					System.out.println("Id.: " + p.getIdPessoa());
					System.out.println("Nome : "  +p.getNomePessoa());
					System.out.println("Email : " +p.getEmail());
				}
				 
			}
			
			catch (Exception ex ){
		        System.out.println("Alguma coisa deu errado no acesso ao banco de dados. Contate a TI");
			}
 			
			// Excluir uma pessoa    
			// verificar se a pessoa existe por codigo
			Pessoa pessoa1 = pd.consultarPessoaIndividual(1);
			// chamar o metodo que contem o comando delete
			pd.excluirPessoa(pessoa1);
			// Listando todas as pessoas
			// Lista retornada do while dentro da classe PessoaDao
			// chamada através do método ListarPessoas()
			List<Pessoa> listaPessoas = pd.ListarPessoas();
			for(Pessoa p : listaPessoas){
				System.out.println("Id.: " + p.getIdPessoa());
				System.out.println("Nome : "  +p.getNomePessoa());
				System.out.println("Email : " +p.getEmail());
			}
			

		} catch (Exception e) {
			System.out.println("Error:  " + e.getMessage());
		}		
		
	}
}