package org.firstinspires.ftc.teamcode.Hardware.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.DashConstants.Unfixed.d;
import static org.firstinspires.ftc.teamcode.DashConstants.Unfixed.i;
import static org.firstinspires.ftc.teamcode.DashConstants.Unfixed.p;
import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.angleMode.DEGREES;
import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.closestAngle;
import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.cos;
import static org.firstinspires.ftc.teamcode.Utilities.MathUtils.sin;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;

import org.firstinspires.ftc.teamcode.Hardware.Sensors.Color_Sensor;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Distance_Sensor;
import org.firstinspires.ftc.teamcode.Hardware.Sensors.Gyro;
import org.firstinspires.ftc.teamcode.Utilities.MathUtils;
import org.firstinspires.ftc.teamcode.Utilities.PID;
import org.firstinspires.ftc.teamcode.Z.Side;
import org.firstinspires.ftc.teamcode.Z.Vision.Camera;
import org.firstinspires.ftc.teamcode.Z.Vision.DetectionPipeline;
import org.opencv.core.Point;

public class Mecanum {

    public DcMotor fr,fl,br,bl;
    public Color_Sensor flColor, frColor, blColor, brColor;
    public PID pid;
    public Gyro gyro;
    public static ElapsedTime time = new ElapsedTime();
    private final ElapsedTime timeOut = new ElapsedTime();
    private final ElapsedTime loopTimer1 = new ElapsedTime();
    public Camera cam;
    public double dist = 0;



    public Mecanum(){
//        pid = new PID(p,i,d);
        pid = new PID(0.03, 0, 0.002);
        gyro = new Gyro();

        initChassis();
    }




