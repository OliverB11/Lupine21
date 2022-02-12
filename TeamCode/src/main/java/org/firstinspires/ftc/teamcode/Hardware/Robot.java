package org.firstinspires.ftc.teamcode.Hardware;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.Gyro;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Capper;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.ScoringMechanism;
import org.firstinspires.ftc.teamcode.Z.Vision.Camera;
import org.firstinspires.ftc.teamcode.Z.Vision.DetectionPipeline;

public class Robot {
    public Capper capper;
    public Mecanum chassis;
    public ScoringMechanism scorer;
    public Intake intake;
    public DuckWheel duckWheel;

    public Robot(){
        initRobot();
    }

    public void initRobot(){
        chassis = new Mecanum();
        scorer = new ScoringMechanism();
        intake = new Intake();
        duckWheel = new DuckWheel();

    }
}
