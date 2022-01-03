package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.opMode;

import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;

public class Intake {
    public DcMotor intake;
    public ElapsedTime time = new ElapsedTime();

    public Intake(){
        intake = hardwareMap.get(DcMotor.class, "intake");
    }


    public void spin() {
        if(time.seconds() > 0.3 && time.seconds() < 0.4)
        intake.setPower(0.5);


    }

    public void backSpin(){
        if(time.seconds() > 0.3 && time.seconds() < 0.4)
        intake.setPower(-0.5);
    }

    public void stop(){
        intake.setPower(0);
    }

    public void autoSpin(){
        intake.setPower(0.5);
    }

}


