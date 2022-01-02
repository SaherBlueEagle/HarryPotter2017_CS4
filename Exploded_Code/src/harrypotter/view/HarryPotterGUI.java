package harrypotter.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.*;

import harrypotter.model.tournament.*;
public class HarryPotterGUI extends JFrame {
	private JPanel ChoosePlayersPanel;
	private JComboBox<String> Player1_Box;private JComboBox<String> Player2_Box;private JComboBox<String> Player3_Box;private JComboBox<String> Player4_Box;
	
	private JComboBox<String> Spell11_Box;private JComboBox<String> Spell12_Box;private JComboBox<String> Spell13_Box;
	private JComboBox<String> Spell21_Box;private JComboBox<String> Spell22_Box;private JComboBox<String> Spell23_Box;
	private JComboBox<String> Spell31_Box;private JComboBox<String> Spell32_Box;private JComboBox<String> Spell33_Box;
	private JComboBox<String> Spell41_Box;private JComboBox<String> Spell42_Box;private JComboBox<String> Spell43_Box;
	private ArrayList<String>ChooseSpells;
	private JTextArea Championnameentry ;private JTextArea Championnameentry2 ;private JTextArea Championnameentry3 ;private JTextArea Championnameentry4 ;
	private JPanel Gameover;
	private JPanel GameWins;
	private JLabel PlayerCh;

	private JPanel ItemInventroy;
	public JPanel getItemInventroy() {
		return ItemInventroy;
	}

	

	private JPanel FirstTask;private JPanel SecondTask;private JPanel ThirdTask;
	private JPanel Palyer_info_inventory;
//	Playerinfo_control.jpg
	public JPanel getGameWins() {
	
		return GameWins;
	}
	public HarryPotterGUI(){
		ImageIcon gameover = (new ImageIcon("output_gameover.gif")) ;  
		ImageIcon Winner = (new ImageIcon("tri.gif")) ;  
		//Set Main Window
				setTitle("HarryPotter");
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				setBounds(50, 0,1200,725);
				ChooseSpells = new ArrayList<String>(); 
				
				FirstTask= new ImagePanel(new ImageIcon("seabed01.jpg").getImage());
				SecondTask= new ImagePanel(new ImageIcon("arenamap.jpg").getImage());
				ThirdTask= new ImagePanel(new ImageIcon("task01.jpg").getImage());
				Palyer_info_inventory= new ImagePanel(new ImageIcon("ad_MainBG-700.jpg").getImage());	
				Palyer_info_inventory.setLayout(null);
				ItemInventroy = new ImagePanel(new ImageIcon("inv2.png").getImage());
				JLabel GameX = new JLabel(gameover);
				
				JLabel GameWin = new JLabel( Winner);
				GameX.setBounds(0, 0, 1200, 950);

				Gameover = new JPanel();
				GameWins= new JPanel();
		
				Gameover.add(GameX);
				GameWins.setLayout(null);
				 GameWins.setBackground(Color.BLACK);
				GameWin.setBounds(getBounds().width/2 - 150, 0, 300,300);
				GameWins.add(GameWin);
				
		//Choose Player Panel
		Player1_Box = new JComboBox<String>();  
		ImageIcon Welcomescreen =  new ImageIcon("7t0Azu.jpg");
		ChoosePlayersPanel = new ImagePanel(Welcomescreen.getImage());
		
		add(ChoosePlayersPanel, BorderLayout.CENTER);
		
	
	}
	public JPanel getGameover() {
		
		add(Gameover, BorderLayout.CENTER);
		
		return Gameover;
	}
	public void Add_Player(String Name,int c){
	//	String[] content = Data.split(","); //Split Data Name,House
		if (c==1){Player1_Box.addItem(Name);
		Player1_Box.updateUI();
		}
		if (c==2){Player2_Box.addItem(Name);
		Player2_Box.updateUI();
		}
		
		if (c==3){Player3_Box.addItem(Name);
		Player3_Box.updateUI();
		}
		if (c==4){Player4_Box.addItem(Name);
		Player4_Box.updateUI();
		}
		
 
 
	
	}
	
	
	
	
	
	
	
