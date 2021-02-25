package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.INCH;


@TeleOp(name="MainV1", group="Iterative Opmode")
//@Disabled

public class TeleOpCodeV1 extends OpMode
{
    private DistanceSensor sensorRange;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor RF = null;
    public DcMotor RB = null;
    public DcMotor LF = null;
    public DcMotor LB = null;

    private DcMotor motorA = null;
    private DcMotor motorB = null;

    public double rightX = 0;
    public double rightY = 0;
    public double leftX = 0;
    public double speed = 0;
    public double angle = 0;
    public double distance = 0;
    public double SetDistance = 0;
    public double EncoderDistance = 0;
    public int Ticks = 0;
    public boolean run = false;
    boolean running = false;
    double outtakespeed = 0;

    @Override
    public void init() {
        sensorRange = hardwareMap.get(DistanceSensor.class, "followMe");

        // you can also cast this to a Rev2mDistanceSensor if you want to use added
        // methods associated with the Rev2mDistanceSensor class.
        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)sensorRange;
        telemetry.addData("Status", "Initialized");

        RF  = hardwareMap.get(DcMotor.class, "rightFront");
        RB = hardwareMap.get(DcMotor.class, "rightBack");
        LF = hardwareMap.get(DcMotor.class, "leftFront");
        LB = hardwareMap.get(DcMotor.class, "leftBack");
        motorA = hardwareMap.get(DcMotor.class, "motor_A");
        motorB = hardwareMap.get(DcMotor.class, "motor_B");
        motorA.setDirection(DcMotor.Direction.REVERSE);
        motorB.setDirection(DcMotor.Direction.FORWARD);



        RF.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.REVERSE);
        LF.setDirection(DcMotor.Direction.FORWARD);
        LB.setDirection(DcMotor.Direction.FORWARD);


        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    public void start() {
        runtime.reset();
    }


    @Override
    public void loop() {
        distance = sensorRange.getDistance(INCH);
        double intakespeed = 0;
        run = false;

        if(gamepad1.a){
            SetDistance = 30;
            run = true;
            outtakespeed = 0.25;
            running = true;
        }else if(gamepad1.b){
            SetDistance = 66;
            run = true;
            outtakespeed = 0.45;
            running = true;
        }
        if(run){
            if(distance < 50) {
                EncoderDistance = distance - SetDistance;
                Ticks = (int) Math.round(EncoderDistance / ((3 * 3.141592) / 740));
                LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                LF.setTargetPosition(Ticks);
                LB.setTargetPosition(Ticks);
                RF.setTargetPosition(Ticks);
                RB.setTargetPosition(Ticks);
                LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LB.setPower(0.25);
                RB.setPower(0.25);
                LF.setPower(0.25);
                RF.setPower(0.25);
                runtime.reset();
       // while(LB.isBusy() && LF.isBusy() && RF.isBusy() && RB.isBusy() && opModeIsActive()){idle();}
                motorA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorA.setTargetPosition(2000);
                motorB.setTargetPosition(2000);
                motorA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorA.setPower(outtakespeed);
                motorB.setPower(outtakespeed);
                motorA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motorB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


                LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                LB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                RB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
        }


            LB.setPower(0);
            RB.setPower(0);
            LF.setPower(0);
            RF.setPower(0);
            motorA.setPower(0);
            motorB.setPower(0);
            rightX = gamepad1.left_stick_x;
            rightY = -gamepad1.left_stick_y;
            leftX = gamepad1.right_stick_x;
            speed = Math.hypot(rightX, rightY);
            angle = Math.atan2(rightY, rightX);

            angle = angle - Math.PI / 4;
            if (angle < 0) {
                angle = 2 * Math.PI + angle;
            }

            double powerRF = speed * Math.sin(angle) - leftX;
            double powerRB = speed * Math.cos(angle) - leftX;
            double powerLF = speed * Math.cos(angle) + leftX;
            double powerLB = speed * Math.sin(angle) + leftX;

            RF.setPower(powerRF);
            RB.setPower(powerRB);
            LF.setPower(powerLF);
            LB.setPower(powerLB);


        if(distance < 50){
            telemetry.addData("Shooting : ", "Available");
        }else{
            telemetry.addData("Shooting : ", "Not In Range");
        }
        telemetry.addData("distance : ", distance);

        telemetry.update();
    }

    @Override
    public void stop() {
    }

}