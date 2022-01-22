package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Controls.Controller;
import org.firstinspires.ftc.teamcode.Hardware.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.ScoringMechanism;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Z.Side;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;


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
    public boolean justRumbled = false;

    enum SlideState {
        TOP, MIDDLE, BOTTOM, DEPOSIT, DRIVING, INTAKE, NONE
    }

    SlideState currentSlideState = SlideState.DRIVING;


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




        if (!(controller.rightStick().x == 0)) {
            rotation = controller.rightStick().x;
            wasTurning = true;
        } else if (wasTurning && Math.abs(robot.gyro.rateOfChange()) > 0) {
            rotation = controller.rightStick().x;
        } else {
            if (wasTurning) {
                setPoint = robot.gyro.rawAngle();
                wasTurning = false;
            }
            rotation = robot.pid.update(robot.gyro.rawAngle() - setPoint);
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
            robot.gyro.reset();
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


// Stuff

    //Controller1 Stuff
        if (controller.RTrigger.press() && !scorer.armUp) {
            if(currentSlideState != SlideState.INTAKE) {
                scorer.time.reset();
                intake.time.reset();
            }
            currentSlideState = SlideState.INTAKE;
            intake.spin();
        } else if (controller.LTrigger.press() && !scorer.armUp) {
            if(currentSlideState != SlideState.INTAKE) {
                scorer.time.reset();
                intake.time.reset();
            }
            currentSlideState = SlideState.INTAKE;
            intake.backSpin();
        } else if (!scorer.armUp){
            currentSlideState = SlideState.DRIVING;
            scorer.time.reset();
            intake.stop();
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
                    duckSpinner.blueSpin(.28);
                } else {
                    duckSpinner.stop();
                }
            } else if (Side.red) {
                if (controller2.cross.toggle()) {
                    duckSpinner.redSpin(.28);
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


                case DRIVING:
                    scorer.drivingFromIntake();

                case INTAKE:
                    scorer.intake();

                case NONE:
                    break;
            }


//Movement control
            double drive = -MathUtils.shift(controller.leftStick(), robot.gyro.rawAngle()).y;
            double strafe = MathUtils.shift(controller.leftStick(), robot.gyro.rawAngle()).x;
            double turn = -rotation;

            robot.setDrivePower(power, strafe, turn, drive);


//Telemetry


        }


        @Override
        public void stop(){

        /*
                    Y O U R   C O D E   H E R E
                                                   */
        }
}