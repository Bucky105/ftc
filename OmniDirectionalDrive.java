package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name="OmniDirectionalDrive")
//@Disabled
public class OmniDirectionalDrive extends OpMode
{
    //Declare Variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor intake = null;
    private DcMotor outTake = null;
    BNO055IMU imu;

    // State used for updating telemetry
    Orientation angles;
    Acceleration gravity;

    @Override
    public void init() {
        //Set up hardware
        telemetry.addData("Status", "Initialized");
        backLeft = hardwareMap.get(DcMotor.class, "leftBack");
        backRight = hardwareMap.get(DcMotor.class, "rightBack");
        frontLeft = hardwareMap.get(DcMotor.class, "leftFront");
        frontRight = hardwareMap.get(DcMotor.class, "rightFront");
        //intake = hardwareMap.get(DcMotor.class, "intake_Motor");
        //outTake = hardwareMap.get(DcMotor.class, "outtake_Motor");
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        //intake.setDirection(DcMotor.Direction.FORWARD);
        //outTake.setDirection(DcMotor.Direction.FORWARD);
    }
    @Override
    public void init_loop(){}

    @Override
    public void start(){runtime.reset();}


    @Override
    public void loop() {
        double bRPower = 0;
        double bLPower = 0;
        double fRPower = 0;
        double fLPower = 0;
        double intakePower = 0;
        double outTakePower = 0;
        boolean intakeActive = gamepad1.a;
        boolean outtakeActive = gamepad1.b;

        double yValL = -gamepad1.left_stick_y;
        double xValL = gamepad1.left_stick_x;
        double xValR = gamepad1.right_stick_x;

        if(intakeActive){
            intakePower = 1;
        }
        if(outtakeActive){
            outTakePower = 1;
        }

        if(Math.abs(xValR) < 0.1) {
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
        }else{
            //Turning Left & Right
            bLPower = xValR;
            fLPower = xValR;
            bRPower = xValR * (-1);
            fRPower = xValR * (-1);
        }
        backLeft.setPower(bLPower);
        backRight.setPower(bRPower);
        frontLeft.setPower(fLPower);
        frontRight.setPower(fRPower);
       // intake.setPower(intakePower);
        //outTake.setPower(outTakePower);
        telemetry.addData("Motors", "FR: " + fRPower + " FL: " + fLPower + " BR: " + bRPower + " BL: " + bLPower);
    }

    @Override
    public void stop(){}
}
