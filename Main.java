import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FATİH_EMECEN_S011599 {
    public static void main(String[] args) {
        Stack<String> alp = new Stack<>();
        Stack<String> non_terminal = new Stack<>();
        Stack<String> terminal = new Stack<>();
        Stack<String> abcReplace = new Stack<>();
        Stack<String> ab = new Stack<>();
        Stack<String> v = new Stack<>();
        Stack<String> terminalAndNonTerminal = new Stack<>();
        Set<String> hash_Set = new HashSet<>();
        String startState = ((alphabetRead("START","")).elementAt(0));
        non_terminal = (alphabetRead("NON-TERMINAL","TERMINAL"));
        terminal = (alphabetRead("TERMINAL","RULES"));
        terminal.remove(startState);
        alp = alphabetRead("RULES","START");
        String a = "";
        for(int i = 0; i < alp.size(); i++){
            if( alp.elementAt(i).substring(alp.elementAt(i).lastIndexOf(":")+1).equals("e")) {

                    a = (alp.elementAt(i).substring(0, 1));
                    for (int j = 0; j < alp.size(); j++) {
                        if (alp.elementAt(j).substring(alp.elementAt(j).lastIndexOf(":") + 1).contains(a)) {

                            String xy = (alp.elementAt(j).substring(alp.elementAt(j).lastIndexOf(":") + 1).replace(a, " "));
                            String xyReplace = xy.replace(" ", "");
                            char c = alp.elementAt(j).charAt(0);
                            alp.add(c + ":" + xyReplace);


                        }
                    }

                alp.remove(i);
            }
        }

        for(int i = 0 ; i < alp.size(); i ++){
            if(alp.elementAt(i).equals("S:")){
                alp.remove(alp.elementAt(i));
            }
            hash_Set.add(alp.elementAt(i));

        }
        char el = ' ';
        for (String element :hash_Set) {
            // Implementing for loop
for(int i = 0 ; i < non_terminal.size(); i++) {
    if (element.substring(element.lastIndexOf(":") + 1).equals(non_terminal.elementAt(i))) {
        ab.push(non_terminal.elementAt(i));
        el = element.charAt(0);
        v.push(element);

    }
}
        }
        for (String element :hash_Set) {
            for(int i = 0; i < ab.size(); i++){
                if (element.charAt(0) == ab.elementAt(i).charAt(0)) {
                    String abc = (element.substring(2));
                    abcReplace.push(abc.replace(":", ""));

                }
            }

        }
        for(int i = 0; i < abcReplace.size(); i ++){
            alp.add(el + ":" + abcReplace.elementAt(i));
        }
        for(int i = 0; i < alp.size(); i++){
            hash_Set.add(alp.elementAt(i));
        }
        for(int i = 0; i < v.size(); i++){
            hash_Set.remove(v.elementAt(i));
        }
for(String element: hash_Set){
    terminalAndNonTerminal.add(element);
}



/*
*/

        for(int i = 0; i < terminalAndNonTerminal.size(); i++){
            if(terminalAndNonTerminal.elementAt(i).substring(terminalAndNonTerminal.elementAt(i).lastIndexOf(":")+1).length() >= 2){
                for(int j = 0; j < terminal.size(); j++){
                    if(terminalAndNonTerminal.elementAt(i).substring(terminalAndNonTerminal.elementAt(i).lastIndexOf(":")+1).contains(terminal.elementAt(j))){

                        String replaceTerminal = terminalAndNonTerminal.elementAt(i).substring(terminalAndNonTerminal.elementAt(i).lastIndexOf(":") + 1).replace(terminal.elementAt(j), "X");
                        hash_Set.remove(terminalAndNonTerminal.elementAt(i));
                        hash_Set.add(terminalAndNonTerminal.elementAt(i).charAt(0) + ":" + replaceTerminal);
                        hash_Set.add("X:" + terminal.elementAt(j));

                    }
                }

            }
        }
        terminalAndNonTerminal.removeAllElements();
        for(String element: hash_Set){

            terminalAndNonTerminal.add(element);
        }
        for(int i = 0; i < terminalAndNonTerminal.size(); i++){
            if(terminalAndNonTerminal.elementAt(i).substring(terminalAndNonTerminal.elementAt(i).lastIndexOf(":")+1).length() == 3){
                for(int j = 0; j < terminal.size(); j++){
                    String replaceNonTerminal = terminalAndNonTerminal.elementAt(i).substring(terminalAndNonTerminal.elementAt(i).lastIndexOf(":") + 1).replace(terminalAndNonTerminal.elementAt(i).substring(2,4), "Y");
                    hash_Set.remove(terminalAndNonTerminal.elementAt(i));
                    hash_Set.add(terminalAndNonTerminal.elementAt(i).charAt(0) + ":" + replaceNonTerminal);
                    hash_Set.add("Y:" + terminalAndNonTerminal.elementAt(i).substring(2,4));
                }

            }
        }
        System.out.println(hash_Set);

    }
    public static Stack<String> alphabetRead(String statesName1, String statesName2 ) {
        Stack<String> alphabet = new Stack<>();
        try {
            File myObj = new File("src/G2.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.matches("START")){
                    data = myReader.nextLine();
                    alphabet.push(data);
                }
                else if(data.matches(statesName1)) {
                    for (int i = 0; i< myObj.length();i++) {
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
}