	//First task
	public JPanel getFirstTask() {
		return FirstTask;
	}
	public void Add_Map_Cell_First_Task(JLabel Cell){
		FirstTask.add(Cell);
		FirstTask.updateUI();
	}
	public void replace_Map_Cell_First_Task(Point OldCellPoint,Point NewCellPoint,JLabel mapcell){
	//	FirstTask.remove(FirstTask.getComponentAt(OldCellPoint));
	((JLabel)FirstTask.getComponentAt(OldCellPoint)).setIcon(null);
		mapcell.setBounds(NewCellPoint.x,NewCellPoint.y,mapcell.getWidth(),mapcell.getHeight());
	//	FirstTask.add(mapcell, index)
		FirstTask.add(mapcell);
		//FirstTask.add(NewCellPoint);
		FirstTask.updateUI();
	}
	//Second Task
	public JPanel getSecondTask() {
		return SecondTask;
	}
	public void Add_Map_Cell_Second_Task(JLabel Cell){
		SecondTask.add(Cell);
		SecondTask.updateUI();
	}
	//Third Task
		public JPanel getThirdTask() {
			return ThirdTask;
		}
	
		public void Add_Map_Cell_Third_Task(JLabel Cell){
			ThirdTask.add(Cell);
			ThirdTask.updateUI();
		}
	//player`s info inventory
		public JPanel getPalyer_info_inventory() {
			ChoosePlayersPanel.setVisible(false);
			 Palyer_info_inventory.updateUI();
			return Palyer_info_inventory;
		}
	
		
	
	
	//Champions Setup Panel an Entrys
	
	public void FisrtCampEntry(){
		//Champions Data For User Entry
				PlayerCh = new JLabel("Fisrt Champion =>");
				PlayerCh.setBounds(5,5,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions name label
				PlayerCh = new JLabel("Champion's name : ");
				PlayerCh.setBounds(150,5,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions name entry
				Championnameentry = new JTextArea("Champion1");
				Championnameentry.setBounds(300,5,150,25);
				Championnameentry.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(Championnameentry);
				//Champions type label
				PlayerCh = new JLabel("Champion's Type : ");
				PlayerCh.setBounds(460,5,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions type ComboBox
				Player1_Box = new JComboBox<String>();  
				Player1_Box.setBounds(460 + 150,5,310,30);
				Player1_Box.setForeground(Color.BLACK);
				Player1_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(Player1_Box);
				
				
				
				//Champions Spells Label
				PlayerCh = new JLabel("First Spell : ");
				PlayerCh.setBounds(5,60,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);
				
				//Champions spell ComboBox
				Spell11_Box = new JComboBox<String>();  
				Spell11_Box.setBounds(150,60,150,30);
				Spell11_Box.setForeground(Color.BLACK);
				Spell11_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell11_Box.addItem(Current);
				}
				Spell11_Box.updateUI();
				ChoosePlayersPanel.add(Spell11_Box);
				
				//Champions Spells Label
				PlayerCh = new JLabel("Second Spell : ");
				PlayerCh.setBounds(320,60,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);
						
				//Champions spell ComboBox
				Spell12_Box = new JComboBox<String>();  
				Spell12_Box.setBounds(320+150,60,150,30);
				Spell12_Box.setForeground(Color.BLACK);
				Spell12_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell12_Box.addItem(Current);
				}
				Spell12_Box.updateUI();
				ChoosePlayersPanel.add(Spell12_Box);
						
				//Champions Spells Label
				PlayerCh = new JLabel("Third Spell : ");
				PlayerCh.setBounds(320+150+150 +20,60,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);
						
						//Champions spell ComboBox
				Spell13_Box = new JComboBox<String>();  
				Spell13_Box.setBounds(320+150+150+150,60,150,30);
				Spell13_Box.setForeground(Color.BLACK);
				Spell13_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell13_Box.addItem(Current);
				}
				Spell13_Box.updateUI();
				ChoosePlayersPanel.add(Spell13_Box);

				//Champions Splitter label
				PlayerCh = new JLabel("========================================================================================================================================");
				PlayerCh.setBounds(5,80,1200,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);	
	
			
	}

