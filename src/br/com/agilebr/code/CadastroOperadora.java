package br.com.agilebr.code;


import java.math.BigDecimal;

public class CadastroOperadora {
	
	private ServicoLegado servicoLegado;
	
	

	public CadastroOperadora(ServicoLegado servicoLegado) {
		this.servicoLegado = servicoLegado;
	}

	public boolean cadastrarVisa(Operadora visa){
		if(!visa.getBandeira().equals("V"))
			return false;
		BigDecimal taxaPagamento = new BigDecimal(0);
		if(visa.getAnuidadeVisa()){
			//para quem possuir plano anual não eh cobrado a taxa adicional da empresa
			taxaPagamento = new BigDecimal(5.5);
		}else{
			//para quem não possuir plano anual eh cobrado a taxa adicional da empresa
			taxaPagamento = new BigDecimal(5.5).add(new BigDecimal(5.5));
		}
		visa.setTaxaPagamento(taxaPagamento);
		servicoLegado.cadastrarOperadora(visa);
		return true;
	}
	

	public boolean cadastrarMaster(Operadora master){
		if(!master.getBandeira().equals("M"))
			return false;
		BigDecimal taxaPagamento = new BigDecimal(0);
		if(!master.getLiberacaoTaxaMaster()){
			// se não possuir liberação da taxa é cobrada a taxa com o adicional da empresa
			taxaPagamento = new BigDecimal(5.5).add(new BigDecimal(2.5));
		}
		master.setTaxaPagamento(taxaPagamento);
		servicoLegado.cadastrarOperadora(master);
		return true;
	}

}
