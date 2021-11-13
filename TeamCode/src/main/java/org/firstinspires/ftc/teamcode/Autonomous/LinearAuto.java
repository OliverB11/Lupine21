package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;


@Autonomous(name="LinearAuto", group="Autonomous Linear Opmode")
public class LinearAuto extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Mecanum evansChassis;
    PID evansChassisPid;
    DuckWheel duckWheel;

    public void initialize(){
        setOpMode(this);
        evansChassisPid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.integralWeight);
        evansChassis = new Mecanum();
        duckWheel = new DuckWheel();
        ElapsedTime time = new ElapsedTime();
        multTelemetry.addData("Status", "Initalized");
        multTelemetry.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){
        initialize();

        evansChassis.strafe(.15,200,0,90);
        evansChassis.strafe(.15,800, 0,0);
        duckWheel.blueSpin(.5);
        time.reset();
        while(time.seconds()<Unfixed.duckWheelTime){

        }
        duckWheel.stop();
        evansChassis.strafe(.3,150,0,270);
        evansChassis.strafe(.3,4800,0,180);



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
