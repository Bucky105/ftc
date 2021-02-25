package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.INCH;
@Disabled

@TeleOp(name="DistanceCalculationCode", group="Iterative Opmode")

public class ShooterCalc extends OpMode
{
    private DistanceSensor sensorRange;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor RF = null;
    public DcMotor RB = null;
    public DcMotor LF = null;
    public DcMotor LB = null;
    public Servo S1 = null;

    public double powerRF = 0;
    public double powerRB = 0;
    public double powerLF = 0;
    public double powerLB = 0;
    public double rightX = 0;
    public double rightY = 0;
    public double leftX = 0;
    public double speed = 0;
    public double angle = 0;
    public double distance = 0;
    public double SetDistance = 0;
    public boolean run = false;

    @Override
    public void init() {
        sensorRange = hardwareMap.get(DistanceSensor.class, "sensor_range");

        // you can also cast this to a Rev2mDistanceSensor if you want to use added
        // methods associated with the Rev2mDistanceSensor class.
        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)sensorRange;

        telemetry.addData("Status", "Initialized");

        RF  = hardwareMap.get(DcMotor.class, "rightFront");
        RB = hardwareMap.get(DcMotor.class, "rightBack");
        LF = hardwareMap.get(DcMotor.class, "leftFront");
        LB = hardwareMap.get(DcMotor.class, "leftBack");
        S1 = hardwareMap.get(Servo.class, "servoTest");

        RF.setDirection(DcMotor.Direction.FORWARD);
        RB.setDirection(DcMotor.Direction.FORWARD);
        LF.setDirection(DcMotor.Direction.REVERSE);
        LB.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void init_loop() {
    }

    public void start() {
        runtime.reset();
    }


    @Override
    public void loop() {
distance = sensorRange.getDistance(INCH) / 12;
        if(gamepad1.a) {
            SetDistance = 2.5;
            if(SetDistance > distance){
                while(SetDistance > distance){
                    RF.setPower(-1);
                    RB.setPower(-1);
                    LF.setPower(-1);
                    LB.setPower(-1);
                }
            }
            while(SetDistance < distance){
                RF.setPower(-1);
                RB.setPower(-1);
                LF.setPower(-1);
                LB.setPower(-1);
            }
        }else if(gamepad1.b){
            SetDistance = 5.5;
            run = true;
        }


        rightX = gamepad1.left_stick_x;
        rightY = -gamepad1.left_stick_y;
        leftX = gamepad1.right_stick_x;
        speed = Math.hypot(rightX,rightY);
        angle = Math.atan2(rightY,rightX);

        angle = angle - Math.PI/4;
        if (angle < 0) {
            angle = 2 * Math.PI + angle;
        }

         powerRF = speed * Math.sin(angle) - leftX;
         powerRB = speed * Math.cos(angle) - leftX;
         powerLF = speed * Math.cos(angle) + leftX;
         powerLB = speed * Math.sin(angle) + leftX;

        RF.setPower(powerRF);
        RB.setPower(powerRB);
        LF.setPower(powerLF);
        LB.setPower(powerLB);
        telemetry.addData("SetDistance : ", SetDistance);
        telemetry.addData("Distance : ", distance);
        telemetry.update();

    }

    @Override
    public void stop() {
    }

}
