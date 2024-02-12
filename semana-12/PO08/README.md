#### 1. Explique brevemente os conceitos fundamentais do padrão de arquitetura MVC (Model-View-Controller). Descreva o papel de cada componente (Model, View e Controller) e como eles interagem entre si.

- Model: responsável por representar o negócio, responsável pelo acesso e manipulação dos dados da aplicação;
- View: responsável pela interface apresentada ao usuário, mostra as informações do model para o usuário e também faz parte da coleta de informações passadas pelo mesmo;
- Controller: responsável por fazer a ligação entre o model e o view, ou seja, faz com que os models possam ser repassados para as views. A recíproca é verdadeira;
- O controller faz a comunicação entre model e view, apresenta ao usuário as regras de negócio atráves de views e também faz a coleta dos dados inseridos pelo usuário e repassa para o model.

####  2. Quais são as principais vantagens de usar o padrão MVC em uma aplicação web? Dê exemplos de situações em que a separação de responsabilidades oferecida pelo MVC é benéfica.

- Vantagens:
    - Desenvolvimento mais rápido;
    - Facilita o trabalho em grupo e colaboração;
    - Atualização se torna mais fácil;
    - Depuração se torna mais fácil;
    - Melhor suporte para TDD;
    - Reutilização de código;

- Exemplos:
    - Atualização UI: se caso necessário realizar atualização na interface do usuário, precisa somente realizar as alterações nas views, sem necessidade de alterar a lógica de negócio;
    - Adição de recursos: Caso queira adicionar um recurso que não necessite visibilidade para o cliente, só precisa realizar a adição no model e controller;
    - Testes: Pode-se testar cada camada de modo independente;

#### 3. Crie um cenário hipotético de uma aplicação web simples e mostre como esta aplicação funciona se implementada utilizando MVC.
- Suponha uma aplicação simples para gerenciar listas de tarefas. Os usuários podem se cadastrar, fazer login e criar suas próprias listas de tarefas personalizadas. Cada lista pode conter várias tarefas, e os usuários podem marcar as tarefas como concluídas ou excluir tarefas da lista.
    - Model:
        - O model é responsável pela manipulação dos dados da aplicação. Ele define como os dados são armazenados, acessados e manipulados.
        - Classes para representar os usuários, as listas de tarefas e as próprias tarefas. Cada uma dessas classes teria métodos para interagir com o banco de dados, como salvar, atualizar e excluir registros.
    - View:
        - A view é responsável pela apresentação dos dados ao usuário e pela interação com ele.
        - Páginas que os usuários veem e interagem, como de registro, login, visualização das listas de tarefas e adição/edição de tarefas.
    - Controller: 
        - O controller é responsável por receber as requisições do usuário, processá-las e decidir qual ação tomar.
        - Lidar com o registro e login de usuários, manipulação de listas de tarefas (criação, edição, exclusão) e manipulação de tarefas individuais.
        - Receber os dados inseridos pelo usuário na view, acionar os métodos apropriados no model para manipular esses dados e retornar o resultado para a view, para que possa ser apresentado ao usuário.

#### 4. Como o MVC facilita a manutenção e a escalabilidade de um projeto? Dê exemplos práticos de como a estrutura do MVC contribui para esses objetivos.
- Em relação a manutenção, como dito no exemplo da questão 2:
    - Atualização UI: se caso necessário realizar atualização na interface do usuário, precisa somente realizar as alterações nas views, sem necessidade de alterar a lógica de negócio;
    - Adição de recursos: Caso queira adicionar um recurso que não necessite visibilidade para o cliente, só precisa realizar a adição no model e controller;
    - Testes: Pode-se testar cada camada de modo independente;
    - Em complemento, adição de recursos podem serem realizados mantendo a integridade dos existentes;
- Em relação a escalabilidade:
    - Adição de recursos podem serem realizados mantendo a integridade dos existentes;
    - Ao realizar a adição de recursos em diferentes camadas, podemos manter a integridade dos existentes, já que adicionamos o necessário sem necessidade de alterar os que já está consolidado;

