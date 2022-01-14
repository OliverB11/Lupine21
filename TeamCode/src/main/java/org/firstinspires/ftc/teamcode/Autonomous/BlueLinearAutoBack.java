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
import org.firstinspires.ftc.teamcode.Z.Vision.DuckPosition;


@Autonomous(name="BlueLinearAutoBack", group="Autonomous Linear Opmode")
public class BlueLinearAutoBack extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Mecanum robot;
    PID pid;
    DuckWheel duckWheel;
    Intake intake;
    ScoringMechanism scorer;


    public void initialize(){
        setOpMode(this);
        Side.red = false;
        Side.blue = true;
        pid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.integralWeight);
        robot = new Mecanum();
        duckWheel = new DuckWheel();
        intake = new Intake();
        scorer = new ScoringMechanism();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){
        initialize();

        time.reset();
        MathUtils.wait(time, 5);



// DUCK ON LEFT

        if (DuckPosition.duckPos == 1) {
            multTelemetry.addData("Auto", "Blue Front Left");


// DUCK IN MIDDLE

        } else if (DuckPosition.duckPos == 2) {
            multTelemetry.addData("Auto", "Blue Back Middle");

// DUCK ON RIGHT

        } else if (DuckPosition.duckPos == 3) {
            multTelemetry.addData("Auto", "Blue Back Right");

// NO DUCK
        } else {
            multTelemetry.addData("Auto", "Blue Back None");

        }

        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            robot.gyro.reset();
            //WRITE AUTOS HERE
            if(DuckPosition.duckPos == 3){
                //RIGHT

                robot.strafe(.5,1250,0,295);
                scorer.autoTop();
                scorer.deposit();
            }else if(DuckPosition.duckPos == 1){
                //LEFT

                robot.strafe(.5,1250,0,295);
                scorer.autoDeposit();
            }else if(DuckPosition.duckPos == 2){
                //MIDDLE

                robot.strafe(.5,1250,0,295);
                scorer.autoMiddle();
                scorer.autoDeposit();
            }

        }
    }
}
