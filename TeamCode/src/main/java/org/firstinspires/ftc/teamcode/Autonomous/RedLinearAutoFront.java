package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.ScoringMechanism;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Color_Sensor;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Distance_Sensor;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.DashConstants.Unfixed;
import org.firstinspires.ftc.teamcode.Z.Side;
import org.firstinspires.ftc.teamcode.Z.Vision.DuckPosition;


@Autonomous(name="RedLinearAutoFront", group="Autonomous Linear Opmode")
public class RedLinearAutoFront extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Mecanum robot;
    DuckWheel duckWheel;
    ScoringMechanism scorer;
    Intake intake;
    Color_Sensor flColor;
    Color_Sensor frColor;
    Color_Sensor blColor;
    Color_Sensor brColor;
    Distance_Sensor distance;




    public void initialize(){
        setOpMode(this);
        Side.red = true;
        Side.blue = false;
        robot = new Mecanum();
        duckWheel = new DuckWheel();
        intake = new Intake();
        scorer = new ScoringMechanism();
        flColor = new Color_Sensor();
        frColor = new Color_Sensor();
        blColor = new Color_Sensor();
        brColor = new Color_Sensor();
        distance = new Distance_Sensor();
        flColor.init("flColor");
        frColor.init("frColor");
        frColor.init("blColor");
        frColor.init("brColor");
        distance.init("distance");



        multTelemetry.addData("Status", "Initalized");
        multTelemetry.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){
        initialize();

        time.reset();
        MathUtils.wait(time, 5);


// DUCK ON LEFT

        if(DuckPosition.duckPos == 1) {
            multTelemetry.addData("Auto", "Red Front Left");



// DUCK IN MIDDLE

        }else if (DuckPosition.duckPos == 2){
            multTelemetry.addData("Auto", "Red Front Middle");


// DUCK ON RIGHT

        }else if(DuckPosition.duckPos == 3) {
            multTelemetry.addData("Auto", "Red Front Right");

// NO DUCK
        }else{
            multTelemetry.addData("Auto", "Red Front None");
        }

        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            //WRITE AUTOS HERE

            if(DuckPosition.duckPos == 3){
                //RIGHT
                //Top
                robot.strafe(.6, 1500,180, 70);
                scorer.autoTop();
                scorer.autoDeposit();
                robot.sleep(0.3, time);
                robot.strafe(.5,200,180,180);
                robot.strafe(.6,1250,270,225);
                robot.cycle(intake, scorer, flColor, frColor, blColor, brColor, distance);
                robot.strafe(.5,800,270,270);




            }else if(DuckPosition.duckPos == 2){
                //MIDDLE
                //Middle



            }else if(DuckPosition.duckPos == 1){
                //Left
                //Top



            }else{
                //NONE

            }





        }
    }
}
