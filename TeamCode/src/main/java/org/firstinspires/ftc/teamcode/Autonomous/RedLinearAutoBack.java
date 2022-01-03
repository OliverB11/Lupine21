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


@Autonomous(name="RedLinearAutoBack", group="Autonomous Linear Opmode")
public class RedLinearAutoBack extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Mecanum robot;
    DuckWheel duckWheel;
    ScoringMechanism scorer;
    Intake intake;
    private String duckPos = "";


    public void initialize(){
        setOpMode(this);
        Side.red = true;
        Side.blue = false;
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
        multTelemetry.addData("DRIVERS", "WAIT");
        multTelemetry.update();
        time.reset();
        MathUtils.wait(time, 5);

// DUCK ON LEFT

        if(RedDuckPosition.duckOnLeft) {
            multTelemetry.addData("Auto", "Red Back Left");
            multTelemetry.update();
            duckPos = "Left";


// DUCK IN MIDDLE

        }else if (RedDuckPosition.duckInMiddle){
            multTelemetry.addData("Auto", "Red Back Middle");
            duckPos = "Middle";

// DUCK ON RIGHT

        }else if(RedDuckPosition.duckOnRight) {
            multTelemetry.addData("Auto", "Red Back Right");
            duckPos = "Right";
// NO DUCK
        }else{
            multTelemetry.addData("Auto", "Red Back None");
        }




        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            robot.gyro.reset();
            //WRITE AUTOS HERE
            if(duckPos == "Right"){
                robot.strafe(.5,1200,180,295);
                scorer.autoTop();
                scorer.autoDeposit();
                robot.strafe(.5,2600,180,100);
                robot.strafe(.2,200,180,100);
                duckWheel.redSpin(.2);
                robot.sleep(3,time);
                robot.strafe(.4, 700,180,270);
                duckWheel.stop();
                intake.autoSpin();
                robot.strafe(.4, 700,100,0);

            }else if(duckPos == "Left"){
                robot.strafe(.5,1200,180,295);
                scorer.autoDeposit();
                robot.strafe(.5,2600,180,100);
                robot.strafe(.2,200,180,100);
                duckWheel.redSpin(.2);
                robot.sleep(3,time);
                duckWheel.stop();
                intake.autoSpin();
                robot.strafe(.4, 700,100,0);

            }else if(duckPos == "Middle"){
                robot.strafe(.5,1200,180,295);
                scorer.autoMiddle();
                scorer.autoDeposit();
                robot.strafe(.5,2600,180,100);
                robot.strafe(.2,200,180,100);
                duckWheel.redSpin(.2);
                robot.sleep(3,time);
                duckWheel.stop();
                intake.autoSpin();
                robot.strafe(.4, 700,100,0);



            }





        }
    }
}
