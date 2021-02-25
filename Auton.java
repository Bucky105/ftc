package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Auton")
@Disabled
public class Auton extends OpMode
{
    //Declare Variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private int mode = 0;
    double bRPower = 0;
    double bLPower = 0;
    double fRPower = 0;
    double fLPower = 0;
    double motorSpeed = 0.75;

    @Override
    public void init() {
        //Set up hardware
        telemetry.addData("Status", "Initialized");
        backLeft = hardwareMap.get(DcMotor.class, "back_Left");
        backRight = hardwareMap.get(DcMotor.class, "back_Right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_Left");
        frontRight = hardwareMap.get(DcMotor.class, "front_Right");
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void init_loop(){}

    @Override
    public void start(){runtime.reset();}


    @Override
    public void loop() {
        switch (mode){
            case 1:
                driveForward();

            break;
            case 2:

            break;
            case 3:

            break;
        }
    }

    @Override
    public void stop(){}

    public void driveForward(){
        fLPower = motorSpeed;
        bLPower = motorSpeed;
        fRPower = motorSpeed;
        bRPower = motorSpeed;
        updateMotors();
    }
    public void driveBackward(){
        fLPower = -motorSpeed;
        bLPower = -motorSpeed;
        fRPower = -motorSpeed;
        bRPower = -motorSpeed;
        updateMotors();
    }
    public void strafeLeft(){
        fLPower = -motorSpeed;
        bLPower = motorSpeed;
        fRPower = motorSpeed;
        bRPower = -motorSpeed;
        updateMotors();
    }
    public void strafeRight(){
        fLPower = motorSpeed;
        bLPower = -motorSpeed;
        fRPower = -motorSpeed;
        bRPower = motorSpeed;
        updateMotors();
    }
    public void updateMotors(){
        backLeft.setPower(bLPower);
        backRight.setPower(bRPower);
        frontLeft.setPower(fLPower);
        frontRight.setPower(fRPower);
    }

}
