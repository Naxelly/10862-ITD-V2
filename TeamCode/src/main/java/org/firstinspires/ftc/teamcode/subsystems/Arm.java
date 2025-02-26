package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
	private static Servo leftServo;
	private static Servo rightServo;
	
	private static final double Sample = .7;
	private static final double Intake = 0.16;
	private static final double Hold = 0.2;
	private static final double Specimen = 0;
	
	public static boolean ArmIsUp;
	private static boolean previousButtonState = false; // Tracks the previous state of the 'a' button
	
	public Arm(OpMode opMode) {
		leftServo = opMode.hardwareMap.get(Servo.class, "armL");
		rightServo = opMode.hardwareMap.get(Servo.class, "armR");
		
		leftServo.setDirection(Servo.Direction.FORWARD);
		rightServo.setDirection(Servo.Direction.REVERSE);
		armServo(Hold, Hold);
	}
	
	// Methods for direct control if needed elsewhere
	public void Hold() {
		armServo(Hold, Hold);
	}
	public void Intake() {
		armServo(Intake, Intake);
	}
	public void Spec() {
		armServo(Specimen, Specimen);
	}
	public void Sample() {
		armServo(Sample, Sample);
	}
	
	private static void armServo(double setPositionRight, double setPositionLeft) {
		rightServo.setPosition(setPositionRight);
		leftServo.setPosition(setPositionLeft);
	}
}