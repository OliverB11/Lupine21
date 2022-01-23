package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Mecanum;
import org.firstinspires.ftc.teamcode.Z.Side;

@Autonomous(name="BaseAuto", group="Autonomous Linear Opmode")
public class BaseAuto extends LinearOpMode {
    // Declare Things.
    private ElapsedTime time = new ElapsedTime();
    Mecanum robot;

//Code to run when the driver hits INIT
    public void initialize(){
        setOpMode(this);

        //This should depend on if this is a red or blue auto
        Side.red = true;
        Side.blue = false;

        //Initialize Things
        robot = new Mecanum();
        ElapsedTime time = new ElapsedTime();
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void runOpMode(){

        initialize();
        waitForStart();

//Code to run when the driver hits start
        if (opModeIsActive()){


        }
    }


}