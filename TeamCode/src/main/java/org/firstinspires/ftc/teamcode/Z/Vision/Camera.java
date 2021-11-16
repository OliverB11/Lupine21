package org.firstinspires.ftc.teamcode.Z.Vision;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;

public class Camera {

    private OpenCvCamera webcam;

    public Camera(String id, OpenCvPipeline pipeline){

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId","id",hardwareMap.appContext.getPackageName());

        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class,id),cameraMonitorViewId);

        webcam.openCameraDeviceAsync(() -> webcam.startStreaming(432,240, OpenCvCameraRotation.UPRIGHT));

        webcam.setPipeline(pipeline);


    }




}
