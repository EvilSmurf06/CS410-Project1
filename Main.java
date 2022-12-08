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
        Set<String> hash_Set = new HashSet<>();
        non_terminal = (alphabetRead("NON-TERMINAL","TERMINAL"));
        terminal = (alphabetRead("TERMINAL","RULES"));
        String x = ((alphabetRead("START","")).elementAt(0));
        alp = alphabetRead("RULES","START");
        //alp.add(x+"':"+ x);
        System.out.println(alp);
        String a = "";
        for(int i = 0; i < alp.size(); i++){
            if( alp.elementAt(i).substring(alp.elementAt(i).lastIndexOf(":")+1).equals("e")) {
                if (alp.elementAt(i).charAt(0) == x.charAt(0)) {
                    a = (alp.elementAt(i).substring(0, 1));



                    for (int j = 0; j < alp.size(); j++) {
                        if (alp.elementAt(j).substring(alp.elementAt(j).lastIndexOf(":") + 1).contains(a)) {

                            String xy = (alp.elementAt(j).substring(alp.elementAt(j).lastIndexOf(":") + 1).replace(a, " "));
                            String xyReplace = xy.replace(" ", "");
                            char c = alp.elementAt(j).charAt(0);
                            alp.add(c + ":" + xyReplace);


                        }
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
        System.out.println(hash_Set);
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
        System.out.println(hash_Set);
        for(String element: hash_Set){
            if(element.substring(element.lastIndexOf(":") + 1).length() == 1){
                if(element.substring(element.lastIndexOf(":") + 1).contains(terminal.elementAt(0)) || element.substring(element.lastIndexOf(":") + 1).contains(terminal.elementAt(1))){

                }
            else if (element.substring(element.lastIndexOf(":") + 1).length() == 2 ){
                if((element.substring(element.lastIndexOf(":") + 1).contains(terminal.elementAt(0)) || element.substring(element.lastIndexOf(":") + 1).contains(terminal.elementAt(1)))){

                }
                }

            }
        }
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

