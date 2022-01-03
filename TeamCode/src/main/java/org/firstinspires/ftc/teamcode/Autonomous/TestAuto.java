package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.ScoringMechanism;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;
import org.firstinspires.ftc.teamcode.Z.Side;

@Autonomous(name="TestAuto", group="Autonomous Linear Opmode")
public class TestAuto extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Mecanum robot;
    DuckWheel duckWheel;
    ScoringMechanism scorer;
    Intake intake;


    public void initialize(){
        setOpMode(this);
        Side.red = true;
        Side.blue = false;
        robot = new Mecanum();
        duckWheel = new DuckWheel();
        intake = new Intake();
        scorer = new ScoringMechanism();
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



        }
    }
}
