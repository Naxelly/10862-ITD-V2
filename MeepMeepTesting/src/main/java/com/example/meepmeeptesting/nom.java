package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class nom {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-32, -62, Math.toRadians(90)))

                        .strafeTo(new Vector2d(-45,-50))
                        .turn(Math.toRadians(-45))
                        .lineTo(new Vector2d(-75,-58))
                        .strafeTo(new Vector2d(-45,-50))
                        .turn(Math.toRadians(45))
                        .setTangent(90)
                        .lineTo(new Vector2d(-48,-40))
                        .turn(Math.toRadians(-45))
                        .lineTo(new Vector2d(-75,-58))
                        .setTangent(270)
                        .splineToLinearHeading(new Pose2d(-30,-10),Math.toRadians(0))



                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
