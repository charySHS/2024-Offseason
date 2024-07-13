// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.PowerDistribution;

import edu.wpi.first.wpilibj2.command.Commands;
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

        Logger.recordMetadata("ProjectName", "FRC Amideus- offseason");

        if (isReal())
        {
            System.out.println("Robot is real, forcing Robot mode to REAL");
            // Logger.addDataReceiver(new WPILOGWriter("/U/logs"));
            Logger.addDataReceiver(
                new WPILOGWriter("/home/lvuser/logs")); // Log to a USB stick ("/U/logs")
            Logger.addDataReceiver(new NT4Publisher()); // Publish data to NetworkTables
            new PowerDistribution(
                1, PowerDistribution.ModuleType.kRev); // Enables power distribution logging
        } else if (isSimulation()) {
            // DriverStation.silenceJoystickConnectionWarning(true);
            Logger.addDataReceiver(new WPILOGWriter(""));
            Logger.addDataReceiver(new NT4Publisher());
        } else {
            // Unknown mode
            Logger.addDataReceiver(new WPILOGWriter(""));
            Logger.addDataReceiver(new NT4Publisher());
        }

        Map<String, Integer> commandCounts = new HashMap<>();
        BiConsumer<Command, Boolean> logCommandFunction =
            (Command command, Boolean active) -> {
                String name = command.getName();
                int count = commandCounts.getOrDefault(name, 0) + (active ? 1 : -1);
                commandCounts.put(name, count);
                // TODO: Switch this to WBLogger
                Logger.recordOutput(
                    "CommandsUnique/" + name + "_" + Integer.toHexString(command.hashCode()), active);
                Logger.recordOutput("CommandsAll/" + name, count > 0);
            };
        CommandScheduler.getInstance()
                        .onCommandInitialize(
                            (Command command) -> {
                                logCommandFunction.accept(command, true);
                            });
        CommandScheduler.getInstance()
                        .onCommandFinish(
                            (Command command) -> {
                                logCommandFunction.accept(command, false);
                            });
        CommandScheduler.getInstance()
                        .onCommandInterrupt(
                            (Command command) -> {
                                logCommandFunction.accept(command, false);
                            });

        if (Constants.kEnableAdvKit) {
            Logger.start();
        }

        for (int port = 5800; port <= 5807; port++)
        {
            PortForwarder.add(port, "10.33.9.11", port);
        }

        System.out.println("Robot Initialized!");

    }
}