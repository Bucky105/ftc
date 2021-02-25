package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Shoots")
//@Disabled
public class FrickingShoot extends OpMode
{
    //Declare Variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorA = null;
    private DcMotor motorB = null;
    private DcMotor SM = null;
    boolean runningy = false;
    boolean runninga = false;
    boolean runningb = false;
    boolean runningx = false;

    @Override
    public void init() {
        //Set up hardware
        telemetry.addData("Status", "Initialized");
        motorA = hardwareMap.get(DcMotor.class, "motor_A");
        motorB = hardwareMap.get(DcMotor.class, "motor_B");
        motorA.setDirection(DcMotor.Direction.REVERSE);
        motorB.setDirection(DcMotor.Direction.FORWARD);
        SM = hardwareMap.get(DcMotor.class, "StorageMotor");
        SM.setDirection(DcMotor.Direction.REVERSE);
        SM.setPower(1);
    }
    @Override
    public void init_loop(){}

    @Override
    public void start(){runtime.reset();}


    @Override
    public void loop() {

        double motorAPower = 0;
        double motorBPower = 0;
        if(gamepad1.right_bumper){
            runningy = false;
             runninga = false;
             runningb = false;
             runningx = false;
        }
        if(gamepad1.y){runningy = true;

            runninga = false;
            runningb = false;
            runningx = false;}
        if(gamepad1.a){runninga = true;
            runningy = false;

            runningb = false;
            runningx = false;}
        if(gamepad1.b){runningb = true;
            runningy = false;
            runninga = false;

            runningx = false;}
        if(gamepad1.x){runningx = true;
            runningy = false;
            runninga = false;
            runningb = false;
           }
        if(runningy){
            motorAPower = 1;
            motorBPower = 0.9;
        }
        if(runningx){
            motorAPower = 0.55;
            motorBPower = 0.45;
        }
        if(runninga){
            motorAPower = 0.6;
            motorBPower = 0.5;
        }
        if(runningb){
            motorAPower = 0.5;
            motorBPower = 0.4;
        }
        motorA.setPower(motorAPower);
        motorB.setPower(motorBPower);
    }

    @Override
    public void stop(){}
}
