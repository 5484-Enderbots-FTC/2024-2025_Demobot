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

import java.math.MathContext.*;

import org.firstinspires.ftc.teamcode.PATCHY.hardwareCS;
import org.firstinspires.ftc.teamcode.PATCHY.pocCode.INTAKE.IntakeServo;
//


@TeleOp(name = "Demobot TeleOp", group = "Linear Opmode")
//@Disabled
public class CenterstageTeleop extends LinearOpMode {

    public enum slideState{
        free,
        back,
        forward
    }

    @Override
    public void runOpMode() throws InterruptedException {
        hardwareCS drive = new hardwareCS(hardwareMap);
        slideState slides = slideState.free;
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


           /* switch (slides){
                case free:
                    drive.slidesMtr.setPower(gamepad1.right_stick_y * (1-gamepad1.right_trigger * .7));
                    if (Math.abs(drive.slidesMtr.getCurrentPosition()) >= 1700) {
                        slides = slideState.back;
                        break;
                    } else if ((Math.abs(drive.slidesMtr.getCurrentPosition()) <= 10)) {
                        slides = slideState.forward;
                        break;
                    }
                case back:
                    if (gamepad1.right_stick_y > 0.0 && Math.abs(drive.slidesMtr.getCurrentPosition()) >= 1700) {
                    drive.slidesMtr.setPower(gamepad1.right_stick_y * (1 - (gamepad1.right_trigger * .7)));
                    }
                    if (Math.abs(drive.slidesMtr.getCurrentPosition()) <= 1600) {
                        slides = slideState.free;
                        break;
                    }
                    break;
                case forward:
                    if (gamepad1.right_stick_y < 0.0 && Math.abs(drive.slidesMtr.getCurrentPosition()) <= 10) {
                        drive.slidesMtr.setPower(gamepad1.right_stick_y * (1 - gamepad1.right_trigger * .7));
                    }
                    if (Math.abs(drive.slidesMtr.getCurrentPosition()) >= 100) {
                        slides = slideState.free;
                        break;
                    }
                    break;
                default:
                    slides = slideState.free;
            } */

/* if (Math.abs(gamepad1.right_stick_y) > 0.0){
                drive.slidesMtr.setPower(gamepad1.right_stick_y * (1 - gamepad1.right_trigger * .5));
            } else {
                drive.slidesMtr.setPower(0);
            }*/

            if (Math.abs(gamepad1.right_stick_y) > 0.0 && Math.abs(drive.slidesMtr.getCurrentPosition()) <= 1700 && Math.abs(drive.slidesMtr.getCurrentPosition()) >= 0){
                drive.slidesMtr.setPower(gamepad1.right_stick_y * (1-gamepad1.right_trigger * .7));
            } else if (gamepad1.right_stick_y > 0.0 && Math.abs(drive.slidesMtr.getCurrentPosition()) >= 1700) {
                drive.slidesMtr.setPower(gamepad1.right_stick_y * (1 - (gamepad1.right_trigger * .7)));
            } else if (gamepad1.right_stick_y < 0.0 && Math.abs(drive.slidesMtr.getCurrentPosition()) <= 10) {
                drive.slidesMtr.setPower(gamepad1.right_stick_y * (1-gamepad1.right_trigger * .7));
            } else {
                drive.slidesMtr.setPower(0);
            }

        }

    }
}
