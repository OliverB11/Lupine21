package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class DuckWheel {
    CRServo duck1;
    CRServo duck2;
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



}
