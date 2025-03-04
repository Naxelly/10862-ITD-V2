package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class Dropdown {
		private static Servo DropDown;
		private static Gamepad driver1;
		private static final double Down = 0.06;
		private static final double Init = 0.8;
		private static final double Up = 0.5;

		
		public Dropdown (OpMode opMode) {
			driver1 = opMode.gamepad1;
			DropDown = opMode.hardwareMap.get(Servo.class, "dropDownL");
			DropDown.setDirection(Servo.Direction.FORWARD);
			DropDown.setPosition(Init);}
		
		public void Down() {
			DropDown.setPosition(Down);
		}
		public void Up() {
			DropDown.setPosition(Up);
		}
	}

