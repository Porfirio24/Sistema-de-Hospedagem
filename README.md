# Sistema-de-Hospedagem 🏨

## Descrição
O **Sistema de Pousada** é uma aplicação Java orientada a objetos desenvolvida para auxiliar no gerenciamento de uma pousada, oferecendo suporte completo ao controle de hóspedes, quartos, reservas, funcionários, autenticação por login, além da geração de relatórios de ocupação e faturamento.

O sistema foi projetado com foco em modularidade, reutilização de código e persistência simples com arquivos .txt, proporcionando uma experiência prática para simulação de um ambiente real de hospedagem.

---

## Funcionalidades ⚙️ 
### 🧑‍💼 **Funcionário**

🧑‍💼 Gerenciamento de Funcionários

- Listar Funcionários
- Cadastrar Funcionário
- Editar Funcionário
- Remover Funcionário

🧳 Gerenciamento de Hóspedes

- Listar Hóspedes
- Cadastrar Hóspede
- Buscar Hóspede por CPF
- Editar Informações do Hospede
- Remover Hóspede

🛏️ Gerenciamento de Quartos

- Listar Todos os Quartos
- Listar Quartos Disponíveis
- Buscar Quarto por Número
- Cadastrar Quarto
- Editar Quarto
- Remover Quarto
- Marcar como Disponível
- Marcar como Ocupado

📅 Sistema de Reservas

- Listar Todas as Reservas
- Listar Reservas por Hóspede
- Listar Reservas por Quarto
- Listar Reservas por Período
- Criar Reserva
- Confirmar Reserva
- Cancelar Reserva
- Remover Reserva

📊 Relatórios

- Geração de relatório de ocupação de quartos com taxa de ocupação
- Geração de relatório de reservas com faturamento total

---

### 🔒 Controle de Acesso

O sistema possui um funcionário principal (Gerente/admin) que poder ser acessado com os seguintes email e senha:
```bash
email: admin@pousada.com
senha: admin123
```

| Camada                | Descrição                                                      | 
|-----------------------|----------------------------------------------------------------|
| **models**            | Representa as entidades: Hospede, Funcionario, Reserva, Quarto | 
| **views**             | Contém os menus de interação com o usuário                     |
| **controllers**       | Lógica de negócio e validações                                 |
| **data**              | Persistência de dados usando arquivos .txt                     | 
| **util**              | Classes utilitárias para validações, autenticação, manipulação de datas e geração de relatórios                         | 

## 🛠️ Instalação

### Pré-requisitos

💻 Ambiente de Desenvolvimento

- Java SE 17+
- IDE recomendada: IntelliJ IDEA, Eclipse ou VS-Code

### Passo a Passo

```bash
# Clone o repositório
[git clone https://github.com/Porfi24/Sistema-de-Hospedagem]
```

```bash
# Compile todos os arquivos Java
javac -d bin src/**/*.java
```

```bash
# Execute a classe principal
java -cp bin Main
```
