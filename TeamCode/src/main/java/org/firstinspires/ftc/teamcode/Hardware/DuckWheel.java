package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Hardware.DuckWheel.wheelState.OFF;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import org.firstinspires.ftc.teamcode.Utilities.Unfixed;

public class DuckWheel {
    CRServo duck1;
    CRServo duck2;
    public enum wheelState {ON, OFF,}
    private wheelState duckState = OFF;
    public static ElapsedTime time = new ElapsedTime();

    public DuckWheel() {
        duck1 = hardwareMap.get(CRServo.class, "duck1");
        duck2 = hardwareMap.get(CRServo.class, "duck2");
    }

    public void redSpin(double speed){
        duck1.setPower(speed);
        duck2.setPower(speed);
    }
    public void blueSpin(double speed){
        duck1.setPower(-speed);
        duck2.setPower(-speed);
    }

    public void stop(){
        duck1.setPower(0);
        duck2.setPower(0);
    }

    public void redUpdate(){
        switch(duckState){

            case OFF:
                duck2.setPower(.5);
                duck1.setPower(.5);
                duckState = wheelState.ON;
                time.reset();
                break;

            case ON:
                if(time.seconds()> Unfixed.duckWheelTime) {
                    duck2.setPower(0);
                    duck1.setPower(0);
                    duckState = wheelState.OFF;
                }
                break;

        }
    }
    public void blueUpdate(){
        switch(duckState){

            case OFF:
                duck2.setPower(-0.5);
                duck1.setPower(-0.5);
                duckState = wheelState.ON;
                time.reset();
                break;

            case ON:
                if(time.seconds()> Unfixed.duckWheelTime) {
                    duck2.setPower(0);
                    duck1.setPower(0);
                    duckState = wheelState.OFF;
                }
                break;

        }
    }
}
