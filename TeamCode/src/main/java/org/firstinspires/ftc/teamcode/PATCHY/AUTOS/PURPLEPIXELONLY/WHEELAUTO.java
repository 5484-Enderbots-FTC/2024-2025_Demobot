package org.firstinspires.ftc.teamcode.PATCHY.AUTOS.PURPLEPIXELONLY;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.PATCHY.hardwareCS;
import org.firstinspires.ftc.teamcode.RoadrunnerUtilStuff.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.vision.VisionPortal;

@Config
@Autonomous(name = "Wheel Testing Auto", group = "Test Autos")
public class WHEELAUTO extends LinearOpMode {

    private VisionPortal portal;
    private org.firstinspires.ftc.teamcode.PATCHY.PIPELINES.redpropPipeline redpropPipeline;
    DcMotorEx mtrI;

    private String auto;
    @Override
    public void runOpMode() throws InterruptedException {

        hardwareCS drive = new hardwareCS(hardwareMap);

        drive.inithardware();



        Pose2d startPose = new Pose2d(0,0, Math.toRadians(0));

        TrajectorySequence move = drive.trajectorySequenceBuilder(startPose)
                .forward(60)
                .turn(Math.toRadians(60))
                .build();

        while (!isStarted() && !isStopRequested()) {

            telemetry.addLine("waitForStart");
            telemetry.update();
        }

        waitForStart();

        if (isStopRequested()) return;

        drive.setPoseEstimate(new Pose2d(22,0,Math.toRadians(0)));
        telemetry.addData("Updated start pose estimate: ", drive.getPoseEstimate());
        telemetry.update();
        drive.followTrajectorySequence(move);
        telemetry.addData("Currently at: ", drive.getPoseEstimate());
        telemetry.update();

        while (!isStopRequested() && opModeIsActive()) ;

    }
}
