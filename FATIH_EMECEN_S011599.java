import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.PrintWriter;

public class FATIH_EMECEN_S011599 {

    public static void main(String[] args) {
        transitionTable();
    }
    public static Stack<String> alphabetRead(String statesName1, String statesName2 ) {
        Stack<String> alphabet = new Stack<String>();
        try {
            File myObj = new File("src/NFA1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.matches(statesName1)) {
                    for (int i = 0; i<10;i++) {
                        data = myReader.nextLine();
                        if(data.matches(statesName2)) {
                            break;
                        }
                        alphabet.push(data);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return alphabet;
    }
    public static void transitionTable() {
        Stack<String> allData = new Stack<String>();
        Stack<String> alphabet = new Stack<String>();
        Stack<String> allStates = new Stack<String>();
        Stack<String> startStates = new Stack<String>();
        Stack<String> finalState = new Stack<String>();
        Stack<String> statesNew = new Stack<String>();
        Stack<String> statesNew2 = new Stack<String>();
        Stack<String> statesNew3 = new Stack<String>();
        Stack<String> statesOfLast = new Stack<String>();
        Stack<String> wholeStates = new Stack<String>();
        Set<String> elementsOfTransition = new HashSet<String>();

        alphabet=alphabetRead("ALPHABET","STATES");
        finalState = alphabetRead("FINAL","TRANSITIONS");
        allStates = alphabetRead("STATES","START");
        startStates = alphabetRead("START","FINAL");
        try {
            File myObj = new File("src/NFA1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.matches("TRANSITIONS")) {
                    for (int i = 0; i < 100; i++) {
                        data = myReader.nextLine();
                        if (data.matches("END")) {
                            break;
                        }
                        allData.push(data);
                    }
                }
            }
            for(int j = 0; j+1 < allData.size(); j++) {
                if (allData.elementAt(j).charAt(0) == startStates.elementAt(0).charAt(0)) {
                    String result = allData.elementAt(j);
                    elementsOfTransition.add(result);
                }
            }
            String[] newList = elementsOfTransition.toArray(new String[elementsOfTransition.size()]);
            for (int i = 0 ; i < newList.length;i++) {
                if (newList[i].charAt(0) != newList[i].charAt(4)) {
                    char state = (newList[i].charAt(2));
                    String s = "" + state;
                    for(int j = 0 ; j+1 < allData.size(); j++){
                        if(allData.elementAt(j).charAt(0) == allData.elementAt(j+1).charAt(0) &&
                                allData.elementAt(j).contains(s) && allData.elementAt(j+1).contains(s)) {
                            char stateName = allData.elementAt(j).charAt(4);
                            char stateName2 = allData.elementAt(j+1).charAt(4);
                            String states = "" + stateName + stateName2;
                            statesNew.push(states);
                            for (int z = 0; z+1 < alphabet.size(); z++) {
                                for(int x = 0; x <= statesNew.size(); x++) {
                                    for(int y = 0; y < allData.size(); y++) {
                                        if (allData.elementAt(y).charAt(0) == statesNew.elementAt(0).charAt(x) && allData.elementAt(y).charAt(2) == alphabet.elementAt(z).charAt(0)) {
                                            char nameS = allData.elementAt(y).charAt(4);
                                            String stat = "" + nameS;
                                            statesNew3.push(stat);
                                        } else if (allData.elementAt(y).charAt(0) == statesNew.elementAt(0).charAt(x) && allData.elementAt(y).charAt(2) == alphabet.elementAt(z+1).charAt(0)) {
                                            char nameS = allData.elementAt(y).charAt(4);
                                            String stat = "" + nameS;
                                            statesOfLast.push(stat);
                                        }
                                    }
                                }
                            }
                            String result = statesNew3.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "").replaceAll(" ", "");
                            String result2 = statesOfLast.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "").replaceAll(" ", "");
                            String result3 = statesNew.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "").replaceAll(" ", "");
                            String result4 = startStates.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "").replaceAll(" ", "");

                            if (result3.equalsIgnoreCase(result2)) {

                            } else {
                                statesNew.push(result2);
                            }
                            if (result3.equalsIgnoreCase(result)) {

                            } else {
                                statesNew.push(result);
                            }
                            if(statesNew.contains(result4)){
                                statesNew.remove(result4);
                            }
                            statesNew.remove("B");
                            for (int z = 0; z+1 < alphabet.size(); z++) {
                                for(int x = 0; x <= statesNew.size(); x++) {
                                    for(int y = 0; y < allData.size(); y++) {
                                        if (allData.elementAt(y).charAt(0) == statesNew.elementAt(1).charAt(x) && allData.elementAt(y).charAt(2) == alphabet.elementAt(z).charAt(0)) {
                                            char nameS = allData.elementAt(y).charAt(4);
                                            String stat = "" + nameS;
                                            statesNew3.push(stat);
                                        } else if (allData.elementAt(y).charAt(0) == statesNew.elementAt(1).charAt(x) && allData.elementAt(y).charAt(2) == alphabet.elementAt(z+1).charAt(0)) {
                                            char nameS = allData.elementAt(y).charAt(4);
                                            String stat = "" + nameS;
                                            statesOfLast.push(stat);
                                        }
                                    }
                                }
                            }
                            //0
                            String output = statesNew3.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "").replaceAll(" ", "");
                            //1
                            String output2 = statesOfLast.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "").replaceAll(" ", "");
                            String output3 = statesNew.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "").replaceAll(" ", "");
                            char[] chars = output.toCharArray();
                            Set<Character> charSet = new LinkedHashSet<Character>();
                            for (char c : chars) {
                                charSet.add(c);
                            }

                            StringBuilder sb = new StringBuilder();
                            for (Character character : charSet) {
                                sb.append(character);
                            }
                            char[] chars2 = output2.toCharArray();
                            Set<Character> charSet2 = new LinkedHashSet<Character>();
                            for (char c2 : chars2) {
                                charSet2.add(c2);
                            }

                            StringBuilder sb2 = new StringBuilder();
                            for (Character character : charSet2) {
                                sb2.append(character);
                            }
                            wholeStates.push(result);
                            wholeStates.push(result2);
                            wholeStates.push(sb.toString());
                            wholeStates.push(sb2.toString());

                            for(int a = 0; a < statesNew.size(); a++) {
                                if (statesNew.elementAt(a).contains(finalState.elementAt(0))) {
                                    finalState.push(statesNew.elementAt(a));
                                }
                            }
                            if(statesNew2.equals(statesNew2)){

                                PrintWriter out = new PrintWriter("output1.txt");
                                out.println("ALPHABET");
                                for(int alp = 0; alp < alphabet.size();alp++) {
                                    out.println(alphabet.elementAt(alp));
                                }
                                out.println("STATES");
                                for(int sta = 0; sta < allStates.size();sta++){
                                    out.println(allStates.elementAt(sta));
                                }
                                out.println("START");
                                out.println(startStates.elementAt(0));
                                out.println("FINAL");
                                for(int fin = 0 ; fin < finalState.size() ; fin++) {
                                    out.println(finalState.elementAt(fin));
                                }
                                out.println("TRANSITIONS");
                                Iterator<String> elements = elementsOfTransition.iterator();
                                while (elements.hasNext()) {
                                    out.println(elements.next());
                                }
                                for(int k = 0; k < statesNew.size(); k++){
                                    for(int l = 0; l < statesNew.size(); l++){
                                        out.print(statesNew.elementAt(k) + " ");
                                        out.print(alphabet.elementAt(l) + " ");
                                        out.println(wholeStates.elementAt(l));
                                    }
                                }
                                out.println("END");
                                out.close();
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
