package org.firstinspires.ftc.teamcode.PATCHY.TELEOP;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.DigitalChannel;
//import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.PATCHY.hardwareCS;
import org.firstinspires.ftc.teamcode.PATCHY.pocCode.INTAKE.IntakeServo;
//


@TeleOp(name = "CenterstageTeleop", group = "Linear Opmode")
//@Disabled
public class CenterstageTeleop extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        hardwareCS drive = new hardwareCS(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        drive.inithardware();

        //start
        waitForStart();

        while (opModeIsActive()) {

             //GAMEPAD 1 CONTROLS
            //set a new pose based off our previous pose utilizing the positions of gamepad sticks
            drive.setWeightedDrivePower(
                    new Pose2d(
                            gamepad1.left_stick_y * (1 - (gamepad1.right_trigger * 0.7)),
                            gamepad1.left_stick_x * (1 - (gamepad1.right_trigger * 0.7)),
                            gamepad1.right_stick_x * (1 - (gamepad1.right_trigger * 0.7))
                    )
            );

            ///////////////////////////////////////////////////////////////////////////////////
            //
            // Put logic for controlling robot here
            //  |||||||||||||||||||||||||||||
            //  VVVVVVVVVVVVVVVVVVVVVVVVVVVVV




        }

    }
}
