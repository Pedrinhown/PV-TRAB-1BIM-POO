package view.controllers;

import java.util.List;
import java.util.Optional;

import controle.ProdutoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import negocio.Produto;

public class MenuController {

    @FXML
    private Button btnProduto;

    @FXML
    private Button btnSair;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtVlrUni;

    @FXML
    private TextField txtQtd;

    @FXML
    private Button btnGravar;
    
    @FXML
    private TextArea txtAreaListProduto;

    @FXML
    private TextField txtCodProd;
    
    @FXML
    private Button btnBuscar;
    
    @FXML
    private Label lblCodProduto;
    
    @FXML
    private Button btnAtualizar;
    
    @FXML
    private Button btnExcluir;
    
    @FXML
    private RadioButton optProva;

    @FXML
    private ToggleGroup gpOpt;

    @FXML
    private RadioButton optTrabalho;
    
    @FXML
    void btnExcluir_Action(ActionEvent event) {
    	Produto produto = null;
    	boolean trabalho = false;
    	
    	try 
    	{
		if (lblCodProduto.getText().equals(""))
		{
		throw new Exception("Informe o código do produto que deseja excluir");
		}
		else
		{
		produto = pegaDadosCod();
		}
		
		if (optTrabalho.isSelected() == true)
		{
			trabalho = true;
		}
		else if(optProva.isSelected() == true)
		{
			trabalho = false;
		}
		
   		if(new ProdutoController().Excluir(produto.getCod_produto(), trabalho) == 1) {
   			listarProdutos(trabalho);
   			LimparCampos();
   		}		
    }
    	
    	catch (Exception e)
    	{
    		Alert alert = new Alert(AlertType.WARNING);

            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            
            alert.showAndWait();
		}  	
    }

    @FXML
    void btnAtualizar_Action(ActionEvent event) {
    	boolean trabalho = false;
    	
    	try {
    		try 
    		{
    			Float.parseFloat(txtVlrUni.getText());
            }
    		catch (NumberFormatException e) {
    			throw new Exception("Informe um valor valido");
            }
    		
    		try
    		{
				Double.parseDouble(txtQtd.getText());
			} 
    		catch (Exception e) {
    			throw new Exception("Informe uma quantidade valida");
			}
    		Produto produto = pegaDadosCod();        	
    		
    		if (produto != null)
    		{
    			if (optTrabalho.isSelected() == true)
    			{
    				trabalho = true;
    			}
        		else if(optProva.isSelected() == true)
        		{
    				trabalho = false;
    			}
    			new ProdutoController().Editar(produto, trabalho);	
			}
    		else
    		{
    			throw new Exception("Não foi possível carregar o produto para edição");
			}
		}
    	catch (Exception e) {
    		Alert alert = new Alert(AlertType.WARNING);

            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            
            alert.showAndWait();	
    	}
    	finally 
    	{			
    		listarProdutos(trabalho);	
		}
    }

    @FXML
    void btnBuscar_Action(ActionEvent event) {
    	boolean trabalho = false;
    	String codProduto = txtCodProd.getText();
    	Produto produto = null;
    	
    	if (!codProduto.equals("")) {
			try {
				if (optTrabalho.isSelected() == true)
				{
					trabalho = true;
				}
	    		else if(optProva.isSelected() == true)
	    		{
					trabalho = false;
				}
				int cod = Integer.valueOf(codProduto);
				produto = new ProdutoController().CarregarPorCod(cod, trabalho);
				
				if (produto != null) {
					lblCodProduto.setText(produto.getCod_produto() + "");
					txtDescricao.setText(produto.getNome_produto());
					txtVlrUni.setText(produto.getValor_un() + "");
					txtQtd.setText(produto.getQtd_atual() + "");
				}
				else
				{
					throw new Exception("Não foi possivel carregar o produto com o código informado");
				}
			}
			
			catch (Exception e) {
	    		Alert alert = new Alert(AlertType.WARNING);

	            alert.setTitle("Atenção");
	            alert.setHeaderText(e.getMessage());
	            
	            alert.showAndWait();
			}
		}
    }
    

    @FXML
    void btnGravar_Action(ActionEvent event) {	
    	boolean trabalho = false;
    	try {
    		
    		try 
    		{
    			Float.parseFloat(txtVlrUni.getText());
            }
    		catch (NumberFormatException e) {
    			throw new Exception("Informe um valor valido");
            }
    		
    		try
    		{
				Double.parseDouble(txtQtd.getText());
			} 
    		catch (Exception e) {
    			throw new Exception("Informe uma quantidade valida");
			}
    		
    		if (optTrabalho.isSelected() == true)
    		{
    			trabalho = true;
    		}
    		else if(optProva.isSelected() == true)
    		{
				trabalho = false;
			}
    		
    		new ProdutoController().Gravar(new Produto(txtDescricao.getText(), Float.parseFloat(txtVlrUni.getText()), Double.parseDouble(txtQtd.getText())), trabalho);
    		listarProdutos(trabalho);		
		}
    	catch (Exception e) {
    		Alert alert = new Alert(AlertType.WARNING);

            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            
            alert.showAndWait();	
    	}
    }

    @FXML
    void btnProduto_Action(ActionEvent event) {

    }

    @FXML
    void btnSair_Action(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	
    	alert.setTitle("Sair");
    	alert.setHeaderText("Deseja realmente sair?");  	
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	 if (result.isPresent() && result.get() == ButtonType.OK) {
    	     System.exit(0);
    	 }    	
    }
    
	private void LimparCampos() {
		txtDescricao.clear();
		txtQtd.clear();
		txtVlrUni.clear();
		lblCodProduto.setText("");
		txtDescricao.requestFocus();
	}
    
    private Produto pegaDadosCod() {
    	
    	return new Produto(Integer.valueOf(lblCodProduto.getText()), txtDescricao.getText(), Float.parseFloat(txtVlrUni.getText()), Double.parseDouble(txtQtd.getText()));
    }
    
    private void listarProdutos(boolean trabalho) {
    	txtAreaListProduto.clear();
    	
    	try {    		   		
        	List<Produto> lstProduto = new ProdutoController().Listar(trabalho);
        	lstProduto.forEach(produto -> { txtAreaListProduto.appendText(produto.toString() + "\n");
            });	
        	LimparCampos();
		}    	
    	catch (Exception e) {
    		Alert alert = new Alert(AlertType.WARNING);

            alert.setTitle("Atenção");
            alert.setHeaderText(e.getMessage());
            
            alert.showAndWait();
		}   	
    	
    }
    
}



