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

        if (DuckPosition.duckPos == 1) {
            multTelemetry.addData("Auto", "Blue Back Left");


// DUCK IN MIDDLE

        } else if (DuckPosition.duckPos == 2) {
            multTelemetry.addData("Auto", "Blue Back Middle");

// DUCK ON RIGHT

        } else if (DuckPosition.duckPos == 3) {
            multTelemetry.addData("Auto", "Blue Back Right");

// NO DUCK
        } else {
            multTelemetry.addData("Auto", "Blue Back None");

        }

        multTelemetry.update();
        waitForStart();


        if (opModeIsActive()){
            robot.gyro.reset();
            //WRITE AUTOS HERE
            if(DuckPosition.duckPos == 3){
                //RIGHT
                //Top
                robot.strafe(.2, 50,180,0);
                robot.strafe(.3,400,180,90);
                robot.strafe(.2,50,180,180);
                robot.strafe(.5,1200,180,295);
                robot.strafe(.2, 50,180,180);
                robot.strafe(.2,75,180,135);
                robot.strafe(.2,100,180,0);
                scorer.autoTop();
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2500,180,100);
                robot.strafe(.2,400,180,100);
                duckWheel.redSpin(.2);
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,1000,180,315);
                robot.strafe(.3,800,0,90);
                robot.strafe(.3,50,0,180);
                while(robot.getColorSensorRed() < Unfixed.howMuchRed){
                    robot.setDrivePower(.3, 0, 0, .4);
                }
                while(robot.getColorSensorRed() > Unfixed.howMuchRed){
                    robot.setDrivePower(.3,0, 0, -.3);
                }
                robot.strafe(.2, 75,0,180);



            }else if(DuckPosition.duckPos == 2){
                //Middle
                //Middle
                robot.strafe(.2, 50,180,0);
                robot.strafe(.3,400,180,90);
                robot.strafe(.2,50,180,180);
                robot.strafe(.5,1200,180,115);
                robot.strafe(0.3,50,180,0);
                scorer.autoMiddle();
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2500,180,100);
                robot.strafe(.2,400,180,100);
                duckWheel.redSpin(.2);
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,1000,180,315);
                robot.strafe(.3,800,0,90);
                robot.strafe(.3,75,0,180);
                while(robot.getColorSensorRed() < Unfixed.howMuchRed){
                    robot.setDrivePower(.3, 0, 0, .4);
                }
                while(robot.getColorSensorRed() > Unfixed.howMuchRed){
                    robot.setDrivePower(.3,0, 0, -.3);
                }
                robot.strafe(.2, 75,0,180);



            }else if(DuckPosition.duckPos == 1){
                //Left
                //Bottom
                robot.strafe(.2, 50,180,0);
                robot.strafe(.3,400,180,90);
                robot.strafe(.2,50,180,180);
                robot.strafe(.5,1200,180,295);
                robot.strafe(.3,75,180,20);
                scorer.autoDeposit();
                robot.sleep(0.5, time);
                robot.strafe(.5,2500,180,100);
                robot.strafe(.2,500,180,100);
                robot.strafe(.2,100,180,180);
                duckWheel.redSpin(.2);
                intake.autoSpin();
                robot.sleep(3.5,time);
                duckWheel.stop();
                robot.strafe(.3,1000,180,270);
                intake.stop();
                robot.strafe(.2,1300,0, 60);
                robot.strafe(.3,200,0,90);
                while(robot.getColorSensorRed() < Unfixed.howMuchRed){
                    robot.setDrivePower(.3, 0, 0, .4);
                }
                while(robot.getColorSensorRed() > Unfixed.howMuchRed){
                    robot.setDrivePower(.3,0, 0, -.3);
                }
                robot.strafe(.2, 75,0,180);

            }

        }
    }
}
