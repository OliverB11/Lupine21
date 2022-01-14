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
import org.firstinspires.ftc.teamcode.Z.Side;
import org.firstinspires.ftc.teamcode.Z.Vision.DuckPosition;


@Autonomous(name="RedLinearAutoBack", group="Autonomous Linear Opmode")
public class RedLinearAutoBack extends LinearOpMode {
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



        multTelemetry.addData("Status", "Initalized");
        multTelemetry.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){
        initialize();
        time.reset();
        MathUtils.wait(time,5);

// DUCK ON LEFT

        if(DuckPosition.duckPos == 1) {
            multTelemetry.addData("Auto", "Red Back Left");



// DUCK IN MIDDLE

        }else if (DuckPosition.duckPos == 2){
            multTelemetry.addData("Auto", "Red Back Middle");


// DUCK ON RIGHT

        }else if(DuckPosition.duckPos == 3) {
            multTelemetry.addData("Auto", "Red Back Right");

// NO DUCK
        }else{
            multTelemetry.addData("Auto", "Red Back None");
        }




        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            robot.gyro.reset();
            //WRITE AUTOS HERE
            if(DuckPosition.duckPos == 3){
                //RIGHT
                //Top

                robot.strafe(.5,1200,180,295);
                robot.strafe(.2,25,180,180);
                scorer.autoTop();
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2500,180,100);
                robot.strafe(.2,400,180,100);
                duckWheel.redSpin(.2);
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,1200,0,315);
                robot.strafe(.3,1000,0,90);



            }else if(DuckPosition.duckPos == 2){
                //Middle
                //Middle

                robot.strafe(.5,1200,180,295);
                robot.strafe(.2,25,180,180);
                scorer.autoMiddle();
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2500,180,100);
                robot.strafe(.2,400,180,100);
                duckWheel.redSpin(.2);
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,300,0,0);
                robot.strafe(.3,1000,0,0);

            }else if(DuckPosition.duckPos == 1){
                //Left
                //Bottom

                robot.strafe(.5,1200,180,295);
                robot.strafe(.2,25,180,180);
                scorer.autoBottom();
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2500,180,100);
                robot.strafe(.2,400,180,100);
                duckWheel.redSpin(.2);
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,300,0,0);
                robot.strafe(.3,1000,0,0);


            }





        }
    }
}
