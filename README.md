# idwall
App para participação do desafio da idwall

[apk](https://drive.google.com/open?id=1swxOSIluJnZYuuPElaisQ_2eQrq2yNDn)

## Como executar os testes
- Execute o teste LoginActivityTest sem ter validado qualquer email.
- Execute o teste FeedActivityTest depois de ter validado um email.
- Execute o teste DogExpandidoActivity normalmente.

É necessário fazer destar forma porque o token é salvo e por isso o app verifica se existe, se houver a tela de feed é apresentada se não a tela de login é apresentada.

## Para executar o projeto
- Utilizar o Android Studio e fazer os procedimentos padrões;
- As informações de assinatura estão juntas com o projeto para a criarção de um release, mas é claro você pode criar a sua própria assinatura;

## Bibliotecas utilizadas e motivos
- **Retrofit:** Além da afinidade, ela é excencial, pois encapsula toda a parte estrutural, dessa forma eu dediquei mais tempo ao que realmente importa no desenvolvimento do app;
- **okhttp3:logging-interceptor:** Utilizei apenas para gerar o log de requisição e resposta da api, me ajudou a fazer o devido uso da api de cachorros.
- **okhttp3:mockwebserver:** Utilizei para criar as resposta dos serviços para os testes;
- **Picasso:** acredito também ser uma das libs excenciais, pois ao utiliza-la não precisei me preocupar com o tratamento das imagens e o cache das mesmas e tudo isso com apenas uma linha, porém não tenho total confiança no que acebei de dizer, pois conheço a lib superficialmente;

## Dificuldades
- Tive várias dificuldade para criar os teste, pois não tinha experiência alguma e precisei estudar tutorias na internet e em aulas da plataforma Alura;
- Tive dificuldade para criar uma imagem expandida dos cães ao clicar em uma delas, acabei criando outra activity para exibir apenas a imagem clicada.

