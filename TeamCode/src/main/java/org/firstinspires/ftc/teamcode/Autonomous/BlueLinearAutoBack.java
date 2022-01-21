package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import org.firstinspires.ftc.teamcode.Hardware.DuckWheel;
import org.firstinspires.ftc.teamcode.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Hardware.ScoringMechanism;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;
import org.firstinspires.ftc.teamcode.Z.Side;
import org.firstinspires.ftc.teamcode.Z.Vision.DuckPosition;


@Autonomous(name="BlueLinearAutoBack", group="Autonomous Linear Opmode")
public class BlueLinearAutoBack extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime time = new ElapsedTime();
    Mecanum robot;
    PID pid;
    DuckWheel duckWheel;
    Intake intake;
    ScoringMechanism scorer;


    public void initialize(){
        setOpMode(this);
        Side.red = false;
        Side.blue = true;
        pid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.integralWeight);
        robot = new Mecanum();
        duckWheel = new DuckWheel();
        intake = new Intake();
        scorer = new ScoringMechanism();

        multTelemetry.addData("Drivers", "WAIT");
        multTelemetry.update();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){
        initialize();
        time.reset();
        MathUtils.wait(time, 5);



// DUCK ON LEFT

        if (DuckPosition.duckPos == 3) {
            multTelemetry.addData("Auto", "Blue Back Right");


// DUCK IN MIDDLE

        } else if (DuckPosition.duckPos == 2) {
            multTelemetry.addData("Auto", "Blue Back Middle");

// DUCK ON RIGHT

        } else if (DuckPosition.duckPos == 1) {
            multTelemetry.addData("Auto", "Blue Back Left");

// NO DUCK
        } else {
            multTelemetry.addData("Auto", "Blue Back None");

        }


        multTelemetry.addData("Drivers", "Run");
        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            robot.gyro.reset();
            //WRITE AUTOS HERE
            if(DuckPosition.duckPos == 3){
                //RIGHT
                //Top
                robot.strafe(.5,1400,180,65);
                robot.strafe(.5,180,180,90);
                scorer.autoTop();
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2000, 180,265);
                intake.autoSpin();
                robot.strafe(.2,1500,180,270);
                duckWheel.blueSpin(.2);
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,700,180,90);
                robot.strafe(.3,600,0,270);
                robot.strafe(.3,1000,0,315);
                while(robot.getColorSensorBlue() < 100){
                    robot.setDrivePower(.3, 0, 0, .4);
                }
                while(robot.getColorSensorBlue() > 100){
                    robot.setDrivePower(.3,0, 0, -.3);

                }
                robot.strafe(.2, 75,0,180);




            }else if(DuckPosition.duckPos == 2){
                //Middle
                //Middle
                robot.strafe(.5,1400,180,65);
                robot.strafe(.5,180,180,90);
                scorer.autoMiddle();
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2000, 180,265);
                intake.autoSpin();
                robot.strafe(.2,1500,180,270);
                duckWheel.blueSpin(.2);
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,700,180,90);
                robot.strafe(.3,600,0,270);
                robot.strafe(.3,1000,0,315);
                while(robot.getColorSensorBlue() < 100){
                    robot.setDrivePower(.3, 0, 0, .4);
                }
                while(robot.getColorSensorBlue() > 100){
                    robot.setDrivePower(.3,0, 0, -.3);

                }
                robot.strafe(.2, 75,0,180);

            }else if(DuckPosition.duckPos == 1){
                //Left
                //Bottom
                robot.strafe(.5,1400,180,75);
                robot.strafe(.5,180,180,90);
                scorer.autoBottom();
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2000, 180,265);
                intake.autoSpin();
                robot.strafe(.2,1500,180,270);
                duckWheel.blueSpin(.2);
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,700,180,90);
                robot.strafe(.3,600,0,270);
                robot.strafe(.3,1000,0,315);
                while(robot.getColorSensorBlue() < 100){
                    robot.setDrivePower(.3, 0, 0, .4);
                }
                while(robot.getColorSensorBlue() > 100){
                    robot.setDrivePower(.3,0, 0, -.3);

                }
                robot.strafe(.2, 75,0,180);
                           }

        }
    }
}
