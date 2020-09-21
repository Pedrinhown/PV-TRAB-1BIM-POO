package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.ConexaoMySql;
import negocio.Produto;

public class ProdutoDAO extends ConexaoMySql{
	
	// INSERT		
	final String SQL_INSERT_PRODUTO = "INSERT INTO produto(nome_produto, valor_un, qtd_atual, data_cadastro)"
			+ " VALUES (?, ?, ?, ?)";
	// SELECT	
	final String SQL_SELECT_PRODUTO = "SELECT * FROM produto";
	
	// SELECT BY ID
	final String SQL_SELECT_PRODUTO_ID = "SELECT * FROM produto WHERE cod_produto = ?";
	
	// UPDATE BY ID 
	final String SQL_UPDATE_PRODUTO = "UPDATE produto SET nome_produto=?, valor_un=?, qtd_atual=? WHERE cod_produto=?";
	
	// DELETE BY ID
	final String SQL_DELETE_PRODUTO ="DELETE FROM produto WHERE cod_produto=?";

	public int Insert(Produto produto) {				
		int qtd = 0;
		
		try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_INSERT_PRODUTO);){
			pst.setString(1, produto.getNome_produto());
			pst.setFloat(2, produto.getValor_un());
			pst.setDouble(3, produto.getQtd_atual());
			
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
			String currentDateTime = format.format(date);
			
			pst.setString(4, currentDateTime);
			qtd = pst.executeUpdate();
			
		}
				
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return qtd;
	}
	
	// SELECT 

	public List<Produto> Listar() {
		List<Produto> lstProdutos = new ArrayList<Produto>();
		
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PRODUTO);){
			
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Produto produto = new Produto(); 
			
			produto.setCod_produto(rs.getInt("cod_produto"));
			produto.setNome_produto(rs.getString("nome_produto"));
			produto.setValor_un(rs.getFloat("valor_un"));
			produto.setQtd_atual(rs.getDouble("qtd_atual"));
			produto.setData_cadastro(rs.getDate("data_cadastro"));
			
			lstProdutos.add(produto);			
		}
	} 	
	catch (SQLException e) {
		e.printStackTrace();
	}			
	return lstProdutos;
}

	public Produto buscarPorId(int cod) {
		Produto produto = null;
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PRODUTO_ID );){
			
			pst.setInt(1, cod);
			ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
		    produto = new Produto(); 
			
			produto.setCod_produto(rs.getInt("cod_produto"));
			produto.setNome_produto(rs.getString("nome_produto"));
			produto.setValor_un(rs.getFloat("valor_un"));
			produto.setQtd_atual(rs.getDouble("qtd_atual"));
			
			produto.setData_cadastro(rs.getDate("data_cadastro"));
						
		}
	} 	
	catch (SQLException e) {
		e.printStackTrace();
	}	
		return produto;
}

	public int Atualizar(Produto produto) {
		
		int qtd = 0;
		
		try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_PRODUTO);){
			pst.setString(1, produto.getNome_produto());
			pst.setFloat(2, produto.getValor_un());
			pst.setDouble(3, produto.getQtd_atual());
			pst.setInt(4, produto.getCod_produto());
			
			qtd = pst.executeUpdate();
			
		}
				
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return qtd;
	}
	
	public int Excluir(int cod) {
		
		int qtd = 0;
		
		try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_DELETE_PRODUTO);){
			pst.setInt(1, cod);
			
			pst.executeUpdate();			
		}
				
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return qtd;
	}
	
	
}
