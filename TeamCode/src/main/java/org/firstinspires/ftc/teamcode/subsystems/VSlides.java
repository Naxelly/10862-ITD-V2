package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class VSlides {
	
		public static double POWER = 0.65;
		public static int HighBasket = 3100;
		public static int SpecimenWall = 500;
		public static int SpecWallHigh = 800;
		public static int HighChamber = 1100;
		public static int ScoreSpec = 200;
		public static int RESET = 0;
		
		public static int MANUAL_MOVE_SPEED = 10;
		private int position = 0;
		
		public final DcMotor slideLeft;
		public final DcMotor slideRight;
		private final HardwareMap hardwareMap;
		
		public final Telemetry telemetry;
		
		public VSlides(OpMode opMode) {
			hardwareMap = opMode.hardwareMap;
			telemetry = opMode.telemetry;
			
			slideLeft = hardwareMap.get(DcMotor.class, "vLSlide");
			slideLeft.setDirection(DcMotorSimple.Direction.REVERSE);
			slideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
			
			slideRight = hardwareMap.get(DcMotor.class, "vRSlide");
			slideRight.setDirection(DcMotorSimple.Direction.FORWARD);
			slideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
			
			slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			
			slideLeft.setTargetPosition(0);
			slideRight.setTargetPosition(0);
			
			slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
			slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		}
		
		public void SpecWall() {
			slideLeft.setPower(1);
			slideRight.setPower(1);
			slideLeft.setTargetPosition(SpecimenWall);
			slideRight.setTargetPosition(SpecimenWall);
		}
	
	public void SpecWallHigh() {
		slideLeft.setPower(1);
		slideRight.setPower(1);
		slideLeft.setTargetPosition(SpecWallHigh);
		slideRight.setTargetPosition(SpecWallHigh);
	}
	public void ScoreSpec() {
		slideLeft.setPower(1);
		slideRight.setPower(1);
		slideLeft.setTargetPosition(ScoreSpec);
		slideRight.setTargetPosition(ScoreSpec);
	}
		public void HighBasket() {
			slideLeft.setPower(1);
			slideRight.setPower(1);
			slideLeft.setTargetPosition(HighBasket);
			slideRight.setTargetPosition(HighBasket);
		}
		
		public void Reset() {
			slideLeft.setPower(1);
			slideRight.setPower(1);
			slideLeft.setTargetPosition(RESET);
			slideRight.setTargetPosition(RESET);
		}
		
		public void HighChamber() {
			slideLeft.setPower(1);
			slideRight.setPower(1);
			slideLeft.setTargetPosition(HighChamber);
			slideRight.setTargetPosition(HighChamber);
		}
		
		public void moveMotors(int position) {
			this.position = position;
			slideLeft.setTargetPosition(position);
			slideRight.setTargetPosition(position);
			slideLeft.setPower(POWER);
			slideRight.setPower(POWER);
		}
	public void updateTelemetry() {
		telemetry.addData("VSlide Pos", slideLeft.getCurrentPosition());
		telemetry.addData("Vslide Target" , slideLeft.getTargetPosition());
		telemetry.update();
	}
	}
	
	
