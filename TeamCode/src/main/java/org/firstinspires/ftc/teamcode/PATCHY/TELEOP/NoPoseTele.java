package org.firstinspires.ftc.teamcode.PATCHY.TELEOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.PATCHY.hardwareCS;

@TeleOp
public class NoPoseTele extends LinearOpMode {

    public enum slideState{
        free,
        back,
        forward
    }

    DcMotorEx slidesMtr;
    TouchSensor zeroSlides;
    Boolean memoryBit = true;

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        slidesMtr = hardwareMap.get(DcMotorEx.class, "slides");
        slidesMtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slidesMtr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        zeroSlides = hardwareMap.touchSensor.get("ls");

        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("m1");
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("m2");
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("m4");
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DcMotor backRightMotor = hardwareMap.dcMotor.get("m3");
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DcMotor slidesMtr = hardwareMap.dcMotor.get("slides");
        slidesMtr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideState slides = slideState.free;

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.2; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator *0.6;
            double frontRightPower = (y - x - rx) / denominator * 0.6;
            double backRightPower = (y + x - rx) / denominator;

            telemetry.addLine("bR: "+Double.toString(backRightPower));
            telemetry.addLine("bL: "+Double.toString(backLeftPower));
            telemetry.addLine("fR: " +Double.toString(frontRightPower) );
            telemetry.addLine("fL: "+Double.toString(frontLeftPower) );
            telemetry.addLine(Double.toString(denominator));
            telemetry.update();

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);


          /*  switch (slides){
                case free:
                    if (Math.abs(gamepad1.right_stick_y) > 0.0) {
                        slidesMtr.setPower(gamepad1.right_stick_y * (1 - gamepad1.right_trigger * .7));
                        if (Math.abs(slidesMtr.getCurrentPosition()) >= 1700) {
                            slides = slideState.back;
                            slidesMtr.setPower(0);
                            break;
                        } else if (Math.abs(slidesMtr.getCurrentPosition()) <= 10) {
                            slides = slideState.forward;
                            slidesMtr.setPower(0);
                            break;
                        }
                    } else {
                        slidesMtr.setPower(0);
                    }
                case forward:
                    if (gamepad1.right_stick_y < 0.0){
                        slidesMtr.setPower(gamepad1.right_stick_y * (1 - gamepad1.right_trigger * .7));
                        if (Math.abs(slidesMtr.getCurrentPosition()) >= 300){
                            slides = slideState.free;
                            break;
                        }
                    } else {
                        slidesMtr.setPower(0);
                    }
                case back:
                    if (gamepad1.right_stick_y > 0.0){
                        slidesMtr.setPower(gamepad1.right_stick_y * (1 - gamepad1.right_trigger * .7));
                        if (Math.abs(slidesMtr.getCurrentPosition()) < 1500) {
                            slides = slideState.free;
                            break;
                        }
                    }
                default:
                    slides = slideState.free;
            } */

           if (Math.abs(gamepad1.right_stick_y) > 0.0 && Math.abs(slidesMtr.getCurrentPosition()) <= 1700 && Math.abs(slidesMtr.getCurrentPosition()) >= 0){
                slidesMtr.setPower(gamepad1.right_stick_y * (1-gamepad1.right_trigger * .7));
            } else if (gamepad1.right_stick_y > 0.0 && Math.abs(slidesMtr.getCurrentPosition()) >= 1700) {
                slidesMtr.setPower(gamepad1.right_stick_y * (1 - (gamepad1.right_trigger * .7)));
            } else if (gamepad1.right_stick_y < 0.0 && Math.abs(slidesMtr.getCurrentPosition()) <= 10) {
                slidesMtr.setPower(gamepad1.right_stick_y * (1-gamepad1.right_trigger * .7));
            } else {
                slidesMtr.setPower(0);
            }

            if(!zeroSlides.isPressed() && !memoryBit) {
                slidesMtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slidesMtr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                slidesMtr.setTargetPosition(50);
                memoryBit = true;
            } else if(zeroSlides.isPressed()) {
                memoryBit = false;
            }

            telemetry.addData("Slide State: ", slides);
            telemetry.addData("mtr encoder: ", Math.abs(slidesMtr.getCurrentPosition()));
            telemetry.addData("Slide Power: ", slidesMtr.getPower());
            telemetry.addData("Limit Switch Pressed?: ", zeroSlides.isPressed());

        }
    }
}