    public void initChassis() {


        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        flColor = new Color_Sensor();
        frColor = new Color_Sensor();
        blColor = new Color_Sensor();
        brColor = new Color_Sensor();
        flColor.init("flColor");
        frColor.init("frColor");
        blColor.init("blColor");
        brColor.init("brColor");
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

    public void countDistReset(){
        dist = (fr.getCurrentPosition() + fl.getCurrentPosition() + br.getCurrentPosition() + bl.getCurrentPosition()) / 4.0;
    }

    public void startCamera(DetectionPipeline pipeline){
        cam = new Camera("Camera", pipeline);
    }

    public double countDist(){
        return((fr.getCurrentPosition() + fl.getCurrentPosition() + br.getCurrentPosition() + bl.getCurrentPosition()) / 4.0 - dist);
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


    public void strafe(double power, double ticks, double targetAngle, double strafeAngle, double marginOfError){

        // Reset our encoders to 0
        resetMotors();
        timeOut.reset();



        strafeAngle = strafeAngle - 90;
        targetAngle = targetAngle - 180;

        targetAngle = closestAngle(targetAngle, gyro.angle());

        // Calculate our x and y powers
        double xPower = cos(strafeAngle, DEGREES);
        double yPower = sin(strafeAngle, DEGREES);

        // Calculate the distances we need to travel
        double xDist = xPower * ticks;
        double yDist = yPower * ticks;



        // Initialize our current position variables
        Point curPos;
        double curHDist = 0;

        while ((curHDist < ticks || gyro.absAngularDist(targetAngle) > marginOfError) && timeOut.seconds() < ticks/500){
            gyro.update();
            curPos = getPosition();


            curHDist = Math.hypot(curPos.x, curPos.y);
            Point shiftedPowers = MathUtils.shift(new Point(xPower, yPower), -gyro.angle());


            if(curHDist < ticks){

                setDrivePower(power, shiftedPowers.x, pid.update(targetAngle - gyro.angle()), shiftedPowers.y);
            }else{
                setDrivePower(power, 0, pid.update(targetAngle - gyro.angle()), 0);
            }




        }
        setAllPower(0);
    }

    public void strafe(double power, double ticks, double targetAngle, double strafeAngle){
        strafe(power, ticks, targetAngle, strafeAngle, 8);
    }

    public void turn(double targetAngle, double marginOfError){
        targetAngle = targetAngle - 180;
        targetAngle = closestAngle(targetAngle, gyro.angle());
        while(gyro.angle() + marginOfError > targetAngle && gyro.angle() - marginOfError < targetAngle) {
            gyro.update();
            pid.update(gyro.angle() - targetAngle);
        }
    }
    public void turn(double targetAngle){
        turn(targetAngle, 8);
    }

    public void sleep(double time, ElapsedTime timer){
        timer.reset();
        while(timer.seconds() < time){
        }
    }

    public void cycle(Intake intake, ScoringMechanism scorer, Distance_Sensor distance,int cycleNo){
        if(Side.red){
            multTelemetry.addData("isLoaded", scorer.isLoaded());
            multTelemetry.update();
            strafe(.6,600,270,274);
            strafe(.3,300,270,274);

            if (cycleNo != 1){
                strafe(.4,500,270,274);
                strafe(.4,400,270,0);
            }

            intake.autoSpin();
            scorer.updateBucketSensor();
            loopTimer1.reset();
            while (!scorer.isLoaded()) {

                distance.distanceUpdate();
                scorer.updateBucketSensor();
                intake.updateEncoders();

                if(!distance.isChanging){
                    intake.autoBackSpin();
                    strafe(.6,100,270,90);
                    multTelemetry.addData("Stage", "Retreating");
                    loopTimer1.reset();
                }
                if (!intake.jammed() && loopTimer1.seconds()<5) {
                    intake.autoSpin();
                    strafe(.15, 100, 270, 270);
                    multTelemetry.addData("Stage", "Not Jammed, going slowly forwards");
                }else{
                    intake.autoBackSpin();
                    strafe(.6, 100, 270, 90);
                    multTelemetry.addData("Stage", "Retreating");
                    loopTimer1.reset();
                }

                multTelemetry.addData("isLoaded", scorer.isLoaded());
                multTelemetry.addData("Red", scorer.bucketSensor.getRedCacheValue());
                multTelemetry.addData("Green", scorer.bucketSensor.getGreenCacheValue());
                multTelemetry.update();
            }

            intake.stop();
            intake.autoBackSpin();

            multTelemetry.addData("Stage", "Going Into Wall");
            multTelemetry.update();
            strafe(.2,100,270,180);
            if (cycleNo != 1){
                strafe(.4,400,270,180);
            }

            multTelemetry.addData("Stage", "Leaving Warehouse");
            multTelemetry.update();
            strafe(.6,1700,270,95);

            intake.stop();
            multTelemetry.addData("Stage", "Out of warehouse");
            multTelemetry.update();
            scorer.autoTop();
            strafe(.5, 300, 270, 10);
            strafe(.4, 300, 190, 5);
            strafe(.2,150,205,45);
            scorer.autoDeposit();
            strafe(.6, 600, 260, 185);
            strafe(.5, 600, 270, 190);
            strafe(.4,300,270,180);



        }else if(Side.blue){
            multTelemetry.addData("isLoaded", scorer.isLoaded());
            multTelemetry.update();
            strafe(.6,400,90,95);
            strafe(.3,300,90,95);

            if (cycleNo != 1){
                strafe(.4,500,90,90);
                strafe(.4,600,90,0);
            }
            intake.autoSpin();
            scorer.updateBucketSensor();

            loopTimer1.reset();
            while (!scorer.isLoaded()) {

                distance.distanceUpdate();
                scorer.updateBucketSensor();
                intake.updateEncoders();

                if(!distance.isChanging){
                    intake.autoBackSpin();
                    strafe(.6,100,90,270);
                    multTelemetry.addData("Stage", "Retreating");
                    loopTimer1.reset();
                }
                if (!intake.jammed() && loopTimer1.seconds()<3) {
                    intake.autoSpin();
                    strafe(.15, 100, 90, 90);
                    multTelemetry.addData("Stage", "Not Jammed, going slowly forwards");
                }else{
                    intake.autoBackSpin();
                    strafe(.6, 100, 90, 270);
                    multTelemetry.addData("Stage", "Retreating");
                    loopTimer1.reset();
                }

                multTelemetry.addData("isLoaded", scorer.isLoaded());
                multTelemetry.addData("Red", scorer.bucketSensor.getRedCacheValue());
                multTelemetry.addData("Green", scorer.bucketSensor.getGreenCacheValue());
                multTelemetry.update();
            }

            intake.stop();
            intake.autoBackSpin();

            if (cycleNo != 1){
                strafe(.5,200,90,270);
            }

            multTelemetry.addData("Stage", "Going Into Wall");
            multTelemetry.update();
            strafe(.2,100,90,180);
            if (cycleNo != 1){
                strafe(.4,600,90,180);
            }


            multTelemetry.addData("Stage", "Leaving Warehouse");
            multTelemetry.update();

            strafe(.6,1250,90,265);

            if (cycleNo == 1){
                strafe(.6,200,90,265);
            }

            intake.stop();

            multTelemetry.addData("Stage", "Out of warehouse");
            multTelemetry.update();

            scorer.autoTop();
            if (cycleNo != 1) {
                strafe(.4,550,165,358);
            }else{
                strafe(.4,550,165,355);
            }

            strafe(.2,100,165,0);
            scorer.autoDeposit();
            strafe(.6, 600, 90, 170);
            strafe(.4,400,90,180);
        }
    }
}