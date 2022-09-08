package base;

import java.util.Arrays;
import java.util.Random;

public class ExplorationPolicy implements IExplorationPolicy {
    private double eps;
    @Override
    public int ChooseAction(double[] actionEstimates) {
        Random r = new Random( );
        double rand = r.nextDouble();
        if(rand <= eps){
            return r.nextInt(actionEstimates.length);
        }else{
            int find = 0;
            double max = 0;
            for(int i = 0; i < actionEstimates.length; i++){
                if(actionEstimates[i] > max){
                    find = i;
                }
            }
            return find;
        }
    }

    public ExplorationPolicy() {
        this.eps = 0.4;
    }
}
