import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        String path = null;
        File file = null;
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true){
            System.out.println("Please select your option from the menu.\n0: Exit\n" +
                    "1: Select Directory\n2: List Directory content (first level)\n" +
                    "3: List directory content (all levels)\n4: Delete file\n5: Display file (hexadecimal view)\n" +
                    "6: Encrypt file\n7: Decrypt file");
            input = scanner.nextLine();
            String selection;
            switch (input){
                case "0":
                    System.out.println("Goodbye.");
                    System.exit(0);
                    break;

                case "1":
                    if (path == null){
                        System.out.println("You have not selected a directory yet.");
                    }else{
                        System.out.println("Your current directory is " + path);
                    }
                    System.out.println("Please enter which directory you want.");
                    selection = scanner.nextLine();
                    path = selection;
                    file = new File(path);
                    if (!file.exists()|| !file.isDirectory()){
                        System.out.println("That is an invalid choice. Returning to menu");
                        path = null;
                        break;
                    }
                    path = file.getAbsolutePath();
                    break;

                case "2":
                    if (path == null){
                        System.out.println("No directory has been selected yet. " +
                                "Please select a directory first.");
                        break;
                    }
                    getFirstLevel(path);
                    break;

                    
                case "3":
                    if (path == null){
                        System.out.println("No directory has been selected yet. " +
                                "Please select a directory first.");
                        break;
                    }
                    displayAllLevels(Objects.requireNonNull(file.listFiles()), 0, 0);
                    break;

                case "4":
                    if (path == null){
                        System.out.println("No directory has been selected yet. " +
                                "Please select a directory first.");
                        break;
                    }
                    System.out.println("Enter the name of file you wish to delete.\n" +
                            "The files in the current directory are:");
                    File[] deleteChoices = file.listFiles();
                    String [] deleteChoiceString = new String[deleteChoices.length];
                    for (int i = 0; i < deleteChoices.length; i++){
                        deleteChoiceString[i] = deleteChoices[i].getName().replace(path +"\\", "");
                        System.out.println(deleteChoiceString[i]);
                    }
                    selection = scanner.nextLine();
                    if (!isFileFound(selection, deleteChoices, deleteChoiceString)){
                        break;
                    }
                    deleteFile(getSubFolder(path, selection));
                    break;



                case "5":
                    if (path == null){
                        System.out.println("No directory has been selected yet. " +
                                "Please select a directory first.");
                        break;
                    }
                    System.out.println("Enter the name of file you wish to view in hexadecimal.\n" +
                            "The files in the current directory are:");
                    File[] hexChoices = file.listFiles();
                    String [] hexChoiceString = new String[hexChoices.length];
                    for (int i = 0; i < hexChoices.length; i++){
                        hexChoiceString[i] = hexChoices[i].getName().replace(path +"\\", "");
                        System.out.println(hexChoiceString[i]);
                    }
                    selection = scanner.nextLine();
                    if (!isFileFound(selection, hexChoices, hexChoiceString)){
                        break;
                    }
                    fileToHex(getSubFolder(path, selection));
                    break;

                case "6":
                    if (path == null){
                        System.out.println("No directory has been selected yet. " +
                                "Please select a directory first.");
                        break;
                    }
                    System.out.println("Please enter what you would like the password to be.");
                    String key = scanner.nextLine();
                    System.out.println("Enter the name of file you wish to encrypt.\n" +
                            "The files in the current directory are:");
                    File[] encryptChoices = file.listFiles();
                    String [] encryptChoiceString = new String[encryptChoices.length];
                    for (int i = 0; i < encryptChoices.length; i++){
                        encryptChoiceString[i] = encryptChoices[i].getName().replace(path +"\\", "");
                        System.out.println(encryptChoiceString[i]);
                    }
                    selection = scanner.nextLine();
                    if (!isFileFound(selection, encryptChoices, encryptChoiceString)){ break; }
                    System.out.println("Please enter the new name for the file.");
                    String encryptName = scanner.nextLine();

                    encryptDecrypt(getSubFolder(path, selection), key, encryptName);
                    break;
                case "7":
                    if (path == null){
                        System.out.println("No directory has been selected yet. " +
                                "Please select a directory first.");
                        break;
                    }
                    System.out.println("Please enter your password.");
                    String unlock = scanner.nextLine();
                    System.out.println("Enter the name of file you wish to decrypt.\n" +
                            "The files in the current directory are:");
                    File[] decryptChoices = file.listFiles();
                    String [] decryptChoiceString = new String[decryptChoices.length];
                    for (int i = 0; i < decryptChoices.length; i++){
                        decryptChoiceString[i] = decryptChoices[i].getName().replace(path +"\\", "");
                        System.out.println(decryptChoiceString[i]);
                    }
                    selection = scanner.nextLine();
                    if (!isFileFound(selection, decryptChoices, decryptChoiceString)){ break; }
                    System.out.println("Please enter the new name for the file.");
                    String decryptName = scanner.nextLine();

                    encryptDecrypt(getSubFolder(path, selection), unlock, decryptName);
                    break;
            }
        }
    }

    private static boolean isFileFound(String selection, File[] choices, String[] choiceString) {
        boolean fileFound = true;
        for (int i = 0; i < choiceString.length; i++){
            if (Objects.equals(choiceString[i], selection)){
                break;
            }
            if (i == choices.length-1){
                fileFound = false;
                System.out.println("File does not exist. Returning to menu.");
            }
        }
        return fileFound;
    }

    private static ArrayList<String> getFirstLevel(String path){
        File currentFolder = new File(path);
        File[] files = currentFolder.listFiles();
        ArrayList<String> fileAndDirectoryList = new ArrayList<>();
        ArrayList<String> directoryList = new ArrayList<>();
        ArrayList<String> fileList = new ArrayList<>();

        assert files != null;
        for(File testFile : files){
                fileAndDirectoryList.add(testFile.getName());
                if (testFile.isDirectory()){
                    directoryList.add(testFile.getName());
                }else{
                    fileList.add(testFile.getName());
                }
        }
        System.out.println("***************************************************");
        if (!directoryList.isEmpty()){
            System.out.println("Subdirectories in " + currentFolder.getAbsolutePath());
            for (int i = 0; i < directoryList.size(); i++){
                System.out.println(directoryList.get(i));
            }
        }else{
            System.out.println("No subdirectories to display.");
        }
        System.out.println("***************************************************");
        if(!fileList.isEmpty()){
            System.out.println("Files in " + currentFolder.getAbsolutePath());
            for (int i = 0; i < fileList.size(); i++){
                System.out.println(fileList.get(i));
            }
        }else{
            System.out.println("No files to display");
        }
        System.out.println("***************************************************");
        return fileAndDirectoryList;
    }

    private static File getSubFolder(String path, String input){
        return new File(path + "\\" + input);
    }
    private static void displayAllLevels(File[] fileArray, int index, int level){
        if (index == fileArray.length){
            return;
        }

        ArrayList<File> subFiles = new ArrayList<>();
        ArrayList<File> subDirectories = new ArrayList<>();
        File directory = fileArray[index];
        File[] directoryFiles = directory.listFiles();
        if (directoryFiles != null){
            for (File file : directoryFiles){
                if(file.isFile()){
                    subFiles.add(file);
                }else{
                    subDirectories.add(file);
                    displayAllLevels(fileArray[index].listFiles(), 0, ++level);
                }
            }
        }

        if (fileArray[index].isDirectory()){
            System.out.println("***************************************************");
            if (subDirectories.isEmpty()){
                for (int j = 0; j < level; j++) {
                    System.out.print("-");
                }
                System.out.println("No subdirectories in " + fileArray[index].getAbsolutePath());
            }else{
                for (int j = 0; j < level; j++) {
                    System.out.print("-");
                }
                System.out.println("Subdirectories in " + fileArray[index].getAbsolutePath());
                for ( File file : subDirectories){
                    for (int j = 0; j < level; j++){
                        System.out.print("-");
                    }
                    System.out.println(file.getName());
                }
            }
            System.out.println("***************************************************");
            if (subFiles.isEmpty()){
                for (int j = 0; j < level; j++) {
                    System.out.print("-");
                }
                System.out.println("No files in " + fileArray[index].getAbsolutePath());
            }else{
                for (int j = 0; j < level; j++) {
                    System.out.print("-");
                }
                System.out.println("Files in " + fileArray[index].getAbsolutePath());
                for ( File file : subFiles){
                    for (int j = 0; j < level; j++){
                        System.out.print("-");
                    }
                    System.out.println(file.getName());
                }
            }
            System.out.println("***************************************************");
        }

        displayAllLevels(fileArray, ++index, level);
    }
    private static void deleteFile(File fileToBeDeleted){
        String fileName = fileToBeDeleted.getName();
        boolean deleteCheck = fileToBeDeleted.delete();
        if (deleteCheck){
            System.out.println(fileName + " deleted.");
        }else{
            System.out.println(fileName + " could not be deleted.");
        }
    }
    private static void fileToHex(File fileToBeRead) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            FileInputStream fileInputStream = new FileInputStream(fileToBeRead);
            byte [] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) > - 1){
                for(int i = 0; i < bytesRead; i++){
                    stringBuilder.append(String.format("%02x", buffer[i] & 0xFF)).append(i != bytesRead - 1 ? " " : "");
                }
                System.out.println(stringBuilder);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private static void encryptDecrypt(File fileToBeEncrypted, String key, String newName){
        try {
            FileInputStream fileInputStream = new FileInputStream(fileToBeEncrypted);
            byte[] data = new byte[fileInputStream.available()];
            byte[] keyBytes = key.getBytes();
            fileInputStream.read(data);
            if (keyBytes.length > data.length){
                System.out.println("Encryption key is too long for file size. " +
                        "Key cannot be longer than " + data.length + ". Exiting.");
                return;
            }
            if (keyBytes.length > 256){
                System.out.println("Encryption key cannot be more than 256 bytes. Exiting.");
                return;
            }

            if (data.length > keyBytes.length){
                for (int i = 0; i < data.length; i++){
                    for (int j = 0; j < keyBytes.length; j++){
                        data[i] = (byte) (data[i] ^ keyBytes[j % keyBytes.length]);
                        i++;
                        if (i == data.length){
                            break;
                        }
                    }
                }
            }

            String filePath = fileToBeEncrypted.getPath();
            String[] pathArray = filePath.split("\\\\");
            filePath = "";
            for (int i = 0; i < pathArray.length-1; i++){
                filePath = filePath + pathArray[i] + "\\";
            }
            FileOutputStream fileOutputStream = new FileOutputStream(filePath+newName);
            fileOutputStream.write(data);
            fileOutputStream.close();
            fileInputStream.close();
        }catch(IOException e){
            System.out.println("error");
            System.out.println(e.getMessage());
        }
    }
}