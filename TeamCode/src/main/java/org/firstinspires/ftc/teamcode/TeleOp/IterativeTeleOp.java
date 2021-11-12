package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Arm;
import org.firstinspires.ftc.teamcode.Hardware.Controls.Controller;
import org.firstinspires.ftc.teamcode.Hardware.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;


@TeleOp(name="Iterative TeleOp", group="Iterative Opmode")
public class IterativeTeleOp extends OpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    Mecanum evansChassis;
    double power;
    PID evansChassisPid;
    Controller controller;
    IMU imu;
    Arm arm;
    DuckWheel duckSpinner;
    private double setPoint = 0;
    private boolean wasTurning;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        setOpMode(this);

        power = 0.6;
        imu = new IMU("imu");
        evansChassis = new Mecanum();
        evansChassisPid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.derivativeWeight);
        controller = new Controller(gamepad1);
        duckSpinner = new DuckWheel();
        arm = new Arm();

        multTelemetry.addData("Status", "Initialized");
        multTelemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

        imu.getAngle();


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
        double correction = evansChassisPid.update(imu.getAngle() - setPoint, true);
        double rotation;
        if(!(controller.rightStick().x == 0)){
            rotation = -controller.rightStick().x;
            wasTurning = true;
        }else{
            if(wasTurning){
                setPoint = imu.getAngle();
                wasTurning = false;
            }
            rotation = correction;
        }

           if(controller.RTrigger.press()){
                power = 0.3;
            }else{
            power = 0.6;
        }

        if(controller.cross.toggle()){
            duckSpinner.spin(Unfixed.duckWheelSpeed);
            if(controller.RB.press()){
                duckSpinner.spin(Unfixed.duckWheelSpeed - .25);
            }
        }else{
            duckSpinner.spin(0.0);
        }

        if(controller.LB.press()){
            arm.slideUp();
        }else if(controller.RB.press()){
            arm.slideDown();
        }else{
            arm.slideStop();
        }

        if(controller.LTrigger.press()){
            arm.armUp();
        }else if(controller.RTrigger.press()){
            arm.slideDown();
        }else{
            arm.slideStop();
        }


        double drive = -MathUtils.shift(controller.leftStick(), imu.getAngle()).y;
        double strafe = MathUtils.shift(controller.leftStick(), imu.getAngle()).x;
        double turning = rotation;
        evansChassis.setDrivePower(power,strafe,turning,drive);






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