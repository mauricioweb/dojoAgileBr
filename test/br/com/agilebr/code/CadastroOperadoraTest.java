package br.com.agilebr.code;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

public class CadastroOperadoraTest {

	private CadastroOperadora cadastroOperadora;
	@Mock
	private ServicoLegado servicoLegado;
	@Captor
	private ArgumentCaptor<Operadora> operadoraCaptor;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		cadastroOperadora = new CadastroOperadora(servicoLegado);
	}

	@Test(expected = ServicoException.class)
	public void naoDeveCadastrarOperadoraVisaQuandoForCastrarVisaMasBandeiraPassadaForDiferente() {
		Operadora master = buildOperadora("M", true, null);
		cadastroOperadora.cadastrarVisa(master);
	}

	@Test(expected = ServicoException.class)
	public void naoDeveCadastrarOperadoraMasterQuandoForCastrarMasterMasBandeiraPassadaForDiferente() {
		Operadora visa = buildOperadora("V", true, null);
		cadastroOperadora.cadastrarMaster(visa);
	}


	@Test
	public void deveSerCobradoApenasTaxaAdesaoQuandoPossuirPlanoAnuidadeParaVisa() {
		Operadora visa = buildOperadora("V", true, null);
		cadastroOperadora.cadastrarVisa(visa);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		assertEquals(new BigDecimal(5.5), operadoraCaptor.getValue().getTaxaPagamento());

	}

	@Test
	public void deveSerCobradoTaxaAdesaoMaisAdicionalEmpresaQuandoNaoPossuirPlanoAnuidadeParaVisa() {
		Operadora visa = buildOperadora("V", false, null);
		cadastroOperadora.cadastrarVisa(visa);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		assertEquals(new BigDecimal("11.0"), operadoraCaptor.getValue().getTaxaPagamento());
	}

	@Test
	public void deveSerCobradoTaxaAdesaoQuandoNaoPossuirLiberacaoTaxaParaMaster() {
		Operadora master = buildOperadora("M", true, false);
		cadastroOperadora.cadastrarMaster(master);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		assertEquals(new BigDecimal("8.0"), operadoraCaptor.getValue().getTaxaPagamento());

	}

	@Test
	public void naoDeveSerCobradoTaxaAdesaoQuandoPossuirLiberacaoTaxaParaMaster() {
		Operadora master = buildOperadora("M", true, true);
		cadastroOperadora.cadastrarMaster(master);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		assertEquals(new BigDecimal(0),operadoraCaptor.getValue().getTaxaPagamento());

	}


	private Operadora buildOperadora(String bandeira, Boolean planoAnuidade,Boolean liberacaoTaxa) {
		Operadora operadora = new Operadora();
		operadora.setBandeira(bandeira);
		operadora.setAnuidadeVisa(planoAnuidade);
		operadora.setLiberacaoTaxaMaster(liberacaoTaxa);
		return operadora;
	}

}
