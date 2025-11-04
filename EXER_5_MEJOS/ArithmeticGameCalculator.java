import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class ArithmeticGameCalculator extends JFrame {
    private JLabel leftNumLabel, operatorLabel, rightNumLabel, equalsLabel;
    private JTextField answerField;
    private JButton submitBtn, nextBtn;
    private JLabel resultBox;
    private JLabel scoreLabel;
    private JComboBox<String> difficultyCombo;
    private Random rand = new Random();

    private int leftNum, rightNum;
    private char operator = '+';
    private int correct = 0, total = 0;
    private boolean answered = false;

    public ArithmeticGameCalculator() {
        super("🎮 Arithmetic Game Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 420);
        setLocationRelativeTo(null);

        // MAIN PANEL (dark theme)
        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBackground(new Color(25, 25, 25));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // QUESTION PANEL
        JPanel questionPanel = new JPanel(new GridBagLayout());
        questionPanel.setBackground(new Color(40, 40, 40));
        questionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Question", 0, 0, null, Color.WHITE));
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

        // INPUT PANEL
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,15,10));
        inputPanel.setBackground(new Color(25,25,25));
        JLabel ansLabel = new JLabel("Enter Answer:");
        ansLabel.setForeground(Color.WHITE);
        inputPanel.add(ansLabel);

        answerField = new JTextField(10);
        answerField.setFont(new Font("Arial", Font.PLAIN,16));
        inputPanel.add(answerField);

        submitBtn = createButton("Submit", new Color(0, 180, 80));
        nextBtn = createButton("Next", new Color(80, 120, 220));
        inputPanel.add(submitBtn);
        inputPanel.add(nextBtn);

        // OPERATOR PANEL
        JPanel operatorPanel = new JPanel(new GridLayout(1,4,10,10));
        operatorPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Choose Operator", 0, 0, null, Color.WHITE));
        operatorPanel.setBackground(new Color(35,35,35));

        JButton addBtn = createButton("+", new Color(255, 193, 7));
        JButton subBtn = createButton("-", new Color(255, 87, 34));
        JButton mulBtn = createButton("×", new Color(156, 39, 176));
        JButton divBtn = createButton("÷", new Color(33, 150, 243));

        operatorPanel.add(addBtn);
        operatorPanel.add(subBtn);
        operatorPanel.add(mulBtn);
        operatorPanel.add(divBtn);

        // DIFFICULTY PANEL
        JPanel diffPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        diffPanel.setBackground(new Color(35,35,35));
        diffPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Difficulty", 0, 0, null, Color.WHITE));

        String[] diffs = {"Easy (1-10)", "Medium (1-50)", "Hard (1-200)"};
        difficultyCombo = new JComboBox<>(diffs);
        difficultyCombo.setFont(new Font("Arial", Font.PLAIN,16));
        diffPanel.add(difficultyCombo);

        // SCORE PANEL
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scorePanel.setBackground(new Color(20,20,20));
        scorePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Score", 0, 0, null, Color.WHITE));

        scoreLabel = new JLabel("Score: 0 / 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setForeground(new Color(0, 230, 118));
        scorePanel.add(scoreLabel);

        // LOWER PANEL COMBINATION
        JPanel lowerPanel = new JPanel(new BorderLayout(10,10));
        lowerPanel.setBackground(new Color(25,25,25));
        lowerPanel.add(operatorPanel, BorderLayout.NORTH);
        lowerPanel.add(diffPanel, BorderLayout.CENTER);
        lowerPanel.add(scorePanel, BorderLayout.SOUTH);

        // ADD ALL TO MAIN
        mainPanel.add(questionPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(lowerPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // ACTIONS
        addBtn.addActionListener(e -> { operator = '+'; nextQuestion(); });
        subBtn.addActionListener(e -> { operator = '-'; nextQuestion(); });
        mulBtn.addActionListener(e -> { operator = '*'; nextQuestion(); });
        divBtn.addActionListener(e -> { operator = '/'; nextQuestion(); });

        submitBtn.addActionListener(e -> checkAnswer());
        nextBtn.addActionListener(e -> nextQuestion());
        answerField.addActionListener(e -> checkAnswer());
        difficultyCombo.addActionListener(e -> nextQuestion());

        nextQuestion();
    }

    private void nextQuestion() {
        int max = switch (difficultyCombo.getSelectedIndex()) {
            case 0 -> 10;
            case 1 -> 50;
            default -> 200;
        };

        leftNum = rand.nextInt(max) + 1;
        rightNum = rand.nextInt(max) + 1;

        if (operator == '/') {
            rightNum = rand.nextInt(Math.max(1, max/4)) + 1;
            leftNum = rightNum * (rand.nextInt(Math.max(1, max/rightNum)) + 1);
        }

        leftNumLabel.setText(String.valueOf(leftNum));
        rightNumLabel.setText(String.valueOf(rightNum));
        operatorLabel.setText(String.valueOf(operator));
        resultBox.setText("?");
        resultBox.setForeground(Color.WHITE);
        answerField.setText("");
        answerField.requestFocusInWindow();
        answered = false;
    }

    private void checkAnswer() {
        if (answered) return;
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
                resultBox.setForeground(new Color(0,230,118));
                showEmojiPopup("Correct! 😄", new Color(0, 200, 0));
            } else {
                resultBox.setText(String.valueOf(correctAns));
                resultBox.setForeground(new Color(255,82,82));
                showEmojiPopup("Wrong! 😢", new Color(200, 0, 0));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number.", "Error", JOptionPane.ERROR_MESSAGE);
            total--;
        }

        scoreLabel.setText("Score: " + correct + " / " + total);
    }

    // POP-UP emoji window (disappears after 1.5 sec)
    private void showEmojiPopup(String message, Color bgColor) {
        JDialog popup = new JDialog(this, false);
        popup.setUndecorated(true);
        popup.setSize(180, 100);
        popup.getContentPane().setBackground(bgColor);
        popup.setLocationRelativeTo(this);

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        popup.add(label);

        popup.setVisible(true);

        new Timer(1500, e -> popup.dispose()).start(); // hide after 1.5s
    }

    private JLabel bigLabel(String t) {
        JLabel lbl = new JLabel(t, SwingConstants.CENTER);
        lbl.setPreferredSize(new Dimension(70,70));
        lbl.setOpaque(true);
        lbl.setBackground(new Color(45,45,45));
        lbl.setFont(new Font("Arial", Font.BOLD, 28));
        lbl.setForeground(Color.WHITE);
        lbl.setBorder(BorderFactory.createLineBorder(new Color(100,100,100),2));
        return lbl;
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(color);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        return btn;
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch(Exception ignored){}
        SwingUtilities.invokeLater(() -> new ArithmeticGameCalculator().setVisible(true));
    }
}
