package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Controls.Controller;
import org.firstinspires.ftc.teamcode.Hardware.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;
import org.firstinspires.ftc.teamcode.Z.Side;

import static java.lang.Math.floorMod;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;


@TeleOp(name="Base TeleOp", group="Iterative Opmode")
public class BaseTeleOp extends OpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public static Mecanum robot;
    double power;
    DuckWheel duckSpinner;
    Intake intake;
    Controller controller;
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
        duckSpinner = new DuckWheel();
        intake = new Intake();

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
        robot.gyro.update();



        // PID
        double correction = robot.pid.update(robot.gyro.rawAngle() - setPoint, true);


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
            power = 0.2;
        } else {
            power = 0.4;
        }

        // Stuff
        if (Side.blue) {
            if (controller.cross.toggle()) {
                duckSpinner.blueSpin(.4);
            } else {
                duckSpinner.stop();
            }
        } else if (Side.red) {
            if (controller.cross.toggle()) {
                duckSpinner.blueSpin(-.4);
            }
        } else {
            duckSpinner.stop();
        }

        if(controller.circle.toggle()){
            intake.spin(1);
        }else{
            intake.spin(0);
        }

        //Movement control
        double drive = -MathUtils.shift(controller.leftStick(), robot.gyro.rawAngle()).y;
        double strafe = MathUtils.shift(controller.leftStick(), robot.gyro.rawAngle()).x;
        double turn = -controller.rightStick().x;

        robot.setDrivePower(power, strafe, -rotation, drive);




    }



    @Override
    public void stop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */
    }
}