# Teste de Caixa Branca

## Descrição da Atividade

O teste de Caixa Branca faz parte da avaliação da matéria de UX/UI do quarto semestre do curso de Análise e Desenvolvimento de Sistemas. Este exercício tem como objetivo testar um código e verificar se suas funcionalidades estão sendo implementadas corretamente, testando os possíveis cenários criados.

É importante ressaltar que não foi informado quais versões de ferramentas, drivers e do próprio Java foram utilizados. No entanto, foi utilizado o Java 17 para a execução do código, o que pode impactar a compatibilidade e influenciar as sugestões de melhorias.


# **Erros identificados**


## **1 - Driver MySQL :** 

Foi verificado que a classe "Driver" do MySQL foi erroneamente importada na linha 12:

- **"com.mysql.Driver.Manager"**

Melhorias recomendadas:

- Versões anteriores ao MySQL Connector/J 8.0: "com.mysql.jdbc.Driver".
  
- Versões superiores ao MySQL Connector/J 8.0: "com.mysql.cj.jdbc.Driver".


## **2 - NewInstance :** 

Foi identificado que este comando presente na linha 12 foi depreciado a partir da versão 9 do Java. Caso esteja utilizando versões superiores a 9 é indicado que o comando seja removido :

- **"newInstance()"**

## **3 - Ausência de porta na URL :** 

Não foi especificada a porta de conexão com o banco de dados na URL, o que pode causar problemas de conexão caso o banco de dados esteja configurado para usar uma porta diferente da padrão. É recomendado incluir a porta correta para garantir a comunicação adequada com o banco de dados.

## **4 - Parâmetros da Url :** 

A URL não inclui parâmetros essenciais como useSSL e serverTimezone. Esses parâmetros são importantes para garantir a segurança da conexão e para evitar problemas de compatibilidade com o fuso horário, especialmente ao conectar a um bancos de dados em ambiente de produção.

## **5 - Tratamento de exceções :** 

Observa-se que nas linhas 15 e 33 foi omitido o tratamento dos erros, dificultando a identificação de possíveis falhas. O recomendado seria adicionar logs ou mensagens de erro para identificar os problemas. Erro identificado:

- **"catch (Exception e) { }"**

Também é possível observar o uso direto da classe genérica Exception. Utilizar uma classe genérica para o tratamento de exceções pode resultar em uma abordagem incorreta para lidar com erros específicos, o que pode levar o programa a operar em um estado inconsistente, aumentando o risco de comportamentos inesperados ou de falhas difíceis de diagnosticar e corrigir.


## **6 - Verificar conexão :** 

A função definida na linha 9, chamada "conectarBD", não realiza a verificação da validade da conexão. Isso transfere a responsabilidade de garantir que a conexão seja válida para quem chama a função, o que pode levar a uma implementação incorreta ou a falhas no uso da conexão.

## **7 - Uso inadequado de váriavel global :** 

Ao definir uma variável como "public", ela pode ser acessada e modificada diretamente por outras classes do sistema, o que pode comprometer a integridade e o controle sobre os dados. 

O ideal seria definir a variável como local ou utilizá-la com o modificador private, caso seja necessário acessá-la ou modificá-la fora da classe, é recomendado fornecer métodos específicos como getters e setters, garantindo um controle mais seguro sobre seu uso e evitando alterações indesejadas.

## **8 - Vulnerabilidade a MySQL Injection :** 

A String "Sql" definida na linha 20 e preenchida nas linhas 23, 24 e 25 incluem comandos SQL que serão executados na linha 28. Essa abordagem de consulta pode ser vulnerável a comandos maliciosos inseridos pelo usuário.

Uma abordagem mais recomendada seria a utilização de "PreparedStatement". Este recurso possibilita a inserção de parâmetros de forma segura, evitando a concatenação de valores diretamente na string de consulta. 

## **9 - Recursos não fechados :** 

Os recursos "Connection" na linha 21, "Statement" na linha 27 e "ResultSet" na linha 28 não estão sendo fechados corretamente. Fechar estes recursos com ".close()" ou com "try-with-resources" é necessário para garantir que os recursos sejam liberados corretamente e evitar vazamentos de memória.

## **10 - Retorno Inadequado da função "verificarUsuario"  :** 

A função "verificarUsuario" retorna false por padrão, mesmo quando ocorre um erro durante a execução, em vez de indicar que um item está faltando na busca. Seria mais adequado tratar erros e retornar false apenas quando o usuário não for encontrado.

#  **Sugestões de melhoria**

A seguir, apresento algumas melhorias que, embora não sejam erros, podem contribuir para a qualidade e clareza do código.



## **Nome genérico da classe "User":**

A classe "User" está sendo utilizada para realizar operações de busca no banco de dados. É recomendado renomea-la para algo mais específico, como "UserRepository" ou "UserService", para refletir melhor sua responsabilidade e melhorar a clareza do código.

## **Indentação :**

Apesar de não ser um erro, é altamente recomendado alinhar o código para melhor legibilidade. Essa ação tornará o código mais legivel tanto para você no futuro quanto para seus colegas de equipe que possam estar contribuindo para o projeto.
