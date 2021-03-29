//s20635 Mikolaj Mialkowski C22 NAI lab(2)
//zakladam poprawnosc danych wejciowych (prawidlowy i staly rozmiar wektora)

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {

        List<Node> nodeTrainList;
        List<Node> nodeTestList;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Pass A:");
        double alpha = Double.parseDouble(bufferedReader.readLine());
        System.out.println("Pass train-set:");
        String trainSetAddress = bufferedReader.readLine();
        System.out.println("Pass test-set: [type \"MY OWN VECTOR\", if you want to pass custom vector]");
        String testSetAddress = bufferedReader.readLine();

        nodeTrainList = getNodeList(trainSetAddress);

        if (testSetAddress.equals("MY OWN VECTOR")) {
            while (true) {
                System.out.println("Pass correct vector [split with \";\"]");

                String line = bufferedReader.readLine();
                line += ";[NO LABEL]";
                List<Node> nodeSet = new ArrayList<>();
                String[] tmp = line.split(";");
                List<Double> attributesColumn = new ArrayList<>();


            }
        }
        else{
            nodeTestList = getNodeList(testSetAddress);

            HashMap<String,Integer> answerMap = new HashMap<>();

            int n=0;
            for (Node node : nodeTrainList) {
                if (!answerMap.containsKey(node.getNodeClassName()))
                    answerMap.put(node.getNodeClassName(),n++);

                if (answerMap.size()==2)
                    break;
            }

            System.out.println("ANSWER KEY: ");
            for (String key :answerMap.keySet())
                System.out.println("KEY =  "+ key +" "+ answerMap.get(key));


            Collections.shuffle(nodeTrainList);

            Perceptron perceptron = new Perceptron(nodeTestList.get(0).getAttributesColumn().size(),alpha);
            for (int i = 0; i < nodeTrainList.size() ; i++) {

                //System.out.println();
               //System.out.println(perceptron);
                perceptron.learn(nodeTrainList.get(i),answerMap.get(nodeTrainList.get(i).getNodeClassName()));

            }

            for (int i = 0; i < nodeTestList.size() ; i++) {
                int y = perceptron.evaluate(nodeTestList.get(i));
                System.out.println("Y = " + y+ " CORRECT ANSWER = "+ answerMap.get(nodeTestList.get(i).getNodeClassName()));
            }










        }
    }

    public static List<Node> getNodeList(String fileAddress) throws IOException {
        String line;
        List<Node> nodeSet = new ArrayList<>();

        FileReader fileReader = new FileReader(fileAddress);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine())!=null && (!line.equals(""))){
            String [] tmp = line.split(";");

            List<Double> attributesColumn = new ArrayList<>(); // JELISLI BLAD TUTAJ TO USUNAC OSTATNI PUSTY WIERSZ
            for (int i = 0; i < tmp.length-1 ; i++)
                attributesColumn.add(Double.parseDouble(tmp[i]));

            nodeSet.add(new Node(attributesColumn,tmp[tmp.length-1]));
        }
        return nodeSet;
    }
}
