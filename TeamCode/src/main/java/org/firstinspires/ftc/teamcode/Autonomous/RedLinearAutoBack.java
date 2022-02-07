package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Z.Side;
import org.firstinspires.ftc.teamcode.Z.Vision.DuckPosition;


@Autonomous(name="RedLinearAutoBack", group="Autonomous Linear Opmode")
public class RedLinearAutoBack extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Robot robot;



    public void initialize(){
        setOpMode(this);
        Side.red = true;
        Side.blue = false;
        robot = new Robot();




        multTelemetry.addData("Drivers", "WAIT");
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
            multTelemetry.addData("Auto", "Red Back Right");



// DUCK IN MIDDLE

        }else if (DuckPosition.duckPos == 2){
            multTelemetry.addData("Auto", "Red Back Middle");


// DUCK ON RIGHT

        }else if(DuckPosition.duckPos == 3) {
            multTelemetry.addData("Auto", "Red Back Left");

// NO DUCK
        }else{
            multTelemetry.addData("Auto", "Red Back None");
        }



        multTelemetry.addData("Drivers", "Run");
        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            robot.chassis.gyro.reset();
            //WRITE AUTOS HERE

            if(DuckPosition.duckPos == 3){
                //RIGHT
                //Top
                robot.chassis.strafe(.5,1200,180,295);
                robot.chassis.strafe(.2,75,180,135);
                robot.chassis.strafe(.2,100,180,0);
                robot.scorer.autoTop();
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.5, time);
                robot.chassis.strafe(.5,2500,180,100);
                robot.chassis.strafe(.2,400,180,100);
                robot.duckWheel.redSpin(.2);
                robot.chassis.sleep(3.5,time);
                robot.duckWheel.stop();
                robot.chassis.strafe(.3,1000,180,315);
                robot.chassis.strafe(.4,600,0,90);
                robot.chassis.strafe(.3,50,0,180);
                while(robot.chassis.flColor.getRedCacheValue() < 70 && robot.chassis.frColor.getRedCacheValue() < 70){
                    robot.chassis.setDrivePower(.3, 0, 0, .4);
                    robot.chassis.flColor.updateRed();
                }
                while(robot.chassis.flColor.getRedCacheValue() > 70 && robot.chassis.frColor.getRedCacheValue() < 70){
                    robot.chassis.setDrivePower(.3,0, 0, -.3);
                    robot.chassis.flColor.updateRed();

                }
                robot.chassis.strafe(.2, 75,0,180);


            }else if(DuckPosition.duckPos == 2){
                //Middle
                //Middle
                robot.chassis.strafe(.5,1200,180,295);
                robot.chassis.strafe(0.3,50,180,0);
                robot.scorer.autoMiddle();
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.5, time);
                robot.chassis.strafe(.5,2500,180,100);
                robot.chassis.strafe(.2,400,180,100);
                robot.duckWheel.redSpin(.2);
                robot.chassis.sleep(3.5,time);
                robot.duckWheel.stop();
                robot.chassis.strafe(.3,1000,180,315);
                robot.chassis.strafe(.3,800,0,90);
                robot.chassis.strafe(.3,75,0,180);
                while(robot.chassis.flColor.getRedCacheValue() < 70 && robot.chassis.frColor.getRedCacheValue() < 70){
                    robot.chassis.setDrivePower(.3, 0, 0, .4);
                    robot.chassis.flColor.updateRed();

                }
                while(robot.chassis.flColor.updateRed() > 70 && robot.chassis.frColor.updateRed() < 70){
                    robot.chassis.setDrivePower(.3,0, 0, -.3);
                }
                robot.chassis.strafe(.2, 75,0,180);


            }else if(DuckPosition.duckPos == 1){
                //Left
                //Bottom
                robot.chassis.strafe(.5,1200,180,295);
                robot.chassis.strafe(.3,75,180,20);
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.5, time);
                robot.chassis.strafe(.5,2500,180,100);
                robot.chassis.strafe(.2,500,180,100);
                robot.chassis.strafe(.2,100,180,180);
                robot.duckWheel.redSpin(.2);
                robot.intake.autoSpin();
                robot.chassis.sleep(3.5,time);
                robot.duckWheel.stop();
                robot.chassis.strafe(.3,1000,180,270);
                robot.intake.stop();
                robot.chassis.strafe(.2,1300,0, 60);
                robot.chassis.strafe(.3,200,0,90);
                while(robot.chassis.flColor.updateRed() < 70 && robot.chassis.frColor.updateRed() < 70){
                    robot.chassis.setDrivePower(.3, 0, 0, .4);
                }
                while(robot.chassis.flColor.updateRed() > 70 && robot.chassis.frColor.updateRed() < 70){
                    robot.chassis.setDrivePower(.3,0, 0, -.3);
                }
                robot.chassis.strafe(.2, 75,0,180);


            }





        }
    }
}
