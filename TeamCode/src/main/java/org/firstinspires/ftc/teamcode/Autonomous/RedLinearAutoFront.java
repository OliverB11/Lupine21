package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.ScoringMechanism;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
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
    ScoringMechanism scorer;
    Intake intake;
    private String duckPos = "";



    public void initialize(){
        setOpMode(this);
        Side.red = true;
        Side.blue = false;
        pid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.integralWeight);
        robot = new Mecanum();
        duckWheel = new DuckWheel();
        intake = new Intake();
        scorer = new ScoringMechanism();


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

        if(RedDuckPosition.duckOnLeft) {
            multTelemetry.addData("Auto", "Red Front Left");
            duckPos = "Left";


// DUCK IN MIDDLE

        }else if (RedDuckPosition.duckInMiddle){
            multTelemetry.addData("Auto", "Red Front Middle");
            duckPos = "Middle";

// DUCK ON RIGHT

        }else if(RedDuckPosition.duckOnRight) {
            multTelemetry.addData("Auto", "Red Front Right");
            duckPos = "Right";
// NO DUCK
        }else{
            multTelemetry.addData("Auto", "Red Front None");
        }

        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            //WRITE AUTOS HERE
            if(duckPos == "Right"){

            }else if(duckPos == "Left"){

            }else if(duckPos == "Middle"){

            }





        }
    }
}
