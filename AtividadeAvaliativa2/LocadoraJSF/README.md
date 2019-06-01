# Desenvolvimento de Software para Web

## Grupo
* Caio Ferreira, RA: 726502
* Jackson Victor Teodoro, RA: 726543
* Leonardo Penna de Lima, RA: 726559

## Atividade Avaliativa 2 - Sistema para Locação de Bicicletas

O sistema deve possuir um cadastro de clientes, com os seguintes dados: e-mail, senha, CPF, nome, telefone, sexo e data de nascimento.
O sistema deve possuir um cadastro de locadoras, com os seguintes dados: email, senha, CNPJ, nome e cidade.
O sistema deve possuir um cadastro de locações, com os seguintes dados: cliente, locadora e dia/horário da locação. Assume-se que a duração da locação é de 1 hora e sempre inicia-se em “hora cheia” (13h 00min etc).

O sistema deve atender aos seguintes requisitos:
* __R1:__ CRUD de clientes (requer login de administrador).
* __R2:__ CRUD de locadoras (requer login de administrador).
* __R3:__ Listagem de todos as locadoras em uma única página (não requer login).
* __R4:__ Listagem de todos as locadoras por cidade (não requer login). Para tal, deve-se digitar o nome da cidade ou escolher a cidade a partir de uma lista.
* __R5:__ Locação de uma bicicleta em uma locadora (requer login do cliente via e-mail + senha). Depois de fazer login, o cliente pode cadastrar uma locação. Para isso, deve escolher uma locadora (a partir de uma lista), um dia/horário, e deve ser gravado a locação na base de dados.
* __R6:__ Listagem de todas as locações de um cliente (requer login do cliente via e-mail + senha). Depois de fazer login, o cliente pode visualizar todas as suas locações gravadas.
* __R7:__ O sistema não deve permitir o cadastro de locações de um mesmo cliente ou de um mesma locadora em um mesmo dia/horário.
* __R8:__ Listagem de todas as locações de uma locadora (requer login da locadora via e-mail + senha). Depois de fazer login, a locadora pode
visualizar todas as suas locações gravadas.
* __R9:__ O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha.

O sistema deve tratar todos os erros possíveis (cadastros duplicados, problemas técnicos, etc) mostrando uma página de erros amigável ao usuário e registrando o erro no console. Além disso, o sistema deve proporcionar máscaras (telefone, CNPJ, CPF, e-mail, etc) para entrada dos dados.

__Arquitetura:__ Modelo-Visão-Controlador
__Tecnologias:__ JSF/PrimeFaces & JPA (Lado Servidor) e Javascript & CSS (Lado Cliente)

## Roteiro

O SGBD utilizado é o Java DB.
* __Nome do banco:__ Locadora
* __Usuario:__ root
* __Senha:__ root

Para criação de usuarios teste, execute a classe criaUsuario.java no pacote br.ufscar.dc.dsw.dao

Será criado um usuário admin:

__E-mail:__ admin@admin.com
__Senha:__ admin123
__Permissão:__ administrador

E também serão criados 5 clientes e 5 locadoras para teste:

__E-mail:__ cliente1@cliente.com
__Senha:__ 12345678
__Permissão:__ cliente

__E-mail:__ cliente2@cliente.com
__Senha:__ 12345678
__Permissão:__ cliente

__E-mail:__ cliente3@cliente.com
__Senha:__ 12345678
__Permissão:__ cliente

__E-mail:__ cliente4@cliente.com
__Senha:__ 12345678
__Permissão:__ cliente

__E-mail:__ cliente5@cliente.com
__Senha:__ 12345678
__Permissão:__ cliente

__E-mail:__ locadora1@locadora.com
__Senha:__ 12345678
__Permissão:__ locadora

__E-mail:__ locadora2@locadora.com
__Senha:__ 12345678
__Permissão:__ locadora

__E-mail:__ locadora3@locadora.com
__Senha:__ 12345678
__Permissão:__ locadora

__E-mail:__ locadora4@locadora.com
__Senha:__ 12345678
__Permissão:__ locadora

__E-mail:__ locadora5@locadora.com
__Senha:__ 12345678
__Permissão:__ locadora