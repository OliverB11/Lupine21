package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

public class Intake {
    public DcMotor intake;
    public ElapsedTime time = new ElapsedTime();
    double updateIteration = 19;


    public Intake(){
        intake = hardwareMap.get(DcMotor.class, "intake");
        resetMotors();
    }
    public void resetMotors(){
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    double mostRecentAngle;
    double currentAngle;
    public boolean isMoving;

    public void updateEncoders(){
        updateIteration ++;
        if(updateIteration == 20){
            updateIteration = 0;
            currentAngle = intake.getCurrentPosition();
            isMoving = Math.abs(mostRecentAngle - currentAngle) > 2;
            mostRecentAngle = currentAngle;
        }
    }

    public void spin() {
        if(time.seconds() > 0.3) {
            intake.setPower(0.5);
        }


    }

    public void backSpin(){
        if(time.seconds() > 0.3) {
            intake.setPower(-1);
        }
    }

    public void stop(){
        intake.setPower(0);
    }

    public void autoSpin(){
        intake.setPower(0.5);
    }

    public void autoBackSpin(){intake.setPower(-1);}

    public boolean jammed(){
        if(!isMoving && intake.getPower() != 0){
            return(true);
        }else{
            return(false);
        }
    }

}


