package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Controls.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.StaticStuff.SlideStart;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.StaticStuff.Side;

import static org.firstinspires.ftc.teamcode.StaticStuff.Joystick_Dull.driveDull;
import static org.firstinspires.ftc.teamcode.StaticStuff.Joystick_Dull.turnDull;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;


@TeleOp(name="TeleOp", group="Iterative Opmode")
public class IterativeTeleOp extends OpMode {

    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();
    public static Robot robot;
    double power;
    Controller controller;
    Controller controller2;
    double setPoint = 360;
    boolean wasTurning;
    boolean wasLoaded = false;
    int manualIntake = 0;
    private DigitalChannel redLED;
    private DigitalChannel greenLED;




    enum SlideState {
        TOP, MIDDLE, BOTTOM, DEPOSIT, DRIVING, INTAKE, NONE
    }

    enum CapperState {
        UP, DOWN, RESTING
    }

    CapperState currentCapperState = CapperState.RESTING;

    SlideState currentSlideState = SlideState.DRIVING;



    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);

        power = 0.6;
        robot = new Robot();
        controller = new Controller(gamepad1);
        controller2 = new Controller(gamepad2);



        multTelemetry.addData("Status", "Initialized");
        multTelemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */


    @Override
    public void init_loop() {

        multTelemetry.addData("Status", "InitLoop");
        multTelemetry.addData("Slide Start", SlideStart.slideStart);

        multTelemetry.update();
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
        robot.scorer.spool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.scorer.spool.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        /*
                    Y O U R   C O D E   H E R E
                                                   */

        multTelemetry.addData("Status", "Started");
        multTelemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

    }


        @Override
        public void stop(){

        //redLED.setState(true);
        //greenLED.setState(false);
        }
}