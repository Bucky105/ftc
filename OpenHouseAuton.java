package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.INCH;

@Autonomous(name = "OpenHouseAuton")
@Disabled
public class OpenHouseAuton extends OpMode {
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
    public void loop() {
        distance = sensorRange.getDistance(INCH) / 12;
        double intakespeed = 0;
        double outtakespeed = 0;
        sleep(500);


    }
    public void driveForward(){

    }
    public void driveBackward(){

    }
    public void strafeLeft(){

    }
    public void sleep(int millis){
        ElapsedTime currentTime = runtime;
        while(currentTime.milliseconds() + millis > runtime.milliseconds()){}
    }

}
