package org.firstinspires.ftc.teamcode.Utilities;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

public class PID {


    private double proportionalWeight;
    private double integralWeight;
    private double derivativeWeight;

    private double integralSum = 0;


    private double previousError = 0;
    private long previousTime = System.currentTimeMillis();





    public PID(double proportional, double integral, double derivative){
        this.proportionalWeight = proportional;
        this.integralWeight = integral;
        this.derivativeWeight = derivative;
    }


    public double update(double error) {

        integralSum += error;

        double deltaTime = (System.currentTimeMillis() - previousTime) / 1000.0;
        double deltaError = error - previousError;
        double rateOfChange = deltaError / deltaTime;

        previousError = error;
        previousTime = System.currentTimeMillis();

        double pComponent = error * proportionalWeight;
        double iComponent = integralSum * integralWeight;
        double dComponent = rateOfChange * derivativeWeight;


        return pComponent + iComponent + dComponent;
    }






    public double update(double error, boolean isTuning){

        integralSum += error;

        double deltaTime = (System.currentTimeMillis() - previousTime)/1000.0;
        double deltaError = error - previousError;
        double rateOfChange = deltaError/deltaTime;

        previousError = error;
        previousTime = System.currentTimeMillis();

        double pComponent = error * Unfixed.proportionalWeight;
        double iComponent = integralSum * Unfixed.integralWeight;
        double dComponent = rateOfChange * Unfixed.derivativeWeight;

        multTelemetry.addData("P",pComponent);
        multTelemetry.addData("I",iComponent);
        multTelemetry.addData("D",dComponent);


        return pComponent + iComponent + dComponent;
    }











}
