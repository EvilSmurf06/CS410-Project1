import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Fatih_Emecen_S011599 {
    private static String currentState;
    private static int tapeHead;
    private static ArrayList<String> allStates = new ArrayList<String>();
    static boolean exists;
    static int maxTapeLength = 20;
    private static String[] tape = new String[maxTapeLength];
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

        Fatih_Emecen_S011599 TM = new Fatih_Emecen_S011599();
        TuringMachine();
        printConsole();
        while(TM.exists == true){
            inputString = inputScanner.next();
            TM.TuringMachine();
            TM.printConsole();
        }
    }
    public static void TuringMachine() {
        tapeHead = 0;
        currentState = Fatih_Emecen_S011599.initialState;
        allStates.add(currentState);

        int j = 0;
        while(j < maxTapeLength) {
            if(j < Fatih_Emecen_S011599.inputString.length()){
                tape[j] = String.valueOf(Fatih_Emecen_S011599.inputString.charAt(j));
            } else {
                tape[j] = Fatih_Emecen_S011599.blankSymbol;
            }
            j++;
        }


        while (!currentState.equals(Fatih_Emecen_S011599.acceptState) && !currentState.equals(Fatih_Emecen_S011599.rejectState)) {
            String currentChar = tape[tapeHead];
            String[] transition = getTransition(currentState, currentChar);
            writingTape(transition[2]);
            shiftingTape(transition[3]);
            currentState = transition[4];
            allStates.add(currentState);
        }
    }

    private static String[] getTransition(String state, String c) {
        for (String[] transition : Fatih_Emecen_S011599.transitions) {
            if (transition[0].equals(state) && transition[1].equals(c)) {
                return transition;
            }
        }
        return null;
    }

    private static void writingTape(String value) {
        tape[tapeHead] = value;
    }

    private static void shiftingTape(String direction) {
        if (direction.equals("R") && tapeHead < tape.length) {
            tapeHead++;
        } else if (direction.equals("L") && tapeHead >= 0) {
            tapeHead--;
        }
    }
    public static void printConsole() {
        if (!exists) {
            System.out.print("Route: ");
            for (int i = 0; i < allStates.size(); i++) {
                System.out.print((allStates.get(i) + " "));
            }
        } else {
            System.out.print("Route: ");
            for (int i = 0; i < allStates.size(); i++) {
                System.out.print(allStates.get(i) + " ");
            }


        }
        if (currentState.compareTo(Fatih_Emecen_S011599.acceptState) == 0) {
            System.out.println("\nResult: Accepted\n");

        } else if (currentState.compareTo(Fatih_Emecen_S011599.rejectState) == 0) {
            System.out.println("\nResult: Rejected\n");
        }
        if (Fatih_Emecen_S011599.inputScanner.hasNext()) {
            allStates = new ArrayList<String>();
            int i = 0;
            while (i < maxTapeLength) {
                tape[i] = Fatih_Emecen_S011599.blankSymbol;
                exists = true;
                i++;
            }
        } else {
            exists = false;
        }

    }
    public static void arrays(String[] array){
        for(int i = 0; i < array.length; i++){
            array[i] = inputScanner.next();
        }

    }

}