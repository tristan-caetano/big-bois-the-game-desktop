import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/*
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
*/

public class BigBois {

	// Font variables
	int tf;
	int sbf;
	int cbf;
	int ntf;

	// Creating windows, fonts, and buttons
	JFrame window;
	Container con;
	JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, versionPanel, levelPanel,
			staminaPanel;
	JLabel titleNameLabel, startButtonLabel, hpLabel, hpLabelNumber, enemyHP, versionLabel, levelLabel, staminaLabel;
	Font titleFont = new Font("Yu Gothic Regular", Font.PLAIN, tf);
	Font startButtonFont = new Font("Yu Gothic Regular", Font.PLAIN, sbf);
	Font choiceButtonFont = new Font("Yu Gothic Regular", Font.PLAIN, cbf);
	Font normalTextFont = new Font("Yu Gothic Regular", Font.PLAIN, ntf);
	JButton startButton, choice1, choice2, choice3, choice4, choice5, choice6, choice7, choice8;
	JTextArea mainTextArea;
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	ChoiceHandler choiceHandler = new ChoiceHandler();

	// Player Variables
	int health;
	int attack;
	int coins;
	int ehealthTemp;
	int level;
	int levelTracker;
	int xpMilestone;
	double levelMultiplier;
	int stamina;
	int numStaminaChonk;
	int staminaChonkAmt;
	int numHealthPacks;
	int healthPackAmt;
	int healthPackDropChance; // Drop Percentage
	int pp;
	int counter = 0;

	// Game Variables
	Random rand = new Random();
	int chance = 100;
	double doubleLevel;
	double multiplier;
	String position;
	String musicChoice;
	boolean muteMusic;

	/*
	 * Music Variables AudioPlayer MGP = AudioPlayer.player; AudioStream BGM;
	 * AudioData MD;
	 * 
	 * ContinuousAudioDataStream loop = null;
	 */

	// Enemy Variables
	int maxEnemyHealth = 1000;
	int maxEnemyAtt = 100;
	int enemyHealth = rand.nextInt(maxEnemyHealth);
	int bossChance;
	String[] enemies = { "Prime Time Bad Guy", "Normie", "Big Salad", "Lowly Salad", "Bad Crouton", "Apple Fanboy",
			"Long Boi" };
	String enemy = enemies[rand.nextInt(enemies.length)];

