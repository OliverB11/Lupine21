package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

public class ScoringMechanism {
    public DcMotor spool;
    public Servo bucket;
    public static ElapsedTime time = new ElapsedTime();
    public ScoringMechanism() {
        spool = hardwareMap.get(DcMotor.class, "spool");
        bucket = hardwareMap.get(Servo.class, "bucket");
        resetMotors();

    }

//    public void spoolDown(){
//        spool.setPower(0.5);
//    }
//    public void spoolUp(){
//        spool.setPower(-0.5);
//    }
//    public void spoolStop(){spool.setPower(0);}
//
//    public void dump(){
//        bucket.setPosition(0.2);
//        time.reset();
//
//        while(time.seconds()<1){
//        }
//        bucket.setPosition(1);
//    }
//}
    public void resetMotors(){
        spool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        spool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        spool.setTargetPosition(0);
        spool.setPower(.2);
        spool.getCurrentPosition();
    }

    public void top(){
        spool.setTargetPosition(-2128);
        while(spool.isBusy()){
            multTelemetry.addData("Encoder Pos", spool.getCurrentPosition());
            multTelemetry.update();
        }
        bucket.setPosition(1);
        while(time.seconds()<2){}
        bucket.setPosition(0);
        spool.setTargetPosition(0);
    }

    public void middle(){
        spool.setTargetPosition(-1000);
        while(spool.isBusy()){ }
        bucket.setPosition(1);
        while(time.seconds()<2){}
        bucket.setPosition(0);
        spool.setTargetPosition(0);
    }

    public void bottom(){
        spool.setTargetPosition(-500);
        while(spool.isBusy()){ }
        bucket.setPosition(1);
        while(time.seconds()<2){}
        bucket.setPosition(0);
        spool.setTargetPosition(0);
    }
}