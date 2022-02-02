package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class Capper {
    public Servo capperServo;
    public boolean capperUp = false;

    public Capper(){
        capperServo = hardwareMap.get(Servo.class, "capperServo");
    }

    public void up(float placement){
        capperServo.setPosition(0.5 + (placement/20));
    }
    public void down(float placement){
        capperServo.setPosition(0.9 + (placement/20));
    }

    public void resting(){
        capperServo.setPosition(0);
    }
}
