# Currency Exchange App (em desenvolvimento)

Um aplicativo Android que permite aos usuários converter valores entre diferentes moedas. O aplicativo consome uma API externa para obter taxas de câmbio em tempo real e oferece uma interface simples e intuitiva para selecionar moedas, inserir valores e visualizar os resultados da conversão.

## Funcionalidades principais

- **Conversão de Moedas:** Permite aos usuários selecionar moedas de origem e destino, inserir um valor e ver o resultado da conversão.
- **Lista de Moedas:** Exibe uma lista de moedas disponíveis, com suporte a pesquisa por nome ou código.
- **Troca de Moedas:** Botão para trocar rapidamente as moedas de origem e destino.
- **Teclado Numérico:** Interface de teclado numérico para facilitar a entrada de valores.
- **Atualização em Tempo Real:** A conversão é atualizada automaticamente conforme o usuário digita o valor.

## Tecnologias utilizadas

- **Kotlin:** Linguagem de programação principal.
- **Android Jetpack:** ViewModel, LiveData e ViewBinding para uma arquitetura limpa e eficiente.
- **Retrofit:** Para consumir a API de conversão de moedas.
- **Corrotinas:** Para operações assíncronas e chamadas de rede.
- **RecyclerView:** Para exibir a lista de moedas de forma otimizada.
- **Material Design:** Componentes de UI modernos e responsivos.
- **Arquitetura MVVM:** Separa a lógica de negócios (Model), a interface de usuário (View) e a comunicação entre elas (ViewModel), promovendo um desenvolvimento modular e escalável.