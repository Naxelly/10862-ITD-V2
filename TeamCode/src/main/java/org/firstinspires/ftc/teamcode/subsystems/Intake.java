package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@SuppressLint("NotConstructor")
@Config
public class Intake {
	private static final double Intake = .5;
	private static final double Outtake = -.5;
	private static DcMotor IntakeMotor;
	
	public Intake(OpMode opMode) {
		hardwareMap = opMode.hardwareMap;
		IntakeMotor = hardwareMap.get(DcMotor.class, "intake");
		IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		IntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
	}
	public void Intake() {
		IntakeMotor.setPower(Intake);
	}
	public void Outtake() {
		IntakeMotor.setPower(Outtake);
	}
	public void Stop() {
		IntakeMotor.setPower(0);
	}
}