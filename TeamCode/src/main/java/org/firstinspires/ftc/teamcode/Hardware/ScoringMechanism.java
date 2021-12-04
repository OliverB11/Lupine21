package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class ScoringMechanism {
    DcMotor spool;
    Servo bucket;
    public static ElapsedTime time = new ElapsedTime();
    public ScoringMechanism() {
        spool = hardwareMap.get(DcMotor.class, "spool");
        bucket = hardwareMap.get(Servo.class, "bucket");
    }

    public void spoolDown(){
        spool.setPower(0.3);
    }
    public void spoolUp(){
        spool.setPower(-0.3);
    }


    public void dump(){
        bucket.setPosition(1);
        time.reset();
        while(time.seconds()<3){
        }
        bucket.setPosition(0);
    }
}
