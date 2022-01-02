package harrypotter.controller;
import javax.swing.*;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
import harrypotter.model.character.*;
import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.TaskListener;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.TreasureCell;
import harrypotter.model.world.WallCell;
import harrypotter.view.*;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HarryPotterController implements ActionListener,TaskListener{
	private HarryPotterGUI HarryPotterGUI;
	private Tournament Tournament;
	private ArrayList<Wizard> Wizards;
	private JTextArea ChampionFullInfo;
	GryffindorWizard GryffindorWizard = new GryffindorWizard("GryffindorWizard");
	HufflepuffWizard HufflepuffWizard = new HufflepuffWizard("HufflepuffWizard");
	RavenclawWizard RavenclawWizard = new RavenclawWizard("RavenclawWizard");
	SlytherinWizard SlytherinWizard = new SlytherinWizard("SlytherinWizard");
	private JTextArea FourChampionsInfo ;
	private JPanel PlayerInventory ;
	private JLabel chiname;//Champion Name Label
	private JLabel chhp;//champion HP label
	private JLabel chip;//champion HP label
	private JScrollPane sp;
	private JButton Start_Tournement;
	private Boolean inFirstTask ;private Boolean inSecondTask ;private Boolean inThirdTask ;
	ArrayList<JLabel> Cells = new ArrayList<JLabel>();
	private JLabel Taskinfo ;
	private JLabel Wizardtypelabel ;
	private JPanel AttackList;
	private JComboBox<String> currentspells;
	private boolean infoviewed;
	private boolean Correctvisiabilty;
	private ArrayList<String>winnernotifier;






	public HarryPotterController () throws IOException{
		//Identify Wizards (Champion Types)
		Tournament = new Tournament();
		winnernotifier = new ArrayList<String>();
		HarryPotterGUI = new HarryPotterGUI();
		infoviewed = false;
		 PlayerInventory=  new ImagePanel(new ImageIcon("inv2.png").getImage());
		 PlayerInventory.setBounds(23,150,295,125);
		 ChampionFullInfo = new JTextArea();
		Wizards = new ArrayList<Wizard>(); 
		Wizards.add(GryffindorWizard);Wizards.add(HufflepuffWizard);Wizards.add(RavenclawWizard);Wizards.add(SlytherinWizard);
		for (int i = 0;i<Tournament.getSpells().size();i++){
			
			if (Tournament.getSpells().get(i) instanceof RelocatingSpell){
				HarryPotterGUI.getChooseSpells().add("Relocate " + Tournament.getSpells().get(i).getName() + "cost " +Tournament.getSpells().get(i).getCost() + " ip");
			}

			if (Tournament.getSpells().get(i) instanceof HealingSpell){
				HarryPotterGUI.getChooseSpells().add("Heal " + Tournament.getSpells().get(i).getName() + "cost " +Tournament.getSpells().get(i).getCost() + " ip");
			}

			if (Tournament.getSpells().get(i) instanceof DamagingSpell){
				HarryPotterGUI.getChooseSpells().add("Damaging " + Tournament.getSpells().get(i).getName() + "cost " +Tournament.getSpells().get(i).getCost() + " ip");
			}
			
		
			}
		HarryPotterGUI.FisrtCampEntry();
		for (int i = 0; i <Wizards.size(); i++){
			try{
				Wizard Player = Wizards.get(i);
				HarryPotterGUI.Add_Player(Player.getName(),1);
				
			}catch(Exception ex){
				System.out.print("Here" + ex.getMessage());
			}
			}
	
		HarryPotterGUI.SecondCampEntry();
		for (int i = 0; i <Wizards.size(); i++){
			try{
				Wizard Player = Wizards.get(i);
				HarryPotterGUI.Add_Player(Player.getName(),2);
				HarryPotterGUI.getPlayer2_Box().setSelectedIndex(1);
				
			}catch(Exception ex){
				System.out.print("Here" + ex.getMessage());
			}
			}
		
		HarryPotterGUI.ThirdCampEntry();
		for (int i = 0; i <Wizards.size(); i++){
			try{
				Wizard Player = Wizards.get(i);
				HarryPotterGUI.Add_Player(Player.getName(),3);
				HarryPotterGUI.getPlayer3_Box().setSelectedIndex(2);
			}catch(Exception ex){
				System.out.print("Here" + ex.getMessage());
			}
			}

		HarryPotterGUI.FourthCampEntry();
		for (int i = 0; i <Wizards.size(); i++){
			try{
				Wizard Player = Wizards.get(i);
				HarryPotterGUI.Add_Player(Player.getName(),4);
				HarryPotterGUI.getPlayer4_Box().setSelectedIndex(3);
			}catch(Exception ex){
				System.out.print("Here" + ex.getMessage());
			}
			}
		
		this.ChampionsButtonSetter();
		FourChampionsInfo = new JTextArea("Champion4");
		currentspells = new JComboBox<String>();
		FourChampionsInfo.setBounds(5,480,1200,400);
		FourChampionsInfo.setForeground(Color.GREEN);
		FourChampionsInfo.setEditable(false);
		FourChampionsInfo.setText("Current Chossen Champions : ");
		FourChampionsInfo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		FourChampionsInfo.setBackground(new Color(0,0,0,0));
		
		ChampionFullInfo.setBounds(5,75,295,130);
		ChampionFullInfo.setEditable(false);
		ChampionFullInfo.setForeground(Color.YELLOW);
		ChampionFullInfo.setBackground(Color.BLACK);
		ChampionFullInfo.setVisible(true);
		ChampionFullInfo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		
		JButton forward = new JButton("Forward");JButton backward = new JButton("Backward");JButton left = new JButton("Left");JButton right = new JButton("Right");JButton UseChoosenSpell = new JButton("Use Spell");
		forward.addActionListener(this);backward.addActionListener(this);left.addActionListener(this);right.addActionListener(this);UseChoosenSpell.addActionListener(this);
		
		forward.setBounds(100,5,100,25);
		backward.setBounds(100,50,100,25);
		left.setBounds(5,25,90,25);
		right.setBounds(205,25,90,25);
		
		UseChoosenSpell.setBackground(Color.GREEN);
		JButton endturn = new JButton("End Turn");endturn.setBounds(5,85,90,25);endturn.addActionListener(this);
		JButton usepotion = new JButton("Use Potion"); usepotion.setBounds(100,85,100,25); usepotion.addActionListener(this);
		JButton viewspellinfo = new JButton("Full Info"); viewspellinfo.setBounds(210,85,100,25);viewspellinfo.addActionListener(this);

		PlayerInventory.add(forward);
		PlayerInventory.add(backward);
		PlayerInventory.add(left);
		PlayerInventory.add(right);
		Wizardtypelabel= new JLabel();		
		 Wizardtypelabel.setBounds(25,80,300,30);
		 Wizardtypelabel.setForeground(Color.YELLOW);
		 Wizardtypelabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		 currentspells.setBounds(92,3,250,25);
		PlayerInventory.add(currentspells);
	
		PlayerInventory.add(endturn );
		PlayerInventory.add( usepotion );
		PlayerInventory.add(viewspellinfo);
		
		sp = new JScrollPane(ChampionFullInfo);
		sp.setBounds(25,275,295,130);
		
		
		sp.setBackground(Color.BLACK);
		HarryPotterGUI.getItemInventroy().setBounds(25,320, 290, 280);
		HarryPotterGUI.getItemInventroy().setLayout(new GridLayout(4,4));
		
		AttackList = new ImagePanel(new ImageIcon("attacklist.png").getImage());
	
		
		
		UseChoosenSpell.setBounds(160,30,90,20);
		AttackList.setBounds(20,625,280,50);
		JLabel XStart = new JLabel ("Current Spells");
		 XStart.setForeground(Color.YELLOW);
		AttackList.add(XStart);
		AttackList.add(currentspells);
		XStart.setBounds(5,5,90,20);
		//currentspells.setBounds(20,5,100,25);
		AttackList.add(UseChoosenSpell);
		//HarryPotterGUI.getItemInventroy().add(AttackList);
		//HarryPotterGUI.getPalyer_info_inventory().
		//PlayerInventory.add(sp);
		PlayerInventory.updateUI();
	//	HarryPotterGUI.getPalyer_info_inventory().add(sp);
		HarryPotterGUI.getChoosePlayersPanel().add(FourChampionsInfo);	
		
		HarryPotterGUI.getChoosePlayersPanel().updateUI();
		
		HarryPotterGUI.setVisible(true);
		Correctvisiabilty = false;
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void ChampionsButtonSetter(){
		JButton SetFirst = new JButton("Set First Champion");
		SetFirst.setBounds(320+150+150+150+150 + 100,60,150,30);
		SetFirst.addActionListener(this);
		HarryPotterGUI.getChoosePlayersPanel().add(SetFirst);
		JButton SetSecond = new JButton("Set Second Champion");
		SetSecond.setBounds(320+150+150+150+150 + 100,175,160,30);
		SetSecond.addActionListener(this);
		HarryPotterGUI.getChoosePlayersPanel().add(SetSecond);
		JButton SetThird= new JButton("Set Third Champion");
		SetThird.setBounds(320+150+150+150+150 + 100,295,150,30);
		SetThird.addActionListener(this);
		HarryPotterGUI.getChoosePlayersPanel().add(SetThird);
		JButton SetFourth= new JButton("Set Fourth Champion");
		SetFourth.setBounds(320+150+150+150+150 + 100,420,160,30);
		SetFourth.addActionListener(this);
		HarryPotterGUI.getChoosePlayersPanel().add(SetFourth);
		Start_Tournement= new JButton("Start Tournement");
		Start_Tournement.setBounds(320+150+150+150+150 + 100,470,160,30);
		Start_Tournement.addActionListener(this);
		Start_Tournement.setEnabled(false);
		HarryPotterGUI.getChoosePlayersPanel().add(Start_Tournement);
		HarryPotterGUI.getChoosePlayersPanel().updateUI();
	}












	public static void main(String[] args) throws IOException {
		new HarryPotterController() ;
	}
	int CCounter=0;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JButton Pressed_Button = (JButton) arg0.getSource();
		
	
		if(Pressed_Button.getText().equals("Start Tournement")){
			
			HarryPotterGUI.getPalyer_info_inventory().add(PlayerInventory);
			HarryPotterGUI.getPalyer_info_inventory().add( Wizardtypelabel);
			HarryPotterGUI.getFirstTask().setLayout(new GridLayout(0, 10));
			HarryPotterGUI.add(	HarryPotterGUI.getFirstTask(),BorderLayout.CENTER);
			HarryPotterGUI.getPalyer_info_inventory().add(AttackList);
			try {
				Tournament.beginTournament();
				Tournament.getFirstTask().setListener(this);
				inFirstTask = true ;
				inSecondTask = false ;
				inThirdTask = false ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//HarryPotterGUI.getFirstTask().setVisible(true);

			this. UpdateCellsFirstTask();
			
			
			
			
			
			HarryPotterGUI.getFirstTask().setBackground(Color.BLACK);
			HarryPotterGUI.validate();
			HarryPotterGUI.add(	HarryPotterGUI.getPalyer_info_inventory(),BorderLayout.EAST);
			Taskinfo = new JLabel("Current Task : First Task");
			Taskinfo.setBounds(25,19,300,30);
			Taskinfo.setForeground(Color.YELLOW);
			Taskinfo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			HarryPotterGUI.getPalyer_info_inventory().add(Taskinfo);
			
			//##################################################################################
			//#############################Inventory Panel######################################
			//##################################################################################
			
			Wizard CurrentWizard = (Wizard)Tournament.getFirstTask().getCurrentChamp();
			
		
			/*
			for (Collectible item :CurrentWizard.getInventory()){
			
				if(item instanceof Potion){
					JLabel CItem = new JLabel();
					Image img = (new ImageIcon("HPotion.png")).getImage() ;  
					Image newimg = img.getScaledInstance(40,40,  java.awt.Image.SCALE_SMOOTH ) ;  
					ImageIcon icon = new ImageIcon( newimg );
					CItem .setIcon(icon);
					PlayerInventory.add(CItem);
				}

			}
			*///Current Champ info
			chiname = new JLabel("Player Name: " + CurrentWizard.getName());
			chiname.setBounds(25,49,300,30);
			chiname.setForeground(Color.YELLOW);
			chiname.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			HarryPotterGUI.getPalyer_info_inventory().add(chiname);
			//Icon
			Image img = (new ImageIcon("HPotion.png")).getImage() ;  
			Image newimg = img.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
			ImageIcon icon = new ImageIcon( newimg );
			//ChHP label
			chhp = new JLabel("HP : " + CurrentWizard.getHp());
			 chhp.setBounds(25,90+30,300,30);
			 chhp.setForeground(Color.YELLOW);
			 chhp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			 chhp.setIcon(icon);
			HarryPotterGUI.getPalyer_info_inventory().add( chhp);
			
			//Icon
			Image img2  = (new ImageIcon("magic(IP).png")).getImage() ;  
			Image newimg2 = img2.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH ) ;  
			ImageIcon icon2 = new ImageIcon( newimg2 );
			
			//chIP
			chip = new JLabel("IP : " + CurrentWizard.getIp());
			 chip.setBounds(150,90+30,300,30);
			 chip.setForeground(Color.YELLOW);
			 chip.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			 chip.setIcon(icon2);
			HarryPotterGUI.getPalyer_info_inventory().add( chip );
			
			this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
			HarryPotterGUI.getPalyer_info_inventory().add(sp);
			sp.setVisible(false);
			HarryPotterGUI.getPalyer_info_inventory().add(HarryPotterGUI.getItemInventroy());
			
			HarryPotterGUI.getPalyer_info_inventory().updateUI();
		}
		
		//first task firing  end code
		
		if(Pressed_Button.getText().equals("Full Info")){//info viewer
			if (infoviewed == false){
		
				sp.setVisible(true);
				HarryPotterGUI.getPalyer_info_inventory().add(sp);
				infoviewed = true;
				System.out.print("Fail Here");
				HarryPotterGUI.getPalyer_info_inventory().repaint();
				HarryPotterGUI.getPalyer_info_inventory().updateUI();
				
		
		
			}else if (infoviewed == true){
				sp.setVisible(false);
				infoviewed = false;
				System.out.print("Fail Here No GUI Update Error");
				HarryPotterGUI.getPalyer_info_inventory().updateUI();
		
		
				}
			
			
		}
		
		if(Pressed_Button.getText().equals("Restart Game")){
			HarryPotterGUI.removeAll();
			try {
				new HarryPotterController() ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(Pressed_Button.getText().equals("Use Spell")){
			try {
				String Choosed_Spell_Name = currentspells.getSelectedItem().toString();
			
						//currentspells.getItemAt(HarryPotterGUI.getPlayer1_Box().getSelectedIndex());
				Spell Choosed_Spell = GetSpellFromName(Choosed_Spell_Name);
				
				if(inFirstTask){
					
					if (Choosed_Spell instanceof RelocatingSpell){
						try{
							RelocatingInput RelocatingInput = new RelocatingInput();
							int range = RelocatingInput.getRange();
							Direction t = RelocatingInput.getT();
							Direction d = RelocatingInput.getD();
							relocateFirstTask((RelocatingSpell)Choosed_Spell, d, t, range);
						}catch(Exception ex){
							new MessageBox(ex.getMessage());
						}
						
					}
					if (Choosed_Spell instanceof HealingSpell){
						
						try{
							Tournament.getFirstTask().castHealingSpell((HealingSpell)Choosed_Spell);

						}catch(Exception ex){
							new MessageBox(ex.getMessage());
						}
					}
					if (Choosed_Spell instanceof DamagingSpell){
						try{
							DamagingInput  DamagingInput = new DamagingInput();
							
							if (DamagingInput.getT() == Direction.FORWARD){
								DamgingSpellForward((DamagingSpell)Choosed_Spell);
							}else if (DamagingInput.getT() == Direction.BACKWARD){
								DamagingSpellDownward((DamagingSpell)Choosed_Spell);
							}else if (DamagingInput.getT() == Direction.RIGHT){
								DamagingSpellRight((DamagingSpell)Choosed_Spell);
							}else if (DamagingInput.getT() == Direction.LEFT){
								DamagingSpellLeft((DamagingSpell)Choosed_Spell);
							}
						}catch(Exception ex){
							new MessageBox(ex.getMessage());
						}
						
					
					
					
						
					}
					
					
					this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
					}
				if(inSecondTask){

					if (Choosed_Spell instanceof RelocatingSpell){
						try{
							RelocatingInput RelocatingInput = new RelocatingInput();
							int range = RelocatingInput.getRange();
							Direction t = RelocatingInput.getT();
							Direction d = RelocatingInput.getD();
						RelocateSecondTask((RelocatingSpell)Choosed_Spell, d, t, range);
						}
						catch(Exception ex){
							new MessageBox(ex.getMessage());
						}
						
					}
					if (Choosed_Spell instanceof HealingSpell){
						try{
						Tournament.getSecondTask().castHealingSpell((HealingSpell)Choosed_Spell);
						}catch(Exception ex){
							new MessageBox("The required IP you need to cast this spell is 300."+
"You need another 250 IP to be able to cast this spell");
						}
					}
					if (Choosed_Spell instanceof DamagingSpell){
						DamagingInput  DamagingInput = new DamagingInput();
						try{
							if (DamagingInput.getT() == Direction.FORWARD){
								DamgingSpellForward((DamagingSpell)Choosed_Spell);
							}else if (DamagingInput.getT() == Direction.BACKWARD){
								DamagingSpellDownward((DamagingSpell)Choosed_Spell);
							}else if (DamagingInput.getT() == Direction.RIGHT){
								DamagingSpellRight((DamagingSpell)Choosed_Spell);
							}else if (DamagingInput.getT() == Direction.LEFT){
								DamagingSpellLeft((DamagingSpell)Choosed_Spell);
							}
						}catch (Exception ex){
							new MessageBox(ex.getMessage());						}
					
					}
					
					this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
				}
				if(inThirdTask){

					if (Choosed_Spell instanceof RelocatingSpell){
						try{
							RelocatingInput RelocatingInput = new RelocatingInput();
							int range = RelocatingInput.getRange();
							Direction t = RelocatingInput.getT();
							Direction d = RelocatingInput.getD();
							
							RelocatingThirdTask((RelocatingSpell)Choosed_Spell, d, t, range);
						}catch(Exception ex){
							new MessageBox(ex.getMessage());
						}
						
					}
					if (Choosed_Spell instanceof HealingSpell){
	try{
		Tournament.getThirdTask().castHealingSpell((HealingSpell)Choosed_Spell);

						}catch(Exception ex){
							new MessageBox(ex.getMessage());
						}
					}
					if (Choosed_Spell instanceof DamagingSpell){
						DamagingInput  DamagingInput = new DamagingInput();
	try{if (DamagingInput.getT() == Direction.FORWARD){
		DamgingSpellForward((DamagingSpell)Choosed_Spell);
	}else if (DamagingInput.getT() == Direction.BACKWARD){
		DamagingSpellDownward((DamagingSpell)Choosed_Spell);
	}else if (DamagingInput.getT() == Direction.RIGHT){
		DamagingSpellRight((DamagingSpell)Choosed_Spell);
	}else if (DamagingInput.getT() == Direction.LEFT){
		DamagingSpellLeft((DamagingSpell)Choosed_Spell);
	}
							
						}catch(Exception ex){
							new MessageBox(ex.getMessage());
						}
						
					}
					this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(Pressed_Button.getText().equals("End Turn")){
			//End Current Champion Turn Manually and Renew the Information

			try {
				if(inFirstTask){
					Tournament.getFirstTask().endTurn();
					this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
					}
				if(inSecondTask){
					Tournament.getSecondTask().endTurn();	
					this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
				}
				if(inThirdTask){
					Tournament.getThirdTask().endTurn();	
					this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		if(Pressed_Button.getText().equals("Use Potion")){
			//End Current Champion Turn Manually and Renew the Information
			Wizard CurrentWizard = null;
			try {
				if(inFirstTask){
					CurrentWizard =(Wizard)Tournament.getFirstTask().getCurrentChamp();
				//	Tournament.getFirstTask().endTurn();
					for (Collectible item:CurrentWizard.getInventory()){
						if (item instanceof Potion){
							Tournament.getFirstTask().usePotion((Potion)item);
							this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
							return;
						}
					}
					
					}
				if(inSecondTask){
					CurrentWizard =(Wizard)Tournament.getSecondTask().getCurrentChamp();
					for (Collectible item:CurrentWizard.getInventory()){
						if (item instanceof Potion){
							Tournament.getSecondTask().usePotion((Potion)item);
							this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
							return;
						}
					}
					//Tournament.getSecondTask().endTurn();	
					
				}
				if(inThirdTask){
					CurrentWizard =(Wizard)Tournament.getThirdTask().getCurrentChamp();
					for (Collectible item:CurrentWizard.getInventory()){
						if (item instanceof Potion){
							Tournament.getThirdTask().usePotion((Potion)item);
							this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
							return;
						}
					}
				//	Tournament.getThirdTask().endTurn();	
				
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
		
		
		//Motion Buttons
		if(Pressed_Button.getText().equals("Forward")){
			 Wizard Current = null;

			if(inFirstTask){
				Current = (Wizard) Tournament.getFirstTask().getCurrentChamp();
			}
			if(inSecondTask){
				Current = (Wizard) Tournament.getSecondTask().getCurrentChamp();
			}
			if(inThirdTask){
				Current = (Wizard) Tournament.getThirdTask().getCurrentChamp();
			}
			 
			 JLabel mapcell = new JLabel();
		
				 try {
						
							
							//Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y]
							if (Current instanceof GryffindorWizard){//2551.jpg
								Image img = (new ImageIcon("2551forward.png")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveForward();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveForward();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveForward();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
								//HarryPotterGUI.replace_Map_Cell_First_Task(OldPoint,Current.getLocation(),mapcell);
							}else 	if (Current instanceof HufflepuffWizard){
								Image img = (new ImageIcon("2552.jpg")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveForward();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveForward();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveForward();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							}else 	if (Current instanceof  RavenclawWizard){
								Image img = (new ImageIcon("2362.jpg")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveForward();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveForward();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveForward();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							}else if (Current instanceof SlytherinWizard){
								Image img = (new ImageIcon("2550.png")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);

								if(inFirstTask){
									Tournament.getFirstTask().moveForward();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveForward();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveForward();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
								
							
							}
							else if (Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y] instanceof CollectibleCell){
								CollectibleCell CollectibleCell = (CollectibleCell)Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y];
								if (CollectibleCell.getCollectible() instanceof Potion){
									Image img = (new ImageIcon("HPotion.png")).getImage() ;  
									Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
									ImageIcon icon = new ImageIcon( newimg );
									mapcell.setIcon(icon);
					
									Current.getInventory().add(CollectibleCell.getCollectible());
									if(inFirstTask){
										Tournament.getFirstTask().moveForward();
										this. UpdateCellsFirstTask();
									}
									if(inSecondTask){
										Tournament.getSecondTask().moveForward();
										this. UpdateCellsSecondTask();
									}
									if(inThirdTask){
										Tournament.getThirdTask().moveForward();
										this. UpdateCellsThirdTask();
									}
								//	mapcell.setText("Collectible");
								}
							}
							
							
						 
							
				} catch (OutOfBordersException | InvalidTargetCellException | IOException e) {
					if (e instanceof OutOfBordersException){
						new 	MessageBox("Cannot Move Out of Borders");
					}
				
				}
				
						
				
		}
		if(Pressed_Button.getText().equals("Backward")){



			 Wizard Current = null;

				if(inFirstTask){
					Current = (Wizard) Tournament.getFirstTask().getCurrentChamp();
				}
				if(inSecondTask){
					Current = (Wizard) Tournament.getSecondTask().getCurrentChamp();
				}
				if(inThirdTask){
					Current = (Wizard) Tournament.getThirdTask().getCurrentChamp();
				}
			 
			 JLabel mapcell = new JLabel();
			 Point OldPoint = Current.getLocation();
				 try {
						
							
							//Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y]
							if (Current instanceof GryffindorWizard){
								Image img = (new ImageIcon("2551.jpg")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveBackward();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveBackward();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveBackward();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							//	HarryPotterGUI.replace_Map_Cell_First_Task(OldPoint,Current.getLocation(),mapcell);
							}else 	if (Current instanceof HufflepuffWizard){
								Image img = (new ImageIcon("2552.jpg")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveBackward();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveBackward();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveBackward();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							}else 	if (Current instanceof  RavenclawWizard){
								Image img = (new ImageIcon("2362.jpg")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveBackward();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveBackward();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveBackward();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							}else if (Current instanceof SlytherinWizard){
								Image img = (new ImageIcon("2550.png")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveBackward();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveBackward();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveBackward();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							}
							else if (Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y] instanceof CollectibleCell){
								CollectibleCell CollectibleCell = (CollectibleCell)Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y];
								if (CollectibleCell.getCollectible() instanceof Potion){
									Image img = (new ImageIcon("HPotion.png")).getImage() ;  
									Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
									ImageIcon icon = new ImageIcon( newimg );
									mapcell.setIcon(icon);
					
									Current.getInventory().add(CollectibleCell.getCollectible());
									if(inFirstTask){
										Tournament.getFirstTask().moveBackward();
										this. UpdateCellsFirstTask();
										 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
									}
									if(inSecondTask){
										Tournament.getSecondTask().moveBackward();
										this. UpdateCellsSecondTask();
										 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
									}
									if(inThirdTask){
										Tournament.getThirdTask().moveBackward();
										this. UpdateCellsThirdTask();
										 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
									}
									
								//	mapcell.setText("Collectible");
								}
							}
							
							
						 
							
				} catch (OutOfBordersException
								| InvalidTargetCellException | IOException e) {
					
					if (e instanceof OutOfBordersException){
						new 	MessageBox("Cannot Move Out of Borders");
					}
		
				}
				
		
						
		}
		if(Pressed_Button.getText().equals("Left")){


			 Wizard Current = null;

				if(inFirstTask){
					Current = (Wizard) Tournament.getFirstTask().getCurrentChamp();
				}
				if(inSecondTask){
					Current = (Wizard) Tournament.getSecondTask().getCurrentChamp();
				}
				if(inThirdTask){
					Current = (Wizard) Tournament.getThirdTask().getCurrentChamp();
				}
			 
			 JLabel mapcell = new JLabel();
			
				 try {
						
							
							//Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y]
							if (Current instanceof GryffindorWizard){
								Image img = (new ImageIcon("2551.jpg")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
							
								if(inFirstTask){
									Tournament.getFirstTask().moveLeft();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveLeft();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveLeft();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
								
								//HarryPotterGUI.replace_Map_Cell_First_Task(OldPoint,Current.getLocation(),mapcell);
							}else 	if (Current instanceof HufflepuffWizard){
								Image img = (new ImageIcon("2552.jpg")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveLeft();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveLeft();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveLeft();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							}else 	if (Current instanceof  RavenclawWizard){
								Image img = (new ImageIcon("2362.jpg")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveLeft();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveLeft();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveLeft();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							}else if (Current instanceof SlytherinWizard){
								Image img = (new ImageIcon("2550.png")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveLeft();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveLeft();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveLeft();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							}
							else if (Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y] instanceof CollectibleCell){
								CollectibleCell CollectibleCell = (CollectibleCell)Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y];
								if (CollectibleCell.getCollectible() instanceof Potion){
									Image img = (new ImageIcon("HPotion.png")).getImage() ;  
									Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
									ImageIcon icon = new ImageIcon( newimg );
									mapcell.setIcon(icon);
									
									Current.getInventory().add(CollectibleCell.getCollectible());
									if(inFirstTask){
										Tournament.getFirstTask().moveLeft();
										this. UpdateCellsFirstTask();
										 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
									}
									if(inSecondTask){
										Tournament.getSecondTask().moveLeft();
										this. UpdateCellsSecondTask();
										 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
									}
									if(inThirdTask){
										Tournament.getThirdTask().moveLeft();
										this. UpdateCellsThirdTask();
										 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
									}
								//	mapcell.setText("Collectible");
								}
							}
							
							
						 
							
				} catch (OutOfBordersException
								| InvalidTargetCellException | IOException e) {
				
					if (e instanceof OutOfBordersException){
						new 	MessageBox("Cannot Move Out of Borders");
					}
				}
				
						
		}
		if(Pressed_Button.getText().equals("Right")){


			 Wizard Current = null;

				if(inFirstTask){
					Current = (Wizard) Tournament.getFirstTask().getCurrentChamp();
				}
				if(inSecondTask){
					Current = (Wizard) Tournament.getSecondTask().getCurrentChamp();
				}
				if(inThirdTask){
					Current = (Wizard) Tournament.getThirdTask().getCurrentChamp();
				}
			 
			 JLabel mapcell = new JLabel();
			 Point OldPoint = Current.getLocation();
				 try {
						
							
							//Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y]
							if (Current instanceof GryffindorWizard){
								Image img = (new ImageIcon("2551.jpg")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveRight();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveRight();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveRight();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							//	HarryPotterGUI.replace_Map_Cell_First_Task(OldPoint,Current.getLocation(),mapcell);
							}else 	if (Current instanceof HufflepuffWizard){
								Image img = (new ImageIcon("2552.jpg")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveRight();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveRight();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveRight();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							}else 	if (Current instanceof  RavenclawWizard){
								Image img = (new ImageIcon("2362.jpg")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveRight();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveRight();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveRight();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							}else if (Current instanceof SlytherinWizard){
								Image img = (new ImageIcon("2550.png")).getImage() ;  
								Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
								ImageIcon icon = new ImageIcon( newimg );
								mapcell.setIcon(icon);
								chiname.setIcon(icon);
								if(inFirstTask){
									Tournament.getFirstTask().moveRight();
									this. UpdateCellsFirstTask();
									 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
								}
								if(inSecondTask){
									Tournament.getSecondTask().moveRight();
									this. UpdateCellsSecondTask();
									 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
								}
								if(inThirdTask){
									Tournament.getThirdTask().moveRight();
									this. UpdateCellsThirdTask();
									 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
								}
							}
							else if (Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y] instanceof CollectibleCell){
								CollectibleCell CollectibleCell = (CollectibleCell)Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y];
								if (CollectibleCell.getCollectible() instanceof Potion){
									Image img = (new ImageIcon("HPotion.png")).getImage() ;  
									Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
									ImageIcon icon = new ImageIcon( newimg );
									mapcell.setIcon(icon);
									Current.getInventory().add(CollectibleCell.getCollectible());
									if(inFirstTask){
										Tournament.getFirstTask().moveRight();
										this. UpdateCellsFirstTask();
										 this.UpdateFullInfo(Tournament.getFirstTask().getCurrentChamp());
									}
									if(inSecondTask){
										Tournament.getSecondTask().moveRight();
										this. UpdateCellsSecondTask();
										 this.UpdateFullInfo(Tournament.getSecondTask().getCurrentChamp());
									}
									if(inThirdTask){
										Tournament.getThirdTask().moveRight();
										this. UpdateCellsThirdTask();
										 this.UpdateFullInfo(Tournament.getThirdTask().getCurrentChamp());
									}
								//	mapcell.setText("Collectible");
								}
							}
							
							
						 
							
				} catch (OutOfBordersException
								| InvalidTargetCellException | IOException e) {
					
	
					if (e instanceof OutOfBordersException){
						new 	MessageBox("Cannot Move Out of Borders");
					}
				}
			
						
		}
		
		
		
		
		
		
		
		
		
		//#########################################################################################
		//##################################### Champions Setters #################################
		//##########################################################################################
		//#########################################################################################
		//##################################### Champions Setters #################################
		//##########################################################################################
		//#########################################################################################
		//##################################### Champions Setters #################################
		//##########################################################################################
		//#########################################################################################
		//##################################### Champions Setters #################################
		//##########################################################################################
		//#########################################################################################
		//##################################### Champions Setters #################################
		//##########################################################################################
		//#########################################################################################
		//##################################### Champions Setters #################################
		//##########################################################################################		
		
		
		
		
		
		
		
		if(Pressed_Button .getText().equals("Set First Champion")){
		//Switch if Type is changed
			switch (HarryPotterGUI.getPlayer1_Box().getItemAt(HarryPotterGUI.getPlayer1_Box().getSelectedIndex())){
			case "GryffindorWizard" :
				GryffindorWizard GryffindorWizard = new GryffindorWizard("GryffindorWizard");
				GryffindorWizard.setName(HarryPotterGUI.getChampionnameentry().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell11_Box().getItemAt(HarryPotterGUI.getSpell11_Box().getSelectedIndex()));
				if (CurrentSpell!=null){
					GryffindorWizard.getSpells().add(CurrentSpell);
					Update_Choosen_info("First Champion  : " +GryffindorWizard.getName() +  " Type : GryffindorWizard " + " First Spell: " +CurrentSpell.getName());
					//System.out.println("spell1 : " +CurrentSpell.getName());
				}
				 CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell12_Box().getItemAt(HarryPotterGUI.getSpell12_Box().getSelectedIndex()));
					if (CurrentSpell!=null){
						GryffindorWizard.getSpells().add(CurrentSpell);
						Update_Choosen_info( "Second Spell: " +CurrentSpell.getName());

						//System.out.println("spell2 : " +CurrentSpell.getName());
					}
				CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell13_Box().getItemAt(HarryPotterGUI.getSpell13_Box().getSelectedIndex()));
					if (CurrentSpell!=null){
						GryffindorWizard.getSpells().add(CurrentSpell);
						Update_Choosen_info("Third Spell: " +CurrentSpell.getName());

						//System.out.println("spell3 : " +CurrentSpell.getName());
				}
					
					Tournament.addChampion(GryffindorWizard);
				
				break;
			case "HufflepuffWizard" :
				HufflepuffWizard HufflepuffWizard = new HufflepuffWizard("HufflepuffWizard");
				HufflepuffWizard.setName(HarryPotterGUI.getChampionnameentry().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell11_Box().getItemAt(HarryPotterGUI.getSpell11_Box().getSelectedIndex()));
				if (CurrentSpell2!=null){
					HufflepuffWizard .getSpells().add(CurrentSpell2);
					Update_Choosen_info("First Champion  : " +HufflepuffWizard.getName() +  " Type : HufflepuffWizard" + " First Spell: " +CurrentSpell2.getName());
					
					System.out.println("spell1 : " +CurrentSpell2.getName());
				}
				 CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell12_Box().getItemAt(HarryPotterGUI.getSpell12_Box().getSelectedIndex()));
					if (CurrentSpell2!=null){
						HufflepuffWizard .getSpells().add(CurrentSpell2);
						Update_Choosen_info( "Second Spell: " +CurrentSpell2.getName());
						System.out.println("spell2 : " +CurrentSpell2.getName());
					}
				CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell13_Box().getItemAt(HarryPotterGUI.getSpell13_Box().getSelectedIndex()));
					if (CurrentSpell2!=null){
						HufflepuffWizard .getSpells().add(CurrentSpell2);
						Update_Choosen_info( "Third Spell: " +CurrentSpell2.getName());
						System.out.println("spell3 : " +CurrentSpell2.getName());
				}

					Tournament.addChampion(HufflepuffWizard );
				break;
			case "RavenclawWizard"  :
				RavenclawWizard RavenclawWizard = new RavenclawWizard("RavenclawWizard");
				RavenclawWizard.setName(HarryPotterGUI.getChampionnameentry().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell11_Box().getItemAt(HarryPotterGUI.getSpell11_Box().getSelectedIndex()));
				if (CurrentSpell3!=null){
					RavenclawWizard  .getSpells().add(CurrentSpell3);
					Update_Choosen_info("First Champion  : " +RavenclawWizard.getName() +  " Type : RavenclawWizard " + " First Spell: " +CurrentSpell3.getName());
					
					System.out.println("spell1 : " +CurrentSpell3.getName());
				}
				 CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell12_Box().getItemAt(HarryPotterGUI.getSpell12_Box().getSelectedIndex()));
					if (CurrentSpell3!=null){
						RavenclawWizard  .getSpells().add(CurrentSpell3);
						Update_Choosen_info( "Second Spell: " +CurrentSpell3.getName());
						System.out.println("spell2 : " +CurrentSpell3.getName());
					}
				CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell13_Box().getItemAt(HarryPotterGUI.getSpell13_Box().getSelectedIndex()));
					if (CurrentSpell3!=null){
						RavenclawWizard  .getSpells().add(CurrentSpell3);
						Update_Choosen_info( "Third Spell: " +CurrentSpell3.getName());
						System.out.println("spell3 : " +CurrentSpell3.getName());
				}

					Tournament.addChampion(RavenclawWizard);
				break;
			case "SlytherinWizard"  :
				SlytherinWizard SlytherinWizard = new SlytherinWizard("SlytherinWizard");
				SlytherinWizard .setName(HarryPotterGUI.getChampionnameentry().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell11_Box().getItemAt(HarryPotterGUI.getSpell11_Box().getSelectedIndex()));
				
				if (CurrentSpell4!=null){
					 SlytherinWizard   .getSpells().add(CurrentSpell4);
					 Update_Choosen_info("First Champion  : " + SlytherinWizard .getName() +  " Type : SlytherinWizard " + " First Spell: " +CurrentSpell4.getName());
						
					//System.out.println("spell1 : " +CurrentSpell4.getName());
				}
				 CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell12_Box().getItemAt(HarryPotterGUI.getSpell12_Box().getSelectedIndex()));
					if (CurrentSpell4!=null){
						 SlytherinWizard   .getSpells().add(CurrentSpell4);
						 Update_Choosen_info( "Second Spell: " +CurrentSpell4.getName());
						//System.out.println("spell2 : " +CurrentSpell4.getName());
					}
				CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell13_Box().getItemAt(HarryPotterGUI.getSpell13_Box().getSelectedIndex()));
					if (CurrentSpell4!=null){
						 SlytherinWizard   .getSpells().add(CurrentSpell4);
						 Update_Choosen_info( "Third Spell: " +CurrentSpell4.getName());
						//System.out.println("spell3 : " +CurrentSpell4.getName());
				}

					Tournament.addChampion( SlytherinWizard );
				break;
			}
			
			System.out.println("CH1 : " + HarryPotterGUI.getChampionnameentry().getText());
			Pressed_Button.setEnabled(false);	
			CCounter +=1;
			
		}else if(Pressed_Button .getText().equals("Set Second Champion")){//2nd champ with it`s spell
			
			switch (HarryPotterGUI.getPlayer2_Box().getItemAt(HarryPotterGUI.getPlayer2_Box().getSelectedIndex())){
			case "GryffindorWizard" :
				GryffindorWizard GryffindorWizard = new GryffindorWizard("GryffindorWizard");
				GryffindorWizard.setName(HarryPotterGUI.getChampionnameentry2().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell21_Box().getItemAt(HarryPotterGUI.getSpell21_Box().getSelectedIndex()));
				if (CurrentSpell!=null){
					GryffindorWizard.getSpells().add(CurrentSpell);
					Update_Choosen_info("Second Champion  : " +HufflepuffWizard.getName() +  " Type : GryffindorWizard " + " First Spell: " +CurrentSpell.getName());
					
					//System.out.println("spell1 : " +CurrentSpell.getName());
				}
				 CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell22_Box().getItemAt(HarryPotterGUI.getSpell22_Box().getSelectedIndex()));
					if (CurrentSpell!=null){
						GryffindorWizard.getSpells().add(CurrentSpell);
						Update_Choosen_info( "Second Spell: " +CurrentSpell.getName());
						//System.out.println("spell2 : " +CurrentSpell.getName());
					}
				CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell23_Box().getItemAt(HarryPotterGUI.getSpell23_Box().getSelectedIndex()));
					if (CurrentSpell!=null){
						GryffindorWizard.getSpells().add(CurrentSpell);
						Update_Choosen_info("Third Spell: " +CurrentSpell.getName());
						//System.out.println("spell3 : " +CurrentSpell.getName());
				}

			
					Tournament.addChampion(GryffindorWizard);
				break;
			case "HufflepuffWizard" :
				HufflepuffWizard HufflepuffWizard = new HufflepuffWizard("HufflepuffWizard");
				HufflepuffWizard.setName(HarryPotterGUI.getChampionnameentry2().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell21_Box().getItemAt(HarryPotterGUI.getSpell21_Box().getSelectedIndex()));
				if (CurrentSpell2!=null){
					HufflepuffWizard .getSpells().add(CurrentSpell2);
					Update_Choosen_info("Second Champion  : " +HufflepuffWizard.getName() +  " Type : HufflepuffWizard " + " First Spell: " +CurrentSpell2.getName());

					System.out.println("spell1 : " +CurrentSpell2.getName());
				}
				 CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell22_Box().getItemAt(HarryPotterGUI.getSpell22_Box().getSelectedIndex()));
					if (CurrentSpell2!=null){
						HufflepuffWizard .getSpells().add(CurrentSpell2);
						Update_Choosen_info( "Second Spell: " +CurrentSpell2.getName());
						System.out.println("spell2 : " +CurrentSpell2.getName());
					}
				CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell23_Box().getItemAt(HarryPotterGUI.getSpell23_Box().getSelectedIndex()));
					if (CurrentSpell2!=null){
						HufflepuffWizard .getSpells().add(CurrentSpell2);
						Update_Choosen_info("Third Spell: " +CurrentSpell2.getName());
						System.out.println("spell3 : " +CurrentSpell2.getName());
				}

					Tournament.addChampion(HufflepuffWizard);
				break;
			case "RavenclawWizard"  :
				RavenclawWizard RavenclawWizard = new RavenclawWizard("RavenclawWizard");
				RavenclawWizard.setName(HarryPotterGUI.getChampionnameentry2().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell21_Box().getItemAt(HarryPotterGUI.getSpell21_Box().getSelectedIndex()));
				if (CurrentSpell3!=null){
					RavenclawWizard  .getSpells().add(CurrentSpell3);
					Update_Choosen_info("Second Champion  : " +RavenclawWizard.getName() +  " Type : RavenclawWizard " + " First Spell: " +CurrentSpell3.getName());

					System.out.println("spell1 : " +CurrentSpell3.getName());
				}
				 CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell22_Box().getItemAt(HarryPotterGUI.getSpell22_Box().getSelectedIndex()));
					if (CurrentSpell3!=null){
						RavenclawWizard  .getSpells().add(CurrentSpell3);
						Update_Choosen_info("Second Spell: " +CurrentSpell3.getName());
						System.out.println("spell2 : " +CurrentSpell3.getName());
					}
				CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell23_Box().getItemAt(HarryPotterGUI.getSpell23_Box().getSelectedIndex()));
					if (CurrentSpell3!=null){
						RavenclawWizard  .getSpells().add(CurrentSpell3);
						Update_Choosen_info("Third Spell: " +CurrentSpell3.getName());
						System.out.println("spell3 : " +CurrentSpell3.getName());
				}

					Tournament.addChampion(RavenclawWizard);
				break;
			case "SlytherinWizard"  :
				SlytherinWizard SlytherinWizard = new SlytherinWizard("SlytherinWizard");
				SlytherinWizard.setName(HarryPotterGUI.getChampionnameentry2().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell21_Box().getItemAt(HarryPotterGUI.getSpell21_Box().getSelectedIndex()));
				if (CurrentSpell4!=null){
					 SlytherinWizard   .getSpells().add(CurrentSpell4);
						Update_Choosen_info("Second Champion  : " +SlytherinWizard.getName() +  " Type : SlytherinWizard " + " First Spell: " +CurrentSpell4.getName());

					System.out.println("spell1 : " +CurrentSpell4.getName());
				}
				 CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell22_Box().getItemAt(HarryPotterGUI.getSpell22_Box().getSelectedIndex()));
					if (CurrentSpell4!=null){
						 SlytherinWizard   .getSpells().add(CurrentSpell4);
						 Update_Choosen_info("Second Spell: " +CurrentSpell4.getName());
						System.out.println("spell2 : " +CurrentSpell4.getName());
					}
				CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell23_Box().getItemAt(HarryPotterGUI.getSpell23_Box().getSelectedIndex()));
					if (CurrentSpell4!=null){
						 SlytherinWizard   .getSpells().add(CurrentSpell4);
						 Update_Choosen_info("Third Spell: " +CurrentSpell4.getName());
						System.out.println("spell3 : " +CurrentSpell4.getName());
				}

					Tournament.addChampion(SlytherinWizard);
				break;
			}
					System.out.println("CH2 : " + HarryPotterGUI.getChampionnameentry2().getText());
			
					Pressed_Button.setEnabled(false);	
					CCounter+=1;
		}else if(Pressed_Button .getText().equals("Set Third Champion")){//3rd champ with it`s spell
			switch (HarryPotterGUI.getPlayer3_Box().getItemAt(HarryPotterGUI.getPlayer3_Box().getSelectedIndex())){
			case "GryffindorWizard" :
				GryffindorWizard GryffindorWizard = new GryffindorWizard("GryffindorWizard");
				GryffindorWizard.setName(HarryPotterGUI.getChampionnameentry3().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell31_Box().getItemAt(HarryPotterGUI.getSpell31_Box().getSelectedIndex()));
				if (CurrentSpell!=null){
					GryffindorWizard.getSpells().add(CurrentSpell);
					Update_Choosen_info("Third Champion  : " +HufflepuffWizard.getName() +  " Type : GryffindorWizard " + " First Spell: " +CurrentSpell.getName());
					System.out.println("spell1 : " +CurrentSpell.getName());
				}
				 CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell32_Box().getItemAt(HarryPotterGUI.getSpell32_Box().getSelectedIndex()));
					if (CurrentSpell!=null){
						GryffindorWizard.getSpells().add(CurrentSpell);
						Update_Choosen_info( "Second Spell: " +CurrentSpell.getName());
						System.out.println("spell2 : " +CurrentSpell.getName());
					}
				CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell33_Box().getItemAt(HarryPotterGUI.getSpell33_Box().getSelectedIndex()));
					if (CurrentSpell!=null){
						GryffindorWizard.getSpells().add(CurrentSpell);
						Update_Choosen_info("Third Spell: " +CurrentSpell.getName());
						System.out.println("spell3 : " +CurrentSpell.getName());
				}

			
					Tournament.addChampion(GryffindorWizard);
				break;
			case "HufflepuffWizard" :
				HufflepuffWizard HufflepuffWizard = new HufflepuffWizard("HufflepuffWizard");
				HufflepuffWizard.setName(HarryPotterGUI.getChampionnameentry3().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell31_Box().getItemAt(HarryPotterGUI.getSpell31_Box().getSelectedIndex()));
				if (CurrentSpell2!=null){
					HufflepuffWizard .getSpells().add(CurrentSpell2);
					Update_Choosen_info("Third Champion  : " +HufflepuffWizard.getName() +  " Type : HufflepuffWizard " + " First Spell: " +CurrentSpell2.getName());

					System.out.println("spell1 : " +CurrentSpell2.getName());
				}
				 CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell32_Box().getItemAt(HarryPotterGUI.getSpell32_Box().getSelectedIndex()));
					if (CurrentSpell2!=null){
						HufflepuffWizard .getSpells().add(CurrentSpell2);
						Update_Choosen_info("Second Spell: " +CurrentSpell2.getName());
						System.out.println("spell2 : " +CurrentSpell2.getName());
					}
				CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell33_Box().getItemAt(HarryPotterGUI.getSpell33_Box().getSelectedIndex()));
					if (CurrentSpell2!=null){
						HufflepuffWizard .getSpells().add(CurrentSpell2);
						Update_Choosen_info("Third Spell: " +CurrentSpell2.getName());
						System.out.println("spell3 : " +CurrentSpell2.getName());
				}

					Tournament.addChampion(HufflepuffWizard);
				break;
			case "RavenclawWizard"  :
				RavenclawWizard RavenclawWizard = new RavenclawWizard("RavenclawWizard");
				 RavenclawWizard.setName(HarryPotterGUI.getChampionnameentry3().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell31_Box().getItemAt(HarryPotterGUI.getSpell31_Box().getSelectedIndex()));
				if (CurrentSpell3!=null){
					RavenclawWizard  .getSpells().add(CurrentSpell3);
					Update_Choosen_info("Third Champion  : " +RavenclawWizard .getName() +  " Type : GryffindorWizard " + " First Spell: " +CurrentSpell3.getName());

					System.out.println("spell1 : " +CurrentSpell3.getName());
				}
				 CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell32_Box().getItemAt(HarryPotterGUI.getSpell32_Box().getSelectedIndex()));
					if (CurrentSpell3!=null){
						RavenclawWizard  .getSpells().add(CurrentSpell3);
						Update_Choosen_info("Second Spell: " +CurrentSpell3.getName());
						System.out.println("spell2 : " +CurrentSpell3.getName());
					}
				CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell33_Box().getItemAt(HarryPotterGUI.getSpell33_Box().getSelectedIndex()));
					if (CurrentSpell3!=null){
						RavenclawWizard  .getSpells().add(CurrentSpell3);
						Update_Choosen_info("Third Spell: " +CurrentSpell3.getName());
						System.out.println("spell3 : " +CurrentSpell3.getName());
				}

					Tournament.addChampion(RavenclawWizard );
				break;
			case "SlytherinWizard"  :
				SlytherinWizard SlytherinWizard = new SlytherinWizard("SlytherinWizard");
				SlytherinWizard.setName(HarryPotterGUI.getChampionnameentry3().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell31_Box().getItemAt(HarryPotterGUI.getSpell31_Box().getSelectedIndex()));
				if (CurrentSpell4!=null){
					 SlytherinWizard   .getSpells().add(CurrentSpell4);
						Update_Choosen_info("Third Champion  : " +SlytherinWizard.getName() +  " Type : GryffindorWizard " + " First Spell: " +CurrentSpell4.getName());

					System.out.println("spell1 : " +CurrentSpell4.getName());
				}
				 CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell32_Box().getItemAt(HarryPotterGUI.getSpell32_Box().getSelectedIndex()));
					if (CurrentSpell4!=null){
						 SlytherinWizard   .getSpells().add(CurrentSpell4);
						 Update_Choosen_info("Second Spell: " +CurrentSpell4.getName());
						System.out.println("spell2 : " +CurrentSpell4.getName());
					}
				CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell33_Box().getItemAt(HarryPotterGUI.getSpell33_Box().getSelectedIndex()));
					if (CurrentSpell4!=null){
						 SlytherinWizard   .getSpells().add(CurrentSpell4);
						 Update_Choosen_info("Third Spell: " +CurrentSpell4.getName());
						System.out.println("spell3 : " +CurrentSpell4.getName());
				}

					Tournament.addChampion(SlytherinWizard);
				break;
			}
					System.out.println("CH3 : " + HarryPotterGUI.getChampionnameentry3().getText());
		
					Pressed_Button.setEnabled(false);	
					CCounter+=1;
		}else if(Pressed_Button .getText().equals("Set Fourth Champion")){//4th Champ with it`s spell
			switch (HarryPotterGUI.getPlayer4_Box().getItemAt(HarryPotterGUI.getPlayer4_Box().getSelectedIndex())){
			case "GryffindorWizard" :
				GryffindorWizard GryffindorWizard = new GryffindorWizard("GryffindorWizard");
				GryffindorWizard.setName(HarryPotterGUI.getChampionnameentry4().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell41_Box().getItemAt(HarryPotterGUI.getSpell41_Box().getSelectedIndex()));
				if (CurrentSpell!=null){
					GryffindorWizard.getSpells().add(CurrentSpell);
					
					Update_Choosen_info("Fourth Champion  : " +HufflepuffWizard.getName() +  " Type : GryffindorWizard " + " First Spell: " +CurrentSpell.getName());
					System.out.println("spell1 : " +CurrentSpell.getName());
				}
				 CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell42_Box().getItemAt(HarryPotterGUI.getSpell42_Box().getSelectedIndex()));
					if (CurrentSpell!=null){
						GryffindorWizard.getSpells().add(CurrentSpell);
						Update_Choosen_info( "Second Spell: " +CurrentSpell.getName());
						System.out.println("spell2 : " +CurrentSpell.getName());
					}
				CurrentSpell= this.GetSpellFromName(HarryPotterGUI.getSpell43_Box().getItemAt(HarryPotterGUI.getSpell43_Box().getSelectedIndex()));
					if (CurrentSpell!=null){
						GryffindorWizard.getSpells().add(CurrentSpell);
						Update_Choosen_info("Third Spell: " +CurrentSpell.getName());
						System.out.println("spell3 : " +CurrentSpell.getName());
				}

			
					Tournament.addChampion(GryffindorWizard);
				break;
			case "HufflepuffWizard" :
				HufflepuffWizard HufflepuffWizard = new HufflepuffWizard("HufflepuffWizard");
				HufflepuffWizard.setName(HarryPotterGUI.getChampionnameentry4().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell41_Box().getItemAt(HarryPotterGUI.getSpell41_Box().getSelectedIndex()));
				if (CurrentSpell2!=null){
					HufflepuffWizard .getSpells().add(CurrentSpell2);
					Update_Choosen_info("Fourth Champion  : " +HufflepuffWizard.getName() +  " Type : GryffindorWizard " + " First Spell: " +CurrentSpell2.getName());

					System.out.println("spell1 : " +CurrentSpell2.getName());
				}
				 CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell42_Box().getItemAt(HarryPotterGUI.getSpell42_Box().getSelectedIndex()));
					if (CurrentSpell2!=null){
						HufflepuffWizard .getSpells().add(CurrentSpell2);
						Update_Choosen_info("Second Spell: " +CurrentSpell2.getName());
						System.out.println("spell2 : " +CurrentSpell2.getName());
					}
				CurrentSpell2= this.GetSpellFromName(HarryPotterGUI.getSpell43_Box().getItemAt(HarryPotterGUI.getSpell43_Box().getSelectedIndex()));
					if (CurrentSpell2!=null){
						HufflepuffWizard .getSpells().add(CurrentSpell2);
						Update_Choosen_info("Third Spell: " +CurrentSpell2.getName());
						System.out.println("spell3 : " +CurrentSpell2.getName());
				}

					Tournament.addChampion(HufflepuffWizard);
				break;
			case "RavenclawWizard"  :
				RavenclawWizard RavenclawWizard = new RavenclawWizard("RavenclawWizard");
				RavenclawWizard.setName(HarryPotterGUI.getChampionnameentry4().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell41_Box().getItemAt(HarryPotterGUI.getSpell41_Box().getSelectedIndex()));
				if (CurrentSpell3!=null){
					RavenclawWizard  .getSpells().add(CurrentSpell3);
					Update_Choosen_info("Fourth Champion  : " +RavenclawWizard.getName() +  " Type : RavenclawWizard  " + " First Spell: " +CurrentSpell3.getName());

					System.out.println("spell1 : " +CurrentSpell3.getName());
				}
				 CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell42_Box().getItemAt(HarryPotterGUI.getSpell42_Box().getSelectedIndex()));
					if (CurrentSpell3!=null){
						RavenclawWizard  .getSpells().add(CurrentSpell3);
						Update_Choosen_info("Second Spell: " +CurrentSpell3.getName());
						System.out.println("spell2 : " +CurrentSpell3.getName());
					}
				CurrentSpell3= this.GetSpellFromName(HarryPotterGUI.getSpell43_Box().getItemAt(HarryPotterGUI.getSpell43_Box().getSelectedIndex()));
					if (CurrentSpell3!=null){
						RavenclawWizard  .getSpells().add(CurrentSpell3);
						Update_Choosen_info("Third Spell: " +CurrentSpell3.getName());
						System.out.println("spell3 : " +CurrentSpell3.getName());
				}

					Tournament.addChampion(RavenclawWizard);		
				break;
			case "SlytherinWizard"  :
				SlytherinWizard SlytherinWizard = new SlytherinWizard("SlytherinWizard");
				SlytherinWizard.setName(HarryPotterGUI.getChampionnameentry4().getText());
				//Get Choosen Spells (Three Spells for Each)
				Spell CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell41_Box().getItemAt(HarryPotterGUI.getSpell41_Box().getSelectedIndex()));
				if (CurrentSpell4!=null){
					 SlytherinWizard   .getSpells().add(CurrentSpell4);
					 Update_Choosen_info("Fourth Champion  : " +SlytherinWizard.getName() +  " Type : SlytherinWizard  " + " First Spell: " +CurrentSpell4.getName());
					System.out.println("spell1 : " +CurrentSpell4.getName());
				}
				 CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell42_Box().getItemAt(HarryPotterGUI.getSpell42_Box().getSelectedIndex()));
					if (CurrentSpell4!=null){
						 SlytherinWizard   .getSpells().add(CurrentSpell4);
						 Update_Choosen_info("Second Spell: " +CurrentSpell4.getName());
						System.out.println("spell2 : " +CurrentSpell4.getName());
					}
				CurrentSpell4= this.GetSpellFromName(HarryPotterGUI.getSpell43_Box().getItemAt(HarryPotterGUI.getSpell43_Box().getSelectedIndex()));
					if (CurrentSpell4!=null){
						 SlytherinWizard   .getSpells().add(CurrentSpell4);
						 Update_Choosen_info("Third Spell: " +CurrentSpell4.getName());
						System.out.println("spell3 : " +CurrentSpell4.getName());
				}
					Tournament.addChampion(SlytherinWizard);	
				break;
			}
					System.out.println("CH4 : " + HarryPotterGUI.getChampionnameentry4().getText());
					Pressed_Button.setEnabled(false);
					CCounter+=1;
					Start_Tournement.setEnabled(true);
					if(CCounter ==4){
						Start_Tournement.setEnabled(true);
					}
					
					
		}
	}
	private void UpdateFullInfo(Champion Target){
	
		HarryPotterGUI.getItemInventroy().removeAll();
		Wizard CurrentWizard = (Wizard)Target;
		String Spells =  "Spells:";
		Spells += "\n";
		

		
		
		if (CurrentWizard instanceof GryffindorWizard){
			Image img = (new ImageIcon("2551.jpg")).getImage() ;  
			Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
			ImageIcon icon = new ImageIcon( newimg );
			chiname.setIcon(icon);
			 chiname.setText("Player Name: " + CurrentWizard.getName());
		
			Wizardtypelabel.setText("House : GryffindorWizard");
		}else 	if (CurrentWizard instanceof HufflepuffWizard){
			Image img = (new ImageIcon("2552.jpg")).getImage() ;  
			Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
			ImageIcon icon = new ImageIcon( newimg );
			chiname.setIcon(icon);
			 chiname.setText("Player Name: " + CurrentWizard.getName());
		
			 Wizardtypelabel.setText("House : HufflepuffWizard");
			
		}else 	if (CurrentWizard instanceof  RavenclawWizard){
			Image img = (new ImageIcon("2362.jpg")).getImage() ;  
			Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
			ImageIcon icon = new ImageIcon( newimg );
			chiname.setIcon(icon);
			 chiname.setText("Player Name: " + CurrentWizard.getName());

			Wizardtypelabel.setText("House : RavenclawWizard");
			
		}else 	if (CurrentWizard instanceof SlytherinWizard){
			Image img = (new ImageIcon("2550.png")).getImage() ;  
			Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
			ImageIcon icon = new ImageIcon( newimg );
			chiname.setIcon(icon);
			 chiname.setText("Player Name: " + CurrentWizard.getName());

			Wizardtypelabel.setText("House : SlytherinWizard");
		
		}
		currentspells.removeAllItems();
		for (Spell Spell :CurrentWizard.getSpells()){
			
			if (Spell instanceof DamagingSpell){
				currentspells.addItem("Damage " + Spell.getName());
			}
			if (Spell instanceof HealingSpell){
				currentspells.addItem("Heal " + Spell.getName());
			}
			if (Spell instanceof RelocatingSpell){
				currentspells.addItem("Relocate "  + Spell.getName());
			}
			currentspells.updateUI();
			
			
			
			
			Spells += "Name : " + Spell.getName() ;
			Spells += "\n";
			Spells += "Cost : " + Spell.getCost();
			Spells += "\n";
			Spells += "Cooldown : " + Spell.getCoolDown();
			Spells += "\n";
			
		}
		Spells += "Trait Cooldown : " + CurrentWizard.getTraitCooldown();
		Spells += "\n";
		
	
		
		
		//Inventory Updater
		
		int old_i,old_j;
		old_i = 4;old_j=4;
		int empt = 16;
		
		for (Collectible item:CurrentWizard.getInventory()){
			Spells += "Inventory Item : " + item.getName() ;
			Spells += "\n";
		if(item.getName().equals("Pepperup Potion")){
		
				JPanel Itemcase = new ImagePanel(new ImageIcon("potion.png").getImage());
				HarryPotterGUI.getItemInventroy().add(Itemcase);
			}
			if(item.getName().equals("Senzu")){
				
				JPanel Itemcase = new ImagePanel(new ImageIcon("Senzu.png").getImage());
				HarryPotterGUI.getItemInventroy().add(Itemcase);
			}
			if(item.getName().contains("Thunder")){
				JPanel Itemcase = new ImagePanel(new ImageIcon("thunder.png").getImage());
				HarryPotterGUI.getItemInventroy().add(Itemcase);
			}
			if(item.getName().equals("Felix Felicis")){
				JPanel Itemcase = new ImagePanel(new ImageIcon("flex.png").getImage());
				HarryPotterGUI.getItemInventroy().add(Itemcase);
			}
			if(item.getName().equals("Skele-Gro")){
				JPanel Itemcase = new ImagePanel(new ImageIcon("gro.png").getImage());
				HarryPotterGUI.getItemInventroy().add(Itemcase);
			}
			if(item.getName().equals("Amortentia")){
				JPanel Itemcase = new ImagePanel(new ImageIcon("amort.png").getImage());
				HarryPotterGUI.getItemInventroy().add(Itemcase);
			}
			HarryPotterGUI.getItemInventroy().updateUI();
			empt -=1;
		}
		//
			for (int i = 0 ; i <empt ; i++){
				 	JPanel Itemcase = new ImagePanel(new ImageIcon("itemcase.png").getImage());
				 	HarryPotterGUI.getItemInventroy().add(Itemcase);
			}
		for (int i = 0 ; i <old_i ; i++){
			for(int j = 0; j<old_j ; j++){
			//	JPanel Itemcase = new ImagePanel(new ImageIcon("itemcase.png").getImage());
			//	HarryPotterGUI.getItemInventroy().add(Itemcase);
				
			}
		}

		HarryPotterGUI.getPalyer_info_inventory().updateUI();

		
		
		
	
	
	
		chhp.setText("HP :" + CurrentWizard.getHp());
		chip.setText("IP :" + CurrentWizard.getIp());
		ChampionFullInfo.setText(Spells);
		//make Text area Scrollable
		ChampionFullInfo.setCaretPosition(ChampionFullInfo.getDocument().getLength());
		ChampionFullInfo.updateUI();
		
	
		HarryPotterGUI.getPalyer_info_inventory().updateUI();
	
	}
	private void Update_Choosen_info(String string) {
		String Olddata = FourChampionsInfo.getText();
		Olddata += "\n";
		Olddata += string;
		FourChampionsInfo.setText( Olddata);
		FourChampionsInfo.updateUI();
		HarryPotterGUI.getChoosePlayersPanel().updateUI();
	}
	public Spell GetSpellFromName(String Spell_Name){
		
	
		
		for (Spell Spell: Tournament.getSpells()){
			if(Spell_Name.contains(Spell.getName())){
				return Spell;
			}
			
		}
		return null;
		}
	
	private void UpdateCellsFirstTask(){
		HarryPotterGUI.getFirstTask().removeAll();
		for (Champion Taskwinner:Tournament.getFirstTask().getWinners()){
			
			if (winnernotifier.contains(((Wizard)Taskwinner).getName().toString())){
			}else{
				Notify_Who_Win(((Wizard)Taskwinner).getName());
				winnernotifier.add(((Wizard)Taskwinner).getName());
			}
			}
		Cells = new ArrayList<JLabel>();
		for (int i = 0;i<Tournament.getFirstTask().getMap().length; i++){
			for (int j = 0;j<Tournament.getFirstTask().getMap()[i].length; j++){
				JLabel mapcell = new JLabel();
				
				if(i ==4){
					if(j==4){
						//at cell 4,4
						Image img = (new ImageIcon("2000070.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}
				}
				if(Tournament.getFirstTask().getMap()[i][j] instanceof ChampionCell){
					
					ChampionCell ChampionCell = (ChampionCell) (Tournament.getFirstTask().getMap()[i][j]);
					Wizard CurrentWizard = (Wizard)ChampionCell.getChamp();
					if (CurrentWizard instanceof GryffindorWizard){
						Image img = (new ImageIcon("imageedit_52_4436777975.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}else 	if (CurrentWizard instanceof HufflepuffWizard){
						Image img = (new ImageIcon("imageedit_56_2325007762.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}else 	if (CurrentWizard instanceof  RavenclawWizard){
						Image img = (new ImageIcon("imageedit_54_9136846717.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}else 	if (CurrentWizard instanceof SlytherinWizard){
						Image img = (new ImageIcon("imageedit_50_6468847174.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}
					
					
				
				}
				if(Tournament.getFirstTask().getMap()[i][j] instanceof ObstacleCell){
					
					Image img = (new ImageIcon("3004679.png")).getImage() ;  
					Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
					ImageIcon icon = new ImageIcon( newimg );
				//	mapcell.setIcon(icon);
					ImageIcon imgx = (new ImageIcon("output_obstr.gif")) ;  
					JLabel DragonCell = new JLabel(imgx);
					DragonCell.setBounds(mapcell.getBounds().x, mapcell.getBounds().y, 60, 60);
					mapcell.setIcon(DragonCell.getIcon());
				//	mapcell.setText("Obstacle");
				}
				if(Tournament.getFirstTask().getMap()[i][j] instanceof TreasureCell){
					Image img = (new ImageIcon("Treasure.png")).getImage() ;  
					Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
					ImageIcon icon = new ImageIcon( newimg );
					mapcell.setIcon(icon);
					//mapcell.setText("Treasure");
				}
				if(Tournament.getFirstTask().getMap()[i][j] instanceof WallCell){
					mapcell.setText("Wall");
				}
				if(Tournament.getFirstTask().getMap()[i][j] instanceof CollectibleCell){
					
					CollectibleCell CollectibleCell = (CollectibleCell)Tournament.getFirstTask().getMap()[i][j];
					
					if (CollectibleCell.getCollectible().getName().contains("Potion")){
						Image img = (new ImageIcon("HPotion.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
						}
						
					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Felix ")){
						Image img = (new ImageIcon("imageedit_44_8072470586.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
						mapcell.setIcon(icon);
						}
					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Skele")){
						Image img = (new ImageIcon("imageedit_30_3123284690.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}
					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Amortenti")){
						Image img = (new ImageIcon("imageedit_41_8752953100.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Thunder ")){
						Image img = (new ImageIcon("imageedit_3_5097974523.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().equals("Senzu")){
						Image img = (new ImageIcon("imageedit_27_8073199860.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					
					
					}
				if(Tournament.getFirstTask().getMap()[i][j] instanceof CupCell){
					Image img = (new ImageIcon("imageedit_64_5644640836.png")).getImage() ;  
					Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
					ImageIcon icon = new ImageIcon( newimg );
					if (Correctvisiabilty == false){
						mapcell.setIcon(icon);
						}					//mapcell.setText("Cup");
				}
				
				
				for (int mark = 0;mark < Tournament.getFirstTask().getMarkedCells().size(); mark++) {
					//Cell cell = map[Tournament.getFirstTask().getMarkedCells().get(i).x][markedCells.get(i).y];
					Point MarkedCell = Tournament.getFirstTask().getMarkedCells().get( mark);
					
					if (i == MarkedCell.x && j == MarkedCell.y){
						if(!(Tournament.getFirstTask().getMap()[i][j] instanceof ChampionCell)){
							if(!(Tournament.getFirstTask().getMap()[i][j] instanceof CollectibleCell)){
								if(!(Tournament.getFirstTask().getMap()[i][j] instanceof ObstacleCell)){
									if(!(Tournament.getFirstTask().getMap()[i][j] instanceof TreasureCell)){
										if (i !=4 && j!=4){
											ImageIcon img = (new ImageIcon("output_YnC7mK.gif")) ;  
											JLabel DragonCell = new JLabel(img);
											DragonCell.setBounds(mapcell.getBounds().x, mapcell.getBounds().y, 60, 60);
											mapcell.setIcon(DragonCell.getIcon());
										}
									}
									}
								}
							}
							
						
					}
					
					
				}
			
				//Tournament.getFirstTask().getMap()[i][j]
			//	mapcell.addActionListener(this);
				mapcell.setForeground(Color.YELLOW);
				HarryPotterGUI.Add_Map_Cell_First_Task(mapcell);
			
				Cells.add(mapcell);
			
			}
		}
		
		
	}












	@Override
	public void onFinishingFirstTask(ArrayList<Champion> winners)
			throws IOException {
		// TODO Auto-generated method stub
		
		
		
	
		
		
		System.out.print("First Task Finished " + winners.size());
		if (winners.size() >0){
			
			HarryPotterGUI.getFirstTask().setVisible(false);
			
			HarryPotterGUI.getSecondTask().setLayout(new GridLayout(0, 10));
			Tournament.onFinishingFirstTask(winners);
		
			
			HarryPotterGUI.add(	HarryPotterGUI.getSecondTask(),BorderLayout.CENTER);
			HarryPotterGUI.getSecondTask().setVisible(true);
			//HarryPotterGUI.repaint();
			
			inFirstTask = false ;
			inSecondTask = true ;
			inThirdTask = false ;


			Tournament.getSecondTask().setListener(this );
			this.UpdateCellsSecondTask();
			Taskinfo.setText("Current Task : Second Task");
			Taskinfo.updateUI();
			
		}else if (winners.size() <0 || winners.size() ==0){
			HarryPotterGUI.getFirstTask().setVisible(false);
				 Gameover();
					
			
		}
		
	
		
	}












	@Override
	public void onFinishingSecondTask(ArrayList<Champion> winners)
			throws IOException {
		// TODO Auto-generated method stub
		System.out.print("Second Task Winners Size : " + winners.size());
		if (winners.size() >0){
		
			
			
			System.out.print("Third Task Started");
			HarryPotterGUI.getSecondTask().setVisible(false);
			HarryPotterGUI.getThirdTask().setLayout(new GridLayout(0, 10));
			Tournament.onFinishingSecondTask(winners);
			HarryPotterGUI.add(	HarryPotterGUI.getThirdTask(),BorderLayout.CENTER);

			HarryPotterGUI.getThirdTask().setVisible(true);
			inFirstTask = false ;
			inSecondTask = false;
			inThirdTask = true ;
			Tournament.getThirdTask().setListener(this);
		
			
			
			
		
			
		
			this.UpdateCellsThirdTask();
			Taskinfo.setText("Current Task :  Third Task");
			Taskinfo.updateUI();
		}else if (winners.size() ==0){
			HarryPotterGUI.getSecondTask().setVisible(false);
			 Gameover();
		}
			

		
		
	}


	public void Notify_Who_Win(String Championname){
		
		
		
		new MessageBox("A Champion Won : " + Championname);
	}









	@Override
	public void onFinishingThirdTask(Champion winner) {
		HarryPotterGUI.getFirstTask().setVisible(false);
		HarryPotterGUI.getSecondTask().setVisible(false);
		HarryPotterGUI.getThirdTask().setVisible(false);
		HarryPotterGUI.getGameover().setVisible(false);
		HarryPotterGUI.getPalyer_info_inventory().setVisible(false);
		HarryPotterGUI.add(HarryPotterGUI.getGameWins(), BorderLayout.CENTER);
		JLabel Winner_Data = new JLabel("Winner : "  + ((Wizard)winner).getName());
		 Winner_Data .setBounds(HarryPotterGUI.getBounds().width/2-50,320,100,25);
		
		 JLabel winnerimage = new JLabel();
		 winnerimage.setBounds(HarryPotterGUI.getBounds().width/2-150, 350, 300, 60);
		 if (winner instanceof GryffindorWizard){
			 Image img = (new ImageIcon("imageedit_52_4436777975.png")).getImage() ;  
				Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
				ImageIcon icon = new ImageIcon( newimg );
				 winnerimage.setIcon(icon);
				 winnerimage.setText("Type : GryffindorWizard");
		 }else if (winner instanceof HufflepuffWizard){
				Image img = (new ImageIcon("imageedit_56_2325007762.png")).getImage() ;  
				Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
				ImageIcon icon = new ImageIcon( newimg );
				 winnerimage.setIcon(icon);
				 winnerimage.setText("Type : HufflepuffWizard");

		 }else if (winner instanceof RavenclawWizard){
			 Image img = (new ImageIcon("imageedit_54_9136846717.png")).getImage() ;  
				Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
				ImageIcon icon = new ImageIcon( newimg );
				 winnerimage.setIcon(icon);
				 winnerimage.setText("Type : RavenclawWizard");

		 }else if (winner instanceof SlytherinWizard){
			 Image img = (new ImageIcon("imageedit_50_6468847174.png")).getImage() ;  
				Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
				ImageIcon icon = new ImageIcon( newimg );
				 winnerimage.setIcon(icon);
				 winnerimage.setText("Type : SlytherinWizard");

		 }
		 Winner_Data .setForeground(Color.GREEN);
		 winnerimage.setForeground(Color.GREEN);
		 HarryPotterGUI.getGameWins().add(Winner_Data);
		 
		 HarryPotterGUI.getGameWins().add(winnerimage);
		 JButton Restart = new JButton("Restart Game");
		 Restart .addActionListener(this);
		Restart.setBounds(HarryPotterGUI.getBounds().width/2-50, 420, 100, 30);
		 HarryPotterGUI.getGameWins().add(Restart);
		HarryPotterGUI.getGameWins().updateUI();
		//HarryPotterGUI.getGameWins();
		System.out.print("Third Task Finished ");
	
		
	}
	private void UpdateCellsSecondTask(){

		HarryPotterGUI.getSecondTask().removeAll();
		for (Champion Taskwinner:Tournament.getSecondTask().getWinners()){
			if (winnernotifier.contains(((Wizard)Taskwinner).getName().toString())){
			}else{
				Notify_Who_Win(((Wizard)Taskwinner).getName());
				winnernotifier.add(((Wizard)Taskwinner).getName());
			}		}
		Cells = new ArrayList<JLabel>();
		for (int i = 0;i<Tournament.getSecondTask().getMap().length; i++){
			for (int j = 0;j<Tournament.getSecondTask().getMap()[i].length; j++){
				JLabel mapcell = new JLabel();
				
				if(i ==4){
					if(j==4){
						//at cell 4,4
						Image img = (new ImageIcon("2000070.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}
				}
				if(Tournament.getSecondTask().getMap()[i][j] instanceof ChampionCell){
					
					ChampionCell ChampionCell = (ChampionCell) (Tournament.getSecondTask().getMap()[i][j]);
					Wizard CurrentWizard = (Wizard)ChampionCell.getChamp();
					if (CurrentWizard instanceof GryffindorWizard){
						Image img = (new ImageIcon("imageedit_52_4436777975.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}else 	if (CurrentWizard instanceof HufflepuffWizard){
						Image img = (new ImageIcon("imageedit_56_2325007762.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}else 	if (CurrentWizard instanceof  RavenclawWizard){
						Image img = (new ImageIcon("imageedit_54_9136846717.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}else 	if (CurrentWizard instanceof SlytherinWizard){
						Image img = (new ImageIcon("imageedit_50_6468847174.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}
					
					
				
				}
				if(Tournament.getSecondTask().getMap()[i][j] instanceof ObstacleCell){
					ObstacleCell  ObstacleCell = (ObstacleCell)Tournament.getSecondTask().getMap()[i][j] ;
					if (ObstacleCell.getObstacle() instanceof Merperson){
						
						ImageIcon imgx = (new ImageIcon("output_Mer.gif")) ;  
					JLabel DragonCell = new JLabel(imgx);
					DragonCell.setBounds(mapcell.getBounds().x, mapcell.getBounds().y, 60, 60);
					mapcell.setIcon(DragonCell.getIcon());
					}
                    if (ObstacleCell.getObstacle() instanceof PhysicalObstacle){
                    	Image img = (new ImageIcon("3004679.png")).getImage() ;  
    					Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
    					ImageIcon icon = new ImageIcon( newimg );
    					mapcell.setIcon(icon);
					}
					
				//	mapcell.setText("Obstacle");
				}
				if(Tournament.getSecondTask().getMap()[i][j] instanceof TreasureCell){
					Image img = (new ImageIcon("Treasure.png")).getImage() ;  
					Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
					ImageIcon icon = new ImageIcon( newimg );
					if (Correctvisiabilty == false){
						mapcell.setIcon(icon);
						}					//mapcell.setText("Treasure");
				}
			
				if(Tournament.getSecondTask().getMap()[i][j] instanceof CollectibleCell){
					CollectibleCell CollectibleCell = (CollectibleCell)Tournament.getSecondTask().getMap()[i][j];
					
					if (CollectibleCell.getCollectible().getName().contains("Potion")){
						Image img = (new ImageIcon("HPotion.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Felix ")){
						Image img = (new ImageIcon("imageedit_44_8072470586.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Skele")){
						Image img = (new ImageIcon("imageedit_30_3123284690.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Amortenti")){
						Image img = (new ImageIcon("imageedit_41_8752953100.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Thunder ")){
						Image img = (new ImageIcon("imageedit_3_5097974523.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().equals("Senzu")){
						Image img = (new ImageIcon("imageedit_27_8073199860.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					
					
					
				}
				if(Tournament.getSecondTask().getMap()[i][j] instanceof CupCell){
					Image img = (new ImageIcon("imageedit_64_5644640836.png")).getImage() ;  
					Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
					ImageIcon icon = new ImageIcon( newimg );
					if (Correctvisiabilty == false){
						mapcell.setIcon(icon);
						}					//mapcell.setText("Cup");
				}
			
				//Tournament.getFirstTask().getMap()[i][j]
			//	mapcell.addActionListener(this);
				mapcell.setForeground(Color.YELLOW);
		
				HarryPotterGUI.Add_Map_Cell_Second_Task(mapcell);
			
				Cells.add(mapcell);
			
			}
		}
		
		
	}
	private void UpdateCellsThirdTask(){

		HarryPotterGUI.getThirdTask().removeAll();
	
		Cells = new ArrayList<JLabel>();
		for (int i = 0;i<Tournament.getThirdTask().getMap().length; i++){
			for (int j = 0;j<Tournament.getThirdTask().getMap()[i].length; j++){
				JLabel mapcell = new JLabel();
				
				if(i ==4){
					if(j==4){
						//at cell 4,4
						Image img = (new ImageIcon("2000070.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}
				}
				if(Tournament.getThirdTask().getMap()[i][j] instanceof ChampionCell){
					
					ChampionCell ChampionCell = (ChampionCell) (Tournament.getThirdTask().getMap()[i][j]);
					Wizard CurrentWizard = (Wizard)ChampionCell.getChamp();
					if (CurrentWizard instanceof GryffindorWizard){
						Image img = (new ImageIcon("imageedit_52_4436777975.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}else 	if (CurrentWizard instanceof HufflepuffWizard){
						Image img = (new ImageIcon("imageedit_56_2325007762.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}else 	if (CurrentWizard instanceof  RavenclawWizard){
						Image img = (new ImageIcon("imageedit_54_9136846717.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}else 	if (CurrentWizard instanceof SlytherinWizard){
						Image img = (new ImageIcon("imageedit_50_6468847174.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						mapcell.setIcon(icon);
					}
					
					
				
				}
				if(Tournament.getThirdTask().getMap()[i][j] instanceof ObstacleCell){
					
					ImageIcon imgx = (new ImageIcon("output_obtask3.gif")) ;  
					JLabel DragonCell = new JLabel(imgx);
					DragonCell.setBounds(mapcell.getBounds().x, mapcell.getBounds().y, 60, 60);
					mapcell.setIcon(DragonCell.getIcon());
				//	mapcell.setText("Obstacle");
				}
				if(Tournament.getThirdTask().getMap()[i][j] instanceof TreasureCell){
					Image img = (new ImageIcon("Treasure.png")).getImage() ;  
					Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
					ImageIcon icon = new ImageIcon( newimg );
					if (Correctvisiabilty == false){
						mapcell.setIcon(icon);
						}					//mapcell.setText("Treasure");
				}
				if(Tournament.getThirdTask().getMap()[i][j] instanceof WallCell){
					Image img = (new ImageIcon("wall.png")).getImage() ;  
					Image newimg = img.getScaledInstance(80,80,  java.awt.Image.SCALE_SMOOTH ) ;  
					ImageIcon icon = new ImageIcon( newimg );
					mapcell.setIcon(icon);
					mapcell.setText("Wall");
				}
				if(Tournament.getThirdTask().getMap()[i][j] instanceof CollectibleCell){
					CollectibleCell CollectibleCell = (CollectibleCell)Tournament.getThirdTask().getMap()[i][j];
					
					if (CollectibleCell.getCollectible().getName().contains("Potion")){
						Image img = (new ImageIcon("HPotion.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Felix ")){
						Image img = (new ImageIcon("imageedit_44_8072470586.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Skele")){
						Image img = (new ImageIcon("imageedit_30_3123284690.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Amortenti")){
						Image img = (new ImageIcon("imageedit_41_8752953100.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().contains("Thunder ")){
						Image img = (new ImageIcon("imageedit_3_5097974523.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					if (CollectibleCell.getCollectible().getName().equals("Senzu")){
						Image img = (new ImageIcon("imageedit_27_8073199860.png")).getImage() ;  
						Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
						ImageIcon icon = new ImageIcon( newimg );
						if (Correctvisiabilty == false){
							mapcell.setIcon(icon);
							}					//	mapcell.setText("Collectible");
					}
					
					
					
					
				}
				if(Tournament.getThirdTask().getMap()[i][j] instanceof CupCell){
				
					Image img = (new ImageIcon("imageedit_64_5644640836.png")).getImage() ;  
					Image newimg = img.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH ) ;  
					ImageIcon icon = new ImageIcon( newimg );
					mapcell.setIcon(icon);
					//mapcell.setText("Cup");
				}
			
				//Tournament.getFirstTask().getMap()[i][j]
			//	mapcell.addActionListener(this);
				mapcell.setForeground(Color.YELLOW);
				
			HarryPotterGUI.Add_Map_Cell_Third_Task(mapcell);
				Cells.add(mapcell);
			
			}
		}
		
		
	}
	public void DamgingSpellForward(DamagingSpell Spell){

		 Wizard Current = null;

			if(inFirstTask){
				Current = (Wizard) Tournament.getFirstTask().getCurrentChamp();
			}
			if(inSecondTask){
				Current = (Wizard) Tournament.getSecondTask().getCurrentChamp();
			}
			if(inThirdTask){
				Current = (Wizard) Tournament.getThirdTask().getCurrentChamp();
			}
		if(inFirstTask){
					
		if(Current.getLocation().x>0){
			
		
			if(Tournament.getFirstTask().getMap()[Current.getLocation().x-1][Current.getLocation().y] instanceof ObstacleCell){
				
			
					
						try {
							Tournament.getFirstTask().castDamagingSpell((DamagingSpell)Spell, Direction.FORWARD);
							
						} catch (InCooldownException
								| NotEnoughIPException
								| OutOfBordersException
								| InvalidTargetCellException
								| IOException e1) {
							// TODO Auto-generated catch block
							
							try {
								Tournament.getFirstTask().endTurn();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					
				
				
				
			}
			
		}
		
		
			this. UpdateCellsFirstTask();
	
		
		
		}//Check Current Map to avoid editing in another Task ######### 1st Task
		
		if(inSecondTask){
			
		if(Current.getLocation().x>0){
			if(Tournament.getSecondTask().getMap()[Current.getLocation().x-1][Current.getLocation().y] instanceof ObstacleCell){
				
				
						try {
							Tournament.getSecondTask().castDamagingSpell((DamagingSpell)Spell, Direction.FORWARD);
				
						} catch (InCooldownException
								| NotEnoughIPException
								| OutOfBordersException
								| InvalidTargetCellException
								| IOException e1) {
							// TODO Auto-generated catch block
							try {
								Tournament.getSecondTask().endTurn();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					
					
				
				
				
			}
			
		}
		
		
			this. UpdateCellsSecondTask();
	
		
		
		}//Check Current Map to avoid editing in another Task ######### 2nd Task	
		if(inThirdTask){
			
			if(Current.getLocation().x>0){
				if(Tournament.getThirdTask().getMap()[Current.getLocation().x-1][Current.getLocation().y] instanceof ObstacleCell){
					
					
				
							try {
								Tournament.getThirdTask().castDamagingSpell((DamagingSpell)Spell, Direction.FORWARD);
					
							} catch (InCooldownException
									| NotEnoughIPException
									| OutOfBordersException
									| InvalidTargetCellException
									| IOException e1) {
								// TODO Auto-generated catch block
								try {
									Tournament.getThirdTask().endTurn();
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							}
						
						
					
					
					
				}
				
			}
			
			
				this. UpdateCellsThirdTask();
		
			
			
			}//Check Current Map to avoid editing in another Task ######### 2nd Task	
	}
	
	public void DamagingSpellDownward(DamagingSpell Spell ){
		 Wizard Current = null;

			if(inFirstTask){
				Current = (Wizard) Tournament.getFirstTask().getCurrentChamp();
			}
			if(inSecondTask){
				Current = (Wizard) Tournament.getSecondTask().getCurrentChamp();
			}
			if(inThirdTask){
				Current = (Wizard) Tournament.getThirdTask().getCurrentChamp();
			}
		if(inFirstTask){
			
			if(Current.getLocation().x<9){
				if(Tournament.getFirstTask().getMap()[Current.getLocation().x+1][Current.getLocation().y] instanceof ObstacleCell){
					
			
						if(Spell instanceof DamagingSpell){
							try {
								Tournament.getFirstTask().castDamagingSpell((DamagingSpell)Spell, Direction.BACKWARD);
								return;
							} catch (InCooldownException
									| NotEnoughIPException
									| OutOfBordersException
									| InvalidTargetCellException
									| IOException e1) {
								// TODO Auto-generated catch block
								try {
									Tournament.getFirstTask().endTurn();
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							}
						}
					}
					
					
				
				
			}
			
			

			this. UpdateCellsFirstTask();
			}//Check Current Map to avoid editing in another Task
			if(inSecondTask){
				
				if(Current.getLocation().x<9){
					if(Tournament.getSecondTask().getMap()[Current.getLocation().x+1][Current.getLocation().y] instanceof ObstacleCell){
						
						
							if(Spell instanceof DamagingSpell){
								try {
									Tournament.getSecondTask().castDamagingSpell((DamagingSpell)Spell, Direction.BACKWARD);
									return;
								} catch (InCooldownException
										| NotEnoughIPException
										| OutOfBordersException
										| InvalidTargetCellException
										| IOException e1) {
									// TODO Auto-generated catch block
									try {
										Tournament.getSecondTask().endTurn();
									} catch (IOException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
								}
							}
						}
						
						
					
					
				}
				
				

				this. UpdateCellsSecondTask();
				}//Check Current Map to avoid editing in another Task 2nd Task
			if(inThirdTask){
				
				if(Current.getLocation().x<9){
					if(Tournament.getThirdTask().getMap()[Current.getLocation().x+1][Current.getLocation().y] instanceof ObstacleCell){
						
						
							if(Spell instanceof DamagingSpell){
								try {
									Tournament.getThirdTask().castDamagingSpell((DamagingSpell)Spell, Direction.BACKWARD);
									return;
								} catch (InCooldownException
										| NotEnoughIPException
										| OutOfBordersException
										| InvalidTargetCellException
										| IOException e1) {
									// TODO Auto-generated catch block
									try {
										Tournament.getThirdTask().endTurn();
									} catch (IOException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
								}
							}
						}
						
						
					}
					
				
				
				

				this. UpdateCellsThirdTask();
				}//Check Current Map to avoid editing in another Task 2nd Task
	}
	public void DamagingSpellRight(DamagingSpell Spell){
		 Wizard Current = null;

			if(inFirstTask){
				Current = (Wizard) Tournament.getFirstTask().getCurrentChamp();
			}
			if(inSecondTask){
				Current = (Wizard) Tournament.getSecondTask().getCurrentChamp();
			}
			if(inThirdTask){
				Current = (Wizard) Tournament.getThirdTask().getCurrentChamp();
			}

			if(inFirstTask){
						
			if(Current.getLocation().y<9){
				if(Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y+1] instanceof ObstacleCell){
					
			
						if(Spell instanceof DamagingSpell){
							try {
								Tournament.getFirstTask().castDamagingSpell((DamagingSpell)Spell, Direction.RIGHT);
							} catch (InCooldownException
									| NotEnoughIPException
									| OutOfBordersException
									| InvalidTargetCellException
									| IOException e1) {
								// TODO Auto-generated catch block
								try {
									Tournament.getFirstTask().endTurn();
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							}
						}
					
					
					
				}
				
			}
			
			

			this. UpdateCellsFirstTask();
			}//Check Current Map to avoid editing in another Task
			

			if(inSecondTask){
						
			if(Current.getLocation().y<9){
				if(Tournament.getSecondTask().getMap()[Current.getLocation().x][Current.getLocation().y+1] instanceof ObstacleCell){
					
				
						if(Spell instanceof DamagingSpell){
							try {
								Tournament.getSecondTask().castDamagingSpell((DamagingSpell)Spell, Direction.RIGHT);
							} catch (InCooldownException
									| NotEnoughIPException
									| OutOfBordersException
									| InvalidTargetCellException
									| IOException e1) {
								// TODO Auto-generated catch block
								try {
									Tournament.getSecondTask().endTurn();
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							}
						}
					
					
					
				}
				
			}
			
			

			this. UpdateCellsSecondTask();
			}//Check Current Map to avoid editing in another Task
			if(inThirdTask){
				
				if(Current.getLocation().y<9){
					if(Tournament.getThirdTask().getMap()[Current.getLocation().x][Current.getLocation().y+1] instanceof ObstacleCell){
						
						
							if(Spell instanceof DamagingSpell){
								try {
									Tournament.getThirdTask().castDamagingSpell((DamagingSpell)Spell, Direction.RIGHT);
								} catch (InCooldownException
										| NotEnoughIPException
										| OutOfBordersException
										| InvalidTargetCellException
										| IOException e1) {
									// TODO Auto-generated catch block
									try {
										Tournament.getThirdTask().endTurn();
									} catch (IOException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
								}
							}
						
						
						
					}
					
				}
				
				

				this. UpdateCellsThirdTask();
				}//Check Current Map to avoid editing in another Task		
			
	}
	public void DamagingSpellLeft(DamagingSpell Spell){
		
		 Wizard Current = null;

			if(inFirstTask){
				Current = (Wizard) Tournament.getFirstTask().getCurrentChamp();
			}
			if(inSecondTask){
				Current = (Wizard) Tournament.getSecondTask().getCurrentChamp();
			}
			if(inThirdTask){
				Current = (Wizard) Tournament.getThirdTask().getCurrentChamp();
			}
		if(inFirstTask){
					
		if(Current.getLocation().y>0){
			if(Tournament.getFirstTask().getMap()[Current.getLocation().x][Current.getLocation().y-1] instanceof ObstacleCell){
				
			
					if(Spell instanceof DamagingSpell){
						try {
							Tournament.getFirstTask().castDamagingSpell((DamagingSpell)Spell, Direction.LEFT);
						} catch (InCooldownException
								| NotEnoughIPException
								| OutOfBordersException
								| InvalidTargetCellException
								| IOException e1) {
							// TODO Auto-generated catch block
							try {
								Tournament.getFirstTask().endTurn();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					}
				
				
				
			}
			
		}
		
		

		this. UpdateCellsFirstTask();
		}//Check Current Map to avoid editing in another Task
		
		if(inSecondTask){
			
			if(Current.getLocation().y>0){
				if(Tournament.getSecondTask().getMap()[Current.getLocation().x][Current.getLocation().y-1] instanceof ObstacleCell){
					
		
						if(Spell instanceof DamagingSpell){
							try {
								Tournament.getSecondTask().castDamagingSpell((DamagingSpell)Spell, Direction.LEFT);
							} catch (InCooldownException
									| NotEnoughIPException
									| OutOfBordersException
									| InvalidTargetCellException
									| IOException e1) {
								// TODO Auto-generated catch block
								try {
									Tournament.getSecondTask().endTurn();
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							}
						}
					
					
					
				}
				
			}
			
			

			this. UpdateCellsSecondTask();
			}//Check Current Map to avoid editing in another Task
			
		if(inThirdTask){
			
			if(Current.getLocation().y>0){
				if(Tournament.getThirdTask().getMap()[Current.getLocation().x][Current.getLocation().y-1] instanceof ObstacleCell){
					
				
						if(Spell instanceof DamagingSpell){
							try {
								Tournament.getThirdTask().castDamagingSpell((DamagingSpell)Spell, Direction.LEFT);
							} catch (InCooldownException
									| NotEnoughIPException
									| OutOfBordersException
									| InvalidTargetCellException
									| IOException e1) {
								// TODO Auto-generated catch block
								try {
									Tournament.getThirdTask().endTurn();
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							}
						}
					}
					
					
				
				
			}
			
			

			this. UpdateCellsThirdTask();
			}//Check Current Map to avoid editing in another Task
	}
	// RelocatingInput
	public void relocateFirstTask(RelocatingSpell Spell,Direction d,Direction t ,int range){
		try {
			Tournament.getFirstTask().castRelocatingSpell(Spell, d, t, range);
			this.UpdateCellsFirstTask();
		} catch (InCooldownException e) {
			new MessageBox(" You must wait 3 until you can use this spell/trait again");
		} catch (NotEnoughIPException e) {
			// TODO Auto-generated catch block
			new MessageBox(e.getMessage());
		//	e.printStackTrace();
		} catch (OutOfBordersException e) {
			// TODO Auto-generated catch block
			new MessageBox(e.getMessage());
			//e.printStackTrace();
		} catch (InvalidTargetCellException e) {
			new MessageBox("Cannot cast a relocating spell with destination ObstacleCell");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OutOfRangeException e) {
			// TODO Auto-generated catch block
			new MessageBox(e.getMessage());
		//	e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			new MessageBox(e.getMessage());
		//	e.printStackTrace();
		}
	}
	public void RelocateSecondTask(RelocatingSpell Spell,Direction d,Direction t ,int range){
		try {
			Tournament.getSecondTask().castRelocatingSpell(Spell, d, t, range);
			this.UpdateCellsSecondTask();
		} catch (InCooldownException e) {
			// TODO Auto-generated catch block
			new MessageBox(" You must wait 3 until you can use this spell/trait again");
		} catch (NotEnoughIPException e) {
			// TODO Auto-generated catch block
			new MessageBox(e.getMessage());
			//e.printStackTrace();
		} catch (OutOfBordersException e) {
			// TODO Auto-generated catch block
			new MessageBox(e.getMessage());
			//e.printStackTrace();
		} catch (InvalidTargetCellException e) {
			// TODO Auto-generated catch block
			new MessageBox("Cannot cast a relocating spell with destination ObstacleCell");
			e.printStackTrace();
		} catch (OutOfRangeException e) {
			new MessageBox("The allowed range for this spell is 1");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			new MessageBox(e.getMessage());
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	public void RelocatingThirdTask(RelocatingSpell Spell,Direction d,Direction t ,int range){
		try {
			Tournament.getThirdTask().castRelocatingSpell(Spell, d, t, range);
			this.UpdateCellsThirdTask();
		} catch (InCooldownException e) {
			// TODO Auto-generated catch block
			new MessageBox(" You must wait 3 until you can use this spell/trait again");
		} catch (NotEnoughIPException e) {
			// TODO Auto-generated catch block
			new MessageBox(e.getMessage());
		} catch (OutOfBordersException e) {
			// TODO Auto-generated catch block
			new MessageBox(e.getMessage());
		} catch (InvalidTargetCellException e) {
			// TODO Auto-generated catch block
			new MessageBox(e.getMessage());
		} catch (OutOfRangeException e) {
			// TODO Auto-generated catch block
			new MessageBox(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			new MessageBox(e.getMessage());
		}
	}
	public void Gameover(){
		HarryPotterGUI.getGameover();
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


//Custom MessageInputBox

class RelocatingInput
{
private int range;
private Direction t;
private Direction d;


  public int getRange() {
	return range;
}

public Direction getT() {//place from move obstacle to 
	return t;
}
public Direction getD() {//place to move obstacle to 
	return d;
}

public void SetListener(){
	
}

public RelocatingInput()
  {
	String [] Directions = {"Forward","Backward","Right","Left"};
    // a jframe here isn't strictly necessary, but it makes the example a little more real
    JFrame frame = new JFrame("Relocating Spell");
    
    String neededDirection = (String) JOptionPane.showInputDialog(frame, 
            "Choose Destenation to Relocate to , Move Obstacle To Direction ?",
            "Use Rellocating Spell",
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            Directions, 
            Directions[0]);
    if(neededDirection.equals("Forward")){
    	t = Direction.FORWARD;
    }
    if(neededDirection.equals("Backward")){
    	t = Direction.BACKWARD;
    }
    if(neededDirection.equals("Right")){
    	t = Direction.RIGHT;
    }
 	if(neededDirection.equals("Left")){
 		t = Direction.LEFT;
 	}
 	if(neededDirection.equals(null)){
 	  	new  MessageBox("Please Choose One Direction to move obstacle to");
 	}
    
 	 String ToDirection = (String) JOptionPane.showInputDialog(frame, 
             "Choose Obstacle to Relocate , Move Obstacle From  Direction ?",
             "Use Rellocating Spell",
             JOptionPane.QUESTION_MESSAGE, 
             null, 
             Directions, 
             Directions[0]);
     if(ToDirection .equals("Forward")){
     	d = Direction.FORWARD;
     }
     if(ToDirection .equals("Backward")){
     	d = Direction.BACKWARD;
     }
     if(ToDirection .equals("Right")){
     	d = Direction.RIGHT;
     }
  	if(ToDirection .equals("Left")){
  		d = Direction.LEFT;
  	}
  	if(ToDirection .equals(null)){
  	  	new  MessageBox("Please Choose One Direction to move obstacle to");
  	}
    
    
    
    
    String range = JOptionPane.showInputDialog(frame,  "Enter Number of Cells ",  "Enter Relocating Spell Entries",  JOptionPane.WARNING_MESSAGE
        );
    this.range = Integer.parseInt(range);

  }
}
//MessageBoxNotifier
class MessageBox
{

    public MessageBox(String infoMessage)//+ titleBar
    {
        JOptionPane.showMessageDialog(null, infoMessage, "HarryPotter", JOptionPane.INFORMATION_MESSAGE);
    }
}

//Custom MessageInputBox

class DamagingInput
{

private Direction DamagingDirection;


public Direction getT() {//place to Damaging spell to use in 
	return  DamagingDirection;
}


public void SetListener(){
	
}

public DamagingInput()
{
	String [] Directions = {"Forward","Backward","Right","Left"};
  // a jframe here isn't strictly necessary, but it makes the example a little more real
  JFrame frame = new JFrame("Damaging Spell");
  
  String neededDirection = (String) JOptionPane.showInputDialog(frame, 
          "Enter Direction to Use Damaging Spell In",
          "Use Damaging Spell",
          JOptionPane.QUESTION_MESSAGE, 
          null, 
          Directions, 
          Directions[0]);
  if(neededDirection.equals("Forward")){
	  DamagingDirection = Direction.FORWARD;
  }
  if(neededDirection.equals("Backward")){
	  DamagingDirection = Direction.BACKWARD;
  }
  if(neededDirection.equals("Right")){
	  DamagingDirection = Direction.RIGHT;
  }
	if(neededDirection.equals("Left")){
		 DamagingDirection = Direction.LEFT;
	}
	if(neededDirection.equals(null)){
	  	new  MessageBox("Please Choose One Direction to use Damaging Spell");
	}
  
  
  
  
  
  

}
}