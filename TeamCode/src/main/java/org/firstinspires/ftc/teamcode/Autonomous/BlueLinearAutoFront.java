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
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Color_Sensor;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.DashConstants.Unfixed;
import org.firstinspires.ftc.teamcode.Z.Side;
import org.firstinspires.ftc.teamcode.Z.Vision.DuckPosition;


@Autonomous(name="BlueLinearAutoFront", group="Autonomous Linear Opmode")
public class BlueLinearAutoFront extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Mecanum robot;
    PID pid;
    DuckWheel duckWheel;
    ScoringMechanism scorer;
    Intake intake;
    Color_Sensor flColor;
    Color_Sensor frColor;
    Color_Sensor blColor;
    Color_Sensor brColor;


    public void initialize() {
        setOpMode(this);
        Side.red = false;
        Side.blue = true;
        pid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.integralWeight);
        robot = new Mecanum();
        duckWheel = new DuckWheel();
        scorer = new ScoringMechanism();
        intake = new Intake();
        flColor = new Color_Sensor();
        frColor = new Color_Sensor();
        blColor = new Color_Sensor();
        brColor = new Color_Sensor();
        flColor.init("flColor");
        frColor.init("frColor");
        frColor.init("blColor");
        frColor.init("brColor");

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

        if (DuckPosition.duckPos == 3) {
            multTelemetry.addData("Auto", "Blue Front Right");



// DUCK IN MIDDLE

        } else if (DuckPosition.duckPos == 2) {
            multTelemetry.addData("Auto", "Blue Front Middle");

// DUCK ON RIGHT

        } else if (DuckPosition.duckPos == 1) {
            multTelemetry.addData("Auto", "Blue Front Left");

// NO DUCK
        } else {
            multTelemetry.addData("Auto", "Blue Front None");

        }

        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()) {
            robot.gyro.reset();
            //WRITE AUTOS HERE

            if(DuckPosition.duckPos == 3){
                //RIGHT
                //Top

            }else if(DuckPosition.duckPos == 2){
                //MIDDLE
                //Middle

            }else if(DuckPosition.duckPos == 1){
                //LEFT
                //Bottom



            }else{
                //NONE

            }
            



        }
    }
}
