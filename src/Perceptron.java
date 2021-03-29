import java.util.ArrayList;
import java.util.List;

public class Perceptron {
   private List<Double> vectorW;
   private double thetaThreshold;
   private double alpha;



    public Perceptron(int vectorSize, double alpha) {
        this.alpha = alpha;
        this.vectorW = new ArrayList<>();
        for (int i = 0; i < vectorSize ; i++) // from -5 to 5 as starting values
            this.vectorW.add((Math.random()*10)-5);

        this.thetaThreshold = Math.random()*10-5;



        /*vectorW.set(0,5d);
        vectorW.set(1,d5d);
        vectorW.set(2,5d);
        vectorW.set(3,5d);

        thetaThreshold=0.05;*/

    }

    public List<Double> getVectorW() {
        return vectorW;
    }

    public void setVectorW(List<Double> vectorW) {
        this.vectorW = vectorW;
    }

    public double getThetaThreshold() {
        return thetaThreshold;
    }

    public void setThetaThreshold(double thetaThreshold) {
        this.thetaThreshold = thetaThreshold;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public boolean learn(Node node, int correctAnswer){
        double net = 0;

        if (node.getAttributesColumn().size()!= vectorW.size())
            System.err.println("Rożne dlogosci wektorów!");

        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // Obliczenie X * W
            net += node.getAttributesColumn().get(i) * this.vectorW.get(i);

        int y = net >= this.thetaThreshold?1:0;

        //System.out.println("NET = "+ net);

        if (y == correctAnswer) { // NIE DOSZLO DO NAUKI PERCEPTRONU (vector W jest optymalny?)
            //System.out.println("Y = "+ y + " CORRECT-ANSWER = "+ correctAnswer + " BRAK NAUKI -----------------------------------");
            return true;

        }



        // (CorrectAnswer != Y) --> start learning
        else {
            //System.out.println("Y = "+ y + " CORRECT-ANSWER = "+ correctAnswer + " ROZPOCZATO NAUKE +++++++++++++++++++++++++++++++++");

            List<Double> vectorWPrime = new ArrayList<>(this.vectorW);

            for (int i = 0; i < node.getAttributesColumn().size(); i++) // W' = W + (1-0) * alpha * X
                vectorWPrime.set(i, (this.vectorW.get(i) + ((correctAnswer - y) * alpha * node.getAttributesColumn().get(i))));

            double thetaThresholdPrime = thetaThreshold + (correctAnswer - y) * alpha * -1;

            this.vectorW = vectorWPrime;
            this.thetaThreshold = thetaThresholdPrime;

            return false;
        }
    }

    public int evaluate(Node node){

        double net = 0;

        if (node.getAttributesColumn().size()!= vectorW.size())
            System.err.println("Rożne dlogosci wektorów!");

        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // Obliczenie X * W
            net += node.getAttributesColumn().get(i) * this.vectorW.get(i);

        //System.out.println("THRESHOLD "+thetaThreshold);
        //System.out.println("NET "+net);

        if (net>=this.thetaThreshold)
            return 1;

        return 0;


    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "vectorW=" + vectorW +
                ", thetaThreshold=" + thetaThreshold +
                '}';
    }
}



