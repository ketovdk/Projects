import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;

public class Main implements ActionListener {

	boolean fileChosen=false;
	File file = new File("");
	public Main(){
		JFrame frame = new JFrame("Text edit");
		frame.setSize(800,600);
		frame.setVisible(true);
		
		
		JTextArea area = new JTextArea();
		area.setLocation(10, 100);
		area.setSize(790,530);
		area.setEditable(true);
		area.setLineWrap(true);
		
		///////////////////////////
		JButton buttonOpen = new JButton("Open");
		buttonOpen.setLocation(10,10);
		buttonOpen.setSize(100,50);
		frame.add(buttonOpen);
		buttonOpen.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(frame);
				file=chooser.getSelectedFile();
				try(
					FileReader reader = new FileReader(file)){
					char[] buf = new char[(int) file.length()];
					reader.read(buf);
					area.setText(new String(buf));
					fileChosen=true;
					
				}catch(Exception e2){
					
				}
			}
		});
		
		JButton buttonSave = new JButton("Save");
		buttonSave.setLocation(120,10);
		buttonSave.setSize(100,50);
		frame.add(buttonSave);
		buttonSave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
			if(fileChosen){
				try(FileWriter writer = new FileWriter(file)){
					writer.write(area.getText());
					
					writer.flush();
					JOptionPane.showMessageDialog(frame, "File saved");
				}catch (Exception e){
					
				}}
		JOptionPane.showMessageDialog(frame, "File isn't chosen");
			}
		});
		
		JButton buttonNew = new JButton("New File");
		buttonNew.setLocation(230,10);
		buttonNew.setSize(100,50);
		frame.add(buttonNew);
		buttonNew.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				JFileChooser chooser = new JFileChooser();
				if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
					JOptionPane.showMessageDialog(frame, "File created");
					file = chooser.getSelectedFile();
					try(FileWriter writer = new FileWriter(file)){
						writer.write("");;
						writer.flush();
						fileChosen=true;
					}catch (Exception e1){
						
					}
				}
			}
		});

		JButton buttonSaveAs = new JButton("Save as");
		buttonSaveAs.setLocation(340,10);
		buttonSaveAs.setSize(100,50);
		frame.add(buttonSaveAs);
		buttonSaveAs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				JFileChooser chooser = new JFileChooser();
				if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
				JOptionPane.showMessageDialog(frame, "File saved");
					file = chooser.getSelectedFile();
					try(FileWriter writer = new FileWriter(file)){
					writer.write(area.getText());
					writer.flush();
					
				}catch (Exception e){
					
				}}
			}
		});
		
		JButton buttonIncreaseFont = new JButton("+");
		buttonIncreaseFont.setLocation(500,10);
		buttonIncreaseFont.setSize(50,50);
		frame.add(buttonIncreaseFont);
		buttonIncreaseFont.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Font f = area.getFont();
				f=new Font(f.getFontName(), f.getStyle(), f.getSize()+2);
				area.setFont(f);
			}
			
		});

		JButton buttonDecreaseFont = new JButton("-");
		buttonDecreaseFont.setLocation(450,10);
		buttonDecreaseFont.setSize(50,50);
		frame.add(buttonDecreaseFont);
		buttonDecreaseFont.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Font f = area.getFont();
				f=new Font(f.getFontName(), f.getStyle(), f.getSize()-2);
				area.setFont(f);
			}
			
		});
		
		
		JButton buttonReplace = new JButton("replace");
		buttonReplace.setLocation(560,10);
		buttonReplace.setSize(100,50);
		frame.add(buttonReplace);
		buttonReplace.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String from = (String) JOptionPane.showInputDialog(frame, "From:", "Find replacing", JOptionPane.PLAIN_MESSAGE, null, null,"");
				if((from!=null)&&(from.length()>0)){
					String to = (String)JOptionPane.showInputDialog(frame, "To:", "Replace replacing", JOptionPane.PLAIN_MESSAGE, null, null,"");
						if(to!=null) area.setText(area.getText().replace(from, to));
				}
			}
		});
		
		
		
/*
		JButton buttonAlligment = new JButton("alligment");
		buttonAlligment.setLocation(670,10);
		buttonAlligment.setSize(100,50);
		frame.add(buttonAlligment);
		buttonAlligment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(area.getAlignmentX()==0)
				{
					area.setAlignmentX( (float)0.5);
				}
				else
					if(area.getAlignmentX()==0.5){
						area.setAlignmentX(1);
					}
					else
						area.setAlignmentX(0);
			}

			
		});*/
		///////////////////////////
	
	
		frame.add(area);

		frame.add(new JLabel());

		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new Main();
				}
			
		});
	}
	
}
