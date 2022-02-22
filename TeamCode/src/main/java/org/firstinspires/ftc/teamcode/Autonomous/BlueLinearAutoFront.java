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
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Distance_Sensor;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Z.Side;
import org.firstinspires.ftc.teamcode.Z.Vision.DetectionPipeline;
import org.firstinspires.ftc.teamcode.Z.Vision.DuckPosition;


@Autonomous(name="BlueLinearAutoFront", group="Autonomous Linear Opmode")
public class BlueLinearAutoFront extends LinearOpMode {
    // Declare OpMode members.
    private final ElapsedTime time = new ElapsedTime();
    private DetectionPipeline pipeline = new DetectionPipeline();
    Robot robot;
    Distance_Sensor distance;




    public void initialize() {
        setOpMode(this);
        Side.red = false;
        Side.blue = true;
        robot = new Robot();
        robot.chassis.startCamera(pipeline);
        distance = new Distance_Sensor();
        distance.init("distance");


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
        robot.chassis.cam.close();


// DUCK ON LEFT

        if(DuckPosition.duckPos == 1) {
            multTelemetry.addData("Auto", "Blue Front Left");



// DUCK IN MIDDLE

        }else if (DuckPosition.duckPos == 2){
            multTelemetry.addData("Auto", "Blue Front Middle");


// DUCK ON RIGHT

        }else if(DuckPosition.duckPos == 3) {
            multTelemetry.addData("Auto", "Blue Front Right");

// NO DUCK
        }else{
            multTelemetry.addData("Auto", "Blue Front None");
        }

        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            //WRITE AUTOS HERE

            if(DuckPosition.duckPos == 3){
                //RIGHT
                //Top
                robot.scorer.autoTop();
                robot.chassis.strafe(.6, 1150, 180, 290);
                robot.chassis.strafe(.3,300,180,45);
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.3, time);
                robot.chassis.strafe(.5, 200, 180, 180);
                robot.chassis.strafe(.6, 900, 90, 135);
                robot.chassis.strafe(.6,300,90,90);
                robot.chassis.cycle(robot.intake, robot.scorer, distance, 1);
                robot.chassis.cycle(robot.intake, robot.scorer, distance,2);
                robot.chassis.strafe(.7,1700,90,90);
                robot.chassis.strafe(.4,300,0,0);






            }else if(DuckPosition.duckPos == 2){
                //MIDDLE
                //Middle
                robot.scorer.autoMiddle();
                robot.chassis.strafe(.6, 1150, 180, 290);
                robot.chassis.strafe(.3,300,180,45);
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.3, time);
                robot.chassis.strafe(.5, 300, 180, 180); 
                robot.chassis.strafe(.6, 900, 90, 135);
                robot.chassis.strafe(.6,200,90,90);
                robot.chassis.cycle(robot.intake, robot.scorer, distance, 1);
                robot.chassis.cycle(robot.intake, robot.scorer, distance,2);
                robot.chassis.strafe(.7,1700,90,90);
                robot.chassis.strafe(.4,300,0,0);


            }else if(DuckPosition.duckPos == 1){
                //Left
                //Top
                robot.scorer.autoBottom();
                robot.chassis.strafe(.6,200,180,0);
                robot.chassis.strafe(.6, 780, 180, 290);
                robot.chassis.strafe(.3, 250, 180, 290);
                robot.chassis.strafe(.3,200,180,135);
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.3, time);
                robot.chassis.strafe(.4,200,180,180);
                robot.chassis.strafe(.6, 900, 90, 135);
                robot.chassis.cycle(robot.intake, robot.scorer, distance, 1);
                robot.chassis.cycle(robot.intake, robot.scorer, distance,2);
                robot.chassis.strafe(.7,1300,90,90);
                robot.chassis.strafe(.4,300,0,0);


            }else{
                //NONE

            }
            offsetAngle = 360 - (robot.chassis.gyro.angle() % 360);




        }
    }
}