	public JPanel getChoosePlayersPanel() {
		return ChoosePlayersPanel;
	}
	public void SecondCampEntry(){
		//Champions Data For User Entry
				PlayerCh = new JLabel("Second  Champion =>");
				PlayerCh.setBounds(5,95,200,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions name label
				PlayerCh = new JLabel("Champion's name : ");
				PlayerCh.setBounds(5,130,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions name entry
				Championnameentry2 = new JTextArea("Champion2");
				Championnameentry2.setBounds(150,130,150,25);
				Championnameentry2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(Championnameentry2);
				//Champions type label
				PlayerCh = new JLabel("Champion's Type : ");
				PlayerCh.setBounds(320,130,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions type ComboBox
				Player2_Box = new JComboBox<String>();  
				Player2_Box.setBounds(460,130,310,30);
				Player2_Box.setForeground(Color.BLACK);
				Player2_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				
				ChoosePlayersPanel.add(Player2_Box);
				
				//Champions Spells Label
				PlayerCh = new JLabel("First Spell : ");
				PlayerCh.setBounds(5,175,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				
				ChoosePlayersPanel.add(PlayerCh);
				
				//Champions spell ComboBox
				Spell21_Box = new JComboBox<String>();  
				Spell21_Box.setBounds(150,175,150,30);
				Spell21_Box.setForeground(Color.BLACK);
				Spell21_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell21_Box.addItem(Current);
				}
				Spell21_Box.updateUI();
				ChoosePlayersPanel.add(Spell21_Box);
				
				//Champions Spells Label
				PlayerCh = new JLabel("Second Spell : ");
				PlayerCh.setBounds(320,175,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);
						
				//Champions spell ComboBox
				Spell22_Box = new JComboBox<String>();  
				Spell22_Box.setBounds(320+150,175,150,30);
				Spell22_Box.setForeground(Color.BLACK);
				Spell22_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell22_Box.addItem(Current);
				}
				Spell22_Box.updateUI();
				ChoosePlayersPanel.add(Spell22_Box);
						
				//Champions Spells Label
				PlayerCh = new JLabel("Third Spell : ");
				PlayerCh.setBounds(320+150+150 +20,175,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);
						
						//Champions spell ComboBox
				Spell23_Box = new JComboBox<String>();  
				Spell23_Box.setBounds(320+150+150+150,175,150,30);
				Spell23_Box.setForeground(Color.BLACK);
				Spell23_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell23_Box.addItem(Current);
				}
				Spell23_Box.updateUI();
				ChoosePlayersPanel.add(Spell23_Box);

				//Champions Splitter label
				PlayerCh = new JLabel("========================================================================================================================================");
				PlayerCh.setBounds(5,200,1200,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);	
			
	}

	public void ThirdCampEntry(){
		//Champions Data For User Entry
				PlayerCh = new JLabel("Third Champion =>");
				PlayerCh.setBounds(5,220,200,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions name label
				PlayerCh = new JLabel("Champion's name : ");
				PlayerCh.setBounds(5,250,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions name entry
				Championnameentry3 = new JTextArea("Champion3");
				Championnameentry3.setBounds(150,250,150,25);
				Championnameentry3.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(Championnameentry3);
				//Champions type label
				PlayerCh = new JLabel("Champion's Type : ");
				PlayerCh.setBounds(320,250,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions type ComboBox
				Player3_Box = new JComboBox<String>();  
				Player3_Box.setBounds(460,250,310,30);
				Player3_Box.setForeground(Color.BLACK);
				Player3_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(Player3_Box);
				
				//Champions Spells Label
				PlayerCh = new JLabel("First Spell : ");
				PlayerCh.setBounds(5,295,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);
				
				//Champions spell ComboBox
				Spell31_Box = new JComboBox<String>();  
				Spell31_Box.setBounds(150,295,150,30);
				Spell31_Box.setForeground(Color.BLACK);
				Spell31_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell31_Box.addItem(Current);
				}
				Spell31_Box.updateUI();
				ChoosePlayersPanel.add(Spell31_Box);
				
				//Champions Spells Label
				PlayerCh = new JLabel("Second Spell : ");
				PlayerCh.setBounds(320,295,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);
						
				//Champions spell ComboBox
				Spell32_Box = new JComboBox<String>();  
				Spell32_Box.setBounds(320+150,295,150,30);
				Spell32_Box.setForeground(Color.BLACK);
				Spell32_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell32_Box.addItem(Current);
				}
				Spell32_Box.updateUI();
				ChoosePlayersPanel.add(Spell32_Box);
						
				//Champions Spells Label
				PlayerCh = new JLabel("Third Spell : ");
				PlayerCh.setBounds(320+150+150 +20,295,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);
						
						//Champions spell ComboBox
				Spell33_Box = new JComboBox<String>();  
				Spell33_Box.setBounds(320+150+150+150,295,150,30);
				Spell33_Box.setForeground(Color.BLACK);
				Spell33_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell33_Box.addItem(Current);
				}
				Spell33_Box.updateUI();
				ChoosePlayersPanel.add(Spell33_Box);

				//Champions Splitter label
				PlayerCh = new JLabel("========================================================================================================================================");
				PlayerCh.setBounds(5,320,1200,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);	
			
					
	}
	public void FourthCampEntry(){
		//Champions Data For User Entry
				PlayerCh = new JLabel("Fourth Champion =>");
				PlayerCh.setBounds(5,340,200,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions name label
				PlayerCh = new JLabel("Champion's name : ");
				PlayerCh.setBounds(5,370,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions name entry
				Championnameentry4= new JTextArea("Champion4");
				Championnameentry4.setBounds(150,370,150,25);
				Championnameentry4.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(Championnameentry4);
				//Champions type label
				PlayerCh = new JLabel("Champion's Type : ");
				PlayerCh.setBounds(320,370,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(PlayerCh);
				//Champions type ComboBox
				Player4_Box = new JComboBox<String>();  
				Player4_Box.setBounds(460,370,310,30);
				Player4_Box.setForeground(Color.BLACK);
				Player4_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				ChoosePlayersPanel.add(Player4_Box);
			
				
				
				//Champions Spells Label
				PlayerCh = new JLabel("First Spell : ");
				PlayerCh.setBounds(5,420,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);
				
				//Champions spell ComboBox
				Spell41_Box = new JComboBox<String>();  
				Spell41_Box.setBounds(150,420,150,30);
				Spell41_Box.setForeground(Color.BLACK);
				Spell41_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell41_Box.addItem(Current);
				}
				Spell41_Box.updateUI();
				ChoosePlayersPanel.add(Spell41_Box);
				
				//Champions Spells Label
				PlayerCh = new JLabel("Second Spell : ");
				PlayerCh.setBounds(320,420,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);
						
				//Champions spell ComboBox
				Spell42_Box = new JComboBox<String>();  
				Spell42_Box.setBounds(320+150,420,150,30);
				Spell42_Box.setForeground(Color.BLACK);
				Spell42_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell42_Box.addItem(Current);
				}
				Spell42_Box.updateUI();
				ChoosePlayersPanel.add(Spell42_Box);
						
				//Champions Spells Label
				PlayerCh = new JLabel("Third Spell : ");
				PlayerCh.setBounds(320+150+150 +20,420,150,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);
						
						//Champions spell ComboBox
				Spell43_Box = new JComboBox<String>();  
				Spell43_Box.setBounds(320+150+150+150,420,150,30);
				Spell43_Box.setForeground(Color.BLACK);
				Spell43_Box.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
				for (String Current :ChooseSpells){
					Spell43_Box.addItem(Current);
				}
				Spell43_Box.updateUI();
				ChoosePlayersPanel.add(Spell43_Box);

				//Champions Splitter label
				PlayerCh = new JLabel("========================================================================================================================================");
				PlayerCh.setBounds(5,450,1200,30);
				PlayerCh.setForeground(Color.GREEN);
				PlayerCh.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
				ChoosePlayersPanel.add(PlayerCh);	
				
				
				
	}

	public JTextArea getChampionnameentry2() {
		return Championnameentry2;
	}
	public JTextArea getChampionnameentry3() {
		return Championnameentry3;
	}
	public JTextArea getChampionnameentry4() {
		return Championnameentry4;
	}
	//Getters to get Champions Data

	public ArrayList<String> getChooseSpells() {
		return ChooseSpells;
	}
	public JTextArea getChampionnameentry() {
		return Championnameentry;
	}


	public JComboBox<String> getSpell11_Box() {
		return Spell11_Box;
	}
	public JComboBox<String> getSpell12_Box() {
		return Spell12_Box;
	}
	public JComboBox<String> getSpell13_Box() {
		return Spell13_Box;
	}
	public JComboBox<String> getSpell21_Box() {
		return Spell21_Box;
	}
	public JComboBox<String> getSpell22_Box() {
		return Spell22_Box;
	}
	public JComboBox<String> getSpell23_Box() {
		return Spell23_Box;
	}
	public JComboBox<String> getSpell31_Box() {
		return Spell31_Box;
	}
	public JComboBox<String> getSpell32_Box() {
		return Spell32_Box;
	}
	public JComboBox<String> getSpell33_Box() {
		return Spell33_Box;
	}
	public JComboBox<String> getSpell41_Box() {
		return Spell41_Box;
	}
	public JComboBox<String> getSpell42_Box() {
		return Spell42_Box;
	}
	public JComboBox<String> getSpell43_Box() {
		return Spell43_Box;
	}
	public JComboBox<String> getPlayer1_Box() {
		return Player1_Box;
	}
	public JComboBox<String> getPlayer2_Box() {
		return Player2_Box;
	}
	public JComboBox<String> getPlayer3_Box() {
		return Player3_Box;
	}
	public JComboBox<String> getPlayer4_Box() {
		return Player4_Box;
	}
}




//custom JPanel for background
class ImagePanel extends JPanel {

  private Image img;

  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
  }

  public ImagePanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }

  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }

}

