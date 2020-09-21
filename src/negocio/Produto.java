package negocio;

import java.util.Date;

public class Produto {

	private int cod_produto;
	private String nome_produto;
	private float valor_un;
	private double qtd_atual;
	private Date data_cadastro;
		
	public Produto() {
		
	}
	
	public Produto(String nome_produto, float valor_un, double qtd_atual) {
		super();
		this.nome_produto = nome_produto;
		this.valor_un = valor_un;
		this.qtd_atual = qtd_atual;
	}
	
	
	public Produto(int cod_produto, String nome_produto, float valor_un, double qtd_atual) {
		super();
		this.cod_produto = cod_produto;
		this.nome_produto = nome_produto;
		this.valor_un = valor_un;
		this.qtd_atual = qtd_atual;
	}

	public int getCod_produto() {
		return cod_produto;
	}
	public void setCod_produto(int cod_produto) {
		this.cod_produto = cod_produto;
	}
	public String getNome_produto() {
		return nome_produto;
	}
	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}
	public float getValor_un() {
		return valor_un;
	}
	public void setValor_un(float valor_un) {
		this.valor_un = valor_un;
	}
	public double getQtd_atual() {
		return qtd_atual;
	}
	public void setQtd_atual(double qtd_atual) {
		this.qtd_atual = qtd_atual;
	}
	public Date getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_produto;
		result = prime * result + ((data_cadastro == null) ? 0 : data_cadastro.hashCode());
		result = prime * result + ((nome_produto == null) ? 0 : nome_produto.hashCode());
		long temp;
		temp = Double.doubleToLongBits(qtd_atual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(valor_un);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (cod_produto != other.cod_produto)
			return false;
		if (data_cadastro == null) {
			if (other.data_cadastro != null)
				return false;
		} else if (!data_cadastro.equals(other.data_cadastro))
			return false;
		if (nome_produto == null) {
			if (other.nome_produto != null)
				return false;
		} else if (!nome_produto.equals(other.nome_produto))
			return false;
		if (Double.doubleToLongBits(qtd_atual) != Double.doubleToLongBits(other.qtd_atual))
			return false;
		if (Float.floatToIntBits(valor_un) != Float.floatToIntBits(other.valor_un))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Produto [cod_produto=" + cod_produto + ", nome_produto=" + nome_produto + ", valor_un=" + valor_un
				+ ", qtd_atual=" + qtd_atual + ", data_cadastro=" + data_cadastro + "]";
	}
	
}
