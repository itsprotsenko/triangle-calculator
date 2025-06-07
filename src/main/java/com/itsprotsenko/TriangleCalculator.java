package com.itsprotsenko;

import javax.swing.*;
import java.awt.*;

public class TriangleCalculator {
    private Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    private double scrnW = screensize.getWidth(), scrnH = screensize.getHeight();
    private int frameW =500, frameH = 350;

    Image icon = new ImageIcon("src/main/resources/icon.png").getImage();

    JFrame frame = new JFrame("Triangle Calculator");

    JLabel sideABLabel = new JLabel();
    JTextField sideABInput = new JTextField();
    JLabel sideBCLabel = new JLabel();
    JTextField sideBCInput = new JTextField();
    JLabel sideCALabel = new JLabel();
    JTextField sideCAInput = new JTextField();
    JLabel angleALabel = new JLabel();
    JTextField angleAInput = new JTextField();
    JLabel angleBLabel = new JLabel();
    JTextField angleBInput = new JTextField();
    JLabel angleCLabel = new JLabel();
    JTextField angleCInput = new JTextField();

    JButton calculateButton = new JButton("Calculate");

    public TriangleCalculator() {

        sideABLabel.setBounds(15, 15, 150 , 35);
        sideABLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        sideABLabel.setVisible(true);
        sideABLabel.setText("Length of Side AB:");
        frame.add(sideABLabel);

        sideABInput.setBounds(15, 55, 150, 35);
        sideABInput.setFont(new Font("Arial", Font.PLAIN, 15));
        sideABInput.setOpaque(true);
        sideABInput.setVisible(true);
        frame.add(sideABInput);

        sideBCLabel.setBounds(15, 100, 150 , 35);
        sideBCLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        sideBCLabel.setVisible(true);
        sideBCLabel.setText("Length of Side AB:");
        frame.add(sideBCLabel);

        sideBCInput.setBounds(15, 140, 150, 35);
        sideBCInput.setFont(new Font("Arial", Font.PLAIN, 15));
        sideBCInput.setOpaque(true);
        sideBCInput.setVisible(true);
        frame.add(sideBCInput);

        sideCALabel.setBounds(15, 200, 150 , 35);
        sideCALabel.setFont(new Font("Arial", Font.PLAIN, 15));
        sideCALabel.setVisible(true);
        sideCALabel.setText("Length of Side AB:");
        frame.add(sideCALabel);

        sideCAInput.setBounds(15, 240, 150, 35);
        sideCAInput.setFont(new Font("Arial", Font.PLAIN, 15));
        sideCAInput.setOpaque(true);
        sideCAInput.setVisible(true);
        frame.add(sideCAInput);

        angleALabel.setBounds(265, 15, 150 , 35);
        angleALabel.setFont(new Font("Arial", Font.PLAIN, 15));
        angleALabel.setVisible(true);
        angleALabel.setText("Measure of Angle A:");
        frame.add(angleALabel);

        angleAInput.setBounds(265, 55, 150, 35);
        angleAInput.setFont(new Font("Arial", Font.PLAIN, 15));
        angleAInput.setOpaque(true);
        angleAInput.setVisible(true);
        frame.add(angleAInput);

        angleBLabel.setBounds(265, 100, 150 , 35);
        angleBLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        angleBLabel.setVisible(true);
        angleBLabel.setText("Measure of Angle B:");
        frame.add(angleBLabel);

        angleBInput.setBounds(265, 140, 150, 35);
        angleBInput.setFont(new Font("Arial", Font.PLAIN, 15));
        angleBInput.setOpaque(true);
        angleBInput.setVisible(true);
        frame.add(angleBInput);

        angleCLabel.setBounds(265, 200, 150 , 35);
        angleCLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        angleCLabel.setVisible(true);
        angleCLabel.setText("Measure of Angle C:");
        frame.add(angleCLabel);

        angleCInput.setBounds(265, 240, 150, 35);
        angleCInput.setFont(new Font("Arial", Font.PLAIN, 15));
        angleCInput.setOpaque(true);
        angleCInput.setVisible(true);
        frame.add(angleCInput);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation((int)(scrnW/2 - frameW/2), (int)(scrnH/2 - frameH/2));
        frame.setIconImage(icon);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.white);
        frame.getContentPane().setPreferredSize(new Dimension(frameW, frameH));
        frame.pack();
    }
}
