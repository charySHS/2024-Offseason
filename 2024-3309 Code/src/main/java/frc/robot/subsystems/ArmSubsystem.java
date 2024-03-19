package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.controls.*;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import java.security.PrivateKey;

public class ArmSubsystem extends SubsystemBase
{
    // -- Variables for arm/pivot movement
    private static final double ArmTolerance = 15.0 / 360.0;
    private static final double LowerLimit = -0.12;
    private static final double UpperLimit = 0.28;
    private final double PivotTolerance = 15.0 / 360.0;
    static private final double PivotLimitForward = 0.325;
    static private final double PivotLimitReverse = -0.31;

    // Since we zero on the hard stop, add this buffer to when going home so we don't slam into the stop.
    static private final double PivotLimitReverseBuffer = 0.02;
    
    private double ManualArmControlTarget = 0;

    public enum EArmPosition
    {
        Stowed(LowerLimit),
        ShootSpeaker(-0.076),
        ShootPodium(LowerLimit),
        ShootWing(LowerLimit),
        ClimbFirstPos(UpperLimit),
        Amp(0.169),
        Trap(LowerLimit),
        Source(-0.05);
        
        private final double Rotations;
        
        EArmPosition(double rotations){ Rotations = rotations; }
    }
    
    public enum EPivotPosition
    {
        Stowed(PivotLimitReverse + PivotLimitReverseBuffer),
        ShootSpeaker(PivotLimitReverse),
        Climb(-0.25),
        Amp(0.075),
        Trap(PivotLimitReverse + PivotLimitReverseBuffer),
        Source(-0.237),
        Unstick(-0.166);
        
        private final double Rotations;
        EPivotPosition(double rotations){ Rotations = rotations; }
    }
    
    // -- Motors
    private TalonFX PivotMotor;
    private TalonFX LeftArmMotor;
    private TalonFX RightArmMotor;
    
    // -- PhoenixRequests;
    private final MotionMagicExpoTorqueCurrentFOC PoseRequest = 
        new MotionMagicExpoTorqueCurrentFOC(LowerLimit)
            .withSlot(0);
    private final PositionTorqueCurrentFOC ClimbRequest = 
        new PositionTorqueCurrentFOC(LowerLimit)
            .withSlot(0);
    private final MotionMagicExpoTorqueCurrentFOC PivotRequest =
        new MotionMagicExpoTorqueCurrentFOC(0);
    
    public ArmSubsystem()
    {
        PivotMotor = CreateMotor(Constants.CanivoreBusIDs.LeftArm.GetID());
    }
    
}
