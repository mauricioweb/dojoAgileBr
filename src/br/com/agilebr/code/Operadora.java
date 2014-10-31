package br.com.agilebr.code;

import java.math.BigDecimal;

public class Operadora {

	/**
	 * V - Visa
	 * M - Master
	 */
	private String bandeira;
	private Boolean anuidadeVisa;
	private Boolean liberacaoTaxaMaster;
	private BigDecimal taxaPagamento;
	
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	public Boolean getAnuidadeVisa() {
		return anuidadeVisa;
	}
	public void setAnuidadeVisa(Boolean anuidadeVisa) {
		this.anuidadeVisa = anuidadeVisa;
	}
	public Boolean getLiberacaoTaxaMaster() {
		return liberacaoTaxaMaster;
	}
	public void setLiberacaoTaxaMaster(Boolean liberacaoTaxaMaster) {
		this.liberacaoTaxaMaster = liberacaoTaxaMaster;
	}

	public void setTaxaPagamento(BigDecimal taxaPagamento) {
		this.taxaPagamento = taxaPagamento;
	}
	public BigDecimal getTaxaPagamento() {
		return taxaPagamento;
	}
}
