package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Dropdown;
import org.firstinspires.ftc.teamcode.subsystems.HSlides;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.VSlides;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;
import org.firstinspires.ftc.teamcode.subsystems.drive.FieldCentricDrive;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "TeleOpMain")
public class TeleOpMain extends OpMode {
	
	private final FtcDashboard dash = FtcDashboard.getInstance();
	private List<Action> runningActions = new ArrayList<>();
	
	// Subsystems
	private HSlides HSlide;
	private VSlides VSlide;
	private Intake intake;
	private FieldCentricDrive fieldCentricDrive;
	private Claw claw;
	private Arm arm;
	private Wrist wrist;
	private Dropdown dropdown;
	
	@Override
	public void init() {
		// Initialize subsystems
		fieldCentricDrive = new FieldCentricDrive(this);
		HSlide = new HSlides(this);
		VSlide = new VSlides(this);
		intake = new Intake(this);
		claw = new Claw(this);
		arm = new Arm(this);
		wrist = new Wrist(this);
		dropdown = new Dropdown(this);
	}
	
	@Override
	public void loop() {
		fieldCentricDrive.fieldCentric();
		//HSlide.updateTelemetry();
		VSlide.updateTelemetry();
		// DRIVER 1
	
		// Intake or Outtake logic with else block handling default actions
		if (gamepad1.right_bumper) {
			runningActions.add(new SequentialAction(
				new InstantAction(intake::Intake),
				new InstantAction(dropdown::Down),
				new SleepAction(1.5),
				new InstantAction(HSlide::Intake)
			));}
		 if (gamepad1.left_bumper) {
			runningActions.add(new SequentialAction(
				new InstantAction(intake::Outtake),
				new InstantAction(dropdown::Down),
				new SleepAction(1.5),
				new InstantAction(HSlide::Outtake)
			));}
//		else {
//			dropdown.Up();
//			intake.Stop();
//			HSlide.Start();
//		}
		if (gamepad1.right_trigger>=0.1) {
			runningActions.add(new SequentialAction(
				new InstantAction(claw::Open)
			));}
		
		if (gamepad1.left_trigger>=0.1) {
			runningActions.add(new SequentialAction(
				new InstantAction(claw::Closed)
			));}
		
		if (gamepad1.dpad_left) {
			runningActions.add(new SequentialAction(
				new InstantAction(dropdown::Up),
				new InstantAction(intake::Stop),
				new SleepAction(0.2),
				new InstantAction(HSlide::Start)
			));}
		
		
		// IntakeSample
//		if (gamepad1.a) {
//			runningActions.add(new SequentialAction(
//				new InstantAction(VSlide::Reset),
//				new InstantAction(wrist::setIntakePosition),
//				new InstantAction(arm::Intake)
//			));
//		}
		
		// Open
//		if (gamepad1.left_bumper) {
//			runningActions.add(new SequentialAction(
//				new InstantAction(claw::Open)
//			));
//		}
//
//		// Close
//		if (gamepad1.right_bumper) {
//			runningActions.add(new SequentialAction(
//				new InstantAction(claw::Closed)
//			));
		//}
		
		// DRIVER 2
		
		// HighBasket
		if (gamepad2.y) {
			runningActions.add(new SequentialAction(
				new InstantAction(claw::Open),
				new SleepAction(.2),
				new InstantAction(wrist::setIntakePosition),
				new InstantAction(arm::Intake),
				new SleepAction(.4),
				new InstantAction(claw::Closed),
				new SleepAction(1),
				new InstantAction(arm::Sample),
				new InstantAction(wrist::setSamplePosition),
				new InstantAction(VSlide::HighBasket)
			));
		}
		
		// Re-start
		if (gamepad2.dpad_down) {
			runningActions.add(new SequentialAction(
				new InstantAction(arm::Hold),
				new InstantAction(wrist::setIntakePosition),
				new InstantAction(VSlide::Reset)
			));
		}
		
		// HighSpecimen
		if (gamepad2.dpad_up) {
			runningActions.add(new SequentialAction(
				new InstantAction(VSlide::HighChamber),
				new SleepAction(0.5),
				new InstantAction(arm::SpecScore),
				new InstantAction(wrist::setSpecPosition),
				new SleepAction(0.8)
			));
		}
		if (gamepad2.dpad_right){
			runningActions.add(new SequentialAction(
				new InstantAction(VSlide::ScoreSpec),
				new SleepAction(0.8),
				new InstantAction(claw::Open),
				new SleepAction(1)
			));
		}
		
		// SpecimenWall
		if (gamepad2.left_bumper) {
			runningActions.add(new SequentialAction(
				new InstantAction(VSlide::SpecWallHigh),
				new SleepAction(0.5),
				new InstantAction(claw::Open),
				new InstantAction(arm::Spec),
				new InstantAction(wrist::setSpecWallPosition),
				new SleepAction(0.5),
				new InstantAction(VSlide::SpecWall)
			));
		}
		
		// Run the queued sequential actions
		List<Action> newActions = new ArrayList<>();
		for (Action action : runningActions) {
			TelemetryPacket packet = new TelemetryPacket();
			action.preview(packet.fieldOverlay());
			if (!action.run(packet)) {
				continue;
			}
			newActions.add(action);
			dash.sendTelemetryPacket(packet);
		}
		runningActions = newActions;
	}
}