package model;

import java.util.Date;

public class Celular {
	private int id;
	private String modelo;
	private String cor;
	private int armazenamentoGB;
	private double preco;
	private Date dataLancamento;
	private Marca marca;

	public Celular() {
		this(0);
	}

	public Celular(int id) {
		this.id = id;
		this.modelo = "";
		this.cor = "";
		this.armazenamentoGB = 0;
		this.preco = 0.0;
		this.dataLancamento = null;
		this.marca = new Marca();
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getArmazenamentoGB() {
		return armazenamentoGB;
	}

	public void setArmazenamentoGB(int armazenamentoGB) {
		this.armazenamentoGB = armazenamentoGB;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
}