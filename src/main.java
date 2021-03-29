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

            int n=0;
            HashMap<String,Integer> answerMap = new HashMap<>();
            for (Node node : nodeTrainList) {
                if (!answerMap.containsKey(node.getNodeClassName()))
                    answerMap.put(node.getNodeClassName(),n++);

                if (answerMap.size()==2)
                    break;
            }

            n=0;
            String[] keyArray = new String[2];
            for (String key :answerMap.keySet())
                keyArray[n++]=key;

            Collections.shuffle(nodeTrainList);
            Perceptron perceptron = new Perceptron(nodeTestList.get(0).getAttributesColumn().size(),alpha);
            for (Node value : nodeTrainList)
                perceptron.learn(value, answerMap.get(value.getNodeClassName()));

            int numberOfCorrectAnswerOfFirstClass = 0;
            int numberOfCorrectAnswerOfSecondClass = 0;
            int numberOfAppearancesOfFirstClass = 0;
            int numberOfAppearancesOfSecondClass = 0;

            for (Node node : nodeTestList) {
                int y = perceptron.evaluate(node);

                if (answerMap.get(node.getNodeClassName()) == 0)
                    numberOfAppearancesOfFirstClass++;

                if (answerMap.get(node.getNodeClassName()) == 1)
                    numberOfAppearancesOfSecondClass++;


                if (answerMap.get(keyArray[0]) == y && y == answerMap.get(node.getNodeClassName()))
                    numberOfCorrectAnswerOfFirstClass++;

                if (answerMap.get(keyArray[1]) == y && y == answerMap.get(node.getNodeClassName()))
                    numberOfCorrectAnswerOfSecondClass++;
            }

            System.out.println("Accuracy for "+ keyArray[0]+ ": "+ ((double) numberOfCorrectAnswerOfFirstClass/numberOfAppearancesOfFirstClass)*100+"%");
            System.out.println("Accuracy for "+ keyArray[1]+ ": "+ (double) numberOfCorrectAnswerOfSecondClass/numberOfAppearancesOfSecondClass*100+"%");
            System.out.println("Total accuracy: "+ (double) (numberOfCorrectAnswerOfFirstClass+numberOfCorrectAnswerOfSecondClass)/nodeTestList.size()*100+"%");
            System.out.println("Final Vector: "+perceptron.getVectorW()+ " THETA THRESHOLD: "+ perceptron.getThetaThreshold());
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
