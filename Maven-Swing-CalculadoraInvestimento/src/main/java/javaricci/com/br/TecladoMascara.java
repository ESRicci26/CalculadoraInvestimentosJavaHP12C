package javaricci.com.br;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TecladoMascara extends PlainDocument {
    private static final long serialVersionUID = 1L;
    private JTextField textField;

    public TecladoMascara(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        // Filtra apenas dígitos
        String filteredStr = str.replaceAll("[^0-9]", "");
        if (filteredStr.isEmpty()) {
            return;
        }

        // Constrói o novo texto com o valor inserido
        StringBuilder currentText = new StringBuilder(getText(0, getLength()));
        currentText.insert(offset, filteredStr);

        // Atualiza o documento com o valor formatado
        updateText(currentText.toString());
    }

    @Override
    public void replace(int offset, int length, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        // Filtra apenas dígitos
        String filteredStr = str.replaceAll("[^0-9]", "");
        StringBuilder currentText = new StringBuilder(getText(0, getLength()));
        currentText.replace(offset, offset + length, filteredStr);

        // Atualiza o documento com o valor formatado
        updateText(currentText.toString());
    }

    @Override
    public void remove(int offset, int length) throws BadLocationException {
        StringBuilder currentText = new StringBuilder(getText(0, getLength()));
        currentText.delete(offset, offset + length);

        // Atualiza o documento com o valor formatado
        updateText(currentText.toString());
    }

    private void updateText(String text) throws BadLocationException {
        if (text.isEmpty()) {
            super.remove(0, getLength());
            textField.setCaretPosition(0);
            return;
        }

        try {
            // Remove vírgulas para converter para número
            text = text.replace(",", "");

            // Converte a string para um número
            long value = Long.parseLong(text);

            // Formata o número para moeda com duas casas decimais
            String formattedValue = formatToCurrency(value);

            // Atualiza o documento com o texto formatado
            super.remove(0, getLength());
            super.insertString(0, formattedValue, null);

            // Atualiza a posição do cursor para o final do texto
            textField.setCaretPosition(getLength());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private String formatToCurrency(long value) {
        // Divide o valor por 100 para obter o valor decimal
        double decimalValue = value / 100.0;
        return String.format("%.2f", decimalValue).replace(".", ",");
    }
}

