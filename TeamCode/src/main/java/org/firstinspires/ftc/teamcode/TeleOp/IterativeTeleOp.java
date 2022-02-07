package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Controls.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Robot;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.Subsystems.ScoringMechanism;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Z.Side;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;


@TeleOp(name="TeleOp", group="Iterative Opmode")
public class IterativeTeleOp extends OpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public static Robot robot;
    double power;
    Controller controller;
    Controller controller2;
    double setPoint = 360;
    boolean wasTurning;
    boolean wasLoaded = false;

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

        if (!Side.blue && !Side.red) {
            Side.blue = true;
            Side.red = false;
        }

        multTelemetry.addData("Status", "Initialized");
        multTelemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */


    @Override
    public void init_loop() {

        multTelemetry.addData("Status", "InitLoop");

        multTelemetry.update();
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();


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

//Declarations
        double rotation;


// Updates
        controller.controllerUpdate();
        controller2.controllerUpdate();
        robot.chassis.gyro.update();
        robot.intake.updateEncoders();



// PID




        if (!(controller.rightStick().x == 0)) {
            rotation = controller.rightStick().x;
            wasTurning = true;
        } else if (wasTurning && Math.abs(robot.chassis.gyro.rateOfChange()) > 0) {
            rotation = controller.rightStick().x;
        } else {
            if (wasTurning) {
                setPoint = robot.chassis.gyro.rawAngle();
                wasTurning = false;
            }
            rotation = robot.chassis.pid.update(robot.chassis.gyro.rawAngle() - setPoint);
        }

// Speed Control
        if (controller.RB.press()) {
            power = 0.2;
        }else if(controller.LB.press()){
            power = 0.5;
        } else {
            power = 0.8;
        }


//gyro reset ability
        if (controller.share.tap()) {
            setPoint = 0;
            robot.chassis.gyro.reset();
        }

// Switch Sides
        if(controller2.share.tap()) {
            if (Side.blue) {
                Side.blue = false;
                Side.red = true;
            } else if (Side.red) {
                Side.blue = true;
                Side.red = false;
            }
        }

// Rumble
        if(currentSlideState == SlideState.INTAKE) {
            if (!wasLoaded && robot.scorer.isLoaded()) {
                controller.gamepad.rumble(2000);
                controller2.gamepad.rumble(2000);
            }
            wasLoaded = robot.scorer.isLoaded();
        }


// Stuff

    //Controller1 Stuff
        if (controller.RTrigger.press() && !robot.scorer.armUp) {
            if(currentSlideState != SlideState.INTAKE) {
                robot.scorer.time.reset();
                robot.intake.time.reset();
            }
            currentSlideState = SlideState.INTAKE;
            if(robot.scorer.isLoaded()) {
                robot.intake.backSpin();
            }else{
                robot.intake.spin();
            }
        } else if (controller.LTrigger.press() && !robot.scorer.armUp) {
            if(currentSlideState != SlideState.INTAKE) {
                robot.scorer.time.reset();
                robot.intake.time.reset();
            }
            currentSlideState = SlideState.INTAKE;
            robot.intake.backSpin();
        } else if (!robot.scorer.armUp){
            currentSlideState = SlideState.DRIVING;
            robot.scorer.time.reset();
            robot.intake.stop();
        }


            if (controller.left.press()) {
                setPoint = MathUtils.closestAngle(90, robot.chassis.gyro.rawAngle());
            }
            if (controller.up.press()) {
                setPoint = MathUtils.closestAngle(0, robot.chassis.gyro.rawAngle());
            }
            if (controller.down.press()) {
                setPoint = MathUtils.closestAngle(180, robot.chassis.gyro.rawAngle());
            }
            if (controller.right.press()) {
                setPoint = MathUtils.closestAngle(270, robot.chassis.gyro.rawAngle());
            }


        //Controller 2 Stuff

            if(controller2.LB.tap()){
                switch (currentCapperState){
                    case DOWN:
                        currentCapperState = CapperState.RESTING;
                        break;
                    case RESTING:
                        currentCapperState = CapperState.UP;
                        break;
                    case UP:
                        currentCapperState = CapperState.DOWN;
                        break;
                }
            }

        if(controller2.RB.tap()){
            switch (currentCapperState){
                case DOWN:
                    currentCapperState = CapperState.UP;
                    break;
                case RESTING:
                    currentCapperState = CapperState.DOWN;
                    break;
                case UP:
                    currentCapperState = CapperState.RESTING;
                    break;
            }
        }

            switch(currentCapperState){
                case DOWN:
                    robot.capper.down(controller2.RTrigger.getValue());
                    break;
                case RESTING:
                    robot.capper.resting();
                    break;
                case UP:
                    robot.capper.up(controller2.RTrigger.getValue());
                    break;
            }


            if (Side.blue) {
                if (controller2.cross.toggle()) {
                    robot.duckWheel.blueSpin(.28);
                } else {
                    robot.duckWheel.stop();
                }
            } else if (Side.red) {
                if (controller2.cross.toggle()) {
                    robot.duckWheel.redSpin(.28);
                } else {
                    robot.duckWheel.stop();
                }
            }

            // Slide Stuff

            if (controller2.up.tap()) {
                currentSlideState = SlideState.TOP;
                robot.scorer.time.reset();
            }
            if (controller2.left.tap()) {
                currentSlideState = SlideState.MIDDLE;
                robot.scorer.time.reset();
            }
            if (controller2.right.tap()) {
                currentSlideState = SlideState.BOTTOM;
                robot.scorer.time.reset();
            }
            if (controller2.down.tap()) {
                currentSlideState = SlideState.DEPOSIT;
                robot.scorer.time.reset();
            }
            if (controller2.circle.tap()){
                currentSlideState = SlideState.DRIVING;
                robot.scorer.time.reset();
            }

            switch (currentSlideState) {
                case TOP:
                    robot.scorer.top();
                    break;

                case MIDDLE:
                    robot.scorer.middle();
                    break;

                case BOTTOM:
                    robot.scorer.bottom();
                    break;

                case DEPOSIT:
                    robot.scorer.deposit();
                    break;


                case DRIVING:
                    robot.scorer.driving();

                case INTAKE:
                    robot.scorer.intake();

                case NONE:
                    break;
            }


//Movement control
            double drive = -MathUtils.shift(controller.leftStick(), robot.chassis.gyro.rawAngle()).y;
            double strafe = MathUtils.shift(controller.leftStick(), robot.chassis.gyro.rawAngle()).x;
            double turn = -rotation;

            robot.chassis.setDrivePower(power, strafe, turn, drive);


//Telemetry
            multTelemetry.addData("Is Loaded?", robot.scorer.isLoaded());
            multTelemetry.update();

        }


        @Override
        public void stop(){

        /*
                    Y O U R   C O D E   H E R E
                                                   */
        }
}