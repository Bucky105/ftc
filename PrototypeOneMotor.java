package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="PrototypeOneMotor")
//@Disabled
public class PrototypeOneMotor extends OpMode
{
    //Declare Variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorA = null;


    @Override
    public void init() {
        //Set up hardware
        telemetry.addData("Status", "Initialized");
        motorA = hardwareMap.get(DcMotor.class, "motor_A");

        motorA.setDirection(DcMotor.Direction.REVERSE);

    }
    @Override
    public void init_loop(){}

    @Override
    public void start(){runtime.reset();}


    @Override
    public void loop() {
        double motorAPower = 0;
        boolean AButton = gamepad1.a;
        boolean BButton = gamepad1.b;
        if(AButton){motorAPower = 1;}
        if(BButton){motorAPower = 0;}
        motorA.setPower(motorAPower);
    }
    @Override
    public void stop(){}
}
