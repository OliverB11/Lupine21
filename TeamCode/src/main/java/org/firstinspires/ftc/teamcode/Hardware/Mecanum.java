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

import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Utilities.Unfixed;
import org.opencv.core.Point;

public class Mecanum {

    private DcMotor fr,fl,br,bl;
    public IMU imu;
    public PID pid;
    public static ElapsedTime time = new ElapsedTime();

    public Mecanum(){
        imu = new IMU("imu");
        pid = new PID(Unfixed.proportionalWeight, Unfixed.integralWeight, Unfixed.derivativeWeight);
        initRobot();
    }



    public void initRobot() {


        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
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

        multTelemetry.addData("Front Left: ", flPower);
        multTelemetry.addData("Front Right: ", frPower);
        multTelemetry.addData("Back Left: ", blPower);
        multTelemetry.addData("Back Right: ", brPower);
        multTelemetry.update();

    }
    public void strafe(double ticks, double targetAngle, double strafeAngle){

        // Reset our encoders to 0
        resetMotors();

        targetAngle = closestAngle(targetAngle, imu.getAngle());

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
            curPos = getPosition();
            curHDist = Math.hypot(curPos.x, curPos.y);

            Point shiftedPowers = MathUtils.shift(new Point(xPower, yPower), imu.getAngle());
            setDrivePower(0.3, shiftedPowers.x, pid.update(imu.getAngle() - targetAngle), shiftedPowers.y);

            // Log some data out for debugging
            multTelemetry.addData("curPos", "(" + curPos.x + ", " + curPos.y + ")");
            multTelemetry.addData("curHDist", curHDist);
            multTelemetry.update();
        }
        setAllPower(0);
    }

    public void turn(double power, double targetAngle, double seconds){
        targetAngle = MathUtils.closestAngle(targetAngle, imu.getAngle());
        time.reset();
        double startPos = imu.getAngle();
        double current;
        double distance = targetAngle - imu.getAngle();

        current = imu.getAngle() - startPos;
        setDrivePower(power, 0, pid.update(imu.getAngle() - targetAngle, true), 0);

    }

}