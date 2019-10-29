import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 900;
    JPanel mainPanel;
    JPanel topPanel;
    JPanel midPanel;
    JPanel bottomPanel;
    JPanel rightPanel;
    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;
    JButton quitButton;
    JLabel titleLabel;
    ImageIcon rockImg;
    ImageIcon paperImg;
    ImageIcon scissorsImg;
    JLabel compWinsLabel;
    JLabel playerWinsLabel;
    JLabel tiesLabel;
    JScrollPane scrollPane;
    JTextArea rpsTextArea;
    int cWins = 0;
    int pWins = 0;
    int tieGames = 0;
    //A stats panel with 3 JLabels and JTextFields (Player Wins, Computer Wins, Ties) each should have the count of the wins etc.
   //         - A panel with a `JTextArea` with `JScrollPane` that displays the results of each game one per line as a text string: Rock breaks scissors (Player wins) or Paper covers Rock (Computer Wins) This should accumulate and display the results for each game in the session, one per line, not just show the results for the last game played. (In other words, you can scroll through all of the game results for the session.)

    public RockPaperScissorsFrame(String title) {
        super(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        topPanel = new JPanel();
        midPanel = new JPanel();
        bottomPanel = new JPanel();
        rightPanel = new JPanel();

        //scroll
        rpsTextArea = new JTextArea(10, 70);
        scrollPane = new JScrollPane(rpsTextArea);
        titleLabel = new JLabel("Rock Paper Scissors");
        //game stats
        compWinsLabel = new JLabel("Computer Wins: 0");
        playerWinsLabel = new JLabel("Player Wins: 0");
        tiesLabel = new JLabel("Ties: 0");
        //3 img
        rockImg = new ImageIcon("assets/rock.png");
        paperImg = new ImageIcon("assets/paper.png");
        scissorsImg = new ImageIcon("assets/scissors.png");
        //3 RPS buttons
        rockButton = new JButton(rockImg);
        paperButton = new JButton(paperImg);
        scissorsButton = new JButton(scissorsImg);

        quitButton = new JButton("Quit");

        UISetup();
        //actions
        quitButton.addActionListener((ActionEvent actionEvent) -> System.exit(0));
        rockButton.addActionListener((ActionEvent actionEvent) -> playGame("Rock"));
        paperButton.addActionListener((ActionEvent actionEvent) -> playGame("Paper"));
        scissorsButton.addActionListener((ActionEvent actionEvent) -> playGame("Scissors"));
        setVisible(true);
    }
    public void UISetup() {
    mainPanel.setLayout(new BorderLayout());

    //top
    topPanel.add(titleLabel);
    //mid
    midPanel.setLayout(new BorderLayout());
    midPanel.add(rockButton, BorderLayout.WEST);
    midPanel.add(paperButton, BorderLayout.CENTER);
    midPanel.add(scissorsButton, BorderLayout.EAST);
    //bot
    bottomPanel.setLayout(new BorderLayout());
    bottomPanel.add(scrollPane, BorderLayout.NORTH);
    bottomPanel.add(quitButton, BorderLayout.CENTER);
    //right
    rightPanel.setLayout(new GridLayout(3,1));
    rightPanel.add(compWinsLabel);
    rightPanel.add(playerWinsLabel);
    rightPanel.add(tiesLabel);

    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(midPanel, BorderLayout.CENTER);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    mainPanel.add(rightPanel, BorderLayout.EAST);
    add(mainPanel);
    }
        private void updateGameResults(String whoWon, String compChoice, String playerChoice) {
            switch (whoWon) {
                case "ComputerWon":
                    cWins++;
                    compWinsLabel.setText("Computer Wins: "+ cWins);
                    rpsTextArea.append("Your choice: " + playerChoice + ", Computers Choice: " + compChoice + " |-| " + compChoice + " beats " + playerChoice + ", Computer wins!\n");
                    break;
                case "PlayerWon":
                    pWins++;
                    playerWinsLabel.setText("Player Wins: "+ pWins);
                    rpsTextArea.append("Your choice: " + playerChoice + ", Computers Choice: " + compChoice + " |-| " + playerChoice + " beats " + compChoice + ",You Win!\n");

                    break;
                case "tie":
                    tieGames++;
                    tiesLabel.setText("Ties: " + tieGames);
                    rpsTextArea.append("Both players picked " + compChoice + ", It's a Tie!\n");
                    break;
        }
    }
    public void playGame(String playerChoice) {
        String computerChoice = "";
        String Winner = "";
        Random random = new Random();
        int randInt = random.nextInt(3);// possible choices 0,1,2
        switch (randInt){
            case 0:
                computerChoice = "Rock";
                break;
            case 1:
                computerChoice = "Paper";
                break;
            case 2:
                computerChoice = "Scissors";
                break;
        }
        if(playerChoice == "Rock"){
            if(computerChoice == "Paper"){
                Winner = "ComputerWon";
            }
            else if(computerChoice == "Scissors"){
                Winner = "PlayerWon";
            }
        }
        else if(playerChoice == "Paper"){
            if(computerChoice == "Rock"){
                Winner = "PlayerWon";
            }
            else if(computerChoice == "Scissors"){
                Winner = "ComputerWon";
            }
        }
        else if(playerChoice == "Scissors"){
            if(computerChoice == "Paper"){
                Winner = "PlayerWon";
            }
            else if(computerChoice == "Rock"){
                Winner = "ComputerWon";
            }
        }
        if(computerChoice == playerChoice){
            Winner = "tie";
        }
        updateGameResults(Winner, computerChoice, playerChoice);
    }

}


