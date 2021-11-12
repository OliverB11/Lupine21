package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class DuckWheel {
    CRServo duck1;
    CRServo duck2;
    public DuckWheel() {
        duck1 = hardwareMap.get(CRServo.class, "duck1");
        duck2 = hardwareMap.get(CRServo.class, "duck2");
    }
    public void spin(double speed){
        duck1.setPower(speed);
        duck2.setPower(speed);
    }
}
