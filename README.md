# MovieImdb

O aplicativo “MovieImdb” é uma lista de filmes listados na API do imdb, onde você pode listar filmes para ter acesso ao repositório público do mesmo.

O aplicativo consume a API ([https://www.omdbapi.com](https://www.omdbapi.com)) com uma api key e foi desenvolvido seguindo os princípios do MVVM + Clean Architecture, separando em camadas de `Data`, `Domain` e `Presenter`, sendo este ultimo dividido entre `View e ViewModel`.

- A camada de `Data` utiliza as bibliotecas Retrofit, para consumir a API externa.

- A camada de `Domain` foi implementado os `UseCases` com as regras que acessam a camada `Data` em busca dos dados externos. Realizando as tarefas de consulta de dados utilizando Coroutines, na thread IO e retornar os dados para thread MAIN com mapeamento dos tipos de erros.

- A camada de `Presenter` é responsável por toda a parte visual do aplicativo, como Activities, Fragments, ViewModels e etc. Foi implementado seguindo os padrões de programação reativa (LiveData e ViewState e Flow, para streaming de dados e podendo gerar uma paginação automática).
  Também utilizando algumas bibliotecas como:
    - ViewBinding: Para o binding direto dos modelos de dados com as interfaces visuais.
    - Glide: Carregamento de imagens.
    - Koin: Injeção de dependências.
    - Material: Componentes de UI do Android.
    - Flow: Sistema de fluxo de dados com paginação automática.
    - Navigation Components: Para navegação dinâmica com argumentos entre fragmentos e activities.

O aplicativo foi desenvolvido utilizando a linguagem Kotlin e toda as comunições entre as camadas são feitas através de Coroutines. Foi feito um módulo exclusivo para requisições de internet, chamado MSHttp.


## Como utilizar
Insira sua API KEY no arquivo Constants.kt, execute o app e pesquise um filme. Ao achar, veja os detalhes do mesmo e se divirta!
Abra o app e pesquise por algum filme. Ao achar, clique no filme e veja seus detalhes.

## Imagens

Light mode <br>
<img src="https://user-images.githubusercontent.com/30272949/239996225-8a606338-5482-430f-b919-5c96856c13c7.png" width="250">
<img src="https://user-images.githubusercontent.com/30272949/239995626-f430386e-5b26-4e74-895d-a77bb17bea4e.png" width="250">
<img src="https://user-images.githubusercontent.com/30272949/239995738-59de7470-89de-4254-9e04-8e022f58f016.png" width="250">

Dark mode<br>
<img src="https://user-images.githubusercontent.com/30272949/239996100-8be6f3e4-4565-4dc1-9e07-5e9470a16bdc.png" width="250">
<img src="https://user-images.githubusercontent.com/30272949/239995936-425ddcb1-515f-434b-a3ec-b857bc068105.png" width="250">
<img src="https://user-images.githubusercontent.com/30272949/239996004-15d38173-03b6-47cd-88f7-1eb030eaf3f6.png" width="250">


