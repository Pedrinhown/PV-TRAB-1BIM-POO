package controle;

import java.util.Date;
import java.util.List;

import dao.ProdutoDAO;
import negocio.Produto;
import view.Menu;


public class ProdutoController {
	
	
	
	public int Gravar(Produto produto, boolean trabalho) throws Exception{	
		int retorno = 0;
		
		try 
		{
			if(produto == null) {
				throw new Exception("Nenhum produto informado para gravação");
			}
			
			if(produto != null) 
			{	
				if (produto.getNome_produto().equals(""))
				{
					throw new Exception("Informe um nome para o Produto");
				}
				
				if (produto.getValor_un() <= 0)
				{
					throw new Exception("Informe um valor valido para o produto");
				}		
				
				if (produto.getQtd_atual() <= 0) 
				{
					throw new Exception("Informe uma quantidade valida para o produto");
				}
				
				if (trabalho == true) 
				{
					produto.setCod_produto(Menu.listaProduto.size() + 1);
					
					produto.setData_cadastro(new Date());
					
					Menu.listaProduto.add(produto);
				}
				else 
				{					
					retorno = new ProdutoDAO().Insert(produto);
				}
			}		
		} 
		
		catch (Exception e) {
		throw e;
		
		}
		return retorno;
	}
	
	public List<Produto> Listar(boolean trabalho) throws Exception{
		List<Produto> listaProduto = null;
		
		try
		{
			if (trabalho == true) 
			{
				listaProduto = Menu.listaProduto;
			}
			else 
			{
				listaProduto = new ProdutoDAO().Listar();
			}
		} 		
		catch (Exception e)
		{		
			throw e;
		}
		return listaProduto;
				
	}
	
	public Produto CarregarPorCod(int cod, boolean trabalho) throws Exception{
		Produto produto = null;
		try {
			if (cod > 0)
			{
				if (trabalho == true)
				{
					produto = Menu.listaProduto.stream().filter(x->x.getCod_produto() == cod).findFirst().orElse(null);
				}
				else 
				{					
					produto = new ProdutoDAO().buscarPorId(cod);
				}
			}
			else
			{
				throw new Exception("Informe o código do produto");
			}
		} 
		
		catch (Exception e) {
			throw e;
		}
		
		return produto;
	}

	public int Editar(Produto produto, boolean trabalho) throws Exception {				
		int retorno = 0;
		try {
			if (produto != null)
			{
				if (produto.getCod_produto() <= 0)
				{
					throw new Exception("Código de produto invalido");
				}
				
				if (trabalho == true) 
				{
				    if(Menu.listaProduto.removeIf(x->x.getCod_produto() == produto.getCod_produto()) == true)
				    {
				    	if (Menu.listaProduto.add(produto) == true)
				    	{							
				    		retorno = 1;
						}
				    }
				    else
				    {
				    	throw new Exception("Não foi possível atualizar o produto na lista");
					}
				    
				}
				else
				{
					retorno = new ProdutoDAO().Atualizar(produto);	
				}
			}
			else
			{
				throw new Exception("Informe o código do produto");
			}
		} 
		
		catch (Exception e) {
			throw e;
		}
		return retorno;
	}
	
	public int Excluir(int cod, boolean trabalho) throws Exception {
		int retorno = 0;
		try {
			if (cod > 0)
			{
				if (trabalho == true) 
				{
					Menu.listaProduto.remove(Menu.listaProduto.stream().filter(x->x.getCod_produto() == cod).findFirst().orElse(null));
					
				}
				else
				{
					retorno = new ProdutoDAO().Excluir(cod);
				}
			}
			else
			{
				throw new Exception("Informe o código do produto");
			}
		} 
		
		catch (Exception e) {
			throw e;
		}
		
		return retorno;
	}
}
