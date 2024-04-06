// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

public class Robot extends LoggedRobot
{
    private Command AutonomousCommand;
    private RobotContainer RobotContainer;

    @Override
    public void robotInit() {
        RobotContainer = new RobotContainer();
        if (Constants.kEnableOBlog) {
            io.github.oblarg.oblog.Logger.configureLoggingAndConfig(RobotContainer, false);
        } else {
            System.out.println("OBlog is disabled -- not configuring logging and config.");
        }

        Logger.recordMetadata("ProjectName", BuildConstants.);
    }
}