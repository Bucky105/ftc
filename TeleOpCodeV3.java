package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.INCH;

@TeleOp(name="MainV3", group="Iterative Opmode")
//@Disabled

public class TeleOpCodeV3 extends LinearOpMode
{
    private DistanceSensor FBSensor;
    private DistanceSensor FTSensor;
    private DistanceSensor SSensor;
    // Declare OpMode members.
    double voltage = 1;
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor RF = null;
    public DcMotor RB = null;
    public DcMotor LF = null;
    public DcMotor LB = null;
    public DcMotor Intake = null;
    public DcMotor StorageMotor = null;
    public boolean lbPast = false;
    public boolean rbPast = false;
public boolean IntakeOff = false;
    private DcMotorEx motorA = null;
    private DcMotorEx motorB = null;
public boolean lb2past = false;
    public boolean rb2past = false;
    public double rightX = 0;
    public double rightY = 0;
    public double leftX = 0;
    public double speed = 0;
    public double outtakeVariant = 0;
    public double angle = 0;
    public double FTdistance = 0;
    public double SetDistance = 0;
    public double EncoderDistance = 0;
    public int Ticks = 0;
    public boolean run = false;
    boolean running = false;
    double outtakespeed = 0;
    public double intakespeed = 0;
    public double storagespeed = 0;
    private DistanceSensor distSide = null;
    int TIMEFORSHOOTER = 0;
    double outtakeConstant = 0;
    int Sensativity = 1;
    public double SideDistance = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        distSide = hardwareMap.get(DistanceSensor.class, "followMeS");
        FBSensor = hardwareMap.get(DistanceSensor.class, "followMeFB");
        FTSensor = hardwareMap.get(DistanceSensor.class, "followMeFT");
        SSensor = hardwareMap.get(DistanceSensor.class, "followMeS");
        // you can also cast this to a Rev2mDistanceSensor if you want to use added
        // methods associated with the Rev2mDistanceSensor class.


        telemetry.addData("Status", "Initialized");

        RF = hardwareMap.get(DcMotor.class, "rightFront");
        RB = hardwareMap.get(DcMotor.class, "rightBack");
        LF = hardwareMap.get(DcMotor.class, "leftFront");
        LB = hardwareMap.get(DcMotor.class, "leftBack");
        motorA = hardwareMap.get(DcMotorEx.class, "motor_A");
        motorB = hardwareMap.get(DcMotorEx.class, "motor_B");
        Intake = hardwareMap.get(DcMotor.class, "IntakeMotor");
        StorageMotor = hardwareMap.get(DcMotor.class, "StorageMotor");
        motorA.setDirection(DcMotor.Direction.FORWARD);
        motorB.setDirection(DcMotor.Direction.FORWARD);
        Intake.setDirection(DcMotor.Direction.FORWARD);
        StorageMotor.setDirection(DcMotor.Direction.REVERSE);

