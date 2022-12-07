import java.util.*;

public class Apaxians {
    
    public static void main(String args[]) {
        Scanner nameInput = new Scanner(System.in);
        String name = nameInput.nextLine();
        System.out.println(compactName(name));
    }
    
    public static String compactName(String name) {
        String nameOutput = "";
        int nameLen = name.length();
        char currentChar;
        char prevChar = 0;
        
        for (int i = 0; i < nameLen; i++) {
            currentChar = name.charAt(i);
            
            if (currentChar != prevChar) {
                nameOutput = nameOutput + currentChar;
            }
            prevChar = currentChar;
        }
        
        return nameOutput;
    }
}