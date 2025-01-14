package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.StaticStuff.SlideStart;
import org.firstinspires.ftc.teamcode.StaticStuff.Side;
import org.firstinspires.ftc.teamcode.Vision.DetectionPipeline;
import org.firstinspires.ftc.teamcode.Vision.DuckPosition;


@Autonomous(name="RedLinearAutoBack", group="Autonomous Linear Opmode")
public class RedLinearAutoBack extends LinearOpMode {
    // Declare OpMode members.
    private final ElapsedTime time = new ElapsedTime();
    Robot robot;
    private final DetectionPipeline pipeline = new DetectionPipeline();



    public void initialize(){
        setOpMode(this);
        Side.red = true;
        Side.blue = false;
        robot = new Robot();
        robot.chassis.startCamera(pipeline);




        multTelemetry.addData("Drivers", "WAIT");
        multTelemetry.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){
        initialize();
        time.reset();



        //INIT LOOP
        while (!opModeIsActive()) {
// DUCK ON LEFT

            if (DuckPosition.getDuckPos() == 1) {
                multTelemetry.addData("Auto", "Red Back Left");


// DUCK IN MIDDLE

            } else if (DuckPosition.getDuckPos() == 2) {
                multTelemetry.addData("Auto", "Red Back Middle");


// DUCK ON RIGHT

            } else if (DuckPosition.getDuckPos() == 3) {
                multTelemetry.addData("Auto", "Red Back Right");

// NO DUCK
            } else {
                multTelemetry.addData("Auto", "Red Back None");
            }

            multTelemetry.update();
        }


        multTelemetry.addData("Drivers", "Run");
        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            robot.chassis.gyro.reset();
            //WRITE AUTOS HERE

            if(DuckPosition.getDuckPos() == 3){
                //RIGHT
                //Top
                robot.chassis.cam.close();
                robot.scorer.autoTop();
                robot.chassis.strafe(.5,1200,180,295);
                robot.chassis.strafe(.2,75,180,135);
                robot.chassis.strafe(.2,100,180,0);
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.5, time);
                robot.chassis.strafe(.5,2500,170,96.5, 10);
                robot.chassis.strafe(.15,300,170,90);
                robot.duckWheel.redSpin(.2);
                robot.chassis.sleep(3.5,time);
                robot.duckWheel.stop();
                robot.chassis.strafe(.3,1000,180,315);
                robot.chassis.strafe(.4,600,0,90);
                robot.chassis.strafe(.3,300,0,180);
                //robot.chassis.flColor.updateRed();
                robot.chassis.frColor.updateRed();
                time.reset();
                while(/*robot.chassis.flColor.getRedCacheValue() < 90 && */robot.chassis.frColor.getRedCacheValue() < 90 && time.seconds()<2){
                    robot.chassis.setDrivePower(.3, 0, 0, .3);
                    //robot.chassis.flColor.updateRed();
                    robot.chassis.frColor.updateRed();

                }
                time.reset();
                while(/*robot.chassis.flColor.getRedCacheValue() < 90 && */ robot.chassis.frColor.getRedCacheValue() > 90 && time.seconds()<2){
                    robot.chassis.setDrivePower(.3,0, 0, -.3);
//                    robot.chassis.flColor.updateRed();
                    robot.chassis.frColor.updateRed();


                }
                robot.chassis.strafe(.2, 125,0,180);


            }else if(DuckPosition.getDuckPos() == 2){
                //Middle
                //Middle
                robot.chassis.cam.close();
                robot.chassis.strafe(.5,1200,180,295);
                robot.chassis.strafe(0.3,50,180,0);
                robot.scorer.autoMiddle();
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.5, time);
                robot.chassis.strafe(.3,300,180,180);
                robot.chassis.strafe(.5,2500,170,90);
                robot.chassis.strafe(.2,400,170,90);
                robot.chassis.strafe(.15,100,170,135);
                robot.duckWheel.redSpin(.2);
                robot.chassis.sleep(3.5,time);
                robot.duckWheel.stop();
                robot.chassis.strafe(.3,1000,180,315);
                robot.chassis.strafe(.3,800,0,100);
                robot.chassis.strafe(.3,200,0,180);
                //robot.chassis.flColor.updateRed();
                robot.chassis.frColor.updateRed();
                time.reset();
                while(/*robot.chassis.flColor.getRedCacheValue() < 90 && */ robot.chassis.frColor.getRedCacheValue() < 90 && time.seconds()<2){
                    robot.chassis.setDrivePower(.3, 0, 0, .3);
                    //robot.chassis.flColor.updateRed();
                    robot.chassis.frColor.updateRed();

                }
                time.reset();
                while(/*robot.chassis.flColor.getRedCacheValue() < 90 && */robot.chassis.frColor.getRedCacheValue() > 90 && time.seconds()<2){
                    robot.chassis.setDrivePower(.3,0, 0, -.3);
                    //robot.chassis.flColor.updateRed();
                    robot.chassis.frColor.updateRed();


                }
                robot.chassis.strafe(.2, 75,0,180);


            }else if(DuckPosition.duckPos == 1){
                //Left
                //Bottom
                robot.chassis.cam.close();
                robot.chassis.strafe(.5,1200,180,295);
                robot.chassis.strafe(.3,75,180,20);
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.5, time);
                robot.chassis.strafe(.5,2500,180,100);
                robot.chassis.strafe(.2,500,180,100);
                robot.chassis.strafe(.2,100,180,90);
                robot.duckWheel.redSpin(.2);
                robot.intake.autoSpin();
                robot.chassis.sleep(3.5,time);
                robot.duckWheel.stop();
                robot.chassis.strafe(.3,1000,180,270);
                robot.chassis.strafe(.3,1000,0,70);
                robot.chassis.strafe(.3,400,0,90);
                robot.intake.stop();
                //robot.chassis.flColor.updateRed();
                robot.chassis.frColor.updateRed();
                time.reset();
                while(/*robot.chassis.flColor.getRedCacheValue() < 90 && */robot.chassis.frColor.getRedCacheValue() < 90 && time.seconds()<2){
                    robot.chassis.setDrivePower(.3, 0, 0, .3);
                    //robot.chassis.flColor.updateRed();
                    robot.chassis.frColor.updateRed();

                }
                time.reset();
                while(/*robot.chassis.flColor.getRedCacheValue() < 90 && */robot.chassis.frColor.getRedCacheValue() > 90 && time.seconds()<2){
                    robot.chassis.setDrivePower(.3,0, 0, -.3);
                    //robot.chassis.flColor.updateRed();
                    robot.chassis.frColor.updateRed();


                }
                robot.chassis.strafe(.2, 75,0,180);

            }
        }
        SlideStart.slideStart = robot.scorer.spool.getCurrentPosition();
    }
}

