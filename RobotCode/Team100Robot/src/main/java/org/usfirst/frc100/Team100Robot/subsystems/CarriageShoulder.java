// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc100.Team100Robot.subsystems;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderDefault;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderHoming;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderLevel;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderTeleop;

/**
 *
 */
public class CarriageShoulder extends Subsystem {

    public int currentSetpointIndex = 0;
    public int currentSetpoint = -1;
    public boolean POST_TO_NT_PREFERENCES = false;

    public static final int HOMING_SETPOINT = 14/*was 14*/; //Degrees from zero
    public static final int LEVEL_SETPOINT = 0;
    public static final int DOWN_SETPOINT = 130;

    public WPI_TalonSRX carriageShoulderMotor;

    public CarriageShoulder() {
        if(POST_TO_NT_PREFERENCES){
            Preferences.getInstance().putDouble("SHOULDER_KP", Constants.SHOULDER_KP);
            Preferences.getInstance().putDouble("SHOULDER_KI", Constants.SHOULDER_KI);
            Preferences.getInstance().putDouble("SHOULDER_KD", Constants.SHOULDER_KD);
            Preferences.getInstance().putDouble("SHOULDER_KF", Constants.SHOULDER_KF);
        }
        System.out.println("NINETY CHECK: " + degreesToSetpointConverter(90));
        carriageShoulderMotor = new WPI_TalonSRX(Constants.ELEVATOR_CARRIAGE_SHOULDER_CANID);
        carriageShoulderMotor.configFactoryDefault();
        carriageShoulderMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.SHOULDER_MASTER_TIMEOUT);
        carriageShoulderMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 1, Constants.SHOULDER_MASTER_TIMEOUT);

        carriageShoulderMotor.setInverted(false);
        carriageShoulderMotor.setSensorPhase(false);
        carriageShoulderMotor.configPeakOutputForward(Constants.SHOULDER_MAX_OUTPUT_FORWARD);
        carriageShoulderMotor.configPeakOutputReverse(Constants.SHOULDER_MAX_OUTPUT_REVERSE);
        carriageShoulderMotor.configNominalOutputForward(0);
        carriageShoulderMotor.configNominalOutputReverse(0);
        carriageShoulderMotor.configAllowableClosedloopError(0, Constants.SHOULDER_BUFFER, Constants.SHOULDER_MASTER_TIMEOUT);
        carriageShoulderMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0,10,Constants.SHOULDER_MASTER_TIMEOUT);
        carriageShoulderMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,Constants.SHOULDER_MASTER_TIMEOUT);
        carriageShoulderMotor.configMotionCruiseVelocity(32767,Constants.SHOULDER_MASTER_TIMEOUT);
        carriageShoulderMotor.configMotionAcceleration(32767,Constants.SHOULDER_MASTER_TIMEOUT);
        carriageShoulderMotor.configFeedbackNotContinuous(true, Constants.SHOULDER_MASTER_TIMEOUT);
        carriageShoulderMotor.enableCurrentLimit(true);
        carriageShoulderMotor.configPeakCurrentLimit(Constants.SHOULDER_MAX_AMP);
        carriageShoulderMotor.enableVoltageCompensation(true);
        carriageShoulderMotor.configVoltageCompSaturation(Constants.SHOULDER_MAX_VOLTAGE_COMPENSATE);
        carriageShoulderMotor.overrideLimitSwitchesEnable(false);
        carriageShoulderMotor.configForwardSoftLimitEnable(true);
        carriageShoulderMotor.configForwardSoftLimitThreshold(Constants.SHOULDER_UPPER_ENCODER_VALUE);
        carriageShoulderMotor.configReverseSoftLimitThreshold(Constants.SHOULDER_DOWN_DEGREES);
        carriageShoulderMotor.selectProfileSlot(0, 0);
        carriageShoulderMotor.config_kP(0, Constants.SHOULDER_KP);
        carriageShoulderMotor.config_kI(0, Constants.SHOULDER_KI);
        carriageShoulderMotor.config_kD(0, Constants.SHOULDER_KD);
        carriageShoulderMotor.config_kF(0, Constants.SHOULDER_KF);
        resetRelativeEncoder();
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        /*currentSetpointIndex = 0;
        this.updateSetpointGivenIndex();*/
        //setDefaultCommand(new ShoulderHoming());

        setDefaultCommand(new ShoulderDefault());
        //setDefaultCommand(new ShoulderTeleop());
    }

    @Override
    public void periodic() {
        //System.out.println("IN PERIODIC");
        this.carriageShoulderMotor.config_kP(0,Preferences.getInstance().getDouble("SHOULDER_KP",Constants.SHOULDER_KP));
        this.carriageShoulderMotor.config_kI(0,Preferences.getInstance().getDouble("SHOULDER_KI",Constants.SHOULDER_KI));
        this.carriageShoulderMotor.config_kD(0,Preferences.getInstance().getDouble("SHOULDER_KD",Constants.SHOULDER_KD));
        this.carriageShoulderMotor.config_kF(0,Preferences.getInstance().getDouble("SHOULDER_KF",Constants.SHOULDER_KF));
        SmartDashboard.putNumber("Prefs P",Preferences.getInstance().getDouble("SHOULDER_KP",-1));
        SmartDashboard.putData("Shoulder Level", new ShoulderLevel());
        SmartDashboard.putString("CarriageShoulder Current Command",this.getCurrentCommandName());

        SmartDashboard.putNumber("Shoulder Enc",this.carriageShoulderMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("Shoulder PO", this.carriageShoulderMotor.getMotorOutputPercent());
        //SmartDashboard.putString("Shoulder CM", this.carriageShoulderMotor.getControlMode().toString());
        //SmartDashboard.putNumber("Shoulder Setpoint",this.currentSetpoint);
        SmartDashboard.putData("setToHome",new ShoulderHoming());
        //SmartDashboard.putString("Shoulder Control Mode",this.carriageShoulderMotor.getControlMode().toString());
        SmartDashboard.putNumber("Shoulder ENC Value", this.carriageShoulderMotor.getSelectedSensorPosition());
        if(this.carriageShoulderMotor.getControlMode() == ControlMode.MotionMagic){
            SmartDashboard.putNumber("Shoulder Error", this.carriageShoulderMotor.getClosedLoopError());
            SmartDashboard.putNumber("Shoulder Vel",this.carriageShoulderMotor.getSelectedSensorVelocity());
            //SmartDashboard.putNumber("Shoulder Motor Setpoint",this.carriageShoulderMotor.getActiveTrajectoryPosition());
        }
    }

    public static int degreesToSetpointConverter(double degrees){
      
        double step1 = (degrees - Constants.SHOULDER_DOWN_DEGREES); // Get the distance from the bottom to go
        double step2 = (step1*Constants.SHOULDER_ENCODER_TICKS_PER_DEGREE_TRAVEL); // Get the 
        double step3 = step2+Constants.SHOULDER_DOWN_ENCODER_VALUE;
        return (int)step3;
    }
    public void resetRelativeEncoder(){
        this.carriageShoulderMotor.setSelectedSensorPosition(this.carriageShoulderMotor.getSensorCollection().getPulseWidthPosition());
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    /*public void updateSetpointGivenIndex(){
        this.currentSetpoint = this.setpoints[this.currentSetpointIndex];
        //this.carriageShoulderMotor.set(ControlMode.MotionMagic,this.currentSetpoint);
    }
    public void moveUp(){
        if(this.currentSetpoint < this.setpoints.length - 1){
            this.currentSetpoint += 1;
        }
        this.updateSetpointGivenIndex();
    }
    public void moveDown(){
        if(this.currentSetpoint > 0){
            this.currentSetpoint -= 1;
        }
        this.updateSetpointGivenIndex();
    }*/

    public void updateSetpoint(int setpoint){
        this.currentSetpoint = setpoint;
        this.carriageShoulderMotor.set(ControlMode.MotionMagic,setpoint);
        //System.out.println("***************************"+this.carriageShoulderMotor.getControlMode().toString());
    }

    
}

