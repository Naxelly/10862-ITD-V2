package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Claw {
	private static final double OPEN_POSITION = 0.75;
	private static final double CLOSED_POSITION = 0.65;
	private static Servo clawServo;
	
	public Claw(OpMode opMode) {
		clawServo = opMode.hardwareMap.get(Servo.class, "claw");
		clawServo.setDirection(Servo.Direction.REVERSE);
		clawServo.setPosition(OPEN_POSITION); // Start with the claw closed
	}
	public void Closed() {
		clawServo.setPosition(CLOSED_POSITION);
	}
	public void Open() {
		clawServo.setPosition(OPEN_POSITION);
	}
	}
