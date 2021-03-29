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

        if (y == correctAnswer) // NIE DOSZLO DO NAUKI PERCEPTRONU (vector W jest optymalny?)
            return true;

        // (CorrectAnswer != Y) --> start learning

        List<Double> vectorWPrime = new ArrayList<>(this.vectorW);
        double thetaThresholdPrime = this.thetaThreshold;


        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // W' = W + (1-0) * alpha * X
            vectorWPrime.set(i, (vectorWPrime.get(i) + ((correctAnswer-y)*alpha*node.getAttributesColumn().get(i))) );

        thetaThresholdPrime = thetaThreshold + (correctAnswer-y) * alpha * -1;

        this.vectorW = vectorWPrime;
        this.thetaThreshold = thetaThresholdPrime;

        return false;
    }

    public int evaluate(Node node){

        double net = 0;

        if (node.getAttributesColumn().size()!= vectorW.size())
            System.err.println("Rożne dlogosci wektorów!");

        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // Obliczenie X * W
            net += node.getAttributesColumn().get(i) * this.vectorW.get(i);

        return  net >= this.thetaThreshold?1:0;

    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "vectorW=" + vectorW +
                ", thetaThreshold=" + thetaThreshold +
                '}';
    }
}



