package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.DashConstants.Unfixed;
import org.firstinspires.ftc.teamcode.Z.Side;

@Autonomous(name="BaseLinearAuto", group="Autonomous Linear Opmode")
public class BaseLinearAuto extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Mecanum robot;
    PID pid;


    public void initialize(){
        setOpMode(this);
        robot = new Mecanum();
        Side.red = true;
        Side.blue = false;
        pid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.integralWeight);
        ElapsedTime time = new ElapsedTime();


        multTelemetry.addData("Status", "Initalized");
        multTelemetry.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){
        initialize();

        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){

/*
                YOUR CODE HERE
 */


        }
    }
}
