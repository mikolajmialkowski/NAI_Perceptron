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

    public void learn(Node node, int correctAnswer){

        double net = 0;
        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // Calculate X * W
            net += node.getAttributesColumn().get(i) * this.vectorW.get(i);

        //net = d - theta

        int y = (net>=this.thetaThreshold?1:0);

        if (y != correctAnswer) { //Do learn
            //System.out.println("LEARN!");
            List<Double> vectorWPrime = new ArrayList<>(this.vectorW);
            for (int i = 0; i < node.getAttributesColumn().size(); i++) // W' = W + (Correct-Y) * Alpha * X
                vectorWPrime.set(i, (this.vectorW.get(i) + ((correctAnswer - y) * alpha * node.getAttributesColumn().get(i))));

            this.vectorW = vectorWPrime;
            this.thetaThreshold = thetaThreshold + (correctAnswer - y) * alpha * -1;
        }
    }

    public int evaluate(Node node){
        double net = 0;
        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // Calculate X * W
            net += node.getAttributesColumn().get(i) * this.vectorW.get(i);

        return (net>=this.thetaThreshold?1:0);
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "vectorW=" + vectorW +
                ", thetaThreshold=" + thetaThreshold +
                '}';
    }
}



