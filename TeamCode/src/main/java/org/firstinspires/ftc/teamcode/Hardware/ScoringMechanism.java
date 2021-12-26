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
        bucket.setPosition(0.85);
        resetMotors();

    }

    public void resetMotors() {
        spool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spool.setTargetPosition(0);
        spool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        spool.setTargetPosition(0);
        spool.setPower(0.8);


    }

    public void top() {

        armUp = true;
        if(time.seconds() > 0 && time.seconds() < .1){
            bucket.setPosition(0.8);
        }
        if(time.seconds() > 0.2 && time.seconds() < .5){
            spool.setTargetPosition(-300);
        }
        if (time.seconds() > .5 && time.seconds() < .7 ) {
            bucket.setPosition(0.75);
        }
        if (time.seconds() > .7 && time.seconds() < 1) {
            spool.setTargetPosition(-1900);
            spool.setPower((spool.getTargetPosition() - spool.getCurrentPosition()) / (double) spool.getTargetPosition() + 0.2);
        }
    }


    public void middle() {


        armUp = true;
        if(time.seconds() > 0 && time.seconds() < .1){
            bucket.setPosition(0.8);
        }
        if(time.seconds() > 0.2 && time.seconds() < .5){
            spool.setTargetPosition(-300);
        }
        if (time.seconds() > .5 && time.seconds() < .7 ) {
            bucket.setPosition(0.75);
        }
        if (time.seconds() > .7 && time.seconds() < 1) {
            spool.setTargetPosition(-1000);
            spool.setPower((spool.getTargetPosition() - spool.getCurrentPosition()) / (double) spool.getTargetPosition() + 0.2);
        }
    }

    public void bottom() {

        armUp = true;
        if(time.seconds() > 0 && time.seconds() < .1){
            bucket.setPosition(0.8);
        }
        if(time.seconds() > 0.2 && time.seconds() < .5){
            spool.setTargetPosition(-300);
        }
        if (time.seconds() > .5 && time.seconds() < .7 ) {
            bucket.setPosition(0.75);
        }
        if (time.seconds() > .7 && time.seconds() < 1) {
            spool.setTargetPosition(-500);
            spool.setPower((spool.getTargetPosition() - spool.getCurrentPosition()) / (double) spool.getTargetPosition() + 0.2);
        }
    }

    public void deposit() {
        if (time.seconds() < .5) {
            bucket.setPosition(0);
        }
        if(time.seconds() > 1 && time.seconds() < 1.5){
            bucket.setPosition(0.5);
        }
        if (time.seconds() > 2 && time.seconds() < 2.2) {
            spool.setPower(1);
            spool.setTargetPosition(-800);
        }
        if (time.seconds() > 2.2 && time.seconds() < 2.5) {
            bucket.setPosition(0.77);
        }
        if (time.seconds() > 2.5 && time.seconds() < 3) {
            spool.setTargetPosition(0);
        }
        if (time.seconds() > 3 && time.seconds() < 3.1) {
            bucket.setPosition(0.85);
            armUp = false;
            }
        }
    }
