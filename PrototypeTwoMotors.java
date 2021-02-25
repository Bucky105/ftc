package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="PrototypeTwoMotors")
@Disabled
public class PrototypeTwoMotors extends OpMode
{
    //Declare Variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorA = null;
    private DcMotor motorB = null;

    @Override
    public void init() {
        //Set up hardware
        telemetry.addData("Status", "Initialized");
        motorA = hardwareMap.get(DcMotor.class, "motor_A");
        motorB = hardwareMap.get(DcMotor.class, "motor_B");
        motorA.setDirection(DcMotor.Direction.REVERSE);
        motorB.setDirection(DcMotor.Direction.FORWARD);
    }
    @Override
    public void init_loop(){}

    @Override
    public void start(){runtime.reset();}


    @Override
    public void loop() {
        double motorAPower = 0;
        double motorBPower = 0;
        boolean AButton = gamepad1.a;
        boolean BButton = gamepad1.b;

        if(AButton){motorAPower = 0.9; motorBPower = 1;}
        if(BButton){motorAPower = 0; motorBPower = 0;}
        motorA.setPower(motorAPower);
        motorB.setPower(motorBPower);
    }

    @Override
    public void stop(){}
}
