package org.firstinspires.ftc.teamcode.Hardware.Sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class Color_Sensor {
    public static ColorSensor colorSensor;
    public static void init() {

        colorSensor = hardwareMap.get(com.qualcomm.robotcore.hardware.ColorSensor.class, "color");
    }
    public static double getColorSensorRed(){
        return colorSensor.red();
    }

    public static double getColorSensorBlue(){
        return colorSensor.blue();
    }

    public static double getColorSensorGreen(){return colorSensor.green();}

}
