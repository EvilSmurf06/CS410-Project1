import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Fatih_Emecen_S011599_2 {

    static int numInputVariables;
    static int numTapeVariables;
    static int numStates;
    static String initialState;
    static String acceptState;
    static String rejectState;
    static String blankSymbol;
    static Scanner inputScanner;
    static String [][] transitions;
    static String[] tapeAlphabet;
    static String[] inputAlphabet;
    static String inputString;

    public static void main (String[] args) throws Exception {

        File input = new File("input.txt");
        inputScanner = new Scanner(input);

        numInputVariables = Integer.parseInt(inputScanner.next());
        numTapeVariables = Integer.parseInt(inputScanner.next());
        numStates = Integer.parseInt(inputScanner.next());
        String[] allStates = new String [numStates];
        arrays(allStates);
        initialState = inputScanner.next();
        acceptState = inputScanner.next();
        rejectState = inputScanner.next();
        blankSymbol = inputScanner.next();
        tapeAlphabet = new String[numTapeVariables +1];
        arrays(tapeAlphabet);

        inputAlphabet = new String[numInputVariables];
        arrays(inputAlphabet);
        inputScanner.nextLine();

        ArrayList<String> temp =new ArrayList<>();

        String currentInput = inputScanner.nextLine();


        while(currentInput.charAt(0) == 'q'){
            String[] splited = currentInput.split("\\s+");
            for(int i = 0; i <5; i++) {
                temp.add(splited[i]);
            }
            currentInput = inputScanner.nextLine();
            if (currentInput.equals(""))
                break;
        }



        inputString = currentInput;

        transitions = new String[temp.size()/5][5];
        int count = 0;
        for(int i = 0; i< temp.size()/5; i++){
            for (int j = 0; j < 5; j++){
                transitions[i][j] = temp.get(count);
                count++;
            }
        }

        Fatih_Emecen_S011599_1 fatihEmecenS0115991 = new Fatih_Emecen_S011599_1();
        fatihEmecenS0115991.TuringMachine();
        fatihEmecenS0115991.printConsole();
        while(fatihEmecenS0115991.exists == true){
            inputString = inputScanner.next();
            fatihEmecenS0115991.TuringMachine();
            fatihEmecenS0115991.printConsole();
        }
    }

    public static void arrays(String[] array){
        for(int i = 0; i < array.length; i++){
            array[i] = inputScanner.next();
        }

    }

}