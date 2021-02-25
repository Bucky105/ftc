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


@TeleOp(name="trigDriving", group="Iterative Opmode")
@Disabled
public class AdvancedDriving extends OpMode
{
    private DistanceSensor sensorRange;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor RF = null;
    public DcMotor RB = null;
    public DcMotor LF = null;
    public DcMotor LB = null;
    public Servo S1 = null;
    private DcMotor intake = null;
    private DcMotor outTake = null;

    public double rightX = 0;
    public double rightY = 0;
    public double leftX = 0;
    public double speed = 0;
    public double angle = 0;
    public double distance = 0;

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
        intake = hardwareMap.get(DcMotor.class, "intake_Motor");
        outTake = hardwareMap.get(DcMotor.class, "outtake_Motor");

        RF.setDirection(DcMotor.Direction.FORWARD);
        RB.setDirection(DcMotor.Direction.FORWARD);
        LF.setDirection(DcMotor.Direction.REVERSE);
        LB.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        outTake.setDirection(DcMotor.Direction.FORWARD);

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
        distance = sensorRange.getDistance(INCH) / 12;
        double intakespeed = 0;
        double outtakespeed = 0;

        if(gamepad1.a){
            outtakespeed = 1;
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

        double powerRF = speed * Math.sin(angle) - leftX;
        double powerRB = speed * Math.cos(angle) - leftX;
        double powerLF = speed * Math.cos(angle) + leftX;
        double powerLB = speed * Math.sin(angle) + leftX;

        RF.setPower(powerRF);
        RB.setPower(powerRB);
        LF.setPower(powerLF);
        LB.setPower(powerLB);
        intake.setPower(intakespeed);
        outTake.setPower(outtakespeed);
        if(2 < distance && distance < 3){
            telemetry.addData("Bottom Goal : ", "In Range");
        }else{
            telemetry.addData("Bottom Goal : ", "Not In Range");
        }
        telemetry.update();
    }

    @Override
    public void stop() {
    }

}