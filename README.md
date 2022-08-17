# Sistema-de-prescricao-medica-versao-banco-de-dados-

Com base nos conhecimentos obtidos ao longo do semestre, implemente um programa em Java para simular uma parte de um sistema hospitalar responsável pela prescrição de medicamentos aos pacientes previamente internados. Esse sistema deverá seguir algumas regras gerais (de interface, por exemplo) e algumas regras específicas (regras estabelecidas pelo negócio). Nos tópicos a seguir, essas regras gerais e específicas serão melhor detalhadas.

  1.1. Regras Gerais do Sistema
  
  1.1.1. Os painéis devem utilizar o MigLayout como gerenciador de layout, exceto a inicial e o painel de login.
  
  1.1.2. Os painéis devem ser organizados internamente no padrão MVC.
  
  1.1.3. Os painéis devem ter um botão “Salvar” e um “Cancelar”, exceto a inicial (o painel de login e o painel de medicamentos).
  
    ● O botão Salvar vai validar e gravar as informações do painel no banco de dados.
    ● O botão Cancelar vai voltar ao painel inicial sem gravar nenhuma informação no banco de dados.
    ● Para este trabalho, não serão exigidas funcionalidades de Atualização e nem Exclusão no banco de dados, exceto para o painel de medicamentos que deve possuir todas operações CRUD.
  
  1.1.4. Cada tela exigida neste sistema hospitalar terá regras específicas que devem ser atendidas em sua totalidade.
  
  1.1.5. Todas as mensagens de crítica quanto ao preenchimento de campos podem ser emitidas no console da aplicação (não é preciso utilizar caixas de diálogo, nem criar labels específicos para essa finalidade).
