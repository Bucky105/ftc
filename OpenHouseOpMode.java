package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.INCH;


@TeleOp(name="Open House Demo (If you're not sure which one to use at open house you are dumb)", group="Iterative Opmode")

public class OpenHouseOpMode extends OpMode
{
    private DistanceSensor sensorRange;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor RF = null;
    public DcMotor RB = null;
    public DcMotor LF = null;
    public DcMotor LB = null;
    private DcMotor intake = null;
    private DcMotor outTake2 = null;
    private DcMotor outTake1 = null;

    public double rightX = 0;
    public double rightY = 0;
    public double leftX = 0;
    public double speed = 0;
    public double angle = 0;
    public double distance = 0;
    public int MODEB = 0;
    public double rightFactor;
    public double leftFactor;

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
      //  intake = hardwareMap.get(DcMotor.class, "intakeMotor");
        outTake1 = hardwareMap.get(DcMotor.class, "motor_A");
        outTake2 = hardwareMap.get(DcMotor.class, "motor_B");

        RF.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.REVERSE);
        LF.setDirection(DcMotor.Direction.FORWARD);
        LB.setDirection(DcMotor.Direction.FORWARD);
        //intake.setDirection(DcMotor.Direction.FORWARD);
        //outTake.setDirection(DcMotor.Direction.FORWARD);
        outTake1.setDirection(DcMotor.Direction.REVERSE);
        outTake2.setDirection(DcMotor.Direction.FORWARD);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    public void start() {runtime.reset();}


    @Override
    public void loop() {
        distance = sensorRange.getDistance(INCH);
        double intakeSpeed = 0;
        double outtakesSpeedA = 0;
        double outtakesSpeedB = 0;



        if(gamepad1.b) {
            MODEB = 1;
        }
            if(MODEB == 1){
                if(gamepad1.x){
                    MODEB = 0;
                }
                distance = sensorRange.getDistance(INCH);
                leftFactor = 0;
                rightFactor = 0;
                if(gamepad1.left_bumper){
                    leftFactor = -0.1;
                }else if(gamepad1.right_bumper){
                    rightFactor = -0.1;
                }
                if(distance < 8){
                    setMotorSpeed(-0.5 - leftFactor,-0.5 - leftFactor,-0.5 - rightFactor,-0.5 - rightFactor);
                }else if(distance > 15){
                    setMotorSpeed(0.5 + rightFactor,0.5 + rightFactor,0.5 + leftFactor,0.5 + leftFactor);
                }else{
                    RF.setPower(0);
                    RB.setPower(0);
                    LF.setPower(0);
                    LB.setPower(0);
                }
                if(gamepad1.a){
                    outtakesSpeedA = 0.7;
                    outtakesSpeedB = 0.6;
                }
                if(gamepad1.y){
                    outtakesSpeedA = 0.5;
                    outtakesSpeedB = 0.4;
                }
                if(gamepad1.left_bumper) {
                    outtakesSpeedA = 0.6;
                    outtakesSpeedB = 0.5;
                }
                if(gamepad1.right_bumper) {
                    outtakesSpeedA = 0.55;
                    outtakesSpeedB = 0.45;
                }
                outTake1.setPower(outtakesSpeedA);
                outTake2.setPower(outtakesSpeedB);
            }else {

                if(gamepad1.a){
                    outtakesSpeedA = 0.7;
                    outtakesSpeedB = 0.6;
                }
                if(gamepad1.y){
                    outtakesSpeedA = 0.5;
                    outtakesSpeedB = 0.4;
                }
                if(gamepad1.left_bumper) {
                    outtakesSpeedA = 0.6;
                    outtakesSpeedB = 0.5;
                }
                if(gamepad1.right_bumper) {
                    outtakesSpeedA = 0.55;
                    outtakesSpeedB = 0.45;
                }

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
            }
   //     intake.setPower(intakeSpeed);
     //   outTake.setPower(outtakesSpeed);
        outTake1.setPower(outtakesSpeedA);
        outTake2.setPower(outtakesSpeedB);
        if(24 < distance && distance < 36){
            telemetry.addData("Bottom Goal : ", "In Range");
        }else{
            telemetry.addData("Bottom Goal : ", "Not In Range");
        }
        telemetry.addData("Distance", distance);
        telemetry.update();
    }

    @Override
    public void stop() {
    }
    public void setMotorSpeed(double RFspeed, double RBspeed, double LFspeed, double LBspeed){
        RF.setPower(RFspeed);
        RB.setPower(RBspeed);
        LF.setPower(LFspeed);
        LB.setPower(LBspeed);
    }
}