package br.com.agilebr.code;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;

import org.junit.Assert;
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

	@Test
	public void naoDeveCadastrarOperadoraVisaQuandoForCastrarVisaMasBandeiraPassadaForDiferente() {
		Operadora visa = buildOperadora("M", true, null, null);
		Assert.assertFalse(cadastroOperadora.cadastrarVisa(visa));
	}

	@Test
	public void naoDeveCadastrarOperadoraMasterQuandoForCastrarMasterMasBandeiraPassadaForDiferente() {
		Operadora master = buildOperadora("V", true, null, null);
		Assert.assertFalse(cadastroOperadora.cadastrarMaster(master));
	}

	@Test
	public void naoDeveCadastrarOperadoraSodexoQuandoForCastrarSodexoMasBandeiraPassadaForDiferente() {
		Operadora master = buildOperadora("M", true, null, null);
		Assert.assertFalse(cadastroOperadora.cadastrarSodexo(master));
	}

	@Test
	public void deveSerCobradoApenasTaxaAdesaoQuandoPossuirPlanoAnuidadeParaVisa() {
		Operadora visa = buildOperadora("V", true, null, null);
		cadastroOperadora.cadastrarVisa(visa);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertEquals(new BigDecimal(5.5), operadoraCaptor.getValue().getTaxaPagamento());

	}

	@Test
	public void deveSerCobradoTaxaAdesaoMaisAdicionalEmpresaQuandoNaoPossuirPlanoAnuidadeParaVisa() {
		Operadora visa = buildOperadora("V", false, null, null);
		cadastroOperadora.cadastrarVisa(visa);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertTrue(new BigDecimal(11).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);
	}

	@Test
	public void deveSerCobradoTaxaAdesaoQuandoNaoPossuirLiberacaoTaxaParaMaster() {
		Operadora visa = buildOperadora("M", true, false, null);
		cadastroOperadora.cadastrarMaster(visa);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertTrue(new BigDecimal(8).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);

	}

	@Test
	public void naoDeveSerCobradoTaxaAdesaoQuandoPossuirLiberacaoTaxaParaMaster() {
		Operadora visa = buildOperadora("M", true, true, null);
		cadastroOperadora.cadastrarMaster(visa);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertTrue(new BigDecimal(0).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);

	}

	@Test
	public void deveSerCobradoTaxaAdesaoTaxEmpresaTaxaAlimentacaoQuandoForSodexoAlimentacao() {
		Operadora sodexo = buildOperadora("S", true, true, 1);
		cadastroOperadora.cadastrarSodexo(sodexo);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertTrue(new BigDecimal(9.5).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);
	}

	@Test
	public void deveSerCobradoTaxaAdesaoTaxEmpresaQuandoForSodexoRefeicao() {
		Operadora sodexo = buildOperadora("S", true, true, 2);
		cadastroOperadora.cadastrarSodexo(sodexo);
		verify(servicoLegado, atLeastOnce()).cadastrarOperadora(operadoraCaptor.capture());

		Assert.assertTrue(new BigDecimal(7).compareTo(operadoraCaptor.getValue().getTaxaPagamento()) == 0);

	}

	@Test
	public void naoDeveCadastrarOperadoraSodexoQuandoForPassadoUmTipoDeValeInvalido() {
		Operadora master = buildOperadora("S", true, null, 3);
		Assert.assertFalse(cadastroOperadora.cadastrarSodexo(master));
	}

	private Operadora buildOperadora(String bandeira, Boolean planoAnuidade,Boolean liberacaoTaxa, Integer tipoOperadoraSodexo) {
		Operadora operadora = new Operadora();
		operadora.setBandeira(bandeira);
		operadora.setAnuidadeVisa(planoAnuidade);
		operadora.setLiberacaoTaxaMaster(liberacaoTaxa);
		operadora.setTipoCartaoSodexo(tipoOperadoraSodexo);
		return operadora;
	}

}
