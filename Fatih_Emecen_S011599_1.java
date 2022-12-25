import java.util.ArrayList;

public class Fatih_Emecen_S011599_1 {

    private static String currentState;
    private int tapeHead;
    private static ArrayList<String> allStates = new ArrayList<String>();
    static boolean exists;
    static int maxTapeLength = 20;

    private static String[] tape = new String[maxTapeLength];
    public void TuringMachine() {
        tapeHead = 0;
        currentState = Fatih_Emecen_S011599_2.initialState;
        allStates.add(currentState);

        int j = 0;
        while(j < maxTapeLength) {
            if(j < Fatih_Emecen_S011599_2.inputString.length()){
                tape[j] = String.valueOf(Fatih_Emecen_S011599_2.inputString.charAt(j));
            } else {
                tape[j] = Fatih_Emecen_S011599_2.blankSymbol;
            }
            j++;
        }


        while (!currentState.equals(Fatih_Emecen_S011599_2.acceptState) && !currentState.equals(Fatih_Emecen_S011599_2.rejectState)) {
            String currentChar = tape[tapeHead];
            String[] transition = getTransition(currentState, currentChar);
            writingTape(transition[2]);
            shiftingTape(transition[3]);
            currentState = transition[4];
            allStates.add(currentState);
        }
    }

    private String[] getTransition(String state, String c) {
        for (String[] transition : Fatih_Emecen_S011599_2.transitions) {
            if (transition[0].equals(state) && transition[1].equals(c)) {
                return transition;
            }
        }
        return null;
    }

    private void writingTape(String value) {
        tape[tapeHead] = value;
    }

    private void shiftingTape(String direction) {
        if (direction.equals("R") && tapeHead < tape.length) {
            tapeHead++;
        } else if (direction.equals("L") && tapeHead >= 0) {
            tapeHead--;
        }
    }
    public void printConsole() {
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
        if (currentState.compareTo(Fatih_Emecen_S011599_2.acceptState) == 0) {
            System.out.println("\nResult: Accepted\n");

        } else if (currentState.compareTo(Fatih_Emecen_S011599_2.rejectState) == 0) {
            System.out.println("\nResult: Rejected\n");
        }
        if (Fatih_Emecen_S011599_2.inputScanner.hasNext()) {
            allStates = new ArrayList<String>();
            int i = 0;
            while (i < maxTapeLength) {
                tape[i] = Fatih_Emecen_S011599_2.blankSymbol;
                exists = true;
                i++;
            }
        } else {
            exists = false;
        }

    }
}
