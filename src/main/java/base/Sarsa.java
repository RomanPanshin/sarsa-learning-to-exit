package base;

import base.IExplorationPolicy;

import java.util.Random;

public class Sarsa {
    private int states;
    private int actions;
    private double[][] qvalues;
    private IExplorationPolicy explorationPolicy;


    private double discountFactor = 0.95;
    private double learningRate = 0.25;

    public float[] getQvaluesSum(){
        float[] result = new float[states];
        for ( int i = 0; i < this.states; i++ ){
           float sum = 0;
           for ( int j = 0; j < this.actions; j++ ) {
               sum += this.qvalues[i][j];
           }
           result[i] = sum;
        }
        return result;
    }

    public void setQvalues(double[][] qvalues) {
        this.qvalues = qvalues;
        System.out.println(this.qvalues);
    }

    public double[][] getQvalues() {
        return qvalues;
    }

    public int getStates() {
        return states;
    }

    public int getActions() {
        return actions;
    }

    public IExplorationPolicy getExplorationPolicy() {
        return explorationPolicy;
    }

    public void setExplorationPolicy(IExplorationPolicy explorationPolicy) {
        this.explorationPolicy = explorationPolicy;
    }

    public void setlearningRate(double learningRate) {
        this.learningRate = Math.max(0.0, Math.min(1.0, learningRate));
    }

    public double getLearningRate() {
        return learningRate;
    }

    public int GetAction( int state ){
        return explorationPolicy.ChooseAction( qvalues[state] );
    }

    public void UpdateState( int previousState, int previousAction, double reward, int nextState, int nextAction ){
        // previous state's action estimations
        double[] previousActionEstimations = qvalues[previousState];
        // update expexted summary reward of the previous state
        previousActionEstimations[previousAction] *= ( 1.0 - learningRate );
        previousActionEstimations[previousAction] += ( learningRate * ( reward + discountFactor *
                qvalues[nextState][nextAction] ) );

    }

    public Sarsa( int states, int actions, IExplorationPolicy explorationPolicy, boolean randomize ){
        this.states = states;
        this.actions = actions;
        this.explorationPolicy = explorationPolicy;

        // create Q-array
        qvalues = new double[states][];
        for ( int i = 0; i < states; i++ ){
            qvalues[i] = new double[actions];
        }

        // do randomization
        if ( randomize ){
            Random r = new Random( );

            for ( int i = 0; i < states; i++ ){
                for ( int j = 0; j < actions; j++ ){
                    qvalues[i][j] = r.nextDouble() / 10;
                }
            }
        }
    }
}
