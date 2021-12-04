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
import org.firstinspires.ftc.teamcode.Z.Side;

import static java.lang.Math.floorMod;
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

        if(!Side.blue && !Side.red){
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


        if(!(controller.rightStick().x == 0)){
            rotation = controller.rightStick().x;
            wasTurning = true;
        }else{
            if(wasTurning){
                setPoint = robot.gyro.rawAngle();
                wasTurning = false;
            }
            rotation = correction;
        }

// Speed Control
        if (controller.RTrigger.press()) {
            power = 0.3;
        } else {
            power = 0.8;
        }

// Stuff

    //Controller1 Stuff
        if(controller.circle.toggle()){
            intake.spin(1);
        }else{
            intake.spin(0);
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

        if(controller2.up.tap()){
            scorer.top();
        }
        if(controller2.left.tap()){
            scorer.middle();
        }
        if(controller2.right.tap()){
            scorer.bottom();
        }




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







//Movement control
        double drive = -MathUtils.shift(controller.leftStick(), robot.gyro.rawAngle()).y;
        double strafe = MathUtils.shift(controller.leftStick(), robot.gyro.rawAngle()).x;
        double turning = -rotation;

        robot.setDrivePower(power, strafe, turning, drive);



//Telemetry
        multTelemetry.addData("Motor Position", scorer.spool.getCurrentPosition());



    }






    @Override
    public void stop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */
    }
}