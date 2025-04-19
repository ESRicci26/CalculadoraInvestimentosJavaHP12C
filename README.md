# Calculadora de Investimentos Java HP 12C

## 📋 Descrição

A **Calculadora de Investimentos Java HP 12C** é uma aplicação Java que simula cálculos financeiros de juros compostos, similar à tradicional calculadora HP 12C. A aplicação permite calcular o valor futuro de investimentos com ou sem aportes mensais, baseando-se no valor presente, taxa de juros mensal e prazo do investimento.

## ✨ Funcionalidades

- Cálculo de valor futuro de investimentos com juros compostos
- Suporte para cenários com e sem aportes mensais
- Interface gráfica intuitiva com máscaras para entradas numéricas
- Formatação automática de valores monetários
- Modelos pré-configurados para demonstração
- Validação de entrada de dados

## 🖥️ Interface

A aplicação possui uma interface gráfica amigável com campos para:

- **Valor Presente (R$)**: Montante inicial investido
- **Juros ao Mês (%)**: Taxa de juros mensal 
- **Número de Meses**: Prazo do investimento
- **Valor Aporte Mensal (R$)**: Contribuições mensais (opcional)
- **Valor Futuro (R$)**: Resultado calculado automaticamente

### Botões de ação:

- **Calcular**: Processa os valores e exibe o resultado
- **Limpar**: Apaga todos os campos de entrada
- **Modelo COM Aporte**: Preenche os campos com exemplo incluindo aporte mensal
- **Modelo SEM Aporte**: Preenche os campos com exemplo sem aporte mensal

## 🧮 Fórmulas Utilizadas

### Para investimentos sem aporte mensal:
```
VF = VP × (1 + i)^n
```

### Para investimentos com aporte mensal:
```
VF = VP × (1 + i)^n + AM × [(1 + i)^n - 1] / i
```

Onde:
- **VF**: Valor Futuro
- **VP**: Valor Presente
- **i**: Taxa de juros mensal (decimal)
- **n**: Número de meses
- **AM**: Aporte Mensal

## 🛠️ Tecnologias Utilizadas

- Java SE
- Java Swing (interface gráfica)
- Padrão de projeto Document (para máscara de entrada)

## 📦 Estrutura do Projeto

```
calculadora-investimentos/
├── src/
│   ├── javaricci/
│   │   └── com/
│   │       └── br/
│   │           ├── CalculadoraInvestimento.java
│   │           └── TecladoMascara.java
│   └── imagens/
│       ├── calculadorahp.png
│       ├── calculadora.png
│       ├── limpar-limpo.png
│       ├── modelocomaporte.png
│       └── modelosemaporte.png
└── bin/
    └── [arquivos compilados]
```

## 💡 Como Usar

### Pré-requisitos
- Java Runtime Environment (JRE) 8 ou superior
- Ambiente de desenvolvimento Java (para compilação)


### Utilizando a Aplicação

1. Insira os valores nos campos apropriados:
   - Valor inicial do investimento
   - Taxa de juros mensal (%)
   - Número de meses do investimento
   - Valor de aporte mensal (se houver)

2. Clique em "Calcular" para obter o valor futuro do investimento

3. Para facilitar, utilize os botões de modelo:
   - "Modelo COM Aporte" para cenário com aportes mensais
   - "Modelo SEM Aporte" para cenário sem aportes mensais

## 🔍 Detalhes da Implementação

### Classe CalculadoraInvestimento
Esta classe principal implementa a interface gráfica da aplicação e a lógica de cálculo financeiro. Estende `JFrame` para criar a janela principal.

#### Características principais:
- Configura layout e componentes da interface gráfica
- Implementa os listeners para as ações dos botões
- Realiza cálculos financeiros com base nas fórmulas matemáticas
- Formata valores para exibição em formato de moeda brasileira

### Classe TecladoMascara
Esta classe implementa a funcionalidade de máscara para os campos de entrada, permitindo apenas a digitação de valores numéricos e formatando-os automaticamente.

#### Características principais:
- Estende `PlainDocument` para controlar o que pode ser inserido nos campos
- Filtra entradas para aceitar apenas dígitos
- Formata automaticamente os valores como moeda com duas casas decimais
- Atualiza a posição do cursor para melhor experiência do usuário

## 📝 Exemplos

A aplicação inclui dois modelos pré-configurados para facilitar a utilização:

### Exemplo 1: Com Aporte Mensal
- Valor Presente: R$ 32.114,68
- Juros ao Mês: 1,00%
- Número de Meses: 36
- Valor Aporte Mensal: R$ 5.000,00

### Exemplo 2: Sem Aporte Mensal
- Valor Presente: R$ 32.114,68
- Juros ao Mês: 1,00%
- Número de Meses: 36
- Valor Aporte Mensal: R$ 0,00

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 👤 Autor

**Java Ricci** - [Perfil GitHub](https://github.com/ESRicci26)
