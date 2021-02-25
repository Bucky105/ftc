package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name="ColorApp")
public class Strafe extends OpMode {
    //Declare Variables
    private ElapsedTime runtime = new ElapsedTime();

    View relativeLayout;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


    }

    @Override
    public void loop() {


if(gamepad1.a) {
    relativeLayout.post(new Runnable() {
        public void run() {
            relativeLayout.setBackgroundColor(Color.RED);
        }
    });
}
    }
}