	// Creating resolution variables
	int currentScreenWidth;
	int currentScreenHeight;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BigBois();

	}

	/*
	 * public void music() { if (muteMusic) {
	 * 
	 * ContinuousAudioDataStream loop = null;
	 * 
	 * { try {
	 * 
	 * InputStream test = new FileInputStream(musicChoice);
	 * 
	 * BGM = new AudioStream(test);
	 * 
	 * MD = BGM.getData();
	 * 
	 * } catch (FileNotFoundException e) { System.out.print(e.toString());
	 * System.out.println("Error: File Not Found"); } catch (IOException error) {
	 * System.out.print(error.toString()); }
	 * 
	 * }
	 * 
	 * try { loop = new ContinuousAudioDataStream(MD); } catch (NullPointerException
	 * e) {
	 * 
	 * } MGP.start(loop);
	 * 
	 * }
	 * 
	 * }
	 */

	// Game Method
	public BigBois() {

		/*
		 * muteMusic = true; MGP.stop(BGM); musicChoice =
		 * "Title Sequence (Epic Journey).wav"; music(); MGP.start(BGM);
		 */

		// Initializing resolution variables
		currentScreenWidth = 800;
		currentScreenHeight = 600;

		// Font Sizes
		tf = 65;
		sbf = 27;
		cbf = 20;
		ntf = 25;

		// Setting font sizes
		titleFont = new Font("Yu Gothic Regular", Font.PLAIN, tf);
		startButtonFont = new Font("Yu Gothic Regular", Font.PLAIN, sbf);
		choiceButtonFont = new Font("Yu Gothic Regular", Font.PLAIN, cbf);
		normalTextFont = new Font("Yu Gothic Regular", Font.PLAIN, ntf);

		// Setting window
		window = new JFrame();
		window.setSize(currentScreenWidth, currentScreenHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setVisible(true);
		con = window.getContentPane();

		// Setting Panels and Labels
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100, 600, 150);
		titleNamePanel.setBackground(Color.black);
		titleNameLabel = new JLabel("Big Bois: The Game");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);

		versionPanel = new JPanel();
		versionPanel.setBounds(450, 500, 350, 400);
		versionPanel.setBackground(Color.black);
		versionLabel = new JLabel("alpha 0.4.0");
		versionLabel.setForeground(Color.white);
		versionLabel.setFont(startButtonFont);

		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 400, 200, 100);
		startButtonPanel.setBackground(Color.black);

		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(startButtonFont);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);

		versionPanel.add(versionLabel);
		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);
		con.add(titleNamePanel);
		con.add(startButtonPanel);
		con.add(versionPanel);
	}

	public void createGameScreen() {

		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);

		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(20, 100, 800, 250);
		mainTextPanel.setBackground(Color.black);
		mainTextPanel.setForeground(Color.white);
		con.add(mainTextPanel);

		mainTextArea = new JTextArea();
		mainTextArea.setBounds(20, 100, 800, 250);
		mainTextArea.setBackground(Color.black);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(normalTextFont);
		mainTextArea.setLineWrap(true);
		mainTextPanel.add(mainTextArea);
		mainTextArea.setEditable(false);

		choiceButtonPanel = new JPanel();
		choiceButtonPanel.setBounds(100, 350, 600, 150);
		choiceButtonPanel.setBackground(Color.black);
		con.add(choiceButtonPanel);

		choice1 = new JButton();
		choice1.setBackground(Color.black);
		choice1.setForeground(Color.red);
		choice1.setFont(choiceButtonFont);
		choiceButtonPanel.add(choice1);
		choice1.setFocusPainted(false);
		choice1.addActionListener(choiceHandler);
		choice1.setActionCommand("c1");

		choice2 = new JButton();
		choice2.setBackground(Color.black);
		choice2.setForeground(Color.orange);
		choice2.setFont(choiceButtonFont);
		choiceButtonPanel.add(choice2);
		choice2.setFocusPainted(false);
		choice2.addActionListener(choiceHandler);
		choice2.setActionCommand("c2");

		choice3 = new JButton();
		choice3.setBackground(Color.black);
		choice3.setForeground(Color.yellow);
		choice3.setFont(choiceButtonFont);
		choiceButtonPanel.add(choice3);
		choice3.setFocusPainted(false);
		choice3.addActionListener(choiceHandler);
		choice3.setActionCommand("c3");

		choice4 = new JButton();
		choice4.setBackground(Color.black);
		choice4.setForeground(Color.green);
		choice4.setFont(choiceButtonFont);
		choiceButtonPanel.add(choice4);
		choice4.setFocusPainted(false);
		choice4.addActionListener(choiceHandler);
		choice4.setActionCommand("c4");

		choice5 = new JButton();
		choice5.setBackground(Color.black);
		choice5.setForeground(Color.blue);
		choice5.setFont(choiceButtonFont);
		choiceButtonPanel.add(choice5);
		choice5.setFocusPainted(false);
		choice5.addActionListener(choiceHandler);
		choice5.setActionCommand("c5");

		choice6 = new JButton();
		choice6.setBackground(Color.black);
		choice6.setForeground(Color.magenta);
		choice6.setFont(choiceButtonFont);
		choiceButtonPanel.add(choice6);
		choice6.setFocusPainted(false);
		choice6.addActionListener(choiceHandler);
		choice6.setActionCommand("c6");

		choice7 = new JButton();
		choice7.setBackground(Color.black);
		choice7.setForeground(Color.lightGray);
		choice7.setFont(choiceButtonFont);
		choiceButtonPanel.add(choice7);
		choice7.setFocusPainted(false);
		choice7.addActionListener(choiceHandler);
		choice7.setActionCommand("c7");

		choice8 = new JButton();
		choice8.setBackground(Color.black);
		choice8.setForeground(Color.white);
		choice8.setFont(choiceButtonFont);
		choiceButtonPanel.add(choice8);
		choice8.setFocusPainted(false);
		choice8.addActionListener(choiceHandler);
		choice8.setActionCommand("c8");

		levelPanel = new JPanel();
		levelPanel.setBounds(15, 470, 200, 100);
		levelPanel.setBackground(Color.black);
		levelPanel.setLayout(new GridLayout(1, 4));
		con.add(levelPanel);

		levelLabel = new JLabel();
		levelLabel.setFont(startButtonFont);
		levelLabel.setForeground(Color.white);
		levelPanel.add(levelLabel);

		playerPanel = new JPanel();
		playerPanel.setBounds(15, 15, 1000, 50);
		playerPanel.setBackground(Color.black);
		playerPanel.setLayout(new GridLayout(1, 4));
		con.add(playerPanel);

		staminaPanel = new JPanel();
		staminaPanel.setBounds(515, 65, 300, 50);
		staminaPanel.setBackground(Color.black);
		staminaPanel.setLayout(new GridLayout(1, 4));
		con.add(staminaPanel);

		staminaLabel = new JLabel();
		staminaLabel.setFont(startButtonFont);
		staminaLabel.setForeground(Color.green);
		staminaPanel.add(staminaLabel);

		enemyHP = new JLabel();
		enemyHP.setFont(startButtonFont);
		enemyHP.setForeground(Color.red);
		playerPanel.add(enemyHP);

		hpLabel = new JLabel();
		hpLabel.setFont(startButtonFont);
		hpLabel.setForeground(Color.blue);
		playerPanel.add(hpLabel);

		playerSetup();
	}

	public void playerSetup() {

		// Player Variables
		health = 1000;
		attack = 100;
		coins = 0;
		ehealthTemp = 0;
		level = 0;
		levelTracker = 0;
		stamina = 100;
		numHealthPacks = 2;
		healthPackAmt = 300;
		healthPackDropChance = 70; // Drop Percentage
		numStaminaChonk = 0;
		staminaChonkAmt = 30;
		pp = 10;
		counter = 0;
		multiplier = 1;
		bossChance = 10; // Percentage

		// Naming labels
		hpLabel.setText("Health Bois: " + health);
		levelLabel.setText("Level: " + level);
		staminaLabel.setText("Stamina Bois: " + stamina);

		// Calling initiateGame() method (starting game)
		loadState();
		initiateGame();

	}

	public void initiateGame() {

		/*
		 * if (musicChoice != "Title Sequence (Epic Journey).wav") { MGP.stop(BGM);
		 * musicChoice = "Title Sequence (Epic Journey).wav"; music(); MGP.start(BGM);
		 * 
		 * try { loop = new ContinuousAudioDataStream(MD); } catch (NullPointerException
		 * e) {
		 * 
		 * } AudioPlayer.player.start(loop); }
		 */

		xpMilestone = (100 * (1 + level));

		if (levelTracker > xpMilestone) {
			levelUp();
		} else {

			System.out.println("lvlT " + levelTracker + " coins " + coins);

			choice1.setVisible(true);
			choice2.setVisible(true);
			choice3.setVisible(true);
			choice4.setVisible(true);
			choice5.setVisible(true);

			choice1.setText("Battle");
			choice2.setText("Shop");
			choice3.setText("Settings");
			choice4.setText("Save Game");
			choice5.setText("Load Game");
			choice6.setVisible(false);
			choice7.setVisible(false);
			choice8.setVisible(false);

			position = "initiateGame";
			levelLabel.setText("Level: " + level);
			staminaLabel.setText("Stamina Bois: " + stamina);
			versionLabel.setText(levelTracker + " / " + xpMilestone);

			enemyHP.setText("  ");

			if (counter == (multiplier) * 5) {
				multiplier++;
				if (rand.nextInt(100) < (bossChance * level)) {
					int[] bossHealthChance = { 2000, 3000, 4000 };
					int bossHealth = bossHealthChance[rand.nextInt(bossHealthChance.length)];

					String[] enemies = { "The Big Boi", "Ultimate Salad", "PoopyJuice", "Mononucleosis",
							"The Inhibition", "Big Sad", "Viscus Fart Chamber", "COVID-19", "Karen" };
					enemy = enemies[rand.nextInt(enemies.length)];

					enemyHealth = bossHealth;
				}

			} else {

				hpLabel.setText("Health Bois: " + health);

				String[] enemies = { "Prime Time Bad Guy", "Normie", "Big Salad", "Lowly Salad", "Bad Crouton",
						"Apple Fanboy", "Long Boi", "Sad Boi", "Orange Juice", "Bad Larry", "Big Kidney Stone",
						"Fart Chamber" };
				enemy = (enemies[rand.nextInt(enemies.length)]);
				enemyHealth = rand.nextInt(maxEnemyHealth);

				if (enemyHealth < 0) {
					enemyHealth *= -1;
				}

			}

			ehealthTemp = enemyHealth / 10;

			mainTextArea.setText("Wus poppin my guy? \nOh balls! You're here to heck stuff up yo!");

		}

	}

	public void saveState() {

		// Reading stats from file
		try (FileWriter save = new FileWriter("SaveState.txt")) {
			save.write("" + health);
			save.write("\n");
			save.write("" + stamina);
			save.write("\n");
			save.write("" + numHealthPacks);
			save.write("\n");
			save.write("" + level);
			save.write("\n");
			save.write("" + counter);
			save.write("\n");
			save.write("" + numStaminaChonk);
			save.write("\n");
			save.write("" + pp);
			save.write("\n");
			save.write("" + coins);
			save.write("\n");
			save.write("" + levelTracker);

			System.out.println("Your game has been saved.");
			mainTextArea.setText("Your game has been saved.");

		} catch (IOException e) {
			System.out.println("File Not Found");
			mainTextArea.setText("File Not Found");
			e.printStackTrace();
		}

	}

	public void loadState() {

		// Write stats to file
		try {
			File file = new File("SaveState.txt");
			Scanner sc = new Scanner(file);
			health = sc.nextInt();
			stamina = sc.nextInt();
			numHealthPacks = sc.nextInt();
			level = sc.nextInt();
			counter = sc.nextInt();
			numStaminaChonk = sc.nextInt();
			pp = sc.nextInt();
			coins = sc.nextInt();
			levelTracker = sc.nextInt();
			sc.close();
			System.out.println("Your game has been loaded.");
			mainTextArea.setText("Your game has been loaded.");

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			mainTextArea.setText("File Not Found");
			e.printStackTrace();
		}
		versionLabel.setText(levelTracker + " / " + ((level + 1) * 100));
		hpLabel.setText("Health Bois: " + health);
		levelLabel.setText("Level: " + level);
		staminaLabel.setText("Stamina Bois: " + stamina);
	}

	public void shop() {

		/*
		 * if (muteMusic) { MGP.stop(BGM); musicChoice = "Shop (Touch_Tone).wav";
		 * music(); MGP.start(BGM); try { loop = new ContinuousAudioDataStream(MD); }
		 * catch (NullPointerException e) {
		 * 
		 * } MGP.start(loop); }
		 */

		position = "shop";

		mainTextArea.setText("Click items to buy please and thanks!");

		hpLabel.setText("Coin Bois: " + coins);
		staminaLabel.setText("");
		levelLabel.setText("");

		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(true);
		choice7.setVisible(true);

		choice1.setText("1 Health Pack (50 CB)");
		choice2.setText("5 PP (20 CB)");
		choice3.setText("1 Stamina Chonk (50 CB)");
		choice7.setText("Back");
		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice8.setVisible(false);

	}

	public void settings() {

		position = "settings";

		mainTextArea.setText("Welcome to the settings!\nSorry for the excitement, it's quite boring here.");

		hpLabel.setText("");
		levelLabel.setText("");
		staminaLabel.setText("");
		versionLabel.setText("");

		choice1.setVisible(true);
		choice2.setVisible(false);
		choice3.setVisible(true);

		choice1.setText("Change Resolution");

		/*
		 * if (muteMusic == true) { choice2.setText("Mute Music");
		 * 
		 * } else if (muteMusic == false) { choice2.setText("Unmute Music");
		 * 
		 * }
		 */

		choice3.setText("Back");

		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice8.setVisible(false);

	}

	public void resolution() {

		position = "resolution";

		mainTextArea.setText("I actually believe that the screen size is quite nice,\nbut whatever.");

		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(true);

		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice7.setVisible(false);
		choice8.setVisible(false);

		choice1.setText("1920x1080");
		choice2.setText("800x600");
		choice3.setText("Back");
	}

	public void startBattle() {

		position = "startBattle";

		mainTextArea.setText("Boi, " + enemy + "'s got " + enemyHealth + " health left. \nWhat you doin now fam?");

		/*
		 * 
		 * if (muteMusic) { if (enemyHealth > 1001 && musicChoice != "Final_Boss.wav") {
		 * MGP.stop(BGM); musicChoice = "Final_Boss.wav"; music(); MGP.start(BGM);
		 * 
		 * try { loop = new ContinuousAudioDataStream(MD); } catch (NullPointerException
		 * e) {
		 * 
		 * } AudioPlayer.player.start(loop);
		 * 
		 * } }
		 */

		enemyHP.setText(enemy + "'s Health: " + enemyHealth + "  ");
		hpLabel.setText("Health Bois: " + health);
		levelLabel.setText("Level: " + level);
		staminaLabel.setText("Stamina Bois: " + stamina);

		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(true);
		choice4.setVisible(true);
		choice5.setVisible(true);
		choice6.setVisible(true);
		choice7.setVisible(true);
		choice8.setVisible(true);

		choice1.setText("Fight");
		choice2.setText("Special Attack");
		choice3.setText("Dronk Health Pack");
		choice4.setText("Cronch Stamina Chonk");
		choice5.setText("Run Away");
		choice6.setText("Inventory");
		choice7.setText("Block");
		choice8.setText("Take The Easy Way Out");

	}

	public void fightInstance() {

		position = "fightInstance";

		if (stamina > 10) {

			doubleLevel = level;

			levelMultiplier = ((doubleLevel / 10) + 1);
			double doubleDamageDealt = ((rand.nextInt(attack) * levelMultiplier));
			int damageDealt = (int) doubleDamageDealt;
			System.out.println(damageDealt / levelMultiplier);
			System.out.println(level + "  " + levelMultiplier);

			int damageTaken = rand.nextInt(maxEnemyAtt);
			health -= damageTaken;
			enemyHealth -= damageDealt;

			stamina -= (damageDealt / 10);

			if (stamina < 0) {

				stamina = 0;

			}

			staminaLabel.setText("Stamina Bois: " + stamina);

			mainTextArea.setText("Boi, you hit " + enemy + " for " + damageDealt + " damage,\nbig yeet. " + enemy
					+ " also hit you for " + damageTaken + ", oof. \nWhat's poppin next?");

			enemyHP.setText(enemy + "'s Health: " + enemyHealth + "  ");
			hpLabel.setText("Health Bois: " + health);

		} else {

			mainTextArea.setText("You are too tired to fight.");

		}
		choice1.setText("Fight");
		choice2.setText("Special Attack");
		choice3.setText("Dronk Health Pack");
		choice4.setText("Cronch Stamina Chonk");
		choice5.setText("Run Away");
		choice6.setText("Inventory");
		choice7.setText("Block");
		choice8.setText("Take The Easy Way Out");

		/*
		 * if (muteMusic) { if (health < 100 && musicChoice !=
		 * "Low on health (Bus_Da_Blockbuster).wav") {
		 * 
		 * lowHealthMusic();
		 * 
		 * } else { if (health > 100 && musicChoice !=
		 * "Title Sequence (Epic Journey).wav") {
		 * 
		 * MGP.stop(BGM); musicChoice = "Title Sequence (Epic Journey).wav"; music();
		 * MGP.start(BGM);
		 * 
		 * try { loop = new ContinuousAudioDataStream(MD); } catch (NullPointerException
		 * e) {
		 * 
		 * } AudioPlayer.player.start(loop);
		 * 
		 * } } }
		 */

		if (enemyHealth < 1 && health < 1) {
			doubleDeath();
		} else if (enemyHealth < 1) {
			winScreen();
		} else if (health < 1) {
			loseScreen();
		}
	}

	public void specialInstance() {

		position = "specialInstance";

		if (enemyHealth < 1 && health < 1) {
			doubleDeath();
		} else if (enemyHealth < 1) {
			winScreen();
		} else if (health < 1) {
			loseScreen();
		}

		/*
		 * if (muteMusic) { if (health < 100 && musicChoice !=
		 * "Low on health (Bus_Da_Blockbuster).wav") {
		 * 
		 * lowHealthMusic();
		 * 
		 * } else { if (health > 100 && musicChoice !=
		 * "Title Sequence (Epic Journey).wav") {
		 * 
		 * MGP.stop(BGM); musicChoice = "Title Sequence (Epic Journey).wav"; music();
		 * MGP.start(BGM);
		 * 
		 * try { loop = new ContinuousAudioDataStream(MD); } catch (NullPointerException
		 * e) {
		 * 
		 * } AudioPlayer.player.start(loop);
		 * 
		 * } } }
		 */

		choice1.setText("Big Slap Attack (2 PP)");
		choice2.setText("Cronchy Malk Attack (3 PP)");
		choice3.setText("Ask " + enemy + " politely, but firmly, to leave (5 PP)");
		choice4.setText("PP for Stamina (10 PP)");
		choice5.setText("Back");
		choice6.setVisible(false);
		choice7.setVisible(false);
		choice8.setVisible(false);
	}

	public void firstSpecial() {

		if (pp > 1) {

			if (stamina > 19) {

				int sDamageDealt = rand.nextInt(attack) * 2;
				int sDamageTaken = rand.nextInt(maxEnemyAtt);
				health -= sDamageTaken;
				enemyHealth -= sDamageDealt;
				stamina -= (sDamageDealt / 10);
				staminaLabel.setText("Stamina Bois: " + stamina);
				mainTextArea.setText("You gave " + enemy + " a phat slap for double damage!\nBtw that's " + sDamageDealt
						+ " damage.\nAlso you took like " + sDamageTaken + ". Lol heckin loser.");
				pp -= 2;

				hpLabel.setText("Health Bois: " + health);
				enemyHP.setText(enemy + "'s Health: " + enemyHealth + "  ");
			} else {
				mainTextArea.setText("You are too tired to do this move, tired boi");
			}

			if (enemyHealth < 1 && health < 1) {
				doubleDeath();
			} else if (enemyHealth < 1) {
				winScreen();
			} else if (health < 1) {
				loseScreen();
			}

			/*
			 * if (muteMusic) { if (health < 100 && musicChoice !=
			 * "Low on health (Bus_Da_Blockbuster).wav") {
			 * 
			 * lowHealthMusic();
			 * 
			 * } else { if (health > 100 && musicChoice !=
			 * "Title Sequence (Epic Journey).wav") {
			 * 
			 * MGP.stop(BGM); musicChoice = "Title Sequence (Epic Journey).wav"; music();
			 * MGP.start(BGM);
			 * 
			 * try { loop = new ContinuousAudioDataStream(MD); } catch (NullPointerException
			 * e) {
			 * 
			 * } AudioPlayer.player.start(loop);
			 * 
			 * } } }
			 */

		} else {
			mainTextArea.setText("You don't have enough PP for this move, dummy.");
			notEnoughPP();
		}

	}

	public void secondSpecial() {

		if (stamina > 29) {
			if (pp > 2) {
				int sDamageDealt = rand.nextInt(attack) * 3;
				int sDamageTaken = rand.nextInt(maxEnemyAtt);
				health -= sDamageTaken;
				enemyHealth -= sDamageDealt;
				stamina -= (sDamageDealt / 10);
				staminaLabel.setText("Stamina Bois: " + stamina);
				mainTextArea.setText("You ate the cronchy malk and gave \n" + enemy
						+ " a PHAT slap for THRICE damage.\nThat's like " + sDamageDealt
						+ " damage! Give or take.\nAlso you took like " + sDamageTaken + ". Lol heckin loser.");
				pp -= 3;
				hpLabel.setText("Health Bois: " + health);
				enemyHP.setText(enemy + "'s Health: " + enemyHealth + "  ");

				if (enemyHealth < 1 && health < 1) {
					doubleDeath();
				} else if (enemyHealth < 1) {
					winScreen();
				} else if (health < 1) {
					loseScreen();
				}

				/*
				 * if (muteMusic) { if (health < 100 && musicChoice !=
				 * "Low on health (Bus_Da_Blockbuster).wav") {
				 * 
				 * lowHealthMusic();
				 * 
				 * } else { if (health > 100 && musicChoice !=
				 * "Title Sequence (Epic Journey).wav") {
				 * 
				 * MGP.stop(BGM); musicChoice = "Title Sequence (Epic Journey).wav"; music();
				 * MGP.start(BGM);
				 * 
				 * try { loop = new ContinuousAudioDataStream(MD); } catch (NullPointerException
				 * e) {
				 * 
				 * } AudioPlayer.player.start(loop);
				 * 
				 * } } }
				 */

			} else {
				mainTextArea.setText("You don't have enough PP for this move, dummy.");
				notEnoughPP();
			}
		} else {
			mainTextArea.setText("You are too tired to do this move, tired boi");

		}
	}

	public void thirdSpecial() {

		position = "thirdSpecial";

		if (enemyHealth > 1000) {
			mainTextArea.setText("You can't ask " + enemy + " to leave, it's a boss!");
		} else {

			if (pp > 4) {
				int sChance = rand.nextInt(chance);
				if (sChance > 50) {
					mainTextArea.setText("You were able to pursuade " + enemy + " to leave.");
					pp -= 5;
					thirdSpecialWin();

				} else {
					mainTextArea.setText("Well, " + enemy + " told you to stop being a weenie and fight.");
					pp -= 5;
					thirdSpecialLose();
				}
			}
			// If the player doesn't have enough pp for the move
			else {

				mainTextArea.setText("You don't have enough PP for this move, dummy.");
				notEnoughPP();

			}
		}

	}

	public void thirdSpecialWin() {
		position = "thirdSpecialWin";

		enemyHP.setText("  ");

		choice1.setText("Neato, guess I'll kill some more!");
		choice2.setText("You know what, I'm leaving too.");
		choice3.setVisible(false);
		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice7.setVisible(false);
		choice8.setVisible(false);
	}

	public void thirdSpecialLose() {
		position = "thirdSpecialLose";

		choice1.setText("Fine, then don't leave. See if I care.");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice7.setVisible(false);
		choice8.setVisible(false);

	}

	public void fourthSpecial() {

		if (stamina < 100) {
			if (pp > 9) {

				stamina += 15;
				staminaLabel.setText("Stamina Bois: " + stamina);
				mainTextArea.setText("You got 15 more stamina!");
				pp -= 10;
				if (stamina > 100) {
					stamina = 100;
					staminaLabel.setText("Stamina Bois: " + stamina);
				}

			} else {
				mainTextArea.setText("You don't have enough PP for this move, dummy.");
				notEnoughPP();
			}
		} else {

			mainTextArea.setText("Your stamina is already maxed boi!");

		}

	}

	public void notEnoughPP() {

		position = "notEnoughPP";

		choice1.setText("Damn.");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice7.setVisible(false);
		choice8.setVisible(false);
	}

	public void healthPackInstance() {

		if (health >= 1500) {
			mainTextArea.setText("Your health is hecking maxed!");
		} else if (numHealthPacks > 0) {
			health += healthPackAmt;
			numHealthPacks--;
			hpLabel.setText("Health Bois: " + health);
			mainTextArea.setText("You dronked the health pack for " + healthPackAmt + " health back. \n You have "
					+ numHealthPacks + " left.");
			if (health >= 1500) {
				health = 1500;
				hpLabel.setText("Health Bois: " + health);
			}
		} else if (numHealthPacks <= 0) {
			mainTextArea.setText("You dronked the all the health packs!");
		}
		/*
		 * if (muteMusic) { if (health < 100 && musicChoice !=
		 * "Low on health (Bus_Da_Blockbuster).wav") {
		 * 
		 * lowHealthMusic();
		 * 
		 * } else { if (health > 100 && musicChoice !=
		 * "Title Sequence (Epic Journey).wav") {
		 * 
		 * MGP.stop(BGM); musicChoice = "Title Sequence (Epic Journey).wav"; music();
		 * MGP.start(BGM);
		 * 
		 * try { loop = new ContinuousAudioDataStream(MD); } catch (NullPointerException
		 * e) {
		 * 
		 * } AudioPlayer.player.start(loop);
		 * 
		 * } } }
		 */

	}

	public void staminaChonkInstance() {

		if (stamina >= 100) {
			mainTextArea.setText("Your stamina is hecking maxed!");
		} else if (numStaminaChonk > 0) {
			stamina += staminaChonkAmt;
			numStaminaChonk--;
			staminaLabel.setText("Stamina Bois: " + stamina);
			mainTextArea.setText("You cronched the stamina chonk for " + staminaChonkAmt + " stamina back. \n You have "
					+ numStaminaChonk + " left.");
			if (stamina >= 100) {
				stamina = 100;
				staminaLabel.setText("Stamina Bois: " + stamina);
			}
		} else if (numStaminaChonk <= 0) {
			mainTextArea.setText("You cronched the all the stamina chonks!");
		}

	}

	public void runInstance() {

		position = "runInstance";

		if (enemyHealth > 1000) {

			mainTextArea.setText("You can't run from a boss idiot.");

		} else {

			if (stamina > 19) {

				enemyHP.setText("  ");

				mainTextArea.setText("You ran away from " + enemy + ".");

				stamina -= 20;
				staminaLabel.setText("Stamina Bois: " + stamina);

				choice1.setText("That's cool dawg.");
				choice2.setText("Too scared to keep going dawg.");
				choice3.setVisible(false);
				choice4.setVisible(false);
				choice5.setVisible(false);
				choice6.setVisible(false);
				choice7.setVisible(false);
				choice8.setVisible(false);
			} else {

				mainTextArea.setText("You're too tired to run away.");

				choice1.setVisible(false);
				choice2.setVisible(false);
				choice3.setVisible(true);
				choice4.setVisible(false);
				choice5.setVisible(false);
				choice6.setVisible(false);
				choice7.setVisible(false);
				choice8.setVisible(false);

				choice3.setText("Darn.");

			}
		}
	}

	public void ppCheckInstance() {

		position = "ppCheckInstance";

		mainTextArea.setText("PP: " + pp + "\nHealth Pack: " + numHealthPacks + "\nCB: " + coins + "\nStamina Chonks: "
				+ numStaminaChonk);

		choice1.setText("Neat broski.");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice7.setVisible(false);
		choice8.setVisible(false);

	}

	public void blockInstance() {

		position = "blockInstance";

		System.out.println("\tYou did your best to block " + enemy + "'s attack.");
		double bChance = rand.nextInt(chance);
		double block = (bChance / 100);
		int damageTaken = rand.nextInt(maxEnemyAtt);
		double blockedDamage = damageTaken - (block * damageTaken);
		health -= Math.round(blockedDamage);

		stamina += (block * 15);

		if (stamina > 100) {
			stamina = 100;
		}

		/*
		 * if (muteMusic) { if (health < 100 && musicChoice !=
		 * "Low on health (Bus_Da_Blockbuster).wav") {
		 * 
		 * lowHealthMusic();
		 * 
		 * } else { if (health > 100 && musicChoice !=
		 * "Title Sequence (Epic Journey).wav") {
		 * 
		 * MGP.stop(BGM); musicChoice = "Title Sequence (Epic Journey).wav"; music();
		 * MGP.start(BGM);
		 * 
		 * try { loop = new ContinuousAudioDataStream(MD); } catch (NullPointerException
		 * e) {
		 * 
		 * } AudioPlayer.player.start(loop);
		 * 
		 * } } }
		 */

		hpLabel.setText("Health Bois: " + health);
		staminaLabel.setText("Stamina Bois: " + stamina);

		mainTextArea.setText("You blocked " + Math.round(bChance) + "% of " + enemy + "'s attack. \nYou took "
				+ Math.round(blockedDamage) + " damage.");

		if (enemyHealth < 1 && health < 1) {
			doubleDeath();
		} else if (enemyHealth < 1) {
			winScreen();
		} else if (health < 1) {
			loseScreen();
		}
	}

	public void suicideInstance() {

		position = "suicideInstance";
		mainTextArea.setText("You actually decided to kill yourself. \nYou killed " + counter + " enemies.");

		health = 0;
		hpLabel.setText("Health Bois: " + health);

		choice1.setText("It do be like that sometimes.");
		choice2.setText("I'm just the big sad right now and can't keep going on");
		choice3.setVisible(false);
		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice7.setVisible(false);
		choice8.setVisible(false);

		counter = 0;
		numHealthPacks = 2;
		pp = 10;
		coins = 0;
		multiplier = 1;
		level = 0;
		stamina = 100;
		levelTracker = 0;

	}

	public void winScreen() {

		position = "winScreen";

		choice1.setText("I'm beating sum more mofos.");
		choice2.setText("The hek, I'm leavin boiiiiii.");
		choice3.setVisible(false);
		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice7.setVisible(false);
		choice8.setVisible(false);

		if (stamina < 100) {

			stamina += 20;
			if (stamina > 100) {
				stamina = 100;
			}
		}
		counter++;
		coins += Math.abs(ehealthTemp);
		levelTracker += ehealthTemp;

		enemyHP.setText(enemy + "'s Health: " + 0 + "  ");

		if (rand.nextInt(100) < healthPackDropChance) {
			numHealthPacks++;
			numStaminaChonk++;
			mainTextArea.setText("Thank god. " + enemy + " has passed the HECK away. \nHeck yeah! The " + enemy
					+ " dropped a\nhealth pack and stamina chonk. You now have " + numHealthPacks
					+ " health pack(s) \nand " + numStaminaChonk + " stamina chonk(s).\nYou got "
					+ Math.abs(ehealthTemp) + " CB and 5 more pp!\nYou have killed " + counter + " enemie(s) so far.");
			pp += 5;
		} else {

			coins += ehealthTemp;
			mainTextArea.setText("Thank god. " + enemy + " has passed the HECK away.\nYou got " + Math.abs(ehealthTemp)
					+ " CB!\nYou have killed " + counter + " enemie(s) so far and got 5 more pp!.");
			System.out.println("");
			pp += 5;
		}

	}

	public void loseScreen() {

		position = "loseScreen";

		mainTextArea.setText("Looks like you hecken died bro. \n " + enemy + " defiled your corpse. \n You killed "
				+ counter + " enemies.");

		health = 0;
		hpLabel.setText("Health Bois: " + health);

		choice1.setText("Guess I'll come back from the dead to beat more mofos.");
		choice2.setText("K. Cool. Guess I'll leave then.");
		choice3.setVisible(false);
		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice7.setVisible(false);
		choice8.setVisible(false);

		counter = 0;
		numHealthPacks = 2;
		pp = 10;
		coins = 0;
		multiplier = 1;
		level = 0;
		stamina = 100;
		levelTracker = 0;

	}

	public void doubleDeath() {

		position = "loseScreen";
		mainTextArea.setText(
				"So, uh, you both died at the same time.\nThat's pretty rare so, good job?\nYou're still dead though.");

		health = 0;
		hpLabel.setText("Health Bois: " + health);
		enemyHP.setText(enemy + "'s Health: " + 0 + "  ");

		counter = 0;
		numHealthPacks = 2;
		pp = 10;
		coins = 0;
		multiplier = 1;
		level = 0;
		stamina = 100;
		levelTracker = 0;

		choice1.setText("Well, that sucks. Guess I'll be a good sport and play again.");
		choice2.setText("Well, that sucks. Guess I'll be a poopyjuice and quit.");
		choice3.setVisible(false);
		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice7.setVisible(false);
		choice8.setVisible(false);
	}

	public void levelUp() {

		position = "levelUp";

		if (level < 10) {
			levelTracker = 0;
			level++;
			mainTextArea.setText("Aye guy, you leveled up! \nHecken real nice!\nYou are now level " + level + ".");

			levelLabel.setText("Level: " + level);
			enemyHP.setText("  ");
		} else if (level > 9) {

			mainTextArea
					.setText("Welp, you're at max level, but here's some stuff!\nYou got 5 health packs and 30 pp!");
			numHealthPacks += 5;
			pp += 30;
		}

		if (health < 1000) {
			health = 1000;
			stamina = 100;
		} else {
			stamina = 100;
		}

		xpMilestone = (100 * (1 + level));

		choice1.setText("Neato fajito.");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
		choice5.setVisible(false);
		choice6.setVisible(false);
		choice7.setVisible(false);
		choice8.setVisible(false);
	}

	/*
	 * public void lowHealthMusic() { if (muteMusic) { MGP.stop(BGM); musicChoice =
	 * "Low on health (Bus_Da_Blockbuster).wav"; music(); MGP.start(BGM);
	 * 
	 * } }
	 */

	public class TitleScreenHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			createGameScreen();
		}

	}

	public class ChoiceHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {

			String yourChoice = event.getActionCommand();

			switch (position) {
			case "initiateGame":
				switch (yourChoice) {
				case "c1":
					startBattle();
					break;
				case "c2":
					shop();
					break;
				case "c3":
					settings();
					break;
				case "c4":
					saveState();
					break;
				case "c5":
					loadState();
					break;
				}
				break;

			case "shop":
				switch (yourChoice) {
				case "c1":
					if (coins >= 50) {
						numHealthPacks++;
						mainTextArea.setText(
								"You got another health pack!\nYou now have " + numHealthPacks + " health packs!");
						coins -= 50;
						hpLabel.setText("Coin Bois: " + coins);
						break;
					} else {
						mainTextArea.setText("You don't have enough coin bois to purchase that, idiot.");
					}
					break;
				case "c2":
					if (coins >= 20) {
						pp += 5;
						mainTextArea.setText("You got more pp!\nYou now have " + pp + " pp!");
						coins -= 20;
						hpLabel.setText("Coin Bois: " + coins);
						break;
					} else {
						mainTextArea.setText("You don't have enough coin bois to purchase that, idiot.");
					}
					break;
				case "c3":
					if (coins >= 50) {
						numStaminaChonk++;
						mainTextArea.setText(
								"You got another stamina chonk!\nYou now have " + numStaminaChonk + " stamina chonks!");
						coins -= 50;
						hpLabel.setText("Coin Bois: " + coins);
						break;
					} else {
						mainTextArea.setText("You don't have enough coin bois to purchase that, idiot.");
					}
					break;

				case "c7":
					initiateGame();
					break;
				}
				break;
			case "startBattle":
				switch (yourChoice) {
				case "c1":
					fightInstance();
					break;
				case "c2":
					specialInstance();
					break;
				case "c3":
					healthPackInstance();
					break;
				case "c4":
					staminaChonkInstance();
					break;
				case "c5":
					runInstance();
					break;
				case "c6":
					ppCheckInstance();
					break;
				case "c7":
					blockInstance();
					break;
				case "c8":
					suicideInstance();
					break;

				}
				break;

			case "fightInstance":
				switch (yourChoice) {
				case "c1":
					fightInstance();
					break;
				case "c2":
					specialInstance();
					break;
				case "c3":
					healthPackInstance();
					break;
				case "c4":
					staminaChonkInstance();
					break;
				case "c5":
					runInstance();
					break;
				case "c6":
					ppCheckInstance();
					break;
				case "c7":
					blockInstance();
					break;
				case "c8":
					suicideInstance();
					break;
				}
				break;

			case "specialInstance":
				switch (yourChoice) {
				case "c1":
					firstSpecial();
					break;
				case "c2":
					secondSpecial();
					break;
				case "c3":
					thirdSpecial();
					break;
				case "c4":
					fourthSpecial();
					break;
				default:
					startBattle();
					break;
				}
				break;

			case "notEnoughPP":
				switch (yourChoice) {
				case "c1":
					startBattle();
					break;
				}
				break;

			case "thirdSpecial":
				switch (yourChoice) {
				case "c1":
					startBattle();
					break;
				}
				break;

			case "thirdSpecialWin":
				switch (yourChoice) {
				case "c1":
					initiateGame();
					break;
				case "c2":
					System.exit(JFrame.EXIT_ON_CLOSE);
					break;
				}
				break;

			case "thirdSpecialLose":
				switch (yourChoice) {
				case "c1":
					startBattle();
				}
				break;

			case "winScreen":
				switch (yourChoice) {

				case "c1":
					initiateGame();
					break;
				case "c2":
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
				break;

			case "loseScreen":
				switch (yourChoice) {

				case "c1":
					initiateGame();
					health = 1000;
					hpLabel.setText("Health Bois: " + health);
					break;
				case "c2":
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
				break;

			case "suicideInstance":
				switch (yourChoice) {

				case "c1":
					initiateGame();
					health = 1000;
					hpLabel.setText("Health Bois: " + health);
					break;
				case "c2":
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
				break;

			case "runInstance":
				switch (yourChoice) {
				case "c1":
					initiateGame();
					break;
				case "c2":
					System.exit(JFrame.EXIT_ON_CLOSE);
					break;
				case "c3":
					startBattle();
					break;
				}
				break;

			case "ppCheckInstance":
				switch (yourChoice) {
				case "c1":
					startBattle();
					break;
				}
				break;

			case "blockInstance":
				switch (yourChoice) {
				case "c1":
					fightInstance();
					break;
				case "c2":
					specialInstance();
					break;
				case "c3":
					healthPackInstance();
					break;
				case "c4":
					staminaChonkInstance();
					break;
				case "c5":
					runInstance();
					break;
				case "c6":
					ppCheckInstance();
					break;
				case "c7":
					blockInstance();
					break;
				case "c8":
					suicideInstance();
					break;
				}
				break;

			case "levelUp":
				switch (yourChoice) {
				case "c1":
					initiateGame();
					break;
				}
				break;
			case "settings":
				switch (yourChoice) {
				case "c1":
					resolution();
					break;
				case "c2":
					/*
					 * if (muteMusic == true) { muteMusic = false; MGP.stop(BGM); settings(); } else
					 * if (muteMusic == false) { muteMusic = true; MGP.start(BGM); settings(); }
					 */
					break;
				case "c3":
					initiateGame();
					break;
				}
				break;

			case "resolution":
				switch (yourChoice) {
				case "c1":
					window.setSize(1920, 1080);
					titleNamePanel.setBounds(240, 180, 1440, 270);
					versionPanel.setBounds(1080, 900, 840, 720);
					startButtonPanel.setBounds(720, 720, 480, 180);
					mainTextPanel.setBounds(48, 180, 1920, 450);
					mainTextArea.setBounds(48, 180, 1920, 450);
					choiceButtonPanel.setBounds(240, 630, 1440, 270);
					levelPanel.setBounds(36, 846, 480, 180);
					playerPanel.setBounds(36, 27, 2400, 90);
					staminaPanel.setBounds(1236, 119, 720, 90);
					tf = 100;
					sbf = 55;
					cbf = 40;
					ntf = 45;
					titleFont = new Font("Yu Gothic Regular", Font.PLAIN, tf);
					startButtonFont = new Font("Yu Gothic Regular", Font.PLAIN, sbf);
					choiceButtonFont = new Font("Yu Gothic Regular", Font.PLAIN, cbf);
					normalTextFont = new Font("Yu Gothic Regular", Font.PLAIN, ntf);
					levelLabel.setFont(startButtonFont);
					versionPanel.add(versionLabel);
					titleNamePanel.add(titleNameLabel);
					startButtonPanel.add(startButton);
					mainTextArea.setFont(normalTextFont);
					choice1.setFont(choiceButtonFont);
					choice2.setFont(choiceButtonFont);
					choice3.setFont(choiceButtonFont);
					choice4.setFont(choiceButtonFont);
					choice5.setFont(choiceButtonFont);
					choice6.setFont(choiceButtonFont);
					choice7.setFont(choiceButtonFont);
					choice8.setFont(choiceButtonFont);
					staminaLabel.setFont(startButtonFont);
					enemyHP.setFont(startButtonFont);
					hpLabel.setFont(startButtonFont);
					versionLabel.setFont(startButtonFont);
					break;

				case "c2":
					window.setSize(800, 600);
					titleNamePanel.setBounds(100, 100, 600, 150);
					versionPanel.setBounds(450, 500, 350, 400);
					startButtonPanel.setBounds(300, 400, 200, 100);
					mainTextPanel.setBounds(20, 100, 800, 250);
					mainTextArea.setBounds(20, 100, 800, 250);
					choiceButtonPanel.setBounds(100, 350, 600, 150);
					levelPanel.setBounds(15, 470, 200, 100);
					playerPanel.setBounds(15, 15, 1000, 50);
					staminaPanel.setBounds(515, 65, 300, 50);
					tf = 65;
					sbf = 27;
					cbf = 20;
					ntf = 25;
					titleFont = new Font("Yu Gothic Regular", Font.PLAIN, tf);
					startButtonFont = new Font("Yu Gothic Regular", Font.PLAIN, sbf);
					choiceButtonFont = new Font("Yu Gothic Regular", Font.PLAIN, cbf);
					normalTextFont = new Font("Yu Gothic Regular", Font.PLAIN, ntf);
					levelLabel.setFont(startButtonFont);
					versionPanel.add(versionLabel);
					titleNamePanel.add(titleNameLabel);
					startButtonPanel.add(startButton);
					mainTextArea.setFont(normalTextFont);
					choice1.setFont(choiceButtonFont);
					choice2.setFont(choiceButtonFont);
					choice3.setFont(choiceButtonFont);
					choice4.setFont(choiceButtonFont);
					choice5.setFont(choiceButtonFont);
					choice6.setFont(choiceButtonFont);
					choice7.setFont(choiceButtonFont);
					choice8.setFont(choiceButtonFont);
					staminaLabel.setFont(startButtonFont);
					enemyHP.setFont(startButtonFont);
					hpLabel.setFont(startButtonFont);
					versionLabel.setFont(startButtonFont);
					break;

				case "c3":
					settings();
					break;
				}
				break;

			}

		}
	}
}
