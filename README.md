## trab4bim141167
#Trabalho 4º Bimestre - TÓPICOS AVANÇADOS DE PROGRAMAÇÃO ORIENTADA A OBJETOS - UNIVEL - Cascavel/PR

# Docente: Fernando D'Agostini
# Discente: Danny Felipe

# TUTORIAL DO PROJETO - http://www.ciceroednilson.com.br/criando-uma-aplicacao-java-com-jsf-primefaces-cdi-e-jpa-parte-1-introducao/

# Parte 2 - Criação do banco de dados em MySQL.

# Parte 3 - Criado projeto web com o Maven. Realizado as configurações necessárias no pom.xml para utilizar o JSF, PrimeFaces, CDI e JPA. Criado os arquivos: persistence.xml, beans.xml, context.xml.

# Parte 04 - Criado o template para a aplicação utilizando Facelets. Assim não é necessário replicar o mesmo componente em todas as páginas que desejar visualizar. Ex.: Header e Menu.

# Parte 05 - Criado a autenticação do usuário no sistema. Criados os arquivos filter para gerenciamento das ações no banco de dados, validação de usuário para que possa acessar a aplicação. Um arquivo global - uteis, para recuperar o EntityManager que vai ser criado pelo JPAFilter. Classe para persistir a tabela de usuários no banco de dados. Classes para serem usadas pelo Managed Beans, que receberão os dados na aplicação. Classe controller para transformar a classe em um bean gerenciado pelo CDI, e por último, criado a página de login da aplicação.

# Parte 06 - Criado o cadastro de pessoa no sistema. Criados os arquivos responsáveis para persistência da entidade pessoa no banco de dados. Criado a página da web para realização do cadastro de pessoa.

# Parte 07 - Desenvolvido cadastro de pessoa via upload de arquivo XML, através do componente fileUpload do PrimeFaces. Atualizado o arquivo controller de pessoa para receber e processar o arquivo XML. Alterado a página de cadastro para a realização de upload, adicionado mais uma tab, que tem o componente para a realização do upload do arquivo XML.

# Parte 08 - Desenvolvido uma consulta de pessoas com PrimeFaces dataTable. Criado uma página de consulta para visualizar os registros persistidos no banco de dados, para fins, foi utilizado o componente dataTable do PrimeFaces para que seja possível renderizar os registros na aplicação. Também foi alterados os arquivos da PessoaEntity, PessoaRepository, que agora retorna todos os registros cadastrados. Criado: o controller para gerir todas as ações de consulta, e o layout da página de consulta para visualização na aplicação.