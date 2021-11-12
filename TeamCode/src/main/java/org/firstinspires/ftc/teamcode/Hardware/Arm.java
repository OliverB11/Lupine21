package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Arm {
    CRServo armServo;
    DcMotor slideMotor;

    public Arm() {
        slideMotor = hardwareMap.get(DcMotor.class, "slide");
        armServo = hardwareMap.get(CRServo.class, "arm");
    }
    public void slideUp(){
        slideMotor.setPower(.1);
    }
    public void slideDown(){
        slideMotor.setPower(-.1);
    }
    public void slideStop(){
        slideMotor.setPower(0);
    }


    public void armUp(){
        armServo.setPower(.1);
    }
    public void armDown(){
        armServo.setPower(-.1);
    }
    public void armStop(){
        armServo.setPower(0);
    }
}
