/*
ArithmeticGameCalculator.java
Java GUI arithmetic game + calculator
Ikaw ang mopili sa operator sign (+ - × ÷)
Naay submit ug next button, score ug difficulty level
*/

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class ArithmeticGameCalculator extends JFrame {
    // mga components
    private JLabel leftNumLabel, operatorLabel, rightNumLabel, equalsLabel;
    private JTextField answerField;
    private JButton submitBtn, nextBtn;
    private JLabel resultBox;
    private JLabel scoreLabel;
    private JComboBox<String> difficultyCombo;
    private Random rand = new Random();

    // game data
    private int leftNum, rightNum;
    private char operator = '+'; // default
    private int correct = 0, total = 0;
    private boolean answered = false; // para malikayan double submit

    public ArithmeticGameCalculator() {
        super("Arithmetic Game Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(540, 350);
        setLocationRelativeTo(null);

        // TOP display (question area)
        JPanel questionPanel = new JPanel(new GridBagLayout());
        questionPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        leftNumLabel = bigLabel("0");
        operatorLabel = bigLabel("+");
        rightNumLabel = bigLabel("0");
        equalsLabel = bigLabel("=");
        resultBox = bigLabel("?");

        gbc.gridx=0; questionPanel.add(leftNumLabel,gbc);
        gbc.gridx=1; questionPanel.add(operatorLabel,gbc);
        gbc.gridx=2; questionPanel.add(rightNumLabel,gbc);
        gbc.gridx=3; questionPanel.add(equalsLabel,gbc);
        gbc.gridx=4; questionPanel.add(resultBox,gbc);

        // INPUT area (answer + buttons)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,8));
        inputPanel.add(new JLabel("Answer:"));
        answerField = new JTextField(8);
        answerField.setFont(new Font("SansSerif", Font.PLAIN,18));
        inputPanel.add(answerField);
        submitBtn = new JButton("Submit");
        inputPanel.add(submitBtn);
        nextBtn = new JButton("Next");
        inputPanel.add(nextBtn);

        // OPERATOR buttons (para ikaw ang mopili)
        JPanel operatorPanel = new JPanel(new GridLayout(1,4,10,10));
        operatorPanel.setBorder(BorderFactory.createTitledBorder("Choose Operator"));
        JButton addBtn = new JButton("+");
        JButton subBtn = new JButton("-");
        JButton mulBtn = new JButton("×");
        JButton divBtn = new JButton("÷");
        operatorPanel.add(addBtn);
        operatorPanel.add(subBtn);
        operatorPanel.add(mulBtn);
        operatorPanel.add(divBtn);

        // DIFFICULTY combo box
        JPanel diffPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        diffPanel.setBorder(BorderFactory.createTitledBorder("Difficulty"));
        String[] diffs = {"Easy (1-10)", "Medium (1-50)", "Hard (1-200)"};
        difficultyCombo = new JComboBox<>(diffs);
        diffPanel.add(difficultyCombo);

        // SCORE label
        scoreLabel = new JLabel("Score: 0 / 0");
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        // MAIN layout
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(questionPanel, BorderLayout.NORTH);
        cp.add(inputPanel, BorderLayout.CENTER);

        JPanel lower = new JPanel(new BorderLayout());
        lower.add(operatorPanel, BorderLayout.NORTH);
        lower.add(diffPanel, BorderLayout.CENTER);
        lower.add(scoreLabel, BorderLayout.SOUTH);
        cp.add(lower, BorderLayout.SOUTH);

        // --- ACTIONS ---
        // kung mopili kag operator, automatic mag-generate ug question
        addBtn.addActionListener(e -> { operator = '+'; nextQuestion(); });
        subBtn.addActionListener(e -> { operator = '-'; nextQuestion(); });
        mulBtn.addActionListener(e -> { operator = '*'; nextQuestion(); });
        divBtn.addActionListener(e -> { operator = '/'; nextQuestion(); });

        submitBtn.addActionListener(e -> checkAnswer());
        nextBtn.addActionListener(e -> nextQuestion());
        answerField.addActionListener(e -> checkAnswer());

        // sugdan ang game
        nextQuestion();
    }

    // mag-generate ug bag-ong question
    private void nextQuestion() {
        int max = switch (difficultyCombo.getSelectedIndex()) {
            case 0 -> 10;
            case 1 -> 50;
            default -> 200;
        };

        leftNum = rand.nextInt(max) + 1;
        rightNum = rand.nextInt(max) + 1;

        // para sa division, siguruhon nga divisible
        if (operator == '/') {
            rightNum = rand.nextInt(Math.max(1, max/4)) + 1;
            leftNum = rightNum * (rand.nextInt(Math.max(1, max/rightNum)) + 1);
        }

        leftNumLabel.setText(String.valueOf(leftNum));
        rightNumLabel.setText(String.valueOf(rightNum));
        operatorLabel.setText(String.valueOf(operator));
        resultBox.setText("?");
        resultBox.setForeground(Color.BLACK);
        answerField.setText("");
        answerField.requestFocusInWindow();
        answered = false;
    }

    // check sa answer kung sakto ba
    private void checkAnswer() {
        if (answered) return; // dili pwede i-double click
        String ans = answerField.getText().trim();
        if (ans.isEmpty()) return;
        answered = true;
        total++;

        double correctAns = switch (operator) {
            case '+' -> leftNum + rightNum;
            case '-' -> leftNum - rightNum;
            case '*' -> leftNum * rightNum;
            case '/' -> (double) leftNum / rightNum;
            default -> 0;
        };

        try {
            double user = Double.parseDouble(ans);
            boolean isCorrect = Math.abs(user - correctAns) < 1e-6;
            if (isCorrect) {
                correct++;
                resultBox.setText("✔");
                resultBox.setForeground(new Color(0,130,0));
            } else {
                resultBox.setText(String.valueOf(correctAns));
                resultBox.setForeground(Color.RED);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number.", "Error", JOptionPane.ERROR_MESSAGE);
            total--;
        }

        scoreLabel.setText("Score: " + correct + " / " + total);
    }

    // helper function sa big display labels
    private JLabel bigLabel(String t) {
        JLabel lbl = new JLabel(t, SwingConstants.CENTER);
        lbl.setPreferredSize(new Dimension(70,70));
        lbl.setOpaque(true);
        lbl.setBackground(new Color(250,250,250));
        lbl.setFont(new Font("SansSerif", Font.BOLD, 26));
        lbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2));
        return lbl;
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch(Exception ignored){}
        SwingUtilities.invokeLater(() -> {
            new ArithmeticGameCalculator().setVisible(true);
        });
    }
}
