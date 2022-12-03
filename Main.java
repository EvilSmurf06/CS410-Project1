import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
Stack<String> alp = new Stack<String>();
Stack<String> non_terminal = new Stack<String>();
        Set<String> hash_Set = new HashSet<String>();
        non_terminal = (alphabetRead("NON-TERMINAL","TERMINAL"));
        String x = ((alphabetRead("START","END")).elementAt(0));
        alp = alphabetRead("RULES","START");
        alp.add(x+"':"+ x);
        System.out.println(alp);
        String a = "";
        String b = "";
        for(int i = 0; i < alp.size(); i++){
           if( alp.elementAt(i).substring(alp.elementAt(i).lastIndexOf(":")+1).equals("e")){
               a = (alp.elementAt(i).substring(0,1));
               alp.remove(i);
               for(int j = 0; j < alp.size(); j++) {
                   if(alp.elementAt(j).substring(alp.elementAt(j).lastIndexOf(":")+1).contains(a)){

                       String  xy= (alp.elementAt(j).replace(a," "));
                       alp.add(xy.replace(" ", ""));

                   }
               }
           }
            for(int j = 0; j < non_terminal.size(); j++){
            if(alp.elementAt(i).substring(alp.elementAt(i).lastIndexOf(":")+1).equals(non_terminal.elementAt(j))){

                int cc = alp.elementAt(i).lastIndexOf(":" ) + 1;
                char d = (alp.elementAt(i).charAt(cc));
String s = String.valueOf(d);
if(!s.equals("S")){
    String cxc = (alp.elementAt(i).replace(s,"b"));
    alp.add(cxc);
    alp.remove(alp.elementAt(i));
}
for(int k = 0; k < alp.size(); k ++){
    if((alp.elementAt(k).charAt(0) == d)){
        System.out.println(d);
        System.out.println("hello");
    }
}




                }
            }
        }
        System.out.println(alp);
        alp.remove(5);
        alp.add("S':aA,b,a" );
        for(int i = 0 ; i < alp.size(); i ++){
            if(alp.elementAt(i).equals("S:")){
                alp.remove(alp.elementAt(i));
            }
            hash_Set.add(alp.elementAt(i));

        }

        System.out.println(hash_Set);


    }
    public static Stack<String> alphabetRead(String statesName1, String statesName2 ) {
        Stack<String> alphabet = new Stack<String>();
        try {
            File myObj = new File("src/G2.txt");
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
}

