package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp//comment back in later when needed

public class ArmTuner extends OpMode {
	static Servo leftservo;
	static Servo rightservo;
	/**
	 * User defined init method
	 * <p>
	 * This method will be called once when the INIT button is pressed.
	 */
	@Override
	public void init() {
		leftservo = hardwareMap.get(Servo.class, "leftArm");
		rightservo = hardwareMap.get(Servo.class, "rightArm");
		leftservo.setDirection(Servo.Direction.FORWARD);
		rightservo.setDirection(Servo.Direction.REVERSE);
		leftservo.setPosition(0);
	}
	
	/**
	 * User defined loop method
	 * <p>
	 * This method will be called repeatedly in a loop while this op mode is running
	 */
	@Override
	public void loop() {
		if(gamepad1.dpad_up){
			armServo(rightservo.getPosition()+ 0.001,leftservo.getPosition()+ 0.001);
		}
		if(gamepad1.dpad_down){
			armServo(rightservo.getPosition()- 0.001,leftservo.getPosition()- 0.001);
		}
		
		//DO NOT CHANGE THIS PLEASE
		telemetry.addData("Servo", leftservo.getPosition());
		telemetry.update();
	}
	private static void armServo(double setPositionRight, double setPositionLeft) {
		rightservo.setPosition(setPositionRight);
		leftservo.setPosition(setPositionLeft);
	}
	
}
