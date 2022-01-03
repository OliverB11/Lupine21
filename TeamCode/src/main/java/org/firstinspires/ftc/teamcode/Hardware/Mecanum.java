package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.angleMode.DEGREES;
import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.closestAngle;
import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.cos;
import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.sin;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.Gyro;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;
import org.firstinspires.ftc.teamcode.Z.Vision.Camera;
import org.firstinspires.ftc.teamcode.Z.Vision.DetectionPipeline;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvPipeline;
public class Mecanum {

    public DcMotor fr,fl,br,bl;
    public Gyro gyro;
    public PID pid;
    public static ElapsedTime time = new ElapsedTime();
    public Camera cam;
    private DetectionPipeline pipeline = new DetectionPipeline();




    public Mecanum(){
        gyro = new Gyro();
        pid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.derivativeWeight);
        initRobot();
    }



    public void initRobot() {


        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        cam = new Camera("Camera",pipeline);
        resetMotors();

    }
    public Point getPosition(){
        double yDist = (fr.getCurrentPosition() + fl.getCurrentPosition() + br.getCurrentPosition() + bl.getCurrentPosition()) / 4.0;
        double xDist = (fl.getCurrentPosition() - fr.getCurrentPosition() + br.getCurrentPosition() - bl.getCurrentPosition()) / 4.0;
        return new Point(xDist, yDist);
    }

    public void setAllPower(double power){
        fl.setPower(power);
        fr.setPower(power);
        bl.setPower(power);
        br.setPower(power);
    }

    public void resetMotors(){
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setDrivePower(double power, double strafe, double turn, double drive){

        double frPower = (drive - strafe - turn) * power;
        double flPower = (drive + strafe + turn) * power;
        double brPower = (drive + strafe - turn) * power;
        double blPower = (drive - strafe + turn) * power;

        fr.setPower(frPower);
        fl.setPower(flPower);
        br.setPower(brPower);
        bl.setPower(blPower);


    }
    public void strafe(double power, double ticks, double targetAngle, double strafeAngle){

        // Reset our encoders to 0
        resetMotors();
        strafeAngle = strafeAngle - 90;
        targetAngle= targetAngle - 180;

        targetAngle = closestAngle(targetAngle, gyro.rawAngle());

        // Calculate our x and y powers
        double xPower = cos(strafeAngle, DEGREES);
        double yPower = sin(strafeAngle, DEGREES);

        // Calculate the distances we need to travel
        double xDist = xPower * ticks;
        double yDist = yPower * ticks;

        // Initialize our current position variables
        Point curPos;
        double curHDist = 0;

        while (curHDist < ticks){
            gyro.update();
            curPos = getPosition();
            curHDist = Math.hypot(curPos.x, curPos.y);

            Point shiftedPowers = MathUtils.shift(new Point(xPower, yPower), gyro.rawAngle());
            setDrivePower(power, shiftedPowers.x, pid.update(gyro.rawAngle() - targetAngle), shiftedPowers.y);
            multTelemetry.addData("Target", targetAngle);
            multTelemetry.update();
        }
        setAllPower(0);
    }

    public void turn(double targetAngle, double power){
        targetAngle = closestAngle(targetAngle, gyro.rawAngle());
        while(gyro.rawAngle() != targetAngle) {
            gyro.update();
            pid.update(gyro.rawAngle() - targetAngle);
        }
    }

    public void sleep(double time, ElapsedTime timer){
        timer.reset();
        while(timer.seconds() < time){
        }

    }

}