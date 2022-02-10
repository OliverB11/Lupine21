package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;
import static org.firstinspires.ftc.teamcode.Z.OffsetAngle.offsetAngle;

import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Z.Side;
import org.firstinspires.ftc.teamcode.Z.Vision.DuckPosition;


@Autonomous(name="BlueLinearAutoBack", group="Autonomous Linear Opmode")
public class BlueLinearAutoBack extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Robot robot;



    public void initialize(){
        setOpMode(this);
        Side.red = false;
        Side.blue = true;
        robot = new Robot();

        multTelemetry.addData("Drivers", "WAIT");
        multTelemetry.update();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){
        initialize();
        time.reset();
        MathUtils.wait(time, 5);



// DUCK ON LEFT

        if (DuckPosition.duckPos == 3) {
            multTelemetry.addData("Auto", "Blue Back Right");


// DUCK IN MIDDLE

        } else if (DuckPosition.duckPos == 2) {
            multTelemetry.addData("Auto", "Blue Back Middle");

// DUCK ON RIGHT

        } else if (DuckPosition.duckPos == 1) {
            multTelemetry.addData("Auto", "Blue Back Left");

// NO DUCK
        } else {
            multTelemetry.addData("Auto", "Blue Back None");

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
                robot.chassis.strafe(.5,1400,180,65);
                robot.chassis.strafe(.5,180,180,90);
                robot.scorer.autoTop();
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.5, time);
                robot.chassis.strafe(.5,2000, 180,265);
                robot.intake.autoSpin();
                robot.chassis.strafe(.2,1500,180,270);
                robot.duckWheel.blueSpin(.2);
                robot.chassis.sleep(3.5,time);
                robot.duckWheel.stop();
                robot.chassis.strafe(.3,700,180,90);
                robot.chassis.strafe(.3,600,0,270);
                robot.chassis.strafe(.3,1000,0,315);
                robot.chassis.flColor.updateBlue();
                robot.chassis.frColor.updateBlue();
                while(robot.chassis.flColor.getBlueCacheValue() < 100 && robot.chassis.frColor.getBlueCacheValue() < 100){
                    robot.chassis.setDrivePower(.3, 0, 0, .4);
                    robot.chassis.flColor.updateBlue();
                }
                while(robot.chassis.flColor.getBlueCacheValue() > 100 && robot.chassis.frColor.getBlueCacheValue() < 100){
                    robot.chassis.setDrivePower(.3,0, 0, -.3);
                    robot.chassis.flColor.updateBlue();
                }
                robot.chassis.strafe(.2, 75,0,180);




            }else if(DuckPosition.duckPos == 2){
                //Middle
                //Middle
                robot.chassis.strafe(.5,1400,180,65);
                robot.chassis.strafe(.5,180,180,90);
                robot.scorer.autoMiddle();
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.5, time);
                robot.chassis.strafe(.5,2000, 180,265);
                robot.intake.autoSpin();
                robot.chassis.strafe(.2,1500,180,270);
                robot.duckWheel.blueSpin(.2);
                robot.chassis.sleep(3.5,time);
                robot.duckWheel.stop();
                robot.chassis.strafe(.3,700,180,90);
                robot.chassis.strafe(.3,600,0,270);
                robot.chassis.strafe(.3,1000,0,315);
                robot.chassis.flColor.updateBlue();
                robot.chassis.frColor.updateBlue();
                while(robot.chassis.flColor.getBlueCacheValue() < 100 && robot.chassis.frColor.getBlueCacheValue() < 100){
                    robot.chassis.setDrivePower(.3, 0, 0, .4);
                    robot.chassis.flColor.updateBlue();
                }
                while(robot.chassis.flColor.getBlueCacheValue() > 100 && robot.chassis.frColor.getBlueCacheValue() < 100){
                    robot.chassis.setDrivePower(.3,0, 0, -.3);
                    robot.chassis.flColor.updateBlue();
                }
                robot.chassis.strafe(.2, 75,0,180);

            }else if(DuckPosition.duckPos == 1){
                //Left
                //Bottom
                robot.chassis.strafe(.5,1400,180,75);
                robot.chassis.strafe(.5,180,180,90);
                robot.scorer.autoBottom();
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.5, time);
                robot.chassis.strafe(.5,2000, 180,265);
                robot.intake.autoSpin();
                robot.chassis.strafe(.2,1500,180,270);
                robot.duckWheel.blueSpin(.2);
                robot.chassis.sleep(3.5,time);
                robot.duckWheel.stop();
                robot.chassis.strafe(.3,700,180,90);
                robot.chassis.strafe(.3,600,0,270);
                robot.chassis.strafe(.3,1000,0,315);
                robot.chassis.flColor.updateBlue();
                robot.chassis.frColor.updateBlue();
                while(robot.chassis.flColor.getBlueCacheValue() < 100 && robot.chassis.frColor.getBlueCacheValue() < 100){
                    robot.chassis.setDrivePower(.3, 0, 0, .4);
                    robot.chassis.flColor.updateBlue();
                }
                while(robot.chassis.flColor.getBlueCacheValue() > 100 && robot.chassis.frColor.getBlueCacheValue() < 100){
                    robot.chassis.setDrivePower(.3,0, 0, -.3);
                    robot.chassis.flColor.updateBlue();
                }
                robot.chassis.strafe(.2, 75,0,180);
            }
            offsetAngle = 360 - (robot.chassis.gyro.angle() % 360);

        }
    }
}