#### 5. O que é o Spring Boot e quais são seus principais objetivos? Explique como o Spring Boot simplifica o desenvolvimento de aplicativos Java.
- É um framework de desenvolvimento de aplicativos Java, utilizado para simplificar e acelerar o processo de desenvolvimento.
    - Reduz a quantidade necessária de configurações de um projeto, utilizando convenções inteligentes e configurações padrões de projeto;
    - Simplifica a integração com outros frameworks e bibliotecas Java, fornecendo módulos de autoconfiguração que detectam as dependências no classpath;
    - Suporte embutido para servidores de aplicativos  incorporados, como Tomcat, Jetty ou Undertow, o Spring Boot facilita a criação de micro serviços independentes, permitindo que eles sejam executados sem a necessidade de implantar em um servidor de aplicativos externo;
    - Fornece recursos essenciais, como monitoramento, métricas, logging e suporte a segurança, fora da caixa, facilitando a implantação de aplicativos em ambientes de produção.
    


#### 6. Pesquise sobre o ciclo de vida de uma aplicação Spring Boot e o descreva aqui,incluindo as fases de inicialização, configuração e execução. Destaque a importância de anotações.
- Inicialização: 
    - Carrega e configura o ambiente de execução para a aplicação;
    - Carregamento de classes e recursos;
    - Configuração do ambiente, incluindo propriedades, perfis ativos e outras configurações do Spring;
    - Inicialização do contêiner do Spring IoC (Inversion of Control) e do contêiner de injeção de dependência;

- Configuração:
    - Configurada com base nas anotações arquivos de configuração e outras meta informações disponíveis;
    - A configuração permite que os desenvolvedores personalizem e ajustem o comportamento da aplicação de acordo com suas necessidades específicas.
    - Anotações nessa fase são de extrema importância, pois são usadas para configurar componentes, beans, rotas de URL, segurança, entre outros aspectos da aplicação;

- Execução:
    - Processa solicitações e fornece funcionalidades ao usuário;
    - Beans e componentes configurados são injetados uns nos outros conforme necessário, e os controladores de endpoints,caso a aplicação seja API REST, são ativados para lidar com solicitações HTTP;

- Anotações:
    - Usadas para definir e configurar componentes, beans, endpoints e outros aspectos da aplicação;
    - Simplifica significativamente o desenvolvimento, pois permite que os desenvolvedores especifiquem a configuração e o comportamento da aplicação de forma concisa e legível.


#### 7. Você conhece outros Frameworks para desenvolvimento de APIs Rest como o Spring Boot? Pesquise sobre alguns (inclusive de outras linguagens) e fale um pouco sobre eles.

Falando com experiência própria, utilizei muito o nodeJS em aplicações web utilizando express. O mesmo possui algumas características interessantes:

- Assíncrono e orientado a eventos: I/O e solicitações de rede são executadas de forma assíncrona, assim o servidor continua a responder outras solicitações enquanto aguarda a conclusão dessas operações;
- O JS pode ser usado tanto no lado do cliente quando no servidor, ou seja, o desenvolvimento se torna consistente e permite o compartilhamento de código entre o servidor e o navegador;
- NPM(Node Package Manager) é utilizado de modo fácil para instalar as dependências do projeto;
- Suporte para API RESTful e serviços web;
- Pode ser utilizado para desenvolvimento de servidores web tradicionais até aplicativos em tempo real, microsserviços, IoT etc;

Isso tudo utilizando o express onde:
- API simples e intuitiva para lidar com solicitações HTTP, roteamento, manipulação de middlewares etc;
- Facilita o roteamento de URLs em manipulações específicas, suportando roteamento de padrões de URL, parâmetros de rota e manipuladores de middleware para cada rota;
- Construído em torno de um sistema de middlewares, utilizados para autenticação, autorização, registro de solicitações, manipulação de erros entre outros;
- Pode ser integrado com Passport.js para autenticação, Socket.io para comunicação em tempo real entre outros;
- 

#### 8. Uma aplicação desenvolvida com Spring Boot pode ser back end de aplicações front end desenvolvidas com outras plataformas que não sejam Java? Que relação há entre isto e o protocolo https?

Sim, uma aplicação pode ser desenvolvida com Spring Boot e integrada à um front end desenvolvido em outra plataforma, como um servidor web por exemplo.  Essa comunicação é feita via protocolo de rede com HTTPS, sendo independente da plataforma de desenvolvimento. 
O Spring Boot é configurado para receber solicitações via HTTPS, assim o front end se comunica com ele e realiza essas solicitações. 
Realizar esse tipo de comunicação adiciona mais uma camada de segurança na troca de dados;
