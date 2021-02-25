package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;


import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.INCH;
@Autonomous(name = "Auton V55")
public class AutonV5 extends LinearOpMode{



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

            org.firstinspires.ftc.teamcode.AutonV5.FunctionsB FUNC = new org.firstinspires.ftc.teamcode.AutonV5.FunctionsB();
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


                FTDist = distFrontTop.getDistance(INCH);
                telemetry.addData("Dist : ", SDist);
                telemetry.addData("Front:", FTDist);
                telemetry.update();
                FUNC.driveForward(4500, true);

                sleep(500);
FUNC.strafeRight(950);
                LF.setPower(-0.1);
                LB.setPower(0.1);
                RF.setPower(0.1);
                RB.setPower(-0.1);
                FTDist = distFrontTop.getDistance(INCH);
                telemetry.addData("Front:", FTDist);
                telemetry.update();


                if (FTDist < 50) {
                    EncoderDistance = FTDist - 56;
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
                    sleep(200);
                    LB.setPower(0.05);
                    RB.setPower(0.05);
                    LF.setPower(0.05);
                    RF.setPower(0.05);
                    sleep(100);
                    LB.setPower(0);
                    RB.setPower(0);
                    LF.setPower(0);
                    RF.setPower(0);
                    double voltage = (double) getBatteryVoltage();
                    double outtakeConstant = 10/voltage;
                    motorA.setPower(0.525*outtakeConstant + 0.2);
                    motorB.setPower(0.525*outtakeConstant);
                     sleep(1000);
                     voltage = (double) getBatteryVoltage();
                     outtakeConstant = 10/voltage;
                    motorA.setPower(0.55*outtakeConstant + 0.2);
                    motorB.setPower(0.55*outtakeConstant);
                    sleep(1500);
                    StorageMotor.setPower(0.8*outtakeConstant);
                    sleep(500);
                    Intake.setPower(0.75);
                    sleep(500);
                    Intake.setPower(0);



                }
                sleep(3000);
                StorageMotor.setPower(-0.5);
                sleep(500);
                StorageMotor.setPower(1);
                sleep(3000);
                motorA.setPower(0);
                motorB.setPower(0);
                sleep(200);
                FUNC.driveForward(1000, false);


                stop();
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











        public class FunctionsB {
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


            public void driveForward(int TurnTicks, boolean Slow) {
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
                if(Slow) {
                    LF.setPower(0.6);
                    LB.setPower(0.6);
                    RF.setPower(0.6);
                    RB.setPower(0.6);
                    sleep(500);
                    LF.setPower(0.5);
                    LB.setPower(0.5);
                    RF.setPower(0.5);
                    RB.setPower(0.5);
                    sleep(400);
                    LF.setPower(0.325);
                    LB.setPower(0.325);
                    RF.setPower(0.325);
                    RB.setPower(0.325);
                    sleep(400);
                    LF.setPower(0.15);
                    LB.setPower(0.15);
                    RF.setPower(0.15);
                    RB.setPower(0.15);
                    sleep(200);
                    LF.setPower(0);
                    LB.setPower(0);
                    RF.setPower(0);
                    RB.setPower(0);
                }
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



