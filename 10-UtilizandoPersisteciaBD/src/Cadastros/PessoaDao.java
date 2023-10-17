package Cadastros;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// CLASSE COM A CAMADA DE PERSISTENCIA DE DADOS(PessoaDao)
//Ela precisa do DAO para si conectar ao banco
public class PessoaDao extends Dao {
	
	public void incluirPessoa(Pessoa p) throws Exception {
		//Abrindo conexão com o banco
		open();
		stmt = con.prepareStatement("insert into pessoa values(?,?,?)");
		//Abaixo "get" esta recebendo através da chamada do método incluirPessoa(), na classe Menu
		//"set" coloca/insere, os valores na tabela.(BANCO DE DADOS)
		stmt.setInt(1, p.getIdPessoa());
		stmt.setString(2, p.getNomePessoa());
		stmt.setString(3, p.getEmail());
		stmt.execute();
		stmt.close();
		close();
	}

	
	public void alterarPessoa(Pessoa p) throws Exception {
		open();
		stmt = con.prepareStatement("update Pessoa set nomepessoa = ?, email = ? where idPessoa = ?");
		stmt.setString(1, p.getNomePessoa());
		stmt.setString(2, p.getEmail());
		stmt.setInt(3, p.getIdPessoa());
		stmt.execute();
		stmt.close();
		close();
	}

	public void excluirPessoa(Pessoa p) throws Exception {

		open();
		stmt = con.prepareStatement("delete from Pessoa where idPessoa = ?");
		stmt.setInt(1, p.getIdPessoa());
		stmt.execute();
		stmt.close();
		close();

	}
	// retornando um objeto
	public Pessoa consultarPessoaIndividual(int cod) throws Exception {
			open();
			stmt = con.prepareStatement("select * from pessoas where idPessoa = ? ");
			stmt.setInt(1, cod);
			rs = stmt.executeQuery();

			try {
				rs = stmt.executeQuery();			
			}
			catch (SQLException ex) {
				throw new Exception(ex);
			}
			finally {
				System.out.println("Fechando a conexao com banco de dados no Finally");
				close();
			}
			
			Pessoa p = null;
			if (rs != null) {
				if (rs.next()) {
					p = new Pessoa();
					p.setIdPessoa(rs.getInt("idPessoa"));
					p.setNomePessoa(rs.getString("nomePessoa"));
					p.setEmail(rs.getString("email"));				
				}
			}
			close();
			return p;
	}

	public List<Pessoa> ListarPessoas() {
		try {
			open();
			stmt = con.prepareStatement("select * from pessoa");
			rs = stmt.executeQuery();
	        List<Pessoa> listaPessoas = new ArrayList<>();
			//  Enquanto tiver resultado(rs), instancia Pessoa p, set idPessoa, setNomePessoa, setEmailPessoa
			// e adiciona na listaPessoa.add(p).
			while (rs.next()) {
				Pessoa p = new Pessoa();
				p.setIdPessoa(rs.getInt("idPessoa"));
				p.setNomePessoa(rs.getString("nomePessoa"));
				p.setEmail(rs.getString("email"));
				listaPessoas.add(p);
			}
			close();
			return listaPessoas;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}