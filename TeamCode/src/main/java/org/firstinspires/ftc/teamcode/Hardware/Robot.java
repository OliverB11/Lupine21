package org.firstinspires.ftc.teamcode.Hardware;


import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Mecanum;


public class Robot {

    public Mecanum chassis;
    public Intake intake;

    public Robot(){
        initRobot();
    }

    public void initRobot(){
        chassis = new Mecanum();
        intake = new Intake();

    }
}
