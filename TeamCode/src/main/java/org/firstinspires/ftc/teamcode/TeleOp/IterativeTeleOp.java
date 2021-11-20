package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Controls.Controller;
import org.firstinspires.ftc.teamcode.Hardware.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;
import static java.lang.Math.floorMod;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;


@TeleOp(name="Iterative TeleOp", group="Iterative Opmode")
public class IterativeTeleOp extends OpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public static Mecanum evansChassis;
    double power;
    Controller controller;
//    Arm arm;
    DuckWheel duckSpinner;
    private double setPoint = 0;
    private boolean wasTurning;
    public static double releaseAngle = 0;
    public static double adjRateOfChange;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);

        power = 0.6;
        evansChassis = new Mecanum();
        controller = new Controller(gamepad1);
        duckSpinner = new DuckWheel();
//        arm = new Arm();

        multTelemetry.addData("Status", "Initialized");
        multTelemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */


    @Override
    public void init_loop() {

        evansChassis.gyro.rawAngle();


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
        controller.controllerUpdate();
        evansChassis.gyro.update();
        double correction = evansChassis.pid.update(evansChassis.gyro.rawAngle() - setPoint, true);
        double rotation;
        double inputTurn;


        if(!(controller.rightStick().x == 0)){
            rotation = -controller.rightStick().x;
            wasTurning = true;
        }else{
            if(wasTurning){
                setPoint = evansChassis.gyro.rawAngle();
                wasTurning = false;
            }
            rotation = correction;
        }

           if(controller.RTrigger.press()){
                power = 0.3;
            }else{
            power = 0.6;
        }

           if(controller.cross.press()){
               duckSpinner.blueSpin(.4);
           }else{
               duckSpinner.stop();
           }


//
//        if(controller.LB.press()){
//            arm.slideUp();
//        }else if(controller.RB.press()){
//            arm.slideDown();
//        }else{
//            arm.slideStop();
//        }
//
//        if(controller.LTrigger.press()){
//            arm.armUp();
//        }else if(controller.RTrigger.press()){
//            arm.armDown();
//        }else{
//            arm.armStop();
//        }


        double drive = -MathUtils.shift(controller.leftStick(), evansChassis.gyro.rawAngle()).y;
        double strafe = MathUtils.shift(controller.leftStick(), evansChassis.gyro.rawAngle()).x;
        double turning = rotation;
  //      evansChassis.setDrivePower(power,strafe,turning,drive);

        if(turning!= 0) {
            inputTurn = turning;
            releaseAngle = evansChassis.gyro.rawAngle();
            adjRateOfChange = MathUtils.pow(evansChassis.gyro.rawAngle(), 2);
        }else if(adjRateOfChange > 1000){
            releaseAngle = evansChassis.gyro.rawAngle();
            adjRateOfChange = MathUtils.pow(evansChassis.gyro.rawAngle(), 2);
            inputTurn = 0;
        }else{
            setPoint = releaseAngle + .5 * .0035 * adjRateOfChange;
            inputTurn = evansChassis.pid.update(MathUtils.closestAngle(setPoint, evansChassis.gyro.rawAngle()) - evansChassis.gyro.rawAngle());
        }



        evansChassis.setDrivePower(power, strafe, inputTurn, drive);







        /*
             ----------- L O G G I N G -----------
                                                */
//        multTelemetry.addData("Status", "TeleOp Running");
//        multTelemetry.addData("Drive:", drive);
//        multTelemetry.addData("Strafe:", strafe);
//        multTelemetry.addData("Turning:", turning);
//        multTelemetry.update();
    }

    @Override
    public void stop() {

        /*
                    Y O U R   C O D E   H E R E
                                                   */

    }
}