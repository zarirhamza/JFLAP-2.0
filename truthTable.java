/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zarir Hamza
 */
public class truthTable {

    public DefaultTableModel TruthTable(ArrayList<StatesData> state, ArrayList<TransitionsData> transition) {
        ArrayList<String> letter = new ArrayList<String>();
        int numberofRows = transition.get(0).getInput().length() * state.size();
        int numofTrans = 1;
        int numofBits = 0;
        for (int a = 0; a < transition.get(0).getInput().length(); a++) {
            numofTrans = (int) (numofTrans + (Math.pow(2, a)));
        }
        /*letter.add("Current State");
       letter.add("Input");
       letter.add("Next State");*/
        String[] array;
        for (int f = 0; f < transition.size(); f++) {
            array = transition.get(f).getInput().split("/");
            for (int k = 0; k < array.length; k++) {
                if (array[k].length() > numofBits) {
                    numofBits = array[k].length();
                }
            }
        }
        for (int g = 0; g < state.get(0).getName().length() - 1; g++) {
            letter.add("Q" + Integer.toString(g));
        }
        for (int h = 0; h < numofBits; h++) {
            letter.add(Character.toString((char) ((char) h + 65)));
        }
        for (int g = 0; g < state.get(0).getName().length() - 1; g++) {
            letter.add("D" + Integer.toString(g));
        }

        DefaultTableModel truthTables = new DefaultTableModel(letter.toArray(), 0);
        //   System.out.println("asdfasd" + numofTrans);
        ArrayList<String> used = new ArrayList<>();
        String formatter = "%0" + numofBits + "d";
        ArrayList<Object> thing = new ArrayList<>();

        for (int b = 0; b < state.size(); b++) {
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int bit = 0; bit < Math.pow(2, numofBits); bit++) {
                String abc = Integer.toBinaryString(bit);
                String ghf = String.format(formatter, Integer.parseInt(abc));

                numbers.add(Integer.parseInt(ghf, 10));
            }
            //   System.out.println("hello" + numbers);
            for (int iter = 0; iter < transition.size(); iter++) {
                //       System.out.println(transition.get(iter).getInput().contains(Integer.toBinaryString(bit)));
                String array2[] = transition.get(iter).getInput().split("/");
                for (int q = 0; q < array2.length; q++) {
                    if (transition.get(iter).getfState().equals(state.get(b).getName()) && numbers.contains(Integer.parseInt(array2[q], 10))) {
                        numbers.remove(new Integer(Integer.parseInt(array2[q])));
                    }
                }
            }
            //   used.clear();
            //     System.out.println("hello2" + numbers);
            for (int bit = 0; bit < Math.pow(2, numofBits); bit++) {
                String abc = Integer.toBinaryString(bit);
                String ghf = String.format(formatter, Integer.parseInt(abc));
                // System.out.println("hello" + bit);

                if (numbers.contains(Integer.parseInt(ghf, 10))) {
                    //       System.out.println("Im here" + ghf);
                    for (int u = 0; u < state.get(b).getName().length() - 1; u++) {
                        thing.add(state.get(b).getName().charAt(u));
                    }
                    for (int u = 0; u < ghf.length(); u++) {
                        thing.add(ghf.charAt(u));
                    }
                    for (int u = 0; u < state.get(b).getName().length() - 1; u++) {
                        thing.add(state.get(b).getName().charAt(u));
                    }
                    // System.out.println(thing);
                    truthTables.addRow(thing.toArray());
                    thing.clear();
                } else {
                    for (int v = 0; v < transition.size(); v++) {
                        String array2[] = transition.get(v).getInput().split("/");
                        for (int q = 0; q < array2.length; q++) {
                            if (transition.get(v).getfState().equals(state.get(b).getName()) && Integer.parseInt(ghf, 10) == Integer.parseInt(array2[q], 10)) {
                                //   System.out.println("Im not here" + ghf);
                                for (int u = 0; u < state.get(b).getName().length() - 1; u++) {
                                    thing.add(state.get(b).getName().charAt(u));
                                }
                                for (int u = 0; u < ghf.length(); u++) {
                                    thing.add(ghf.charAt(u));
                                }
                                for (int u = 0; u < transition.get(v).getnState().length() - 1; u++) {
                                    thing.add(transition.get(v).getnState().charAt(u));
                                }
                                truthTables.addRow(thing.toArray());
                                //   System.out.println(thing);
                                thing.clear();
                            }
                        }
                    }

                }
            }
            //    System.out.println(transition + " sadasasd ");

        }
        return truthTables;
    }

    public ArrayList<ArrayList<Integer>> findMinterms(JTable truthTable) {
        ArrayList<Integer> minTerms = new ArrayList<>();
        ArrayList<ArrayList<Integer>> numofD = new ArrayList<>();
        int index = 0;
        for (int iter = 0; iter < truthTable.getColumnCount(); iter++) {
            if (truthTable.getColumnName(iter).contains("D")) {
                index = iter;
                break;
            }
        }
        for (int b = index; b < truthTable.getColumnCount(); b++) {
            for (int a = 0; a < truthTable.getRowCount(); a++) {

                //      System.out.println(a + " sadad " + b);
                if (truthTable.getValueAt(a, b).equals('1')) {
                    minTerms.add(a);
                }
            }
            numofD.add(new ArrayList<Integer>(minTerms));
            minTerms.clear();
        }
        //   System.out.println(numofD + " zarir was ere");
        return numofD;
    }

    //start finding patricks
    public Set groupColumn(ArrayList<Integer> minTerms) {
        System.out.println(" rihgt here" + minTerms);
        int beginSize;
        ArrayList<String> newMint = new ArrayList<>();
        HashMap group1 = new HashMap();
        if (minTerms.size() == 1) {
            beginSize = 1;
        } else {
            beginSize = Integer.toBinaryString(minTerms.get(minTerms.size() - 1)).length();
        }
        for (int a = 0; a < minTerms.size(); a++) {
            String abc = Integer.toBinaryString(minTerms.get(a));
            String ghf = String.format("%0" + beginSize + "d", Integer.parseInt(abc));
            //newMint.add(ghf);
            int counter = 0;
            for (int b = 0; b < abc.length(); b++) {
                if (abc.charAt(b) == '1') {
                    counter++;
                }
            }
            group1.put(String.valueOf(ghf), minTerms.get(a));
        }
        HashMap group4 = new HashMap(groupTermsBinary2(group1));
        for (int a = 0; a < beginSize - 1; a++) {
            group4 = groupTermsBinary2(group4);
        }
        ArrayList<String> useless = essentialPrimes(group4);
        System.out.println(useless.size() + "  " + group4.size() + " " + useless + " " + group4.entrySet());
        HashMap group5 = new HashMap();
        System.out.println(group4.keySet());
        if (useless.isEmpty() == false && ifPetrick(useless, group4)) {
            for (int b = 0; b < useless.size(); b++) {
                Set set = group4.entrySet();
                Iterator x = set.iterator();
                while (x.hasNext()) {
                    Map.Entry me = (Map.Entry) x.next();
                    String array2[] = me.getValue().toString().split(",");
                    for (int c = 0; c < array2.length; c++) {
                        System.out.println(array2[c] + " x " + useless.get(b));
                        if (array2[c].equals(useless.get(b))) {
                            group5.put(me.getKey(), me.getValue());
                            x.remove();
                        }
                    }

                }
                minTerms.remove(new Integer(Integer.parseInt(useless.get(b))));
            }

            ArrayList<String> equations = new ArrayList<>();
            HashMap letters = new HashMap();
            Set set2 = group4.entrySet();
            Iterator x2 = set2.iterator();
            int counter = 0;
            while (x2.hasNext()) {
                Map.Entry me = (Map.Entry) x2.next();
                letters.put(me.getKey(), (char) (counter + 65));
                counter++;
            }
            System.out.println(" this is " + letters + containUseless(useless, group4) + useless + group4);
            for (Integer minTerm : minTerms) {
                String equat = "";
                Set set = group4.entrySet();
                Iterator x = set.iterator();
                while (x.hasNext()) {
                    Map.Entry me = (Map.Entry) x.next();
                    if (me.getValue().toString().contains(minTerm.toString()) && containUseless(useless, group4)) {
                        if (equat.length() == 1) {
                            equat = equat + "+" + letters.get(me.getKey());
                        } else {
                            equat = equat + letters.get(me.getKey());
                        }
                    }
                }
                equations.add(equat);
            }
            System.out.println(equations);
            String first = equations.get(0);
            ArrayList<String> newEq = new ArrayList<>();
            for (int a = 1; a < equations.size(); a++) {
                String temp = muiltplyEquation(simplifyEquationsbyRepeats(first), simplifyEquationsbyRepeats(equations.get(a)));
                temp = simplifyEquationsbyRepeats(temp);
                temp = simplifyEquationsbyRepeats2(temp);
                newEq.add(temp);
                first = temp;
            }
            String ar[] = newEq.get(newEq.size() - 1).split("\\+");
            String max = "";
            for(String g: ar){
            if(g.length() > max.length())
                max = g;
            }
            newEq.add(max);
            for (int a = 0; a < newEq.get(newEq.size() - 1).length(); a++) {
                Set set = letters.entrySet();
                Iterator x = set.iterator();
                while (x.hasNext()) {
                    Map.Entry me = (Map.Entry) x.next();
                    if (me.getValue().equals(newEq.get(newEq.size() - 1).charAt(a))) {
                        group5.put(me.getKey(), me.getValue());
                    }
                }
            }
            System.out.println(simplifyEquationbyRepeats3("ACBD + ABC + AB + EF"));
            System.out.println(newEq.get(newEq.size() - 1));
            return group5.keySet();
        }

        System.out.println(group4.keySet());
        return group4.keySet();
    }

    public String simplifyEquationsbyRepeats(String a) {
        String answer = "";
        ArrayList<String> lettes = new ArrayList<>();
        //use rules one char at a time and group them for matching? ya so ABC and BCD find common chars as a group between two
        String array2[] = a.split("\\+");
        for (int x = 0; x < array2.length; x++) {
            for (int y = 0; y < array2[x].length(); y++) {
                if (lettes.contains(Character.toString(array2[x].charAt(y))) == false) {
                    lettes.add(Character.toString(array2[x].charAt(y)));
                }
            }
        }
        for (String abc : lettes) {
            answer = answer + abc;
        }
        System.out.println(answer);
        return answer;
    }

    public String simplifyEquationsbyRepeats2(String a) {
        String answer = "";
        ArrayList<String> lettes = new ArrayList<>();
        //use rules one char at a time and group them for matching? ya so ABC and BCD find common chars as a group between two
        String array2[] = a.split("\\+");
        String c = "";
        for (String array22 : array2) {
            if (!lettes.contains(array22)) {
                answer = answer + array22 + "+";
                lettes.add(array22);
            }
        }
        System.out.println(answer.substring(0, answer.length() - 1));
        return answer.substring(0, answer.length() - 1);
    }

    public String muiltplyEquation(String a, String b) {
        String array2[] = a.split("\\+");
        String array3[] = b.split("\\+");
        String c = "";
        for (int x = 0; x < array2.length; x++) {
            for (int y = 0; y < array3.length; y++) {
                c = c + array2[x] + array3[y] + "+";
                System.out.println(array3[y]);
            }

            System.out.println(c);
        }
        System.out.println(c);
        System.out.println(a + " times " + b + " = " + c.substring(0, c.length() - 1) + "," + array2[0] + "," + array3[0]);
        return c.substring(0, c.length() - 1);
    }

    public String simplifyEquationbyRepeats3(String a) {
        String answer = "";
        String array2[] = a.split("\\+");
        for (int x = 0; x < array2.length; x++) {
            for (String array21 : array2) {
                
        System.out.println(array2[x]);
                if (array21.contains(array2[x])) {
                    answer = answer + array2[x] + "+";
                }
                else if(array2[x].contains(array21))
                    answer = answer + array2[x] + "+";
                else
                   answer = answer + array21 + "+";
            }
        }
        return answer;
    }

    public Boolean ifPetrick(ArrayList<String> useless, HashMap group3) {
        HashMap group4 = new HashMap(group3);
        for (int b = 0; b < useless.size(); b++) {
            Set set = group4.entrySet();
            Iterator x = set.iterator();
            while (x.hasNext()) {
                Map.Entry me = (Map.Entry) x.next();
                String array2[] = me.getValue().toString().split(",");

                for (int c = 0; c < array2.length; c++) {
                    if (array2[c].equals(useless.get(b))) {
                        System.out.println(array2[c] + " x");
                        x.remove();
                    }
                }
            }
        }
        System.out.println(" asdas " + group4.size());
        if (group4.size() == 0) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean ifContinue(HashMap group1) {
        Set set = group1.entrySet();
        HashMap unused = new HashMap(group1);
        Iterator x = set.iterator();
        HashMap group2 = new HashMap();
        ArrayList<HashMap> groups = new ArrayList<>();
        while (x.hasNext()) {
            Map.Entry me = (Map.Entry) x.next();
            Iterator y = set.iterator();
            while (y.hasNext()) {
                Map.Entry me2 = (Map.Entry) y.next();
                //     System.out.println("asdasdh hello ");
                //       System.out.println(me + "  " + me2);
                if (ifComparable(me2.getKey().toString(), me.getKey().toString())) {
                    //    group2.putIfAbsent(compareStrings(me2.getKey().toString(),me.getKey().toString()),me2.getValue().toString() + "," + me.getValue().toString());
                    return true;
                }
            }
        }

        return false;
    }

    public Boolean containUseless(ArrayList<String> useless, HashMap group3) {
        HashMap group4 = new HashMap(group3);
        for (int b = 0; b < useless.size(); b++) {
            Set set = group4.entrySet();
            Iterator x = set.iterator();
            while (x.hasNext()) {
                Map.Entry me = (Map.Entry) x.next();
                String array2[] = me.getValue().toString().split(",");

                for (int c = 0; c < array2.length; c++) {
                    if (array2[c].equals(useless.get(b))) {
                        System.out.println(array2[c] + " x");
                        x.remove();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Set unusedMinterms(HashMap group1) {
        HashMap group2 = new HashMap(group1);
        Set set = group1.entrySet();
        Iterator x = set.iterator();
        while (x.hasNext()) {
            Map.Entry me2 = (Map.Entry) x.next();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();

                int counter = -1;
                for (int l = 0; l < me.getKey().toString().length(); l++) {
                    if (me.getKey().toString().charAt(l) != me2.getKey().toString().charAt(l)) {
                        if (counter == -1) {
                            counter = l;
                        } else {
                            counter = -2;
                            break;
                        }
                    }
                }

                if (counter >= 0) {
                    group2.remove(me.getKey());
                    group2.remove(me2.getKey());
                }

            }
        }
        //System.out.println(group2);
        return group2.keySet();

    }

    public ArrayList<String> essentialPrimes(HashMap x) {
        ArrayList<String> essen = new ArrayList<>();
        ArrayList<Integer> all = new ArrayList<>();
        Set set = x.entrySet();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            Map.Entry me = (Map.Entry) iter.next();

            String[] minTerms = me.getValue().toString().split(",");

            for (int a = 0; a < minTerms.length; a++) {
                //    System.out.println(minTerms[a] + " asda ");
                if (essen.contains((minTerms[a])) == false) {
                    essen.add((minTerms[a]));
                } else {
                    essen.remove((minTerms[a]));
                }
            }
            //   System.out.println(all + " asdas");
            //all.add();
        }
        return essen;
    }

    public Set Petricks(HashMap x) {
        HashMap g2 = new HashMap();
        Iterator iter = x.entrySet().iterator();
        while (iter.hasNext()) {

        }
        return g2.keySet();
    }

    public String convertToBinary(int x, int beginSize) {
        String y = Integer.toBinaryString(x);
        return String.format("%0" + beginSize + "d", Integer.parseInt(y));
    }

    public HashMap groupTermsBinary2(HashMap group1) {
        Set set = group1.entrySet();
        HashMap unused = new HashMap(group1);
        Iterator x = set.iterator();
        HashMap group2 = new HashMap();
        ArrayList<HashMap> groups = new ArrayList<>();
        while (x.hasNext()) {
            Map.Entry me = (Map.Entry) x.next();
            Iterator y = set.iterator();
            while (y.hasNext()) {
                Map.Entry me2 = (Map.Entry) y.next();
                //     System.out.println("asdasdh hello ");
                //       System.out.println(me + "  " + me2);
                if (ifComparable(me2.getKey().toString(), me.getKey().toString())) {
                    group2.putIfAbsent(compareStrings(me2.getKey().toString(), me.getKey().toString()), me2.getValue().toString() + "," + me.getValue().toString());
                    unused.remove(me.getKey(), me.getValue());
                    unused.remove(me2.getKey(), me2.getValue());
                }
            }
        }
        for (int a = 0; a < unused.size(); a++) {
            group2.putAll(unused);
        }
        return group2;
    }

    public int numberofOnes(String x) {
        int r = 0;
        for (int a = 0; a < x.length(); a++) {
            if (x.charAt(a) == '1') {
                r++;
            }
        }
        return r;
    }

    public String compareStrings(String x, String y) {
        String z = "";
        for (int a = 0; a < x.length(); a++) {
            if (x.charAt(a) != y.charAt(a)) {
                z = z + '-';
            } else {
                z = z + x.charAt(a);
            }
        }
        return z;
    }

    public boolean ifComparable(String x, String y) {
        int counter = 0;
        for (int a = 0; a < x.length(); a++) {
            if (x.charAt(a) != y.charAt(a)) {
                counter++;
            }
        }

        if (counter != 1) {
            return false;
        }
        return true;
    }

    public HashMap groupTermsBinary(HashMap group1) {
        ArrayList<Object> key = new ArrayList<>();

        ArrayList<Object> value = new ArrayList<>();
        HashMap group2 = new HashMap();
        Set set = group1.entrySet();
        Iterator x = set.iterator();
        while (x.hasNext()) {
            Map.Entry me2 = (Map.Entry) x.next();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();

                boolean repeat = false;
                int counter = -1;
                for (int l = 0; l < me.getKey().toString().length(); l++) {
                    if (me.getKey().toString().charAt(l) != me2.getKey().toString().charAt(l)) {
                        if (counter == -1) {
                            counter = l;
                        } else {
                            counter = -2;
                            break;
                        }
                    }
                }
                if (counter >= 0) {
                    StringBuilder example = new StringBuilder(me.getKey().toString());
                    example.setCharAt(counter, '-');
                    /*  int counter2 = 0;
                    for(int b = 0; b < example.length(); b++){
                        if(example.charAt(b) == '1'){
                            counter2++;
                        }
                    } */
                    String array[];
                    String array2[];
                    array2 = me2.getValue().toString().split("/");
                    array = me.getValue().toString().split("/");
                    for (int s = 0; s < array.length; s++) {
                        for (int e = 0; e < array2.length; e++) {
                            if (array[s].equals(array2[e])) {
                                repeat = true;
                                //     System.out.println("sadasasjdhaskjh");
                            }
                        }
                    }

                    if (group2.containsKey(example) == false && repeat == false) {
                        group2.putIfAbsent(example, me.getValue().toString() + "/" + me2.getValue().toString());
                        key.add(me.getKey());
                        value.add(me.getValue());
                    } else {
                        key.add(me.getKey());
                        value.add(me.getValue());
                    }
                } else {
                    String array[];
                    String array2[];
                    array2 = me2.getValue().toString().split("/");
                    array = me.getValue().toString().split("/");
                    for (int s = 0; s < array.length; s++) {
                        for (int e = 0; e < array2.length; e++) {
                            if (array[s].equals(array2[e])) {
                                //     repeat = true;
                                //     System.out.println("sadasasjdhaskjh");
                            }
                        }
                    }
                    if (group2.containsKey(me.getKey()) == false && repeat == false) {
                        group2.putIfAbsent(me.getKey().toString(), me.getValue().toString());
                    }
                    if (group2.containsKey(me2.getKey()) == false && repeat == false) {
                        group2.putIfAbsent(me2.getKey().toString(), me2.getValue().toString());
                    }
                }

            }
        }
        for (int bv = 0; bv < key.size(); bv++) {
            group1.remove(key.get(bv));
        }
        key.clear();
        Set set2 = group2.entrySet();
        Iterator d = set2.iterator();
        while (d.hasNext()) {
            Map.Entry me2 = (Map.Entry) d.next();
            Iterator c = set2.iterator();
            while (c.hasNext()) {
                Map.Entry me = (Map.Entry) c.next();
                String array[];
                String array2[];
                array2 = me2.getValue().toString().split("/");
                array = me.getValue().toString().split("/");
                for (int s = 0; s < array.length; s++) {
                    for (int e = 0; e < array2.length; e++) {
                        if (array[s].equals(array2[e])) {
                            key.add(me.getKey());
                            //     System.out.println("sadasasjdhaskjh");
                        }
                    }
                }
            }
        }
        for (int v = 0; v < key.size(); v++) {
            // group2.remove(key.get(v));
        }
        if (group1.equals(group2)) {
            //     System.out.println("ghjfks");

            System.out.println(group2);
            return new HashMap();
        } else {
            System.out.println(group2);
            return group2;
        }
    }

    public HashMap groupTerms(HashMap group1) {
        Set set = group1.entrySet();
        Iterator x = set.iterator();
        ArrayList<HashMap> groups = new ArrayList<>();
        HashMap group2 = new HashMap();
        while (x.hasNext()) {
            Map.Entry me2 = (Map.Entry) x.next();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                if ((Integer) me.getValue() - (Integer) me2.getValue() == 1 && (Integer) me.getKey() > (Integer) me2.getKey()) {
                    int n = Math.abs((Integer) me.getKey() - (Integer) me2.getKey());
                    if ((n > 0) && ((n & (n - 1)) == 0)) {
                        int first, second;
                        if ((Integer) me.getKey() > (Integer) me2.getKey()) {
                            first = (Integer) me.getKey();
                            second = (Integer) me2.getKey();
                        } else {
                            first = (Integer) me2.getKey();
                            second = (Integer) me.getKey();
                        }
                        group2.put(Integer.toString(first) + "/" + Integer.toString(second), n);
                    }
                }
                //  groups.add(group2);
                //  group2.clear();
            }
        }

        /*   Set new2 = group2.entrySet();
    Iterator z = set.iterator();
    while(z.hasNext()){
        Map.Entry f = (Map.Entry)z.next();
    Iterator q = new2.iterator();
    while(q.hasNext()){
        Map.Entry f2 = (Map.Entry)q.next();
        String array2[];
        array2 = f2.getKey().toString().split("/");
        for(int y = 0; y < array2.length; y++){
            if(array2[y].equals(f.getKey().toString())){
                new2.remove(f.getValue());
            }
        }
    }
    }*/
        System.out.println(group2.entrySet());
        return group2;
    }

    public void main(String[] args) {

    }
}
