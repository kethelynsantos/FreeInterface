import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VelhaFrame extends JFrame {
    private JButton[][] buttons;
    private boolean jogadorX;

    public VelhaFrame() {
        jogadorX = true;

        setTitle("Jogo da Velha");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(new ButtonClickListener());
                add(buttons[i][j]);
            }
        }

        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VelhaFrame();
        });
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton buttonClicked = (JButton) e.getSource();

            if (buttonClicked.getText().equals("") && jogadorX) {
                buttonClicked.setText("X");
                jogadorX = false;
            } else if (buttonClicked.getText().equals("") && !jogadorX) {
                buttonClicked.setText("O");
                jogadorX = true;
            }

            if (verificarVencedor("X")) {
                JOptionPane.showMessageDialog(null, "Jogador X ganhou!");
                reiniciarJogo();
            } else if (verificarVencedor("O")) {
                JOptionPane.showMessageDialog(null, "Jogador O ganhou!");
                reiniciarJogo();
            } else if (jogoEmpatado()) {
                JOptionPane.showMessageDialog(null, "Empate!");
                reiniciarJogo();
            }
        }
    }

    private boolean verificarVencedor(String jogador) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(jogador) && buttons[i][1].getText().equals(jogador) && buttons[i][2].getText().equals(jogador))
                return true;
            if (buttons[0][i].getText().equals(jogador) && buttons[1][i].getText().equals(jogador) && buttons[2][i].getText().equals(jogador))
                return true;
        }
        if (buttons[0][0].getText().equals(jogador) && buttons[1][1].getText().equals(jogador) && buttons[2][2].getText().equals(jogador))
            return true;
        if (buttons[0][2].getText().equals(jogador) && buttons[1][1].getText().equals(jogador) && buttons[2][0].getText().equals(jogador))
            return true;

        return false;
    }

    private boolean jogoEmpatado() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void reiniciarJogo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        jogadorX = true;
    }
}
