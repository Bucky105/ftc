package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.INCH;

@Autonomous(name = "AutonV2")
//@Disabled
public class AutonV2 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor RF = null;
    public DcMotor RB = null;
    public DcMotor LF = null;
    public DcMotor LB = null;

    private DcMotor motorA = null;
    private DcMotor motorB = null;
    private DistanceSensor distFrontBottom = null;
    private DistanceSensor distFrontTop = null;
    private DistanceSensor distSide;
    public double FBDist = 0;
    public double FTDist = 0;
    public double SDist = 0;
    public int MODE = 15;
    public int calcVar = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        Functions FUNC = new Functions();
        distFrontBottom = hardwareMap.get(DistanceSensor.class, "followMeFB");
        distFrontTop = hardwareMap.get(DistanceSensor.class, "followMeFT");
        distSide = hardwareMap.get(DistanceSensor.class, "followMeS");
        telemetry.addData("Status", "Initialized");

        RF = hardwareMap.get(DcMotor.class, "rightFront");
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
        waitForStart();
        while (opModeIsActive()) {

            SDist = distSide.getDistance(INCH);
            telemetry.addData("Dist : ", SDist);
            FUNC.driveForward(FUNC.ToTicks(25));
            if (SDist > 23) {
                FUNC.strafeLeft(FUNC.ToTicks(12));
            } else {
                FUNC.strafeRight(FUNC.ToTicks(12));
            }
            FUNC.driveForward(FUNC.ToTicks(25));
            SDist = distSide.getDistance(INCH);
            FBDist = distFrontBottom.getDistance(INCH);
            FTDist = distFrontTop.getDistance(INCH);
            MODE = FUNC.ringConfig();
            telemetry.addData("Mode : ", MODE);

            telemetry.update();
            switch (MODE) {
                case 0:
                    FUNC.turnRight(FUNC.ToTicks(2));
                    FUNC.driveForward(FUNC.ToTicks(10));
                    FUNC.turnLeft(FUNC.ToTicks(2));
                    break;
                case 1:
                    FUNC.turnRight(FUNC.ToTicks(5));
                    FUNC.driveForward(FUNC.ToTicks(25));
                    FUNC.turnLeft(FUNC.ToTicks(5));
                    break;
                case 4:
                    FUNC.turnRight(150);
                    FUNC.driveForward(100);
                    FUNC.turnLeft(150);
                    break;
            }
            FUNC.parkOnLine(MODE);
            telemetry.addData("Mode : ", MODE);
            telemetry.update();
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
            speedSet(0.25);
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
            speedSet(0.25);
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
            speedSet(0.25);
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

