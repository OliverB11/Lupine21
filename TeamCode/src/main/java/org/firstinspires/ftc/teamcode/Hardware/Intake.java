package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class Intake {
    DcMotor intake;

    public Intake(){
        intake = hardwareMap.get(DcMotor.class, "intake");
    }

    public void spin(double speed){
        intake.setPower(speed);
    }
}
