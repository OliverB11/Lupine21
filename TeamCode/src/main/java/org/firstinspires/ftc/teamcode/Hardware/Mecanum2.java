package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.IMU;
import org.firstinspires.ftc.teamcode.Utilities.PID;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

public class Mecanum2 {

    private DcMotor fr,fl,br,bl;

    public static ElapsedTime time = new ElapsedTime();

    public Mecanum2(){
        initRobot();
    }



    public void initRobot() {


        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        resetMotors();

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

        multTelemetry.addData("Front Left:", flPower);
        multTelemetry.addData("Front Right:", frPower);
        multTelemetry.addData("Back Left:", blPower);
        multTelemetry.addData("Back Right:", brPower);
        multTelemetry.update();

    }
}