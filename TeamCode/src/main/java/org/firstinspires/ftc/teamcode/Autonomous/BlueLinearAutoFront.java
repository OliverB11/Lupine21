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


    public void initialize() {
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
    public void runOpMode() {
        initialize();
        multTelemetry.addData("DRIVERS", "WAIT");
        multTelemetry.update();
        time.reset();
        MathUtils.wait(time, 5);

// DUCK ON LEFT

        if (BlueDuckPosition.duckOnLeft) {
            multTelemetry.addData("Auto", "Blue Front Left");
            duckPos = "Left";

// DUCK IN MIDDLE

        } else if (BlueDuckPosition.duckInMiddle) {
            multTelemetry.addData("Auto", "Blue Front Middle");
            duckPos = "Middle";
// DUCK ON RIGHT

        } else if (BlueDuckPosition.duckOnRight) {
            multTelemetry.addData("Auto", "Blue Front Right");
            duckPos = "Right";
// NO DUCK
        } else {
            multTelemetry.addData("Auto", "Blue Front None");
            duckPos = "None";
        }


        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()) {
            robot.gyro.reset();
            //WRITE AUTOS HERE

            if(duckPos == "Right"){
               // robot.strafe(0.3,600,robot.gyro.rawAngle(),-90);
            }else if(duckPos == "Left"){
              //  robot.strafe(0.3,600,robot.gyro.rawAngle(),90);
            }else if(duckPos == "Middle"){

              //  robot.strafe(0.3,600,robot.gyro.rawAngle(),0);

            }else{

            }
            



        }
    }
}
