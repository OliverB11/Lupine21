package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Robot;

import static java.lang.Math.floorMod;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;


@TeleOp(name="Color Tester", group="Iterative Opmode")
public class ColorTester extends OpMode {

    Robot robot;

    @Override
    public void init() {
        setOpMode(this);
        robot = new Robot();

    }

    @Override
    public void init_loop() {

    }
    @Override
    public void start() {

    }

    @Override
    public void loop() {

        robot.scorer.updateBucketSensor();
//        multTelemetry.addData("How Much Red", robot.scorer.blColor.updateRed());
//        multTelemetry.addData("How Much Green", robot.chassis.blColor.updateGreen());
//        multTelemetry.addData("How Much Blue", robot.chassis.blColor.updateBlue());
        multTelemetry.addData("Red",robot.scorer.bucketSensor.getRedCacheValue());
        multTelemetry.addData("Green", robot.scorer.bucketSensor.getGreenCacheValue());
        multTelemetry.addData("IsLoaded?",robot.scorer.isLoaded());
        multTelemetry.update();
    }



    @Override
    public void stop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */
    }
}