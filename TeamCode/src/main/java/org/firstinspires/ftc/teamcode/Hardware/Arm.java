package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    Servo armServo;
    DcMotor slideMotor;

    public Arm() {
        slideMotor = hardwareMap.get(DcMotor.class, "slide");
        armServo = hardwareMap.get(Servo.class, "arm");
    }
    public void slideUp(){
        slideMotor.setPower(.6);
    }
    public void slideDown(){
        slideMotor.setPower(-.6);
    }
    public void slideStop(){
        slideMotor.setPower(0);
    }


    public void armUp(){
        armServo.setPosition(0);
    }
    public void armDown(){
        armServo.setPosition(.5);
    }
    public void armStop(){
        armServo.setPosition(1);
    }
}
