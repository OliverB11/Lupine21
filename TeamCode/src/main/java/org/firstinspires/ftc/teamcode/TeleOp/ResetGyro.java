package org.firstinspires.ftc.teamcode.TeleOp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;
import org.firstinspires.ftc.teamcode.Hardware.Robot;

//@Disabled
@TeleOp(name = "ResetGyro", group="Linear TeleOp")
public class ResetGyro extends LinearOpMode {
    Robot robot;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    public void initialize() {
        setOpMode(this);
        robot = new Robot();

        multTelemetry.addData("Status", "Initialized");
        multTelemetry.update();

    }

    public void shutdown(){
        multTelemetry.addData("Status", "Shutting Down");
        multTelemetry.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {


            /*
                    Y O U R   C O D E   H E R E
                                                   */




           /*
             ----------- L O G G I N G -----------
                                                */
            multTelemetry.addData("Status", "TeleOp Running");
            multTelemetry.update();
        }
    }
}


