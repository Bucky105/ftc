package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.INCH;

@Autonomous(name = "AutonV3")
//@Disabled
public class AutonV3 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor RF = null;
    public DcMotor RB = null;
    public DcMotor LF = null;
    public DcMotor LB = null;
    public DcMotor StorageMotor = null;
    private DcMotorEx motorA = null;
    private DcMotorEx motorB = null;
    private DistanceSensor distFrontBottom = null;
    private DistanceSensor distFrontTop = null;
    private DistanceSensor distSide = null;
    public double FBDist = 0;
    public double FTDist = 0;
    public double SDist = 0;
    public int MODE = 15;
    public DcMotor Intake = null;
    public int calcVar = 0;
    public double SetDistance = 0;
    public double EncoderDistance = 0;
    public int Ticks = 0;
    public double SideDistance = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        Functions FUNC = new Functions();
        Intake = hardwareMap.get(DcMotor.class, "IntakeMotor");
        distFrontBottom = hardwareMap.get(DistanceSensor.class, "followMeFB");
        distFrontTop = hardwareMap.get(DistanceSensor.class, "followMeFT");
        distSide = hardwareMap.get(DistanceSensor.class, "followMeS");
        telemetry.addData("Status", "Initialized");

        RF = hardwareMap.get(DcMotor.class, "rightFront");
        RB = hardwareMap.get(DcMotor.class, "rightBack");
        LF = hardwareMap.get(DcMotor.class, "leftFront");
        LB = hardwareMap.get(DcMotor.class, "leftBack");
        StorageMotor = hardwareMap.get(DcMotor.class, "StorageMotor");
        motorA = hardwareMap.get(DcMotorEx.class, "motor_A");
        motorB = hardwareMap.get(DcMotorEx.class, "motor_B");
        motorA.setDirection(DcMotor.Direction.FORWARD);
        motorB.setDirection(DcMotor.Direction.FORWARD);

        StorageMotor.setDirection(DcMotor.Direction.REVERSE);
        RF.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.REVERSE);
        LF.setDirection(DcMotor.Direction.FORWARD);
        LB.setDirection(DcMotor.Direction.FORWARD);
        waitForStart();
        while (opModeIsActive()) {
            FUNC.driveForward(500);
         /*   LF.setPower(0.1);
            LB.setPower(-0.1);
            RF.setPower(-0.1);
            RB.setPower(0.1);
            sleep(500);
            SideDistance = distSide.getDistance(INCH);
            EncoderDistance = SideDistance - 10;
            Ticks = (int) Math.round(EncoderDistance / ((3 * Math.PI) / 767));
            LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LF.setPower(-Ticks);
            LB.setPower(Ticks);
            RF.setPower(Ticks);
            RB.setPower(-Ticks);
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

FUNC.strafeLeft(200);
            FTDist = distFrontTop.getDistance(INCH);
            telemetry.addData("Dist : ", SDist);
            telemetry.addData("Front:", FTDist);
            telemetry.update();
           FUNC.driveForward(5250);

            FTDist = distFrontTop.getDistance(INCH);
            telemetry.addData("Front:", FTDist);
            telemetry.update();


            if (FTDist < 50) {
                EncoderDistance = FTDist - 60;
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

                while (LB.isBusy() && LF.isBusy() && RF.isBusy() && RB.isBusy() && opModeIsActive()) {
                    idle();
                }
                LB.setPower(0.2);
                RB.setPower(0.2);
                LF.setPower(0.2);
                RF.setPower(0.2);
                sleep(100);
                LB.setPower(0);
                RB.setPower(0);
                LF.setPower(0);
                RF.setPower(0);

                motorA.setVelocity(44742);
                motorB.setVelocity(44742);
                sleep(1000);
                StorageMotor.setPower(1);
                sleep(500);
                Intake.setPower(0.75);
                sleep(500);
                Intake.setPower(0);
                FUNC.strafeLeft(200);


            }

            sleep(1000);
            motorA.setPower(0);
            motorB.setPower(0);
            sleep(200);
            FUNC.driveForward(1000);

            stop();
            }


        


    }
















    public class Functions {
        //332 Distance Sensor goes on bottom. Code right now assumes both are 322. If Top one isn't 332 then change >50 to <4.
        public int ringConfig() {
            SDist = distSide.getDistance(INCH);
            FBDist = distFrontBottom.getDistance(INCH);
            FTDist = distFrontTop.getDistance(INCH);
            telemetry.addData("Top : ", FTDist);
            telemetry.addData("Bottom : ", FBDist);
            if (FBDist > 50) {
                return 0;
            } else if (FTDist > 50) {
                return 1;
            } else {
                return 4;
            }
        }


        public void parkOnLine(int ModeSetting) {
            int distNeeded = 0;
            switch (ModeSetting) {
                case 1:
                    distNeeded = ToTicks(30);
                    break;
                case 4:
                    distNeeded = ToTicks(40);
                    break;
            }
            driveBackward(distNeeded);
        }


        public void driveForward(int TurnTicks) {
            stopReset();
            LF.setTargetPosition(TurnTicks);
            LB.setTargetPosition(TurnTicks);
            RF.setTargetPosition(TurnTicks);
            RB.setTargetPosition(TurnTicks);
            run2Position();
            speedSet(0.75);
            while (LB.isBusy() && LF.isBusy() && RF.isBusy() && RB.isBusy() && opModeIsActive()) {
                idle();
            }
            speedSet(0);
            runWOEncoder();
        }

        public int ToTicks(int inches){
            calcVar = (int) Math.round(inches/((98/25.4)*Math.PI)*767.2);
            return calcVar;
        }
        public void driveBackward(int TurnTicks) {
            stopReset();
            LF.setTargetPosition(-TurnTicks);
            LB.setTargetPosition(-TurnTicks);
            RF.setTargetPosition(-TurnTicks);
            RB.setTargetPosition(-TurnTicks);
            run2Position();
            speedSet(0.25);
            while (LB.isBusy() && LF.isBusy() && RF.isBusy() && RB.isBusy() && opModeIsActive()) {
                idle();
            }
            speedSet(0);
            runWOEncoder();
        }

        public void strafeLeft(int TurnTicks) {
            stopReset();
            LF.setTargetPosition(-TurnTicks);
            LB.setTargetPosition(TurnTicks);
            RF.setTargetPosition(TurnTicks);
            RB.setTargetPosition(-TurnTicks);
            run2Position();
            speedSet(0.75);
            while (LB.isBusy() && LF.isBusy() && RF.isBusy() && RB.isBusy() && opModeIsActive()) {
                idle();
            }
            speedSet(0);
            runWOEncoder();
        }

        public void strafeRight(int TurnTicks) {
            stopReset();
            LF.setTargetPosition(TurnTicks);
            LB.setTargetPosition(-TurnTicks);
            RF.setTargetPosition(-TurnTicks);
            RB.setTargetPosition(TurnTicks);
            run2Position();
            speedSet(0.75);
            while (LB.isBusy() && LF.isBusy() && RF.isBusy() && RB.isBusy() && opModeIsActive()) {
                idle();
            }
            speedSet(0);
            runWOEncoder();
        }

        public void turnRight(int TurnTicks) {
            stopReset();
            LF.setTargetPosition(TurnTicks);
            LB.setTargetPosition(TurnTicks);
            RF.setTargetPosition(-TurnTicks);
            RB.setTargetPosition(-TurnTicks);
            run2Position();
            speedSet(0.25);
            while (LB.isBusy() && LF.isBusy() && RF.isBusy() && RB.isBusy() && opModeIsActive()) {
                idle();
            }
            speedSet(0);
            runWOEncoder();
        }

        public void turnLeft(int TurnTicks) {
            stopReset();
            LF.setTargetPosition(-TurnTicks);
            LB.setTargetPosition(-TurnTicks);
            RF.setTargetPosition(TurnTicks);
            RB.setTargetPosition(TurnTicks);
            run2Position();
            speedSet(0.25);
            while (LB.isBusy() && LF.isBusy() && RF.isBusy() && RB.isBusy() && opModeIsActive()) {
                idle();
            }
            speedSet(0);
            runWOEncoder();
        }


        public void stopReset() {
            LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        public void run2Position() {
            LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        public void speedSet(double speed) {
            LB.setPower(speed);
            RB.setPower(speed);
            LF.setPower(speed);
            RF.setPower(speed);
        }

        public void runWOEncoder() {
            LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            LB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            RB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

    }
}

