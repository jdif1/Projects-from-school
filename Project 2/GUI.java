/*
CMSC 335
11/16/2021
This file contains the GUI for the program. When the program is ran, the first panel the user
will see shows 9 buttons, each of which corresponds to one of the shapes in this program.
After selecting a shape, a second panel is opened that prompts the user to enter parameters for
the shape. After correct parameters are entered, the user can hit the draw button which will show
them the area or volume of the shape as well asa Graphics2D drawing of the shape if it is
a two dimensional shape, and a representative image of the shape if it is a three dimensional
shape. The image files for the three dimensional shapes are in the resources/images folder.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GUI{
    String shapeChoice;
    double input1;
    double input2;

    public GUI(){

        JFrame frame = new JFrame();
        frame.setTitle("Shape Drawing Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 240);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 4, 4, 4));
        JButton circleButton = new JButton("Circle");
        circleButton.setPreferredSize(new Dimension(120, 120));
        JButton squareButton = new JButton("Square");
        squareButton.setPreferredSize(new Dimension(120, 120));
        JButton triangleButton = new JButton("Triangle");
        triangleButton.setPreferredSize(new Dimension(120, 120));
        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.setPreferredSize(new Dimension(120, 120));
        JButton sphereButton = new JButton("Sphere");
        sphereButton.setPreferredSize(new Dimension(120, 120));
        JButton cubeButton = new JButton("Cube");
        cubeButton.setPreferredSize(new Dimension(120, 120));
        JButton coneButton = new JButton("Cone");
        coneButton.setPreferredSize(new Dimension(120, 120));
        JButton cylinderButton = new JButton("Cylinder");
        cylinderButton.setPreferredSize(new Dimension(120, 120));
        JButton torusButton = new JButton("Torus");
        torusButton.setPreferredSize(new Dimension(120, 120));

        mainPanel.add(circleButton);
        mainPanel.add(squareButton);
        mainPanel.add(triangleButton);
        mainPanel.add(rectangleButton);
        mainPanel.add(sphereButton);
        mainPanel.add(cubeButton);
        mainPanel.add(coneButton);
        mainPanel.add(cylinderButton);
        mainPanel.add(torusButton);
        frame.add(mainPanel);
        frame.setVisible(true);

            circleButton.addActionListener(e -> {
                JFrame circleFrame = new JFrame();
                circleFrame.setTitle("Circle");

                circleFrame.setSize(250, 175);
                circleFrame.setResizable(false);

                JPanel circleConfigPanel = new JPanel();
                circleConfigPanel.setLayout(new FlowLayout());
                circleConfigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                JLabel radiusLabel = new JLabel("Radius:");
                circleConfigPanel.add(radiusLabel);
                JTextField circleRadiusTextField = new JTextField(20);
                circleConfigPanel.add(circleRadiusTextField);
                JButton circleDrawButton = new JButton("Draw Circle");
                JLabel circleAreaLabel = new JLabel("Area:");
                circleConfigPanel.add(circleAreaLabel);
                JTextField circleAreaTextField = new JTextField(20);
                circleAreaTextField.setEditable(false);
                circleAreaTextField.setBackground(UIManager.getColor("TextField.background"));
                circleConfigPanel.add(circleAreaTextField);
                circleConfigPanel.add(circleDrawButton);

                circleFrame.add(circleConfigPanel);
                circleFrame.setVisible(true);

                circleDrawButton.addActionListener(e1 -> {
                            shapeChoice = "circle";
                            try {
                                input1 = Double.parseDouble(circleRadiusTextField.getText());
                            }catch (NumberFormatException error){
                                JOptionPane.showMessageDialog(null, "Please make sure you are" +
                                        " only using numbers and no more than one decimal point.");
                                return;
                            }
                            Circle circle = new Circle(Double.parseDouble(circleRadiusTextField.getText()));
                            circleAreaTextField.setText(circle.toString());
                            JFrame circleDrawingFrame = new JFrame();
                            DrawingDisplayPanel circleDisplayPanel = new DrawingDisplayPanel();
                            circleDrawingFrame.setSize(400, 400);
                            circleDrawingFrame.add(circleDisplayPanel);
                            circleDrawingFrame.repaint();
                            circleDrawingFrame.setVisible(true);
                        }
                );
            });

            squareButton.addActionListener(e -> {
                JFrame squareFrame = new JFrame();
                squareFrame.setTitle("Square");
                squareFrame.setSize(250, 175);
                squareFrame.setResizable(false);

                JPanel squareConfigPanel = new JPanel();
                squareConfigPanel.setLayout(new FlowLayout());
                squareConfigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                JLabel sideLengthLabel = new JLabel("Side Length:");
                squareConfigPanel.add(sideLengthLabel);
                JTextField squareSideLengthTextField = new JTextField(20);
                squareConfigPanel.add(squareSideLengthTextField);
                JButton squareDrawButton = new JButton("Draw Square");
                JLabel squareAreaLabel = new JLabel("Area:");
                squareConfigPanel.add(squareAreaLabel);
                JTextField squareAreaTextField = new JTextField(20);
                squareAreaTextField.setEditable(false);
                squareAreaTextField.setBackground(UIManager.getColor("TextField.background"));
                squareConfigPanel.add(squareAreaTextField);
                squareConfigPanel.add(squareDrawButton);
                squareFrame.add(squareConfigPanel);
                squareFrame.setVisible(true);

                squareDrawButton.addActionListener(e1 -> {
                    shapeChoice = "square";
                    try{
                        input1 = Double.parseDouble(squareSideLengthTextField.getText());
                    }catch (NumberFormatException error){
                        JOptionPane.showMessageDialog(null, "Please make sure you are" +
                                " only using numbers and no more than one decimal point.");
                        return;
                    }
                    Square square = new Square(Double.parseDouble(squareSideLengthTextField.getText()));
                    squareAreaTextField.setText(square.toString());
                    JFrame squareDrawingFrame = new JFrame();
                    DrawingDisplayPanel squareDisplayPanel = new DrawingDisplayPanel();
                    squareDrawingFrame.setSize(400, 400);
                    squareDrawingFrame.add(squareDisplayPanel);
                    squareDrawingFrame.repaint();
                    squareDrawingFrame.setVisible(true);
                });
            });
            triangleButton.addActionListener(e -> {
                JFrame triangleFrame = new JFrame();
                triangleFrame.setTitle("Triangle");
                triangleFrame.setSize(250, 225);
                JPanel triangleConfigPanel = new JPanel();
                triangleConfigPanel.setLayout(new FlowLayout());
                triangleConfigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                JLabel triangleHeightLabel = new JLabel("Height:");
                triangleConfigPanel.add(triangleHeightLabel);
                JTextField triangleHeightTextField = new JTextField(20);
                triangleConfigPanel.add(triangleHeightTextField);
                JLabel triangleBaseLabel = new JLabel("Base");
                triangleConfigPanel.add(triangleBaseLabel);
                JTextField triangleBaseTextField = new JTextField(20);
                triangleConfigPanel.add(triangleBaseTextField);
                JButton triangleDrawButton = new JButton("Draw Triangle");
                JLabel triangleAreaLabel = new JLabel("Area:");
                triangleConfigPanel.add(triangleAreaLabel);
                JTextField triangleAreaTextField = new JTextField(20);
                triangleAreaTextField.setEditable(false);
                triangleAreaTextField.setBackground(UIManager.getColor("TextField.background"));
                triangleConfigPanel.add(triangleAreaTextField);
                triangleConfigPanel.add(triangleDrawButton);
                triangleFrame.add(triangleConfigPanel);
                triangleFrame.setVisible(true);

                triangleDrawButton.addActionListener(e1 -> {
                    shapeChoice = "triangle";
                    try{
                        input1 = Double.parseDouble(triangleBaseTextField.getText());
                        input2 = Double.parseDouble(triangleHeightTextField.getText());
                    }catch (NumberFormatException error){
                        JOptionPane.showMessageDialog(null, "Please make sure you are" +
                                " only using numbers and no more than one decimal point.");
                        return;
                    }
                    Triangle triangle = new Triangle(input1, input2);
                    triangleAreaTextField.setText(triangle.toString());
                    JFrame triangleDrawingFrame = new JFrame();
                    DrawingDisplayPanel triangleDisplayPanel = new DrawingDisplayPanel();
                    triangleDrawingFrame.setSize(400, 400);
                    triangleDrawingFrame.add(triangleDisplayPanel);
                    triangleDrawingFrame.repaint();
                    triangleDrawingFrame.setVisible(true);
                });
            });

            rectangleButton.addActionListener(e -> {
                JFrame rectangleFrame = new JFrame();
                rectangleFrame.setTitle("Rectangle");
                rectangleFrame.setSize(250, 225);
                JPanel rectangleConfigPanel = new JPanel();
                rectangleConfigPanel.setLayout(new FlowLayout());
                rectangleConfigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                JLabel sideLengthLabel1 = new JLabel("Side 1 Length:");
                rectangleConfigPanel.add(sideLengthLabel1);
                JTextField rectangleSideLength1TextField = new JTextField(20);
                rectangleConfigPanel.add(rectangleSideLength1TextField);
                JLabel sideLengthLabel2 = new JLabel("Side 2 Length:");
                rectangleConfigPanel.add(sideLengthLabel2);
                JTextField rectangleSideLength2TextField = new JTextField(20);
                rectangleConfigPanel.add(rectangleSideLength2TextField);
                JButton rectangleDrawButton = new JButton("Draw Rectangle");
                JLabel rectangleAreaLabel = new JLabel("Area:");
                rectangleConfigPanel.add(rectangleAreaLabel);
                JTextField rectangleAreaTextField = new JTextField(20);
                rectangleAreaTextField.setEditable(false);
                rectangleAreaTextField.setBackground(UIManager.getColor("TextField.background"));
                rectangleConfigPanel.add(rectangleAreaTextField);
                rectangleConfigPanel.add(rectangleDrawButton);
                rectangleFrame.add(rectangleConfigPanel);
                rectangleFrame.setVisible(true);

                rectangleDrawButton.addActionListener(e1 -> {
                    shapeChoice = "rectangle";
                    try{
                        input1 = Double.parseDouble(rectangleSideLength1TextField.getText());
                        input2 = Double.parseDouble(rectangleSideLength2TextField.getText());
                    }catch (NumberFormatException error){
                        JOptionPane.showMessageDialog(null, "Please make sure you are" +
                                " only using numbers and no more than one decimal point.");
                        return;
                    }
                    Rectangle rectangle = new Rectangle(input1, input2);
                    rectangleAreaTextField.setText(rectangle.toString());
                    JFrame rectangleDrawingFrame = new JFrame();
                    DrawingDisplayPanel rectangleDisplayPanel = new DrawingDisplayPanel();
                    rectangleDrawingFrame.setSize(400, 400);
                    rectangleDrawingFrame.add(rectangleDisplayPanel);
                    rectangleDrawingFrame.repaint();
                    rectangleDrawingFrame.setVisible(true);
                });
            });

            sphereButton.addActionListener(e -> {
                JFrame sphereFrame = new JFrame();
                sphereFrame.setTitle("Sphere");
                sphereFrame.setSize(250, 225);
                JPanel sphereConfigPanel = new JPanel();
                sphereConfigPanel.setLayout(new FlowLayout());
                sphereConfigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                JLabel radiusLabel = new JLabel("Radius:");
                sphereConfigPanel.add(radiusLabel);
                JTextField radiusTextField = new JTextField(20);
                sphereConfigPanel.add(radiusTextField);
                JButton sphereDrawButton = new JButton("Draw Sphere");
                JLabel sphereVolumeLabel = new JLabel("Volume:");
                sphereConfigPanel.add(sphereVolumeLabel);
                JTextField sphereVolumeTextField = new JTextField(20);
                sphereVolumeTextField.setEditable(false);
                sphereVolumeTextField.setBackground(UIManager.getColor("TextField.background"));
                sphereConfigPanel.add(sphereVolumeTextField);
                sphereConfigPanel.add(sphereDrawButton);
                sphereFrame.add(sphereConfigPanel);
                sphereFrame.setVisible(true);

                sphereDrawButton.addActionListener(e1 -> {
                    try {
                        BufferedImage img = ImageIO.read(getClass().getResource("/resources/images/sphere.png"));
                        ImageIcon icon = new ImageIcon(img);
                        JLabel label = new JLabel();
                        label.setIcon(icon);
                        try{
                        input1 = Double.parseDouble(radiusTextField.getText());
                        }catch (NumberFormatException error){
                            JOptionPane.showMessageDialog(null, "Please make sure you are" +
                                    " only using numbers and no more than one decimal point.");
                            return;
                        }
                        Sphere sphere = new Sphere(input1);
                        sphereVolumeTextField.setText(sphere.toString());
                        JFrame sphereDrawingFrame = new JFrame();
                        sphereDrawingFrame.setSize(img.getWidth(), img.getHeight());
                        sphereDrawingFrame.add(label);
                        sphereDrawingFrame.setVisible(true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            });

            cubeButton.addActionListener(e -> {
                JFrame cubeFrame = new JFrame();
                cubeFrame.setTitle("Cube");
                cubeFrame.setSize(250, 225);
                JPanel cubeConfigPanel = new JPanel();
                cubeConfigPanel.setLayout(new FlowLayout());
                cubeConfigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                JLabel cubeSideLengthLabel = new JLabel("Side Length:");
                cubeConfigPanel.add(cubeSideLengthLabel);
                JTextField cubeSideLengthTextField = new JTextField(20);
                cubeConfigPanel.add(cubeSideLengthTextField);
                JButton cubeDrawButton = new JButton("Draw Cube");
                JLabel cubeVolumeLabel = new JLabel("Volume:");
                cubeConfigPanel.add(cubeVolumeLabel);
                JTextField cubeVolumeTextField = new JTextField(20);
                cubeVolumeTextField.setEditable(false);
                cubeVolumeTextField.setBackground(UIManager.getColor("TextField.background"));
                cubeConfigPanel.add(cubeVolumeTextField);
                cubeConfigPanel.add(cubeDrawButton);
                cubeFrame.add(cubeConfigPanel);
                cubeFrame.setVisible(true);

                cubeDrawButton.addActionListener(e1 -> {
                    try {
                        BufferedImage img = ImageIO.read(getClass().getResource("/resources/images/cube.png"));
                        ImageIcon icon = new ImageIcon(img);
                        JLabel label = new JLabel();
                        label.setIcon(icon);
                        try{
                        input1 = Double.parseDouble(cubeSideLengthTextField.getText());
                        }catch (NumberFormatException error){
                            JOptionPane.showMessageDialog(null, "Please make sure you are" +
                                    " only using numbers and no more than one decimal point.");
                            return;
                        }
                        Cube cube = new Cube(input1);
                        cubeVolumeTextField.setText(cube.toString());
                        JFrame sphereDrawingFrame = new JFrame();
                        sphereDrawingFrame.setSize(img.getWidth(), img.getHeight());
                        sphereDrawingFrame.add(label);
                        sphereDrawingFrame.setVisible(true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            });

            coneButton.addActionListener(e -> {
                JFrame coneFrame = new JFrame();
                coneFrame.setTitle("Cone");
                coneFrame.setSize(250, 225);
                JPanel coneConfigPanel = new JPanel();
                coneConfigPanel.setLayout(new FlowLayout());
                coneConfigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                JLabel coneRadiusLabel = new JLabel("Radius:");
                coneConfigPanel.add(coneRadiusLabel);
                JTextField coneRadiusTextField = new JTextField(20);
                coneConfigPanel.add(coneRadiusTextField);
                JLabel coneHeightLabel = new JLabel("Height:");
                coneConfigPanel.add(coneHeightLabel);
                JTextField coneHeightTextField = new JTextField(20);
                coneConfigPanel.add(coneHeightTextField);
                JButton coneDrawButton = new JButton("Draw Cone");
                JLabel coneVolumeLabel = new JLabel("Volume:");
                coneConfigPanel.add(coneVolumeLabel);
                JTextField coneVolumeTextField = new JTextField(20);
                coneVolumeTextField.setEditable(false);
                coneVolumeTextField.setBackground(UIManager.getColor("TextField.background"));
                coneConfigPanel.add(coneVolumeTextField);
                coneConfigPanel.add(coneDrawButton);
                coneFrame.add(coneConfigPanel);
                coneFrame.setVisible(true);

                coneDrawButton.addActionListener(e1 -> {
                    try {
                        BufferedImage img = ImageIO.read(getClass().getResource("/resources/images/cone.png"));
                        ImageIcon icon = new ImageIcon(img);
                        JLabel label = new JLabel();
                        label.setIcon(icon);
                        try{
                        input1 = Double.parseDouble(coneRadiusTextField.getText());
                        input2 = Double.parseDouble(coneHeightTextField.getText());
                        }catch (NumberFormatException error){
                            JOptionPane.showMessageDialog(null, "Please make sure you are" +
                                    " only using numbers and no more than one decimal point.");
                            return;
                        }
                        Cone cone = new Cone(input1, input2);
                        coneVolumeTextField.setText(cone.toString());
                        JFrame coneDrawingFrame = new JFrame();
                        coneDrawingFrame.setSize(img.getWidth(), img.getHeight());
                        coneDrawingFrame.add(label);
                        coneDrawingFrame.setVisible(true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            });
            cylinderButton.addActionListener(e -> {
                JFrame cylinderFrame = new JFrame();
                cylinderFrame.setTitle("Cylinder");
                cylinderFrame.setSize(250, 225);
                JPanel cylinderConfigPanel = new JPanel();
                cylinderConfigPanel.setLayout(new FlowLayout());
                cylinderConfigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                JLabel cylinderRadiusLabel = new JLabel("Radius:");
                cylinderConfigPanel.add(cylinderRadiusLabel);
                JTextField cylinderRadiusTextField = new JTextField(20);
                cylinderConfigPanel.add(cylinderRadiusTextField);
                JLabel cylinderHeightLabel = new JLabel("Height:");
                cylinderConfigPanel.add(cylinderHeightLabel);
                JTextField cylinderHeightTextField = new JTextField(20);
                cylinderConfigPanel.add(cylinderHeightTextField);
                JButton cylinderDrawButton = new JButton("Draw Cylinder");
                JLabel cylinderVolumeLabel = new JLabel("Volume:");
                cylinderConfigPanel.add(cylinderVolumeLabel);
                JTextField cylinderVolumeTextField = new JTextField(20);
                cylinderVolumeTextField.setEditable(false);
                cylinderVolumeTextField.setBackground(UIManager.getColor("TextField.background"));
                cylinderConfigPanel.add(cylinderVolumeTextField);
                cylinderConfigPanel.add(cylinderDrawButton);
                cylinderFrame.add(cylinderConfigPanel);
                cylinderFrame.setVisible(true);

                cylinderDrawButton.addActionListener(e1 -> {
                    try {
                        BufferedImage img = ImageIO.read(getClass().getResource("/resources/images/cylinder.jpg"));
                        ImageIcon icon = new ImageIcon(img);
                        JLabel label = new JLabel();
                        label.setIcon(icon);
                        try {
                        input1 = Double.parseDouble(cylinderRadiusTextField.getText());
                        input2 = Double.parseDouble(cylinderHeightTextField.getText());
                        }catch (NumberFormatException error){
                            JOptionPane.showMessageDialog(null, "Please make sure you are" +
                                    " only using numbers and no more than one decimal point.");
                            return;
                        }
                        Cylinder cylinder = new Cylinder(input1, input2);
                        cylinderVolumeTextField.setText(cylinder.toString());
                        JFrame cylinderDrawingFrame = new JFrame();
                        cylinderDrawingFrame.setSize(img.getWidth(), img.getHeight());
                        cylinderDrawingFrame.add(label);
                        cylinderDrawingFrame.setVisible(true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            });
            torusButton.addActionListener(e -> {
                JFrame torusFrame = new JFrame();
                torusFrame.setTitle("Torus");
                torusFrame.setSize(250, 225);
                JPanel torusConfigPanel = new JPanel();
                torusConfigPanel.setLayout(new FlowLayout());
                torusConfigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                JLabel torusMajorRadiusLabel = new JLabel("Major Radius:");
                torusConfigPanel.add(torusMajorRadiusLabel);
                JTextField torusMajorRadiusTextField = new JTextField(20);
                torusConfigPanel.add(torusMajorRadiusTextField);
                JLabel torusMinorRadiusLabel = new JLabel("Minor Radius:");
                torusConfigPanel.add(torusMinorRadiusLabel);
                JTextField torusMinorRadiusTextField = new JTextField(20);
                torusConfigPanel.add(torusMinorRadiusTextField);
                JButton torusDrawButton = new JButton("Draw Torus");
                JLabel torusVolumeLabel = new JLabel("Volume:");
                torusConfigPanel.add(torusVolumeLabel);
                JTextField torusVolumeTextField = new JTextField(20);
                torusVolumeTextField.setEditable(false);
                torusVolumeTextField.setBackground(UIManager.getColor("TextField.background"));
                torusConfigPanel.add(torusVolumeTextField);
                torusConfigPanel.add(torusDrawButton);
                torusFrame.add(torusConfigPanel);
                torusFrame.setVisible(true);

                torusDrawButton.addActionListener(e1 -> {
                    try {
                        BufferedImage img = ImageIO.read(getClass().getResource("/resources/images/torus.png"));
                        ImageIcon icon = new ImageIcon(img);
                        JLabel label = new JLabel();
                        label.setIcon(icon);
                        try {
                        input1 = Double.parseDouble(torusMajorRadiusTextField.getText());
                        input2 = Double.parseDouble(torusMinorRadiusTextField.getText());
                        }catch (NumberFormatException error){
                            JOptionPane.showMessageDialog(null, "Please make sure you are" +
                                    " only using numbers and no more than one decimal point.");
                            return;
                        }
                        Torus torus = new Torus(input1, input2);
                        torusVolumeTextField.setText(torus.toString());
                        JFrame torusDrawingFrame = new JFrame();
                        torusDrawingFrame.setSize(img.getWidth(), img.getHeight());
                        torusDrawingFrame.add(label);
                        torusDrawingFrame.setVisible(true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            });
    }

    public class DrawingDisplayPanel extends JPanel{

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g;
            if (shapeChoice.equals("circle")) {
                g2D.draw(new Ellipse2D.Double(200 - (input1/2), 200 - (input1/2), input1, input1));
            }
            if (shapeChoice.equals("square")){
                g2D.draw(new Rectangle2D.Double(200 - (input1/2), 200 - (input1/2), input1, input1));
            }
            if (shapeChoice.equals("triangle")){
                g2D.draw(new Line2D.Double(200, 200, 200 + input1, 200));
                g2D.draw(new Line2D.Double(200, 200, (200 + input1/2), input2));
                g2D.draw(new Line2D.Double(200 + input1, 200, (200 + input1/2), input2));
            }
            if (shapeChoice.equals("rectangle")){
                g2D.draw(new Rectangle2D.Double(200-  (input1/2), 200 - (input1/2), input1, input2));
            }
        }
    }
}
