package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.INCH;
@TeleOp(name="AutonV1")
@Disabled
public class AutonV1 extends OpMode {
    private DistanceSensor DistanceSensorTop;
    private DistanceSensor DistanceSensorBottom;
    private DistanceSensor DistanceSensorFront;
    private DistanceSensor DistanceSensorSide;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    public double topDistance = 0;
    public double bottomDistance = 0;
    public double frontDistance = 0;
    public double sideDistance = 0;
    public double SetDistance = 0;
    public double EncoderDistance = 0;
    public int Ticks = 0;
    public boolean run = true;
    private ElapsedTime runtime = new ElapsedTime();
    public int MODE = 0;
    double bRPower = 0;
    double bLPower = 0;
    double fRPower = 0;
    double fLPower = 0;
    double motorSpeed = 0.75;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        backLeft = hardwareMap.get(DcMotor.class, "back_Left");
        backRight = hardwareMap.get(DcMotor.class, "back_Right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_Left");
        frontRight = hardwareMap.get(DcMotor.class, "front_Right");
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        DistanceSensorTop = hardwareMap.get(DistanceSensor.class, "DS_Top");
        DistanceSensorBottom = hardwareMap.get(DistanceSensor.class, "DS_Bottom");
        DistanceSensorFront = hardwareMap.get(DistanceSensor.class, "DS_Front");
        DistanceSensorSide = hardwareMap.get(DistanceSensor.class, "DS_Side");

    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        strafeLeft(100);
        topDistance = DistanceSensorTop.getDistance(INCH);
        bottomDistance = DistanceSensorBottom.getDistance(INCH);
        frontDistance = DistanceSensorFront.getDistance(INCH);
        sideDistance = DistanceSensorSide.getDistance(INCH);
        if(run) {
            if (topDistance < 4.5 && bottomDistance < 4.5) {
                MODE = 4;
            } else if (bottomDistance < 4.5) {
                MODE = 1;
            } else {
                MODE = 0;
            }
            run = false;
        }
        strafeRight(100);
        switch(MODE){
            case 0:
                driveForward(100);
                turnRight(15);
                driveForward(813);
                break;
            case 1:
                driveForward(100);
                turnRight(15);
                driveForward(558);
                turnLeft(-30);
                driveForward(558);
                break;
            case 4:
                driveForward(100);
                turnRight(12);
                driveForward(1419);
                break;
        }

        telemetry.addData("Mode : ", MODE);

    }
    public void driveForward(int TurnTicks){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setTargetPosition(TurnTicks);
        backLeft.setTargetPosition(TurnTicks);
        frontRight.setTargetPosition(TurnTicks);
        backRight.setTargetPosition(TurnTicks);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setPower(0.25);
        backRight.setPower(0.25);
        frontLeft.setPower(0.25);
        frontRight.setPower(0.25);
        while (frontLeft.isBusy() || backLeft.isBusy() || frontRight.isBusy() || backRight.isBusy()) {}
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void driveBackward(int TurnTicks){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setTargetPosition(-TurnTicks);
        backLeft.setTargetPosition(-TurnTicks);
        frontRight.setTargetPosition(-TurnTicks);
        backRight.setTargetPosition(-TurnTicks);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setPower(0.25);
        backRight.setPower(0.25);
        frontLeft.setPower(0.25);
        frontRight.setPower(0.25);
        while (frontLeft.isBusy() || backLeft.isBusy() || frontRight.isBusy() || backRight.isBusy()) {}
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void strafeLeft(int TurnTicks){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setTargetPosition(-TurnTicks);
        backLeft.setTargetPosition(TurnTicks);
        frontRight.setTargetPosition(TurnTicks);
        backRight.setTargetPosition(-TurnTicks);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setPower(0.25);
        backRight.setPower(0.25);
        frontLeft.setPower(0.25);
        frontRight.setPower(0.25);
        while (frontLeft.isBusy() || backLeft.isBusy() || frontRight.isBusy() || backRight.isBusy()) {}
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void strafeRight(int TurnTicks){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setTargetPosition(TurnTicks);
        backLeft.setTargetPosition(-TurnTicks);
        frontRight.setTargetPosition(-TurnTicks);
        backRight.setTargetPosition(TurnTicks);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setPower(0.25);
        backRight.setPower(0.25);
        frontLeft.setPower(0.25);
        frontRight.setPower(0.25);
        while (frontLeft.isBusy() || backLeft.isBusy() || frontRight.isBusy() || backRight.isBusy()) {}
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void turnRight(int TurnTicks){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setTargetPosition(TurnTicks);
        backLeft.setTargetPosition(TurnTicks);
        frontRight.setTargetPosition(-TurnTicks);
        backRight.setTargetPosition(-TurnTicks);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setPower(0.25);
        backRight.setPower(0.25);
        frontLeft.setPower(0.25);
        frontRight.setPower(0.25);
        while (frontLeft.isBusy() || backLeft.isBusy() || frontRight.isBusy() || backRight.isBusy()) {}
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void turnLeft(int TurnTicks){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setTargetPosition(-TurnTicks);
        backLeft.setTargetPosition(-TurnTicks);
        frontRight.setTargetPosition(TurnTicks);
        backRight.setTargetPosition(TurnTicks);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setPower(0.25);
        backRight.setPower(0.25);
        frontLeft.setPower(0.25);
        frontRight.setPower(0.25);
        while (frontLeft.isBusy() || backLeft.isBusy() || frontRight.isBusy() || backRight.isBusy()) {}
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}
