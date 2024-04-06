package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.pathplanner.lib.util.PIDConstants;


public class Constants
{

    public enum RioCanBusIDs
    {
        RoboRIO,
        PDH,
        FeederMotor
    }

    public enum CanivoreBusIDs
    {
        ArmLeft,
        ArmRight,
        IntakePivot,
        Drive_BL,
        Drive_BR,
        Drive_FL,
        Drive_FR,
        Steer_BL,
        Steer_BR,
        Steer_FL,
        Steer_FR,
        CanCoder_BL,
        CanCoder_BR,
        CanCoder_FL,
        CanCoder_FR,
        Pigeon,
        IntakeMotor;

        static public final String BusName = "CANivore";

        public int GetID() {
            return ordinal() + 1;
        }
    }


}
