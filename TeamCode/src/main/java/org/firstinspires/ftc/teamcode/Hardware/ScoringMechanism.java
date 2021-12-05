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
        spool.setTargetPosition(0);
        spool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        spool.setTargetPosition(0);
        spool.setPower(1);
    }

    public void top(){
        spool.setTargetPosition(-1900);
        armUp = true;
        while(time.seconds()<.2){}
        bucket.setPosition(0.7);
    }

    public void middle(){
        spool.setTargetPosition(-1000);
        armUp = true;
        while(time.seconds()<.2){}
        bucket.setPosition(0.7);
    }

    public void bottom(){
        spool.setTargetPosition(-500);
        armUp = true;
        while(time.seconds()<.2){}
        bucket.setPosition(0.7);
    }
    public void deposit(){
        bucket.setPosition(0.2);
        time.reset();
        while(time.seconds()<1){}
        bucket.setPosition(1);
        spool.setTargetPosition(0);
        armUp = false;
    }
}