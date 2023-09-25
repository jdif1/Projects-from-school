/*
CMSC 330
This class contains the methods needed to read in the text file and then translate it into a java GUI.
It contains two methods to assist in checking that the information is formatted correctly.
If there are any issues with the text file a SyntaxError is thrown.
 */

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUIGenerator {
    private static ArrayList<String[]> lineList = new ArrayList();
    private static ArrayList componentList = new ArrayList();
    private static final Pattern PARENTHESES_MATCHER = Pattern.compile("\\((.*?)\\)");
    private Node<Integer> frameHierarchy;
    private Node currentNode;
    private ButtonGroup group;
    private String line = "";

    protected File GetFile(){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.showOpenDialog(null);
        return jFileChooser.getSelectedFile();
    }
    protected void ReadInFile(Scanner scanner2) throws FileNotFoundException {
        if (!scanner2.hasNext()) {
            scanner2.close();
            FrameBuilder(0);
            return;
        } else {
            line += scanner2.next() + " ";
            if (line.contains(";") || line.contains(":") || line.contains(".")) {
                //separating out tokens to simplify reading lines
                line = line.replace("(", " (");
                line = line.replace(")", ") ");
                line = line.replace(";", " ;");
                line = line.replace(";", " ;");
                line = line.replace(":", " :");
                line = line.replaceAll("\\s+", " ");
                lineList.add(line.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)"));
                line = "";
            }
            ReadInFile(scanner2);
        }
    }

    protected void FrameBuilder(int position){

        try {
            if (! lineList.get(0)[0].equals("Window")){

                throw new SyntaxError("The first line of the GUI code must be for a window");
            }
            int currentPosition = position;
            if (currentPosition < lineList.size()){
                lineBreakChecker(lineList.get(currentPosition), currentPosition);
            }
            if (currentPosition < lineList.size()) {

                switch (lineList.get(currentPosition)[0]) {
                    case "Window":
                        String windowDimensions;
                        String[] windowSizeString = new String[2];
                        int[] windowSize = new int[2]; //storing x and y values for window size
                        componentList.add(new JFrame());

                        if (frameHierarchy == null) {
                            frameHierarchy = new Node<>(currentPosition, null);
                            currentNode = frameHierarchy;
                            // if the first line is not a JFrame throw an error
                        } else {
                            currentNode.addChild(new Node(componentList.size() - 1));
                            currentNode = (Node) currentNode.getChildren().get(currentNode.getChildren().size() - 1);
                        }

                        ((JFrame) componentList.get(currentPosition)).setTitle(lineList.get(currentPosition)[1]);
                        windowDimensions = lineList.get(currentPosition)[2].concat(" ").concat(lineList.get(currentPosition)[3]);
                        Matcher matcher = PARENTHESES_MATCHER.matcher(windowDimensions);

                        if (matcher.matches()) {
                            windowDimensions = windowDimensions.replace("(", "");
                            windowDimensions = windowDimensions.replace(")", "");
                            windowSizeString = windowDimensions.split(",");

                            for (int i = 0; i < windowSizeString.length; i++) {
                                windowSizeString[i] = windowSizeString[i].trim();
                                windowSize[i] = Integer.parseInt(windowSizeString[i]);
                            }

                            ((JFrame) componentList.get(currentPosition)).setSize(windowSize[0], windowSize[1]);

                        } else {
                            throw new SyntaxError("Window dimension parameters are invalid.\n Line " +
                                    currentPosition + " \"" + errorReportBuilder(currentPosition)+"\"");
                        }

                        try {
                            if (lineList.get(currentPosition)[4].equals("Layout")) {

                                switch (lineList.get(currentPosition)[5]) {
                                    case "Flow":
                                        ((JFrame) componentList.get((int) currentNode.getData())).setLayout(new FlowLayout());
                                        break;

                                    case "Grid":
                                        int[] gridDimensions = new int[4];
                                        String[] gridDimensionsStringArray = new String[4];
                                        try {
                                            String gridDimensionsString = lineList.get(currentPosition)[6].concat(
                                                    lineList.get(currentPosition)[7].concat(lineList.get(currentPosition)[8]).concat(
                                                            lineList.get(currentPosition)[9]));
                                            matcher = PARENTHESES_MATCHER.matcher(gridDimensionsString);

                                            if (matcher.matches()) {
                                                gridDimensionsString = gridDimensionsString.replace("(", "");
                                                gridDimensionsString = gridDimensionsString.replace(")", "");
                                                gridDimensionsStringArray = gridDimensionsString.split(",");
                                                for (int i = 0; i < gridDimensionsStringArray.length; i++) {
                                                    gridDimensions[i] = Integer.parseInt(gridDimensionsStringArray[i]);
                                                }

                                            } else {
                                                throw new SyntaxError("Issue with grid dimensions.\n Line " +
                                                        currentPosition + " \"" + errorReportBuilder(currentPosition) +"\"");
                                            }

                                        } catch (NullPointerException e) {
                                            try {
                                                String gridDimensionsString = lineList.get(currentPosition)[6].concat(
                                                        lineList.get(currentPosition)[7]);
                                                matcher = PARENTHESES_MATCHER.matcher(gridDimensionsString);

                                                if (matcher.matches()) {
                                                    gridDimensionsString = gridDimensionsString.replace("(", "");
                                                    gridDimensionsString = gridDimensionsString.replace(")", "");
                                                    gridDimensionsStringArray = gridDimensionsString.split(",");

                                                    for (int i = 0; i < gridDimensionsStringArray.length; i++) {
                                                        gridDimensions[i] = Integer.parseInt(gridDimensionsStringArray[i]);
                                                    }
                                                    ((JFrame) componentList.get(currentPosition)).setLayout(
                                                            new GridLayout(gridDimensions[0], gridDimensions[1]));

                                                } else {
                                                    throw new SyntaxError("Issue with grid dimensions.\n Line " +
                                                            currentPosition + " \"" + errorReportBuilder(currentPosition) +"\"");
                                                }

                                            } catch (NullPointerException ee) {
                                                ((JFrame) componentList.get(currentPosition)).setLayout(new GridLayout());
                                            }
                                        }
                                        break;

                                    case "Spring":
                                        ((JFrame) componentList.get(currentPosition)).setLayout(new SpringLayout());
                                        break;

                                    default:
                                        throw new SyntaxError("Invalid layout choice.\n Line " +
                                                currentPosition + " \"" + errorReportBuilder(currentPosition)+ "\"");
                                }
                            }

                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No layout selected.");
                        }
                        break;

                    case "Panel":
                        componentList.add(new JPanel());

                        if (frameHierarchy == null) {
                            frameHierarchy = new Node<>(currentPosition, null);
                            currentNode = frameHierarchy;

                        } else {
                            currentNode.addChild(new Node(componentList.size() - 1));
                            currentNode = (Node) currentNode.getChildren().get(currentNode.getChildren().size() - 1);
                        }
                        try {

                            if (lineList.get(currentPosition)[1].equals("Layout")) {
                                switch (lineList.get(currentPosition)[2]) {
                                    case "Flow":
                                        ((JPanel) componentList.get(currentPosition)).setLayout(new FlowLayout());
                                        break;

                                    case "Grid":
                                        int[] gridDimensions = new int[4];
                                        String[] gridDimensionsStringArray;
                                        try {
                                            String gridDimensionsString = lineList.get(currentPosition)[3].concat(
                                                    lineList.get(currentPosition)[4].concat(lineList.get(currentPosition)[5]).concat(
                                                            lineList.get(currentPosition)[6]));
                                            matcher = PARENTHESES_MATCHER.matcher(gridDimensionsString);

                                            if (matcher.matches()) {
                                                gridDimensionsString = gridDimensionsString.replace("(", "");
                                                gridDimensionsString = gridDimensionsString.replace(")", "");
                                                gridDimensionsStringArray = gridDimensionsString.split(",");

                                                for (int i = 0; i < gridDimensionsStringArray.length; i++) {
                                                    gridDimensions[i] = Integer.parseInt(gridDimensionsStringArray[i]);
                                                }

                                                ((JPanel) componentList.get((Integer) currentNode.getData())).setLayout(
                                                        new GridLayout(gridDimensions[0], gridDimensions[1], gridDimensions[2],
                                                                gridDimensions[3]));

                                            } else {
                                                throw new SyntaxError("Issue with grid dimensions.\n Line " +
                                                        currentPosition + " \"" + errorReportBuilder(currentPosition) +"\"");
                                            }
                                        } catch (NullPointerException e) {
                                            try {
                                                String gridDimensionsString = lineList.get(currentPosition)[6].concat(
                                                        lineList.get(currentPosition)[7]);
                                                matcher = PARENTHESES_MATCHER.matcher(gridDimensionsString);

                                                if (matcher.matches()) {
                                                    gridDimensionsString = gridDimensionsString.replace("(", "");
                                                    gridDimensionsString = gridDimensionsString.replace(")", "");
                                                    gridDimensionsStringArray = gridDimensionsString.split(",");

                                                    for (int i = 0; i < gridDimensionsStringArray.length; i++) {
                                                        gridDimensions[i] = Integer.parseInt(gridDimensionsStringArray[i]);
                                                    }
                                                } else {
                                                    throw new SyntaxError("Issue with grid dimensions.\n Line " +
                                                            currentPosition + " \"" + errorReportBuilder(currentPosition) +"\"");
                                                }
                                            } catch (NullPointerException ee) {
                                                ((JPanel) componentList.get(currentPosition)).setLayout(new GridLayout());
                                            }
                                        }
                                        break;

                                    case "Spring":
                                        ((JPanel) componentList.get(currentPosition)).setLayout(new SpringLayout());
                                        break;

                                    default:
                                        throw new SyntaxError("Invalid layout choice.\n Line " +
                                                currentPosition + " \"" + errorReportBuilder(currentPosition) +"\"");
                                }
                            }
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "No layout selected.");
                        }
                        break;

                    case "End":
                        currentNode = currentNode.getParent();
                        break;

                    case "End.":
                        if (currentPosition != lineList.size() - 1) {
                            throw new SyntaxError("Code after line " + currentPosition + "is unreachable.\n" +
                                    " \"" + errorReportBuilder(currentPosition)+"\"");
                        }
                        break;

                    case "Button":
                        String buttonText = lineList.get(currentPosition)[1];
                        if (quotesChecker(buttonText)) {
                            buttonText = buttonText.replace("\"", "");
                        } else {
                            throw new SyntaxError("Issue with button text.\n Line " +
                                    currentPosition + " \"" + errorReportBuilder(currentPosition) +"\"");
                        }

                        if (componentList.get((Integer) currentNode.getData()) instanceof JFrame) {

                            ((JFrame) componentList.get((Integer) currentNode.getData())).add(new JButton(buttonText));

                        } else if ((componentList.get((Integer) currentNode.getData()) instanceof JPanel)) {

                            ((JPanel) componentList.get((Integer) currentNode.getData())).add(new JButton(buttonText));

                        } else {
                            throw new SyntaxError("Invalid object type.\n Line " +
                                    currentPosition + " \"" + errorReportBuilder(currentPosition) +"\"");
                        }
                        break;

                    case "Textfield":
                        int textFieldSize;

                        String textFieldSizeString = lineList.get(currentPosition)[1];
                        textFieldSize = Integer.parseInt(textFieldSizeString);
                        if (componentList.get((Integer) currentNode.getData()) instanceof JFrame) {

                            ((JFrame) componentList.get((Integer) currentNode.getData())).add(new JTextField(textFieldSize));

                        } else if ((componentList.get((Integer) currentNode.getData()) instanceof JPanel)) {

                            ((JPanel) componentList.get((Integer) currentNode.getData())).add(new JTextField(textFieldSize));
                        } else {
                            throw new SyntaxError("Invalid object type.\n Line " +
                                    currentPosition + " \"" + errorReportBuilder(currentPosition) +"\"");
                        }
                        break;

                    case "Label":

                        String labelName = lineList.get(currentPosition)[1];

                        if (quotesChecker(labelName)) {
                            labelName = labelName.replace("\"", "");

                            if (componentList.get((Integer) currentNode.getData()) instanceof JFrame) {

                                ((JFrame) componentList.get((Integer) currentNode.getData())).add(new JLabel(labelName));

                            } else if ((componentList.get((Integer) currentNode.getData()) instanceof JPanel)) {

                                ((JPanel) componentList.get((Integer) currentNode.getData())).add(new JLabel(labelName));

                            } else {
                                throw new SyntaxError("Invalid object type.\n Line " +
                                        currentPosition + " \"" + errorReportBuilder(currentPosition) +"\"");
                            }
                        }
                        break;

                    case "Group":

                        ArrayList<JRadioButton> radioButtonArrayList = new ArrayList<>();
                        group = new ButtonGroup();
                        int componentCounter = 0;
                        currentPosition++;

                        while (!lineList.get(currentPosition)[0].equals("End")) {

                            if (lineList.get(currentPosition)[0].equals("Radio")) {
                                if (!quotesChecker(lineList.get(currentPosition)[1])){
                                    throw new SyntaxError("Values must be enclosed in quotes.\n Line " +
                                            errorReportBuilder(currentPosition));
                                }
                                radioButtonArrayList.add(new JRadioButton(lineList.get(currentPosition)[1]));

                                if (componentList.get((Integer) currentNode.getData()) instanceof JFrame) {
                                    ((JFrame) componentList.get((Integer) currentNode.getData())).add(
                                            radioButtonArrayList.get(componentCounter));
                                }
                                if ((componentList.get((Integer) currentNode.getData()) instanceof JPanel)) {
                                    ((JPanel) componentList.get((Integer) currentNode.getData())).add(
                                            radioButtonArrayList.get(componentCounter));
                                }
                                componentCounter++;
                                currentPosition++;
                            }
                            if (!lineList.get(currentPosition)[0].equals("End") &&
                                   ! lineList.get(currentPosition)[0].equals("Radio")) {
                                throw new SyntaxError("Error with radio button group format.\n Line " +
                                        currentPosition + " \"" + errorReportBuilder(currentPosition) +"\"");
                            }
                        }
                        for (JRadioButton jRadioButton : radioButtonArrayList) {
                            group.add(jRadioButton);
                        }
                        break;

                    default:
                        throw new SyntaxError("Invalid token.\n Line " +
                                currentPosition + " \"" + errorReportBuilder(currentPosition) +"\"");
                }
                currentPosition++;
                FrameBuilder(currentPosition);

            } else {
                if (!currentNode.isRoot()) {
                    currentNode = currentNode.getParent();
                }
                guiBuilder(currentNode);

                for (Object o : componentList) {

                    if (o instanceof JFrame) {
                        ((JFrame) o).setVisible(true);
                    }
                    if (o instanceof JPanel) {
                        ((JPanel) o).setVisible(true);
                    }
                }
            }

        }catch (SyntaxError e) {
            JOptionPane.showMessageDialog(null, e.message);
            System.exit(0);
        }catch (NullPointerException ee){
            JOptionPane.showMessageDialog(null, "Null pointer exception. Make sure all line breaks" +
                    " in the correct place.");
            }
        }

        private void guiBuilder(Node node){
            if (node.getChildren() == null){
                return;
            }
            for (int i = 0; i < node.getChildren().size(); i++){
                Node childNode =(Node) node.getChildren().get(i);
                if (componentList.get((Integer) node.getData()) instanceof JFrame){
                    if (componentList.get((Integer) childNode.getData()) instanceof JFrame){
                        ((JFrame) componentList.get((Integer) node.getData())).add((JFrame) componentList.get(
                                (Integer) childNode.getData()));
                    }
                    if (componentList.get((Integer) childNode.getData()) instanceof JPanel){
                        ((JFrame) componentList.get((Integer) node.getData())).add((JPanel) componentList.get(
                                (Integer) childNode.getData()));
                    }
                }
                if (componentList.get((Integer) node.getData()) instanceof JPanel){
                    if (componentList.get((Integer) node.getChildren().get(i)) instanceof JFrame){
                        ((JFrame) componentList.get((Integer) node.getData())).add((JFrame) componentList.get(
                                (Integer) childNode.getData()));
                    }
                    if (componentList.get((Integer) node.getData()) instanceof JPanel){
                        ((JFrame) componentList.get((Integer) node.getData())).add((JPanel) componentList.get(
                                (Integer) childNode.getData()));
                    }
                }
                guiBuilder((Node) node.getChildren().get(i));
            }
            ((JFrame) componentList.get(0)).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        private String errorReportBuilder(int currentPos){
            String infoString = "";
            for (int i = 0; i < lineList.get(currentPos).length; i++){
                infoString += lineList.get(currentPos)[i] + " ";
            }
            infoString = infoString.trim();
            return infoString;
        }
        private boolean quotesChecker (String input){
        if(input.startsWith("\"") && input.endsWith("\"")){
            return true;
        }else{
            return false;
            }
        }
        private void lineBreakChecker(String[] input, int currentPosition) throws SyntaxError {
            switch (input [0]){
                case "Window":
                    if (input[5].equals("Flow")){
                        if (! input[6].equals(":")){
                            throw new SyntaxError("Incorrect token.\n" +"Line "+ currentPosition + " " +
                                    errorReportBuilder(currentPosition));
                        }
                    }
                    if (input[5].equals("Grid")){
                        if (! input[10].equals(":")){
                            throw new SyntaxError("Incorrect token.\n" +"Line "+ currentPosition + " " +
                                    errorReportBuilder(currentPosition));
                        }
                    }

                    break;
                case "Frame":
                    if (input[2].equals("Flow")){
                        if (! input[3].equals(":")){
                            throw new SyntaxError("Incorrect token.\n" +"Line "+ currentPosition + " " +
                                    errorReportBuilder(currentPosition));
                        }
                    }
                    if (input[2].equals("Grid")){
                        if (! input[7].equals(":")){
                            throw new SyntaxError("Incorrect token.\n" +"Line "+ currentPosition + " " +
                                    errorReportBuilder(currentPosition));
                        }

                    }

                    break;
                case "Textfield":
                case "Button":
                case "Label":
                case "Radio":
                    if (! input[2].equals(";")){
                        throw new SyntaxError("Incorrect token.\n" +"Line "+ currentPosition + " " +
                                errorReportBuilder(currentPosition));
                    }

                    break;

                case "End":
                    if (! input[1].equals(";")){
                        System.out.println(input[1]);
                        throw new SyntaxError("Incorrect token.\n" +"Line "+ currentPosition + " " +
                                errorReportBuilder(currentPosition));
                    }
                    break;

                case "Group":
                    if (! input[1].equals(":")){
                        throw new SyntaxError("Incorrect token.\n" +"Line "+ currentPosition + " " +
                                errorReportBuilder(currentPosition));
                    }
                    break;
                default:
                    break;


            }


        }
    }




