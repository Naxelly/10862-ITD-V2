package org.firstinspires.ftc.teamcode.opmodes.Autos;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Dropdown;
import org.firstinspires.ftc.teamcode.subsystems.HSlides;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.VSlides;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;
import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDrive;

@Config
@Autonomous
public class Left extends LinearOpMode {
	@Override
	public void runOpMode() throws InterruptedException{
		Pose2d Begin = new Pose2d(-34,-62,Math.toRadians(90));
		Pose2d AngleBasket = new Pose2d(-40,-45,Math.toRadians(90));
		Pose2d Score = new Pose2d(-56,-58,Math.toRadians(90));
		
		MecanumDrive drive = new MecanumDrive(hardwareMap, Begin);
		
		VSlides vSlides = new VSlides(this);
		Arm arm = new Arm(this);
		Wrist wrist = new Wrist(this);
		Claw claw = new Claw(this);
		
		HSlides hSlides = new HSlides(this);
		Intake intake = new Intake(this);
		Dropdown dropdown = new Dropdown(this);
		
		while (!opModeIsActive()&&!isStopRequested()){
			arm.Intake();
			wrist.setIntakePosition();
			claw.Closed();
			
			waitForStart();
			
			if (opModeIsActive()) {
				Actions.runBlocking(
					drive.actionBuilder(Begin)
						.strafeTo(new Vector2d(-45,-40))
						.strafeToLinearHeading(new Vector2d(-70,-62), Math.toRadians(45))
						.build());
						vSlides.HighBasket();
						sleep(800);
						arm.Sample();
						sleep(200);
						wrist.setSamplePosition();
						sleep(2000);
						claw.Open();
						sleep(1000);
				Actions.runBlocking(
					drive.actionBuilder(Begin)
						.strafeTo(new Vector2d(-45,-50))
							.turn(Math.toRadians(45))
						.build());
						wrist.setIntakePosition();
						sleep(200);
						arm.Hold();
						sleep(200);
						vSlides.Reset();
						sleep(500);
//				Actions.runBlocking(
//						drive.actionBuilder(Begin)
//								.setTangent(90)
//								.strafeTo(new Vector2d(-48,-40))
//								.turn(Math.toRadians(-45))
//								.build());
//				hSlides.();
//				);
			}
		}
	}
}
