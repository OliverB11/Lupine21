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
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;
import org.firstinspires.ftc.teamcode.Z.Side;
import org.firstinspires.ftc.teamcode.Z.Vision.BlueDuckPosition;
import org.firstinspires.ftc.teamcode.Z.Vision.RedDuckPosition;


@Autonomous(name="RedLinearAutoFront", group="Autonomous Linear Opmode")
public class RedLinearAutoFront extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Mecanum robot;
    PID pid;
    DuckWheel duckWheel;


    public void initialize(){
        setOpMode(this);
        Side.red = true;
        Side.blue = false;
        pid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.integralWeight);
        robot = new Mecanum();
        duckWheel = new DuckWheel();
        ElapsedTime time = new ElapsedTime();


        multTelemetry.addData("Status", "Initalized");
        multTelemetry.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){
        initialize();

        time.reset();
        while(time.seconds()<5){

        }

// DUCK ON LEFT

        if(RedDuckPosition.duckOnLeft) {
            multTelemetry.addData("Auto", "Red Front Left");

            //Ivan, MathUtils.convertInches2Ticks() and MathUtils.centimeters2Ticks() are both useful
            //You can also make distances config values in Unfixed and that way you don't have to push everytime
            //Currently you're goals are to first drop the preloaded freight, then do the duckspinner, then do cycles, then park fully in the warehouse facing towards the middle of the feild.


// DUCK IN MIDDLE

        }else if (RedDuckPosition.duckInMiddle){
            multTelemetry.addData("Auto", "Red Front Middle");

// DUCK ON RIGHT

        }else if(RedDuckPosition.duckOnRight) {
            multTelemetry.addData("Auto", "Red Front Right");
// NO DUCK
        }else{
            multTelemetry.addData("Auto", "Red Front None");
        }




        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){




            }
        }
    }
