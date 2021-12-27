package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.opMode;

import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;

public class Intake {
    public DcMotor intake;
    public boolean stopped;

    public Intake(){
        intake = hardwareMap.get(DcMotor.class, "intake");
//        resetMotors();
    }

//    void resetMotors(){
//        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        intake.setTargetPosition(0);
//        intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        intake.setPower(1);
//        intake.setTargetPosition(0);
//    }

//    public void spin(double speed){
//        multTelemetry.update();
//        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        intake.setPower(speed);
//        stopped = false;
//    }
//    public void backSpin(double speed){
//        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        intake.setPower(speed);
//        stopped = false;
//    }
//
//    public void stop(){
//        multTelemetry.addData("Current % Unfixed" ,intake.getCurrentPosition() % Unfixed.intakePosition);
//        multTelemetry.update();
//        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
////        if(intake.getCurrentPosition() % Unfixed.intake > -10 && intake.getCurrentPosition() % Unfixed.intake < 10) {
////            intake.setTargetPosition(intake.getCurrentPosition());
////            stopped = true;
////        }else{
////            intake.setTargetPosition((int)(intake.getCurrentPosition()+Unfixed.intakeNumber));
////        }
//
//
//        intake.setTargetPosition((int)MathUtils.closestAngle(Unfixed.intakePosition, intake.getCurrentPosition()));
//        stopped = true;
//    }

    public void spin() {
//        intake.setTargetPosition(intake.getCurrentPosition() + Unfixed.intakePosition);
        intake.setPower(1);


    }

    public void backSpin(){
//        intake.setTargetPosition(intake.getCurrentPosition() - Unfixed.intakePosition);
    }
}
