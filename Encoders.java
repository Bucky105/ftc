package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name="Encoder Test???")
//@Disabled
public class Encoders extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        backLeft = hardwareMap.get(DcMotor.class, "leftBack");
        backRight = hardwareMap.get(DcMotor.class, "rightBack");
        frontLeft = hardwareMap.get(DcMotor.class, "leftFront");
        frontRight = hardwareMap.get(DcMotor.class, "rightFront");
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
    }
    @Override
    public void start(){runtime.reset();}

    @Override
    public void loop() {
        double bRPower = 0;
        double bLPower = 0;
        double fRPower = 0;
        double fLPower = 0;
        double yValL = -gamepad1.left_stick_y;
        double xValL = gamepad1.left_stick_x;

        if(gamepad1.a){
            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontLeft.setTargetPosition(750);
            backLeft.setTargetPosition(750);
            frontRight.setTargetPosition(750);
            backRight.setTargetPosition(750);
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setPower(0.25);
            backRight.setPower(0.25);
            frontLeft.setPower(0.25);
            frontRight.setPower(0.25);
            while (frontLeft.isBusy() || backLeft.isBusy() || frontRight.isBusy() || backRight.isBusy()){}
            backLeft.setPower(0);
            backRight.setPower(0);
            frontLeft.setPower(0);
            frontRight.setPower(0);
        }else{
            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontLeft.setTargetPosition(0);
            backLeft.setTargetPosition(0);
            frontRight.setTargetPosition(0);
            backRight.setTargetPosition(0);
            if (Math.abs(xValL) > Math.abs(yValL)) {
                //Strafing Left & Right
                bLPower = xValL * (-1);
                fLPower = xValL;
                bRPower = xValL;
                fRPower = xValL * (-1);
            } else {
                //Driving Forwards & Backwards
                fLPower = yValL;
                bLPower = yValL;
                fRPower = yValL;
                bRPower = yValL;
            }
            backLeft.setPower(bLPower);
            backRight.setPower(bRPower);
            frontLeft.setPower(fLPower);
            frontRight.setPower(fRPower);
        }

    }
}
