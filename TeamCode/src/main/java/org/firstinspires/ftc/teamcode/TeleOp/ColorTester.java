package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Color_Sensor;

import static java.lang.Math.floorMod;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;


@TeleOp(name="Color Tester", group="Iterative Opmode")
public class ColorTester extends OpMode {
    Color_Sensor color;

    @Override
    public void init() {
        setOpMode(this);
        color = new Color_Sensor();
        color.init("bucketColor");


    }

    @Override
    public void init_loop() {

    }
    @Override
    public void start() {

    }

    @Override
    public void loop() {

        if(color.getRed() > 280 && color.getGreen() > 470 && color.getBlue() > 408){
            multTelemetry.addData("Stuff?", "Yes");
        }else{
            multTelemetry.addData("Stuff?", "No");
        }

        multTelemetry.addData("How Much Red", color.getRed());
        multTelemetry.addData("How Much Green", color.getGreen());
        multTelemetry.addData("How Much Blue", color.getBlue());
        multTelemetry.update();
    }



    @Override
    public void stop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */
    }
}