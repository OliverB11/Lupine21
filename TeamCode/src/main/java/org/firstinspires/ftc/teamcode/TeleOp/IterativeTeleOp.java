package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Controls.Controller1;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum2;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.PIDWeights;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;


@TeleOp(name="Iterative TeleOp", group="Iterative Opmode")
public class IterativeTeleOp extends OpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    Mecanum2 evansChassis;
    double power;
    PID evansChassisPid;
    Controller1 controller;
    IMU imu;
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
        evansChassis = new Mecanum2();
        evansChassisPid = new PID(PIDWeights.proportionalWeight, PIDWeights.integralWeight, PIDWeights.derivativeWeight);
        controller = new Controller1(gamepad1);

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

        if(controller.RTrigger.onPress()){
            power = 0.3;
        }else{
            power = 0.6;
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