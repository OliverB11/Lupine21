package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Distance_Sensor;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Z.Side;
import org.firstinspires.ftc.teamcode.Z.Vision.DetectionPipeline;
import org.firstinspires.ftc.teamcode.Z.Vision.DuckPosition;


@Autonomous(name="RedLinearAutoFront", group="Autonomous Linear Opmode")
public class RedLinearAutoFront extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    private DetectionPipeline pipeline = new DetectionPipeline();
    Robot robot;
    Distance_Sensor distance;




    public void initialize(){
        setOpMode(this);
        Side.red = true;
        Side.blue = false;
        robot = new Robot();
        robot.chassis.startCamera(pipeline);
        distance = new Distance_Sensor();
        distance.init("distance");



        multTelemetry.addData("Status", "Initalized");
        multTelemetry.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){
        initialize();

        time.reset();
        MathUtils.wait(time, 5);
        robot.chassis.cam.close();


// DUCK ON LEFT
        //INIT LOOP

        while (!opModeIsActive()) {
            if (DuckPosition.getDuckPos() == 1) {
                multTelemetry.addData("Auto", "Red Front Left");


// DUCK IN MIDDLE

            } else if (DuckPosition.getDuckPos() == 2) {
                multTelemetry.addData("Auto", "Red Front Middle");


// DUCK ON RIGHT

            } else if (DuckPosition.getDuckPos() == 3) {
                multTelemetry.addData("Auto", "Red Front Right");

// NO DUCK
            } else {
                multTelemetry.addData("Auto", "Red Front None");
            }
        }

        multTelemetry.addLine("Waiting for start");
        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            //WRITE AUTOS HERE

            if(DuckPosition.getDuckPos() == 3){
                //RIGHT
                //Top
                robot.scorer.autoTop();
                robot.chassis.strafe(.6, 1000,180, 72);
                robot.chassis.strafe(.3,250,188,50);
                robot.scorer.autoDeposit();
                robot.chassis.strafe(.5,200,180,180);
                robot.chassis.sleep(0.3, time);
                robot.chassis.strafe(.6,1250,270,225);
                robot.chassis.cycle(robot.intake, robot.scorer, distance,1);
                robot.chassis.cycle(robot.intake, robot.scorer, distance,2);
                robot.chassis.strafe(.7,1500,270,270);
                robot.chassis.strafe(.4,300,0,0);
                robot.chassis.turn(0);






            }else if(DuckPosition.getDuckPos() == 2){
                //MIDDLE
                //Middle
                robot.scorer.autoMiddle();
                robot.chassis.strafe(.6, 1000,180, 70);
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.3, time);
                robot.chassis.strafe(.3,100,180,0);
                robot.chassis.strafe(.5,200,180,180);
                robot.chassis.strafe(.6,1250,270,225);
                robot.chassis.cycle(robot.intake, robot.scorer, distance,1);
                robot.chassis.cycle(robot.intake, robot.scorer, distance,2);
                robot.chassis.strafe(.7,1700,270,270);
                robot.chassis.strafe(.4,300,0,0);


            }else if(DuckPosition.getDuckPos() == 1){
                //Left
                //Bottom
                robot.chassis.strafe(.6, 1200,180, 70);
                robot.scorer.autoDeposit();
                robot.chassis.sleep(0.3, time);
                robot.chassis.strafe(.6,200,180,180);
                robot.chassis.strafe(.6,1250,270,225);
                robot.chassis.cycle(robot.intake, robot.scorer, distance,1);
                robot.chassis.cycle(robot.intake, robot.scorer, distance,2);
                robot.chassis.strafe(.7,1500,270,270);
                robot.chassis.strafe(.4,300,0,0,0);


            }else{
                //NONE

            }




        }
    }
}
