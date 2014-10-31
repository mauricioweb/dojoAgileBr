#Agile Brazil 2014 - Let's Clean the code !


Este workshop consiste em realizar um refactoring neste código legado no qual apresenta varios ponto de refatoração ao qual chamamos de "Bad Smells". Nós iremos lhe dar uma "ajudinha" e vamos disponibilizar este código coberto por testes.


##Problema:

Nós somos uma empresa que trabalhamos com autorização online para  que as empresas de comércio eletrônico permitirem o pagamento por cartão de crédito. Como estamos começando neste nicho de négocio, por enquanto só temos duas bandeiras cadastradas Visa e Mastercard onde cada uma delas possui suas regras específicas.

Nosso négocio vem dando muito certo, e novas bandeiras estão por surgir. Neste momento precisamos também aceitar a American Express porque temos muitos clientes solicitando este tipo de pagamento.

**Para Visa possuímos a segiunte regra:**

1. Quem possui plano anual a cobrança é de 5.5%
2. Quem não possui plano anual a cobrança é de 5.5% mais uma taxa adicional de 2.5%

**Para Mastercard possuímos a seguinte regra:**

1. Quem possui isenção não paga nenhuma taxa
2. Quem não possuir isenção, a cobrança é de 5.5% mais uma taxa adicional de 2.5%

**Para o American Express precisamos da seguinte regra:**

1. Taxa única de 8%


Para iniciar nosso projeto precisamos:

1. Baixe o projeto na sua máquina
2. Importe ele para dentro do Eclipse
3. Rode os testes e confirme que todos os testes estão passando
4. Let's Code !

*Dica: Abuse e use o Pair Programming!*
