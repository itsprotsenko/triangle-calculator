package com.itsprotsenko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TriangleCalculator {
    public final static int decimalPlaces = 5;

    Calculate calculate;

    private Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    private double scrnW = screensize.getWidth(), scrnH = screensize.getHeight();
    private int frameW =500, frameH = 365;

    Image icon = new ImageIcon("src/main/resources/icon.png").getImage();

    JFrame frame = new JFrame("Triangle Calculator");

    List<JLabel> sideLabels = new ArrayList<>();
    List<JLabel> angleLabels = new ArrayList<>();
    List<JTextField> sideInputs = new ArrayList<>();
    List<JTextField> angleInputs = new ArrayList<>();

    JButton calculateButton = new JButton("Calculate");
    JLabel errorLabel = new JLabel();
    JLabel successLabel = new JLabel();

    Color colorError = new Color(252, 29, 29);
//    Color colorWarning = new Color(249, 124, 0);
    Color colorSuccess = new Color(15, 220, 50);

    public TriangleCalculator() {
        calculate = new Calculate();

        int x, y;

        for(String s : new String[]{"AB", "BC", "CA"}) {
            sideLabels.add(new JLabel("Length of Side " + s + ":"));
            sideInputs.add(new JTextField());
        }
        for(String s : new String[]{"A", "B", "C"}) {
            angleLabels.add(new JLabel("Length of Angle " + s + ":"));
            angleInputs.add(new JTextField());
        }

        x = 15;
        y = 15;

        for(int i = 0; i < sideLabels.size(); i++) {
            JLabel sideLabel = sideLabels.get(i);
            JTextField sideInput = sideInputs.get(i);

            sideLabel.setBounds(x, y, 150 , 35);
            sideLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            sideLabel.setVisible(true);
            frame.add(sideLabel);

            y += 40;

            sideInput.setBounds(x, y, 150, 35);
            sideInput.setFont(new Font("Arial", Font.PLAIN, 15));
            sideInput.setOpaque(true);
            sideInput.setVisible(true);
            frame.add(sideInput);

            y += 40;
        }

        x = 265;
        y = 15;

        for(int i = 0; i < angleLabels.size(); i++) {
            JLabel sideLabel = angleLabels.get(i);
            JTextField sideInput = angleInputs.get(i);

            sideLabel.setBounds(x, y, 150 , 35);
            sideLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            sideLabel.setVisible(true);
            frame.add(sideLabel);

            y += 40;

            sideInput.setBounds(x, y, 150, 35);
            sideInput.setFont(new Font("Arial", Font.PLAIN, 15));
            sideInput.setOpaque(true);
            sideInput.setVisible(true);
            frame.add(sideInput);

            y += 40;
        }
        calculateButton.setBounds(15, 260, 150, 35);
        calculateButton.setFont(new Font("Arial", Font.PLAIN, 15));
        calculateButton.setVisible(true);
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double[] sides = new double[3];
                double[] angles = new double[3];
                int emptySides = 0, emptyAngles = 0;

                for(int i = 0; i < sideInputs.size(); i++) {
                    sideInputs.get(i).setBackground(Color.white);
                    angleInputs.get(i).setBackground(Color.white);
                }

                errorLabel.setVisible(false);
                successLabel.setVisible(false);

                for(int i = 0; i < sideInputs.size(); i++) {
                    if(sideInputs.get(i).getText().isEmpty()) {
                        emptySides++;
                    } else {
                        try {
                            sides[i] = calculate.roundDouble(Double.parseDouble(sideInputs.get(i).getText()), decimalPlaces);
                            if(sides[i] <= 0) {
                                sideInputs.get(i).setBackground(colorError);
                                errorLabel.setVisible(true);
                                errorLabel.setText("Error: invalid input. Must be greater than 0");
                                break;
                            }
                        } catch (NumberFormatException ex) {
                            sideInputs.get(i).setBackground(colorError);
                            errorLabel.setVisible(true);
                            errorLabel.setText("Error: invalid input. Not a number");
                        }
                    }

                    if(angleInputs.get(i).getText().isEmpty()) {
                        emptyAngles++;
                    } else {
                        try {
                            angles[i] = calculate.roundDouble(Double.parseDouble(angleInputs.get(i).getText()), decimalPlaces);
                            if(angles[i] <= 0 || angles[i] >= 180) {
                                angleInputs.get(i).setBackground(colorError);
                                errorLabel.setVisible(true);
                                errorLabel.setText("Error: invalid input. Must be greater than 0 and less than 180");
                                break;
                            }
                        } catch (NumberFormatException ex) {
                            angleInputs.get(i).setBackground(colorError);
                            errorLabel.setVisible(true);
                            errorLabel.setText("Error: invalid input. Not a number");
                        }
                    }
                }

                if(!errorLabel.isVisible()) {
                    if(emptySides == 3) {
                        for(JTextField tf : sideInputs) {
                            tf.setBackground(colorError);
                        }
                        errorLabel.setText("Error: At least one side length is required");
                        errorLabel.setVisible(true);
                    }
                    else if(emptySides + emptyAngles > 3) {
                        for (JTextField tf : sideInputs) {
                            if (tf.getText().isEmpty()) {
                                tf.setBackground(colorError);
                            }
                        }
                        for (JTextField tf : angleInputs) {
                            if (tf.getText().isEmpty()) {
                                tf.setBackground(colorError);
                            }
                        }
                        errorLabel.setText("Error: At least three inputs are required");
                        errorLabel.setVisible(true);
                    }
                    else if(emptySides + emptyAngles < 3) {
                            for(JTextField tf : sideInputs) {
                                if(!tf.getText().isEmpty()) {
                                    tf.setBackground(colorError);
                                }
                            }
                            for(JTextField tf : angleInputs) {
                                if(!tf.getText().isEmpty()) {
                                    tf.setBackground(colorError);
                                }
                            }
                            errorLabel.setText("Error: Maximum number of inputs allowed is 3");
                            errorLabel.setVisible(true);
                    } else {
                        try {
                            calculate.calculateTriangle(sides, angles);

                            for(int i = 0; i < angleInputs.size(); i++) {
                                if(sideInputs.get(i).getText().isEmpty()) {
                                    sideInputs.get(i).setBackground(Color.green);
                                }
                                if(angleInputs.get(i).getText().isEmpty()) {
                                    angleInputs.get(i).setBackground(Color.green);
                                }
                                angleInputs.get(i).setText(angles[i] + "");
                                sideInputs.get(i).setText(sides[i] + "");
                            }
                            successLabel.setVisible(true);
                            successLabel.setText("Triangle calculated");

                        } catch (Exception err) {
                            for(JTextField tf : sideInputs) {
                                if(!tf.getText().isEmpty()) {
                                    tf.setBackground(colorError);
                                }
                            }
                            for(JTextField tf : angleInputs) {
                                if(!tf.getText().isEmpty()) {
                                    tf.setBackground(colorError);
                                }
                            }
                            errorLabel.setText("Error: Triangle not possible with given values");
                            errorLabel.setVisible(true);
                        }
                    }
                }
            }
        });
        frame.add(calculateButton);

        errorLabel.setBounds(15, 305, 400, 20);
        errorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        errorLabel.setForeground(colorError);
        errorLabel.setVisible(false);
        frame.add(errorLabel);

        successLabel.setBounds(15, 330, 400, 20);
        successLabel.setFont(new Font("Arial", Font.BOLD, 12));
        successLabel.setForeground(colorSuccess);
        successLabel.setVisible(false);
        frame.add(successLabel);

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