        RF.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.REVERSE);
        LF.setDirection(DcMotor.Direction.FORWARD);
        LB.setDirection(DcMotor.Direction.FORWARD);


        telemetry.addData("Status", "Initialized");

    waitForStart();
        while (opModeIsActive()) {
            outtakeConstant = 10/voltage;
            FTdistance = FTSensor.getDistance(INCH);
            storagespeed = 0;


          /*  if(gamepad1.right_bumper && rbPast != gamepad1.right_bumper){
                if (outtakeVariant < 0.1){
                    outtakeVariant = outtakeVariant + 0.05;
                }
            }else if(gamepad1.left_bumper && lbPast != gamepad1.left_bumper) {
                if (outtakeVariant > -0.1) {
                    outtakeVariant = outtakeVariant - 0.05;
                }
            }*/
            telemetry.addData("Shooter Variant (-0.25 to 0.25) : ", outtakeVariant);



            run = false;
            if (gamepad2.right_trigger > 0.1) {

                outtakespeed = 0.7 + outtakeVariant;
                outtakespeed = outtakespeed*outtakeConstant;
                while (gamepad2.right_trigger > 0.1) {
                    motorA.setPower(outtakespeed);
                    motorB.setPower(outtakespeed);
                }
            }
            if (IntakeOff) {
                intakespeed = 0;

            } else if (gamepad2.left_bumper) {
                intakespeed = 1.0;
            }else if(gamepad2.right_bumper){
                intakespeed = -0.75;
            }
            if(gamepad2.dpad_up) {
                storagespeed = 0.8;
            }else if(gamepad2.dpad_down){
                storagespeed = -0.8;
            }
            if(lb2past != gamepad2.left_bumper && gamepad2.left_bumper){
                IntakeOff = !IntakeOff;
            }else if(rb2past != gamepad2.right_bumper && gamepad2.right_bumper){
                IntakeOff = !IntakeOff;
            }
            if(gamepad1.dpad_left){
                RF.setDirection(DcMotor.Direction.REVERSE);
                RB.setDirection(DcMotor.Direction.REVERSE);
                LF.setDirection(DcMotor.Direction.FORWARD);
                LB.setDirection(DcMotor.Direction.FORWARD);
                telemetry.addData("Reverse Mode : ", "OFF");
            }else if(gamepad1.dpad_right){
                RF.setDirection(DcMotor.Direction.FORWARD);
                RB.setDirection(DcMotor.Direction.FORWARD);
                LF.setDirection(DcMotor.Direction.REVERSE);
                LB.setDirection(DcMotor.Direction.REVERSE);
                telemetry.addData("Reverse Mode : ", "ON");
            }
            
            if (gamepad2.a) { // Low Goal
                SetDistance = 20;
                run = true;
                outtakespeed = 0.2 + outtakeVariant;

                running = true;
            } else if (gamepad2.b) { // Middle Goal
                SetDistance = 56;
                run = true;
                outtakespeed = 0.45 + outtakeVariant;
                running = true;
            } else if (gamepad2.x){ // High Goal
                SetDistance = 56;
                run = true;
                outtakespeed = 0.55 + outtakeVariant;
                running = true;
            }else if (gamepad2.y){ // Powershot
                SetDistance = 58;
                run = true;
                outtakespeed = 0.5 + outtakeVariant;
                running = true;
            }

            if (run) {
                outtakespeed = outtakespeed*outtakeConstant;
               /* LF.setPower(0.1);
                LB.setPower(-0.1);
                RF.setPower(-0.1);
                RB.setPower(0.1);
                sleep(500);*/

               /* SideDistance = distSide.getDistance(INCH);
                EncoderDistance = SideDistance - 10;
                Ticks = (int) Math.round(EncoderDistance / ((3 * Math.PI) / 767));
                telemetry.addData("SD", SideDistance);
                telemetry.addData("Ticks", Ticks);
                telemetry.update();
                LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                LF.setTargetPosition(Ticks);
                LB.setTargetPosition(-Ticks);
                RF.setTargetPosition(-Ticks);
                RB.setTargetPosition(Ticks);
                LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LB.setPower(0.75);
                RB.setPower(0.75);
                LF.setPower(0.75);
                RF.setPower(0.75);
                while(LB.isBusy() && LF.isBusy() && RF.isBusy() && RB.isBusy() && opModeIsActive()){idle();}
                LB.setPower(0);
                RB.setPower(0);
                LF.setPower(0);
                RF.setPower(0);*/
                if (FTdistance < 50) {
                    EncoderDistance = FTdistance - SetDistance;
                    Ticks = (int) Math.round(EncoderDistance / ((3 * Math.PI) / 767));
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
                    LB.setPower(0.75);
                    RB.setPower(0.75);
                    LF.setPower(0.75);
                    RF.setPower(0.75);
                    while(LB.isBusy() && LF.isBusy() && RF.isBusy() && RB.isBusy() && opModeIsActive()){idle();}
                    LB.setPower(0.2);
                    RB.setPower(0.2);
                    LF.setPower(0.2);
                    RF.setPower(0.2);
                    sleep(100);
                    LB.setPower(0);
                    RB.setPower(0);
                    LF.setPower(0);
                    RF.setPower(0);



                    motorA.setPower(outtakespeed + 0.2);
                    motorB.setPower(outtakespeed);
                    sleep(2000);
                    StorageMotor.setPower(0.8*outtakeConstant);
                   sleep(1500);
                   Intake.setPower(1);
                   sleep(500);
                   Intake.setPower(0);
                   sleep(1500);


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
            StorageMotor.setPower(0);
            rightX = gamepad1.left_stick_x;
            rightY = -gamepad1.left_stick_y;
            leftX = -gamepad1.right_stick_x;
            speed = Math.hypot(rightX, rightY);
            angle = Math.atan2(rightY, rightX);

            angle = angle - Math.PI / 4;
            if (angle < 0) {
                angle = 2 * Math.PI + angle;
            }

            double powerRF = (speed * Math.sin(angle) - leftX) / Sensativity;
            double powerRB = (speed * Math.cos(angle) - leftX) / Sensativity;
            double powerLF = (speed * Math.cos(angle) + leftX) / Sensativity;
            double powerLB = (speed * Math.sin(angle) + leftX) / Sensativity;

            RF.setPower(powerRF);
            RB.setPower(powerRB);
            LF.setPower(powerLF);
            LB.setPower(powerLB);
            Intake.setPower(intakespeed);
            StorageMotor.setPower(storagespeed);

            lbPast = gamepad1.left_bumper;
            rbPast = gamepad1.right_bumper;
            lb2past = gamepad2.left_bumper;
            rb2past = gamepad2.right_bumper;
            if (FTdistance < 50) {
                telemetry.addData("Shooting : ", "Available");
            } else {
                telemetry.addData("Shooting : ", "Not In Range");
            }
            telemetry.addData("distance : ", FTdistance);
 voltage = (double) getBatteryVoltage();
           telemetry.addData("Constant:",   voltage);

            telemetry.update();
        }
    }

    double getBatteryVoltage() {
        double result = Double.POSITIVE_INFINITY;
        for (VoltageSensor sensor : hardwareMap.voltageSensor) {
            double voltage = sensor.getVoltage();
            if (voltage > 0) {
                result = Math.min(result, voltage);
            }
        }
        return result;
    }

}