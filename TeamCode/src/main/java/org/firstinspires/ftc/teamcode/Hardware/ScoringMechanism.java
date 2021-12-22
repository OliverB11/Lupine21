package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

import org.firstinspires.ftc.teamcode.Utilities.Unfixed;

public class ScoringMechanism {
    public DcMotor spool;
    public Servo bucket;
    public static ElapsedTime time = new ElapsedTime();
    public boolean armUp = false;
    public ScoringMechanism() {
        spool = hardwareMap.get(DcMotor.class, "spool");
        bucket = hardwareMap.get(Servo.class, "bucket");
        resetMotors();

    }

    public void resetMotors(){
        spool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spool.setTargetPosition(0);
        spool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        spool.setTargetPosition(0);
        spool.setPower(1);
    }

    public void top(){
        armUp = true;
        bucket.setPosition(0.7);
        if(time.seconds()>.2) {
            spool.setTargetPosition(-1700);
            spool.setPower((spool.getTargetPosition()- spool.getCurrentPosition())/ (double) spool.getTargetPosition()+0.1);
        }
    }


    public void middle(){
            armUp = true;
            bucket.setPosition(0.7);
            if(time.seconds()>.2) {
                spool.setTargetPosition(-1000);
                spool.setPower((spool.getTargetPosition()- spool.getCurrentPosition())/ (double) spool.getTargetPosition()+0.1);
            }

    }

    public void bottom(){
        armUp = true;
        bucket.setPosition(0.7);
        if(time.seconds()>.2) {
            spool.setTargetPosition(-500);
            spool.setPower((spool.getTargetPosition()- spool.getCurrentPosition())/ (double) spool.getTargetPosition()+0.1);
        }

    }
    public void deposit(){
        bucket.setPosition(0.2);
        if(time.seconds()>1&&time.seconds()<3) {
            bucket.setPosition(0.8);
            spool.setPower(1);
            spool.setTargetPosition(0);
            if (time.seconds() > 3) {
                bucket.setPosition(1);
                armUp = false;
            }
        }
    }
}