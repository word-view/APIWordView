# API WordView
O principal objetivo da API é fornecer letras de músicas e legendas de vídeos enriquecidas com dados gerados
pela biblioteca [gengolex](https://github.com/word-view/gengolex). A API retorna para o aplicativo um JSON contendo tanto o texto original quanto uma 
decomposição estruturada de todas as palavras presentes nele.

Com essas informações, o aplicativo realiza requisições adicionais à API para obter representações em 
imagem de cada palavra, possibilitando a geração de visualizações do texto e lições.

Além disso, a API inclui um sistema completo de criação de contas e autenticação de usuários.

# Execução
```sh
  # Clone o repositório
  git clone https://github.com/word-view/APIWordView

  # Entre na pasta
  cd APIWordView/

  # Execute
  ./mvnw spring-boot:run
```

# Execução (JAR)
```sh
  # Baixar pelas releases
  # E executar com --prod (produção) ou --dev
  java -jar ./wordview-0.0.4-SNAPSHOT-exec.jar --prod
```