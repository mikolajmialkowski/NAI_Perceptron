//s20635 Mikolaj Mialkowski C22 NAI lab(2)
//zakladam poprawnosc danych wejciowych (prawidlowy i staly rozmiar wektora)

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {

        List<Node> nodeTrainList;
        List<Node> nodeTestList;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Pass K:");
        int k = Integer.parseInt(bufferedReader.readLine());
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
