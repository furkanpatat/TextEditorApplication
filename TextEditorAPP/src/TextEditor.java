import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
public class TextEditor extends JFrame implements ActionListener{

	JTextArea textArea;
	JScrollPane scrollPane;
	JLabel fontLabel;
	JSpinner fontSizeSpinner;
	JButton fontcolorButton;
	JComboBox fontBox;
	JColorChooser colorChooser;
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem exitItem;
	JMenu settings;
	JMenuItem backgroundItem;
	JMenuItem textAreaBackgroundItem;
	ImageIcon openFolderImage,saveFolderImage,exitImage,fileImage,frameImage,settingsImage,colorImage;
	
	TextEditor(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Awesome Text Editor");
		this.setSize(820,900);
		this.setLayout(new FlowLayout(FlowLayout.LEADING)); 
		this.setLocationRelativeTo(null);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true); // ASAGI SATIRA GECER
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Arial",Font.PLAIN,20));
		
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(800,800));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		fontLabel = new JLabel("Font:");
		
		fontSizeSpinner = new JSpinner();
		fontSizeSpinner.setPreferredSize(new Dimension(50,25));
		fontSizeSpinner.setValue(20);
		fontSizeSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int) fontSizeSpinner.getValue()));
				
			}
			
		});
		
		
		
		fontcolorButton = new JButton("Text Color");
		fontcolorButton.addActionListener(this);
		
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		fontBox = new JComboBox(fonts);
		fontBox.addActionListener(this);
		fontBox.setSelectedItem("Arial");
		
		//------------menubar --------------
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");
		exitItem = new JMenuItem("Exit");
		
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		
		menuBar.add(fileMenu);
		
		settings = new JMenu("Settings");
		
		backgroundItem = new JMenuItem("Frame Background Color");
		backgroundItem.addActionListener(this);
		textAreaBackgroundItem = new JMenuItem("TextArea Background Color");
		textAreaBackgroundItem.addActionListener(this);
		
		settings.add(backgroundItem);
		settings.add(textAreaBackgroundItem);
		
		menuBar.add(settings);
		
		
		settingsImage = new ImageIcon("your png");
		settings.setIcon(settingsImage);
		colorImage = new ImageIcon("your png");
		textAreaBackgroundItem.setIcon(colorImage);
		backgroundItem.setIcon(colorImage);
		openFolderImage = new ImageIcon("your png");
		openItem.setIcon(openFolderImage);
		saveFolderImage = new ImageIcon("your png");
		saveItem.setIcon(saveFolderImage);
		exitImage = new ImageIcon("your png");
		exitItem.setIcon(exitImage);
		fileImage = new ImageIcon("your png");
		fileMenu.setIcon(fileImage);
		frameImage = new ImageIcon("your png");
		this.setIconImage(frameImage.getImage());
		//------------menubar --------------
		
		
		this.setResizable(false);
		this.setJMenuBar(menuBar);
		this.add(fontLabel);
		this.add(fontSizeSpinner);
		this.add(fontcolorButton);
		this.add(fontBox);
		this.add(scrollPane);
		this.setVisible(true);
		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==fontcolorButton) {
			colorChooser = new JColorChooser();
			
			Color color = colorChooser.showDialog(null, "Choose a Color", Color.black);
			
			textArea.setForeground(color);
		}
		if(e.getSource()==fontBox) {
			textArea.setFont(new Font((String) fontBox.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize()));
		}
		if(e.getSource()==openItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("C:\\Users\\TNC\\Desktop"));
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files","txt");
			fileChooser.setFileFilter(filter);
			
			int response = fileChooser.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				Scanner fileIn = null;
				
				try {
					fileIn = new Scanner(file);
					if(file.isFile()) {
						while(fileIn.hasNextLine()) {
							String line = fileIn.nextLine()+"\n";
							textArea.append(line);
						}
					}
				}catch (FileNotFoundException e1){
					e1.printStackTrace();
				}finally {
					fileIn.close();
				}
			}
		}
		if(e.getSource()==saveItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("C:\\Users\\TNC\\Desktop"));
			
			int response = fileChooser.showSaveDialog(null);
			if(response == JFileChooser.APPROVE_OPTION) {
				File file;
				PrintWriter fileOut = null;
				 
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				try {
					fileOut = new PrintWriter(file);
					fileOut.println(textArea.getText());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					fileOut.close();
				}
			}
		}
		if(e.getSource()==exitItem) {
			System.exit(0);
		}
		if(e.getSource()==textAreaBackgroundItem) {
			colorChooser = new JColorChooser();
			Color color = colorChooser.showDialog(null, "Choose a Color", Color.black);
			textArea.setBackground(color);
		}
		if(e.getSource()==backgroundItem) {
			colorChooser = new JColorChooser();
			Color color = colorChooser.showDialog(null, "Choose a Color", Color.black);
			this.getContentPane().setBackground(color);
		}
		
	}
	
}
