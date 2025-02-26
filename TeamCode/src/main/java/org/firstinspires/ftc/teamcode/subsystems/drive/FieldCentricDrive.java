package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class FieldCentricDrive {
	private final DcMotor leftFront, leftBack, rightFront, rightBack;
	private final IMU imu;
	private final Gamepad driver;
	
	private double speed = 1.0; // Default speed
	
	public FieldCentricDrive(OpMode opMode) {
		driver = opMode.gamepad2;
		HardwareMap hardwareMap = opMode.hardwareMap;
		
		imu = hardwareMap.get(IMU.class, "imu");
		leftFront = hardwareMap.get(DcMotor.class, "FL");
		leftBack = hardwareMap.get(DcMotor.class, "BL");
		rightFront = hardwareMap.get(DcMotor.class, "FR");
		rightBack = hardwareMap.get(DcMotor.class, "BR");
		
		leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
		leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
		setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		
		IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
			RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
			RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
		imu.initialize(parameters);
	}
	
	public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
		leftFront.setZeroPowerBehavior(behavior);
		leftBack.setZeroPowerBehavior(behavior);
		rightFront.setZeroPowerBehavior(behavior);
		rightBack.setZeroPowerBehavior(behavior);
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void fieldCentric() {
		double y = -driver.left_stick_y;
		double x = driver.left_stick_x * 1.1; // Counteract imperfect strafing
		double rx = driver.right_stick_x;
		
		if (driver.start) {
			imu.resetYaw();
		}
		
		double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
		double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
		double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
		
		double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1.0);
		double frontLeftPower = (rotY + rotX + rx) / denominator * speed;
		double backLeftPower = (rotY - rotX + rx) / denominator * speed;
		double frontRightPower = (rotY - rotX - rx) / denominator * speed;
		double backRightPower = (rotY + rotX - rx) / denominator * speed;
		
		leftFront.setPower(frontLeftPower);
		leftBack.setPower(backLeftPower);
		rightFront.setPower(frontRightPower);
		rightBack.setPower(backRightPower);
	}
}