package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.opMode;

import org.firstinspires.ftc.teamcode.Utilities.Unfixed;

public class Intake {
    DcMotor intake;
    public boolean stopped;

    public Intake(){
        intake = hardwareMap.get(DcMotor.class, "intake");
        resetMotors();
    }

    void resetMotors(){
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setTargetPosition(0);
        intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        intake.setTargetPosition(0);
        intake.setPower(0.8);
    }

    public void spin(double speed){
        multTelemetry.addData("go","");
        multTelemetry.update();
        intake.setPower(speed);
        intake.setTargetPosition((int)(intake.getCurrentPosition()+Unfixed.intakeNumber));
        stopped = false;
    }
    public void backSpin(double speed){
        multTelemetry.addData("go","");
        multTelemetry.update();
        intake.setPower(speed);
        intake.setTargetPosition((int)(intake.getCurrentPosition()-Unfixed.intakeNumber));
        stopped = false;
    }

    public void stop(){
        multTelemetry.addData("stop","");
        multTelemetry.addData("current", intake.getCurrentPosition());
        multTelemetry.addData("Current % Unfixed" ,intake.getCurrentPosition() % Unfixed.intake);
        if(intake.getCurrentPosition() % Unfixed.intake > -10 && intake.getCurrentPosition() % Unfixed.intake < 10) {
            intake.setTargetPosition(intake.getCurrentPosition());
            multTelemetry.addData("current",intake.getCurrentPosition());
            multTelemetry.addData("target" ,intake.getTargetPosition());
            multTelemetry.update();
            stopped = true;
        }
    }
}
