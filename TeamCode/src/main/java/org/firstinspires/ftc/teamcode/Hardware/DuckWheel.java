package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.CRServo;

import static org.firstinspires.ftc.teamcode.Hardware.DuckWheel.wheelState.OFF;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class DuckWheel {
    CRServo duck1;
    CRServo duck2;
    public enum wheelState {ON, OFF,}
    private wheelState duckState = OFF;
    public DuckWheel() {
        duck1 = hardwareMap.get(CRServo.class, "duck1");
        duck2 = hardwareMap.get(CRServo.class, "duck2");
    }

    public void spin(double speed){
        duck1.setPower(speed);
        duck2.setPower(speed);
    }
    public void update(boolean wheelOn){
        switch(duckState){

            case OFF:
                duck2.setPower(.5);
                duck1.setPower(.5);
                if(wheelOn){
                    duckState = wheelState.ON;
                }

             break;
            case ON:
                duck2.setPower(0);
                duck1.setPower(0);
                if(wheelOn){
                    duckState = wheelState.OFF;
                }
                break;

        }
    }
}
