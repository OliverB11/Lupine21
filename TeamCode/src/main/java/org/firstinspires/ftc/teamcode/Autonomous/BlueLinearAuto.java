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


@Autonomous(name="BlueLinearAuto", group="Autonomous Linear Opmode")
public class BlueLinearAuto extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Mecanum robot;
    PID pid;
    DuckWheel duckWheel;


    public void initialize(){
        setOpMode(this);
        pid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.integralWeight);
        robot = new Mecanum();
        duckWheel = new DuckWheel();
        ElapsedTime time = new ElapsedTime();


        multTelemetry.addData("Status", "Initalized");
        multTelemetry.addData("Duck Position:", BlueDuckPosition.whereDuck);
        multTelemetry.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){
        initialize();
        Side.red = false;
        Side.blue = true;

// DUCK ON LEFT

        if(BlueDuckPosition.duckOnLeft) {
            robot.strafe(.25, 200, 180, 90);
            robot.strafe(.25, 200, 180, 270);
            robot.strafe(.25, 200, 0, 90);
            robot.strafe(.2, 800, 0, 0);
            duckWheel.redSpin(.4);
            time.reset();
            while (time.seconds() < 5) {

            }
            duckWheel.stop();
            robot.strafe(.3, 150, 0, 270);
            robot.strafe(.3, 4800, 0, 180);

// DUCK IN MIDDLE

        }else if (BlueDuckPosition.duckInMiddle){
            robot.strafe(.25, 200, 180, 90);
            robot.strafe(.25, 200, 180, 270);
            robot.strafe(.25, 200, 0, 90);
            robot.strafe(.15, 800, 0, 0);
            duckWheel.redSpin(.4);
            time.reset();
            while (time.seconds() < 5) {

            }
            duckWheel.stop();
            robot.strafe(.3, 150, 0, 270);
            robot.strafe(.3, 4800, 0, 180);

// DUCK ON RIGHT

        }else if(BlueDuckPosition.duckOnRight){
            robot.strafe(.25, 200, 180, 90);
            robot.strafe(.25, 200, 180, 270);
            robot.strafe(.25, 200, 0, 90);
            robot.strafe(.15, 800, 0, 0);
            duckWheel.redSpin(.4);
            time.reset();
            while (time.seconds() < 5) {

            }
            duckWheel.stop();
            robot.strafe(.3, 150, 0, 270);
            robot.strafe(.3, 4800, 0, 180);
        }




        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){

            /*
                    Y O U R   C O D E   H E R E
                                                   */

        }
    }
}
