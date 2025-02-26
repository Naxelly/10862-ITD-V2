package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HSlides {
	public static double POWER = 1;
	public static int Start = 0;
	public static int Outtake = 500;
	public static int Intake = 1000;
	public static int IntakeMore = 2000;
	
	
	public static int MANUAL_MOVE_SPEED = 10;
	private int position = 0;
	
	public final DcMotor hSlide;
	private final HardwareMap hardwareMap;
	
	public final Telemetry telemetry;
	
	public HSlides(OpMode opMode) {
		hardwareMap = opMode.hardwareMap;
		telemetry = opMode.telemetry;
		
		hSlide = hardwareMap.get(DcMotor.class, "hSlide");
		hSlide.setDirection(DcMotorSimple.Direction.REVERSE);
		hSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		
		
		hSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		
		hSlide.setTargetPosition(0);
		
		hSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	}
	
	public void Start() {
		hSlide.setPower(0);
		hSlide.setTargetPosition(Start);
	}
	
	public void Outtake() {
		hSlide.setPower(-1);
		hSlide.setTargetPosition(Outtake);
	}
	
	public void Intake() {
		hSlide.setPower(-1);
		hSlide.setTargetPosition(Intake);
	}
	
	public void IntakeMore() {
		hSlide.setPower(-1);
		hSlide.setTargetPosition(IntakeMore);
	}
	
	public void moveMotors(int position) {
		this.position = position;
		hSlide.setTargetPosition(position);
		hSlide.setPower(POWER);
	}
}
