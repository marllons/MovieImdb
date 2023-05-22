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
Abra o app e pesquise por algum usuário. Ao achar, clique no usuário e veja seus detalhes, bem como seus repositórios.

## Imagens

Default theme <br>
<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/30272949/238058249-8e44e180-4e8d-4a9e-94e4-6806a504c98c.png" width="250">
<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/30272949/238058344-724b40f2-01eb-4a61-ae48-d42712b70aa3.png" width="250">
<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/30272949/238058399-1de7bc56-62b0-4ce6-b2af-f3af56c7a4d0.png" width="250">
<img src="https://user-images.githubusercontent.com/30272949/238058475-1db2efc1-6a80-480c-900d-3caac4a556cb.png" width="250">
