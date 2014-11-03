package br.com.agilebr.code;


import java.math.BigDecimal;

public class CadastroOperadora {
	
	private ServicoLegado servicoLegado;
	
	

	public CadastroOperadora(ServicoLegado servicoLegado) {
		this.servicoLegado = servicoLegado;
	}

	public void cadastrarVisa(Operadora visa){
		if(!visa.getBandeira().equals("V"))
			throw new ServicoException("Operadora inválida");
		BigDecimal taxaPagamento = new BigDecimal(0);
		if(visa.getAnuidadeVisa()){
			//para quem possuir plano anual não é cobrado a taxa adicional da empresa
			taxaPagamento = new BigDecimal(5.5);
		}else{
			//para quem não possuir plano anual é cobrado a taxa adicional da empresa
			taxaPagamento = new BigDecimal(5.5).add(new BigDecimal(5.5));
		}
		visa.setTaxaPagamento(taxaPagamento);
		servicoLegado.cadastrarOperadora(visa);
	}
	

	public void cadastrarMaster(Operadora master){
		if(!master.getBandeira().equals("M"))
			throw new ServicoException("Operadora inválida");
		
		BigDecimal taxaPagamento = new BigDecimal(0);
		if(!master.getLiberacaoTaxaMaster()){
			// se não possuir liberaçã da taxa é cobrada a taxa com o adicional da empresa
			taxaPagamento = new BigDecimal(5.5).add(new BigDecimal(2.5));
		}
		master.setTaxaPagamento(taxaPagamento);
		servicoLegado.cadastrarOperadora(master);
	}

}
