package org.firstinspires.ftc.teamcode.PATCHY.AUTOS.PURPLEPIXELONLY;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.PATCHY.hardwareCS;
import org.firstinspires.ftc.vision.VisionPortal;

@Config
@Autonomous(name = "Red Purple Pixel", group = "Purple Pixel Autos")
public class BaseAuto extends LinearOpMode {

    private VisionPortal portal;
    private org.firstinspires.ftc.teamcode.PATCHY.PIPELINES.redpropPipeline redpropPipeline;
    DcMotorEx mtrI;

    private String auto;
    @Override
    public void runOpMode() throws InterruptedException {

        hardwareCS drive = new hardwareCS(hardwareMap);

        drive.inithardware();



        Pose2d startPose = new Pose2d(0,0, Math.toRadians(0));

        while (!isStarted() && !isStopRequested()) {

            telemetry.addLine("waitForStart");
            telemetry.update();
        }

        waitForStart();

        if (isStopRequested()) return;

        drive.setPoseEstimate(new Pose2d(22,0,Math.toRadians(0)));

        while (!isStopRequested() && opModeIsActive()) ;

    }
}
