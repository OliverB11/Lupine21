package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class Capper {
    public CRServo capperServo;
    public boolean capperUp = false;

    public Capper(){
        capperServo = hardwareMap.get(CRServo.class, "capperServo");
    }

    public void down(float speed){
        capperServo.setPower(speed);
    }
    public void up(float speed){
        capperServo.setPower(-speed);
    }
}
