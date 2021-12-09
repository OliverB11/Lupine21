package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.ScoringMechanism;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;
import org.firstinspires.ftc.teamcode.Z.Side;
import org.firstinspires.ftc.teamcode.Z.Vision.BlueDuckPosition;


@Autonomous(name="BlueLinearAutoFront", group="Autonomous Linear Opmode")
public class BlueLinearAutoFront extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Mecanum robot;
    PID pid;
    DuckWheel duckWheel;
    ScoringMechanism scorer;
    private String duckPos = "";


    public void initialize(){
        setOpMode(this);
        Side.red = false;
        Side.blue = true;
        pid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.integralWeight);
        robot = new Mecanum();
        duckWheel = new DuckWheel();
        scorer = new ScoringMechanism();
        ElapsedTime time = new ElapsedTime();



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

        if(BlueDuckPosition.duckOnLeft) {
            multTelemetry.addData("Auto", "Blue Front Left");

            duckPos = "Left";
            //Ivan, MathUtils.convertInches2Ticks() and MathUtils.centimeters2Ticks() are both useful
            //You can also make distances config values in Unfixed and that way you don't have to push everytime
            //Currently you're goals are to first drop the preloaded freight, then do the duckspinner, then do cycles, then park fully in the warehouse facing towards the middle of the feild.

// DUCK IN MIDDLE

        }else if (BlueDuckPosition.duckInMiddle){
            multTelemetry.addData("Auto", "Blue Front Middle");
            duckPos = "Middle";
            multTelemetry.update();
// DUCK ON RIGHT

        }else if(BlueDuckPosition.duckOnRight) {
            multTelemetry.addData("Auto", "Blue Front Right");
            duckPos = "Right";
// NO DUCK
        }else{
            multTelemetry.addData("Auto", "Blue Front None");
            duckPos = "None";
        }




        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            robot.gyro.reset();

            if(duckPos == "Middle") {
                robot.strafe(0.5, 1690, 0, 130);

                robot.turn(0.4,180,6);
                scorer.middle();
                scorer.deposit();
                robot.strafe(0.5,Unfixed.distanceTest1,0,80);



            } else if(duckPos == "Left"){
                
            }

            } else if(duckPos == "Right"){

        }
    }
}
