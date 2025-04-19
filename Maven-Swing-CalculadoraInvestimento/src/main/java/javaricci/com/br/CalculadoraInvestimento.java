package javaricci.com.br;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.text.NumberFormatter;

public class CalculadoraInvestimento extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField valorPresenteField;
    private JTextField jurosMensalField;
    private JTextField numeroMesesField;
    private JTextField aporteMensalField;
    private JTextField valorFuturoField;
    
    private final NumberFormat decimalFormat;
    
    public CalculadoraInvestimento() {
        setTitle("Calculadora de Investimentos HP 12C");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 350);//LARGURA=600 - ALTURA=350
        setLocationRelativeTo(null);
        
        //Carrega o ícone da janela *.PNG 32X32, o default é o ícone Java
        try {
            // Carrega a imagem do ícone
            ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/calculadorahp.png"));
            // Define o ícone da janela
            setIconImage(icon.getImage());
        } catch (Exception e) {
            System.err.println("Erro ao carregar o ícone: " + e.getMessage());
        }

        
        // Configura formatadores
        Locale localeBR = new Locale("pt", "BR");
        decimalFormat = NumberFormat.getCurrencyInstance(localeBR);
        
        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Painel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 15));
        
        // Valor Presente
        inputPanel.add(new JLabel("Valor Presente (R$):"));
        valorPresenteField = new JTextField();
        valorPresenteField.setDocument(new TecladoMascara(valorPresenteField)); //Habilita Máscara com separador vírgula centavos
        inputPanel.add(valorPresenteField);
        
        // Juros ao Mês
        inputPanel.add(new JLabel("Juros ao Mês (%):"));
        jurosMensalField = new JTextField();
        jurosMensalField.setDocument(new TecladoMascara(jurosMensalField));
        inputPanel.add(jurosMensalField);
        
        // Número de Meses
        inputPanel.add(new JLabel("Número de Meses:"));
        numeroMesesField = new JTextField();
        numeroMesesField.setDocument(new TecladoMascara(numeroMesesField));
        inputPanel.add(numeroMesesField);
        
        // Valor Aporte Mensal
        inputPanel.add(new JLabel("Valor Aporte Mensal (R$):"));
        aporteMensalField = new JTextField();
        aporteMensalField.setDocument(new TecladoMascara(aporteMensalField));
        inputPanel.add(aporteMensalField);
        
        // Valor Futuro (resultado)
        inputPanel.add(new JLabel("Valor Futuro (R$):"));
        valorFuturoField = new JTextField();
        valorFuturoField.setEditable(false);
        valorFuturoField.setBackground(new Color(240, 240, 240));
        valorFuturoField.setFont(valorFuturoField.getFont().deriveFont(Font.BOLD));
        inputPanel.add(valorFuturoField);
        
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        
        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        
        JButton calcularButton = new JButton("Calcular");
        calcularButton.setIcon(new ImageIcon(getClass().getResource("/imagens/calculadora.png")));
        
        JButton limparButton = new JButton("Limpar");
        limparButton.setIcon(new ImageIcon(getClass().getResource("/imagens/limpar-limpo.png")));
        
        JButton exemplo1Button = new JButton("Modelo COM Aporte");
        exemplo1Button.setIcon(new ImageIcon(getClass().getResource("/imagens/modelocomaporte.png")));
        
        JButton exemplo2Button = new JButton("Modelo SEM Aporte");
        exemplo2Button.setIcon(new ImageIcon(getClass().getResource("/imagens/modelosemaporte.png")));
        
        buttonPanel.add(calcularButton);
        buttonPanel.add(limparButton);
        buttonPanel.add(exemplo1Button);
        buttonPanel.add(exemplo2Button);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Adiciona listeners aos botões
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularValorFuturo();
            }
        });
        
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        
        exemplo1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preencherExemplo1();
            }
        });
        
        exemplo2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preencherExemplo2();
            }
        });
        
        add(mainPanel);
    }
    
    private void calcularValorFuturo() {
        try {
            // Obter valores dos campos
            double valorPresente = parseDoubleFromField(valorPresenteField.getText());
            double jurosMensal = parseDoubleFromField(jurosMensalField.getText()) / 100; // Converter para decimal
            int numeroMeses = (int) parseDoubleFromField(numeroMesesField.getText());
            double aporteMensal = parseDoubleFromField(aporteMensalField.getText());
            
            // Calcular valor futuro
            double valorFuturo = valorPresente * Math.pow(1 + jurosMensal, numeroMeses);
            
            // Se houver aporte mensal, calcular o montante dos aportes com juros
            if (aporteMensal > 0) {
                // Fórmula para soma dos termos de uma PG para os aportes mensais
                double montanteAportes = aporteMensal * ((Math.pow(1 + jurosMensal, numeroMeses) - 1) / jurosMensal);
                valorFuturo += montanteAportes;
            }
            
            // Exibir resultado formatado
            valorFuturoField.setText(decimalFormat.format(valorFuturo));
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao calcular. Verifique se todos os campos estão preenchidos corretamente.\n" + 
                "Use ponto ou vírgula como separador decimal.", 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private double parseDoubleFromField(String text) {
        // Remove símbolos de moeda e separadores de milhar, e normaliza o separador decimal
        if (text == null || text.trim().isEmpty()) {
            return 0.0;
        }
        
        text = text.replaceAll("[R$\\s.]", "").replace(",", ".");
        return Double.parseDouble(text);
    }
    
    private void limparCampos() {
        valorPresenteField.setText("");
        jurosMensalField.setText("");
        numeroMesesField.setText("");
        aporteMensalField.setText("");
        valorFuturoField.setText("");
    }
    
    private void preencherExemplo1() {
        // Apenas preenche os campos com os valores do exemplo 1, sem calcular automaticamente
        valorPresenteField.setText("32114,68");
        jurosMensalField.setText("1,00");
        numeroMesesField.setText("36,00");
        aporteMensalField.setText("5000,00");
        // Não calcula automaticamente para permitir que o usuário modifique valores se desejar
        valorFuturoField.setText("");
    }
    
    private void preencherExemplo2() {
        // Apenas preenche os campos com os valores do exemplo 2, sem calcular automaticamente
        valorPresenteField.setText("32114,68");
        jurosMensalField.setText("1,00");
        numeroMesesField.setText("36,00");
        aporteMensalField.setText("0,00");
        // Não calcula automaticamente para permitir que o usuário modifique valores se desejar
        valorFuturoField.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Definir o look and feel do sistema
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                CalculadoraInvestimento app = new CalculadoraInvestimento();
                app.setVisible(true);
            }
        });
    }
}