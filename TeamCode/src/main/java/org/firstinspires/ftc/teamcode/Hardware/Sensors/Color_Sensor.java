package org.firstinspires.ftc.teamcode.Hardware.Sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class Color_Sensor {
    public ColorSensor colorSensor;
    public void init(String mapName) {

        colorSensor = hardwareMap.get(com.qualcomm.robotcore.hardware.ColorSensor.class, mapName);
    }
    public double getRed(){
        return colorSensor.red();
    }

    public double getBlue(){
        return colorSensor.blue();
    }

    public double getGreen(){return colorSensor.green();}

}
