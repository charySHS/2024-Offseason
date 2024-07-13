package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;


public class Constants
{

    /**
     * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
     * constants. This class should not be used for any other purpose. All constants should be declared
     * globally (i.e. public static). Do not put anything functional in this class.
     *
     * <p>It is advised to statically import this class (or one of its inner classes) wherever the
     * constants are needed, to reduce verbosity.
     */

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

    // Flags for use in Robot.java

    public static final boolean kEnableAdvKit = true;
    public static final boolean kEnableOBlog = false;
    public static final boolean kDisableSubsystemsOnDisableInit = true;


    /*
    General Robot configs
     */

    public static final double joystickDeadband = 0.1;
    public static final double xboxDeadband = 0.05;

    public static final int pcmCanID = 1;
    public static final int pigeonIMU_ID = 19;

    public static final PneumaticsModuleType pcmType = PneumaticsModuleType.REVPH;
    public static final int timeoutMs = 30;

    public static final double kBrownOutVoltage = 5.4; // We ball
    public static final boolean kIgnoreBrownOutVoltage = true;

    public static final int kLogLinesBeforeFlush = 100;

    public static final class FeatureFlags
    {
        // Subsystems
        public static final boolean kEasterEggEnabled = false;
        public static final boolean kIntakeEnabled = true;
        public static final boolean kShooterEnabled = true;
        public static final boolean kSwerveEnabled = true;
        public static final boolean kPivotEnabled = true;
        public static final boolean kClimbEnabled = true;

        // Logging
        public static final boolean kDebugEnabled = false;
        public static final boolean DebugCommandEnabled = false;
        public static final boolean kRobotVisEnabled = true && !Robot.isReal();

        // Features
        public static final boolean autoAlignEnabled = false;
        public static final boolean LOCALIZATION_ENABLED = false;
        public static final boolean kSwerveUseVisionForPoseEst = true;
        public static final boolean kLocalizationDataCollectionMode = false;
        public static final boolean kLocalizationStdDistanceBased = false;

        public static final boolean kSwerveVelocityLimitingEnabled = false;
        public static final boolean kIntakeAutoScoreDistanceSensorOffset = false;
        public static final boolean kShuffleboardLayoutEnabled = true; // TODO: use this
        public static final boolean kGamePieceDetection = false;
        public static final boolean kUsePrefs = true;

        public static final boolean kPitRoutineEnabled = false;

        public static final boolean kCanTestEnabled = false;
        public static final boolean kResetHeadingOnZeroGyro = true;
        public static final boolean kQuadraticDrive = false;
    }

    public static final class ShuffleboardConstants {
        public static final String kDriverTabName = "Driver";
        public static final String kOperatorTabName = "Operator";
        public static final String kIntakeLayoutName = "Intake";
        public static final String kSwerveLayoutName = "Swerve";
        public static final String kArmLayoutName = "Arm";
        public static final String kShooterLayoutName = "Shooter";
    }
}
