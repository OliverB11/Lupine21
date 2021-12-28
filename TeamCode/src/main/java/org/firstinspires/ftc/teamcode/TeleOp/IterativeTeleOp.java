package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.Hardware.Controls.Controller;
import org.firstinspires.ftc.teamcode.Hardware.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.ScoringMechanism;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;
import org.firstinspires.ftc.teamcode.Z.Side;

import static java.lang.Math.floorMod;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import android.transition.Slide;


@TeleOp(name="TeleOp", group="Iterative Opmode")
public class IterativeTeleOp extends OpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public static Mecanum robot;
    double power;
    DuckWheel duckSpinner;
    Intake intake;
    Controller controller;
    Controller controller2;
    ScoringMechanism scorer;
    double setPoint = 360;
    boolean wasTurning;

    enum SlideState {
        TOP, MIDDLE, BOTTOM, DEPOSIT, DRIVING_FROM_UP, DRIVING_FROM_INTAKE, INTAKE, NONE
    }

    SlideState currentSlideState = SlideState.INTAKE;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);

        power = 0.6;
        robot = new Mecanum();
        controller = new Controller(gamepad1);
        controller2 = new Controller(gamepad2);
        duckSpinner = new DuckWheel();
        intake = new Intake();
        scorer = new ScoringMechanism();

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
        robot.gyro.update();


// PID


        double correction = robot.pid.update(robot.gyro.rawAngle() - setPoint);

        if (!(controller.rightStick().x == 0)) {
            rotation = controller.rightStick().x;
            wasTurning = true;
        } else if (wasTurning && robot.gyro.rateOfChange() < 4) {
            rotation = controller.rightStick().x;
        } else {
            if (wasTurning) {
                setPoint = robot.gyro.rawAngle();
                wasTurning = false;
            }
            rotation = correction;
        }

// Speed Control
        if (controller.RTrigger.press()) {
            power = 0.2;
        } else {
            power = 0.8;
        }

//gyro reset ability
        if (controller.share.tap()) {
            robot.gyro.reset();
        }

// Stuff

    //Controller1 Stuff
        if (controller.circle.tap() && !scorer.armUp) {
            scorer.intake();
            intake.spin();
        } else if (controller.square.tap() && !scorer.armUp && currentSlideState == SlideState.INTAKE) {
            scorer.intake();
            intake.backSpin();
        } else {
        }


            if (controller.left.press()) {
                setPoint = MathUtils.closestAngle(90, robot.gyro.rawAngle());
            }
            if (controller.up.press()) {
                setPoint = MathUtils.closestAngle(0, robot.gyro.rawAngle());
            }
            if (controller.down.press()) {
                setPoint = MathUtils.closestAngle(180, robot.gyro.rawAngle());
            }
            if (controller.right.press()) {
                setPoint = MathUtils.closestAngle(270, robot.gyro.rawAngle());
            }


            //Controller 2 Stuff
            if (Side.blue) {
                if (controller2.cross.toggle()) {
                    duckSpinner.blueSpin(.4);
                } else {
                    duckSpinner.stop();
                }
            } else if (Side.red) {
                if (controller2.cross.toggle()) {
                    duckSpinner.blueSpin(-.4);
                } else {
                    duckSpinner.stop();
                }
            }

            // Slide Stuff

            if (controller2.up.tap()) {
                currentSlideState = SlideState.TOP;
                scorer.time.reset();
            }
            if (controller2.left.tap()) {
                currentSlideState = SlideState.MIDDLE;
                scorer.time.reset();
            }
            if (controller2.right.tap()) {
                currentSlideState = SlideState.BOTTOM;
                scorer.time.reset();
            }
            if (controller2.down.tap()) {
                currentSlideState = SlideState.DEPOSIT;
                scorer.time.reset();
            }

            switch (currentSlideState) {
                case TOP:
                    scorer.top();
                    break;

                case MIDDLE:
                    scorer.middle();
                    break;

                case BOTTOM:
                    scorer.bottom();
                    break;

                case DEPOSIT:
                    scorer.deposit();
                    break;

                case DRIVING_FROM_UP:
                    scorer.drivingFromUp();

                case DRIVING_FROM_INTAKE:
                    scorer.drivingFromIntake();

                case INTAKE:
                    scorer.intake();

                case NONE:
                    break;
            }


//Movement control
            double drive = -MathUtils.shift(controller.leftStick(), robot.gyro.rawAngle()).y;
            double strafe = MathUtils.shift(controller.leftStick(), robot.gyro.rawAngle()).x;
            double turning = -rotation;

            robot.setDrivePower(power, strafe, turning, drive);


//Telemetry


        }


        @Override
        public void stop(){

        /*
                    Y O U R   C O D E   H E R E
                                                   */
        }
}