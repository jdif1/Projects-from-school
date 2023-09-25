import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GUI {

    GUI()
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("Benchmark Report");
        String[] columnTitles = {"Size", "Avg Count", "Coef Count", "Avg Time", "Coef Time"};
        String[][] resultsArray = fileReader();
        JTable table = new JTable(resultsArray, columnTitles);
        table.setBounds(30,40,440,420);
        frame.setSize(500,255);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    String[][] fileReader()
    {

        JFileChooser fileChooser = new JFileChooser();
        int approveCheck = fileChooser.showOpenDialog(null);

        String line;
        String[] temp;
        File inputFile;
        String[][]results = new String[12][5];

        if (approveCheck == JFileChooser.APPROVE_OPTION)
        {
            inputFile = fileChooser.getSelectedFile();
            try
            {
                Scanner scanner = new Scanner(inputFile);
                int yAxis = 0;
                while (scanner.hasNextLine())
                {
                    line = scanner.nextLine();
                    temp = line.split(" ");
                    for (int i = 0; i <temp.length; i++)
                    {
                        results[yAxis][i] = temp[i];
                    }
                    yAxis++;
                }
            } catch (FileNotFoundException e)
            {
                System.out.println("File not found");
                System.exit(0);
            }
        }
        return results;
    }
}
