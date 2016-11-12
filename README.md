## trab4bim141167
#Trabalho 4º Bimestre - TÓPICOS AVANÇADOS DE PROGRAMAÇÃO ORIENTADA A OBJETOS - UNIVEL - Cascavel/PR

# Docente: Fernando D'Agostini
# Discente: Danny Felipe

# TUTORIAL DO PROJETO - http://www.ciceroednilson.com.br/criando-uma-aplicacao-java-com-jsf-primefaces-cdi-e-jpa-parte-1-introducao/

# Parte 2 - Criação do banco de dados em MySQL.

# Parte 3 - Criado projeto web com o Maven. Realizado as configurações necessárias no pom.xml para utilizar o JSF, PrimeFaces, CDI e JPA. Criado os arquivos: persistence.xml, beans.xml, context.xml.

# Parte 04 - Criado o template para a aplicação utilizando Facelets. Assim não é necessário replicar o mesmo componente em todas as páginas que desejar visualizar. Ex.: Header e Menu.

# Parte 05 - Criado a autenticação do usuário no sistema. Criados os arquivos filter para gerenciamento das ações no banco de dados, validação de usuário para que possa acessar a aplicação. Um arquivo global - uteis, para recuperar o EntityManager que vai ser criado pelo JPAFilter. Classe para persistir a tabela de usuários no banco de dados. Classes para serem usadas pelo Managed Beans, que receberão os dados na aplicação. Classe controller para transformar a classe em um bean gerenciado pelo CDI, e por último, criado a página de login da aplicação.