package com.dankrushen.mojangservicechecker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

public class MojangServiceChecker{

	private JFrame frmMinecraftServiceChecker;

	/**
	 * Launch the application.
	 */
	double refreshInt;
	String timeSetting;
	String settings;
	JLabel status = new JLabel();
	boolean forever = true;
	boolean timerstarted = false;
	double timeleft;
	JLabel ttreload;
	String displaything2;
	
	String ResponseName;
	String v1;
	String v2;
	String v3;
	String v4;
	String v5;
	String v6;
	String v7;
	String v8;
	String v9;
	public String[] mojangStatus(){
		try {
			v1 = getStatus("\"minecraft.net\"");
		} catch (IOException e) {
			if(v1 == null){
				v1 = "<font color = \"FF0000\">Unknown/No Response.</font>";
				System.out.println("Can't connect to website!");
			}
			//else e.printStackTrace();
		}
		try {
			v2 = getStatus("\"session.minecraft.net\"");
		} catch (IOException e) {
			if(v2 == null){
				v2 = "<font color = \"FF0000\">Unknown/No Response.</font>";
				System.out.println("Can't connect to website!");
			}
			//else e.printStackTrace();
		}
		try {
			v3 = getStatus("\"account.mojang.com\"");
		} catch (IOException e) {
			if(v3 == null){
				v3 = "<font color = \"FF0000\">Unknown/No Response.</font>";
				System.out.println("Can't connect to website!");
			}
			//else e.printStackTrace();
		}
		try {
			v4 = getStatus("\"auth.mojang.com\"");
		} catch (IOException e) {
			if(v4 == null){
				v4 = "<font color = \"FF0000\">Unknown/No Response.</font>";
				System.out.println("Can't connect to website!");
			}
			//else e.printStackTrace();
		}
		try {
			v5 = getStatus("\"skins.minecraft.net\"");
		} catch (IOException e) {
			if(v5 == null){
				v5 = "<font color = \"FF0000\">Unknown/No Response.</font>";
				System.out.println("Can't connect to website!");
			}
			//else e.printStackTrace();
		}
		try {
			v6 = getStatus("\"authserver.mojang.com\"");
		} catch (IOException e) {
			if(v6 == null){
				v6 = "<font color = \"FF0000\">Unknown/No Response.</font>";
				System.out.println("Can't connect to website!");
			}
			//else e.printStackTrace();
		}
		try {
			v7 = getStatus("\"sessionserver.mojang.com\"");
		} catch (IOException e) {
			if(v7 == null){
				v7 = "<font color = \"FF0000\">Unknown/No Response.</font>";
				System.out.println("Can't connect to website!");
			}
			//else e.printStackTrace();
		}
		try {
			v8 = getStatus("\"api.mojang.com\"");
		} catch (IOException e) {
			if(v8 == null){
				v8 = "<font color = \"FF0000\">Unknown/No Response.</font>";
				System.out.println("Can't connect to website!");
			}
			//else e.printStackTrace();
		}
		try {
			v9 = getStatus("\"textures.minecraft.net\"");
		} catch (IOException e) {
			if(v9 == null){
				v9 = "<font color = \"FF0000\">Unknown/No Response.</font>";
				System.out.println("Can't connect to website!");
			}
			//else e.printStackTrace();
		}
		String[] all = new String[(9)];
		all[(0)] = v1;
		all[(1)] = v2;
		all[(2)] = v3;
		all[(3)] = v4;
		all[(4)] = v5;
		all[(5)] = v6;
		all[(6)] = v7;
		all[(7)] = v8;
		all[(8)] = v9;
		return all;
	}
	public String getStatus(String LookupName) throws IOException {
		URL url = new URL("http://status.mojang.com/check");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
						url.openStream()));
		ResponseName = in.readLine();
		in.close();
		if(ResponseName.contains(LookupName + ":\"green\"")) {
			return "<font color = \"008000\">This service is healthy.</font>";
		}
		else if(ResponseName.contains(LookupName + ":\"yellow\"")) {
			return "<font color = \"FFD700\">This service is shaky.</font>";
		}
		else if(ResponseName.contains(LookupName + ":\"red\"")) {
			return "<font color = \"FF0000\">This service is offline.</font>";
		}
		return "Unknown/No Response.";
	}
	
	public String refreshStatus(){
		String[] list = mojangStatus();
		String display = "<html>minecraft.net: " + list[(0)] + "<br/>session.minecraft.net: " + list[(1)] + "<br/>account.mojang.com: " + list[(2)] + "<br/>auth.mojang.com: " + list[(3)] + "<br/>skins.minecraft.net: " + list[(4)] + "<br/>authserver.mojang.com: " + list[(5)] + "<br/>sessionserver.mojang.com: " + list[(6)] + "<br/>api.mojang.com: " + list[(7)] + "<br/>textures.minecraft.net: " + list[(8)] + "</html>";
		System.out.println("" + display);
		return display;
	}
    
	public void startTimer() {
		Thread timer = new Thread(new Runnable(){
		public void run(){
			while(forever){
				System.out.println("Checking Status");
				status.setText("<html>minecraft.net: <font color = \"00FFFF\">Checking...</font><br/>session.minecraft.net: <font color = \"00FFFF\">Checking...</font><br/>account.mojang.com: <font color = \"00FFFF\">Checking...</font><br/>auth.mojang.com: <font color = \"00FFFF\">Checking...</font><br/>skins.minecraft.net: <font color = \"00FFFF\">Checking...</font><br/>authserver.mojang.com: <font color = \"00FFFF\">Checking...</font><br/>sessionserver.mojang.com: <font color = \"00FFFF\">Checking...</font><br/>api.mojang.com: <font color = \"00FFFF\">Checking...</font><br/>textures.minecraft.net: <font color = \"00FFFF\">Checking...</font></html>");
				status.setText(refreshStatus());
				long settings = readSettings();
				timeleft = settings*1000;
				for(int amount = 0;amount <= settings*1000;amount = amount+100){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(timeleft != 0){
						timeleft = timeleft-100;
					}
					else break;
					ttreload.setText("Time Untill Reload: " + timeleft/1000 + "s");
				}
			}
		}});
		timer.start();
    }
    
	public long readSettings(){
		long Number;
		File file = new File("MSC-Settings.txt");
		if(!file.exists()){
			System.out.println("Creating file...");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(!file.exists()){
				System.out.println("Failed to create file.");
			}
			else{
				System.out.println("Created file successfully!");
				System.out.println("Trying to write to file...\n");
				try {
					PrintWriter out;
					out = new PrintWriter("MSC-Settings.txt");
					out.println("10");
					out.close();
					System.out.println("Written to settings file successfully.");
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
					System.out.println("Giving up writing to file.");
				}
			}
		}
		try(BufferedReader br = new BufferedReader(new FileReader("MSC-Settings.txt"))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    everything = everything.replace("\uFEFF", "");
		    double everything2 = Double.parseDouble(everything);
		    Number = (int) everything2;
			return Number;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 10;
	}
	
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MojangServiceChecker window = new MojangServiceChecker();
					window.frmMinecraftServiceChecker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MojangServiceChecker() {
		initialize();
	}
	private void screen2() throws FileNotFoundException, IOException {
		frmMinecraftServiceChecker = new JFrame();
		frmMinecraftServiceChecker.setTitle("Settings");
		frmMinecraftServiceChecker.setResizable(false);
		frmMinecraftServiceChecker.setBounds(100, 100, 450, 150);
		frmMinecraftServiceChecker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMinecraftServiceChecker.getContentPane().setLayout(null);
		frmMinecraftServiceChecker.setVisible (true);
		
		final JLabel lblNewLabel = new JLabel("Current Setting: " + "10" + " Seconds");
		lblNewLabel.setBounds(14, 42, 173, 14);
		frmMinecraftServiceChecker.getContentPane().add(lblNewLabel);
		
		final JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				refreshInt = slider.getValue();
				if (refreshInt == 1){
					timeSetting = " Second";
				}
				else timeSetting = " Seconds";
				lblNewLabel.setText("Current Setting: " + refreshInt + timeSetting);
			}
		});
		slider.setMinimum(1);
		slider.setMaximum(120);
		slider.setBounds(10, 11, 241, 20);
		frmMinecraftServiceChecker.getContentPane().add(slider);
		slider.setValue(Integer.parseInt(String.valueOf(readSettings())));
		
		JLabel lblRefreshInterval = new JLabel("Refresh Interval in Seconds");
		lblRefreshInterval.setBounds(261, 11, 173, 20);
		frmMinecraftServiceChecker.getContentPane().add(lblRefreshInterval);
		
		JButton btnClose = new JButton("Cancel");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMinecraftServiceChecker.setVisible (false);
				timeleft = 0;
				initialize();
			}
		});
		btnClose.setBounds(180, 70, 89, 23);
		frmMinecraftServiceChecker.getContentPane().add(btnClose);
		frmMinecraftServiceChecker.setVisible (true);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PrintWriter out;
					System.out.println("Writing to settings file.");
					out = new PrintWriter("MSC-Settings.txt");
					out.println(refreshInt);
					out.close();
					System.out.println("Written to settings file successfully.");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.out.println("Creating file...");
					File file = new File("MSC-Settings.txt");
					try {
						file.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(!file.exists()){
						System.out.println("Failed to create file.");
					}
					else{
						System.out.println("Created file successfully!");
						System.out.println("Trying to write to file again...");
						try {
							PrintWriter out;
							out = new PrintWriter("MSC-Settings.txt");
							out.println(refreshInt);
							out.close();
							System.out.println("Written to settings file successfully.");
						} catch (FileNotFoundException e2) {
							e2.printStackTrace();
							System.out.println("Giving up writing to file.");
						}
					}
				}
				frmMinecraftServiceChecker.setVisible (false);
				timeleft = 0;
				initialize();
			}
		});
		btnApply.setBounds(268, 70, 89, 23);
		frmMinecraftServiceChecker.getContentPane().add(btnApply);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				slider.setValue(10);
			}
		});
		btnReset.setBounds(92, 70, 89, 23);
		frmMinecraftServiceChecker.getContentPane().add(btnReset);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void setNames(String namestring){
	}
	private void initialize() {
		frmMinecraftServiceChecker = new JFrame();
		frmMinecraftServiceChecker.setTitle("Minecraft Service Checker");
		frmMinecraftServiceChecker.setBounds(100, 100, 450, 258);
		frmMinecraftServiceChecker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMinecraftServiceChecker.getContentPane().setLayout(null);
		frmMinecraftServiceChecker.setResizable(false);
		frmMinecraftServiceChecker.setVisible (true);
		
		ttreload = new JLabel("Time Untill Reload: " + "Loading...");
		ttreload.setHorizontalAlignment(SwingConstants.CENTER);
		ttreload.setBounds(10, 174, 424, 14);
		frmMinecraftServiceChecker.getContentPane().add(ttreload);
		
		status.setHorizontalAlignment(SwingConstants.LEFT);
		status.setText("<html>minecraft.net: <font color = \"00FFFF\">Checking...</font><br/>session.minecraft.net: <font color = \"00FFFF\">Checking...</font><br/>account.mojang.com: <font color = \"00FFFF\">Checking...</font><br/>auth.mojang.com: <font color = \"00FFFF\">Checking...</font><br/>skins.minecraft.net: <font color = \"00FFFF\">Checking...</font><br/>authserver.mojang.com: <font color = \"00FFFF\">Checking...</font><br/>sessionserver.mojang.com: <font color = \"00FFFF\">Checking...</font><br/>api.mojang.com: <font color = \"00FFFF\">Checking...</font><br/>textures.minecraft.net: <font color = \"00FFFF\">Checking...</font></html>");
		status.setBounds(10, 0, 424, 168);
		frmMinecraftServiceChecker.getContentPane().add(status);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 187, 424, 38);
		frmMinecraftServiceChecker.getContentPane().add(panel);
		JButton btnNewButton = new JButton("Settings");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMinecraftServiceChecker.setVisible (false);
				try {
					screen2();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		if(timerstarted == false){
			timerstarted = true;
			startTimer();
		}
		else{
			System.out.println("Timer already started");
		}
	}
}
