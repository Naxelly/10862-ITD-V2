package org.firstinspires.ftc.teamcode.subsystems;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Wrist {
	private static Servo wristServo;
	private static final double Sample = 0.55;
	private static final double Intake = 0.29;
	private static final double SpecWall = 0;
	private static final double Spec = 0.65;

	public Wrist(OpMode opMode) {
		wristServo = opMode.hardwareMap.get(Servo.class, "wrist");
		wristServo.setDirection(Servo.Direction.FORWARD);
		wristServo.setPosition(Intake);}

	public void setSamplePosition() {
		wristServo.setPosition(Sample);
	}
	public void setIntakePosition() {
		wristServo.setPosition(Intake);
	}
	public void setSpecWallPosition() {
		wristServo.setPosition(SpecWall);
	}
	public void setSpecPosition() {
		wristServo.setPosition(Spec);
	}
}
