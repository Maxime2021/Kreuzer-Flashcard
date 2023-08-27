package com.amedeekreuzer.flashcards;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class FlashCardBuilder {

	private JTextArea question;
	private JTextArea answer;
	private ArrayList <FlashCard> cardList;
	private JFrame frame;
	
	public FlashCardBuilder() {
		//Build User interface 
		frame = new JFrame("Flash Card");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create a JPanel to hold everything
		JPanel mainPanel = new JPanel();
		
		//Create Font
		Font gratFont = new Font("Helvetica Neue", Font.BOLD,21);
		question = new JTextArea(6,20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(gratFont);
		
		//Question area
		JScrollPane qJScrollPane = new JScrollPane(question);
		qJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		//Answer area 
		answer = new JTextArea(6,20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(gratFont);
		
		
		//JscrollPane
		JScrollPane aJScrollPane = new JScrollPane(answer);
		aJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
		//Create Button
		JButton nextBtn = new JButton("Next Card");
		
		cardList = new ArrayList<FlashCard>();
		
		
		
		//Create a few labels	
		JLabel qJLabel = new JLabel("Question");
		JLabel aJLabel = new JLabel("Answer");
		
		
		//Add components to mainPanel
		mainPanel.add(qJLabel);
		mainPanel.add(qJScrollPane);
		mainPanel.add(aJLabel);
		mainPanel.add(aJScrollPane);
		mainPanel.add(nextBtn);
		nextBtn.addActionListener(new NextCardListener());
		
		//Menubar 
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		
		menuBar.add(fileMenu);
		
		
		
		
		
		//Add eventListeners
		newMenuItem.addActionListener(new NewMenuItemListener());
		saveMenuItem.addActionListener(new SaveMenuListener());
		
		
		frame.setJMenuBar(menuBar);
		
		
		//Add to the frame
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500,600);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FlashCardBuilder();
			}
		});
	}
	
	class NextCardListener	implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Create a flash card 
			
			FlashCard card = new FlashCard(question.getText(),answer.getText());
			cardList.add(card);
			System.out.println("Size of ArrayList: "+ cardList.size());
			clearCard();
		}

		
		
	}
	
	class NewMenuItemListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("New Menu clicked");
		}
	}
	
	class SaveMenuListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			FlashCard card = new FlashCard(question.getText(), answer.getText());
			cardList.add(card);
			
			//Create file dialog with file chooser
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
		}

		
	}
		
	private void clearCard() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
		
	}
	
	private void saveFile(File selectedFile) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
			Iterator<FlashCard> cardIterator = cardList.iterator();
			while(cardIterator.hasNext()) {
				FlashCard card = (FlashCard)cardIterator.next();
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
				
				//Format to be like this: Where is Mozambique/Africa 	
			}
			writer.close();
//			For each loop
//			for(FlashCard card: cardList) {
//				writer.write(card.getQuestion() + "/");
//				writer.write(card.getAnswer() + "\n");
//			}
//			For loop
//			for(int i = 0; i < cardList.size();i++) {
//				writer.write(cardList.get(i).getQuestion() + "/");
//				writer.write(cardList.get(i).getAnswer() + "\n");
//			}
			
		}catch(Exception e) {
			System.out.println("Couldn't write to file");
		}
		
	}
}
