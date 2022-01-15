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
                robot.strafe(.2,75,180,135);
                robot.strafe(.2,75,180,0);
                scorer.autoTop();
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2500,180,100);
                robot.strafe(.2,400,180,100);
                duckWheel.redSpin(.2);
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,1000,180,315);
                robot.strafe(.3,800,0,90);


                robot.strafe(.3,300,0,270);
                robot.strafe(.3,300,0,270);
                robot.strafe(.3,300,0,270);



            }else if(DuckPosition.duckPos == 2){
                //Middle
                //Middle

                robot.strafe(.5,1200,180,295);
                robot.strafe(.2,50,180,135);
                scorer.autoMiddle();
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2500,180,100);
                robot.strafe(.2,400,180,100);
                duckWheel.redSpin(.2);
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,1000,180,315);
                robot.strafe(.3,800,0,90);


                robot.strafe(.3,300,0,270);
                robot.strafe(.3,300,0,270);
                robot.strafe(.3,300,0,270);

            }else if(DuckPosition.duckPos == 1){
                //Left
                //Bottom

                robot.strafe(.5,1200,180,295);
                robot.strafe(.3,100,180,45);
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2500,180,100);
                robot.strafe(.2,500,180,100);
                robot.strafe(.2,100,180,180);
                duckWheel.redSpin(.2);
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,1000,180,270);
                robot.strafe(.2,1000,0,45);
                robot.sleep(0.01, time);
                robot.strafe(.3,700,0, 60);
//                robot.strafe(.3,200,0,90);


            }





        }
    }
}
